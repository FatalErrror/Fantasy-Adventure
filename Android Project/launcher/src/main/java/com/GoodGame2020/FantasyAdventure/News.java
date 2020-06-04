package com.GoodGame2020.FantasyAdventure;

public class News {
    public News(String name, String body) {
        this.name = name;
        this.body = body;
        ImageURL = "";
    }

    public News(String ImageURL, String name, String body) {
        this.name = name;
        this.body = body;
        this.ImageURL = ImageURL;
    }


    public String name;
    public String body;

    public String ImageURL;
}
