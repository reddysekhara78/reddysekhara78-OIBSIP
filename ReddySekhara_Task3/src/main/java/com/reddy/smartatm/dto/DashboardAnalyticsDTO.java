
package com.reddy.smartatm.dto;

import java.math.BigDecimal;
import java.util.List;

public class DashboardAnalyticsDTO {


    private BigDecimal totalDeposit;

    private BigDecimal totalWithdrawal;

    private BigDecimal totalTransfer;


    private List<Object[]> monthlyTransactions;



    public DashboardAnalyticsDTO() {
    }



    public DashboardAnalyticsDTO(
            BigDecimal totalDeposit,
            BigDecimal totalWithdrawal,
            BigDecimal totalTransfer,
            List<Object[]> monthlyTransactions) {


        this.totalDeposit = totalDeposit;

        this.totalWithdrawal = totalWithdrawal;

        this.totalTransfer = totalTransfer;

        this.monthlyTransactions = monthlyTransactions;

    }



    public BigDecimal getTotalDeposit() {
        return totalDeposit;
    }


    public void setTotalDeposit(BigDecimal totalDeposit) {
        this.totalDeposit = totalDeposit;
    }



    public BigDecimal getTotalWithdrawal() {
        return totalWithdrawal;
    }


    public void setTotalWithdrawal(BigDecimal totalWithdrawal) {
        this.totalWithdrawal = totalWithdrawal;
    }



    public BigDecimal getTotalTransfer() {
        return totalTransfer;
    }


    public void setTotalTransfer(BigDecimal totalTransfer) {
        this.totalTransfer = totalTransfer;
    }



    public List<Object[]> getMonthlyTransactions() {
        return monthlyTransactions;
    }


    public void setMonthlyTransactions(List<Object[]> monthlyTransactions) {
        this.monthlyTransactions = monthlyTransactions;
    }

}