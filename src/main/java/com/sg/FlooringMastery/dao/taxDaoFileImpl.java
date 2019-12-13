/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.FlooringMastery.dao;


import com.sg.FlooringMastery.dto.tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author colby
 */
public class taxDaoFileImpl implements taxDao {
    
    public final static String TAX_FILE = "tax.txt";
    public final static String DELIMITER = ",";
    private Map<String,tax> taxRates = new HashMap<>();
    @Override
    public tax getTax(String state) throws FlooringMasteryException {
        readFile();
       return taxRates.get(state);
    }
     public void readFile() throws FlooringMasteryException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(new FileReader(TAX_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryException("Unable to read from file",e);

        }
        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            tax currentProduct = new tax();
               
            currentProduct.setState(currentTokens[0]);
            currentProduct.setTaxRate(Double.parseDouble(currentTokens[1]));
          

            taxRates.put(currentProduct.getState(), currentProduct);

        }
        scanner.close();
    }

    @Override
    public List<tax> getAllStates() throws FlooringMasteryException {
       readFile();
       return new ArrayList<tax>(taxRates.values());
    }
     
}
