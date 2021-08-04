<%-- 
    Document   : search
    Created on : Jun 14, 2021, 1:39:49 PM
    Author     : khang
--%>

<%@page import="Khangnn.DTO.ProductErrorDto"%>
<%@page import="Khangnn.DTO.MobileDTO"%>
<%@page import="java.util.List"%>
<%@page import="Khangnn.DTO.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <% UserDTO dto =(UserDTO) session.getAttribute("ADMIN"); 
            String updateId = (String)request.getAttribute("mobileId");
            ProductErrorDto error = (ProductErrorDto)request.getAttribute("errorobj");
        %>
        <h1>
            Hello , <%= dto.getFullName() %>           
        </h1>
            
            <form action="SignoutServlet">
                <input type="submit" value="Sign Out" name="btn" />
            </form><br/>
            <a href="AddNewMobile.jsp">Add New Mobile</a>
            <h1> Search Page </h1>
            <form action="DispatchServlet">
                Search : <input type="text" name="txtsearch" 
                                <%
                                    if (request.getParameter("txtsearch") == null){
                                        %>
                                        value=""
                                        <%
                                    }
                                    else {
                                        %>
                                        value="<%= request.getParameter("txtsearch") %>"
                                        <%
                                    } 
                                %> /><br/>
                
                <input type="submit" value="Search" name="btn" /><br/>
            </form>
            <br/>
            <%
                String searchValue = request.getParameter("txtsearch");
                if (searchValue != null){
                    List<MobileDTO> list =(List<MobileDTO>) request.getAttribute("SEARCH_RESULT");
                    if (list != null){
                        %>
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Mobile Id</th>
                                    <th>Descriptions</th>
                                    <th>price</th>
                                    <th>mobileName</th>
                                    <th>yearOfProduction</th>
                                    <th>quantity</th>
                                    <th>Sale</th>
                                    <th>Delete</th>
                                    <th>Update</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                int count = 0 ;
                                boolean match = false;
                                for (MobileDTO mobile : list) {
                                    String urlRewriting = "DispatchServlet"
                                            + "?btn=delete"
                                            + "&key="+mobile.getMobileId()
                                            +"&lastSearchValue="+searchValue;
                                        if (mobile.getMobileId() == updateId){
                                            match = true;
                                        }
                                    %>
                            <form action="DispatchServlet">
                                <tr>
                                        <td>
                                            <%= count++ %>
                                        </td>
                                        <td>
                                            <%= mobile.getMobileId()%>
                                            <input type="hidden" name="txtmobileid" value="<%= mobile.getMobileId() %>" />
                                        </td>
                                        <td>
                                            <input type="text" name="txtdescription" value="<%= mobile.getDescription() %>" />
                                        </td>
                                        <td>
                                            <input type="text" name="txtprice" value="<%= mobile.getPrice() %>" />
                                            <font color="red">
                                            <% if (error != null && match == true){
                                                out.print(error.getPriceError());
                                            }
                                                %>
                                            </font>
                                        </td>
                                        <td>
                                            <input type="text" name="txtmobilename" value="<%= mobile.getMobileName() %>" />
                                        </td>
                                        <td>
                                            <input type="text" name="txtyear" value="<%= mobile.getYearOfProduction() %>" />
                                            <font color="red">
                                            <% if (error != null && match == true){
                                                out.print(error.getYearError());
                                            }
                                                %>
                                            </font>
                                        </td>
                                        <td>
                                            <input type="text" name="txtquantity" value="<%= mobile.getQuantity() %>" />
                                            <font color="red">
                                            <% if (error != null && match == true){
                                                out.print(error.getQuantityError());
                                            }
                                                %>
                                            </font>
                                        </td>
                                        <td>
                                        <input type="checkbox" name="chkSale" value="String" 
                                               <%
                                                   if (mobile.isNotSale() == true){
                                                       %>
                                                       checked="checked"
                                                       <%
                                                   }
                                               %>
                                               />
                                        </td>
                                        <td>
                                            <a href="<%= urlRewriting %>">Delete</a>
                                        </td>
                                        <td>
                                            <input type="hidden" name="txtLastSearchValue" value="<%= searchValue %>" />
                                            
                                            <input type="submit" value="Update" name="btn" />
                                        </td>
                                </tr>
                                </form>                              
                                    <%
                                }
                                %>
                                
                            </tbody>
                        </table>

                        <%
                    }
                    else {
                        %>
                        <h2>
                            No record is matched !!!
                        </h2>
                        <%
}
                } 
                
            %>
            
    </body>
</html>
