package org.craftedsw.tictactoe.model.board;

import org.craftedsw.tictactoe.model.game.Player;
import org.junit.Test;

import static org.craftedsw.tictactoe.model.board.BoardLines.*;
import static org.craftedsw.tictactoe.model.game.Player.PLAYER_ONE;
import static org.craftedsw.tictactoe.builder.MarksBuilder.marks;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LineShould {

    @Test public void
    should_inform_when_a_line_is_not_a_winning_line() {
        Marks marks = marks()
                              .fromPlayerOneAt(BoardStructure.CELL_1, BoardStructure.CELL_2)
                              .fromPlayerTwoAt(BoardStructure.CELL_3, BoardStructure.CELL_4)
                              .build();

        assertThat(ROW_1.isWinningLine(PLAYER_ONE, marks), is(false));
        assertThat(ROW_2.isWinningLine(PLAYER_ONE, marks), is(false));
        assertThat(ROW_3.isWinningLine(PLAYER_ONE, marks), is(false));
        assertThat(COLUMN_1.isWinningLine(PLAYER_ONE, marks), is(false));
        assertThat(COLUMN_2.isWinningLine(PLAYER_ONE, marks), is(false));
    }

    @Test public void
    should_inform_when_a_line_is_not_a_loosing_line() {
        Marks marks = marks()
                              .fromPlayerOneAt(BoardStructure.CELL_1, BoardStructure.CELL_2)
                              .fromPlayerTwoAt(BoardStructure.CELL_3, BoardStructure.CELL_4)
                              .build();

        assertThat(ROW_1.isLoosingLine(PLAYER_ONE, marks), is(false));
        assertThat(ROW_2.isLoosingLine(PLAYER_ONE, marks), is(false));
        assertThat(ROW_3.isLoosingLine(PLAYER_ONE, marks), is(false));
        assertThat(COLUMN_1.isLoosingLine(PLAYER_ONE, marks), is(false));
        assertThat(COLUMN_2.isLoosingLine(PLAYER_ONE, marks), is(false));
    }

    @Test public void
    should_inform_when_a_line_is_a_winning_line() {
        Marks marks = marks()
                            .fromPlayerOneAt(BoardStructure.CELL_1, BoardStructure.CELL_5, BoardStructure.CELL_6, BoardStructure.CELL_7)
                            .fromPlayerTwoAt(BoardStructure.CELL_3, BoardStructure.CELL_8, BoardStructure.CELL_9)
                            .build();

        assertThat(DIAGONAL_1.isWinningLine(PLAYER_ONE, marks), is(false));
        assertThat(COLUMN_1.isWinningLine(PLAYER_ONE, marks), is(true));
        assertThat(ROW_2.isWinningLine(PLAYER_ONE, marks), is(true));
    }

    @Test public void
    should_inform_when_a_line_is_a_loosing_line() {
        Marks marks = marks()
                            .fromPlayerOneAt(BoardStructure.CELL_1, BoardStructure.CELL_5, BoardStructure.CELL_6, BoardStructure.CELL_7)
                            .fromPlayerTwoAt(BoardStructure.CELL_3, BoardStructure.CELL_8, BoardStructure.CELL_9)
                            .build();

        Player opponent = PLAYER_ONE;

        assertThat(DIAGONAL_1.isLoosingLine(opponent, marks), is(false));
        assertThat(COLUMN_1.isLoosingLine(opponent, marks), is(true));
        assertThat(ROW_2.isLoosingLine(opponent, marks), is(true));
    }

}