package com.myapp.demomonth.Model;

public class Message {
    private String sender;
    private String receiver;
    private String time;
    private String content;

    public Message(String sender, String receiver, String time, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.time = time;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }
}