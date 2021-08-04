/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Khangnn.servlet;

import Khangnn.DAO.CheckOutDAO;
import Khangnn.DTO.MobileDTO;
import Khangnn.DTO.ShoppingCartItems;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {
private final String CHECK_OUT_SUCCESS = "checkoutsucess.html";
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
        String url = CHECK_OUT_SUCCESS;
        try {
            HttpSession session = request.getSession(false);
            if (session != null){
                List<ShoppingCartItems> cart = (List<ShoppingCartItems>) session.getAttribute("cart");
                if (cart != null){
                    try {
                        for (ShoppingCartItems shoppingCartItems : cart) {
                        if (shoppingCartItems.getMobile()!= null){
                            System.out.println(shoppingCartItems.getMobile().getMobileId());
                            CheckOutDAO dao = new CheckOutDAO();
                            if (dao.checkOrder(shoppingCartItems.getMobile().getMobileId()) == false){
                                
                                dao.insertOrder(shoppingCartItems);
                            }
                            else {
                                dao.updateOrder(shoppingCartItems);
                                }
                            }   
                        }
                    } 
                        catch (NamingException ex) {
                        log ("Checkout Servlet's exception: " + ex.getMessage());
                    } catch (SQLException ex) {
                        log ("Checkout Servlet's exception: " + ex.getMessage());
                    }
                    List<ShoppingCartItems> removeList = new ArrayList<>();
                    for (ShoppingCartItems shoppingCartItems : cart) {
                        if (shoppingCartItems.getMobile() != null){
                            removeList.add(shoppingCartItems);
                        }
                    }
                    cart.removeAll(removeList);
                    session.removeAttribute("cart");
                }
            }
        }
        
        finally{
            response.sendRedirect(url);
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
