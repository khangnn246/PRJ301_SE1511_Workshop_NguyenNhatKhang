/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Khangnn.servlet;

import Khangnn.DAO.MobileDAO;
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
@WebServlet(name = "AddItemToCartServlet", urlPatterns = {"/AddItemToCartServlet"})
public class AddItemToCartServlet extends HttpServlet {
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
        String id = request.getParameter("id");
        String urlRewriting = "error.jsp";
        String lastminsearch = request.getParameter("txtlastminsearch");
        String lastmaxsearch = request.getParameter("txtlastmaxsearch");
        int min = Integer.parseInt(lastminsearch);
        int max = Integer.parseInt(lastmaxsearch);
        try {
            MobileDAO dao = new MobileDAO();
            boolean check = dao.getMobileWithPriceInRange(min, max);
            //1 Customer goes to cart place
            HttpSession session = request.getSession();
            //2.Cus tkae cart 
            if (session.getAttribute("cart") == null){ // chua co thuoc tinh cart trong doi tuong session
                List<ShoppingCartItems> cart = new ArrayList<>(); // tao moi doi tuong collection
                MobileDTO mobile = dao.find(id);
                ShoppingCartItems shoppingcartitems = new ShoppingCartItems(mobile, 1);
                cart.add(shoppingcartitems);
                session.setAttribute("cart", cart);
            }
            else {
                List<ShoppingCartItems> cart = (List<ShoppingCartItems>) session.getAttribute("cart");
                int index = dao.isExisting(id, cart);
                if (index == -1){
                    MobileDTO mobile = dao.find(id);
                    ShoppingCartItems shoppingcartitems2 = new ShoppingCartItems(mobile, 1);
                    cart.add(shoppingcartitems2);
                }
                else {
                    int quantity = cart.get(index).getQuantity() + 1;
                    cart.get(index).setQuantity(quantity);
                }
                session.setAttribute("cart", cart);
            }
            urlRewriting = "DispatchServlet"
                    + "?btn=usersearch"
                    + "&txtminprice="+lastminsearch
                    + "&txtmaxprice="+lastmaxsearch;
        }
        catch (NamingException ex) {
            log ("Add item to cart servlet's exception: "+ ex.getMessage());
        } catch (SQLException ex) {
             log ("Add item to cart servlet's exception: "+ ex.getMessage());
        }        
        finally{
            request.getRequestDispatcher(urlRewriting).forward(request, response);
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
