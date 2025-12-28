<%-- 
    Document   : profile
    Created on : Nov 30, 2025, 3:31:22 PM
    Author     : nikza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Profile Saved</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <div class="container">
            <h1>Success!</h1>
            <p style="text-align:center;">The following profile has been saved to the database.</p>
            
            <div class="profile-details">
                <!-- 
                   We use Expression Language (EL) to fetch data passed by the Servlet.
                   ${savedProfile.name} calls the getName() method in your ProfileBean.
                -->
                
                <div class="result-item">
                    <strong>Name:</strong>
                    <span>${savedProfile.name}</span>
                </div>

                <div class="result-item">
                    <strong>ID:</strong> 
                    <span>${savedProfile.studentId}</span>
                </div>

                <div class="result-item">
                    <strong>Program:</strong>
                    <span>${savedProfile.program}</span>
                </div>

                <div class="result-item">
                    <strong>Email:</strong>
                    <span>${savedProfile.email}</span>
                </div>

                <div class="result-item">
                    <strong>Hobbies:</strong>
                    <span>${savedProfile.hobbies}</span>
                </div>

                <div class="result-item" style="border-bottom: none;">
                    <strong>Introduction:</strong>
                    <p style="background: #e0e0ff; padding: 10px; border: 3px solid black; margin-top: 5px;">
                        ${savedProfile.introduction}
                    </p>
                </div>
            </div>

            <br>
            <div style="display: flex; gap: 10px; flex-direction: column;">
                <!-- Link to the Search/List Page -->
                <a href="ProfileServlet" class="btn btn-secondary">View All Profiles</a>
                
                <!-- Link back to the Input Form -->
                <a href="index.html" class="btn">Add Another</a>
            </div>
        </div>
    </body>
</html>