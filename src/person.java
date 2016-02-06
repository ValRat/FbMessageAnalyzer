import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/*
 * An object Representing a person
 */
public class person {
    
    private List<message> messages = new ArrayList<message>();
    private final String realName;
    private List<String> alias = new ArrayList<String>();

    /**
     * Constructor that requires the name of the person
     * @param name - the name of the person
     */
    public person(String name) {
        this.realName = name;
    }
    
    /**
     * Adds a message to the user's message list 
     * @param input - the message being added
     */
    public void addMessage(message input) {
        messages.add(input);
    }
    
    /**
     * Merges two people together. The person on which this function
     * is invoked will absorb the person in the parameter.
     * @param alias - alias being absorbed
     */
    public void addAlias(person alias) {
        this.alias.add(alias.getName());
        this.messages.addAll(alias.getMessages());
    }
    
    /**
     * Returns the name of this person
     * @return the name of this person
     */
    public String getName() {
        return realName;
    }
    
    /**
     * Returns the message list of this person
     * @return a list of messages by this person
     */
    public List<message> getMessages(){
        return messages;
    }
    
    /**
     * Returns number of messages by this person
     * @return the number of messages by this person
     */
    public long countMessages() {
        return messages.size();
    }
    
    /**
     * Returns whether the person have this alias
     * @param alias - the alias in question
     * @return true if this person have that alias, false otherwise
     */
    public Boolean haveAlias(String alias) {
        if (alias.equals(realName)) {
            return true;
        } else {
            for (String i : this.alias) {
                if (i.equals(alias)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Returns a random message by this person
     * @return a random message by this person
     */
    public String randomMessage(){
        Random rnd = new Random();
        int ranNum = rnd.nextInt(messages.size());
        return messages.get(ranNum).getMessage();
        
    }
    
    /**
     * Returns the amount of times this person have said a string
     * @param keyword - the string being analyzed
     * @return the number of times this person have said this string
     */
    public long countTimesSaid(String keyword) {
        long count = 0;
        for (message currMessage : messages) {
            String currString = currMessage.getMessage();
            count += StringUtils.countMatches(currString, keyword);
        }
        return count;
    }
    
    /**
     * Returns a list of the words and the amount of times the word has been said by this person.
     * @return A map with a string and an integer representing the amount of times 
     *          the person had said something
     * TODO: Clean up regex, maybe order the map inside this function
     */
    public Map<String, Integer> commonWord() {
        Map<String, Integer> countSaid = new HashMap<String, Integer>();
        for (message currMessage : messages) {
            String words[] = StringUtils.split(currMessage.getMessage(), null);
            for (String word : words) {
                word = word.toLowerCase().replaceAll("[^A-Za-z0-9]\'", "");
                int count = countSaid.containsKey(word) ? countSaid.get(word) : 0;
                count++;
                countSaid.put(word, count);
            }
        }
        return countSaid;

    }

}
