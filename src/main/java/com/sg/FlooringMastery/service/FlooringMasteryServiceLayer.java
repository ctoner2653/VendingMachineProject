/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.FlooringMastery.service;

import com.sg.FlooringMastery.dao.FlooringMasteryException;
import com.sg.FlooringMastery.dto.order;
import com.sg.FlooringMastery.dto.product;
import com.sg.FlooringMastery.dto.tax;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author colby
 */
public interface FlooringMasteryServiceLayer {
    List<order> displayOrders(LocalDate date) throws FlooringMasteryException;
    void addOrder(order newOrder,LocalDate date) throws FlooringMasteryException;
    order editOrder(int orderNumber, order newOrder,LocalDate date) throws FlooringMasteryException;
    void removeOrder(int orderNumber,LocalDate date) throws FlooringMasteryException;
    void saveProgress() throws FlooringMasteryException;
    order getOrder(int orderNumber,LocalDate date) throws FlooringMasteryException;
    List<product> getAllProducts() throws FlooringMasteryException;
    List<tax> getAllStates() throws FlooringMasteryException;
    
}
