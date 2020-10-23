package com.company.service;

import java.util.Scanner;

public class InputScanner {

    public int scanInt (){

        int scannedInt;
        Scanner scanConsole = new Scanner(System.in);
        System.out.println("Enter value");
        scannedInt = Integer.parseInt(scanConsole.nextLine());

        return scannedInt;
    }

}
