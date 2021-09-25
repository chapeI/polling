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
//        String c = request.getParameter("color");
//        System.out.println("color" + c);

        PrintWriter out = response.getWriter();
        String color = request.getParameter("color");
        out.println("you voted " + color);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
