package com.insano10.puzzlers.puzzles.overlappingmeetings;

import java.time.ZonedDateTime;
import java.util.*;

public class Conflictinator
{
    public static List<ZonedInterval> getConflictingMeetingTimeIntervals(Person subject, Person... otherPeople)
    {
        List<ZonedInterval> conflictingTimeIntervals = new ArrayList<>();

        //get all the meetings sorted by start time
        List<Meeting> allMeetings = getAllMeetings(subject, otherPeople);
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
                    ZonedInterval overlapInterval = new ZonedInterval(overlapStart.withZoneSameInstant(subject.getTimeZone()), overlapEnd.withZoneSameInstant(subject.getTimeZone()));

                    if(!conflictingTimeIntervals.contains(overlapInterval))
                    {
                        conflictingTimeIntervals.add(overlapInterval);
                    }
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
    private static List<Meeting> getAllMeetings(Person subject, Person[] otherPeople)
    {
        Set<Meeting> allMeetings = new HashSet<>();
        allMeetings.addAll(subject.getMeetings());

        for (Person person : otherPeople)
        {
            allMeetings.addAll(person.getMeetings());
        }
        return new ArrayList<>(allMeetings);
    }
}
