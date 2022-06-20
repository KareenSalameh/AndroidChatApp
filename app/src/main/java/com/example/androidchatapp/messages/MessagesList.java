package Messages;

public class MessagesList {

    private String user , lastMessage;

    private int dateOfMessage;

    public MessagesList(String user, String lastMessage, int dateOfMessage){
        this.user = user;
        this.lastMessage = lastMessage;
        this.dateOfMessage = dateOfMessage;
    }

    public String getUser() {
        return user;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public int getDateOfMessage() {
        return dateOfMessage;
    }
}
