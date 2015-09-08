package com.insano10.puzzlers.puzzles;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class MixedUpBoardingPasses
{
    /*
    Given a stack of unordered boarding passes, find the starting and ending destinations

    Assumptions:
     - it is a one way trip. If there was a cycle back to the start you could not determine the starting location without also looking at the date
       (at which you could just say the start was the earliest pass and destination was the latest)


     */


    /*
            A -> B -> C -> D -> E
     */
    @Test
    public void shouldFindStartingAndEndingDestinationsInOneWayTrip() throws Exception
    {
        List<BoardingPass> passes = new ArrayList<>();
        passes.add(new BoardingPass("D", "E"));
        passes.add(new BoardingPass("B", "C"));
        passes.add(new BoardingPass("A", "B"));
        passes.add(new BoardingPass("C", "D"));

        assertStartAndDestinationLocations(passes, "A", "E");
    }

    /*
            A -> B -> C -> D -> E
                 ^         |
                 |         |
                 ----------
     */
    @Test
    public void shouldFindStartingAndEndingDestinationsInTripWithCycle() throws Exception
    {
        List<BoardingPass> passes = new ArrayList<>();
        passes.add(new BoardingPass("D", "B"));
        passes.add(new BoardingPass("A", "B"));
        passes.add(new BoardingPass("D", "E"));
        passes.add(new BoardingPass("B", "C"));
        passes.add(new BoardingPass("C", "D"));
        passes.add(new BoardingPass("B", "C"));
        passes.add(new BoardingPass("C", "D"));

        assertStartAndDestinationLocations(passes, "A", "E");
    }

    private void assertStartAndDestinationLocations(List<BoardingPass> passes, String start, String destination)
    {
        Set<String> comingFrom = new HashSet<>();
        Set<String> goingTo = new HashSet<>();

        for (BoardingPass pass : passes)
        {
            comingFrom.add(pass.start);
            goingTo.add(pass.end);
        }

        Sets.SetView<String> leftButNeverEntered = Sets.difference(comingFrom, goingTo);
        Sets.SetView<String> enteredButNeverLeft = Sets.difference(goingTo, comingFrom);

        if(leftButNeverEntered.size() != 1)
        {
            fail("Found " + leftButNeverEntered.size() + " locations that you left from but never entered");
        }

        if(enteredButNeverLeft.size() != 1)
        {
            fail("Found " + enteredButNeverLeft.size() + " locations that you entered but never left from");
        }

        String startLocation = leftButNeverEntered.iterator().next();
        String destinationLocation = enteredButNeverLeft.iterator().next();

        assertThat(startLocation).isEqualTo(start);
        assertThat(destinationLocation).isEqualTo(destination);
    }


    private static final class BoardingPass
    {
        public final String start;
        public final String end;

        private BoardingPass(String start, String end)
        {
            this.start = start;
            this.end = end;
        }
    }
}
