package ru.etstudio.kuhmeyster.adapter;

public class Card {

    private String title;

    private int imageId;

    public Card(String title, int imageId) {
        this.title = title;
        this.imageId = imageId;
    }

    public String getLabel() {
        return title;
    }

    public int getImageId() {
        return imageId;
    }
}
