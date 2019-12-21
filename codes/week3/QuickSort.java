
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.util.*;
import java.io.FileReader;

public class QuickSort {
    public int count = 0;
    // always first
    // int partition(int arr[],int low, int high){
    //     int pivot = arr[low];
    //     int i = low + 1;
    //     for(int j = low + 1; j <= high; j++){
    //         if(arr[j] < pivot){
                
    //             int tmp = arr[i];
    //             arr[i] = arr[j];
    //             arr[j] = tmp;

    //             i++;
    //         }
    //     }
    //     int tmp = arr[low];
    //     arr[low] = arr[i-1];
    //     arr[i-1] = tmp;

    //     return i-1;
    // }

    // // pivot always final
    // int partition(int arr[],int low, int high){
    //     int tmp0 = arr[high];
    //     arr[high] = arr[low];
    //     arr[low] = tmp0;

    //     int pivot = arr[low];
    //     int i = low + 1;
    //     for(int j = low + 1; j <= high; j++){
    //         if(arr[j] < pivot){
                
    //             int tmp = arr[i];
    //             arr[i] = arr[j];
    //             arr[j] = tmp;

    //             i++;
    //         }
    //     }
    //     int tmp = arr[low];
    //     arr[low] = arr[i-1];
    //     arr[i-1] = tmp;

    //     return i-1;
    // }
    // pivot always median
    int partition(int arr[],int low, int high){
        int median = (low + high)/2;
        int selected = median;
        int[] temp_arr = {arr[low],arr[median],arr[high]};
        Arrays.sort(temp_arr);
        if(temp_arr[1] == arr[low]) selected = low;
        else if(temp_arr[1] == arr[high]) selected = high;
        else if(temp_arr[1] == arr[median]) selected = median;
        int tmp0 = arr[selected];
        arr[selected] = arr[low];
        arr[low] = tmp0;
        
        int pivot = arr[low];
        int i = low + 1;
        for(int j = low + 1; j <= high; j++){
            if(arr[j] < pivot){
                
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;

                i++;
            }
        }
        int tmp = arr[low];
        arr[low] = arr[i-1];
        arr[i-1] = tmp;

        return i-1;
    }
    int sort(int arr[],int low, int high){
        if(low < high){
            count += high-low;
            int pivot_pos = partition(arr,low,high);
            sort(arr, low, pivot_pos-1);
            sort(arr, pivot_pos+1, high);
        }
        return count;

    }
    public static ArrayList<Integer> readFile(String pathname) throws Exception{
        // String pathname = "./test.txt";
            
        File file = new File(pathname); 
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        while ((line = br.readLine()) != null) {
            try {  
                Integer num = Integer.parseInt(line);  
                numbers.add(num);
            } catch(NumberFormatException e){  
                e.printStackTrace();   
            } 
        }
        return numbers;
    }
    static void printArray(int arr[]) 
    { 
        int n = arr.length; 
        for (int i=0; i<n; ++i) 
            System.out.print(arr[i]+" "); 
        System.out.println(); 
    } 
  

	public static void main(String[] args) throws Exception {

            String pathname = "./QuickSort.txt";
            // String pathname = "./test.txt";

            ArrayList<Integer> numbers = readFile(pathname);
            
            System.out.println(numbers.size());
            int [] arr = numbers.stream().mapToInt(i -> i).toArray();
            int n = numbers.size();

            QuickSort obj = new QuickSort();
            obj.sort(arr,0,n-1);
            // printArray(arr); 
            System.out.println(obj.count);

        
	}
}

// always use the first element of the array as the pivot element.
// 162085