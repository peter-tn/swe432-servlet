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
      String printType = request.getParameter("radio1");
      String[] logOps ={"and", "or"};

      // make to lowercase
      input = input.toLowerCase();
      
      String[] a = input.split("and|or");
      String[] arrInput = input.split(" ");
      String[] ops = new String[arrInput.length - a.length];

      for(int k = 0, i = 0; k < arrInput.length; k++) {
         if(k != 0 && k % 2 != 0 && i < ops.length) {
            ops[i++] = arrInput[k];
         }
      }

      out.println("<html>"); 
      out.println("<title>Test</title>");
      out.println("<header>");
      out.println("input: " + input);
      out.println("</br>");
      if(ops.length != a.length-1) {
         out.println("Invalid input :(");  
         out.println("</br>");
         out.println("Please check if there's a logical operator BETWEEN EACH clause");
         out.println("</br>");
         out.println("We don't support multi-word objects right now like \"Saucy Pizza\" :(");  
      }
      else {
         int[] tVals = new int [a.length];
         printTruthTable(out, a.length, 0, tVals, ops, printType);
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
   
   private void printTruthTable(PrintWriter out, int N, int index, int[] truthVals, String ops[], String printType) {
      if (index == N) {

         // prints out truth table values
         int[] a = new int[N];
         for (int i=0; i<N; i++){
            a[i] = truthVals[i];
            if(printType.equals("1-0"))
               out.println(a[i] + " ");
            if(printType.equals("t-f")) {
               String s = (a[i]>=1) ? "t" : "f";
               out.println(s + " ");
            }
         }
         
         // evaluator for each row
         int i = 0;
         boolean r = (a[0]>=1) && (a[1]>=1);;
         if(ops[i++].equals("or"))
            r = (a[0]>=1) || (a[1]>=1);
         for(int k=2; k < a.length; k++) {
            boolean temp = r && (a[k]>=1);
            if(ops[i++].equals("or"))
               temp = r || (a[k]>=1);
            r = temp;
         }
         out.println("        Result: " + r);
         out.println("</br>");
      } else {
         for (int i=0; i<2; i++) {
            truthVals[index] = i;
            printTruthTable(out, N, index + 1, truthVals, ops, printType);
         }
      }
   }

}
