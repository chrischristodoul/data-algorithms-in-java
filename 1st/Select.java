import java.util.Arrays;
import java.util.Random;

//CHRISTOS CHRISTODOULOU 5392

/* find the k-th smallest element of an array */

class Select {

    // fill array A of doubles with random numbers in [0,100] 
    public static void randomArray(double[] A, int seed) {
        Random rand = new Random(seed);
        for (int i = 0; i < A.length; i++) {
            A[i] = 100*rand.nextDouble();
        }
    }
    
    // fill array A of doubles with a worst-case input 
    public static void badArray(double[] A) {
        /* enter you code! */
		for (int i = 0; i < A.length; i++) {
			A[i] = i + 1;
		}
    }
   
    // copy the elements of array A in array B
    public static void copyArray(double[] A, double[] B) {
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
    }
    
    /* partition the array A[l,r] with respect to its last element p=A[r]
       if A[l,r] has j-1 elements < p, then p is placed at position pos = l + (j-1) of A 
          -all elements of A[l,pos-1] are less than p=A[pos], and
          -all elements of A[pos+1,r] are greater than p=A[pos]
       returns the position pos of p in array A */
    public static int partition(double[] A, int l, int r) {
        int pos = r;
		double pivot=A[pos];
		int i = l;           

		for (int j = l; j < r; j++) {
       
			if (A[j] < pivot) {
            
				double temp = A[i];
				A[i] = A[j];
				A[j] = temp;
				i++;  
			}
		}

    
		double temp = A[i];
		A[i] = A[r];
		A[r] = temp;
		return i;  
    }
    
    
    // recursive select: returns the k-th smallest element of array A[l,r]
    public static double rselect(double[] A, int l, int r, int k) {
    if (l == r) { 
        return A[l];
    }

    
    int pos = partition(A, l, r);
    int order = pos - l + 1; 

    if (order == k) { 
        return A[pos];
    } else if (k < order) { 
        return rselect(A, l, pos - 1, k);
	}
	else{
        return rselect(A, pos + 1, r, k - order);
		}
	}

    
    // non-recursive select: returns the k-th smallest element of array A[l,r]
   public static double select(double[] A, int l, int r, int k) {
    while (l <= r) {
        int pos = partition(A, l, r);
        int order = pos - l + 1;

        if (order == k) { 
            return A[pos];
        } else if (k < order) { 
            r = pos - 1;
        } else { 
            l = pos + 1;
            k = k - order; 
        }
    }
    return -1;
	}

    
    public static void main(String[] args) {
        long startTime, endTime, totalTime;
        int n = Integer.parseInt(args[0]);
        int seed = Integer.parseInt(args[1]);
        int k = n/2;
        System.out.println("number of elements = " + n);
        
        double[] A     = new double[n];
        double[] Acopy = new double[n];

        randomArray(Acopy,seed);
        badArray(Acopy);
        System.out.println("Initial array Acopy:");
        System.out.println(Arrays.toString(Acopy));
        
        copyArray(Acopy, A);
        startTime = System.currentTimeMillis();
        System.out.println("executing recursive select");
        System.out.println(k+"-th smallest element in array : " + rselect(A, 0, n - 1, k));
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println("total time for recursive select = " + totalTime);
        
        copyArray(Acopy, A);
        startTime = System.currentTimeMillis();
        System.out.println("executing non-recursive select");
        System.out.println(k+"-th smallest element in array : " + select(A, 0, n - 1, k));
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println("total time for non-recursive select = " + totalTime);
        
        copyArray(Acopy, A);
        startTime = System.currentTimeMillis();
        System.out.println("executing sorting");
        Arrays.sort(A);
        System.out.println(k+"-th smallest element in array : " + A[k-1]);
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println("total time for sorting = " + totalTime);
    }
}
