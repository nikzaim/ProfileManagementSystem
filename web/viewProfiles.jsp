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
        <script>
            function confirmDelete(id) {
                if (confirm("Are you sure you want to delete this profile?")) {
                    window.location.href = "ProfileServlet?action=delete&id=" + id;
                }
            }
        </script>
        
    </head>
    <body>
        <div class="container">
            <h1>Manage Profiles</h1>

            <div class="tools-container">
                <!-- FEATURE 1: SEARCH -->
                <div class="tool-group">
                    <form action="ProfileServlet" method="GET">
                        <input type="hidden" name="action" value="search">
                        <label>Search:</label>
                        <div style="display:flex; gap:5px;">
                            <input type="text" name="search" placeholder="Name">
                            <button type="submit" style="padding:5px; font-size:14px;">Go</button>
                        </div>
                    </form>
                </div>

                <!-- FEATURE 2: FILTER -->
                <div class="tool-group">
                    <form action="ProfileServlet" method="GET">
                        <input type="hidden" name="action" value="filter">
                        <label>Filter by Program:</label>
                        <div style="display:flex; gap:5px;">
                            <select name="program">
                                <option value="All">All Programs</option>
                                <option value="Computer Science">Computer Science</option>
                                <option value="Information Technology">Information Technology</option>
                                <option value="Software Engineering">Software Engineering</option>
                                <option value="Data Science">Data Science</option>
                            </select>
                            <button type="submit" style="padding:5px; font-size:14px;">Filter</button>
                        </div>
                    </form>
                </div>
            </div>
            
            <div class="table-wrapper">
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>ID</th>
                            <th>Program</th>
                            <th>Hobbies</th>
                            <th>Actions</th> <!-- NEW COLUMN -->
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<ProfileBean> list = (List<ProfileBean>) request.getAttribute("profileList");
                            if (list != null && !list.isEmpty()) {
                                for (ProfileBean p : list) {
                        %>
                        <tr>
                            <td><%= p.getName() %></td>
                            <td><%= p.getStudentId() %></td>
                            <td><%= p.getProgram() %></td>
                            <td><%= p.getHobbies() %></td>
                            <td>
                                <!-- FEATURE 3: EDIT -->
                                <a href="ProfileServlet?action=edit&id=<%= p.getId() %>" class="action-btn edit-btn">Edit</a>
                                
                                <!-- FEATURE 4: DELETE -->
                                <a href="javascript:void(0)" onclick="confirmDelete(<%= p.getId() %>)" class="action-btn del-btn">X</a>
                            </td>
                        </tr>
                        <% 
                                }
                            } else {
                        %>
                        <tr><td colspan="5" style="text-align:center;">No profiles found.</td></tr>
                        <% } %>
                    </tbody>
                </table>
            </div>

            <br>
            <a href="index.html" class="btn">Add New Profile</a>
        </div>
    </body>
</html>