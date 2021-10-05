package presentation;

import business.Poll;
import business.PollService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

//@WebServlet(name = "StateManagerServlet", value = "/StateManagerServlet")
public class StateManagerServlet extends HttpServlet {

//    String status;
    Poll poll;

    public void init() {
        this.poll = PollService.instance().get_poll();
//        this.status = this.poll.get_status();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String status_change = request.getParameter("status_change");
        System.out.println("status_change: " + status_change);
        if(status_change.equals("RUNNING") || status_change.equals("UNRELEASE")) {
            this.poll.set_status_to_running();
        }
        if(status_change.equals("RELEASE")) {
            this.poll.set_status_to_released();
        }
        if(status_change.equals(""))
        System.out.println("status manager. status " + this.poll.get_status());
        response.sendRedirect("pollManager");

    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
}
