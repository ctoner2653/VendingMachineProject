/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.VendingMachine.service;

import com.sg.VendingMachine.dao.VendingMachineDao;
import com.sg.VendingMachine.dao.VendingMachineDaoFileImpl;
import com.sg.VendingMachine.dto.Item;
import com.sg.VendingMachine.dto.VendingMachineChange;
import com.sg.VendingMachine.ui.VendingMachineServiceLayerImpl;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author colby
 */
public class VendingMachineServiceLayerTest {
    
    VendingMachineDao dao = new VendingMachineDaoFileImpl();
    VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(dao);
    
    public VendingMachineServiceLayerTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of insertMoney method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testInsertMoneyandGetBalance() {
        service.insertMoney(new BigDecimal("1.50"));
        assertEquals(service.getBalance(), new BigDecimal("1.50"));
        System.out.println(service.getBalance());
    }

    @Test
    public void testInsertMoreMoney() {
        service.insertMoney(new BigDecimal("1.50"));
        service.insertMoney(new BigDecimal("1.50"));
        assertEquals(service.getBalance(), new BigDecimal("3.00"));

    }

    /**
     * Test of recieveChange method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testRecieveChange150() {
        service.insertMoney(new BigDecimal("1.50"));
        VendingMachineChange change = service.recieveChange();
        assertEquals(change.getQuarter(), 6);
    }

    @Test
    public void testRecieveChange157() {
        service.insertMoney(new BigDecimal("1.57"));
        VendingMachineChange change = service.recieveChange();
        assertEquals(change.getQuarter(), 6);
        assertEquals(change.getNickel(), 1);
        assertEquals(change.getPenny(), 2);
    }

    @Test
    public void testRecieveChange41() {
        service.insertMoney(new BigDecimal("0.41"));
        VendingMachineChange change = service.recieveChange();
        assertEquals(change.getQuarter(), 1);
        assertEquals(change.getDime(), 1);
        assertEquals(change.getNickel(), 1);
        assertEquals(change.getPenny(), 1);
    }

    @Test
    public void testPurchaseItem() {

        try {
            service.insertMoney(new BigDecimal("45.00"));
            Item newItem = new Item("Pringles");
            newItem.setPrice(new BigDecimal("34.0"));
            newItem.setQuanity(2);
            service.purchaseItem(newItem);
            assertEquals(newItem.getQuanity(), 1);
        } catch (InsufficientFundsException ex) {
            fail("Should have not thrown this exception, not big brain");
        } catch (NoItemInventoryException ex) {
            fail("Should have not thrown this exception, not big brain");
        }

    }

    @Test
    public void testPurchaseItemInsufficientFunds() {

        try {
            service.insertMoney(new BigDecimal("25.00"));
            Item newItem = new Item("Pringles");
            newItem.setPrice(new BigDecimal("34.0"));
            newItem.setQuanity(2);
            service.purchaseItem(newItem);
            assertEquals(newItem.getQuanity(), 1);
        } catch (InsufficientFundsException ex) {

        } catch (NoItemInventoryException ex) {
            fail("Should have not thrown this exception, not big brain");
        }

    }

    @Test
    public void testPurchaseItemNoItemInventoryException() {

        try {
            service.insertMoney(new BigDecimal("45.00"));
            Item newItem = new Item("Pringles");
            newItem.setPrice(new BigDecimal("34.0"));
            newItem.setQuanity(0);
            service.purchaseItem(newItem);
            assertEquals(newItem.getQuanity(), 1);
        } catch (InsufficientFundsException ex) {
            fail("Should have not thrown this exception, not big brain");
        } catch (NoItemInventoryException ex) {

        }

    }
}
