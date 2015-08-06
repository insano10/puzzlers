package com.insano10.puzzlers.puzzles.overlappingmeetings;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;

public class Person
{
    private final ZoneId timeZone;
    private final Collection<Meeting> meetings = new ArrayList<>();

    public Person(ZoneId timeZone)
    {
        this.timeZone = timeZone;
    }

    public void inviteToMeeting(Meeting meeting)
    {
        this.meetings.add(meeting);
    }

    public ZoneId getTimeZone()
    {
        return timeZone;
    }

    public Collection<Meeting> getMeetings()
    {
        return meetings;
    }
}
