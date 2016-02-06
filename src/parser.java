import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class parser {
    private static List<person> group = new ArrayList<person>();

    public static void main(String[] args) {

        // Gets the html file, maybe convert this entire thing to a database
        File input = new File("messages.htm");
        Document doc;
        try {
            doc = Jsoup.parse(input, "UTF-8", "");
        } catch (IOException e) {
            System.out.println("Error file cannot be parsed");
            e.printStackTrace();
            return;
        }
        Elements meta = doc.getElementsByClass("message");
        Elements messages = doc.getElementsByTag("p");
        if (meta.size() != messages.size()) {
            System.out.println("Not equal header/messages number");
            return;
        }

        // Parses through and makes user-message objects
        for (int i = 0; i < meta.size(); i++) {
            Boolean exists = false;
            person chatter = null;
            String currPerson = meta.get(i).getElementsByClass("user").text();
            // System.out.println(currPerson);
            for (person guy : group) {
                if (guy.getName().equals(currPerson)) {
                    exists = true;
                    chatter = guy;
                }
            }
            if (!exists) {
                chatter = new person(currPerson);
                group.add(chatter);
            }
            String timestamp = meta.get(i).getElementsByClass("meta").text();
            String words = messages.get(i).text();
            message currMessage = new message(words, timestamp, currPerson);
            // System.out.println(currMessage);
            chatter.addMessage(currMessage);
        }

        // word counter, make into function
        /*
         * for (person guy : group){ String name = guy.getName(); Long count =
         * guy.countMessages(); StringBuilder namecount = new StringBuilder();
         * if (count != 0){ namecount.append(name); namecount.append(" : ");
         * namecount.append(count); System.out.println(namecount); } }
         */
        for (person guy : group) {
            String name = guy.getName();
            if (name.equals("Ian Chen")) {
                while(true){
                String randomLine = guy.randomMessage();
                System.out.println(randomLine);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                }
                /*Map<String, Integer> words = guy.commonWord();
                words = sortByComparator(words, true);
                printMap(words);*/
            }
        }
        System.out.println("TEST");

    }

    private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order)
    {

        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<String, Integer>>()
        {
            public int compare(Entry<String, Integer> o1,
                    Entry<String, Integer> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public static void printMap(Map<String, Integer> map)
    {
        for (Entry<String, Integer> entry : map.entrySet())
        {
            System.out.println(entry.getKey() + " : "+ entry.getValue());
        }
    }
}
