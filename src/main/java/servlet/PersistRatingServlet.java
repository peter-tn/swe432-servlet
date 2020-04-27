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

      // response.setContentType("text/html");
      // PrintWriter out = response.getWriter();

      PrintWriter entriesPrintWriter = new PrintWriter(new FileWriter(RESOURCE_FILE, true), true);
      entriesPrintWriter.println(r1+VALUE_SEPARATOR+r2+VALUE_SEPARATOR+r3+VALUE_SEPARATOR+r4);
      entriesPrintWriter.close();

      response.sendRedirect("https://swe432-react-site.herokuapp.com/reviews");
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
    // printResponseBody(out, RESOURCE_FILE);
    out.flush();
  }

  private void printResponseBody(PrintWriter out, String resourcePath) {
    out.println("<table>");
    try {
      File file = new File(resourcePath);
      if(!file.exists()){
        out.println("  <tr>");
        out.println("   <td>No entries persisted yet.</td>");
        out.println("  </tr>");
        return;
      }

      BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        String []  entry= line.split(VALUE_SEPARATOR);
        out.println("  <tr>");
        for(String value: entry){
            out.println("   <td>"+value+"</td>");
        }
        out.println("  </tr>");
      }
      bufferedReader.close();
    } catch (FileNotFoundException ex) {
          ex.printStackTrace();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    out.println("</table>");
  }
}