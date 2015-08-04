package com.insano10.puzzlers.puzzles;

import java.time.ZonedDateTime;
import java.util.*;

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

        public Set<ZonedInterval> conflictingTimeIntervals(Person... otherPeople)
        {
            Set<ZonedInterval> conflictingTimeIntervals = new HashSet<>();

            //get all the meetings sorted by start time
            List<Meeting> allMeetings = getAllMeetings(otherPeople);
            Collections.sort(allMeetings, (m1, m2) -> m1.start.compareTo(m2.start));

            //get the time other meetings overlap with this meeting
            for (Meeting meeting : allMeetings)
            {
                for (Meeting otherMeeting : allMeetings)
                {
                    if (!otherMeeting.equals(meeting))
                    {
                        if(otherMeeting.start.isBefore(meeting.end))
                        {
                            //this meeting must overlap
                            ZonedDateTime overlapStart = otherMeeting.start;
                            ZonedDateTime overlapEnd = meeting.end.isAfter(otherMeeting.end) ? otherMeeting.end : meeting.end;

                            conflictingTimeIntervals.add(new ZonedInterval(overlapStart, overlapEnd));
                        }

                    }
                }
            }

            return conflictingTimeIntervals;
        }

        private List<Meeting> getAllMeetings(Person[] otherPeople)
        {
            Set<Meeting> allMeetings = new HashSet<>();
            allMeetings.addAll(meetings);

            for (Person person : otherPeople)
            {
                allMeetings.addAll(person.meetings);
            }
            return new ArrayList<>(allMeetings);
        }
    }

    public static class ZonedInterval
    {
        private final ZonedDateTime start;
        private final ZonedDateTime end;

        public ZonedInterval(ZonedDateTime start, ZonedDateTime end)
        {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ZonedInterval that = (ZonedInterval) o;

            if (!start.equals(that.start)) return false;
            return end.equals(that.end);

        }

        @Override
        public int hashCode()
        {
            int result = start.hashCode();
            result = 31 * result + end.hashCode();
            return result;
        }

        @Override
        public String toString()
        {
            return "ZonedInterval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
