
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.util.*;
import java.io.FileReader;
import java.util.function.Function;
import java.util.stream.Collectors;

import javafx.util.Pair;

// !!Attention!! 
// In java, when try to compare 2 Integer(class), we must use "equals" method instead of "=="
// Maybe I should go back to C++...
public class MinCut {

    public List<Pair<Integer,Integer>> raw_edges = new ArrayList<Pair<Integer,Integer>>();
    public List<Pair<Integer,Integer>> edges = new ArrayList<Pair<Integer,Integer>>();
    
    // count vertices
    public int getVertexNum(){
        Set<Integer> counter = new HashSet<Integer>();
        for (Pair<Integer,Integer> pair : edges) {
            counter.add(pair.getKey());
            counter.add(pair.getValue());
        }
        return counter.size();
    }

    public int RandomMinCut(){
        
        while(getVertexNum() > 2){
            // random select an edge
            int index = new Random().nextInt(edges.size());
            Pair<Integer,Integer> edge = edges.get(index);
            Integer src = edge.getKey();
            Integer dest = edge.getValue();
            
            // merge u and v into a single vertex
            for (int i = 0; i < edges.size(); i++) {
                if(edges.get(i).getKey().equals(dest)){
                    Pair<Integer,Integer> new_item = new Pair<Integer,Integer>(src, edges.get(i).getValue());
                    edges.set(i,new_item); 
                }else if(edges.get(i).getValue().equals(dest)){
                    Pair<Integer,Integer> new_item = new Pair<Integer,Integer>( edges.get(i).getKey(), src);
                    edges.set(i,new_item); 
                }
            }
            // remove self loops
            ListIterator<Pair<Integer,Integer>> itr = edges.listIterator(); 
            while (itr.hasNext()) 
            { 
                Pair<Integer,Integer> x = (Pair<Integer,Integer>)itr.next(); 
                if (x.getKey().equals(x.getValue())) 
                    itr.remove(); 
            } 
            
            
        }
        
        return edges.size();
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
    
    public static <T, U> List<U> convertList(List<T> from, Function<T, U> func) {
        return from.stream().map(func).collect(Collectors.toList());
    }

	public static void main(String[] args) throws Exception {

        String pathname = "./kargerMinCut.txt";
        // String pathname = "./test.txt";

        File file = new File(pathname); 
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        MinCut obj = new MinCut();

        while ((line = br.readLine()) != null) {
            try {
                List<String> tmp = new ArrayList<String>(Arrays.asList(line.split("\t")));
                ArrayList<Integer> node = new ArrayList<Integer>(convertList(tmp, s -> ( Integer.parseInt(s))));
                Integer source =node.get(0);
                for (Integer endpoint : node) {
                    if(node.indexOf(endpoint) == 0) continue;
                    else{
                        Pair<Integer,Integer> new_item;
                        if(source > endpoint){
                            
                            new_item = new Pair <Integer,Integer> (endpoint,source ); 
                        }else {
                            new_item = new Pair <Integer,Integer> (source,endpoint); 
                        }
                        if(obj.raw_edges.contains(new_item) == false){
                            obj.raw_edges.add(new_item);
                            // obj.raw_edges.add(new_item);
                        }
                    }
                }
            } catch(NumberFormatException e){  
                e.printStackTrace();   
            } 
        }
        
        
        int cnt = 1000000000;
        for(int i = 0 ; i< 100; i++){
            obj.edges.clear();
            for (Pair<Integer,Integer> p : obj.raw_edges) {
                Pair<Integer,Integer> new_item = new Pair<Integer,Integer>(p.getKey(),p.getValue());
                obj.edges.add(new_item);
            }
            int temp = obj.RandomMinCut();
            System.out.println(temp);
            cnt = Math.min(cnt, temp) ;
        }
        System.out.println("Correct result: "+cnt);


        
	}
}