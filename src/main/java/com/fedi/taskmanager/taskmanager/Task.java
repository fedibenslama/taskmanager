package com.fedi.taskmanager.taskmanager;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks") // This specifies the MongoDB collection name
public class Task {
    @Id
    private String id; // Use String for MongoDB ID type
    private String title;
    private String description;
    private boolean completed;
    private String status;

    //Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public boolean isCompleted(){
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }
}
