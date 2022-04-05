package Algorithms;

import java.util.Arrays;

public class InsertionSort extends Algorithm{

    @Override
    public void sort(int[] array, SortCallBack update) {

        for(int i= 1; i<array.length; i++){
            
            int key = array[i];
            int j = i-1;

            while(j >= 0 && key <= array[j]){
                int temp = array[j];
                array[j] = array[j+1];
                array[j+1] = temp;
                j--;

            } 
            int[] tempArray = Arrays.copyOf(array, array.length);
                update.update(tempArray, new int[]{j, j+1});
            
        }
         
    }
    
}

/*
for (int i = 1; i < data.length; i++) {
            T value = data[i];
            int j = i-1;
            while(j >= 0 && compar.compare(value, data[j]) == -1){
                T tmp = data[j];
                data[j] = data[j+1];
                data[j+1] = tmp;
                j--;
            }
            output.accept(data);
        }
*/