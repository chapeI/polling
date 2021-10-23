package presentation;

import Exceptions.WrongStateException;
import business.Poll;
import business.PollManager;
import business.Status;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;

public class StateManagerServlet extends HttpServlet {

    Poll poll;

    public void init() {
        this.poll = PollManager.getPoll();
    }

	/**
	 * performs poll operations based on the user's choice
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String choice = request.getParameter("choice");
        String status_change = request.getParameter("status_change");
        System.out.println("status_change: " + status_change);
        if (choice != null) {
			// do voting here
	    String participant = request.getParameter("participant");
	    PollManager.vote(participant, choice);
		request.getRequestDispatcher("voted.jsp").forward(request, response);
	}
	if (status_change != null){
	    try {
		if(status_change.equals("RUNNING")) {
		    PollManager.runPoll();
		}
		else if (status_change.equals("UNRELEASE")){
		    PollManager.unreleasePoll();
		}
		else if(status_change.equals("RELEASE")) {
		    PollManager.releasePoll();
		}
		else if(status_change.equals("RELEASED_CLEAR")) {
		    PollManager.clearPoll();
		}
		else if(status_change.equals("CREATED_UPDATE")) {
		    PollManager.clearResults();
		    PollManager.setPollStatus(Status.created);
		}
		else if(status_change.equals("RUNNING_CLEAR")) {
		    PollManager.clearPoll();
		}
		else if(status_change.equals("RUNNING_UPDATE")) {
		    PollManager.clearResults();
		    PollManager.setPollStatus(Status.created);
		}
		else if(status_change.equals("VIEW")) {
                    HashMap<String, Integer> results = PollManager.returnResults();
                    request.setAttribute("results", results);
                    request.setAttribute("status_change", "VIEW");
                    request.getRequestDispatcher("poll_released.jsp").forward(request, response);
                }
		else if(status_change.equals("VIEW_PARTICIPANT")) {
			HashMap<String, Integer> results = PollManager.returnResults();
			request.setAttribute("results", results);
			request.setAttribute("status_change", "VIEW");
			request.getRequestDispatcher("poll_result_participant.jsp").forward(request, response);
		}
		else if(status_change.equals("CLOSE")) {
		    PollManager.closePoll();
		    request.setAttribute("status_change", status_change);
		    request.getRequestDispatcher("/").forward(request, response);
		}
		else if(status_change.equals("DOWNLOAD")) {
		    PollManager.downloadPollDetails();
		    request.setAttribute("poll",this.poll);
		    request.getRequestDispatcher("/download_results").forward(request, response);
		}else if(status_change.equals("HOME")) {
		    request.getRequestDispatcher("/").forward(request, response);
		}
	    } catch (WrongStateException e) {
		System.out.println(e.getMessage());
	      
	    } 
	    
	    request.setAttribute("status_change", status_change);
	}
	request.getRequestDispatcher("pollManager").forward(request, response);
    }

}
