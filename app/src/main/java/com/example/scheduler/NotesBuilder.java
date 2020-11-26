package com.example.scheduler;

public class NotesBuilder {

    private String title,content;

    public NotesBuilder() {

    }

    public NotesBuilder(String title,String content){
    this.title=title;
    this.content=content;
    }

    public String getContent() {
        return content;
    }


    public String getTitle() {
        return title;
    }
}
