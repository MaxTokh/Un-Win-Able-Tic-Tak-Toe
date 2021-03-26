public class TicTacToe {
    public Board board = new Board();
    // Four avaliable players
    Player[] players = { new Human(), new Sequence(), new RandoBot(), new MaxBot()};
    
    public void play()
    {
        boolean again = true;
        do
        {
            Player p1 = selectPlayer('X'); // Assigns one player to Player1
            Player p2 = selectPlayer('O'); // Assigns other player to Player2
            game(p1, p2);
            again = oneMore(); // Allows options to play again
        } while (again);
    }
    
    Player selectPlayer(char mark) { // makes assignment of players to move first or second
        for (int i = 0; i < players.length; ++i) {
            UI.say(String.format("%d) %s \n", i + 1, players[i].name()));
        }
        int n = 0;
        boolean invalid = true;
        do {
            String reply = UI.ask("Select a player for " + mark + " : ");
            n = Integer.valueOf(reply);
            invalid = n < 1 || n >  players.length;
            if (invalid)
                UI.say("Choose a valid opponent.");
        } while (invalid);
        return players[n - 1];
    }
    
    void game (Player p1, Player p2) {
        board = new Board();
        for (int turn = 1; turn < 10; ++turn) // terminates game when all squares are full
        {
            Player p; 
            char mark;
            
            if (turn % 2 == 1) 
            {
                p = p1;
                mark = 'X';
            }
            else 
            {
                p = p2;
                mark = 'O';
            }
            
            int square = p.move(turn, mark, board); // Allows players to resign
            if (square == 0) {
                UI.say(p.name() + " resigns!\n");
                break;
            }

            UI.say(String.format("%s places %c in square %d \n", p.name(), mark, square));
            boolean win = board.checkForWin(square, mark);
            board.put(square, (turn % 2  == 1) ? 'X': 'O');
            board.print();
            if (win) {
                UI.say(p.name() + " as " + mark + " wins!\n");
                break;
            }
        }
    }
    
    boolean oneMore() { // Allows players to play again
        String reply = UI.ask("Another game? [Y/N] : ");
        return Character.toLowerCase(reply.charAt(0)) == 'y';
    }
}