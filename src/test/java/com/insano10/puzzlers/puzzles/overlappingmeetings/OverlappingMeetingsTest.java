package com.insano10.puzzlers.puzzles.overlappingmeetings;

import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static org.assertj.core.api.Assertions.assertThat;

public class OverlappingMeetingsTest
{

    @Test
    public void shouldFindOverlappingMeetingMinutesBetweenTwoPeople() throws Exception
    {
        //given

        Person alice = new Person();
        Person bob = new Person();

        Meeting meeting1 = Meeting.create(0L, "2015-07-10T08:00:00+01:00", "2015-07-10T10:00:00+01:00");
        Meeting meeting2 = Meeting.create(1L, "2015-07-10T09:00:00+01:00", "2015-07-10T09:30:00+01:00");

        //when

        alice.inviteToMeeting(meeting1);
        bob.inviteToMeeting(meeting2);

        //then

        List<ZonedInterval> conflictingTimeIntervals = alice.conflictingTimeIntervals(bob);

        ZonedInterval expectedConflictingInterval = new ZonedInterval(
                ZonedDateTime.parse("2015-07-10T09:00:00+01:00", ISO_DATE_TIME),
                ZonedDateTime.parse("2015-07-10T09:30:00+01:00", ISO_DATE_TIME));

        assertThat(conflictingTimeIntervals).containsExactly(expectedConflictingInterval);
        assertThat(conflictingTimeIntervals.get(0).getDurationMins()).isEqualTo(30);

    }
}