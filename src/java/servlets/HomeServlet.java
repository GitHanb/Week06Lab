package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomeServlet extends HttpServlet {

   @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
     {
         HttpSession session = request.getSession();
         
         //check session, if user logged in, go to home page
         if(session.getAttribute("username")!=null)
         {
             String username = (String)session.getAttribute("username");
             
             request.setAttribute("username", username);
             request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
         }
         //go to login page
         response.sendRedirect("login");
           
     }
 
     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException 
     {   
         
     }
}
