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
      String[] b = input.split(" ");
      String[] ops = new String[b.length - a.length];

      for(int k = 0, i = 0; k < b.length; k++) {
         if(k != 0 && k % 2 != 0 && i < ops.length) {
            ops[i] = b[k];
            i++;
         }
      }

      out.println("<html>"); 
      out.println("<title>Test</title>");
      out.println("<header>");
      out.println("input: " + input);
      out.println("</br>");
      for(int k = 0; k < a.length; k++) {
         out.println("  * " + a[k]);
         out.println("</br>");
      }
      out.println("</br>");
      for(int k = 0; k < ops.length; k++) {
         out.println("  * " + ops[k]);
         out.println("</br>");
      }
      int[] tVals = new int [a.length];
      printTruthTable(out, a.length, 0, tVals, ops);
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
   
   private void printTruthTable(PrintWriter out, int N, int index, int[] truthVals, String ops[]) {
      if (index == N) {
         int[] a = new int[N];
         for (int i=0; i<N; i++){
            a[i] = truthVals[i];
            out.println(a[i] + " ");
         }
         
         int i = 0;
         boolean r = (a[0]>=1) && (a[1]>=1);;
         if(ops[0].equals("or"))
            r = (a[0]>=1) || (a[1]>=1);

         for(int k=1 ; k < a.length-1; k++) {
            r = r && (a[k]>=1);
            if(ops[i].equals("or"))
               r = r || (a[k]>=1);
         }

         out.println(" Result: " + r);
         out.println("</br>");
      } else {
         for (int i=0; i<2; i++) {
            truthVals[index] = i;
            printTruthTable(out, N, index + 1, truthVals, ops);
         }
      }
   }

}
