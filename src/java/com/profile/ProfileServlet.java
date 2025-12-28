/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.profile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
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
     * doPost: Handles FORM SUBMISSION (Saving Data)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Get data from HTML form
        String name = request.getParameter("userName");
        String sid = request.getParameter("studentId");
        String email = request.getParameter("userEmail");
        String program = request.getParameter("program");
        String hobbies = request.getParameter("hobbies");
        String intro = request.getParameter("introduction");

        // 2. Wrap data in a Bean
        ProfileBean profile = new ProfileBean(name, sid, email, program, hobbies, intro);

        // 3. Insert into Database
        try (Connection con = DBConnection.getConnection()) {
            String query = "INSERT INTO profiles (name, student_id, email, program, hobbies, introduction) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, profile.getName());
            ps.setString(2, profile.getStudentId());
            ps.setString(3, profile.getEmail());
            ps.setString(4, profile.getProgram());
            ps.setString(5, profile.getHobbies());
            ps.setString(6, profile.getIntroduction());
            
            ps.executeUpdate(); // Run the SQL Insert
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 4. Send saved data to profile.jsp (Confirmation Page)
        request.setAttribute("savedProfile", profile);
        RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
        rd.forward(request, response);
    }
    
    // Standard boilerplate for GET requests (redirects to form if user tries to access Servlet directly)
    /**
     * doGet: Handles VIEW ALL and SEARCH (Unique Feature)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String searchQuery = request.getParameter("search");
        List<ProfileBean> list = new ArrayList<>();
        
        try (Connection con = DBConnection.getConnection()) {
            String sql;
            PreparedStatement ps;
            
            // UNIQUE FEATURE: Search Logic
            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                // If user typed something, search by Name OR Student ID
                sql = "SELECT * FROM profiles WHERE name LIKE ? OR student_id LIKE ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + searchQuery + "%"); // % means "contains this text"
                ps.setString(2, "%" + searchQuery + "%");
            } else {
                // If search is empty, get EVERYONE
                sql = "SELECT * FROM profiles";
                ps = con.prepareStatement(sql);
            }
            
            ResultSet rs = ps.executeQuery();
            
            // Loop through results and save to List
            while (rs.next()) {
                ProfileBean p = new ProfileBean();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setStudentId(rs.getString("student_id"));
                p.setEmail(rs.getString("email"));
                p.setProgram(rs.getString("program"));
                p.setHobbies(rs.getString("hobbies"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Send the list to viewProfiles.jsp
        request.setAttribute("profileList", list);
        RequestDispatcher rd = request.getRequestDispatcher("viewProfiles.jsp");
        rd.forward(request, response);
    }
}
