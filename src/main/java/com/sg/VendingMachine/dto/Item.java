/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.VendingMachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author colby
 */
public class Item {
    
    String Name;
    BigDecimal Price;
    int Quanity;
    
    public Item(String Name){
        this.Name = Name;
    }
    public String getName(){
        return Name;
    }
    public void setPrice(BigDecimal Price){
        this.Price = Price;
    }
    public BigDecimal getPrice(){
        return Price;
    }
    public void setQuanity(int Quanity){
        this.Quanity = Quanity;
    }
    public int getQuanity(){
        return Quanity;
    }
    @Override
    public String toString(){
        return Name;
    }
}
