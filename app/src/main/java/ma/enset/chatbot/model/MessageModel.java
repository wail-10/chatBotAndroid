package ma.enset.chatbot.model;

import java.io.Serializable;

public class MessageModel implements Serializable {
    private String message;
    private String sender;

    public MessageModel() {
    }

    public MessageModel(String message, String sender) {
        this.message = message;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
