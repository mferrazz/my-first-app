package com.example.myfirstapp.model;

public class Book {

    private Integer id;
    private String title;
    private String author;

    public Book(){};

    public Book(String title, String author) {
        super();
        this.title = title;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Book: [id = ");
        sb.append(id);
        sb.append("], [title =  ");
        sb.append(title);
        sb.append("], [author = ");
        sb.append(author);
        sb.append("]");

        return sb.toString();
    }
}
