/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sait.cprg352;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 636334
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String action = request.getParameter("action");
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession();
        
        User user = null;
        String username = null;
        
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
                    }
                }
                if(username != null)
                {
                    user = new User(username,null);
                    request.setAttribute("user", user);
                }
            }
            session.removeAttribute("userlogin");
            session.invalidate();
            request.setAttribute("Message", "Logged out successfully!");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User myUser = new User(username,password);

        String checkbox = request.getParameter("remember");
         
         
        if( username==null || password == null || username.trim().isEmpty() ||password.trim().isEmpty())
        {
            request.setAttribute("Message","Both values are required!");
            request.setAttribute("user", myUser);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }

        UserService userservice = new UserService();
        
        if(userservice.login(username, password) == false)
        {
          request.setAttribute("Message","Invalid username or password!");
          request.setAttribute("user", myUser);
          getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);  
        }
        else
        {
            if(checkbox!=null)
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
            session.setAttribute("userlogin",myUser);   
            response.sendRedirect("home");
        }
    }
}
