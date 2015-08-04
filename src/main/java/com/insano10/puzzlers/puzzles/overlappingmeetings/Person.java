package com.insano10.puzzlers.puzzles.overlappingmeetings;

import java.time.ZonedDateTime;
import java.util.*;

public class Person
{
    final Collection<Meeting> meetings = new ArrayList<>();

    public void inviteToMeeting(Meeting meeting)
    {
        this.meetings.add(meeting);
    }

    //todo: this should not be inside Person
    public List<ZonedInterval> conflictingTimeIntervals(Person... otherPeople)
    {
        List<ZonedInterval> conflictingTimeIntervals = new ArrayList<>();

        //get all the meetings sorted by start time
        List<Meeting> allMeetings = getAllMeetings(otherPeople);
        Collections.sort(allMeetings, (m1, m2) -> m1.start.compareTo(m2.start));

        for (int i = 0; i < allMeetings.size(); i++)
        {
            //get the time other meetings overlap with this meeting
            Meeting meeting = allMeetings.get(i);

            for (int j = i+1; j < allMeetings.size(); j++)
            {
                Meeting otherMeeting = allMeetings.get(j);

                if(otherMeeting.start.isBefore(meeting.end))
                {
                    //this meeting must overlap
                    ZonedDateTime overlapStart = otherMeeting.start;
                    ZonedDateTime overlapEnd = meeting.end.isAfter(otherMeeting.end) ? otherMeeting.end : meeting.end;

                    conflictingTimeIntervals.add(new ZonedInterval(overlapStart, overlapEnd));
                }
            }
        }

        return conflictingTimeIntervals;
    }

    /**
     * Return a list of all meetings attended by at least one person
     * Meetings are deduplicated
     * @param otherPeople
     * @return
     */
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
