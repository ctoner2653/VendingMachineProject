/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.FlooringMastery.service;

import com.sg.FlooringMastery.dao.FlooringMasteryDao;
import com.sg.FlooringMastery.dao.FlooringMasteryException;
import com.sg.FlooringMastery.dto.order;
import java.util.List;

/**
 *
 * @author colby
 */
public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {

    productServiceLayer productService;
    taxServiceLayer taxService;
    FlooringMasteryDao dao;

    public FlooringMasteryServiceLayerImpl(productServiceLayer productService, taxServiceLayer taxService, FlooringMasteryDao dao) {
        this.productService = productService;
        this.taxService = taxService;
        this.dao = dao;
    }

    @Override
    public List<order> displayOrders() throws FlooringMasteryException {
        return dao.displayOrders();
    }

    @Override
    public void addOrder(order newOrder) throws FlooringMasteryException {
        newOrder.setOrderNumber(dao.displayOrders().size() + 1);
        //Get Prodcuts and then get products costPersquareFoot and set to newOrders cost 
        newOrder.setCostPerSquareFoot((productService.getProduct(newOrder.getProductType())).getCostPerSquareFoot());
        newOrder.setLaborCostPerSquareFoot((productService.getProduct(newOrder.getProductType())).getLaborCostPerSquareFoot());
        newOrder.setLaborCost((newOrder.getArea() * newOrder.getLaborCostPerSquareFoot()));
        newOrder.setMaterialCost((newOrder.getArea() * newOrder.getCostPerSquareFoot()));
        double taxRate = (taxService.getTax(newOrder.getState()).getTaxRate());
        newOrder.setTaxRate(taxRate);
        double actualTaxRate = newOrder.getTaxRate() / 100;
        newOrder.setTax((newOrder.getMaterialCost() + newOrder.getLaborCost()) * actualTaxRate);
        newOrder.setTotal(newOrder.getLaborCost() + newOrder.getMaterialCost() + newOrder.getTax());
        dao.addOrder(newOrder);
    }

    @Override
    public order editOrder(int orderNumber, order newOrder) throws FlooringMasteryException {
        newOrder.setCostPerSquareFoot((productService.getProduct(newOrder.getProductType())).getCostPerSquareFoot());
        newOrder.setLaborCostPerSquareFoot((productService.getProduct(newOrder.getProductType())).getLaborCostPerSquareFoot());
        newOrder.setLaborCost((newOrder.getArea() * newOrder.getLaborCostPerSquareFoot()));
        newOrder.setMaterialCost((newOrder.getArea() * newOrder.getCostPerSquareFoot()));
        double taxRate = (taxService.getTax(newOrder.getState()).getTaxRate());
        newOrder.setTaxRate(taxRate);
        double actualTaxRate = newOrder.getTaxRate() / 100;
        newOrder.setTax((newOrder.getMaterialCost() + newOrder.getLaborCost()) * actualTaxRate);
        newOrder.setTotal(newOrder.getLaborCost() + newOrder.getMaterialCost() + newOrder.getTax());
        return dao.editOrder(orderNumber, newOrder);
    }

    @Override
    public void removeOrder(int orderNumber) throws FlooringMasteryException {
        dao.removeOrder(orderNumber);
    }

    @Override
    public void saveProgress() throws FlooringMasteryException {
        dao.saveProgres();
    }

    @Override
    public order getOrder(int orderNumber) throws FlooringMasteryException {
        return dao.getOrder(orderNumber);
    }
}
