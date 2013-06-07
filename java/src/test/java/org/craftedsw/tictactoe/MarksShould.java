package org.craftedsw.tictactoe;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.craftedsw.tictactoe.Board.*;
import static org.craftedsw.tictactoe.builder.MarksBuilder.marks;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(Theories.class)
public class MarksShould {

    @Test public void
    should_inform_when_it_is_empty() {
        Marks marks = marks().build();

        assertThat(marks.isEmpty(), is(true));
    }

    @Test public void
    should_inform_when_it_is_not_empty() {
        Marks marks = marks().fromPlayerOneAt(CELL_1).build();

        assertThat(marks.isEmpty(), is(false));
    }

    @Test public void
    should_inform_when_it_contains_more_than_one_mark() {
        Marks marks = marks().fromPlayerOneAt(CELL_1, CELL_2).build();

        assertThat(marks.containsSingleMark(), is(false));
    }

    @Test public void
    should_inform_when_it_contains_a_single_mark() {
        Marks marks = marks().fromPlayerOneAt(CELL_1).build();

        assertThat(marks.containsSingleMark(), is(true));
    }

    @Test public void
    should_inform_when_no_corners_are_marked() {
        Marks marks = marks().fromPlayerOneAt(CELL_2).build();

        assertThat(marks.hasAnyCornerMarked(), is(false));
    }

    @Test public void
    should_inform_when_at_least_one_corner_is_marked() {
        Marks marks = marks().fromPlayerOneAt(CELL_1, CELL_3, CELL_7, CELL_9).build();

        assertThat(marks.hasAnyCornerMarked(), is(true));
    }

    @DataPoints
    public static int[][] oppositeCorners() {
        return new int[][] {
                {CELL_1, CELL_9},
                {CELL_9, CELL_1},
                {CELL_3, CELL_7},
                {CELL_7, CELL_3}
        };
    }

    @Theory public void
    corner_cell_has_opposite_corner_cell(int[] oppositeCells) {
        Marks marks = marks().build();
        int cell = oppositeCells[0];
        int oppositeCell = oppositeCells[1];

        assertThat(marks.oppositeCornerOf(cell), is(oppositeCell));
    }

    @Test public void
    should_return_no_empty_opposite_cell_when_already_marked() {
        Marks marks = marks().fromPlayerOneAt(CELL_1, CELL_3, CELL_7, CELL_9).build();

        assertThat(marks.emptyOppositeCell(CELL_1), is(NO_CELL));
    }

    @Test public void
    should_inform_which_is_the_first_empty_cell() {
        Marks marks = marks().fromPlayerOneAt(CELL_1, CELL_2, CELL_7, CELL_9).build();

        assertThat(marks.firstEmptyCell(), is(CELL_3));
    }
}