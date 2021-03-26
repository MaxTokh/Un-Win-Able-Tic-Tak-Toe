// AI that plays randomly selected squares

import java.util.Random;

class RandoBot implements Player {
    Random random = new Random();

    public String name() {
        return "RandoBot";   
    }
    public int move(int turn, char mark, Board board)
    {
        // randomly selects a square in a grid
        int square = 0;
        do {
            square = random.nextInt(9) + 1;
        } while ( ! board.isEmpty(square)); 
        return square;
    }

}
