package sait.cprg352;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet 
{

    @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
     {
         
      String action = request.getParameter("action");
      String username =null;
      User user = null;
      Cookie[] cookies =request.getCookies();
        
      HttpSession session = request.getSession();
      if(action == null || action.isEmpty() ||action.equals("login"))
      {
        if(session.getAttribute("userlogin")!=null)
        {
            response.sendRedirect("home");
            return ;
        }
        if(cookies != null)
        {
            String cookiename = "username";
            for(Cookie cookie : cookies)
            {
                if(cookie.getName().equals(cookiename))
                {
                    username = cookie.getValue();
                    System.out.println("username :"+username);
                }
            }
            if(username != null)
            {
                user = new User(username,null);
                request.setAttribute("user", user);
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        return ;
      }
        
        if(action.equals("logout"))
        {
            if(cookies != null)
            {
                String cookiename = "username";

                for(Cookie cookie : cookies)
                {
                    if(cookie.getName().equals(cookiename))
                    {
                        username = cookie.getValue();
                        System.out.println("username :"+username);
                    }
                }
                if(username != null)
                {
                    user = new User(username,null);
                    request.setAttribute("user", user);
                }
            }
            session.removeAttribute("userlogin");
            request.setAttribute("display", "Logged out successfully!");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        
     }
 
     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         
         String username = request.getParameter("Uname");
         String password = request.getParameter("Pword");
         User user1 = new User(username,password);

         boolean rememberme = request.getParameter("rememberme")!=null;
         
         
         if( username==null || password == null || username.trim().isEmpty() ||password.trim().isEmpty())
         {
             request.setAttribute("display","Both values are required!");
             request.setAttribute("user", user1);
             getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
             return;
         }

         UserService us = new UserService();
         User user = us.login(username, password);
         if( user == null)
         {
           request.setAttribute("display","Invalid username or password!");
           request.setAttribute("user", user1);
           getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);  
           return;
         }
         else
         {
            if(rememberme==true)
            {
                Cookie cookie = new Cookie("username", username);
                cookie.setMaxAge(3600);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            else
            {
                 Cookie[] cookies =request.getCookies();
                 String cookiename = "username";
                 for(Cookie cookie : cookies)
                     {
                         if(cookie.getName().equals(cookiename))
                         {
                             cookie.setMaxAge(0);
                             response.addCookie(cookie);
                         }
                     }
            }
            HttpSession session = request.getSession();
            session.setAttribute("userlogin",user);   
            response.sendRedirect("home");
            return;
         }
     }
}
