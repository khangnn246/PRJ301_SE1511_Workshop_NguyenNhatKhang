/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Khangnn.servlet;

import Khangnn.DAO.MobileDAO;
import Khangnn.DTO.MobileDTO;
import Khangnn.DTO.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.SessionTrackingMode;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author khang
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {
private final String SEARCH_PAGE  = "search.jsp";
private final String SHOW_PAGE = "search.jsp";
private final String UN_LOGIN_PAGE = "unlogin.html";

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = SEARCH_PAGE ;
        String searchValue = request.getParameter("txtsearch");
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("ADMIN");
        try {
            if (searchValue.trim().length() > 0){
                    MobileDAO dao = new MobileDAO();
                     dao.getMobileById(searchValue);
                    if (dao.getMobileList() == null){
                        dao.getMobileByName(searchValue);
                    }
                    List<MobileDTO> result =  dao.getMobileList();
                    request.setAttribute("SEARCH_RESULT", result);
                    url = SHOW_PAGE;                           
            }
            if (user == null){
                url = UN_LOGIN_PAGE;
            }
        }
    catch (NamingException ex) {
        log ("\n SearchServlet_Naming exception :" + ex.getMessage());
    } catch (SQLException ex) {
        log ("\n SearchServlet_SQL exception :" + ex.getMessage());
    }        
        finally{
            request.getRequestDispatcher(url).forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
