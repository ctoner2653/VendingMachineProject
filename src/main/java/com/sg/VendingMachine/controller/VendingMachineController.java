/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.VendingMachine.controller;

import com.sg.VendingMachine.dao.VendingMachineException;
import com.sg.VendingMachine.service.InsufficientFundsException;
import com.sg.VendingMachine.service.NoItemInventoryException;
import com.sg.VendingMachine.service.VendingMachineServiceLayer;
import com.sg.VendingMachine.ui.VendingMachineView;

/**
 *
 * @author colby
 */
public class VendingMachineController {

    VendingMachineView view;
    VendingMachineServiceLayer service;

    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    public void run()  {
        boolean keepGoing = true;
        int menuSelection = 0;

        while (keepGoing) {
            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    acceptMoney();
                    break;
                case 2:
                    giveChange();
                    break;
                case 3:
                    purchaseItem();
                    break;
                case 4:
                    getBalance();
                    break;
                case 5:
                    keepGoing = false;
                    break;
                default:
                    break;

            }
        }
    }

    public int getMenuSelection() {
        return view.printMenuGetSelection();

    }

    public void acceptMoney() {
        service.insertMoney(view.insertMoney());

    }

    public void getBalance() {
        view.printcurrentBalance(service.getBalance());
    }

    public void purchaseItem() {
        view.getAllItems(service.getAllItems());

        try {
            service.purchaseItem(service.getItem(view.getItemSelection()));
            view.printeItemPurchased();

        } catch (VendingMachineException | InsufficientFundsException | NoItemInventoryException e) {

            view.displayErrorMessage(e.getMessage());

        }

    }

    public void giveChange() {
        view.recieveChange(service.recieveChange());
    }
}
