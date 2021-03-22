//package com.maxtok;

import java.util.Scanner;
import java.util.Random;

public class Game { // Creates board
    private char [][] board = {{'1', '2' , '3'},
                               {'4', '5' , '6'},
                               {'7', '8' , '9'} };
    private Scanner input;

    public Game() {
        input = new Scanner(System.in);
    }

    public void print () { // Prints board
      System.out.println("Available Moves:");
        for (int y = 0; y < 3 ; ++y)
        {
            System.out.printf (" %c | %c | %c \n", board[y][0], board[y][1], board[y][2] );
            if (y < 2) { // Creates breaks in the board
                System.out.println(" ---------");
            }
        }
        System.out.println(" "); // Makes break in between each move
    }

    public void play()
    {
        boolean playAgain = true;
        while (playAgain) {
            int move = 0;
            do {
                theirMove();
                ++move;
                if (theyWon()) {
                    System.out.println("Congrats!");
                    break;
                } else if (move == 9) { //Finds the game is over after 9 moves
                    System.out.println("A tie. How original.");
                    break;
                }
                ourMove();
                ++move;
            } while (!weWon());
            destroyEnemyMorale(); //Prints random discouraging message
            playAgain = askToPlay(); //STILL NEEDS TO BE MADE
        }
    }

    private boolean askToPlay() { //STILL NEEDS TO BE FINISHED
        System.out.println("Dare to try Again? \n Perply \"sure\" or \"nope\"");
        String str = input.nextLine();
        return (str.equals("sure"));
    }

    /*
NO NEEDED
    public void testScoring(char [][] test)
    {
        board = test;
        print();
        System.out.println(analyzeBoard());
    } */

    private void theirMove() {
        print();
        getTheirs();
    }

    private int getTheirs() {
        int theirs, y, x ;
        do {
            System.out.println("Which square would you like to take");
            System.out.print("reply a number to your corresponding square or 0 to resign: ");
            theirs = input.nextInt();
            y = (theirs - 1) / 3; //Calculates which squre user would like to take
            x = (theirs - 1) % 3;
        } while (theirs < 1 || theirs > 9 || board[y][x] < '1' || board[y][x] > '9' ); //Sets parameters on the board to only real quares. No thinking outside the bbo :(

        put(theirs, 'X');
        System.out.println(" ");
        print();
        return theirs;
    }

    private void ourMove() {
        int bestScore = 100000;
        int bestX = 0;
        int bestY = 0;
        for (int y = 0; y < 3 ; ++y) {
            for (int x = 0; x < 3 ; ++x) {
                if (board[y][x] != 'X' && board[y][x] != 'O') {
                    char temp = board[y][x];
                    board[y][x] = 'O';
                    int moveScore = analyzeBoard();
                    if (moveScore < bestScore)
                    {
                        bestScore = moveScore;
                        bestX = x;
                        bestY = y;
                    }
                    board[y][x] = temp;
                }
            }
        }
        board[bestY][bestX] = 'O';
        print();
        return;
    }

    private boolean theyWon()
    {
       return analyzeBoard() > 1000;
    }

    private boolean weWon()
    {
       boolean win = analyzeBoard() < -1000;
       if (win)
           System.out.println("As expected...");
       return win;
    }

    private int analyzeBoard()
    {
        int score = 0;
        for (int y = 0; y < 3 ; ++y) {
            for (int x = 0; x < 3 ; ++x) {
                score += analyzeLines(y,x);
            }
        }
        return score;
    }

    private int analyzeLines(int y, int x) {
        int countX = 0;
        int countO = 0;
        int score = 0;
        for (int dy = 0; dy <= +1 ; ++dy) {
            for (int dx = -1; dx <= +1 ; ++dx) {
                if (dx + dy <= 0)
                    continue;
                if (x - dx < 0 || x + dx > 2)
                    continue;
                if (y - dy < 0 || y + dy > 2)
                    continue;
                countX = getCount('X', y, x, dy, dx);
                countO = getCount('O', y, x, dy, dx);
                switch(countX)
                {
                    case 3:
                        return 10000; // X's won
                    case 2:
                        if (countO == 0)
                            score += 20;
                        else if (countO == 1)
                            score -= 100;
                        break;
                    case 1:
                        if (countO == 2)
                            score += 100;
                        else if (countO == 0)
                            score -= 5;
                        break;
                    case 0:
                        if (countO == 3)
                            return -10000; // O's won
                        else if (countO == 2)
                            score -= 20;
                        else if (countO == 1)
                            score += 5;
                        break;
                }
            }
        }
//        System.out.printf("Square %d %d = %d \n", y, x, score);
        return score;
    }

    private int getCount(char xo, int y, int x, int dy, int dx) {
        int count = 0;
        if (board[y - dy][x - dx] == xo)
            ++count;
        if (board[y][x] == xo)
            ++count;
        if (board[y + dy][x + dx] == xo)
            ++count;
        return count;
    }

    public void game() {
        // Runs all nine possible branches of the game
        print();
        move1();
        print();
        move2();
        //print();
        //move3();
        //print();
        //move4();
        //print();
        //move5();
        //print();
        //move6();
        //print();
        //move7();
        //print();
        //move8();
        //print();
        //move9();
        //print();

        destroyEnemyMorale();
    }

    private void destroyEnemyMorale() {
        // Randomly choicen comments to crushes opponents souls!! Mwahaha!!
        String [] arr = {"Get REKT", "I knew you didn't have it in you!!", "Just give up...", "As expected"};
        Random random = new Random();
        int select = random.nextInt(arr.length);
        System.out.println("Random String selected: " + arr[select]);
    }

    private void move2() {
    }

    private void put (int square, char xo)
    {
        board[(square - 1) / 3 ][(square - 1) % 3 ] = xo;
    }

    private void move1() {
        // ask user for with first square they would like
        int theirs = getTheirs();

        switch (theirs) {
            case 5: reply1corner();
                break;
            default: reply1center();
                break;
        }
    }

    private void reply1corner() {
        put(1, 'O');
        print();
    }

    private void reply1center(){
        // appropriately responds to user's first move
        put(5, 'O');
        print();
    }
}
