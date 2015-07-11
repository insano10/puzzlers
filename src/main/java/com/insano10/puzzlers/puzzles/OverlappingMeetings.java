package com.insano10.puzzlers.puzzles;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

public class OverlappingMeetings
{
    /*
        Given a set of meetings find the amount of time someone spends in a meeting that conflicts with others

        Questions:

        - How granular do we want the time to be?

            - Realistically no one schedules a meeting to more than minute level accuracy so let's measure our conflicting time in minutes

        - What does it mean to conflict with others?

            - The easiest interpretation of this is to include all time that 2 people are both in a meeting, regardless of whether it is the
              same meeting or not. However this would look stupid in a meeting planner so I will exclude time where the people are both
              in the same meeting
     */


    public static class Meeting
    {
        final ZonedDateTime start;
        final ZonedDateTime end;
        final long id;

        private Meeting(long id, ZonedDateTime start, ZonedDateTime end)
        {
            this.id = id;
            this.start = start;
            this.end = end;
        }

        public static Meeting create(final long id, String isoStartDateTime, String isoEndDateTime)
        {
            return new Meeting(id, ZonedDateTime.parse(isoStartDateTime, ISO_DATE_TIME), ZonedDateTime.parse(isoEndDateTime, ISO_DATE_TIME));
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Meeting meeting = (Meeting) o;

            if (id != meeting.id) return false;

            return true;
        }

        @Override
        public int hashCode()
        {
            return (int) (id ^ (id >>> 32));
        }
    }

    public static class Person
    {
        final Collection<Meeting> meetings = new ArrayList<>();

        public void inviteToMeeting(Meeting meeting)
        {
            this.meetings.add(meeting);
        }

        public int conflictingMeetingMinutes(Person... otherPeople)
        {

            return 0;
        }
    }
}
