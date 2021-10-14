package business;
import Exceptions.WrongStateException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//public enum Status{
//    created,
//    running,
//    released,
//}

public class PollManager {
    private Poll poll;
    private String pollCreator;
    private Ballot ballot = null;
    private Status pollStatus;
    private LocalDateTime releasedTime;

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

    public void createPoll(String name, String question, List<Choice> choices) throws WrongStateException {
        if (pollStatus == null){
            poll = new Poll(name, question, choices);
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
    public void UpdatePoll(String name, String question, List<Choice> choices) throws  WrongStateException{
        if (pollStatus == Status.created || pollStatus == Status.running){
            ballot.clearVotes();
            poll.setName(name);
            poll.setQuestion(question);
            poll.setChoices(choices);
            pollStatus = Status.created;
        }
        else
            throw new WrongStateException("Poll can't be updated because it's not in created or running state");
    };

    /**
     * Clear results when pollStatus is running or released, otherwise throw WrongStateException
     * @throws WrongStateException
     */
    public void ClearPoll() throws  WrongStateException{
        if (pollStatus == Status.running || pollStatus == Status.released){
            ballot.clearVotes();
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
    public void ClosePoll() throws  WrongStateException{
        if (pollStatus == Status.released)
            pollStatus = null;
        else
            throw new WrongStateException("Poll can't be closed because it's not in released state");
    };

    /**
     * run poll when status is created, otherwise throw WrongStateException
     * @throws WrongStateException
     */
    public void RunPoll() throws  WrongStateException{
        if (pollStatus == Status.created)
            pollStatus = Status.running;
        else
            throw new WrongStateException("Poll can't be run because it's not in created state");
    };

    // Khoa
    // ----------------------
    // Alek

    /**
       Sets the status of the current poll to being released only if it is
       in the running state. Otherwise it will send an exception
     */
    public void ReleasePoll() throws  WrongStateException {
	if (this.pollStatus == Status.running){
        this.pollStatus = Status.released;
        releasedTime = LocalDateTime.now();
    }
	else
	    throw new WrongStateException("The state is not in running state.");
    };
    /**
       Will set a poll currently in the released state into the created state.
       if not in the released state it will send an exception
     */
    public void UnreleasePoll() throws  WrongStateException {
	if (this.pollStatus == Status.released)
	    this.pollStatus = Status.created;
	else
	    throw new WrongStateException("The state is not in the released state.");
    };
    /**
       will submit a vote object into the ballot
     */
    public void Vote(String participant, String choice){
	if (this.ballot == null){
	    this.ballot = new Ballot();
	}else{
	    Vote vote = new Vote(participant, choice);
	    this.ballot.submit(vote);
	}
    };
    /**
       Will request from the ballet the poll results
     */
    public HashMap<String, Integer> getPollResults() throws WrongStateException{
	if (pollStatus == Status.released){
	     return this.ballot.getResults();
	}else{
	    throw new WrongStateException("Poll must be released first.");
	}
    };
    /**
       Will write the poll results to a file, then download it through the browser
     */
    public void DownloadPollDetails(PrintWriter printWriter, String filename) throws WrongStateException{
        String fileName = poll.getName() + "-" + releasedTime.toString();
        HashMap<String, Integer> results = getPollResults();

        if (results != null){
            try {
                printWriter = new PrintWriter(fileName);
                for (Map.Entry<String, Integer> entry : results.entrySet()){
                    printWriter.println(entry.getKey() + ": " + entry.getValue());
                }
                printWriter.flush();
                printWriter.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    };

    public Poll getPoll() {
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

    public Ballot getBallot() {
        return ballot;
    }

    public void setBallot(Ballot ballot) {
        this.ballot = ballot;
    }

    public Status getPollStatus() {
        return pollStatus;
    }

    public void setPollStatus(Status pollStatus) {
        this.pollStatus = pollStatus;
    }

    public LocalDateTime getReleasedTime() {
        return releasedTime;
    }

    public void setReleasedTime(LocalDateTime releasedTime) {
        this.releasedTime = releasedTime;
    }
}
