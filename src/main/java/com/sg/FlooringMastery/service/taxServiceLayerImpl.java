/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.FlooringMastery.service;

import com.sg.FlooringMastery.dao.FlooringMasteryException;
import com.sg.FlooringMastery.dao.taxDao;
import com.sg.FlooringMastery.dto.tax;

/**
 *
 * @author colby
 */
public class taxServiceLayerImpl implements taxServiceLayer {
    
    taxDao dao; 
    public taxServiceLayerImpl(taxDao dao){
        this.dao = dao;
    }
    @Override
    public tax getTax(String state) throws FlooringMasteryException{
        return dao.getTax(state);
    }
    
}
