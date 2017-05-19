package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.trivia.runner.Category;
import com.adaptionsoft.games.trivia.runner.Questions;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<String> players = new ArrayList<String>();

    private int[] places = new int[6];
    private int[] purses = new int[6];

    private boolean[] inPenaltyBox = new boolean[6];

    private int currentPlayer = 0;

    private boolean isGettingOutOfPenaltyBox;

    private Questions questions = new Questions();

    public void addPlayer(String playerName) {
        players.add(playerName);

        int numberOfPlayers = numberOfPlayers();

        places[numberOfPlayers] = 0;
        purses[numberOfPlayers] = 0;
        inPenaltyBox[numberOfPlayers] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + numberOfPlayers);
    }

    private int numberOfPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        String currentPlayerName = players.get(this.currentPlayer);

        System.out.println(currentPlayerName + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (inPenaltyBox()) {
            playFromPenaltyBox(roll);
        } else {
            playNormal(roll);
        }
    }

    private boolean inPenaltyBox() {
        return inPenaltyBox[currentPlayer];
    }

    private void playFromPenaltyBox(int roll) {
        String currentPlayerName = players.get(this.currentPlayer);

        if (odd(roll)) {
            isGettingOutOfPenaltyBox = true;
            System.out.println(currentPlayerName + " is getting out of the penalty box");
            playNormal(roll);
        } else {
            isGettingOutOfPenaltyBox = false;
            System.out.println(currentPlayerName + " is not getting out of the penalty box");
        }
    }

    private boolean odd(int roll) {
        return roll % 2 != 0;
    }

    private void playNormal(int roll) {
        String currentPlayerName = players.get(this.currentPlayer);

        places[currentPlayer] = places[currentPlayer] + roll;
        if (places[currentPlayer] > 11) {
            places[currentPlayer] = places[currentPlayer] - 12;
        }

        System.out.println(currentPlayerName
                + "'s new location is "
                + places[currentPlayer]);

        System.out.println("The category is " + currentCategory());

        askQuestion();
    }

    private void askQuestion() {
        System.out.println(questions.getQuestion(currentCategory()));
    }

    private Category currentCategory() {
        return Category.fromPlace(places[currentPlayer]);
    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                return creditPlayer();
            } else {
                moveToNextPlayer();
                return true;
            }
        } else {
            return creditPlayer();
        }
    }

    private boolean creditPlayer() {
        String currentPlayerName = players.get(currentPlayer);

        System.out.println("Answer was correct!!!!");

        purses[currentPlayer]++;

        System.out.println(currentPlayerName
                + " now has "
                + purses[currentPlayer]
                + " Gold Coins.");

        moveToNextPlayer();

        return didPlayerWin();
    }

    public boolean wrongAnswer() {
        String currentPlayerName = players.get(currentPlayer);

        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayerName + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        moveToNextPlayer();

        return true;
    }

    private void moveToNextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) {
            currentPlayer = 0;
        }
    }

    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }
}
