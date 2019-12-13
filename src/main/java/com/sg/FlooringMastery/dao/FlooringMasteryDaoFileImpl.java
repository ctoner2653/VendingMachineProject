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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author colby
 */

public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {

    public final static String NUMBER_FILE = "number.txt";
    public final static String DELIMITER = ",";
    private Map<Integer, order> orders = new HashMap<>();
    private LocalDate date;
    private Integer number;
    private boolean isEdit = false;

    @Override
    public void addOrder(order newOrder, LocalDate date) throws FlooringMasteryException {
        this.date = date;
        orders.put(newOrder.getOrderNumber(), newOrder);
    }

    @Override
    public void removeOrder(int removeOrder,LocalDate date) throws FlooringMasteryException {
        this.date = date;
        readFile(date);
        orders.remove(removeOrder);
        isEdit = true;
        writeFile();
    }

    @Override
    public List<order> displayOrders(LocalDate date) throws FlooringMasteryException {
        try {
            readFile(date);
        } catch (FlooringMasteryException e) {
            throw new FlooringMasteryException("No data in file, please add more orders");
        }
        return new ArrayList<>(orders.values());
    }

    @Override
    public order editOrder(int orderNumber, order newOrder, LocalDate date) throws FlooringMasteryException {
        this.date = date;
        readFile(date);
        orders.put(orderNumber, newOrder);
        isEdit = true;
        writeFile();
        return orders.get(orderNumber);
    }

    @Override
    public void saveProgres() throws FlooringMasteryException {
        writeFile();
    }

    public void readFile(LocalDate date) throws FlooringMasteryException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(new FileReader(date.toString())));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryException("There is no orders on the select date", e);

        }
        try {
            String currentLine;

            String[] currentTokens;

            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();

                currentTokens = currentLine.split(DELIMITER);

                order currentOrder = new order();

                currentOrder.setArea(Double.parseDouble(currentTokens[0]));
                currentOrder.setCostPerSquareFoot(Double.parseDouble(currentTokens[1]));
                currentOrder.setCustomerName(currentTokens[2]);
                currentOrder.setLaborCost(Double.parseDouble(currentTokens[3]));
                currentOrder.setLaborCostPerSquareFoot(Double.parseDouble(currentTokens[4]));
                currentOrder.setMaterialCost(Double.parseDouble(currentTokens[5]));
                currentOrder.setOrderNumber(Integer.parseInt(currentTokens[6]));
                currentOrder.setProductType(currentTokens[7]);
                currentOrder.setState(currentTokens[8]);
                currentOrder.setTax(Double.parseDouble(currentTokens[9]));
                currentOrder.setTaxRate(Double.parseDouble(currentTokens[10]));
                currentOrder.setTotal(Double.parseDouble(currentTokens[11]));

                orders.put(currentOrder.getOrderNumber(), currentOrder);
            }
        } catch (NumberFormatException e) {
            throw new FlooringMasteryException("Unable to read from file", e);
        }

        scanner.close();
    }

    public void writeFile() throws FlooringMasteryException {

        PrintWriter writer;
        if (isEdit == true) {

            try {
                writer = new PrintWriter(date.toString());
            } catch (IOException e) {
                throw new FlooringMasteryException("Unable to create file", e);
            }
        } else {
            try {
                writer = new PrintWriter(new FileWriter(date.toString(), true));
            } catch (IOException e) {
                throw new FlooringMasteryException("Unable to create file", e);
            }
        }
        List<order> orderList;
        orderList = new ArrayList<>(orders.values());
        for (order currentOrder : orderList) {
            writer.println(currentOrder.getArea() + DELIMITER
                    + currentOrder.getCostPerSquareFoot() + DELIMITER
                    + currentOrder.getCustomerName() + DELIMITER
                    + currentOrder.getLaborCost() + DELIMITER
                    + currentOrder.getLaborCostPerSquareFoot() + DELIMITER
                    + currentOrder.getMaterialCost() + DELIMITER
                    + currentOrder.getOrderNumber() + DELIMITER
                    + currentOrder.getProductType() + DELIMITER
                    + currentOrder.getState() + DELIMITER
                    + currentOrder.getTax() + DELIMITER
                    + currentOrder.getTaxRate() + DELIMITER
                    + currentOrder.getTotal());

            writer.flush();

        }
        writer.close();
        isEdit = false;
        orders.clear();
    }

    @Override
    public order getOrder(int orderNumber, LocalDate date) throws FlooringMasteryException {
        readFile(date);
        return orders.get(orderNumber);
    }

    public int readNumber() throws FlooringMasteryException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(new FileReader(NUMBER_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryException("There is no orders on the select date", e);

        }

        int number = 0;
        try {
            String currentLine = scanner.nextLine();
            number = Integer.parseInt(currentLine);
        } catch (Exception e) {
            number = 0;
        }
        this.number = number;
        return number;
    }

    public void writeNumber(int orderNumber) throws FlooringMasteryException {
        PrintWriter writer;
        try {
            writer = new PrintWriter(NUMBER_FILE);
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryException("Unable to create file", e);
        }
        writer.println(orderNumber);
        writer.flush();
        writer.close();
    }

}
