/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tblregistration.CreateNewAccountErr;
import tblregistration.TblRegistrationDAO;

/**
 *
 * @author USER
 */
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ServletContext sc = request.getServletContext();
        Map<String, String> map = (Map<String, String>) sc.getAttribute("MAP");
        String url = map.get("createNewAccount");
        String email = request.getParameter("txtEmail");
        String fullName = request.getParameter("txtName");
        String password = request.getParameter("txtPass");
        String confirmPassword = request.getParameter("txtConfirmPass");
        String phone = request.getParameter("txtPhone");
        String address = request.getParameter("txtAddress");
        CreateNewAccountErr errors = new CreateNewAccountErr();
        boolean foundErr = false;
        try {
            if (email.trim().length() < 10 || email.trim().length() > 50) {
                errors.setEmailLengthErr("Email must has length from 10 to 50 characters");

                foundErr = true;
            } else if (!email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
                errors.setInvalidFormatEmail("Email is invalid (Ex: abc@gmail.com or abc@fpt.edu.vn)");

                foundErr = true;
            }

            if (fullName.trim().length() < 3 || fullName.trim().length() > 25) {
                errors.setNameLengthErr("Name must has length from 3 to 25 characters");

                foundErr = true;
            }

            if (password.trim().length() < 5 || password.trim().length() > 15) {
                errors.setPasswordLengthErr("Password must has length from 5 to 15 character");

                foundErr = true;
            } else if (!password.trim().equals(confirmPassword.trim())) {
                errors.setPasswordNotMatchErr("Confirm password not match with password");

                foundErr = true;
            }
            if (phone.trim().length() < 8 || phone.trim().length() > 15) {
                errors.setPhoneLengthErr("Phone must has length from 8 to 15 characters");

                foundErr = true;
            }else if(!phone.matches("0[^0][0-9]*")){
                errors.setInvalidFormatPhone("Phone number not match format, ex: 01...,02... not 00...");
                
            }
            if (address.trim().length() < 5 || address.trim().length() > 70) {
                errors.setAddressLengthErr("Address must has length from 5 to 70 characters");

                foundErr = true;
            }

            if (foundErr) {
                request.setAttribute("CreateErr", errors);
            } else {
                TblRegistrationDAO dao = new TblRegistrationDAO();
                boolean result = dao.createAccount(email, phone, fullName, address, password, "member", "New");
                if (result) {
                    String code = TblRegistrationDAO.getRandomCode();
                    boolean test = dao.sendEmail(email, code);
                    if (test) {
                        HttpSession session = request.getSession();
                        session.setAttribute("verifyCode", code);
                        session.setAttribute("verifyEmail", email);
                        
                        url = map.get("verifyPage");
                    }
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("CreateErr", errors);
            errors.setExistEmailErr("Email is already in system");
            url = map.get("createNewAccount");

        } catch (NamingException ex) {
            Logger.getLogger(CreateNewAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(CreateNewAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CreateNewAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CreateNewAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
