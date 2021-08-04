/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Khangnn.servlet;

import Khangnn.DAO.MobileDAO;
import Khangnn.DTO.MobileDTO;
import Khangnn.DTO.ProductErrorDto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author khang
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {
private final String ERROR = "error.jsp";
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
        boolean valid = true;
        String urlRewriting = ERROR;
        try {
            ProductErrorDto error = new ProductErrorDto();

            String lastSearchValue = request.getParameter("txtLastSearchValue");
            String id = request.getParameter("txtmobileid");
            String description = request.getParameter("txtdescription");
            String price1 = request.getParameter("txtprice");
            String name = request.getParameter("txtmobilename");
            String year1 = request.getParameter("txtyear");
            String quantity1 = request.getParameter("txtquantity");
            if (!price1.matches("[0-9]")){
                error.setPriceError("Price must be a number and not supposed to be empty");
                valid = false;
            }
            if (year1.matches("[^0-9]+") || year1.trim().isEmpty() == true){
                error.setYearError("Year must be a number and not supposed to be empty");
                valid = false;
            }
            if (quantity1.matches("[^0-9]+") || quantity1.trim().isEmpty() == true){
                error.setQuantityError("Qauntity must be a number and not supposed to be empty");
                valid = false;
            }
            String notSale = request.getParameter("chkSale");
            boolean notSa = false;
            if (notSale != null){
                notSa = true;
            }
            
            MobileDAO dao = new MobileDAO();
            
            if (valid){
                Float price = Float.parseFloat(price1);
                int year =Integer.parseInt(year1);
                int quantity = Integer.parseInt(quantity1);
                MobileDTO dto = new MobileDTO(id, description, price, name, year, quantity, notSa);
                boolean result = dao.updateMobile(dto);
                if (result){
                    urlRewriting = "DispatchServlet"
                                    + "?btn=Search"
                                    + "&txtsearch="+lastSearchValue;
                }
            }
            else if (valid == false){
                urlRewriting = "DispatchServlet"
                                    + "?btn=Search"
                                    + "&txtsearch="+lastSearchValue;
                request.setAttribute("mobileId", id);
                request.setAttribute("errorobj", error);
            }
        }
    catch (NamingException ex) {
        log("\nUpdateServlet's Naming exception: " + ex.getMessage());
    } catch (SQLException ex) {
        log("\nUpdateServlet's SQL exception: " + ex.getMessage());
    }
    catch (NumberFormatException ex){
        log ("\nUpdateServlet's NumberFormatException: "+ ex.getMessage());
    }
        finally{
            response.sendRedirect(urlRewriting);
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
