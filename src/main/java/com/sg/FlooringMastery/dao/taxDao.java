/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.FlooringMastery.dao;

import com.sg.FlooringMastery.dto.tax;

/**
 *
 * @author colby
 */
public interface taxDao {
    tax getTax(String state) throws FlooringMasteryException;
}
