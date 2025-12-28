<%-- 
    Document   : editProfile
    Created on : Dec 28, 2025, 11:02:38 PM
    Author     : nikza
--%>

<%@page import="com.profile.ProfileBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Profile</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <div class="container">
            <h1>Edit Profile</h1>
            <% ProfileBean p = (ProfileBean) request.getAttribute("profile"); %>
            <form action="ProfileServlet" method="POST">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="<%= p.getId() %>">

                <div class="form-group">
                    <label>Full Name:</label>
                    <input type="text" name="userName" value="<%= p.getName() %>" required>
                </div>
                <div class="form-group">
                    <label>Student ID:</label>
                    <input type="text" name="studentId" value="<%= p.getStudentId() %>" required>
                </div>
                <div class="form-group">
                    <label>Email Address:</label>
                    <input type="email" name="userEmail" value="<%= p.getEmail() %>" required>
                </div>
                <div class="form-group">
                    <label>Program:</label>
                    <select name="program">
                        <option value="Computer Science" <%= p.getProgram().equals("Computer Science") ? "selected" : "" %>>Computer Science</option>
                        <option value="Information Technology" <%= p.getProgram().equals("Information Technology") ? "selected" : "" %>>Information Technology</option>
                        <option value="Software Engineering" <%= p.getProgram().equals("Software Engineering") ? "selected" : "" %>>Software Engineering</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Hobbies:</label>
                    <input type="text" name="hobbies" value="<%= p.getHobbies() %>">
                </div>
                <div class="form-group">
                    <label>Self Introduction:</label>
                    <textarea name="introduction" rows="4"><%= p.getIntroduction() %></textarea>
                </div>
                <button type="submit">Update Profile</button>
                <a href="ProfileServlet?action=view" class="back-link">Cancel</a>
            </form>
        </div>
    </body>
</html>
