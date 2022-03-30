package Algorithms;

import java.util.Arrays;

import javax.security.auth.callback.Callback;

public class QuickSort extends Algorithm{

    @Override
    public void sort(int[] array, SortCallBack update) {
        
        quicksort(array, 0, array.length-1, update);
    }

    public static void quicksort(int[] s, int left, int right, SortCallBack update) {
		
		//System.out.println("----> " + left + " " + right);
		
		if (left < right) {
			// pi Index des Pivotelements
			int pi = partition(s, left, right, update);
			
			//ausgabe(s);	
			
			quicksort(s, left, pi - 1, update);
			quicksort(s, pi + 1, right, update);
		}

        
	}

    public static int partition(int[] s, int left, int right, SortCallBack update) {
		int x = s[right]; // w�hle Pivot-Element aus
		
		int l = left;
		int r = right - 1;
		while (true) {
			while (l <= r && s[l] <= x)
				l++;
			while (l <= r && s[r] >= x)
				r--;
			if (l < r) {
				int t = s[l];
				s[l] = s[r];
				s[r] = t;

				int[] temp = Arrays.copyOf(s, s.length);

                update.update(temp, new int[]{t});
			} else {
				// System.out.println(l + " " + r);
				int t = s[l];
				s[l] = s[right];
				s[right] = t;
				
                int[] temp = Arrays.copyOf(s, s.length);

                update.update(temp, new int[]{t});
				
		//		System.out.println("PivotPos: " + l);		
				return l; // l ist die Position des Pivot-Elements
			}
		}
	}
    
}
