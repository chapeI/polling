package business;

import java.util.ArrayList;
import java.util.HashMap;

public class Ballot {
	private ArrayList<Vote> votes;
    
    public Ballot(){
		this.votes= new ArrayList<>();
    }
    /**
       will add a vote object, if the ballot box is full it will double in size
    */
    public boolean submit(Vote vote){
	if (!didVote(vote.getParticipant())){
	    votes.add(vote);
	    return true;
	}
	return false;
	
	
    }

    public void clearVotes(){
		votes.clear();
    };


    public boolean didVote(String participant){
		for (Vote v : votes) {
			if (v.getParticipant().equalsIgnoreCase(participant))
				return true;
		}
		return false;
    };

    public HashMap<String, Integer> getResults(){
        HashMap<String, Integer> results = new HashMap<>();
        for (Vote vote : votes){
            String choice = vote.getChoice();
            if (!results.containsKey(choice)){
                results.put(choice,1);
            }
            else {
                int currentCount = results.get(choice);
                results.put(choice, ++currentCount);
            }
        }
        return results;
    }
}
