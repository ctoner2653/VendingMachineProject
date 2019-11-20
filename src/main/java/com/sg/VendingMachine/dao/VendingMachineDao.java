/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.VendingMachine.dao;

import com.sg.VendingMachine.dto.Item;
import java.util.List;

/**
 *
 * @author colby
 */
public interface VendingMachineDao {
    List<Item> getAll();
    Item editItem(String name, Item item);
    Item getItem(String name);
}
