/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.FlooringMastery.dto;

/**
 *
 * @author colby
 */
public class tax {
    /*State,TaxRate
OH,6.25
PA,6.75
MI,5.75
IN,6.00
*/
    String state;
    double taxRate;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }
    

}
