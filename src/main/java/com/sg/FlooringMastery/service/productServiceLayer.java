/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.FlooringMastery.service;

import com.sg.FlooringMastery.dao.FlooringMasteryException;
import com.sg.FlooringMastery.dto.product;
import java.util.List;

/**
 *
 * @author colby
 */
public interface productServiceLayer {
        product getProduct(String productType) throws FlooringMasteryException;
        List<product> getAllProducts() throws FlooringMasteryException;
}
