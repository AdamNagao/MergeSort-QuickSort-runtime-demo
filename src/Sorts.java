/*
 * Adam Nagao
 * Project 1 
 * COMP482
 * 9/26/17
 */
public class Sorts {

	static void merge(int[] A, int i, int m, int j) {
		int size = (j-i+1);    //calculate the size of the array
		int[] tempArray = new int[size];   //create a temp array to work in
		int temp = 0;  //temp position variable
		int leftPointer = i;   
		int rightPointer = m+1; 
		
		while(temp < size){
			if(leftPointer <= m && rightPointer <= j) {     //Both pointers are in bounds, so check which element is smaller and put it in
				if(A[leftPointer] <= A[rightPointer]) {
					tempArray[temp] = A[leftPointer];
					leftPointer++;
				} else {
					tempArray[temp] = A[rightPointer];
					rightPointer++;
				}
				temp++;
		
			} else if(leftPointer <= m) {     //leftpointer is in the array bounds, just copy over everything from the left side
				tempArray[temp] = A[leftPointer];
				temp++;
				leftPointer++;
			} else if(rightPointer <= j) {  //rightpointer is in the array bounds, just copy over everything from the right side
				tempArray[temp] = A[rightPointer];
				temp++;
				rightPointer++;
			}
		}
        
        temp = 0;
        for(int n = i; n <= j; n++) {   //copy everything from temparray into a
            A[n] = tempArray[temp];
            temp++;
        }

    }

	public static void mergeSort(int[] A, int i, int j) {     
		if(i < j) {
			int m = (i + j)/2;
			mergeSort(A,i,m);
			mergeSort(A,m+1,j);
			merge(A,i,m,j);
		}
	}
	
	public static  void  mergeSort(int[] A ) {
		mergeSort(A,0,A.length -1);
	}
	
	static int partition(int[] A, int i, int k) {    //Partition by swapping
		int p = A[k];
		int l = i;
		int r = k-1;
		
		while(l <= r){
			while(l <= r && A[l]<=p){
				l++;
			}
			while(r>=l && A[r] >= p){
				r--;
			}
			if(l < r){
				//swap A[l] and A[r]
				int temp = A[l];
				A[l] = A[r];
				A[r] = temp;
			}
		}
		//swap A[l] and A[k]
		int temp = A[l];
		A[l] = A[k];
		A[k] = temp;
		return l;
		
	}
	
	static void quickSort(int[] A,int i, int j) {
		if(i < j){
			int s = partition(A,i,j);
			quickSort(A,i,s-1);
			quickSort(A,s+1,j);
		}
	}
	
	public static void quickSort( int[] A) {
		quickSort(A,0,A.length -1);
	}
	
	public static boolean isSorted(int[] A) {   //This method checks if an array is sorted by tranversing the array and comparing adjacent elements
		for(int j = 0;j<A.length;j++){
			if (A[j] < A[j+ 1]) {
		        return true;
		    }
		    else {
		        return false;
		    }
		}
		return true;
	}

}


