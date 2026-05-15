package com.example.foodapp.model;

public class AdminUserModel {
    private String adminId;
    private String name;
    private String email;
    private String role;

    public AdminUserModel() {
        // Default constructor required for Firebase
    }

    public AdminUserModel(String adminId, String name, String email, String role) {
        this.adminId = adminId;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

