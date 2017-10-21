package sait.cprg352;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomeServlet extends HttpServlet {

   @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
           HttpSession session = request.getSession();
           User user = (User)session.getAttribute("userlogin");
           String url = "/WEB-INF/home.jsp";
           if(user != null)
           {
               request.setAttribute("user", user);
               getServletContext().getRequestDispatcher(url).forward(request, response);
               return;
           }
           else
           {
               url= "/WEB-INF/login.jsp";
               response.sendRedirect("login");
               return;
           }
           
     }
 
     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException 
     {
         
         doGet(request,response);
     }
}
