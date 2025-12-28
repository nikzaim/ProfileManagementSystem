<%-- 
    Document   : viewProfiles
    Created on : Dec 28, 2025, 10:42:02 PM
    Author     : nikza
--%>

<%@page import="java.util.List"%>
<%@page import="com.profile.ProfileBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>All Profiles</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <div class="container">
            <h1>Database Records</h1>

            <!-- UNIQUE FEATURE: SEARCH BAR -->
            <!-- Submitting this form sends a GET request to ProfileServlet -->
            <!-- The Servlet will check for the 'search' parameter to filter results -->
            <form action="ProfileServlet" method="GET" class="search-box">
                <input type="text" name="search" placeholder="Search by Name or ID...">
                <button type="submit">Search</button>
            </form>
            
            <div class="table-wrapper">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Student ID</th>
                            <th>Program</th>
                            <th>Hobbies</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Java Logic to Display List -->
                        <%
                            // Retrieve the list of profiles sent from the Servlet
                            List<ProfileBean> list = (List<ProfileBean>) request.getAttribute("profileList");
                            
                            // Check if list is not null and not empty
                            if (list != null && !list.isEmpty()) {
                                for (ProfileBean p : list) {
                        %>
                        <tr>
                            <td><%= p.getId() %></td>
                            <td><%= p.getName() %></td>
                            <td><%= p.getStudentId() %></td>
                            <td><%= p.getProgram() %></td>
                            <td><%= p.getHobbies() %></td>
                        </tr>
                        <% 
                                }
                            } else {
                        %>
                        <tr>
                            <td colspan="5" style="text-align:center; padding: 20px;">
                                No profiles found.
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>

            <br>
            <!-- Navigation Button -->
            <a href="index.html" class="btn">Add New Profile</a>
        </div>
    </body>
</html>