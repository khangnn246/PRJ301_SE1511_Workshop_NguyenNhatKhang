/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Khangnn.servlet;

import Khangnn.DAO.UserDAO;
import Khangnn.DTO.UserDTO;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author khang
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
private final String SEARCH_PAGE = "search.jsp";
private final String ERROR_PAGE = "error.jsp";
private final String LOGIN_PAGE = "login.jsp"; 
private final String USER_SEARCH_PAGE = "userSearch.jsp";
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
        String fullName = "";
        String url = ERROR_PAGE;
        try  {            
            UserDAO dao = new UserDAO();
            String username = request.getParameter("txtusername");
            String password1 =request.getParameter("txtpassword") ;
            if (username == "" && password1 == ""){
                url = LOGIN_PAGE;
                request.setAttribute("errmsg", "Please login first");
            }
            else if ( password1.matches("[^0-9]+") || password1.trim().isEmpty()){
                url = LOGIN_PAGE;
                request.setAttribute("passerr", "Please input the correct type of password");
            }
            else {
                int password = Integer.parseInt(password1);
                UserDTO user = dao.checkLogin(username, password);
                
            if (user != null){
                HttpSession session = request.getSession();
                if (user.getRole()== 1 || user.getRole() == 2){
                    fullName = user.getFullName();
                    session.setAttribute("ADMIN", user);
                    url = SEARCH_PAGE;   
                }
                else if (user.getRole() == 0){
                    session.setAttribute("USER", user);
                    url = USER_SEARCH_PAGE;
                }
            }
            else if (user == null) {
                url = LOGIN_PAGE;
                request.setAttribute("errormessage", "Incorrect Username or Password");
                System.out.println("null");
                }
            }          
        }
        catch (NamingException ex) {
            log ("/n Error at LoginServlet Naming exception: " + ex.getMessage());
        } catch (SQLException ex) {           
            log ("/n Error at LoginServlet SQL Exception : " + ex.getMessage());
        }
        catch (NumberFormatException ex){
            log ("/n Error at LoginServlet Number Format Exception: " + ex.getMessage());
            request.setAttribute(url, ex);
        }
        finally{
            request.setCharacterEncoding("UTF-8");
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
