/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.VendingMachine.service;

import com.sg.VendingMachine.dao.VendingMachineException;
import com.sg.VendingMachine.dto.Item;
import com.sg.VendingMachine.dto.VendingMachineChange;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author colby
 */
public interface VendingMachineServiceLayer {
     BigDecimal insertMoney(BigDecimal moneyIn); 
    
     VendingMachineChange recieveChange();
     
     BigDecimal getBalance();
     
     void purchaseItem(Item item) throws  InsufficientFundsException,NoItemInventoryException;
     
     Item getItem(String name)  throws VendingMachineException ;
     
     List<Item> getAllItems();
}
