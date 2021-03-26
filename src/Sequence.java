// AI that always selects next available square

class Sequence implements Player {

    public String name() {
        return "Sequence";   
    }
    
    public int move(int turn, char mark, Board board)
    {
        int square = 1; 
        while ( ! board.isEmpty(square)) {
            ++square;
        }
        return square;
    }
}