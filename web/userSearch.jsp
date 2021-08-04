<%-- 
    Document   : userSearch
    Created on : Jun 18, 2021, 9:32:32 PM
    Author     : khang
--%>

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
        <%
            UserDTO dto = (UserDTO)session.getAttribute("USER");
            String errorType = (String)request.getAttribute("errorTypeInput");
            String errorEmpty = (String)request.getAttribute("errorempty");
            String errorFound = (String)request.getAttribute("errorSearch");
            %>
            <h1>Hello <%= dto.getFullName() %></h1>
            <form action="SignoutServlet" method="POST">
                <input type="submit" value="Sign Out" name="btnsignout" /><br/>
            </form>
            <form action="DispatchServlet" method="POST">
                <%
                    if (errorEmpty != null){
                        %>
                        <font color="red">
                        <%
                            out.print(errorEmpty);
                            %>
                        </font>
                        <%
                    }
                    if (errorType != null){
                        %>
                        <font color="red">
                        <%
                            out.print(errorType);
                            %>
                        </font>
                        <%
                    }
                %>
                <table border="1">
                    <tr>
                    <h1>Search products's price:</h1>
                    </tr>
                    <td>Min Price</td>
                    <td>Max price</td>
                    <tr>
                        <td><input type="text" name="txtminprice" value="0" /></td>
                        <td><input type="text" name="txtmaxprice" value="0" /><br/></td>
                    </tr>
                </table>
                <br/>
                <input type="submit" value="usersearch" name="btn" /><br/>                
            </form>
                <%
                    String minPrice = request.getParameter("txtminprice");
                    String maxPrice = request.getParameter("txtmaxprice");
                    if (errorEmpty == null && errorType == null && minPrice != "0" && maxPrice != "0"){
                        List<MobileDTO> list = (List<MobileDTO>)request.getAttribute("SEARCH_RESULT");
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
                                    <th>Add to Cart</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int count = 0;
                                        for (MobileDTO mobile : list){
                                            String urlRewriting = "DispatchServlet"
                                                    + "?btn=Add To Cart"
                                                    + "&id="+mobile.getMobileId()
                                                    + "&txtlastminsearch="+minPrice
                                                    + "&txtlastmaxsearch="+maxPrice;
                                            %>
                                <form action="DispatchServlet">
                                    <tr>
                                        <td>
                                            <%= count++ %>
                                        </td>
                                        <td>
                                            <%= mobile.getMobileId() %>
                                        </td>
                                        <td>
                                            <%=  mobile.getDescription() %>
                                        </td>
                                        <td>
                                            <%= mobile.getPrice() %>
                                        </td>
                                        <td>
                                            <%= mobile.getMobileName() %>
                                        </td>
                                        <td>
                                            <%= mobile.getYearOfProduction() %>
                                        </td>
                                        <td>
                                            <%= mobile.getQuantity() %>
                                        </td>
                                        <td>
                                            <input type="checkbox" name="chkSale" value="String" readonly="true"
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
                                            
                                            <a href="<%= urlRewriting %>">Add To Cart</a>
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
else  {
                    %>
                    <h2>
                        No record is matched !!!
                    </h2>
                    <%
            
                        }               
                    }
                    %>
                    <a href="DispatchServlet?&btn=ViewYourCart">View Your Cart</a>
    </body>
</html>
