/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.VendingMachine.dao;

import com.sg.VendingMachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author colby
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    public final static String LIBRARY_FILE = "Machine.txt";
    public final static String DELIMITER = "::";
    private Map<String, Item> items = new HashMap<>();

    @Override
    public List<Item> getAll() {
        try {
            readFile();
        } catch (VendingMachineException ex) {

        }
        return new ArrayList<Item>(items.values());
    }

    @Override
    public Item editItem(String name, Item item) {

        try {
            readFile();
        } catch (VendingMachineException ex) {

        }
        items.put(name, item);
        try {
            writeFile();
        } catch (VendingMachineException ex) {

        }

        return items.get(name);

    }

    public Item getItem(String name) {
        return items.get(name);
    }

    public void readFile() throws VendingMachineException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachineException();

        }
        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            Item currentItem = new Item(currentTokens[0]);

            currentItem.setPrice(new BigDecimal(currentTokens[1]));
            currentItem.setQuanity(Integer.parseInt(currentTokens[2]));

            items.put(currentItem.getName(), currentItem);

        }
        scanner.close();
    }

    public void writeFile() throws VendingMachineException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new VendingMachineException();
        }
        List<Item> itemList = this.getAll();
        for (Item currentItem : itemList) {
            out.println(currentItem.getName() + DELIMITER
                    + currentItem.getPrice() + DELIMITER
                    + currentItem.getQuanity());

            out.flush();

        }
        out.close();
    }
}
