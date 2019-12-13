/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.FlooringMastery.service;

import com.sg.FlooringMastery.dao.FlooringMasteryException;
import com.sg.FlooringMastery.dto.tax;
import java.util.List;

/**
 *
 * @author colby
 */
public interface taxServiceLayer {

    tax getTax(String state) throws FlooringMasteryException;
    List<tax> getAllStates() throws FlooringMasteryException;
}
