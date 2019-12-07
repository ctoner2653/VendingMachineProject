/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.FlooringMastery.ui;

import com.sg.FlooringMastery.dto.order;

import java.util.List;

/**
 *
 * @author colby
 */
public class FlooringMasteryView {

    UserIo io;

    public FlooringMasteryView(UserIo io) {
        this.io = io;
    }

    public int getMenuSelection() {

        io.print("<<Flooring Program>>");
        io.print("* 1. Display Orders");
        io.print("* 2. Add an Order");
        io.print("* 3. Edit an Order");
        io.print("* 4. Remove an Order");
        io.print("* 5. Save Current Work");
        io.print("* 6. Quit");

        return io.readInt("Please Enter Your Selection", 1, 6);
    }

    public order getOrderInfo() {
        String customerName = io.readString("What is the customers name for the order?");
        io.print("======");
        io.print("What state is the order being ordered in?");
        String state = displayStates();
        io.print("======");
        double area = io.readDouble("What is the area of the order?");
        io.print("======");
        io.print("What is the product type?");
        String productType = displayProducts();
        order newOrder = new order();

        newOrder.setArea(area);
        newOrder.setCustomerName(customerName);
        newOrder.setState(state);
        newOrder.setProductType(productType);
        return newOrder;
    }

    public void displayOrders(List<order> orders) {
        for (order newOrder : orders) {
            io.print("* OrderNumber: " + newOrder.getOrderNumber());
            io.print("* Customer Name: " + newOrder.getCustomerName());
            io.print("* Product: " + newOrder.getProductType());
            io.print("* State: " + newOrder.getState());
            io.print("* Area: " + Double.toString(newOrder.getArea()));
            io.print("* Material Cost: " + Double.toString(newOrder.getMaterialCost()));
            io.print("* Labor Cost: " + Double.toString(newOrder.getLaborCost()));
            io.print("* Tax: " + Double.toString(newOrder.getTax()));
            io.print("* Total: " + Double.toString(newOrder.getTotal()));
            io.print(("===================="));
            io.readString("");
        }
    }

    public int removeOrder() {
        io.print("* Remove Order *");
        int returnInt = io.readInt("What Is the Order Number of the order you would like to remove?");
        io.readString("==================");
        return returnInt;
    }
    public int getEditOrder(){
        io.print("* Edit Order *");
        int returnInt = io.readInt("What is the Order Number of the order you would like to edit?");
        io.readString("===================");
        return returnInt;
    }
    public String displayStates() {
        io.print("* 1. OH");
        io.print("* 2. PA");
        io.print("* 3. MI");
        io.print("* 4. IN");
        int selection = io.readInt("Please Enter Your Selection", 1, 4);
        String state = "";
        switch (selection) {
            case 1:
                state = "OH";
                break;
            case 2:
                state = "PA";
            case 3:
                state = "MI";
            case 4:
                state = "IN";
            default:
                break;

        }
        return state;

    }

    public String displayProducts() {
        io.print("* 1. Carpet");
        io.print("* 2. Laminate");
        io.print("* 3. Tile");
        io.print("* 4. Wood");
        int selection = io.readInt("Please Enter Your Selection", 1, 4);
        String product = "";
        switch (selection) {
            case 1:
                product = "Carpet";
                break;
            case 2:
                product = "Laminate";
                break;
            case 3:
                product = "Tile";
                break;
            case 4:
                product = "Wood";
            default:
                break;

        }
        return product;
    }

    public order editOrder(order editOrder) {
        if (editOrder != null) {

            io.print("1. Customer Name");
            io.print("2. State");
            io.print("3. Product");
            io.print("4. Area");
            int selection = io.readInt("What Would you like to edit?", 1, 4);

            switch (selection) {
                case 1:
                    String customerName = io.readString("What is the new Customer Name");
                    editOrder.setCustomerName(customerName);
                    break;
                case 2:
                    String state = displayStates();
                    editOrder.setState(state);
                    break;
                case 3:
                    String product = displayProducts();
                    editOrder.setProductType(product);
                    break;
                case 4:
                    double area = io.readDouble("What is the new area?");
                    editOrder.setArea(area);
                    break;
                default:
                    break;
            }

        } else {
            io.print("Order does not exist");

        }
        io.readString("Please hit enter to continue.");
        return editOrder;

    }

}
