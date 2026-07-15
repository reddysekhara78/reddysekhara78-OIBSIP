package com.reddy.smartatm.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.reddy.smartatm.entity.Account;
import com.reddy.smartatm.entity.Transaction;

public interface PdfService {

    ByteArrayInputStream generateStatement(
            Account account,
            List<Transaction> transactions);

}