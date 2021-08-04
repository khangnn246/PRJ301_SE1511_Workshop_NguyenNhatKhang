/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Khangnn.DAO;

import Khangnn.DBUtils.DBHelper;
import Khangnn.DTO.MobileDTO;
import Khangnn.DTO.ShoppingCartItems;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author khang
 */
public class MobileDAO {
    public ArrayList<MobileDTO> getAllMobile () throws SQLException, NamingException{
        Connection con = null ;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<MobileDTO> list = new ArrayList<>();
        String sql = "select mobileId,descriptions,price,mobileName,yearOfProduction,quantity, notSale "
                + "from tbl_Mobile ";
        try {
            con = DBHelper.makeConnection();
            if (con != null){
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()){
                    String id = rs.getString("mobileId");
                    String description = rs.getString("descriptions");
                    float price = rs.getFloat("price");
                    String name = rs.getString("mobileName");
                    int year = rs.getInt("yearOfProduction");
                    int quantity = rs.getInt("quantity");
                    boolean notSale = rs.getBoolean("notSale");
                    MobileDTO dto = new MobileDTO(id, description, price, name, year, quantity, notSale);
                    list.add(dto);
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
        return list;
    }
    private List<MobileDTO> mobileList ;
    public List<MobileDTO> getMobileList (){
        return mobileList;
    }
    public MobileDTO getMobileById (String id) throws NamingException , SQLException{
        Connection con = null ;
        PreparedStatement ps = null;
        ResultSet rs = null;
            String sql = "select mobileId,descriptions,price,mobileName,yearOfProduction,quantity, notSale "
                    + "from tbl_Mobile "
                    + "where mobileId =?";
        try {
            con = DBHelper.makeConnection();
            if (con != null){
                ps = con.prepareStatement(sql);
                ps.setString(1,id);
                rs = ps.executeQuery();
                if (rs.next()){
                    String mobileId = rs.getString("mobileId");
                    String description = rs.getString("descriptions");
                    float price = rs.getFloat("price");
                    String name = rs.getString("mobileName");
                    int year = rs.getInt("yearOfProduction");
                    int quantity = rs.getInt("quantity");
                    boolean notSale = rs.getBoolean("notSale");
                    MobileDTO dto = new MobileDTO(mobileId, description, price, name, year, quantity, notSale);
                    return dto;
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
        return null;
    }
    public void getMobileByName (String mobileName) throws NamingException , SQLException{
        Connection con = null ;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select mobileId,descriptions,price,mobileName,yearOfProduction,quantity, notSale "
                + "from tbl_Mobile "
                + "where mobileName like ?";
        try {
            con = DBHelper.makeConnection();
            if (con != null){
                ps = con.prepareStatement(sql);
                ps.setString(1, "%"+mobileName+"%");
                rs = ps.executeQuery();
                while (rs.next()){
                    String mobileId = rs.getString("mobileId");
                    String description = rs.getString("descriptions");
                    float price = rs.getFloat("price");
                    String name = rs.getString("mobileName");
                    int year = rs.getInt("yearOfProduction");
                    int quantity = rs.getInt("quantity");
                    boolean notSale = rs.getBoolean("notSale");
                    MobileDTO dto = new MobileDTO(mobileId, description, price, name, year, quantity, notSale);
                    if (mobileList == null){
                        mobileList = new ArrayList<>();
                    }
                    this.mobileList.add(dto);
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
    }
    public boolean deleteMobileById (String id) throws NamingException, SQLException{
        Connection con = null ;
        PreparedStatement ps = null;
        String sql = "delete from tbl_Mobile "
                + "where mobileId = ?";
        try {
            con = DBHelper.makeConnection();
            if (con != null){
                ps = con.prepareStatement(sql);
                ps.setString(1, id);
                ps.executeUpdate();
                return true;
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
    public boolean createMobile (MobileDTO mobile) throws NamingException, SQLException{
        Connection con = null ;
        PreparedStatement ps = null;
        String sql = "insert into tbl_Mobile "
                + "values (?,?,?,?,?,?,?)";
        try {
            con = DBHelper.makeConnection();
            if (con != null){
                ps = con.prepareStatement(sql);
                ps.setString(1, mobile.getMobileId());
                ps.setString(2, mobile.getDescription());
                ps.setFloat(3, mobile.getPrice());
                ps.setString(4, mobile.getMobileName());
                ps.setInt(5, mobile.getYearOfProduction());
                ps.setInt(6, mobile.getQuantity());
                ps.setBoolean(7, mobile.isNotSale());
                ps.executeUpdate();
                return true;
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
    public boolean updateMobile (MobileDTO mobile) throws NamingException, SQLException{
        Connection con = null ;
        PreparedStatement ps = null;
        String sql = "update tbl_Mobile "
                + "set descriptions = ? , price = ?, mobileName = ?, yearOfProduction = ?, quantity = ?, notSale = ? "
                + "where mobileId = ? ";
        try {
            con = DBHelper.makeConnection();
            if (con != null){
                ps = con.prepareStatement(sql);
                ps.setString(7, mobile.getMobileId());
                ps.setString(1, mobile.getDescription());
                ps.setFloat(2, mobile.getPrice());
                ps.setString(3, mobile.getMobileName());
                ps.setInt(4, mobile.getYearOfProduction());
                ps.setInt(5, mobile.getQuantity());
                ps.setBoolean(6, mobile.isNotSale());
                if (ps.executeUpdate() > 0){
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
    public boolean getMobileWithPriceInRange (int minPrice, int maxPrice ) throws NamingException, SQLException{
        Connection con = null ;
        PreparedStatement ps = null;
        ResultSet rs = null ;
        String sql = "select mobileId,descriptions,price,mobileName,yearOfProduction,quantity,notSale "
                + "from tbl_Mobile "
                + "where price < ? and price > ?";
        try {
            con = DBHelper.makeConnection();
            if (con != null){
                ps = con.prepareStatement(sql);
                ps.setInt(1, maxPrice);
                ps.setInt(2, minPrice);
                rs = ps.executeQuery();
                while (rs.next()){
                    String id = rs.getString("mobileId");
                    String description = rs.getString("descriptions");
                    float price = rs.getFloat("price");
                    String name = rs.getString("mobileName");
                    int year = rs.getInt("yearOfProduction");
                    int quantity = rs.getInt("quantity");
                    boolean notSale = rs.getBoolean("notSale");
                    MobileDTO dto = new MobileDTO(id, description, price, name, year, quantity, notSale);
                    if (this.mobileList == null){
                        this.mobileList = new ArrayList<>();
                    }
                    mobileList.add(dto);
                }
                return true;
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
    public MobileDTO find (String id){
        for (MobileDTO mobileDTO : this.mobileList) {
            if (mobileDTO.getMobileId().equalsIgnoreCase(id)){
                return mobileDTO;
            }
        }
        return null;
    }
    public int isExisting (String id, List<ShoppingCartItems> cart){
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getMobile().getMobileId().equalsIgnoreCase(id)){
                return i;
            }
        }
        return -1;
    }
}
