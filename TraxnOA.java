import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
// Uncomment below classes to send network request if needed.
// import java.net.HttpURLConnection;
// import java.net.URL;

class TraxcnOA {
    public static void main(String args[] ) throws Exception {
        String inputData = "";
        String thisLine = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while ((thisLine = br.readLine()) != null) {
            inputData += thisLine + "\n";
        }
        // Output the solution to the console
        System.out.println(codeHere(inputData));
    }
    public static String codeHere(String inputData) {
        // Use this function to write your solution
        String[] lines = inputData.split("\r?\n");
        int n = Integer.parseInt(lines[0]);
        LinkedList<Integer> pipeline = new LinkedList<>();
        Map<Integer, Integer> frequency = new HashMap<>();
        List<Integer> removedElements = new ArrayList<>();
        for(int i=1; i<=n; i++)
        {
            String[] parts = lines[i].split(" ");
            int x = Integer.parseInt(parts[0]);
            int num = Integer.parseInt(parts[1]);
            if(x == 1)
            {
                pipeline.addLast(num);
                frequency.put(num, frequency.getOrDefault(num,0) + 1);
            }
            else
            {
                if(pipeline.isEmpty())
                 removedElements.add(-1);
                else
                {
                    int maxFreq = Integer.MIN_VALUE;
                    List<Integer> candidates = new ArrayList<>();
                    for(int key: frequency.keySet())
                    {
                        if(frequency.get(key) > maxFreq)
                        {
                            maxFreq = frequency.get(key);
                            candidates = new ArrayList<>();
                            candidates.add(key);
                        }
                        else if(frequency.get(key) == maxFreq)
                          candidates.add(key);
                    }

                    // commented part --> wrong and wrote this in OA
                    int toRemove = -1;
                    // int minIndex = Integer.MAX_VALUE;
                    int maxIndex = -1;
                    for(int candidate: candidates)
                    {
                        int index = pipeline.lastIndexOf(candidate);
                        // if(index < minIndex)
                        // {
                        //     minIndex = index;
                        //     toRemove = candidate;
                        // }

                        if(index > maxIndex)
                        {
                            maxindex = index;
                            toRemove = candidate;
                        }
                    }
                    // pipeline.remove(minIndex);
                    pipeline.remove(maxIndex);
                    frequency.put(toRemove, frequency.get(toRemove) - 1);
                    if(frequency.get(toRemove) == 0)
                      frequency.remove(toRemove);
                    removedElements.add(toRemove);
                }
            }
        }
        return removedElements.toString().replaceAll("[\\[\\],]","");
    }
}