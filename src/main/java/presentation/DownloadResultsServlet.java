package presentation;

import business.Poll;
import business.PollManager;
import business.PollService;
import business.Status;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.*;
import business.Poll;
import business.Choice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DownloadResultsServlet extends HttpServlet {

    private final int MAX_DOWNLOAD_SIZE = 2096;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	System.out.println("Downloading ... ");

	Poll poll = PollManager.getPoll();
	HashMap<String, Integer> results = PollManager.getBallot().getResults();
	String fileName = poll.getName() + "-" + PollManager.getReleasedTime()+".txt";
	String fileContents = "";
	List<Choice> choices = poll.getChoices();
	int t = 0;

	    
	fileContents += "Poll: "+poll.getName()+"\n";
	fileContents += "--------------------------------------\n\n";
	fileContents += "Question: "+poll.getQuestion()+"\n";
	for (int i = 0; i < choices.size(); i++){
	    t += results.get(String.valueOf(i));
	    fileContents += "Option "+i+": "+choices.get(i).getText()+"\n";
	    fileContents += "Description: "+choices.get(i).getDescription()+ "\n";
	    fileContents += "Votes: "+results.get(String.valueOf(i))+"\n\n";
	}
	fileContents += "Total Votes: " + t;
	
	System.out.println(fileName);
	
	response.setContentType("text/plain");
	response.setHeader("Content-disposition", "attachment; filename=\""+fileName+"\"");

	try {
	    InputStream in = new ByteArrayInputStream(fileContents.getBytes());
	    OutputStream out = response.getOutputStream();

	    byte[] buffer = new byte[MAX_DOWNLOAD_SIZE];

	    int bytesRead;

	    while ((bytesRead = in.read(buffer)) > 0){
		out.write(buffer, 0, bytesRead);
	    }

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
