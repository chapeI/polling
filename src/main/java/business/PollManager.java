package business;
import Exceptions.WrongStateException;
import Exceptions.RepeatVoterException;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PollManager {
    private static Poll poll;
    private static String pollCreator;
    private static Ballot ballot = null;
    private static Status pollStatus;
    private static LocalDateTime releasedTime;

    public PollManager(){
	this.poll = null;
	this.pollCreator = "";
	this.ballot = null;
    
    }

    /**
     * Create a new poll when pollStatus is null, otherwise throw WrongStateException
     * @param name
     * @param question
     * @param choices
     * @throws WrongStateException
     */

    public static void createPoll(String name, String question, List<String> choices, List<String> descriptions) throws WrongStateException {
        if (pollStatus == null){
            poll = new Poll(name, question, choices, descriptions);
            pollStatus = Status.created;
        }
        else
            throw new WrongStateException("There is already a poll in place");
    };

    /**
     * Update poll when pollStatus is either created or running, otherwise throw WrongStateException
     * @param name
     * @param question
     * @param choices
     * @throws WrongStateException
     */
    public static void updatePoll(String name, String question, List<String> choices, List<String> descriptions, boolean replaceChoice) throws  WrongStateException{
        if (pollStatus == Status.created || pollStatus == Status.running){
            clearResults();
            if (!name.isBlank())
                poll.setName(name);
            if (!question.isBlank())
                poll.setQuestion(question);
            if (choices != null) {
                if (replaceChoice){
                    poll.setChoices(choices, descriptions);
                }
                else {
                    poll.addChoices(choices, descriptions);
                }
            }

            pollStatus = Status.created;
        }
        else
            throw new WrongStateException("Poll can't be updated because it's not in created or running state");
    };

    /**
     * Clear results when pollStatus is running or released, otherwise throw WrongStateException
     * @throws WrongStateException
     */
    public static void clearPoll() throws  WrongStateException{
        if (pollStatus == Status.running || pollStatus == Status.released){
            clearResults();
            if (pollStatus == Status.released)
                pollStatus = Status.created;
        }
        else
            throw new WrongStateException("Poll can't be cleared because it's not in running or released state");
    };

    /**
     * close Poll when pollStatus is running, otherwise throw WrongStateException
     * @throws WrongStateException
     */
    public static void closePoll() throws  WrongStateException{
        if (pollStatus == Status.released) {
            pollStatus = null;
            clearResults();
            poll = null;
        }
        else
            throw new WrongStateException("Poll can't be closed because it's not in released state");
    };

    /**
     * run poll when status is created, otherwise throw WrongStateException
     * @throws WrongStateException
     */
    public static void runPoll() throws  WrongStateException{
        if (pollStatus == Status.created)
            pollStatus = Status.running;
        else
            throw new WrongStateException("Poll can't be run because it's not in created state");
    };

    public static void clearResults() throws WrongStateException{
        if (ballot != null)
            ballot.clearVotes();
    }

    public static HashMap<String, Integer> returnResults() throws WrongStateException{
        if (ballot == null)
            return null;
        else {
            HashMap<String, Integer> voteCounts=  ballot.getResults();
            HashMap<String, Integer> results = new HashMap<>();
            List<Choice> choicesList = poll.getChoices();
            for (int i = 0; i< choicesList.size(); i++){
                String choiceText = choicesList.get(i).getText();
                int count = 0;
                if (voteCounts.containsKey(Integer.toString(i))){
                    count = voteCounts.get(Integer.toString(i));
                }
                results.put(choiceText, count);
            }
            return results;
        }
    }
    // Khoa
    // ----------------------
    // Alek

    /**
       Sets the status of the current poll to being released only if it is
       in the running state. Otherwise it will send an exception
     */
    public static void releasePoll() throws  WrongStateException {
	if (pollStatus == Status.running){
        pollStatus = Status.released;
        releasedTime = LocalDateTime.now();
    }
	else
	    throw new WrongStateException("The state is not in running state.");
    };
    /**
       Will set a poll currently in the released state into the created state.
       if not in the released state it will send an exception
     */
    public static void unreleasePoll() throws  WrongStateException {
	if (pollStatus == Status.released)
	    pollStatus = Status.created;
	else
	    throw new WrongStateException("The state is not in the released state.");
    };
    /**
       will submit a vote object into the ballot
     */
    public static void vote(String participant, String choice){
	if (ballot == null)
	    ballot = new Ballot();
	Vote vote = new Vote(participant, choice);
	if (ballot.submit(vote)){
	    System.out.println("Unique user vote submitted.");
	}else{
	    System.out.println("This user has voted already.");
	}
    };
    /**
       Will request from the ballet the poll results
     */
    public static HashMap<String, Integer> getPollResults() throws WrongStateException{
	if (pollStatus == Status.released){
	     return ballot.getResults();
	}else{
	    throw new WrongStateException("Poll must be released first.");
	}
    };
    /**
       Will write the poll results to a file, then download it through the browser
     */
    public static void downloadPollDetails() throws WrongStateException{
	System.out.println("Doin a download!!");
	
        String fileName = poll.getName() + "-" + releasedTime.toString()+".txt";
        HashMap<String, Integer> results = getPollResults();
        System.out.println(results);
	System.out.println(fileName);
    };

    public static Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public String getPollCreator() {
        return pollCreator;
    }

    public void setPollCreator(String pollCreator) {
        this.pollCreator = pollCreator;
    }

    public static Ballot getBallot() {
        return ballot;
    }

    public void setBallot(Ballot ballot) {
        this.ballot = ballot;
    }

    public static Status getPollStatus() {
        return pollStatus;
    }

    public static void setPollStatus(Status inputPollStatus) {
        pollStatus = inputPollStatus;
    }

    public static  LocalDateTime getReleasedTime() {
        return releasedTime;
    }

    public void setReleasedTime(LocalDateTime releasedTime) {
        this.releasedTime = releasedTime;
    }
}
