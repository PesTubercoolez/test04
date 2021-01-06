package com.company.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class InputScanner {

    public int scanInt (){

        int scannedInt;
        Scanner scanConsole = new Scanner(System.in);
        System.out.println("Enter value");
        scannedInt = Integer.parseInt(scanConsole.nextLine());

        return scannedInt;
    }
}
