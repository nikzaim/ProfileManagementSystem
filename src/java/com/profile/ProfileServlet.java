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
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("update".equals(action)) {
            updateProfile(request, response);
        } else {
            createProfile(request, response);
        }
    }
    
    // Standard boilerplate for GET requests (redirects to form if user tries to access Servlet directly)
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if (action == null) action = "view";

        switch (action) {
            case "delete":
                deleteProfile(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "filter":
                filterProfiles(request, response);
                break;
            case "search":
                searchProfiles(request, response);
                break;
            default:
                listProfiles(request, response);
                break;
        }
    }
    
    private void createProfile(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String name = request.getParameter("userName");
        String sid = request.getParameter("studentId");
        String email = request.getParameter("userEmail");
        String program = request.getParameter("program");
        String hobbies = request.getParameter("hobbies");
        String intro = request.getParameter("introduction");

        ProfileBean profile = new ProfileBean(name, sid, email, program, hobbies, intro);

        try (Connection con = DBConnection.getConnection()) {
            String query = "INSERT INTO profiles (name, student_id, email, program, hobbies, introduction) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, profile.getName());
            ps.setString(2, profile.getStudentId());
            ps.setString(3, profile.getEmail());
            ps.setString(4, profile.getProgram());
            ps.setString(5, profile.getHobbies());
            ps.setString(6, profile.getIntroduction());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }

        request.setAttribute("savedProfile", profile);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    private void updateProfile(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("userName");
        String sid = request.getParameter("studentId");
        String email = request.getParameter("userEmail");
        String program = request.getParameter("program");
        String hobbies = request.getParameter("hobbies");
        String intro = request.getParameter("introduction");

        try (Connection con = DBConnection.getConnection()) {
            String query = "UPDATE profiles SET name=?, student_id=?, email=?, program=?, hobbies=?, introduction=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, sid);
            ps.setString(3, email);
            ps.setString(4, program);
            ps.setString(5, hobbies);
            ps.setString(6, intro);
            ps.setInt(7, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
        
        response.sendRedirect("ProfileServlet?action=view");
    }

    private void deleteProfile(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM profiles WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
        response.sendRedirect("ProfileServlet?action=view");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProfileBean p = null;
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM profiles WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = new ProfileBean();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setStudentId(rs.getString("student_id"));
                p.setEmail(rs.getString("email"));
                p.setProgram(rs.getString("program"));
                p.setHobbies(rs.getString("hobbies"));
                p.setIntroduction(rs.getString("introduction"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        request.setAttribute("profile", p);
        request.getRequestDispatcher("editProfile.jsp").forward(request, response);
    }

    private void listProfiles(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        sendListToJSP("SELECT * FROM profiles", null, request, response);
    }

    private void searchProfiles(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String query = request.getParameter("search");
        if (query != null) {
            query = query.trim().toUpperCase();
        }
        // Updated: Search by name only (removed student_id criteria)
        sendListToJSP("SELECT * FROM profiles WHERE UPPER(name) LIKE ?", 
                new String[]{"%" + query + "%"}, request, response);
    }

    private void filterProfiles(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String program = request.getParameter("program");
        if(program != null && !program.equals("All")) {
            sendListToJSP("SELECT * FROM profiles WHERE program = ?", new String[]{program}, request, response);
        } else {
            listProfiles(request, response);
        }
    }
    
    private void sendListToJSP(String sql, String[] params, HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<ProfileBean> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) ps.setString(i + 1, params[i]);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProfileBean p = new ProfileBean();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setStudentId(rs.getString("student_id"));
                p.setProgram(rs.getString("program"));
                p.setHobbies(rs.getString("hobbies"));
                list.add(p);
            }
        } catch (Exception e) { e.printStackTrace(); }
        request.setAttribute("profileList", list);
        request.getRequestDispatcher("viewProfiles.jsp").forward(request, response);
    }
}
