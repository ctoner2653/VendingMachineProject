package com.sg.FlooringMastery.app;

import com.sg.FlooringMastery.controller.FlooringMasteryController;
import com.sg.FlooringMastery.dao.FlooringMasteryException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author colby
 */
public class app {

    public static void main(String[] args) throws FlooringMasteryException {

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringMasteryController controller
                = ctx.getBean("controller", FlooringMasteryController.class);
        controller.run();

    }

}
