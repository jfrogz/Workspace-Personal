package com.jfrogz.spring;

import com.jfrogz.beans.Mundo;
import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/jfrogz/xml/beans.xml");
        Mundo m = (Mundo) applicationContext.getBean("mundo");

        System.out.println(m.getSaludo());

        ((ConfigurableApplicationContext)applicationContext).close();
    }
}
