/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.FlooringMastery.dao;

import com.sg.FlooringMastery.dto.order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author colby
 */
public interface FlooringMasteryDao {
    void addOrder(order newOrder,LocalDate date) throws FlooringMasteryException;
    List<order> displayOrders(LocalDate date) throws FlooringMasteryException;
    void saveProgres() throws FlooringMasteryException;
    void removeOrder(int removeOrder,LocalDate date) throws FlooringMasteryException;
    order editOrder(int orderNumber, order newOrder,LocalDate date) throws FlooringMasteryException;
    order getOrder(int orderNumber,LocalDate date) throws FlooringMasteryException;
    int readNumber() throws FlooringMasteryException;
    void writeNumber(int orderNumber) throws FlooringMasteryException;
    
  
}
