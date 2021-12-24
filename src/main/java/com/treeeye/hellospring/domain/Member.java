package com.treeeye.hellospring.domain;

public class Member {

    private long id;
    private String name;

    // From intelliJ, command+N is generator, and can generate getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
