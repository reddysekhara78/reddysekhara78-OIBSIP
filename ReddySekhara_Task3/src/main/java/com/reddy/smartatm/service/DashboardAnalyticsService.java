package com.reddy.smartatm.service;

import com.reddy.smartatm.dto.DashboardAnalyticsDTO;
import com.reddy.smartatm.entity.Account;

public interface DashboardAnalyticsService {

    DashboardAnalyticsDTO getAnalytics(Account account);

}