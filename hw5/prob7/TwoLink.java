import java.io.*;
import java.util.*;

/**
 * Created by kumamon on 11/4/2559.
 */
public class TwoLink {

    public static void main(String[] args) {
        HashMap<Integer, Vector<Integer>> hmap = new HashMap<Integer, Vector<Integer>>();
        try (BufferedReader br = new BufferedReader(new FileReader("/home/kumamon/IdeaProjects/webapp2/src/web-Google.txt"))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#")) {
                    continue;
                }
                StringTokenizer itr = new StringTokenizer(line);
                String fromNodeId = "-1", toNodeId = "-1";
                while (itr.hasMoreTokens()) {
                    fromNodeId = itr.nextToken();
                    toNodeId = itr.nextToken();
                    Vector<Integer> vector = hmap.getOrDefault(Integer.parseInt(fromNodeId), new Vector<Integer>());
                    vector.add(Integer.parseInt(toNodeId));
                    hmap.put(Integer.parseInt(fromNodeId), vector);
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<Integer, Vector<Integer>> result = new HashMap<Integer, Vector<Integer>>();
        Iterator it = hmap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry node1 = (Map.Entry) it.next();
            for (Integer nodeID:(Vector<Integer>)node1.getValue()) {

                Vector<Integer> node2 = (Vector<Integer>) hmap.getOrDefault(nodeID, new Vector<Integer>()).clone();
                Vector<Integer> new_node = result.getOrDefault((Integer)node1.getKey(), new Vector<Integer>());
                new_node.addAll(node2);
                if (new_node.contains(node1.getKey())){
                    new_node.remove(node1.getKey());
                }
                Vector<Integer> _new_node = new Vector<Integer>(new LinkedHashSet(new_node));
                result.put((Integer)node1.getKey(), _new_node);
            }

        }

        Map<Integer, Vector<Integer>> treeMap = new TreeMap<Integer, Vector<Integer>>(result);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("result_7_5.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Iterator it2 = treeMap.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry pair = (Map.Entry) it2.next();
            writer.println(pair.getKey() + " -- " + pair.getValue());
            it2.remove(); // avoids a ConcurrentModificationException
        }
        writer.close();
    }
}

