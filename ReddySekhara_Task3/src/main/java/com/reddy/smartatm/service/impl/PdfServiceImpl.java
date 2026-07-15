package com.reddy.smartatm.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.reddy.smartatm.entity.Account;
import com.reddy.smartatm.entity.Transaction;
import com.reddy.smartatm.service.PdfService;

@Service
public class PdfServiceImpl implements PdfService {

    @Override
    public ByteArrayInputStream generateStatement(
            Account account,
            List<Transaction> transactions) {

        Document document = new Document();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);

            document.open();

            Font title = FontFactory.getFont(
        FontFactory.HELVETICA_BOLD,
        18);
            document.add(new Paragraph(
                    "SMART ATM MINI STATEMENT",
                    title));

            document.add(new Paragraph(" "));

            document.add(new Paragraph(
                    "Account Holder : "
                            + account.getUser().getFullName()));

            document.add(new Paragraph(
                    "Account Number : "
                            + account.getAccountNumber()));

            document.add(new Paragraph(
                    "Account Type : "
                            + account.getAccountType()));

            document.add(new Paragraph(
                    "Branch : "
                            + account.getBranch()));

            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(4);

            table.setWidthPercentage(100);

            table.addCell("Date");
            table.addCell("Type");
            table.addCell("Amount");
            table.addCell("Status");

            for (Transaction tx : transactions) {

                table.addCell(
                        tx.getTransactionDate()
                                .toLocalDate()
                                .toString());

                table.addCell(
                        tx.getTransactionType()
                                .toString());

                table.addCell(
                        "₹" + tx.getAmount());

                table.addCell(
                        tx.getStatus()
                                .toString());
            }

            document.add(table);

            document.add(new Paragraph(" "));

            document.add(new Paragraph(
                    "Current Balance : ₹"
                            + account.getBalance()));

            document.add(new Paragraph(" "));

            document.add(new Paragraph(
                    "Thank You For Banking With Us"));

            document.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}