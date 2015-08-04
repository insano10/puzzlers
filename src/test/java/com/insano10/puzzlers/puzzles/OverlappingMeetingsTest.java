package com.insano10.puzzlers.puzzles;

import com.insano10.puzzlers.puzzles.OverlappingMeetings.Meeting;
import com.insano10.puzzlers.puzzles.OverlappingMeetings.Person;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.Set;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

public class OverlappingMeetingsTest
{

    @Test
    @Ignore("not finished")
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

        Set<OverlappingMeetings.ZonedInterval> conflictingTimeIntervals = alice.conflictingTimeIntervals(bob);

        System.out.println(conflictingTimeIntervals);

        OverlappingMeetings.ZonedInterval expectedConflictingInterval = new OverlappingMeetings.ZonedInterval(
                ZonedDateTime.parse("2015-07-10T09:00:00+01:00", ISO_DATE_TIME),
                ZonedDateTime.parse("2015-07-10T09:30:00+01:00", ISO_DATE_TIME));

        Assertions.assertThat(conflictingTimeIntervals).containsExactly(expectedConflictingInterval);

    }
}