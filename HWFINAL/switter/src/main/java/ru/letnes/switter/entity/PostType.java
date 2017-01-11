package ru.letnes.switter.entity;

public enum PostType {

    ECONOMY("Экономика"),
    SCIENCE("Наука"),
    POLITICS("Политика");

    String description;

    PostType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}