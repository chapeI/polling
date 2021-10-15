package presentation;

import Exceptions.WrongStateException;
import business.Poll;
import business.PollManager;
import business.PollService;
import business.Status;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@WebServlet(name = "StateManagerServlet", value = "/StateManagerServlet")
public class StateManagerServlet extends HttpServlet {

//    String status;
    Poll poll;

    public void init() {
        this.poll = PollManager.getPoll();
//        this.status = this.poll.get_status();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String choice = request.getParameter("choice");
        String status_change = request.getParameter("status_change");
        System.out.println("status_change: " + status_change);
        if (choice != null) {
            // do voting here
            PollManager.vote(null, choice);
//            try {
//                HashMap<String, Integer> results = PollManager.returnResults();
//                for (Map.Entry<String, Integer> entry : results.entrySet()){
//                    System.out.println(entry.getKey());
//                    System.out.println(entry.getValue());
//                }
//            } catch (WrongStateException e) {
//                e.printStackTrace();
//            }
            request.getRequestDispatcher("voted.jsp").forward(request, response);

        try {
	    if (choice != null){
		System.out.println("Starting Vote Submission");
		String participant = request.getParameter("participant");
		System.out.println(participant);
		System.out.println(choice);		    
                PollManager.vote(participant, choice);
		request.getRequestDispatcher("/").forward(request, response);

	    }
            else if(status_change.equals("RUNNING")) {
                PollManager.runPoll();
            }
            else if (status_change.equals("UNRELEASE")){
                PollManager.unreleasePoll();
            }
            else if(status_change.equals("RELEASE")) {
                PollManager.releasePoll();
            }
            else if(status_change.equals("RELEASED_CLEAR")) {
                // RELEASED->CREATED. I dont think we need to clear results
                System.out.println("clear() from 'RELEASED'");
                PollManager.clearPoll();
            }
            else if(status_change.equals("CREATED_UPDATE")) {
                // CREATED->CREATED. by "update", we're actually "clearing" the results TODO: clear the results..
                PollManager.clearResults();

                System.out.println("Update() from 'CREATED'");
                System.out.println("poll results need to be cleared");
                PollManager.setPollStatus(Status.created);
            }
            else if(status_change.equals("RUNNING_CLEAR")) {
                // RUNNING->RUNNING clear the results, status stays the same   TODO: clear the results..
                System.out.println("clear() from 'RUNNING'");
                System.out.println("poll results need to be cleared");
                PollManager.clearPoll();
            }
            else if(status_change.equals("RUNNING_UPDATE")) {
                // RUNNING->CREATED clear the results   TODO: clear the results..
                System.out.println("update() from 'RUNNING'");
                System.out.println("poll results need to be cleared");
                PollManager.clearResults();
                PollManager.setPollStatus(Status.created);
            }
            else if(status_change.equals("CLOSE")) {
                PollManager.closePoll();
                request.setAttribute("status_change", status_change);
                request.getRequestDispatcher("/").forward(request, response);
                // redirect to startingPage
            }
	    else if(status_change.equals("DOWNLOAD")) {
		System.out.println("Starting Download");
                PollManager.downloadPollDetails();
		request.getRequestDispatcher("/").forward(request, response);
		System.out.println("Ending Download");
                // redirect to startingPage
            }else if(status_change.equals("HOME")) {
		request.getRequestDispatcher("/").forward(request, response);
	        // redirect to startingPage
            }
        } catch (WrongStateException e) {
            System.out.println(e.getMessage());
	      
	}

            request.setAttribute("status_change", status_change);
        }
        request.getRequestDispatcher("pollManager").forward(request, response);
    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
}
