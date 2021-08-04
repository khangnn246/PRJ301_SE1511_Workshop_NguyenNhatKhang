/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Khangnn.DAO;

import Khangnn.DBUtils.DBHelper;
import Khangnn.DTO.ShoppingCartItems;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author khang
 */
public class CheckOutDAO {
    public boolean checkOrder (String mobileId) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs= null;
        try {
            con = DBHelper.makeConnection();
            if (con != null){
                String sql = "select count (mobileId) as count "
                        + "from tbl_CheckOut "
                        + "where mobileId = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, mobileId);
                rs = ps.executeQuery();
                if (rs.next()){
                    if (rs.getInt("count") > 0){
                        return true;
                    }
                }
            }
        }
        finally{
            if (rs != null){
                rs.close();
            }
            if (ps != null){
                ps.close();
            }
            if (con != null){
                con.close();
            }
        }
        return false;
    }
    public boolean insertOrder (ShoppingCartItems shoppingcart) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null){
                String sql = "Insert into tbl_CheckOut values (?,?,?,?)";
                ps = con.prepareStatement(sql);
                String mobileId = shoppingcart.getMobile().getMobileId();
                String mobileName = shoppingcart.getMobile().getMobileName();
                Float price = shoppingcart.getMobile().getPrice();
                int quantity = shoppingcart.getQuantity();
                ps.setString(1, mobileId);
                ps.setString(2, mobileName);
                ps.setFloat(3, price);
                ps.setInt(4, quantity);
                int row = ps.executeUpdate();
                if (row > 0){
                    return true;
                }
            }
        }
        finally{
            if (ps != null){
                ps.close();
            }
            if (con != null){
                con.close();
            }
        }
        return false;
    }
    public boolean updateOrder (ShoppingCartItems shoppingcart) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null){
                String sql = "update tbl_CheckOut "
                        + "set mobileName = ?, price = ?, quantity = quantity + ? "
                        + "where mobileId = ?";
                ps = con.prepareStatement(sql);
                String mobileId = shoppingcart.getMobile().getMobileId();
                String mobileName = shoppingcart.getMobile().getMobileName();
                Float price = shoppingcart.getMobile().getPrice();
                int quantity = shoppingcart.getQuantity();
                ps.setString(4, mobileId);
                ps.setString(1, mobileName);
                ps.setFloat(2, price);
                ps.setInt(3, quantity);
                int row = ps.executeUpdate();
                if (row > 0){
                    return true;
                }
            }
        }
        finally{
            if (ps != null){
                ps.close();
            }
            if (con != null){
                con.close();
            }
        }
        return false;
    }
}
