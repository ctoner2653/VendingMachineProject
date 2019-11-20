/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.VendingMachine.ui;

import com.sg.VendingMachine.dao.VendingMachineDao;
import com.sg.VendingMachine.dao.VendingMachineDaoFileImpl;
import com.sg.VendingMachine.dao.VendingMachineException;
import com.sg.VendingMachine.dto.Item;
import com.sg.VendingMachine.dto.VendingMachineChange;
import com.sg.VendingMachine.service.InsufficientFundsException;
import com.sg.VendingMachine.service.NoItemInventoryException;
import com.sg.VendingMachine.service.VendingMachineServiceLayer;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author colby
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    BigDecimal money = new BigDecimal("0");
    VendingMachineDao dao = new VendingMachineDaoFileImpl();

    public VendingMachineServiceLayerImpl(VendingMachineDao Dao) {
        //this.dao = dao;
    }

    @Override
    public BigDecimal insertMoney(BigDecimal moneyIn) {

        money = money.add(moneyIn);
        return money;
    }

    /**
     *
     * @return
     */
    @Override
    public VendingMachineChange recieveChange() {
       VendingMachineChange change = new VendingMachineChange();
       while(money.compareTo(new BigDecimal("0.25")) >= 0){
           
            change.setQuarter(change.getQuarter() + 1);
            money = money.subtract(new BigDecimal("0.25"));
       }
       while(money.compareTo(new BigDecimal("0.1")) >= 0){
           
            change.setDime(change.getDime() + 1);
            money = money.subtract(new BigDecimal("0.1"));
       }
       while(money.compareTo(new BigDecimal("0.05")) >= 0){
           
            change.setNickel(change.getNickel() + 1);
            money = money.subtract(new BigDecimal("0.05"));
       }
       while(money.compareTo(new BigDecimal("0.01")) >= 0){
           
            change.setPenny(change.getPenny() + 1);
            money = money.subtract(new BigDecimal("0.01"));
       }
        return change;
    }

    @Override
    public BigDecimal getBalance() {
        return money;
    }

    @Override
    public void purchaseItem(Item item) throws InsufficientFundsException, NoItemInventoryException {
            
     
        double money1 = money.doubleValue();
        double price1 = item.getPrice().doubleValue();
        if(money1 > price1){
            money1 = money1 - price1;
        }else if(money1 < price1){
            throw new InsufficientFundsException("You do not have enough money to purchase " + item.getName() + " Please Insert More Money.");
        }else if(money1 == price1){
           money1 = money1 - price1;
        }
        
        if(item.getQuanity() <= 0){
            throw new NoItemInventoryException("We do not currently have any of " + item.getName() + " in stock.");
        }
        
        money = BigDecimal.valueOf(money1);
        
        editItem(item);
        
    }
    
    @Override
    public Item getItem(String name) throws VendingMachineException{
            if(dao.getItem(name) == null){
        throw new VendingMachineException(
                    "Please Enter Valid Entry. " +
                            name + " is not a valid entry.");
    }
       return dao.getItem(name);
    }
    @Override
    public List<Item> getAllItems(){
        return dao.getAll();
    }
    public void editItem(Item item){
        int newQuanity = item.getQuanity() - 1;
        item.setQuanity(newQuanity);
        dao.editItem(item.getName(), item);
    }
}
