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

    private Poll poll = new Poll();

    public Poll get_poll(){
        return poll;
    }
}
