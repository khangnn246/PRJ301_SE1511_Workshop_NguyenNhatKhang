/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Khangnn.servlet;

import Khangnn.DAO.MobileDAO;
import Khangnn.DTO.ShoppingCartItems;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "RemoveItemServlet", urlPatterns = {"/RemoveItemServlet"})
public class RemoveItemServlet extends HttpServlet {
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
            MobileDAO dao = new MobileDAO();
            HttpSession session = request.getSession();
            List<ShoppingCartItems> shoppinglist = (List<ShoppingCartItems>) session.getAttribute("cart");
            int index = dao.isExisting(request.getParameter("id"), shoppinglist);
            if (index != -1){
                if (shoppinglist.size() > 1){
                    if (shoppinglist.get(index).getQuantity() > 1){
                        int quantity = shoppinglist.get(index).getQuantity() - 1;
                        shoppinglist.get(index).setQuantity(quantity);
                        session.setAttribute("cart", shoppinglist);
                    }
                    else if (shoppinglist.get(index).getQuantity() <= 1) {
                        shoppinglist.remove(shoppinglist.get(index));
                        session.setAttribute("cart",shoppinglist);
                    }
                }
                else if (shoppinglist.size() == 1){
                    shoppinglist.remove(index);
                    session.removeAttribute("cart");
                }
                url = "viewcart.jsp";
            }
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
