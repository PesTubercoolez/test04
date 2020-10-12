package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class MatrixOperation {


    public void fillMatrix (int [] [] arr){

        for (int x = 0; x < arr.length; x++){

            for (int k = 0; k < arr.length; k++){

                arr [x] [k] = ThreadLocalRandom.current().nextInt(0, 9+1);
            }
        }
        showMatrix(arr);
    }

    public void multiplyMatrix (int [] [] arr1, int [] [] arr2){

        int [] [] arr3 = new int [arr1.length] [arr1.length];

        for (int x = 0; x < arr3.length; x++){

            for (int j = 0; j < arr3.length; j++){

                for (int k = 0; k < arr3.length; k++){

                    arr3 [x] [j] += arr1 [x] [k] * arr2 [k] [j];

                }
            }
        }

        showMatrix(arr3);
    }


    private void showMatrix (int [] [] arr){

        for (int x = 0; x < arr.length; x++) {

            for (int k = 0; k < arr.length; k++){

                System.out.print(arr [x] [k] + "\t");

            }
         System.out.println();
        }
        System.out.println("----------------");
    }
}
