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
@WebServlet(name = "AddNewMobileServlet", urlPatterns = {"/AddNewMobileServlet"})
public class AddNewMobileServlet extends HttpServlet {
private final String INVALID = "AddNewMobile.jsp";
private final String SUCCESS = "search.jsp";
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
        String url = ERROR;
        try {
            ProductErrorDto errorobj = new ProductErrorDto();
            String id = request.getParameter("txtMobileId");
            String description = request.getParameter("txtMobileDescription");
            String price = request.getParameter("txtMobilePrice");
            String name = request.getParameter("txtMobileName");
            String year = request.getParameter("txtYear");
            String quantity = request.getParameter("txtMobileQuantity");
            String notSale = request.getParameter("chkSale");
            boolean notSa = false;
            boolean valid = true;
            if (notSale != null){
                notSa = true;
            }
            if (id.trim().isEmpty()){
                valid = false;
                errorobj.setIdError("ID is not supposed to be empty");
            }
            if (description.trim().isEmpty()){
                valid = false;
                errorobj.setDescriptionError("Description is not supposed to be empty");
            }
            if (price.trim().isEmpty()){
                valid = false;
                errorobj.setPriceError("Price is not supposed to be empty");
            }
            if (!price.matches("[0-9]") && !price.trim().isEmpty()){
                valid = false;
                errorobj.setPriceError("Price must be a number");
            }
            if (name.trim().isEmpty()){
                valid = false;
                errorobj.setNameError("Name is not supposed to be empty");
            }
            if (year.trim().isEmpty()){
                valid = false;
                errorobj.setYearError("Year is not supposed to be empty");
            }
            if (year.matches("[^0-9]+") && !year.trim().isEmpty()){
                valid = false;
                errorobj.setYearError("Year must be a number");
            }
            if (quantity.trim().isEmpty()){
                valid = false;
                errorobj.setQuantityError("Quantity is not supposed to be empty");
            }
            if (quantity.matches("[^0-9]+") && !quantity.trim().isEmpty()){
                valid = false;
                errorobj.setQuantityError("Quantity must be an number");
            }

            MobileDAO dao = new MobileDAO();
            if (dao.getMobileById(id) != null){
                System.out.println(dao.getMobileById(id).getMobileName());
                valid = false;
                errorobj.setIdError("ID is existed");
            }
            if (valid){
                float price1 = Float.parseFloat(price);
                int year1 = Integer.parseInt(year);
                int quantity1 = Integer.parseInt(quantity);
                MobileDTO dto = new MobileDTO(id, description, price1, name, year1, quantity1, notSa);
                if (dao.createMobile(dto)){
                    url = SUCCESS;
                }
                else {
                    url = INVALID;
                    request.setAttribute("errorMessage", "Insert Failed");
                }
            }
            else {
                url = INVALID;
                request.setAttribute("INVALID", errorobj);
//                request.setAttribute("computerobj", dto);
            }
        }
    catch (NamingException ex) {
        log("\nAdd New Mobile Servlet's exception: " + ex.getMessage());
    } catch (SQLException ex) {
         log("\nAdd New Mobile Servlet's exception: " + ex.getMessage());
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
