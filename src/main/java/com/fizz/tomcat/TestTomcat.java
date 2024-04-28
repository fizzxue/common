package com.fizz.tomcat;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.loader.ParallelWebappClassLoader;
import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.Tomcat.FixContextListener;
import org.springframework.util.ClassUtils;

/**
 * <a href="https://www.cnblogs.com/strongmore/p/18038815">原博客</a>
 * <p>
 * 上述代码完全是参考 SpringBoot 内创建并启动 Tomcat 的过程，具体流程如下
 * 1. SpringBoot 默认使用的 ApplicationContext 实现类为 AnnotationConfigServletWebServerApplicationContext，
 * 具体判断逻辑为 ApplicationContextFactory 的 DEFAULT。
 * 2. AnnotationConfigServletWebServerApplicationContext 继承 ServletWebServerApplicationContext 的 onRefresh() 方法，
 * 通过 TomcatServletWebServerFactory 的 getWebServer() 方法来创建 WebServer，在这个过程中就会创建 Tomcat 对象并启动
 * <p>
 * 每一个Context都是一个项目，可以有单独的contextPath，每一个Connector都是一个监听端口
 */
public class TestTomcat {

    private static final String DEFAULT_PROTOCOL = "org.apache.coyote.http11.Http11NioProtocol";

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        File baseDir = createTempDir("tomcat");
        tomcat.setBaseDir(baseDir.getAbsolutePath());
        Connector connector = new Connector(DEFAULT_PROTOCOL);
        Connector connector2 = new Connector(DEFAULT_PROTOCOL);
        tomcat.getService().addConnector(connector);
        tomcat.getService().addConnector(connector2);
        customizeConnector(connector);
        customizeConnector2(connector2);
        tomcat.setConnector(connector);
        tomcat.getHost().setAutoDeploy(false);
        prepareContext(tomcat.getHost());
        prepareContext2(tomcat.getHost());
        tomcat.start();
    }

    private static void prepareContext(Host host) {
        StandardContext context = new StandardContext();
        context.setName(getContextPath());
        context.setPath(getContextPath());
        File docBase = createTempDir("tomcat-docbase");
        context.setDocBase(docBase.getAbsolutePath());
        context.addLifecycleListener(new FixContextListener());
        context.setParentClassLoader(ClassUtils.getDefaultClassLoader());
        context.setUseRelativeRedirects(false);
        try {
            context.setCreateUploadTargets(true);
        } catch (NoSuchMethodError ex) {
            // Tomcat is < 8.5.39. Continue.
        }
        WebappLoader loader = new WebappLoader(context.getParentClassLoader());
        loader.setLoaderClass(ParallelWebappClassLoader.class.getName());
        loader.setDelegate(true);
        context.setLoader(loader);
        addDefaultServlet(context);
        host.addChild(context);
        configureContext(context);
    }

    private static void prepareContext2(Host host) {
        StandardContext context = new StandardContext();
        context.setName(getContextPath() + "2");
        context.setPath(getContextPath() + "2");
        File docBase = createTempDir("tomcat-docbase2");
        context.setDocBase(docBase.getAbsolutePath());
        context.addLifecycleListener(new FixContextListener());
        context.setParentClassLoader(ClassUtils.getDefaultClassLoader());
        context.setUseRelativeRedirects(false);
        try {
            context.setCreateUploadTargets(true);
        } catch (NoSuchMethodError ex) {
            // Tomcat is < 8.5.39. Continue.
        }
        WebappLoader loader = new WebappLoader(context.getParentClassLoader());
        loader.setLoaderClass(ParallelWebappClassLoader.class.getName());
        loader.setDelegate(true);
        context.setLoader(loader);
        addDefaultServlet(context);
        host.addChild(context);
        configureContext2(context);
    }

    private static void configureContext(Context context) {
        context.addServletContainerInitializer(new ServletContainerInitializer() {
            @Override
            public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
                Dynamic registration = ctx.addServlet("myServlet", new MyServlet());
                registration.setLoadOnStartup(1);
                registration.addMapping("/myServlet");
            }
        }, new HashSet<>());
    }

    private static void configureContext2(Context context) {
        context.addServletContainerInitializer(new ServletContainerInitializer() {
            @Override
            public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
                Dynamic registration = ctx.addServlet("myServlet2", new MyServlet());
                registration.setLoadOnStartup(1);
                registration.addMapping("/myServlet2");
            }
        }, new HashSet<>());
    }

    private static class MyServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {
            resp.getWriter().println("hello");
            resp.getWriter().flush();
        }
    }

    private static void addDefaultServlet(Context context) {
        Wrapper defaultServlet = context.createWrapper();
        defaultServlet.setName("default");
        defaultServlet.setServletClass("org.apache.catalina.servlets.DefaultServlet");
        defaultServlet.addInitParameter("debug", "0");
        defaultServlet.addInitParameter("listings", "false");
        defaultServlet.setLoadOnStartup(1);
        // Otherwise the default location of a Spring DispatcherServlet cannot be set
        defaultServlet.setOverridable(true);
        context.addChild(defaultServlet);
        context.addServletMappingDecoded("/", "default");
    }

    private static void customizeConnector(Connector connector) {
        int port = Math.max(getPort(), 0);
        connector.setPort(port);
        connector.setURIEncoding(StandardCharsets.UTF_8.name());
        // Don't bind to the socket prematurely if ApplicationContext is slow to start
        connector.setProperty("bindOnInit", "false");
    }

    private static void customizeConnector2(Connector connector) {
        int port = Math.max(getPort2(), 0);
        connector.setPort(port);
        connector.setURIEncoding(StandardCharsets.UTF_8.name());
        // Don't bind to the socket prematurely if ApplicationContext is slow to start
        connector.setProperty("bindOnInit", "false");
    }

    private static File createTempDir(String prefix) {
        try {
            File tempDir = File.createTempFile(prefix + ".", "." + getPort());
            tempDir.delete();
            tempDir.mkdir();
            tempDir.deleteOnExit();
            return tempDir;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static String getContextPath() {
        return "/testtomcat";
    }

    private static int getPort() {
        return 8989;
    }

    private static int getPort2() {
        return 8990;
    }
}
