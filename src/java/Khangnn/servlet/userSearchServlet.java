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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author khang
 */
@WebServlet(name = "userSearchServlet", urlPatterns = {"/userSearchServlet"})
public class userSearchServlet extends HttpServlet {
private final String SEARCH_PAGE = "userSearch.jsp";    
private final String ERROR = "error.jsp";
private final String UN_LOGIN = "unlogin.html";
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
        String url = ERROR;
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("USER");
        boolean check = false;
        try  {
            String minSearch = request.getParameter("txtminprice");
            String maxSearch = request.getParameter("txtmaxprice");
            if (minSearch.trim().length() == 0 || maxSearch.trim().length() == 0){
                url = SEARCH_PAGE;
                request.setAttribute("errorempty", "Please input price");
            }
            if (minSearch.matches("[^0-9]+") || maxSearch.matches("[^0-9]+")){
                url = SEARCH_PAGE;
                request.setAttribute("errorTypeInput", "Price must be a number");
            }
            if (!minSearch.matches("[^0-9]+") && !maxSearch.matches("[^0-9]+") && minSearch.trim().length()>0 && maxSearch.trim().length()>0){
                MobileDAO dao = new MobileDAO();
                int min = Integer.parseInt(minSearch);
                int max = Integer.parseInt(maxSearch);
                check = dao.getMobileWithPriceInRange(min, max);
                if (check == true){
                    List<MobileDTO> list = dao.getMobileList();     
                    request.setAttribute("SEARCH_RESULT", list);
                    url = SEARCH_PAGE;
                }
                else {
                    url = SEARCH_PAGE;
                    request.setAttribute("errorSearch", "No record is founded");
                }
            }
            if (user == null){
                url = UN_LOGIN;
            }
        }
    catch (NamingException ex) {
        log ("\nUserSerchServlet's naming Exception: "+ex.getMessage());
    } catch (SQLException ex) {
        log ("\nUserSerchServlet's SQL Exception: "+ex.getMessage());
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
