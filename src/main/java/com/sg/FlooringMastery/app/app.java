package com.sg.FlooringMastery.app;

import com.sg.FlooringMastery.controller.FlooringMasteryController;
import com.sg.FlooringMastery.dao.FlooringMasteryDao;
import com.sg.FlooringMastery.dao.FlooringMasteryDaoFileImpl;
import com.sg.FlooringMastery.dao.FlooringMasteryException;
import com.sg.FlooringMastery.dao.productDao;
import com.sg.FlooringMastery.dao.productDaoFileImpl;
import com.sg.FlooringMastery.dao.taxDao;
import com.sg.FlooringMastery.dao.taxDaoFileImpl;
import com.sg.FlooringMastery.service.FlooringMasteryServiceLayer;
import com.sg.FlooringMastery.service.FlooringMasteryServiceLayerImpl;
import com.sg.FlooringMastery.service.productServiceLayer;
import com.sg.FlooringMastery.service.productServiceLayerImpl;
import com.sg.FlooringMastery.service.taxServiceLayer;
import com.sg.FlooringMastery.service.taxServiceLayerImpl;
import com.sg.FlooringMastery.ui.FlooringMasteryView;
import com.sg.FlooringMastery.ui.UserIo;
import com.sg.FlooringMastery.ui.UserIoConsoleImpl;

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
         UserIo io = new UserIoConsoleImpl();
         FlooringMasteryView view = new FlooringMasteryView(io);
         productDao productDao = new productDaoFileImpl();
         productServiceLayer product = new productServiceLayerImpl(productDao);
         taxDao taxDao = new taxDaoFileImpl();
         taxServiceLayer tax = new taxServiceLayerImpl(taxDao);
         FlooringMasteryDao dao = new FlooringMasteryDaoFileImpl();
         FlooringMasteryServiceLayer service = new FlooringMasteryServiceLayerImpl(product,tax,dao);
         FlooringMasteryController controller = new FlooringMasteryController(view,service);
         controller.run();
         
    }
   
}
