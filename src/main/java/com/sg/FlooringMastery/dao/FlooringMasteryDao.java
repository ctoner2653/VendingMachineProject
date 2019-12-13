/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.FlooringMastery.dao;

import com.sg.FlooringMastery.dto.order;
import java.util.List;

/**
 *
 * @author colby
 */
public interface FlooringMasteryDao {
    void addOrder(order newOrder) throws FlooringMasteryException;
    List<order> displayOrders() throws FlooringMasteryException;
    void saveProgres() throws FlooringMasteryException;
    void removeOrder(int removeOrder) throws FlooringMasteryException;
    order editOrder(int orderNumber, order newOrder) throws FlooringMasteryException;
    order getOrder(int orderNumber) throws FlooringMasteryException;
}
