/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.profile;

import java.io.Serializable;

/**
 *
 * @author nikza
 */

public class ProfileBean implements Serializable {
    private int id; // Database ID
    private String name;
    private String studentId;
    private String email;
    private String program;
    private String hobbies;
    private String introduction;

    // Default Constructor
    public ProfileBean() {}

    // Constructor for creating new profiles
    public ProfileBean(String name, String studentId, String email, String program, String hobbies, String introduction) {
        this.name = name;
        this.studentId = studentId;
        this.email = email;
        this.program = program;
        this.hobbies = hobbies;
        this.introduction = introduction;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }

    public String getHobbies() { return hobbies; }
    public void setHobbies(String hobbies) { this.hobbies = hobbies; }

    public String getIntroduction() { return introduction; }
    public void setIntroduction(String introduction) { this.introduction = introduction; }
}
