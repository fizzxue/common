package com.fizz.proxy.dynamicproxy;

import com.fizz.proxy.staticproxy.LoLPlayer;
import com.fizz.proxy.staticproxy.Player;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Fizz
 * @since 2019/11/7 15:10
 */
public class Test {

    private Object target;

    public Test(Object target) {
        this.target = target;
    }

    public Object getProxy() {
        Object o = Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("i am proxy player");
                        return method.invoke(target, args);
                    }
                });
        return o;
    }

    public static void main(String[] args) {
        LoLPlayer target = new LoLPlayer();
        Test test = new Test(target);
        Player proxy = (Player) test.getProxy();
        proxy.playGame();
    }
}
