package business;

public class Vote{
    private String participant;
    private String choice;
    
    public Vote(String participant, String choice){
	this.participant = participant;
	this.choice = choice;
    }
    /**       
       returns Srting particpant
     */
    public String getParticipant(){
	return this.participant;
    }
    public String getChoice(){
	return this.choice;
    }
    public void setParticipant(String participant){
	this.participant = participant;
    }
    public void setChoice(){
	this.choice = choice;
    }

}
