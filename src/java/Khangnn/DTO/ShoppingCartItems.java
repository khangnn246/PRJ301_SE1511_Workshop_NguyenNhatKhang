/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Khangnn.DTO;

import java.io.Serializable;

/**
 *
 * @author khang
 */
public class ShoppingCartItems implements Serializable{
    MobileDTO mobile ;
    int quantity;

    public ShoppingCartItems() {
    }

    public ShoppingCartItems(MobileDTO mobile, int quantity) {
        this.mobile = mobile;
        this.quantity = quantity;
    }

    public MobileDTO getMobile() {
        return mobile;
    }

    public void setMobile(MobileDTO mobile) {
        this.mobile = mobile;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
