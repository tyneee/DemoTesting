/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tblregistration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import util.DBHelper;

/**
 *
 * @author USER
 */
public class TblRegistrationDAO implements Serializable {

    public TblRegistrationDTO checkLogin(String email, String password) throws SQLException, NamingException, Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet result = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select email,phone,fullName,address,password,role,status\n"
                        + "from tblRegistration\n"
                        + "where email = ? and password = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, email);
                pstm.setString(2, password);
                result = pstm.executeQuery();
                if (result.next()) {
                    TblRegistrationDTO dto = new TblRegistrationDTO(result.getString("email"), result.getString("phone"), result.getString("fullName"), result.getString("address"), result.getString("password"), result.getString("role"), result.getString("status"));
                    //   System.out.println(dto);
                    return dto;

                }
            }
        } finally {
            if (result != null) {
                result.close();
            }

            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return null;
    }

    public boolean createAccount(String email, String phone, String name, String address, String password, String role, String status) throws SQLException, NamingException, Exception {
        Connection con = null;
        PreparedStatement pstm = null;

        try {

            con = DBHelper.makeConnection();

            if (con != null) {
                String sql = "Insert into tblRegistration values(?,?,?,?,CURRENT_TIMESTAMP,?,?,?)";

                pstm = con.prepareStatement(sql);
                pstm.setString(1, email);
                pstm.setString(2, phone);
                pstm.setString(3, name);
                pstm.setString(4, address);
                pstm.setString(5, password);
                pstm.setString(6, role);
                pstm.setString(7, status);

                int row = pstm.executeUpdate();

                if (row > 0) {
                    return true;
                }

            }
        } finally {

            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;
    }

    public static String getRandomCode() {
        Random rd = new Random();
        int code = rd.nextInt(999999);
        return String.format("%06d", code);
    }

    public boolean sendEmail(String toEmail, String randomCode) throws AddressException, MessagingException {
        boolean test = false;

        String fromEmail = "tamphung1237898@gmail.com";
        String password = "tam123123";
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });
        Message mess = new MimeMessage(session);
        mess.setFrom(new InternetAddress(fromEmail));
        mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        mess.setSubject("User Email Verification");
        mess.setText("Registered successful. Please verify code to your email:" + randomCode);
        Transport.send(mess);
        test = true;
        return test;
    }

    public boolean updateStatusAccount(String email) throws NamingException, SQLException, Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        int row = 0;
        try {
            con = DBHelper.makeConnection();
            String sql = "update tblRegistration\n"
                    + "set status = 'Active'\n"
                    + "where email = ?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, email);
         
            row = pstm.executeUpdate();
            if (row > 0) {
                return true;
            }

        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

}
