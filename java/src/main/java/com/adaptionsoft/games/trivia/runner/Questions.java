package com.adaptionsoft.games.trivia.runner;

import java.util.LinkedList;

/**
 * Created by pivotal on 19/05/2017.
 */
public class Questions {
    private static final int QUESTIONS_COUNT = 50;

    private LinkedList<String> popQuestions = new LinkedList<String>();
    private LinkedList<String> scienceQuestions = new LinkedList<String>();
    private LinkedList<String> sportsQuestions = new LinkedList<String>();
    private LinkedList<String> rockQuestions = new LinkedList<String>();

    public Questions() {
        initQuestions();
    }

    private void initQuestions() {
        for (int i = 0; i < QUESTIONS_COUNT; i++) {
            popQuestions.addLast(createPopQuestion(i));
            scienceQuestions.addLast(createScienceQuestion(i));
            sportsQuestions.addLast(createSportsQuestion(i));
            rockQuestions.addLast(createRockQuestion(i));
        }
    }

    private String createPopQuestion(int index) {
        return "Pop Question " + index;
    }

    private String createScienceQuestion(int index) {
        return "Science Question " + index;
    }

    private String createSportsQuestion(int index) {
        return "Sports Question " + index;
    }

    private String createRockQuestion(int index) {
        return "Rock Question " + index;
    }

    public String getQuestion(Category category) {
        if (category == Category.POP)
            return popQuestions.removeFirst();
        else if (category == Category.SIENCE)
            return scienceQuestions.removeFirst();
        else if (category == Category.SPORTS)
            return sportsQuestions.removeFirst();
        else if (category == Category.POP.ROCK)
            return rockQuestions.removeFirst();
        else
            throw new RuntimeException("Invalid Category!");

    }
}
