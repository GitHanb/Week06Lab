package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sait.cprg352.CookieUtil;
import sait.cprg352.UserService;

public class LoginServlet extends HttpServlet 
{

    @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
     {
         String action = request.getParameter("action");
         HttpSession session = request.getSession();
         
         String username = CookieUtil.getCookieValue(request.getCookies(), "username");
         //cookie check if cookie not null
         if(username!=null && username.equals(""))
         {
             request.setAttribute("checked", "checked");
             request.setAttribute("username", username);   
         }
         
         if(session.getAttribute("username")!=null && action==null)
         {
             response.sendRedirect("home");
             return;
         }
         
         if(action!=null && action.equals("logout"))
         {
             session.removeAttribute("username");
             session.invalidate();
             request.setAttribute("loginMessage", "You have successfully logged out.");
         }
         request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        
     }
 
     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException 
     {
         String username = request.getParameter("username");
         String password = request.getParameter("password");
         boolean rememberMe = false;
         String rememberMeString = request.getParameter("remember");
         String message = null;
         
         if(rememberMeString!=null)
         {
             rememberMe = Boolean.parseBoolean(rememberMeString);
         }
         
         if(username!=null && password!=null && !username.equals("") && !password.equals(""))
         {
             UserService us = new UserService();
             
             if(us.login(username, password)==true)
             {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setMaxInactiveInterval(60);
                 
                Cookie usernameCookie = new Cookie("username", username);
             
                if(rememberMe == true)
                {
                    usernameCookie.setMaxAge(60*60*24*365);
                    usernameCookie.setPath("/");
                    response.addCookie(usernameCookie);
                }
                else
                {
                    if(usernameCookie!=null)
                    {
                        usernameCookie.setMaxAge(0);
                        usernameCookie.setPath("/");
                        response.addCookie(usernameCookie);
                    }
                }
                request.setAttribute("username", username);
                response.sendRedirect("home");
                return;  
             }
             else
             {
                message = "Invalid username or password.";
             }  
         }
         else
         {
             message = "Both username and password are required.";
         }
         request.setAttribute("username", username);
         request.setAttribute("password", password);
         request.setAttribute("loginMessage", message);
         request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
     }
     
}
