<%-- 
    Document   : login
    Created on : Jun 16, 2021, 11:54:18 AM
    Author     : khang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOGIN</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <%
            String errormsg = (String)request.getAttribute("errmsg");
            String passerr = (String)request.getAttribute("passerr");
            String accounterr = (String)request.getAttribute("errormessage");
            if (errormsg != null){
                %>
                <font color="red">
                <%= errormsg %>
                </font>
                <%
            }
            if (accounterr != null){
            %>
            <font color="red">
            <%= accounterr %>
            </font>
            <%
                }
            %>
        <form name="loginform" action="DispatchServlet" method="POST">
            Username: <input type="text" name="txtusername" 
                             <% if (request.getParameter("txtusername")==null){
                                 %>
                                 value=""
                                 <%
                                }
                                else{
                                    %>
                                    value="<%= request.getParameter("txtusername") %>"
                                    <%
                                }
                                %>/><br/>
            Password: <input type="password" name="txtpassword"/><br/>
            <% if (passerr != null){
                %>
                <font color="red">
                <%= passerr %>
                </font> <br/>
                <%
            }
                %>
            <input type="submit" value="Login" name="btn" />
            <input type="reset" value="Reset" />           
        </form>
        <a href="" >Sign Up</a>
    </body>
</html>
