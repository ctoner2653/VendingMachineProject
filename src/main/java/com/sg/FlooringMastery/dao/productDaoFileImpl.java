/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.FlooringMastery.dao;

import com.sg.FlooringMastery.dto.product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author colby
 */
public class productDaoFileImpl implements productDao {
    
    public final static String PRODUCT_FILE = "product.txt";
    public final static String DELIMITER = ",";
    private Map<String, product> products = new HashMap<>();
    
    @Override
    public product getProduct(String productType) throws FlooringMasteryException{
        readFile();
        return products.get(productType);
    }
    
    
     public void readFile() throws FlooringMasteryException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(new FileReader(PRODUCT_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryException("Unable to read from file",e);

        }
        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            product currentProduct = new product();
               
            currentProduct.setProductType(currentTokens[0]);
            currentProduct.setCostPerSquareFoot(Double.parseDouble(currentTokens[1]));
            currentProduct.setLaborCostPerSquareFoot(Double.parseDouble(currentTokens[2]));

            products.put(currentProduct.getProductType(), currentProduct);

        }
        scanner.close();
    }
    
    
    
    
    
    
}
