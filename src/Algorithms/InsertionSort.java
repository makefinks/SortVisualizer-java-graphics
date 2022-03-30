package Algorithms;

import java.util.Arrays;

public class InsertionSort extends Algorithm{

    @Override
    public void sort(int[] array, SortCallBack update) {
        // TODO Auto-generated method stub

        for(int i = 0; i<array.length; i++){
            
            int key = i;
            int j = i-1;

            while(j >= 0 && array[j] < key){
                int temp = array[j];
                array[j] = array[j+1];
                array[j+1] = temp;
                j--;
                int[] tempArray = Arrays.copyOf(array, array.length);
                update.update(array, new int[]{j, j+1});

            } 

        }
        
    }
    
}
