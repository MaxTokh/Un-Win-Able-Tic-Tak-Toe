// PLayer selects where they would like to play

class Human implements Player {

    public String name() {
        return "Human";   
    }
    
    public int move(int turn, char mark, Board board)
    {
        int square = 0;
        boolean valid = false;
        if (turn == 1)
            board.print();
        do {
            String reply = UI.ask("Enter a number of a square to place " + mark + " into \nor 0 to resign : ");
            valid = Character.isDigit(reply.charAt(0));
            if (valid) {
                square = Integer.valueOf(reply);    
            }
            else 
            {
                UI.say("Not a number!");
            }
            
        } while ( ! valid || (square != 0 && ! board.isEmpty(square))); 
        return square;
    }
}


// valid = Character.isDigit(reply.charAt(0)); 