/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tblregistration;

import java.io.Serializable;

/**
 *
 * @author USER
 */
public class TblRegistrationDTO implements Serializable{
    private String email;
    private String phone;
    private String fullName;
    private String address;
    private String password;
    private String role;
    private String status;
    private String activeCode;

    public TblRegistrationDTO() {
    }

    public TblRegistrationDTO(String email, String phone, String fullName, String address, String password, String role, String status) {
        this.email = email;
        this.phone = phone;
        this.fullName = fullName;
        this.address = address;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public TblRegistrationDTO(String email, String activeCode) {
        this.email = email;
        this.activeCode = activeCode;
    }
    
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }
    
    
    
}
