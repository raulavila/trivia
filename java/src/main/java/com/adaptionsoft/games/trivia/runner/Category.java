package com.adaptionsoft.games.trivia.runner;

public enum Category {
    POP("Pop"),
    SIENCE("Science"),
    SPORTS("Sports"),
    ROCK("Rock");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Category fromPlace(int place) {
        if (place == 0) return POP;
        if (place == 4) return POP;
        if (place == 8) return POP;
        if (place == 1) return SIENCE;
        if (place == 5) return SIENCE;
        if (place == 9) return SIENCE;
        if (place == 2) return SPORTS;
        if (place == 6) return SPORTS;
        if (place == 10) return SPORTS;
        return ROCK;
    }

}
