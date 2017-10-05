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
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        UserService user = (UserService)session.getAttribute("user");
        
        if(user!=null)
        {
            request.setAttribute("user", user);
                  
            //check checkbox
            //request.setAttribute("remember", true);
        }
        
        String action = request.getParameter("action");
        
        if(action!=null)
        {
            if (action.equals("logout")) 
            {
                request.setAttribute("Message", "You have successfully logged out!");
                
                session.removeAttribute("user");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            }
        }
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkbox = request.getParameter("remember");
        
        UserService user = new UserService();
        
        if(username==null || password==null)
        {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        else if(username.isEmpty()||password.isEmpty())
        {
            request.setAttribute("Message", "Both vales are required!");
            user.setUsername(username);
            user.setPassword(password);
            request.setAttribute("user", user); 
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        else if(user.login(username, password)==true)
        {
            user.setUsername(username);
            user.setPassword(password);
            request.setAttribute("user", user);
            
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            
            //checkbox
            
            Cookie c = new Cookie("username", username);
            c.setMaxAge(60);
            c.setPath("/");
            if(checkbox!=null)
            {
                //store username cookie
                response.addCookie(c);
            }
            else
            {
                //remove username cookie
                c.setMaxAge(0);
            }
            
            getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
            return;
        }
        user.setUsername(username);
        user.setPassword(password);
        request.setAttribute("user", user);
        request.setAttribute("Message", "Invalid username and password");
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

}
