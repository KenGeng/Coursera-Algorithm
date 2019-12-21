
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.util.*;
import java.io.FileReader;
import java.util.function.Function;
import java.util.stream.Collectors;

import javafx.util.Pair;

public class MinCut {
    public int count = 0;
    public ArrayList<ArrayList<Integer>> G = new ArrayList<ArrayList<Integer>>();
    public Set<Pair<Integer,Integer>> edges = new HashSet<Pair<Integer,Integer>>();
    public ArrayList<Pair<Integer,Integer>> edges_List = new ArrayList<Pair<Integer,Integer>>();
    public int RandomMinCut(){
        int cnt = 0;
        int edge_cnt = 0;
        int vertex_cnt = G.size();
        edge_cnt = edges_List.size();
        while(G.size() > 2){
            int index = new Random().nextInt(edges_List.size());
            Pair<Integer,Integer> edge = edges_List.get(index);
            int src = edge.getKey(), dest = edge.getValue();
            
            // merge u and v into a single vertex
            for(Integer endpoint : G.get(dest)){
                if(G.get(src).contains(dest)) continue;
                else{
                    G.get(src).add(endpoint);
                }
            }
            
            for (ArrayList<Integer> arrayList : G) {
                if(arrayList.get(0) == edge.getKey() || arrayList.get(0) == edge.getValue())
                G.remove(arrayList);
            }
        }
        return cnt;
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
        File file = new File(pathname); 
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        MinCut obj = new MinCut();

        while ((line = br.readLine()) != null) {
            try {
                List<String> tmp = new ArrayList<String>(Arrays.asList(line.split("\t")));
                ArrayList<Integer> node = new ArrayList<Integer>(convertList(tmp, s -> ( Integer.parseInt(s)-1 )));
                Integer source =node.get(0);
                for (Integer endpoint : node) {
                    if(node.indexOf(endpoint) == 0) continue;
                    else{
                        if(source > endpoint){
                            obj.edges.add(new Pair <Integer,Integer> (endpoint,source )); 
                        }else {
                            obj.edges.add(new Pair <Integer,Integer> (source,endpoint)); 
                        }
                    }
                }
                obj.G.add(node);
            } catch(NumberFormatException e){  
                e.printStackTrace();   
            } 
        }
        obj.edges_List.addAll(obj.edges);
        System.out.println(obj.G.get(0));
        System.out.println(obj.edges_List.size());


        
	}
}