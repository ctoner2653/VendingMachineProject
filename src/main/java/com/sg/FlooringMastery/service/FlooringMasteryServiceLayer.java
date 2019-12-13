/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.FlooringMastery.service;

import com.sg.FlooringMastery.dao.FlooringMasteryException;
import com.sg.FlooringMastery.dto.order;
import java.util.List;

/**
 *
 * @author colby
 */
public interface FlooringMasteryServiceLayer {
    List<order> displayOrders() throws FlooringMasteryException;
    void addOrder(order newOrder) throws FlooringMasteryException;
    order editOrder(int orderNumber, order newOrder) throws FlooringMasteryException;
    void removeOrder(int orderNumber) throws FlooringMasteryException;
    void saveProgress() throws FlooringMasteryException;
    order getOrder(int orderNumber) throws FlooringMasteryException;
    
}
