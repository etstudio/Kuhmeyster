package ru.etstudio.kuhmeyster.adapter;

public enum DishType {

    EVERYDAY("Повседневный стол"),

    CELEBRATORY("Праздничный стол");

    private String label;

    DishType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
