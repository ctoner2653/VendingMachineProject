/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.FlooringMastery.controller;

import com.sg.FlooringMastery.dao.FlooringMasteryException;
import com.sg.FlooringMastery.dto.order;
import com.sg.FlooringMastery.service.FlooringMasteryServiceLayer;
import com.sg.FlooringMastery.ui.FlooringMasteryView;

/**
 *
 * @author colby
 */
public class FlooringMasteryController {
    FlooringMasteryView view;
    FlooringMasteryServiceLayer service;
    
    public FlooringMasteryController(FlooringMasteryView view,FlooringMasteryServiceLayer service){
        this.view = view;
        this.service = service;
    }
    public void run() throws FlooringMasteryException{
        boolean keepGoing = true;
        int menuSelection = 0;
        while(keepGoing){
            menuSelection = getMenuSelection();
            switch(menuSelection){
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
    
    public int getMenuSelection(){
        return view.getMenuSelection();
    }
    public void addOrder() throws FlooringMasteryException{
        order newOrder = view.getOrderInfo();
        service.addOrder(newOrder);
    }
    public void displayOrders() throws FlooringMasteryException{
        view.displayOrders(service.displayOrders());
    }
    public void editOrder() throws FlooringMasteryException {
        order editOrder = view.editOrder(service.getOrder(view.getEditOrder()));
        service.editOrder(editOrder.getOrderNumber(), editOrder);
    }
    public void removeOrder() throws FlooringMasteryException{
        service.removeOrder(view.removeOrder());
    }
    public void saveProgres() throws FlooringMasteryException{
        service.saveProgress();
    }
    public void exit(){
        
    }
}
