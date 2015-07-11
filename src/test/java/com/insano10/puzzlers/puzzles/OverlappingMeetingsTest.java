package com.insano10.puzzlers.puzzles;

import com.insano10.puzzlers.puzzles.OverlappingMeetings.Meeting;
import com.insano10.puzzlers.puzzles.OverlappingMeetings.Person;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;

public class OverlappingMeetingsTest
{

    @Test
    @Ignore("not finished")
    public void shouldFindOverlappingMeetingMinutesBetweenTwoPeople() throws Exception
    {
        //given

        Person alice = new Person();
        Person bob = new Person();

        Meeting meeting1 = Meeting.create(0L, "2015-07-10T08:00:00+01:00", "2015-07-10T09:00:00+01:00");
        Meeting meeting2 = Meeting.create(1L, "2015-07-10T12:00:00+01:00", "2015-07-10T12:30:00+01:00");

        //when

        alice.inviteToMeeting(meeting1);
        alice.inviteToMeeting(meeting2);
        bob.inviteToMeeting(meeting2);

        //then

        Assertions.assertThat(alice.conflictingMeetingMinutes(bob)).isEqualTo(30);

    }
}