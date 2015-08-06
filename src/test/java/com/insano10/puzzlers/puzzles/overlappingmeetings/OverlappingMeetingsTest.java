package com.insano10.puzzlers.puzzles.overlappingmeetings;

import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static org.assertj.core.api.Assertions.assertThat;

public class OverlappingMeetingsTest
{
    public static final ZoneId LONDON_TZ = ZoneId.of("Europe/London"); // normally +0 but is +1 as test dates are in BST
    public static final ZoneId NEW_YORK_TZ = ZoneId.of("America/New_York"); // normally -5 but is -4 as test dates are in EDT

    @Test
    public void shouldFindOverlappingMeetingMinutesBetweenTwoPeopleWithMeetingsAtTheSameTime() throws Exception
    {
        //given
        Person alice = new Person(LONDON_TZ);
        Person bob = new Person(LONDON_TZ);

        Meeting meeting1 = Meeting.create(0L, "2015-07-10T08:00:00+01:00", "2015-07-10T10:00:00+01:00");
        Meeting meeting2 = Meeting.create(1L, "2015-07-10T08:00:00+01:00", "2015-07-10T10:00:00+01:00");

        //when
        alice.inviteToMeeting(meeting1);
        bob.inviteToMeeting(meeting2);

        //then
        List<ZonedInterval> conflictingTimeIntervals =  Conflictinator.getConflictingMeetingTimeIntervals(alice, bob);

        ZonedInterval expectedConflictingInterval = new ZonedInterval(
                ZonedDateTime.parse("2015-07-10T08:00:00+01:00[Europe/London]", ISO_DATE_TIME),
                ZonedDateTime.parse("2015-07-10T10:00:00+01:00[Europe/London]", ISO_DATE_TIME));

        assertThat(conflictingTimeIntervals).containsExactly(expectedConflictingInterval);
        assertThat(conflictingTimeIntervals.get(0).getDurationMins()).isEqualTo(120);
    }

    @Test
    public void shouldFindOverlappingMeetingMinutesBetweenTwoPeopleWithDifferentLengthMeetings() throws Exception
    {
        //given
        Person alice = new Person(LONDON_TZ);
        Person bob = new Person(LONDON_TZ);

        Meeting meeting1 = Meeting.create(0L, "2015-07-10T08:00:00+01:00", "2015-07-10T10:00:00+01:00");
        Meeting meeting2 = Meeting.create(1L, "2015-07-10T09:00:00+01:00", "2015-07-10T09:30:00+01:00");

        //when
        alice.inviteToMeeting(meeting1);
        bob.inviteToMeeting(meeting2);

        //then
        List<ZonedInterval> conflictingTimeIntervals = Conflictinator.getConflictingMeetingTimeIntervals(alice, bob);

        ZonedInterval expectedConflictingInterval = new ZonedInterval(
                ZonedDateTime.parse("2015-07-10T09:00:00+01:00[Europe/London]", ISO_DATE_TIME),
                ZonedDateTime.parse("2015-07-10T09:30:00+01:00[Europe/London]", ISO_DATE_TIME));

        assertThat(conflictingTimeIntervals).containsExactly(expectedConflictingInterval);
        assertThat(conflictingTimeIntervals.get(0).getDurationMins()).isEqualTo(30);
    }

    @Test
    public void shouldNotBeOverlappingIfTwoPeopleAttendTheSameMeeting() throws Exception
    {
        //given
        Person alice = new Person(LONDON_TZ);
        Person bob = new Person(LONDON_TZ);

        Meeting theMeeting = Meeting.create(0L, "2015-07-10T08:00:00+01:00", "2015-07-10T10:00:00+01:00");

        //when
        alice.inviteToMeeting(theMeeting);
        bob.inviteToMeeting(theMeeting);

        //then
        assertThat(Conflictinator.getConflictingMeetingTimeIntervals(alice, bob)).isEmpty();
    }

    @Test
    public void shouldNotBeOverlappingIfThereAreNoMeetings() throws Exception
    {
        //given
        Person alice = new Person(LONDON_TZ);
        Person bob = new Person(LONDON_TZ);
        Person charlie = new Person(LONDON_TZ);

        //then
        assertThat(Conflictinator.getConflictingMeetingTimeIntervals(alice, bob, charlie)).isEmpty();
    }

    @Test
    public void shouldNotBeOverlappingIfThereAreNoPeople() throws Exception
    {
        //given
        Person alice = new Person(LONDON_TZ);

        Meeting theMeeting = Meeting.create(0L, "2015-07-10T08:00:00+01:00", "2015-07-10T10:00:00+01:00");

        //when
        alice.inviteToMeeting(theMeeting);

        //then
        assertThat(Conflictinator.getConflictingMeetingTimeIntervals(alice)).isEmpty();
    }

    @Test
    public void shouldFindOverlappingMeetingMinutesBetweenTwoPeopleInDifferentTimeZonesAndReportThemInTheTimeZoneOfTheSubject() throws Exception
    {
        //given
        Person alice = new Person(LONDON_TZ);
        Person bob = new Person(NEW_YORK_TZ);

        Meeting londonMeeting = Meeting.create(0L, "2015-07-10T08:00:00+01:00[Europe/London]", "2015-07-10T09:00:00+01:00[Europe/London]");
        Meeting newYorkMeeting = Meeting.create(1L, "2015-07-10T03:00:00-04:00[America/New_York]", "2015-07-10T03:30:00-04:00[America/New_York]");

        //when
        alice.inviteToMeeting(londonMeeting);
        bob.inviteToMeeting(newYorkMeeting);

        //then
        List<ZonedInterval> conflictingTimeIntervals = Conflictinator.getConflictingMeetingTimeIntervals(alice, bob);

        ZonedInterval expectedConflictingInterval = new ZonedInterval(
                ZonedDateTime.parse("2015-07-10T08:00:00+01:00[Europe/London]", ISO_DATE_TIME),
                ZonedDateTime.parse("2015-07-10T08:30:00+01:00[Europe/London]", ISO_DATE_TIME));

        assertThat(conflictingTimeIntervals).containsExactly(expectedConflictingInterval);
        assertThat(conflictingTimeIntervals.get(0).getDurationMins()).isEqualTo(30);

        //then (from bob's perspective)
        conflictingTimeIntervals = Conflictinator.getConflictingMeetingTimeIntervals(bob, alice);

        expectedConflictingInterval = new ZonedInterval(
                ZonedDateTime.parse("2015-07-10T03:00:00-04:00[America/New_York]", ISO_DATE_TIME),
                ZonedDateTime.parse("2015-07-10T03:30:00-04:00[America/New_York]", ISO_DATE_TIME));

        assertThat(conflictingTimeIntervals).containsExactly(expectedConflictingInterval);
        assertThat(conflictingTimeIntervals.get(0).getDurationMins()).isEqualTo(30);
    }


    /*
    todo:

    - meetings across different time zones
    - multiple meetings overlapping at the same time (do not have duplicated ZonedIntervals)

     */
}