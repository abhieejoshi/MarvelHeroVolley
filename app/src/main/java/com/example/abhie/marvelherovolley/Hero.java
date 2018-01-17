package com.example.abhie.marvelherovolley;

/**
 * Created by abhie on 3/1/18.
 */

public class Hero {
    String name,imageUrl;

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Hero(String name, String imageUrl) {

        this.name = name;
        this.imageUrl = imageUrl;
    }
}
