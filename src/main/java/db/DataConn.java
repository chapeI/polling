import java.sql.Connection;
import java.sql.PreparedStatement;



public class DataConn{

    Conn conn = null;

    DataConn(){
	if (this.conn == null)
	    this.conn = new Conn();
    }

    public bool insertPoll(maplist Data, List Options){
	Connection c = this.conn.getConnection();
	
	String inPoll = "INSERT INTO Polls (PollID, PollName, Question, managerPinID, PollStatus) VALUES(?, ?, ?, ?, ?)";
	String inOps = "INSERT INTO PollOptions (PollID, PollOption, Description) (?, ?, ?))";
	
	PreparedStatement stP = c.preparedStatement(inPoll);
	PreparedStatement stO = c.preparedStatement(inOps);

	
	stP.setString(1,Data["PollID"]);
	// Same for second parameter
	stP.setString(2, Data["PollName"]);
	// Same for rest of the parameters
	stP.setString(3, Data["Question"]);
	stP.setString(4, Data["ManagerPinID"]);
	stP.setString(5, Data["PollStatus"]);
	
	for (O:Options){ 
	    stP.setString(1, Data["PollID"]);
	    stP.setString(2, O[0]);
	    stP.setString(3, O[1]);
	}
	// Execute the insert command using executeUpdate()
	// to make changes in database
	stP.executeUpdate();
	stO.executeUpdate();
	
	// Close all the connections
	stP.close();
	st.close();
	c.close();

	
	
    }    

}
