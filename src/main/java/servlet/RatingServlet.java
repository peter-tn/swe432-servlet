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
        name = "MyServlet3",
        urlPatterns = {"/rating"}
    )

public class RatingServlet extends HttpServlet
{
   // Location of servlet.
   static String Path = "swe432-week8.herokuapp.com/rating";

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

      // out.println("");
      out.println("<head>");
      out.println("<title>HTML Assignment 6</title>");
      out.println("<style>");
      out.println("* {box-sizing: border-box; }");
      out.println("header { background-color: #FFB6C1; padding: 10px; text-align: center; font-size: 25px; color: white; } .column { float: left; width: 25%; }");
      out.println(".row:after { content: ''; display: table; clear: both; }");
      out.println("</style>");
      out.println("<script>");
      out.println("function checkForm() { if( document.form['1st picture rating'].value == '' || document.form['2nd picture rating'].value == '' || document.form['3rd picture rating'].value == '' || document.form['4th picture rating'].value == '') { alert('Please give all pictures a rating :)'); return false; } if(document.form.userExplanation.value == '') { document.getElementById('userExplanation').style.borderColor='red'; document.getElementById('userExplanation').style.borderWidth='3px'; setTimeout(function() { alert('Please tell us how you rated :)'); },10) return false; } return true; }");
      out.println("function resetText() { document.getElementById('userExplanation').style.borderColor=''; document.getElementById('userExplanation').style.borderWidth=''; }");
      out.println("</script>");
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

      out.println("<header>");
      out.println("<h1 style='text-align:center;''>Please rate the cutest animal!:)</h1>");
      out.println("<h4 style='text-align:center;'>SWE 432 Peter Nguyen</h4>");
      out.println("</header>");

      out.print  ("<form style='margin-left: 10px' method=\"post\" action=\"https://" + Path + "\" id='form' name='form'>");
      out.println("<div class='row'>");

      out.println("<div class='column'>");
      out.println("<br> <img src='https://i.redd.it/at0lclbhxewz.jpg' style='max-height: 200px;'> <div>How does this picture look?</div>");
      out.println("<input type='radio' name='1st picture rating' id='eh' value='eh'/>");
      out.println("<label for='eh'>eh</label><br>");
      out.println("<input type='radio' name='1st picture rating' id='ok' value='ok'/>");
      out.println("<label for='ok'>ok</label><br>");
      out.println("<input type='radio' name='1st picture rating' id='cute' value='cute'/>");
      out.println("<label for='cute'>cute</label><br>");
      out.println("<input type='radio' name='1st picture rating' id='adorable' value='adorable'/>");
      out.println("<label for='adorable'>adorable sort of cute </label><br>");
      out.println("<input type='radio' name='1st picture rating' id='please buy me one cute' value='please buy me one cute'/>");
      out.println("<label for='please buy me one cute'>\"please buy me one\" sort of cute</label><br>");
      out.println("</div>");

      out.println("<div class='column'>");
      out.println("<br> <img src='https://snworksceo.imgix.net/ohi/72fe00d0-d563-48cd-b05a-957bec8207e6.sized-1000x1000.jpeg?w=800&h=600' style='max-height: 200px;'> <div>How does this picture look?</div>");
      out.println("<input type='radio' name='1st picture rating' id='eh' value='eh'/>");
      out.println("<label for='eh'>eh</label><br>");
      out.println("<input type='radio' name='1st picture rating' id='ok' value='ok'/>");
      out.println("<label for='ok'>ok</label><br>");
      out.println("<input type='radio' name='1st picture rating' id='cute' value='cute'/>");
      out.println("<label for='cute'>cute</label><br>");
      out.println("<input type='radio' name='1st picture rating' id='adorable' value='adorable'/>");
      out.println("<label for='adorable'>adorable sort of cute </label><br>");
      out.println("<input type='radio' name='1st picture rating' id='please buy me one cute' value='please buy me one cute'/>");
      out.println("<label for='please buy me one cute'>\"please buy me one\" sort of cute</label><br>");
      out.println("</div>");

      out.println("<div class='column'>");
      out.println("<br> <img src='https://1funny.com/wp-content/uploads/2011/06/laughing-red-panda.jpg' style='max-height: 200px;'> <div>How does this picture look?</div>");
      out.println("<input type='radio' name='1st picture rating' id='eh' value='eh'/>");
      out.println("<label for='eh'>eh</label><br>");
      out.println("<input type='radio' name='1st picture rating' id='ok' value='ok'/>");
      out.println("<label for='ok'>ok</label><br>");
      out.println("<input type='radio' name='1st picture rating' id='cute' value='cute'/>");
      out.println("<label for='cute'>cute</label><br>");
      out.println("<input type='radio' name='1st picture rating' id='adorable' value='adorable'/>");
      out.println("<label for='adorable'>adorable sort of cute </label><br>");
      out.println("<input type='radio' name='1st picture rating' id='please buy me one cute' value='please buy me one cute'/>");
      out.println("<label for='please buy me one cute'>\"please buy me one\" sort of cute</label><br>");
      out.println("</div>");

      out.println("<div class='column'>");
      out.println("<br> <img src='https://blogs.cdc.gov/publichealthmatters/files/2016/01/penguin-dancing.jpg' style='max-height: 200px;'> <div>How does this picture look?</div>");
      out.println("<input type='radio' name='1st picture rating' id='eh' value='eh'/>");
      out.println("<label for='eh'>eh</label><br>");
      out.println("<input type='radio' name='1st picture rating' id='ok' value='ok'/>");
      out.println("<label for='ok'>ok</label><br>");
      out.println("<input type='radio' name='1st picture rating' id='cute' value='cute'/>");
      out.println("<label for='cute'>cute</label><br>");
      out.println("<input type='radio' name='1st picture rating' id='adorable' value='adorable'/>");
      out.println("<label for='adorable'>adorable sort of cute </label><br>");
      out.println("<input type='radio' name='1st picture rating' id='please buy me one cute' value='please buy me one cute'/>");
      out.println("<label for='please buy me one cute'>\"please buy me one\" sort of cute</label><br>");
      out.println("</div>");

      out.println("</div>");

      out.println("<div class='row' style='margin-top: 10px;'>");
      out.println("<div class='column' style='width: 50%;'>");
      out.println("<div>We would love to know the reasoning for each picture rating!</div>");
      out.println("<textarea rows='7' cols='150' form='form' id ='userExplanation' name='userExplanation' onclick='resetText()'></textarea>");
      out.println("<input type='submit' value='Submit' style='margin-top: 10px;'>");
      out.println("</div>");
      out.println("</div>");

      out.println("</form>");

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
