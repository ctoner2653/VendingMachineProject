/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.FlooringMastery.dao;


import com.sg.FlooringMastery.dto.order;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author colby
 */
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao{
    
    public final static String ORDER_FILE = "order.txt";
    public final static String DELIMITER = ",";
    private Map<Integer,order> orders = new HashMap<>();
    @Override
    public void addOrder(order newOrder) throws FlooringMasteryException{
        orders.put(newOrder.getOrderNumber(), newOrder);
      
    }
    @Override
    public void removeOrder(int removeOrder) throws FlooringMasteryException{
        orders.remove(removeOrder);
        writeFile();
    }
    @Override
    public List<order> displayOrders() throws FlooringMasteryException{
        try{
              readFile();
        }catch(Exception e){
            throw new FlooringMasteryException("No data in file, please add more orders",e);
        }
          
        return new ArrayList<order>(orders.values());
    }
    
     @Override
    public order editOrder(int orderNumber, order newOrder) throws FlooringMasteryException {
        readFile();
        orders.put(orderNumber,newOrder);
        writeFile();
        return orders.get(orderNumber);
    }
    
    public void saveProgres() throws FlooringMasteryException{
        writeFile();
    }
    public void readFile() throws FlooringMasteryException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(new FileReader(ORDER_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryException("Unable to read from file",e);

        }
        String currentLine;

        String[] currentTokens;
        int i = 0;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            i = i +1;
            currentTokens = currentLine.split(DELIMITER);

            order currentOrder = new order();
            
            currentOrder.setArea(Double.parseDouble(currentTokens[0]));
            currentOrder.setCostPerSquareFoot(Double.parseDouble(currentTokens[1]));
            currentOrder.setCustomerName(currentTokens[2]);
            currentOrder.setLaborCost(Double.parseDouble(currentTokens[3]));
            currentOrder.setLaborCostPerSquareFoot(Double.parseDouble(currentTokens[4]));
            currentOrder.setMaterialCost(Double.parseDouble(currentTokens[5]));
            currentOrder.setOrderNumber(i);
            currentOrder.setProductType(currentTokens[7]);
            currentOrder.setState(currentTokens[8]);
            currentOrder.setTax(Double.parseDouble(currentTokens[9]));
            currentOrder.setTaxRate(Double.parseDouble(currentTokens[10]));
            currentOrder.setTotal(Double.parseDouble(currentTokens[11]));
            
            
            orders.put(currentOrder.getOrderNumber(), currentOrder);
        }
        scanner.close();
    }
    public void writeFile() throws FlooringMasteryException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ORDER_FILE));
        } catch (IOException e) {
            throw new FlooringMasteryException("Can't write to file",e);
        }
        List<order> orderList = this.displayOrders();
        for (order currentOrder : orderList) {
            out.println(currentOrder.getArea() + DELIMITER +
            currentOrder.getCostPerSquareFoot() + DELIMITER + 
            currentOrder.getCustomerName() + DELIMITER + 
            currentOrder.getLaborCost() + DELIMITER + 
            currentOrder.getLaborCostPerSquareFoot() + DELIMITER + 
            currentOrder.getMaterialCost() + DELIMITER + 
            currentOrder.getOrderNumber() + DELIMITER + 
            currentOrder.getProductType() + DELIMITER + 
            currentOrder.getState() + DELIMITER + 
            currentOrder.getTax() + DELIMITER + 
            currentOrder.getTaxRate() + DELIMITER +
            currentOrder.getTotal());

            out.flush();
          
        }
        out.close();
    }

    @Override
    public order getOrder(int orderNumber) throws FlooringMasteryException {
       readFile();
       return orders.get(orderNumber);
    }
}
