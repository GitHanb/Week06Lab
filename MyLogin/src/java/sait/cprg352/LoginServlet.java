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
import javax.servlet.http.HttpSession;

/**
 *
 * @author 636334
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //display login form
        String url = "/WEB-INF/login.jsp";
        getServletContext().getRequestDispatcher(url).forward(request, response);
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
        
        UserService user = new UserService();
        
        if(username==null || password==null)
        {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        else if(username.isEmpty()||password.isEmpty())
        {
            request.setAttribute("loginMessage", "Both vales are required!");
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        else if(user.login(username, password)==true)
        {
            request.setAttribute("username", username);    
            getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
            return;
        }
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.setAttribute("loginMessage", "Invalid username and password");
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

}
