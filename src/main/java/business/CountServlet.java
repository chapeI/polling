package business;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CountServlet", value = "/CountServlet")
public class CountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Poll p = PollService.instance().get_poll();
        p.get_red_count();

        PrintWriter out = response.getWriter();
        String color = request.getParameter("color");
        out.println("you voted " + color);

        int n = Integer.parseInt(color);
        p.increment(n);
        p.get_red_count();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
