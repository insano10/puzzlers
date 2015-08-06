package com.insano10.puzzlers.puzzles.overlappingmeetings;

import java.time.Duration;
import java.time.ZonedDateTime;

public class ZonedInterval
{
    private final ZonedDateTime start;
    private final ZonedDateTime end;

    public ZonedInterval(ZonedDateTime start, ZonedDateTime end)
    {
        this.start = start;
        this.end = end;
    }

    public long getDurationMins()
    {
        Duration duration = Duration.between(start, end);
        return duration.toMinutes();
    }

    public boolean containsInterval(ZonedInterval interval)
    {
        return (interval.start.compareTo(this.start) >= 0) && (interval.end.compareTo(this.end) <= 0);
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
