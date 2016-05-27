import java.io.*;
import java.util.*;


public class OneLink {

    public static void main(String[] args) {

        HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
        try (BufferedReader br = new BufferedReader(new FileReader("/home/kumamon/IdeaProjects/webapp2/src/web-Google.txt")))
        {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#")) {
                    continue;
                }
                StringTokenizer itr = new StringTokenizer(line);
                String fromNodeId = "", toNodeId = "";
                while (itr.hasMoreTokens()) {
                    fromNodeId = itr.nextToken();
                    toNodeId = itr.nextToken();
                    hmap.put(Integer.parseInt(toNodeId), hmap.getOrDefault(Integer.parseInt(toNodeId), 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        Map<Integer, Integer> treeMap = new TreeMap<Integer, Integer>(hmap);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("result.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Iterator it = treeMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            writer.println(pair.getKey() + " -- " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
        writer.close();
    }


}
