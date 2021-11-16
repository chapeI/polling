package presentation;

import business.Poll;
import business.PollManager;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.*;

import business.Choice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DownloadResultsServlet extends HttpServlet {


    private final int MAX_DOWNLOAD_SIZE = 2096;
    
    /**
     * Will first 
     *
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	System.out.println("Downloading ... ");

	//Initializes needed variables
	PollManager PM = new PollManager();
	String pollID = request.getParameter("pollID");
	HashMap<String, HashMap<String, String>> pollInfo = PM.getPoll(pollID);
	HashMap<String, Integer> results = PM.pollResults(pollID);
	String fileName = pollInfo.get(pollID).get("PollName")
	    + "-" + PM.getReleasedTime(pollID)+".txt";
	String fileContents = "";
	ArrayList<HashMap<String, String>> choices = PM.getChoices(pollID);
	int t = 0;

	//Sets up header of the file to be downloaded
	fileContents += "Poll: "+pollInfo.get(pollID).get("PollName")+"\n";
	fileContents += "--------------------------------------\n\n";
	fileContents += "Question: "+pollInfo.get(pollID).get("Question")+"\n";

	/*
	 * For each question in the poll, it will add it to the file string to be
	 * outputted
	 */
	// Need to fix after
	/*
	for (int i = 0; i < choices.size(); i++){
	    int voteCount = 0;
	    if ( results.get(String.valueOf(i)) == null ) {
		t += 0;
		voteCount = 0;
	    } else {
		t += results.get(String.valueOf(i));
		voteCount = results.get(String.valueOf(i));
	    }
	    fileContents += "Option "+i+": "+choices.get(i).getText()+"\n";
	    fileContents += "Description: "+choices.get(i).getDescription()+ "\n";
	    fileContents += "Votes: "+ voteCount +"\n\n";
	}
	*/
	fileContents += "Total Votes: " + t;
	
	System.out.println(fileName);

	// adds to the response type the file attatchment with its content
	response.setContentType("text/plain");
	response.setHeader("Content-disposition", "attachment; filename=\""+fileName+"\"");

	/*
	 * Will set up an input and output stream, then take the String of prepared text
	 * and convert it to a byte stream and feed it throug hthe buffer. the output stream
	 * will then be fed through the response object to then be downloaded
	 */
	try {
	    InputStream in = new ByteArrayInputStream(fileContents.getBytes());
	    OutputStream out = response.getOutputStream();

	    byte[] buffer = new byte[MAX_DOWNLOAD_SIZE];

	    int bytesRead;

	    while ((bytesRead = in.read(buffer)) > 0){
		out.write(buffer, 0, bytesRead);
	    }
	    // flushes the streams
	    try{

		out.flush();
		in.close();
		out.close();
	    }catch(IOException e){
	    
	    }
	}
	catch(IOException e){
	    
	}
    }
}
