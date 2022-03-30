package Algorithms;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

public class BubbleSort extends Algorithm{

    @Override
    public void sort(int[] array, SortCallBack callBack) {

        int[] tempArray = new int[array.length];

        for (int c = 0; c < tempArray.length; c++) {
            tempArray[c] = array[c];
        }

        int n = tempArray.length;
        int temp = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (tempArray[j+1] < tempArray[j]) {

                    // swap elements
                    temp = tempArray[j + 1];
                    tempArray[j + 1] = tempArray[j];
                    tempArray[j] = temp;

                     int[] stepArray = new int[tempArray.length];
                    for (int b = 0; b < tempArray.length; b++) {
                        stepArray[b] = tempArray[b];
                    }
                    
                    callBack.update(tempArray.clone(), new int[]{j});

                    
                   


                }

            }
        }

    }

    
    
}
