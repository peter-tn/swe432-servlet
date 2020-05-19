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
        name = "PredicateEvaluator",
        urlPatterns = {"/evaluate"}
    )

public class PredicateEvaluator extends HttpServlet
{
   // Location of servlet.
   static String Path = "swe432-servlets.herokuapp.com/rating";

   /** *****************************************************
    *  Overrides HttpServlet's doPost().
    *  Converts the values in the form, performs the operation
    *  indicated by the submit button, and sends the results
    *  back to the client.
   ********************************************************* */
   public void doPost (HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
   {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String input = request.getParameter("input");
      String[] logOps ={"and", "or"};

      // make to lowercase
      input = input.toLowerCase();
      
      String[] a = input.split("and|or");

      out.println("<html>"); 
      out.println("<title>Test</title>");
      out.println("<header>");
      out.println("input: " + input + "\n");
      for(int k = 0; k < a.length; k++) {
         out.println("  * " + a[k]);
      }
      out.println("</header>");
      out.println("</html>");
   }  // End doPost

   /** *****************************************************
    *  Overrides HttpServlet's doGet().
    *  Prints an HTML page with a blank form.
   ********************************************************* */
   public void doGet (HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException
   {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();

      out.println("<html>"); 
      out.println("<title>Test</title>");
      out.println("<header>");
      out.println("Hello World");
      out.println("</header>");
      out.println("</html>");
   } // End doGet
}
