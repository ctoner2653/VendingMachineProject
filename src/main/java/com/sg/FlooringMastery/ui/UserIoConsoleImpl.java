/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.FlooringMastery.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author colby
 */
public class UserIoConsoleImpl implements UserIo {

    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    public void println(String msg) {
        System.out.print(msg);
    }

    @Override
    public double readDouble(String prompt) {
        
        double d = 0;
        boolean correctNumber = true;

        while (correctNumber) {
            System.out.println(prompt);

            try {
                d = Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                print("NonNumber detected, please enter a number. Press Enter to try again.");
                
            }
               if(d == 0){
                    correctNumber = true;
               }else{
                   correctNumber = false;
               }
        }
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
                
            if (d > max || d < min) {
                correctNumber = true;
                System.out.println("Number entered is 2 big or 2 small.");
            } else {
                correctNumber = false;
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

    @Override
    public LocalDate getDate(String prompt) {
        String date = readString("Please Enter Date (MM/dd/yyyy)");
        boolean isOkay = true;
        LocalDate ld = null;
        do {

            try {
                 ld = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            } catch (Exception e) {
                readString("Please follow date format");
                date = readString("Please Enter Date (MM/dd/yyyy)");
                isOkay = false;
            }
            isOkay = true;
        } while (isOkay != true);
        return ld;
    }

}
