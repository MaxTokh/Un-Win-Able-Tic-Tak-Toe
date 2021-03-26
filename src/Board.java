public class Board 
{
    public char[] grid = new char[10]; 
    public int[][] lines = { {1,2,3}, {4,5,6}, {7,8,9}, // All possible wins
                               {1,4,7}, {2,5,8}, {3,6,9},
                               {1,5,9}, {3,5,7} };
    
    public Board() { // Creates board
        for (int i = 0; i<10; ++i) 
            grid[i] = (char) ('0' + i);
    }

    public char at(int square) { // Checks if selected square is available
        if (square > 0 && square < 10)
            return grid[square];
        else
            UI.say("Please pick a valid move. \n");
            return ' ';
    }

    public void put(int square, char mark) { // occupies selected squares
        if (square > 0 && square < 10)
            grid[square] = mark;
    }
    
    public void print() { // displays board with available squares with numbers and taken squares with X's & O's
        for (int i = 1; i<10; ++i) {
            UI.say(String.format(" %c ", grid[i]));
            if (i % 3 != 0) 
                UI.say("|");
            else if (i != 9)
                UI.say("\n-----------\n"); // draws line between rows
            else
                UI.say("\n");
        }
    }
    
    public boolean checkForWin(int square, char mark) { // winning lines contain given square and two more of given marks.
        for (int [] line : lines) {                     // checkForWin iterates through lines to find this last needed winning square
           int total = 0;
           for (int i = 0; i < 3; ++i) {
               if (line[i] == square || grid[line[i]] == mark) {
                  ++total;
               }
           }
           if (total == 3)
              return true;
        }
        return false;
    }
    public boolean isEmpty(int square) {
        return grid[square] != 'X' && grid[square] != 'O';
    }
}
