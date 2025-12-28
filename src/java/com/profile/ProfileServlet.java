/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.profile;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nikza
 */

@WebServlet(name = "ProfileServlet", urlPatterns = {"/ProfileServlet"})
public class ProfileServlet extends HttpServlet {

    /**
     * Handles the HTTP POST request.
     * This method is called when the HTML form is submitted.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Retrieve data from the HTML form
        String name = request.getParameter("userName");
        String studentId = request.getParameter("studentId");
        String email = request.getParameter("userEmail");
        String program = request.getParameter("program");
        String hobbies = request.getParameter("hobbies");
        String introduction = request.getParameter("introduction");

        // 2. Create the Student Model object and populate it
        Student student = new Student(name, studentId, email, program, hobbies, introduction);
        
        // 3. Set the whole object as an attribute named "studentProfile"
        request.setAttribute("studentProfile", student);

        // 4. Forward to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
        dispatcher.forward(request, response);
    }
    
    // Standard boilerplate for GET requests (redirects to form if user tries to access Servlet directly)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.html");
    }
}
