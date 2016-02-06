/**
 * A Java message object containing the message content, the timestamp, and the author
 * @author Valerian
 *
 */
public class message {
    private final String content;
    private final String timestamp;
    private final String author;
    
    public message(String content, String timestamp, String author){
        this.content = content;
        this.timestamp = timestamp;
        this.author = author;
    }
    
    public String getMessage(){
        return content;
    }
    
    public String getTimestamp(){
        return timestamp;
    }
    
    public String getAuthor(){
        return author;
    }
    
    @Override
    public String toString(){
        StringBuilder currString = new StringBuilder();
        currString.append(author);
        currString.append(": ");
        currString.append(content);
        currString.append(" ## AT ");
        currString.append(timestamp);
        return currString.toString();
    }
}
