<%-- 
    Document   : viewcart
    Created on : Jun 23, 2021, 8:13:06 PM
    Author     : khang
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Khangnn.DTO.ShoppingCartItems"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
    </head>
    <body>
        <h1>Your Cart</h1>
                <%
                    List<ShoppingCartItems> shoppinglist = new ArrayList<>();
                    shoppinglist = (List<ShoppingCartItems>)session.getAttribute("cart");
                    float total = 0;
                    if (shoppinglist != null){
                        %>
        <table border="1">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Name</th>
                    <th>Year</th>
                    <th>Quantity</th>
                    <th>notSale</th>
                </tr>
            </thead>
            <tbody>
                
                        <%
                        for (ShoppingCartItems cart : shoppinglist){
                            total = total + (cart.getMobile().getPrice() * cart.getQuantity());
                            %>
                        <tr>
                            <td>
                                <%= cart.getMobile().getMobileId() %>
                            </td>
                            <td>
                                <%= cart.getMobile().getDescription()%>
                            </td>
                            <td>
                                <%= cart.getMobile().getPrice() %>
                            </td>
                            <td>
                                <%= cart.getMobile().getMobileName() %>
                            </td>
                            <td>
                                <%= cart.getMobile().getYearOfProduction() %>
                            </td>
                            <td>
                                <%= cart.getQuantity() %>
                            </td>
                            <td>
                                <input type="checkbox" name="chkSale" value="String" readonly="true"
                                               <%
                                                   if (cart.getMobile().isNotSale() == true){
                                                       %>
                                                       checked="checked"
                                                       <%
                                                   }
                                               %>
                                               />            
                            </td>
                            <td>
                                <a href="DispatchServlet?btn=removeitems&id=<%= cart.getMobile().getMobileId() %>">Remove</a>
                            </td>

                        </tr>
            </tbody>
            
                            <% 
                        }                     
%>
<form action="DispatchServlet">
    <tr>
        <td>
            <input type="submit" name="btn" value="Check Out"/>
        </td>
    </tr>
</form>
        </table>
<%
                    }
else if (shoppinglist == null){
                %>
                <h2>
                    Your cart is empty
                </h2>
                <%
            }
            %>
        <a href="DispatchServlet?btn=returnsearchpage">Click here to return search page</a>
    </body>
</html>
