package servlet;
import java.io.PrintWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    name = "MyServlet4",
    urlPatterns = {"/rating2"}
)

public class Rating2Servlet extends HttpServlet {

    // Location of servlet.
    static String Path = "swe432-servlets.herokuapp.com/rating2";

   /** *****************************************************
     *  Overrides HttpServlet's doPost().
     *  Converts the values in the form, performs the operation
     *  indicated by the submit button, and sends the results
     *  back to the client.
    ********************************************************* */
    public void doPost (HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String r1 = request.getParameter("radio1");
        String r2 = request.getParameter("radio2");
        String r3 = request.getParameter("radio3");
        String r4 = request.getParameter("radio4");

        // response.sendRedirect("https://swe432-react-site.herokuapp.com/results/"+r1+"/"+r2+"/"+r3+"/"+r4);
    }

    /** *****************************************************
     *  Overrides HttpServlet's doGet().
     *  Prints an HTML page with a blank form.
    ********************************************************* */
    public void doGet (HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
    } // End doGet
}