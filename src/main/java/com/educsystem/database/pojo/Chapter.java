package com.educsystem.database.pojo;

import javax.persistence.*;

/**
 * Created by Denis on 26.02.2017.
 */
@Entity
@Table(name = "chapter")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "user_level")
    private int user_level;

    public Chapter(){

    }

    public Chapter(int id, String title, String description, int user_level) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.user_level = user_level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUser_level() {
        return user_level;
    }

    public void setUser_level(int user_level) {
        this.user_level = user_level;
    }
}
