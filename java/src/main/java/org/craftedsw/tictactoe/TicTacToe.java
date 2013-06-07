package org.craftedsw.tictactoe;

import org.craftedsw.tictactoe.strategy.AttackStrategy;
import org.craftedsw.tictactoe.strategy.DefenceStrategy;
import org.craftedsw.tictactoe.strategy.WinStrategy;

import static org.craftedsw.tictactoe.Board.ASK_FOR_NEXT_MARK;
import static org.craftedsw.tictactoe.BoardDisplay.CELL_INDEX_INSTRUCTIONS;
import static org.craftedsw.tictactoe.Player.PLAYER_ONE;
import static org.craftedsw.tictactoe.Player.PLAYER_TWO;

public class TicTacToe {

    public static final String WINNER_IS = "Winner is: ";
    public final static String CURRENT_BOARD_STATE_MSG = "Current state of the game: ";
    public static final String YOU_WIN = "You win!!!";
    public static final String YOU_LOSE = "You LOSE!!!";

    private final Console console;
    private final Board board;
    private boolean quit = false;

    public TicTacToe(Console console, Board board) {
        this.console = console;
        this.board = board;
    }

    public void newGame() {
        console.print(CELL_INDEX_INSTRUCTIONS);
        console.print(CURRENT_BOARD_STATE_MSG);
        console.print(board.representation());
        while (!board.hasWinner() && !quit) {
            int cellToBeMarked = console.ask(ASK_FOR_NEXT_MARK);
            board.place(cellToBeMarked - 1);
            console.print(board.representation());
        }
        console.print(WINNER_IS + board.winner());
    }

    public void newSinglePlayerGame(Opponent opponent) {
        console.print(CELL_INDEX_INSTRUCTIONS);
        console.print(CURRENT_BOARD_STATE_MSG);
        console.print(board.representation());
        while (!board.hasWinner() && !quit) {
            board.place(playerNextCell());
            if (!board.hasWinner()) {
                board.place(opponent.nextCell(board));
            }
            console.print(board.representation());
        }
        console.print(board.winner() == PLAYER_ONE
                                ? YOU_WIN
                                : YOU_LOSE);
    }

    private int playerNextCell() {
        return console.ask(ASK_FOR_NEXT_MARK) - 1;
    }

    public String boardRepresentation() {
        return board.representation();
    }

    public void quit() {
        this.quit = true;
    }

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe(new Console(), new Board());

        ticTacToe.newSinglePlayerGame(
                new InvincibleOpponent(PLAYER_TWO,
                                        new WinStrategy(),
                                        new DefenceStrategy(),
                                        new AttackStrategy()));
//        ticTacToe.newGame();
    }

}
