/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sait.cprg352;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 636334
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        
        String action = request.getParameter("action");
        
        if (action.equals("logout")) 
        {
            request.setAttribute("loginMessage", "You have successfully logged out!");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        
        
        
        //if cookie exists, autofill in username and check checkbox
        
        /*
        HttpSession session = request.getSession();
        
        UserService user = (UserService)session.getAttribute("user");
        if(user==null)
        {
            user = new UserService();
        }
        //remove an object
        session.removeAttribute("user");
        */
        
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
            request.setAttribute("loginMessage", "Both vales are required!");
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
            getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
            return;
        }
        if(checkbox.equals("true"))
        {
            //store username in a cookie
            
        }
        request.setAttribute("loginMessage", "Invalid username and password");
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

}
