/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.VendingMachine.App;

import com.sg.VendingMachine.controller.VendingMachineController;
import com.sg.VendingMachine.dao.VendingMachineDao;
import com.sg.VendingMachine.dao.VendingMachineDaoFileImpl;
import com.sg.VendingMachine.dao.VendingMachineException;
import com.sg.VendingMachine.service.InsufficientFundsException;
import com.sg.VendingMachine.service.NoItemInventoryException;
import com.sg.VendingMachine.service.VendingMachineServiceLayer;
import com.sg.VendingMachine.ui.UserIO;
import com.sg.VendingMachine.ui.UserIOConsoleImpl;
import com.sg.VendingMachine.ui.VendingMachineServiceLayerImpl;
import com.sg.VendingMachine.ui.VendingMachineView;

/**
 *
 * @author colby
 */
public class app {

    public static void main(String[] args) throws InsufficientFundsException, NoItemInventoryException , VendingMachineException{
        UserIO io = new UserIOConsoleImpl();
        VendingMachineView view = new VendingMachineView(io);
        VendingMachineDao dao = new VendingMachineDaoFileImpl();
        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(dao);
        VendingMachineController controller = new VendingMachineController(view, service);

        controller.run();
    }
}
