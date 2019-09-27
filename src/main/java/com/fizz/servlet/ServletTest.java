package com.fizz.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Fizz
 * @since 2019/9/19 15:04
 */
@WebServlet(urlPatterns = "/servlet")
public class ServletTest implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        count = new AtomicLong(0);
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    private AtomicLong count;

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        if ("xll".equals(servletRequest.getParameter("name"))) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        PrintWriter pw = servletResponse.getWriter();
        pw.write("第count次访问：" + count.incrementAndGet());
        pw.println();
        pw.write(Thread.currentThread().toString());
        pw.println();
        pw.write(this.toString());
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
