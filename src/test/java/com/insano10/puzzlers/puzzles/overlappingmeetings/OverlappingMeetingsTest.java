package com.insano10.puzzlers.puzzles.overlappingmeetings;

import org.junit.Ignore;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static org.assertj.core.api.Assertions.assertThat;

public class OverlappingMeetingsTest
{
    @Test
    public void shouldFindOverlappingMeetingMinutesBetweenTwoPeopleWithMeetingsAtTheSameTime() throws Exception
    {
        //given
        Person alice = new Person();
        Person bob = new Person();

        Meeting meeting1 = Meeting.create(0L, "2015-07-10T08:00:00+01:00", "2015-07-10T10:00:00+01:00");
        Meeting meeting2 = Meeting.create(1L, "2015-07-10T08:00:00+01:00", "2015-07-10T10:00:00+01:00");

        //when
        alice.inviteToMeeting(meeting1);
        bob.inviteToMeeting(meeting2);

        //then
        List<ZonedInterval> conflictingTimeIntervals = alice.conflictingTimeIntervals(bob);

        ZonedInterval expectedConflictingInterval = new ZonedInterval(
                ZonedDateTime.parse("2015-07-10T08:00:00+01:00", ISO_DATE_TIME),
                ZonedDateTime.parse("2015-07-10T10:00:00+01:00", ISO_DATE_TIME));

        assertThat(conflictingTimeIntervals).containsExactly(expectedConflictingInterval);
        assertThat(conflictingTimeIntervals.get(0).getDurationMins()).isEqualTo(120);
    }

    @Test
    public void shouldFindOverlappingMeetingMinutesBetweenTwoPeopleWithDifferentLengthMeetings() throws Exception
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

    @Test
    public void shouldNotBeOverlappingIfTwoPeopleAttendTheSameMeeting() throws Exception
    {
        //given
        Person alice = new Person();
        Person bob = new Person();

        Meeting theMeeting = Meeting.create(0L, "2015-07-10T08:00:00+01:00", "2015-07-10T10:00:00+01:00");

        //when
        alice.inviteToMeeting(theMeeting);
        bob.inviteToMeeting(theMeeting);

        //then
        assertThat(alice.conflictingTimeIntervals(bob)).isEmpty();
    }

    @Test
    public void shouldNotBeOverlappingIfThereAreNoMeetings() throws Exception
    {
        //given
        Person alice = new Person();
        Person bob = new Person();
        Person charlie = new Person();

        //then
        assertThat(alice.conflictingTimeIntervals(bob, charlie)).isEmpty();
    }

    @Test
    public void shouldNotBeOverlappingIfThereAreNoPeople() throws Exception
    {
        //given
        Person alice = new Person();

        Meeting theMeeting = Meeting.create(0L, "2015-07-10T08:00:00+01:00", "2015-07-10T10:00:00+01:00");

        //when
        alice.inviteToMeeting(theMeeting);

        //then
        assertThat(alice.conflictingTimeIntervals()).isEmpty();
    }

    @Test
    @Ignore
    public void shouldFindOverlappingMeetingMinutesBetweenTwoPeopleInDifferentTimeZonesAndReportThemInTheTimeZoneOfTheSubject() throws Exception
    {
        //given
        Person alice = new Person();
        Person bob = new Person();

        Meeting meeting1 = Meeting.create(0L, "2015-07-10T08:00:00+01:00", "2015-07-10T09:00:00+01:00");
        Meeting meeting2 = Meeting.create(1L, "2015-07-10T07:00:00+00:00", "2015-07-10T07:30:00+00:00");

        //when
        alice.inviteToMeeting(meeting1);
        bob.inviteToMeeting(meeting2);

        //then
        List<ZonedInterval> conflictingTimeIntervals = alice.conflictingTimeIntervals(bob);

        ZonedInterval expectedConflictingInterval = new ZonedInterval(
                ZonedDateTime.parse("2015-07-10T08:00:00+01:00", ISO_DATE_TIME),
                ZonedDateTime.parse("2015-07-10T08:30:00+01:00", ISO_DATE_TIME));

        assertThat(conflictingTimeIntervals).containsExactly(expectedConflictingInterval);
        assertThat(conflictingTimeIntervals.get(0).getDurationMins()).isEqualTo(30);

        //then (from bob's perspective)
        conflictingTimeIntervals = alice.conflictingTimeIntervals(bob);

        expectedConflictingInterval = new ZonedInterval(
                ZonedDateTime.parse("2015-07-10T07:00:00Z", ISO_DATE_TIME),
                ZonedDateTime.parse("2015-07-10T07:30:00Z", ISO_DATE_TIME));

        assertThat(conflictingTimeIntervals).containsExactly(expectedConflictingInterval);
        assertThat(conflictingTimeIntervals.get(0).getDurationMins()).isEqualTo(30);
    }


    /*
    todo:

    - meetings across different time zones
    - multiple meetings overlapping at the same time (do not have duplicated ZonedIntervals)

     */
}