package com.reddy.smartatm.service.impl;

import org.springframework.stereotype.Service;

import com.reddy.smartatm.dto.DashboardAnalyticsDTO;
import com.reddy.smartatm.entity.Account;
import com.reddy.smartatm.repository.TransactionRepository;
import com.reddy.smartatm.service.DashboardAnalyticsService;


@Service
public class DashboardAnalyticsServiceImpl 
        implements DashboardAnalyticsService {


    private final TransactionRepository transactionRepository;


    public DashboardAnalyticsServiceImpl(
            TransactionRepository transactionRepository) {

        this.transactionRepository = transactionRepository;
    }



    @Override
    public DashboardAnalyticsDTO getAnalytics(Account account) {


        DashboardAnalyticsDTO dto =
                new DashboardAnalyticsDTO();


        dto.setTotalDeposit(
            transactionRepository.getTotalDeposit(account)
        );


        dto.setTotalWithdrawal(
            transactionRepository.getTotalWithdrawal(account)
        );


        dto.setTotalTransfer(
            transactionRepository.getTotalTransfer(account)
        );


        return dto;
    }

}