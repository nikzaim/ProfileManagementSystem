<%-- 
    Document   : profile
    Created on : Nov 30, 2025, 3:31:22 PM
    Author     : nikza
--%>

<%@page import="com.profile.Student" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile Created</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <%
            // Retrieve the object stored in the request scope by the servlet
            Student studentProfile = (Student) request.getAttribute("studentProfile");
        %>
        <div class="container">
            <h1>Profile Card</h1>
            
            <div class="profile-details">
                
                <div class="result-item">
                    <strong>Name:</strong>
                    <p><%= studentProfile.getName() %></p>
                </div>

                <div class="result-item">
                    <strong>ID:</strong> 
                    <span><%= studentProfile.getStudentId() %></span>
                </div>

                <div class="result-item">
                    <strong>Program:</strong>
                    <span><%= studentProfile.getProgram() %></span>
                </div>

                <div class="result-item">
                    <strong>Email:</strong>
                    <span><%= studentProfile.getEmail() %></span>
                </div>

                <div class="result-item">
                    <strong>Hobbies:</strong>
                    <p><%= studentProfile.getHobbies() %></p>
                </div>

                <div class="result-item" style="border-bottom: none;">
                    <strong>About Me:</strong>
                    <p style="background: #e0e0ff; padding: 10px; border: 2px solid black;">
                        "<%= studentProfile.getIntroduction()%>"
                    </p>
                </div>
            </div>

            <a href="index.html" class="back-link">CREATE NEW PROFILE</a>
        </div>
    </body>
</html>
