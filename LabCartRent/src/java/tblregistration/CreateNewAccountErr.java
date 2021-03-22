/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tblregistration;

/**
 *
 * @author USER
 */
public class CreateNewAccountErr {

    private String emailLengthErr;
    private String nameLengthErr;
    private String passwordLengthErr;
    private String phoneLengthErr;
    private String addressLengthErr;
    private String passwordNotMatchErr;
    private String existEmailErr;
    private String invalidFormatEmail;
    private String invalidFormatPhone;

    public CreateNewAccountErr() {
    }

    public CreateNewAccountErr(String emailLengthErr, String nameLengthErr, String passwordLengthErr, String phoneLengthErr, String addressLengthErr, String passwordNotMatchErr, String existEmailErr, String invalidFormatEmail, String invalidFormatPhone) {
        this.emailLengthErr = emailLengthErr;
        this.nameLengthErr = nameLengthErr;
        this.passwordLengthErr = passwordLengthErr;
        this.phoneLengthErr = phoneLengthErr;
        this.addressLengthErr = addressLengthErr;
        this.passwordNotMatchErr = passwordNotMatchErr;
        this.existEmailErr = existEmailErr;
        this.invalidFormatEmail = invalidFormatEmail;
        this.invalidFormatPhone = invalidFormatPhone;
    }

    public String getEmailLengthErr() {
        return emailLengthErr;
    }

    public void setEmailLengthErr(String emailLengthErr) {
        this.emailLengthErr = emailLengthErr;
    }

    public String getNameLengthErr() {
        return nameLengthErr;
    }

    public void setNameLengthErr(String nameLengthErr) {
        this.nameLengthErr = nameLengthErr;
    }

    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    public String getPhoneLengthErr() {
        return phoneLengthErr;
    }

    public void setPhoneLengthErr(String phoneLengthErr) {
        this.phoneLengthErr = phoneLengthErr;
    }

    public String getAddressLengthErr() {
        return addressLengthErr;
    }

    public void setAddressLengthErr(String addressLengthErr) {
        this.addressLengthErr = addressLengthErr;
    }

    public String getPasswordNotMatchErr() {
        return passwordNotMatchErr;
    }

    public void setPasswordNotMatchErr(String passwordNotMatchErr) {
        this.passwordNotMatchErr = passwordNotMatchErr;
    }

    public String getExistEmailErr() {
        return existEmailErr;
    }

    public void setExistEmailErr(String existEmailErr) {
        this.existEmailErr = existEmailErr;
    }

    public String getInvalidFormatEmail() {
        return invalidFormatEmail;
    }

    public void setInvalidFormatEmail(String invalidFormatEmail) {
        this.invalidFormatEmail = invalidFormatEmail;
    }

    public String getInvalidFormatPhone() {
        return invalidFormatPhone;
    }

    public void setInvalidFormatPhone(String invalidFormatPhone) {
        this.invalidFormatPhone = invalidFormatPhone;
    }
    
    
}
