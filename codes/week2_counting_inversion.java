
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.util.*;
import java.io.FileReader;

public class week2_counting_inversion {
    public static int CountSplitInv(List<Integer> numbers, int begin, int end){
        int cnt = 0;
        int middle = (begin + end) / 2;
        ArrayList<Integer> L = new ArrayList<>(numbers.subList(begin, middle));
        ArrayList<Integer> R = new ArrayList<>(numbers.subList(middle, end));
        
        int j = 0, k = 0;
        int i = begin;
        for(i = begin; i < end && j < L.size() && k<R.size(); i++){
            
            if(L.get(j) < R.get(k)){
                numbers.set(i, L.get(j));
                j ++;
            }else {
                numbers.set(i, R.get(k));
                cnt += L.size() - j;
                k++;
            }
        }
        while( j < L.size()){
            numbers.set(i++, L.get(j++));
        }
        while( k < R.size()){
            numbers.set(i++, R.get(k++));
        }

        return cnt;
    }
    public static long SortAndCount(List<Integer> numbers, int begin, int end){
        int n = end - begin;
        if(n==1) return 0;
        else{
            long left = SortAndCount(numbers,begin,(begin+end)/2);
            long right = SortAndCount(numbers,(begin+end)/2,end);
            long middle = CountSplitInv(numbers,begin,end);

            return left + right + middle;
        }

    }
	public static void main(String[] args) throws Exception {

            String pathname = "./IntegerArray.txt";
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
            System.out.println(numbers.size());
            System.out.println(SortAndCount(numbers, 0, numbers.size()));

        
	}
}