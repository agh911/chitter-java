package com.agh.chitter.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "peeps")
public class Peep {
    @Id
    private String id;
    private String name;
    private String username;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String createdAt;

    public Peep() {
    }

    public Peep(String name, String username, String content, String createdAt) {
        this.name = name;
        this.username = username;
        this.content = content;
        this.createdAt = createdAt;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return createdAt;
    }

    public void setDate(String createdAt) {
        this.createdAt = createdAt;
    }
}
