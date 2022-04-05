package Algorithms;

import java.util.Arrays;

public class SelectionSort extends Algorithm{

    @Override
    public void sort(int[] array, SortCallBack update) {
        
        int n = array.length;

        for (int i = 0; i < n-1; i++)
        {
            int min_idx = i;
            for (int j = i+1; j < n; j++){
                if (array[j] < array[min_idx]){
                   min_idx = j;
                  
                    }
                }
                   

            int temp = array[min_idx];
            array[min_idx] = array[i];
            array[i] = temp;
            update.update(Arrays.copyOf(array, array.length), new int[]{min_idx});
            
        }


    }
    
}
