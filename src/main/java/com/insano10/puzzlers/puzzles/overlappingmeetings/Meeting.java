package com.insano10.puzzlers.puzzles.overlappingmeetings;

import java.time.ZonedDateTime;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

public class Meeting
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
