package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "PersistRating", urlPatterns = {"/savedRatings"})
public class PersistRatingServlet extends HttpServlet{
  static enum Data {AGE, NAME, NICKNAME};
  static String RESOURCE_FILE = "reviews.txt";
  static final String VALUE_SEPARATOR = ";";

  static String Domain  = "";
  static String Path    = "/";
  static String Servlet = "savedRatings";

  // Button labels
  static String OperationAdd = "Add";

  // Other strings.

  /** *****************************************************
   *  Overrides HttpServlet's doPost().
   *  Converts the values in the form, performs the operation
   *  indicated by the submit button, and sends the results
   *  back to the client.
  ********************************************************* */
  @Override
  public void doPost (HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException {
      String r1 = request.getParameter("radio1");
      String r2 = request.getParameter("radio2");
      String r3 = request.getParameter("radio3");
      String r4 = request.getParameter("radio4");

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();

      out.println("<p>"+ r1 + "</p>");
      out.println("<p>"+ r2 + "</p>");
      out.println("<p>"+ r3 + "</p>");
      out.println("<p>"+ r4 + "</p>");
  }

  /** *****************************************************
   *  Overrides HttpServlet's doGet().
   *  Prints an HTML page with a blank form.
  ********************************************************* */
  @Override
  public void doGet (HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException{ 

    response.setContentType("application/json");
    PrintWriter out = response.getWriter();

    out.print("{\"json-key\":\"json-value\"}");
    out.flush();
  }
}