/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.VendingMachine.ui;

import com.sg.VendingMachine.dto.Item;
import com.sg.VendingMachine.dto.VendingMachineChange;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author colby
 */
public class VendingMachineView {
    
    
    UserIO io;
    
    public VendingMachineView(UserIO io){
        this.io = io;
    }
    
    public int printMenuGetSelection(){
        io.print("Welcome to the Vending Machine");
        io.print("1. Insert Money");
        io.print("2. Receive Change");
        io.print("3. Purchase Item");
        io.print("4. View Balance");
        io.print("5. Exit");
        return io.readInt("Enter your selection. ", 1, 5);
    }
    
    public BigDecimal insertMoney(){
        boolean accepted = true;
        BigDecimal moneyIn = new BigDecimal("0");
       
        try{
             moneyIn = new BigDecimal(io.readString("How Much Money Would You Like To Insert?"));
        }catch(Exception e){
            io.readString("Money not accepted, please try again.");
            accepted = false;
        }
       
        if(accepted){
            io.readString("Money Accepted, press Enter to Continue");
        }
        
        return moneyIn;
    }
    public void printcurrentBalance(BigDecimal currentMoney){
          io.print("Current Balance: " + currentMoney);
    }
    
   
    
    public String getItemSelection(){
        return io.readString("Enter your selection");
    }
    
    public void getAllItems(List<Item> itemList){
        for(Item currentItem : itemList){
            io.println(currentItem.getName() + " || ");
            io.println("Price: " + "$" + currentItem.getPrice().toString() + " || ");
            io.print("Quanity: " + Integer.toString(currentItem.getQuanity()));
        }
        
    }
    public void displayErrorMessage(String errorMsg){
        io.print("=== ERROR ===");
        io.readString(errorMsg);
    }
    public void recieveChange(VendingMachineChange change){
        io.print("Quarters: " + change.getQuarter());
        io.print("Dimes: " + change.getDime());
        io.print("Nickles " + change.getNickel());
        io.print("Pennies: " + change.getPenny());
        io.readString("qwuhier");
    }
    public void printeItemPurchased(){
        io.readString("Item succesfully purchased.");
    }
}
