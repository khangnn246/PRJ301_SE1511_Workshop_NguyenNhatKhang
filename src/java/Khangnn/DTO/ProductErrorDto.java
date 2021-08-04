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
public class ProductErrorDto implements Serializable{
    private String idError, descriptionError, priceError, nameError, yearError, quantityError, notSaleError;

    public ProductErrorDto() {
    }

    public String getIdError() {
        return idError;
    }

    public void setIdError(String idError) {
        this.idError = idError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getYearError() {
        return yearError;
    }

    public void setYearError(String yearError) {
        this.yearError = yearError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    public String getNotSaleError() {
        return notSaleError;
    }

    public void setNotSaleError(String notSaleError) {
        this.notSaleError = notSaleError;
    }
    
}
