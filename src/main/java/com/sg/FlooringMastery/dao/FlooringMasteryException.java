/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.FlooringMastery.dao;

/**
 *
 * @author colby
 */
public class FlooringMasteryException extends Exception {
    
    public FlooringMasteryException(String message){
        super(message);
    }
    public FlooringMasteryException(String message, Throwable cause){
        super(message,cause);
    }
}
