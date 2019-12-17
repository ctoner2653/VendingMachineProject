/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.FlooringMastery.controller;

import com.sg.FlooringMastery.dao.FlooringMasteryException;
import com.sg.FlooringMastery.dto.order;
import com.sg.FlooringMastery.dto.product;
import com.sg.FlooringMastery.dto.tax;
import com.sg.FlooringMastery.service.FlooringMasteryServiceLayer;
import com.sg.FlooringMastery.service.FlooringMasteryServiceLayerImpl;
import com.sg.FlooringMastery.ui.FlooringMasteryView;
import java.time.LocalDate;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author colby
 */
public class FlooringMasteryController {

    FlooringMasteryView view;
    FlooringMasteryServiceLayer service;

    public FlooringMasteryController(FlooringMasteryView view, FlooringMasteryServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    public void switchProduction(String selection) {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        if ("Production".equals(selection)) {
            this.service = ctx.getBean("serviceLayer", FlooringMasteryServiceLayerImpl.class);
        } else {
            this.service = ctx.getBean("serviceLayerTest", FlooringMasteryServiceLayerImpl.class);
        }
    }

    public void run() throws FlooringMasteryException {
        boolean keepGoing = true;
        int menuSelection = 0;
        switchProduction(view.testVsProduction());
        while (keepGoing) {
            menuSelection = getMenuSelection();
            switch (menuSelection) {
                case 1:
                    displayOrders();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    saveProgres();
                    break;
                case 6:

                    keepGoing = false;
                    break;
                default:
                    break;
            }
        }
    }

    public int getMenuSelection() {
        return view.getMenuSelection();
    }

    public void addOrder() throws FlooringMasteryException {
        order newOrder = view.getOrderInfo(service.getAllProducts(), service.getAllStates());
        LocalDate date = (view.getDate());
        service.addOrder(newOrder, date);
    }

    public void displayOrders() throws FlooringMasteryException {
        try {
            view.displayOrders(service.displayOrders(view.getDate()));
        } catch (FlooringMasteryException e) {
            view.displayErrorMessage(e.getMessage());
        }

    }

    public void editOrder() throws FlooringMasteryException {
        try {
            LocalDate date = view.getDate();
            List<tax> taxs = service.getAllStates();
            List<product> products = service.getAllProducts();
            order getOrder = service.getOrder(view.getEditOrder(), date);
            order editOrder = view.editOrder(taxs, products, getOrder);
            service.editOrder(editOrder.getOrderNumber(), editOrder, date);
        } catch (FlooringMasteryException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    public void removeOrder() throws FlooringMasteryException {
        LocalDate date = view.getDate();
        service.removeOrder(view.removeOrder(), date);
    }

    public void saveProgres() throws FlooringMasteryException {
        service.saveProgress();
    }

    public void exit() {

    }
}
