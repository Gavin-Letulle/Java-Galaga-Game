package cs2.util;
import java.util.*;
import java.io.File;

public class Sampler {
    //Fields
    HashMap<String, Integer> map;

    //Constructors
    public Sampler(){
        map = new HashMap<String,Integer>();

    }
    public Sampler(String str){
        map = new HashMap<String,Integer>();
        try{
            File f = new File(str);
            Scanner scan = new Scanner(f);
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                String[] parts = line.split("\t");
                int num = Integer.parseInt(parts[1]);
                map.put(parts[0], num);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Methods
    public Set<String> getWords(){
        Set<String> words = new HashSet<String>();
        for(String word : map.keySet()){
            words.add(word);
        }
        return words;
    }

    public int getCount(String str){
        if(map.get(str) == null){
            return 0;
        }
        else{
            return map.get(str);
        }
    }

    public int totalCount(){
        int counter = 0;
        for(String word : map.keySet()){
            counter = counter + map.get(word);
        }
        return counter;
    }

    public double getProbability(String str){
        double prob = 0.0;
        if(map.get(str) == null){
            return prob;
        }
        else{
            if (totalCount() != 0) {
                double add = (double) getCount(str) / totalCount();
                prob += add;
            }
            return prob;
        }
    }

    public void increment(String str){
        if(getWords().contains(str)){
            map.replace(str, map.get(str) + 1);
        }
        else{
            map.put(str, 1);
        }
    }

    public String sample() {
        double rand = Math.random();
        double cumulativeProbability = 0.0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            cumulativeProbability += getProbability(entry.getKey());
            if (rand <= cumulativeProbability) {
                return entry.getKey();
            }
        }
        return map.keySet().iterator().next();
    }
}