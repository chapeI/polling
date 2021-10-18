package presentation;

import business.Poll;
import business.PollManager;
import business.Status;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet()
public class StartingServlet extends HttpServlet {
    Status status;
    Poll poll;
    String color;

    public void init() {
        this.poll = PollManager.getPoll();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        this.status = PollManager.getPollStatus();

        if (this.status == Status.running ) {
            this.color = "lightgreen";
        } else if (this.status == Status.created ) {
            this.color = "yellow";
        } else if (this.status == Status.released ) {
            this.color = "red";
        } else {
            this.color = "lightgrey";
        }

        request.setAttribute("color", this.color);
        request.setAttribute("status", this.status);
        request.getRequestDispatcher("start.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
