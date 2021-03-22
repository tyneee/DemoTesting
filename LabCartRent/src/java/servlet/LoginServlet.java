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
import tblregistration.TblRegistrationDAO;
import tblregistration.TblRegistrationDTO;

/**
 *
 * @author USER
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
        String url = map.get("loginJsp");
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        try {
            TblRegistrationDAO dao = new TblRegistrationDAO();
            TblRegistrationDTO result = dao.checkLogin(email, password);
            String gRecaptchaResponse = request
                    .getParameter("g-recaptcha-response");
            if (gRecaptchaResponse == null || gRecaptchaResponse.equals("")) {
                // System.out.println(url);
                request.setAttribute("errorsRecaptcha", "You missed recaptcha");
            } else if (result == null) {
                url = map.get("loginJsp");
                request.setAttribute("ErrorLogin", "Invalid email or  password");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("USERNAMES", email);
                String fullName = result.getFullName();
                session.setAttribute("fullName", fullName);
                String status = result.getStatus();
                session.setAttribute("statusAccount", status);
                String role = result.getRole();
                session.setAttribute("role", role);
                if (status.equals("New")) {
                    String code = TblRegistrationDAO.getRandomCode();
                    boolean test = dao.sendEmail(email, code);
                    if (test) {
                      //  HttpSession session = request.getSession();
                        session.setAttribute("verifyCode", code);
                        session.setAttribute("verifyEmail", email);

                        url = map.get("verifyPage");
                    }
                } else if (status.equals("Active")) {

                    url = map.get("homePageJSP");
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
