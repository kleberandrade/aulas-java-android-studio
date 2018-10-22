package br.fatec.aula.interestcalculatorapp.models;

import java.io.Serializable;
import java.util.Date;

public class Bill implements Serializable {

    public static final String EXTRA_BILL = "EXTRA_BILL";

    private double value;

    private Date dueDate;

    private Date paymentDate;

    private Double interest;

    private Double fine;

    private boolean isInterestCurrency;

    private boolean isInterestDay;

    private boolean isFineCurrency;

    public Bill(){

    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Double getFine() {
        return fine;
    }

    public void setFine(Double fine) {
        this.fine = fine;
    }

    public boolean isInterestCurrency() {
        return isInterestCurrency;
    }

    public void setInterestCurrency(boolean interestCurrency) {
        isInterestCurrency = interestCurrency;
    }

    public boolean isInterestDay() {
        return isInterestDay;
    }

    public void setInterestDay(boolean interestDay) {
        isInterestDay = interestDay;
    }

    public boolean isFineCurrency() {
        return isFineCurrency;
    }

    public void setFineCurrency(boolean fineCurrency) {
        isFineCurrency = fineCurrency;
    }
}
