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

@WebServlet("/download_results")
public class DownloadResultsServlet extends HttpServlet {

    private final int MAX_DOWNLOAD_SIZE = 2096;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

	String fileName = "Apples.txt";
	String fileContents = "";
	fileContents += "Apples are the best, you would never have guessed";
	fileContents += "Apples are the best, you would never have guessed";
	fileContents += "Apples are the best, you would never have guessed";
	fileContents += "Apples are the best, you would never have guessed";
	fileContents += "Apples are the best, you would never have guessed";
	fileContents += "Apples are the best, you would never have guessed";
	    
	response.setContentType("text/plain");
	response.setHeader("Content-disposition", "attachment; "+fileName);

	try {
	    InputStream in = new ByteArrayInputStream(fileContents.getBytes());
	    OutputStream out = response.getOutputStream();

	    byte[] buffer = new byte[MAX_DOWNLOAD_SIZE];

	    int bytesRead;

	    while ((bytesRead = in.read(buffer)) > 0){
		out.write(buffer, 0, bytesRead);
	    }

	    try{

		//in.flush();
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
