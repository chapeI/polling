package presentation;

import business.Poll;
import business.PollService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet()
public class LandingServlet extends HttpServlet {
    String status;
    Poll poll;

    public void init() {
        this.poll = PollService.instance().get_poll();
        this.status = this.poll.get_status();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (this.status == "RUNNING") {
            System.out.println("am i alive");
            request.setAttribute("status", this.status);
            request.getRequestDispatcher("poll.jsp").forward(request, response);
        } else {
            System.out.println("route to create poll");
            request.getRequestDispatcher("create_poll.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
