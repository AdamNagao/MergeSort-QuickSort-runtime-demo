/*
 * Adam Nagao
 * Project 1 
 * COMP482
 * 9/26/17
 */
public class SortTester {
	public static void main(String[] args) {
		
		int[] demoArray1 = {34,67,23,19,122,300,2,5,17,18,5,4,3,19,-40,23};		//these two arrays are for the first demo of showing that merge and quick sort work
		int[] demoArray2 = {34,67,23,19,122,300,2,5,17,18,5,4,3,19,-40,23};
		int[] n = {10,100,1000,10000,100000,1000000,2000000}; //  contains all the n's to run through in the experiment
		long[][] resultsArray = new long[8][8];   //This table will save all of the information from the experiments

		System.out.print("Here is the demo array: ");
		printArray(demoArray1);

		System.out.println("Let's sort the demo array!");
		
		System.out.print("Sorted via mergeSort: ");
		Sorts.mergeSort(demoArray1, 0, (demoArray1.length-1));
		printArray(demoArray1);
		
		
		System.out.print("Sorted via quickSort: ");
		Sorts.quickSort(demoArray2, 0, (demoArray2.length-1));
		printArray(demoArray2);
		
		long mStartTime = 0;      //64 bit longs because 32 ints were overflowing
		long mTime = 0;
		long qStartTime = 0;
		long qTime = 0;
		int mergeWin = 0;
		int quickWin = 0;		
		
		for(int k = 0;k<n.length;k++){ //test each of the n's given by the assignment by traversing the n array (each element is a trial of n)
		
			for(int i = 0;i<20;i++){   //run each n 20 times

				int[] array1 = new int[n[k]];       //Make Two Arrays, one for the mergesort, and one for the quicksort
				int[] array2 = new int[n[k]];
			
				for(int j = 0;j<n[k];j++){ 			//Make some random numbers and put them into both arrays
					array1[j] = (int) (Math.random() * 1000000);
					array2[j] = array1[j];
				}
			
				mStartTime = (int) System.nanoTime();    //start timer
				Sorts.mergeSort(array1);
				mTime = ((long)System.nanoTime() - mStartTime);  //end timer
			
				qStartTime = (int) System.nanoTime();  //start timer
				Sorts.quickSort(array2);
				qTime = ((long)System.nanoTime() - qStartTime);  //end timer
			
			
				if(mTime < qTime){    //Determine who won by comparing the time elapsed from each sort
					mergeWin++;
				} else {
					quickWin++;
				}
				resultsArray[k][4] += mTime;     //save out the time each individual trial took for later processing 
				resultsArray[k][6] += qTime;     //This accumulates the total time 
				
				mStartTime = 0;         //reset timers for next trail
				mTime = 0;
				qStartTime = 0;
				qTime = 0;
			} 
			
			resultsArray[k][0] = n[k];  //Save which N we are testing
			resultsArray[k][1] = 20;    //Save how many trails of n
			resultsArray[k][2] = mergeWin;   //save the number of merge sort wins from the 20 trails
			resultsArray[k][3] = quickWin;  //save the number of quick sort wins from the 20 trails 
			resultsArray[k][4] /= 20;    //calculate the mean time of merge sort from the 20 trails
			resultsArray[k][5] = (resultsArray[k][4] / (resultsArray[k][0] * log(resultsArray[k][0],2))); //Mean time of mergesort / nlogn
			resultsArray[k][6] /= 20;   //calculate the mean time of quick sort from the 20 trails
			resultsArray[k][7] = (resultsArray[k][6] / (resultsArray[k][0] * log(resultsArray[k][0],2))); //Mean time of quicksort / nlogn
			
			mergeWin = 0;
			quickWin = 0;
		}
		
		//Experiments have been ran, print out the information into nice looking tables
		
		//First table printout
		System.out.println("n     nTrails     #mergeSort Wins     #quicksortWin");
		
		for(int i = 0;i <n.length;i++){
			for(int j = 0;j<resultsArray.length -4 ;j++){
				System.out.print(resultsArray[i][j] + "          ");
			}
			System.out.println();
			
		}
		
		//Second table printout
		System.out.println("n     MergeSort: mean runtime(nanosecs)     MergeSort: mean runtime/ (n*log2(n) )  "
				+ "  QuickSort: mean runtime(nanosecs)      QuickSort: mean runtime  / (n*log2(n) )");
		
		for(int i = 0;i <n.length;i++){
			System.out.print(resultsArray[i][0] + "     ");
			for(int j = 4;j<8;j++){
				System.out.print(resultsArray[i][j] + "          "  );  
			}
			System.out.println();
			
		}


	}
	
	static void printArray(int[] A){   //simple method to traverse and print an array
		for(int i = 0;i<A.length;i++){
			System.out.print(A[i] + " ");
		}
		System.out.println();
	}
	
	static int log(long x, int base){		//Java does not natively support log base 2, so this method implements change of base formula
	    return (int) (Math.log(x) / Math.log(base));
	}
	
}
