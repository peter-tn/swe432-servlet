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
     throws ServletException, IOException
  {
     String name = request.getParameter(Data.NAME.name());
     String nickname = request.getParameter(Data.NICKNAME.name());
     String age = request.getParameter(Data.AGE.name());

     String error = "";
     if(name == null){
       error= "<li>Name is required</li>";
       name = "";
     }
     if(nickname == null){
      error= "<li>Nickname is required</li>";
      name = "";
    }
     if(age == null){
       error+= "<li>Age is required.<li>";
       age = "";
     }else{
          try{
            Integer ageInteger =new Integer(age);
            if(ageInteger<1){
                error+= "<li>Age must be an integer greater than 0.</li>";
                age = "";
            }else{
              if(ageInteger>150){
                  error+= "<li>Age must be an integer less than 150.</li>";
                  age = "";
              }
            }
          }catch (Exception e) {
            error+= "<li>Age must be an integer greater than 0.</li>";
            age = "";
          }
     }

     response.setContentType("text/html");
     PrintWriter out = response.getWriter();

     if (error.length() == 0){
       PrintWriter entriesPrintWriter = new PrintWriter(new FileWriter(RESOURCE_FILE, true), true);
       entriesPrintWriter.println(name+VALUE_SEPARATOR+nickname+VALUE_SEPARATOR+age);
       entriesPrintWriter.close();

     }else{

     }
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