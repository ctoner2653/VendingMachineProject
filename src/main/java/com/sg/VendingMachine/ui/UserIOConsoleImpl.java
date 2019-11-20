/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.VendingMachine.ui;

import java.util.Scanner;

/**
 *
 * @author colby
 */
public class UserIOConsoleImpl implements UserIO {
     Scanner sc = new Scanner(System.in);

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }
    public void println(String msg){
        System.out.print(msg);
    }
    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        double d = sc.nextDouble();
        return d;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        float d = sc.nextFloat();
        return d;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        int d = sc.nextInt();
        return d;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        boolean correctNumber = true;
        int d = 0;
        while (correctNumber) {
            System.out.println(prompt);

            try {
                d = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                print("NonNumber detected, please enter a number. Press Enter to try again.");
                sc.nextLine();
            }

            if (d < max || d > min) {
                correctNumber = false;
            } else {
                correctNumber = true;
            }

        }

        return d;

    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        long d = sc.nextLong();
        return d;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String d = sc.nextLine();
        return d;
    }

}
