/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tblcar.TblCarDAO;
import tblcar.TblCarDTO;

/**
 *
 * @author USER
 */
@WebServlet(name = "SearchPageServlet", urlPatterns = {"/SearchPageServlet"})
public class SearchPageServlet extends HttpServlet {

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
        String url = map.get("searchPageJSP");

        boolean foundErr = false;
        try {
            String pickUpDate = request.getParameter("txtPickUpDate");
            String returnDate = request.getParameter("txtReturnDate");
            String amount = request.getParameter("txtAmount");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date datePickUp = sdf.parse(pickUpDate);
            Date dateReturn = sdf.parse(returnDate);

            Date today = new Date();
            String today_1 = sdf.format(today);
            Date today_2 = sdf.parse(today_1);
            String indexPage = request.getParameter("index");
            TblCarDAO carDAO = new TblCarDAO();
            HttpSession session = request.getSession();
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);

            if (datePickUp.compareTo(dateReturn) > 0) {
                request.setAttribute("errorDate", "Pick up date must before return Date");
              //  System.out.println("loi ngafy");
                foundErr = true;
            } else if (datePickUp.compareTo(today_2) < 0) {
                request.setAttribute("errorDate", "Pick up date must not before today");
               // System.out.println("loi ngafy");
                foundErr = true;
            } else if (datePickUp.compareTo(dateReturn) == 0) {
                request.setAttribute("errorDate", "Pick up date must not equal return date");
               // System.out.println("loi ngafy");
                foundErr = true;
            }
            if (foundErr) {

            } else {
                String radioSearch = request.getParameter("a");

                if (radioSearch.equals("name")) {
                    String searchValue = request.getParameter("txtSearch");
                    System.out.println("index = " + indexPage);

                    int countPage = carDAO.countPageNumberWithNameCondition(searchValue, pickUpDate, returnDate, Integer.parseInt(amount));

                    session.setAttribute("countPageSearchByName", countPage);

                    List<TblCarDTO> list = carDAO.getCarWithNameCondition(index, searchValue, pickUpDate, returnDate, Integer.parseInt(amount));

                    session.setAttribute("listSearchByName", list);
                    session.setAttribute("radioName", radioSearch);

                    session.removeAttribute("listSearchByCategory");
                    session.removeAttribute("countPageSearchByCategory");
                    session.removeAttribute("radioCate");
                    session.removeAttribute("CATEGORY");

                } else {
                    String category = request.getParameter("Category");
                    int cateID = carDAO.getcategoryID(category);
                    int countPage = carDAO.countPageNumberWithCategoryCondition(cateID, pickUpDate, returnDate, Integer.parseInt(amount));
                    session.setAttribute("countPageSearchByCategory", countPage);
                    List<TblCarDTO> list = carDAO.getCarWithCategoryCondition(index, cateID, pickUpDate, returnDate, Integer.parseInt(amount));
                    
                    session.setAttribute("radioCate", radioSearch);
                    System.out.println("search category");
                    session.setAttribute("listSearchByCategory", list);
                    session.removeAttribute("radioName");
                    session.setAttribute("CATEGORY", category);
                    session.removeAttribute("listSearchByName");
                    session.removeAttribute("countPageSearchByName");

                }
            }

        } catch (ParseException ex) {
            Logger.getLogger(SearchPageServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SearchPageServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SearchPageServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SearchPageServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SearchPageServlet.class.getName()).log(Level.SEVERE, null, ex);
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
