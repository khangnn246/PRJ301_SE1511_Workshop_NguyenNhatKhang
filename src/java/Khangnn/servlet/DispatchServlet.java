/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Khangnn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {
    private final String LOGIN_PAGE = "login.jsp";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String SEARCH_CONTROLLER = "SearchServlet";
    private final String NULL_CONTROLLER = "NullServlet";
    private final String DELETE_SERVLET = "DeleteServlet";
    private final String UPDATE_SERVLET = "UpdateServlet";
    private final String ADD_NEW_MOBILE_SERVLET = "AddNewMobileServlet";
    private final String USER_SEARCH_SERVLET = "userSearchServlet";
    private final String ADD_TO_CART_SERVLET = "AddItemToCartServlet";
    private final String VIEW_CART = "viewcart.jsp";
    private final String REMOVE_ITEM_FROM_CART_SERVLET = "RemoveItemServlet";
    private final String USER_SEARCH_PAGE = "userSearch.jsp";
    private final String CHECK_OUT_SERVLET = "CheckOutServlet";
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
        String button = request.getParameter("btn");
        String url = LOGIN_PAGE;
        try  {
            if (button == null){
                url = NULL_CONTROLLER;
            }
            else if ("Login".equals(button)){
                url = LOGIN_CONTROLLER;
            }
            else if ("Search".equals(button)){
                url = SEARCH_CONTROLLER;
            }
            else if ("delete".equals(button)){
                url = DELETE_SERVLET;
            }
            else if ("Update".equals(button)){
                url = UPDATE_SERVLET;
            }
            else if ("Add New Mobile".equals(button)){
                url = ADD_NEW_MOBILE_SERVLET;
            }
            else if ("usersearch".equals(button)){
                url = USER_SEARCH_SERVLET;
            }
            else if ("Add To Cart".equals(button)){
                url = ADD_TO_CART_SERVLET;
            }
            else if ("ViewYourCart".equals(button)){
                url = VIEW_CART;
            }
            else if ("removeitems".equals(button)){
                url = REMOVE_ITEM_FROM_CART_SERVLET;
            }
            else if ("returnsearchpage".equals(button)){
                url = USER_SEARCH_PAGE;
            }
            else if ("Check Out".equals(button)){
                url = CHECK_OUT_SERVLET;
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
