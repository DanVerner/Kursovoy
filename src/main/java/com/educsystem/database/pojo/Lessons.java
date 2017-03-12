package com.educsystem.database.pojo;

/**
 * Created by Denis on 26.02.2017.
 */
public class Lessons {
    private int id;
    private int chapter_id;
    private String title;
    private String description;
    private String path;

    public Lessons(int id, int chapter_id, String title, String description, String path) {
        this.id = id;
        this.chapter_id = chapter_id;
        this.title = title;
        this.description = description;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(int chapter_id) {
        this.chapter_id = chapter_id;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
