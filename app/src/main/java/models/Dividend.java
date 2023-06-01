/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;

/**
 *
 * @author max
 */
public class Dividend implements Serializable {
    private String company;
    private String sum;
    private String closePeriod;
    private String paymentDate;

    public Dividend(String company, String sum, String closePeriod, String paymentDate) {
        this.company = company;
        this.sum = sum;
        this.closePeriod = closePeriod;
        this.paymentDate = paymentDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getClosePeriod() {
        return closePeriod;
    }

    public void setClosePeriod(String closePeriod) {
        this.closePeriod = closePeriod;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
    
}
