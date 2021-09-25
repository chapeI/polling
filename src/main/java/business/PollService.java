package business;

import java.util.ArrayList;

public class PollService {
    private static PollService pollService;

    public static  PollService instance()
    {
        if(pollService == null)
            pollService  = new PollService();

        return pollService;
    }

    private Poll poll = new Poll("Colors", "Choose Favorite Color");

    public Poll get_poll(){
        if(poll.status == "RUNNING") {
            return poll;
        } else {
            System.out.println("poll is not running, shouldn't show poll");
            return null;
        }
    }




}
