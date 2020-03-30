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
        name = "MyServlet2",
        urlPatterns = {"/twoButtons"}
    )

public class RatingServlet extends HttpServlet
{
   // Location of servlet.
   static String Path = "swe432-week8.herokuapp.com/twoButtons";

   // Button labels
   static String OperationAdd = "Add";
   static String OperationSub = "Subtract";
   static String OperationMulti = "Multiply";

   // Other strings.
   static String Style ="https://www.cs.gmu.edu/~offutt/classes/432/432-style.css";

   /** *****************************************************
    *  Overrides HttpServlet's doPost().
    *  Converts the values in the form, performs the operation
    *  indicated by the submit button, and sends the results
    *  back to the client.
   ********************************************************* */
   public void doPost (HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
   {
      Float rslt   = new Float(0.0);
      Float lhsVal = new Float(0.0);
      Float rhsVal = new Float(0.0);
      String operation = request.getParameter("Operation");
      String lhsStr = request.getParameter("LHS");
      String rhsStr = request.getParameter("RHS");
      if ((lhsStr != null) && (lhsStr.length() > 0))
         lhsVal = new Float(lhsStr);
      if ((rhsStr != null) && (rhsStr.length() > 0))
         rhsVal = new Float(rhsStr);

      if (operation.equals(OperationAdd))
      {
         rslt = new Float(lhsVal.floatValue() + rhsVal.floatValue());
      }
      else if (operation.equals(OperationSub))
      {
         rslt = new Float(lhsVal.floatValue() - rhsVal.floatValue());
      }
      else if (operation.equals(OperationMulti))
      {
         rslt = new Float(lhsVal.floatValue() * rhsVal.floatValue());
      }

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      PrintHead(out);
      PrintBody(out, lhsStr, rhsStr, rslt.toString());
      PrintTail(out);
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
      PrintHead(out);
      PrintBody(out);
      PrintTail(out);
   } // End doGet

   /** *****************************************************
    *  Prints the <head> of the HTML page, no <body>.
   ********************************************************* */
   private void PrintHead (PrintWriter out)
   {
      out.println("<html>");
      out.println("");

      out.println("<head>");
      out.println("<title>Two buttons example</title>");
      out.println(" <link rel=\"stylesheet\" type=\"text/css\" href=\"" + Style + "\">");
      out.println("</head>");
      out.println("");
   } // End PrintHead

   /** *****************************************************
    *  Prints the <BODY> of the HTML page with the form data
    *  values from the parameters.
   ********************************************************* */
   private void PrintBody (PrintWriter out, String lhs, String rhs, String rslt)
   {
      out.println("<body>");
      out.println("<h1>");
      out.println("In-class participation assignment");
      out.println("</h1>");
      out.println("<h5>");
      out.println("By Peter Nguyen!");
      out.println("</h5>");
      out.println("<p>");
      out.println("A simple example that demonstrates how to operate with");
      out.println("multiple submit buttons.");
      out.println("</p>");
      out.print  ("<form method=\"post\"");
      out.println(" action=\"https://" + Path + "\">");
      out.println("");
      out.println(" <table>");
      out.println("  <tr>");
      out.println("   <td>First value:");
      out.println("   <td><input type=\"text\" name=\"LHS\" value=\"" + lhs + "\" size=5>");
      out.println("  </tr>");
      out.println("  <tr>");
      out.println("   <td>Second value:");
      out.println("   <td><input type=\"text\" name=\"RHS\" value=\"" + rhs + "\" size=5>");
      out.println("  </tr>");
      out.println("  <tr>");
      out.println("   <td>Result:");
      out.println("   <td><input type=\"text\" name=\"RHS\" value=\"" + rslt + "\" size=6>");
      out.println("  </tr>");
      out.println(" </table>");
      out.println(" <br>");
      out.println(" <br>");
      out.println(" <input type=\"submit\" value=\"" + OperationAdd + "\" name=\"Operation\">");
      out.println(" <input type=\"submit\" value=\"" + OperationSub + "\" name=\"Operation\">");
      out.println(" <input type=\"submit\" value=\"" + OperationMulti + "\" name=\"Operation\">");
      out.println(" <input type=\"reset\" value=\"Reset\" name=\"reset\">");
      out.println("</form>");
      out.println("");
      out.println("</body>");
   } // End PrintBody

   /** *****************************************************
    *  Overloads PrintBody (out,lhs,rhs,rslt) to print a page
    *  with blanks in the form fields.
   ********************************************************* */
   private void PrintBody (PrintWriter out)
   {
      PrintBody(out, "", "", "");
   }

   /** *****************************************************
    *  Prints the bottom of the HTML page.
   ********************************************************* */
   private void PrintTail (PrintWriter out)
   {
      out.println("");
      out.println("</html>");
   } // End PrintTail
}
