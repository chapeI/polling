package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class DataConn{
    Connection connection = null;
    private static final String POLLS_TABLE = "Polls";
    private static final String POLL_OPTIONS_TABLE = "PollOptions";
    private static final String USER_VOTES_TABLE = "UserVotes";


    ////////////// Connectivity ////////////////
    public DataConn(){
	connection = Conn.getConnection();
    }
    
    public void closeConnection() throws SQLException {
	connection.close();
    }


    ////////////// Polls Table Operations ////////////////

    /**
     * retrieve a unique poll by the pollID and puts the poll info in a HashMap of key String and value HashMap<String, String>
     * the key is the pollID
     * the value is a HashMap that contains (PollName, Question, ManagerPinID, PollStatus, ReleaseTime)
     * @param pollID
     * @return a HashMap of key String and value HashMap<String, String>
     * @throws SQLException
     */
    public HashMap<String, HashMap<String, String>> getPollByID(String pollID) throws SQLException {

	String query = "SELECT * FROM "+ POLLS_TABLE +" WHERE PollID=?";
	PreparedStatement ps = connection.prepareStatement(query);
	ps.setString(1, pollID);
	ResultSet resultSet = ps.executeQuery();
	HashMap<String, HashMap<String, String>> result = resultSetToHashMap(resultSet);
	// Close all the connections
	ps.close();
	return result;
    }

    /**
     * return poll status given the poll ID
     * @param pollID
     * @return
     * @throws SQLException
     */
    public String getPollStatusByID(String pollID) throws SQLException {

	String query = "SELECT PollStatus FROM "+ POLLS_TABLE +" WHERE PollID=?";
	PreparedStatement ps = connection.prepareStatement(query);
	ps.setString(1, pollID);
	ResultSet resultSet = ps.executeQuery();
	HashMap<String, HashMap<String, String>> result = resultSetToHashMap(resultSet);

	String status = result.get(pollID).get("PollStatus");
	// Close all the connections
	ps.close();
	return status;
    }

    /**
     * retrieve a list of polls created by the managerID and puts the polls in a HashMap of key String and value HashMap<String, String>
     * the key is the pollID
     * the value is a HashMap that contains (PollName, Question, ManagerPinID, PollStatus, ReleaseTime)
     * @param managerID
     * @return a HashMap of key String and value HashMap<String, String>
     * @throws SQLException
     */
    public HashMap<String, HashMap<String, String>> getListOfPollsByManagerID(String managerID) throws SQLException {

	String query = "SELECT * FROM "+ POLLS_TABLE +" WHERE ManagerPinID=?";
	PreparedStatement ps = connection.prepareStatement(query);
	ps.setString(1, managerID);
	ResultSet resultSet = ps.executeQuery();
	HashMap<String, HashMap<String, String>> result = resultSetToHashMap(resultSet);
	// Close all the connections
	ps.close();
	return result;
    }

    /**
     * insert into Poll Table a new row
     * @param pollID
     * @param pollName
     * @param question
     * @param managerID
     * @param status
     * @param Options
     * @throws SQLException
     */
    public void insertPoll(String pollID, String pollName, String question, String managerID, String status, String createTime,HashMap<String, String> Options) throws SQLException {
	String inPoll = "INSERT INTO " + POLLS_TABLE + " (PollID, PollName, Question, managerPinID, PollStatus, CreateTime) VALUES(?, ?, ?, ?, ?, ?)";

	PreparedStatement stP = connection.prepareStatement(inPoll);

	stP.setString(1, pollID);
	// Same for second parameter
	stP.setString(2, pollName);
	// Same for rest of the parameters
	stP.setString(3, question);
	stP.setString(4, managerID);
	stP.setString(5, status);
	stP.setString(6, createTime);
	stP.executeUpdate();

	// add choices
	insertPollOptions(pollID, Options);

	// Close all the connections
	stP.close();

    }

    /**
     * delete a row from the Poll Table
     * @param pollID
     * @throws SQLException
     */
    public void deletePoll (String pollID) throws SQLException {
	String inOps = "DELETE FROM " + POLLS_TABLE + " WHERE PollID=?";
	PreparedStatement stO = connection.prepareStatement(inOps);
	stO.setString(1,pollID);

	stO.executeUpdate();
    }

    /**
     * update the poll status in Poll Table
     * @param pollID
     * @param status
     * @throws SQLException
     */
    public void updatePollStatus(String pollID, String status) throws SQLException {

	String query = "UPDATE " + POLLS_TABLE + " SET PollStatus=? WHERE PollID=?";
	PreparedStatement ps = connection.prepareStatement(query);
	ps.setString(2, pollID);
	ps.setString(1, status.toUpperCase(Locale.ROOT));
	ps.executeUpdate();
	// Close all the connections
	ps.close();
    }

    /**
     * update poll name in Poll Table
     * @param pollID
     * @param name
     * @throws SQLException
     */
    public void updatePollName(String pollID, String name) throws SQLException {

	String query = "UPDATE " + POLLS_TABLE + " SET PollName=? WHERE PollID=?";
	PreparedStatement ps = connection.prepareStatement(query);
	ps.setString(2, pollID);
	ps.setString(1, name);
	ps.executeUpdate();
	// Close all the connections
	ps.close();
    }

    /**
     * update poll question in Poll table
     * @param pollID
     * @param question
     * @throws SQLException
     */
    public void updatePollQuestion(String pollID, String question) throws SQLException {

	String query = "UPDATE " + POLLS_TABLE + " SET Question=? WHERE PollID=?";
	PreparedStatement ps = connection.prepareStatement(query);
	ps.setString(2, pollID);
	ps.setString(1, question);
	ps.executeUpdate();
	// Close all the connections
	ps.close();
    }

    public void updatePollReleaseTime(String pollID, String releaseTime) throws SQLException {

	String query = "UPDATE " + POLLS_TABLE + " SET ReleaseTime=? WHERE PollID=?";
	PreparedStatement ps = connection.prepareStatement(query);
	ps.setString(2, pollID);
	ps.setString(1, releaseTime);
	ps.executeUpdate();
	// Close all the connections
	ps.close();
    }


    ////////////// PollOptions Table Operations ////////////////

    public ArrayList<String> getOptions (String pollId) throws SQLException {
	String inOps = "SELECT PollOption FROM " + POLL_OPTIONS_TABLE + " WHERE PollID=?";
	PreparedStatement stO = connection.prepareStatement(inOps);
	stO.setString(1, pollId);
	ResultSet resultSet = stO.executeQuery();

	ArrayList<String> options = new ArrayList<>();
	while (resultSet.next()) {
	    options.add(resultSet.getString(1));
	}
	return options;
    }
    /**
     * insert new options into PollOptions Table
     * @param pollID
     * @param choices
     * @throws SQLException
     */
    public void insertPollOptions (String pollID, HashMap<String, String> choices) throws SQLException {
	String inOps = "INSERT INTO " + POLL_OPTIONS_TABLE + " (PollID, PollOption, Description) VALUES (?, ?, ?)";
	PreparedStatement stO = connection.prepareStatement(inOps);
	for (Map.Entry<String, String> entry : choices.entrySet()) {
	    String key = entry.getKey();
	    String value = entry.getValue();
	    stO.setString(1, pollID);
	    stO.setString(2, key);
	    stO.setString(3,value);
	    stO.executeUpdate();
	}
	stO.close();
    }

    /**
     * update options in PollOptions Table given the pollId
     * @param pollID
     * @param choices
     * @throws SQLException
     */
    public void updatePollOptions (String pollID, HashMap<String, String> choices) throws SQLException {
	deleteOptions(pollID);

	String inOps = "INSERT INTO " + POLL_OPTIONS_TABLE + " (PollID, PollOption, Description) VALUES (?, ?, ?)";
	PreparedStatement stO = connection.prepareStatement(inOps);
	for (Map.Entry<String, String> entry : choices.entrySet()) {
	    String key = entry.getKey();
	    String value = entry.getValue();
	    stO.setString(1, pollID);
	    stO.setString(2, key);
	    stO.setString(3,value);
	    stO.executeUpdate();
	}
	stO.close();
    }

    /**
     * delete options from PollOptions Table given the pollId
     * @param pollID
     * @throws SQLException
     */
    public void deleteOptions (String pollID) throws SQLException {
	String inOps = "DELETE FROM " + POLL_OPTIONS_TABLE + " WHERE PollID=?";
	PreparedStatement stO = connection.prepareStatement(inOps);
	stO.setString(1,pollID);

	stO.executeUpdate();
	stO.close();
    }


    ////////////// UserVotes Table Operations ////////////////

    public void insertVote(String pollId, String option, String PIN) throws SQLException {
	String inOps = "INSERT INTO " + USER_VOTES_TABLE + " VALUE (?,?,?)";
	PreparedStatement stO = connection.prepareStatement(inOps);
	stO.setString(1, pollId);
	stO.setString(2, PIN);
	stO.setString(3, option);

	stO.executeUpdate();
	stO.close();
    }

    public int updateVote(String pollId, String option, String PIN) throws SQLException {
	String inOps = "UPDATE " + USER_VOTES_TABLE + " SET PollOption=? WHERE PollID=? AND PinID =?";
	PreparedStatement stO = connection.prepareStatement(inOps);
	stO.setString(2, pollId);
	stO.setString(3, PIN);
	stO.setString(1, option);

	int result = stO.executeUpdate();
	stO.close();

	return result;
    }

    /**
     * delete all votes in UserVotes Table given the pollID
     * @param pollID
     * @throws SQLException
     */
    public void deleteVotes (String pollID) throws SQLException {
	String inOps = "DELETE FROM " + USER_VOTES_TABLE + " WHERE PollID=?";
	PreparedStatement stO = connection.prepareStatement(inOps);
	stO.setString(1,pollID);

	stO.executeUpdate();
	stO.close();
    }

    public HashMap<String, Integer> getResults (String pollId) throws SQLException {
	ArrayList<String> options = getOptions(pollId);

	HashMap<String, Integer> result = new HashMap<>();
	for (String option : options) {
	    String inOps = "SELECT COUNT(U.PinID)" +
		"FROM "+ POLL_OPTIONS_TABLE+" O " +
		"JOIN "+ USER_VOTES_TABLE+" U " +
		"ON O.PollID=U.PollID AND O.PollOption=U.PollOption " +
		"WHERE O.PollID=? AND O.PollOption=?";
	    PreparedStatement stO = connection.prepareStatement(inOps);
	    stO.setString(1, pollId);
	    stO.setString(2, option);

	    ResultSet resultSet = stO.executeQuery();
	    resultSet.next();
	    result.put(option, resultSet.getInt(1));
	}

	return result;
    }

    ////////////// Helper methods ////////////////
    /**
     * convert a ResultSet into a HashMap of polls
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private HashMap<String, HashMap<String, String>> resultSetToHashMap(ResultSet resultSet) throws SQLException {
	// return a map of (String - HashMap)
	// each key String is a poll ID
	// each value is a HashMap that contains (PollName, Question, ManagerPinID, PollStatus, ReleaseTime)

	HashMap<String, HashMap<String, String>> result = new HashMap<>();

	while (resultSet.next()) {
	    int i = 2;
	    HashMap<String, String> pollMap = new HashMap<>();
	    pollMap.put("PollName","");
	    pollMap.put("Question","");
	    pollMap.put("ManagerPinID","");
	    pollMap.put("PollStatus","");
	    pollMap.put("ReleaseTime","");

	    for (Map.Entry<String, String> e : pollMap.entrySet()) {
		String temp = resultSet.getString(i);
		e.setValue(temp);
		i++;
	    }

	    result.put(resultSet.getString(1), pollMap);
	}

	return result;
    }
}
