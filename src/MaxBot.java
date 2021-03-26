// Bit designed to be good at the game and minimize loses

import java.util.Random;

class MaxBot implements Player {
    Random random = new Random();

    public String name() {
        return "MaxBot";   
    }
    
    public int move(int turn, char mark, Board board)
    {
        int square = 0;
        if (turn == 1) {
            square = firstMove(); 
        } else if (turn == 2) {
            square = secondMove(board); 
        } else {
            square = lookForWin(mark, board);
            if (square == 0)
                square = lookForBlock(mark, board);
            if (square == 0)
                //Only losing opportunity is conner bait
                square = selectRandomCorner(board);
        }
        return square;
    }

    public int lookForWin(char mark, Board board) // looks for winning moves
    {
        for (int [] line : board.lines) {
           int ours = 0;
           int empty = 0;
           for (int i = 0; i < 3; ++i) {
               if (board.at(line[i]) == mark) {
                  ++ours;
               } else if (Character.isDigit(board.at(line[i]))) {
                  empty = line[i]; 
               }
               
           }
           if (ours == 2 && empty != 0) {
              return empty;
           }
        }
        return 0;
    }
    
    public int lookForBlock(char mark, Board board) // looks for lines where opponent could win on next turn
    {
        if (mark == 'X')
            return lookForWin('O', board);
        return lookForWin('X', board);
    }
    
    public int randomMove(Board board)
    {
        // randomly selects a square in a grid
        int square = 0;
        do {
            square = random.nextInt(9) + 1;
        } while ( ! board.isEmpty(square)); 
        return square;
    }
    
    public int selectRandomCorner(Board board) // randomly selects a corner - 1,3,7, or 9
    {
        int square = 0;
        do {
            int seed = random.nextInt(4);
            if (seed > 1) 
                ++seed;
            square = 1 + seed * 2;
        } while ( ! board.isEmpty(square)); 
        return square;
    }

    protected int firstMove () { // randomly takes middel-outside, a corner, or middle-middle square as first move
        switch (random.nextInt(3)) {
            case 0: 
                return 5;
            case 1: 
                int seed = random.nextInt(4);
                if (seed > 1) 
                    ++seed;
                return 1 + seed * 2;
                // or selectRandomCorner();
            default: 
                return random.nextInt(4) * 2;
        }
    }

    protected int secondMove (Board board) { // takes middle (if available) or corners as second move

        if (board.isEmpty(5))
            return 5;
        
            int seed = random.nextInt(4);
            if (seed > 1) 
                ++seed;
            return 1 + seed * 2;
    }
}