package com.myapp.demomonth.Model;

public class Announcement {
    private String title;
    private String time;
    private String content;

    public Announcement(String title, String time, String content) {
        this.title = title;
        this.time = time;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }
}
