<%-- 
    Document   : AddNewMobile
    Created on : Jun 25, 2021, 10:29:33 PM
    Author     : khang
--%>

<%@page import="Khangnn.DTO.ProductErrorDto"%>
<%@page import="Khangnn.DTO.MobileDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Mobile</title>
    </head>
    <body>
        <%
//            MobileDTO dto = (MobileDTO)request.getAttribute("computerobj");
            ProductErrorDto errorobj = (ProductErrorDto)request.getAttribute("INVALID");
            %>
        <h1>Add A New Mobile Information</h1>
        <form action="DispatchServlet" method="POST">
            <table>
                <tr>
                    <td>
                        Mobile Id : <input type="text" name="txtMobileId" 
                                           value="" /> 
                        <font color="red">
                        <%
                            if (errorobj != null){
                                out.print(errorobj.getIdError());
                            }
                            %>
                        </font>
                    </td>
                </tr>
                <tr>
                    <td>
                        Mobile Description: <input type="text" name="txtMobileDescription" value="" /> 
                        <font color="red">
                        <% if (errorobj != null){
                            out.print(errorobj.getDescriptionError());
                        }
                            %>
                            </font>
                    </td>
                </tr>
                <tr>
                    <td>
                        Mobile Price : <input type="text" name="txtMobilePrice" value="" />
                        <font color="red">
                        <%
                            if (errorobj != null){
                                out.print(errorobj.getPriceError());
                            }
                            %>
                        </font>
                    </td>
                </tr>
                <tr>
                    <td>
                        Mobile Name : <input type="text" name="txtMobileName" value="" />
                        <font color="red">
                        <%
                            if (errorobj != null){
                                out.print(errorobj.getNameError());
                            }
                            %>
                        </font>
                    </td>
                </tr>
                <tr>
                    <td>
                        Year : <input type="text" name="txtYear" value="" /> 
                        <font color="red">
                        <%
                            if (errorobj != null){
                                out.print(errorobj.getYearError());
                            }
                            %>
                        </font>
                    </td>
                </tr>
                <tr>
                    <td>
                        Quantity : <input type="text" name="txtMobileQuantity" value="" />
                    
                    <font color="red">
                        <%
                            if (errorobj != null){
                                out.print(errorobj.getQuantityError());
                            }
                            %>
                        </font>
                        </td>
                </tr>
                <tr>
                    <td>
                        Sale : 
                        <input type="checkbox" name="chkSale" value="ON" />
                    </td>
                </tr>
            </table>
                        <input type="submit" value="Add New Mobile" name="btn" />    
        </form>    
    </body>
</html>
