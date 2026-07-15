package com.reddy.smartatm.controller;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import com.reddy.smartatm.entity.Account;
import com.reddy.smartatm.entity.Transaction;
import com.reddy.smartatm.entity.User;
import com.reddy.smartatm.service.AccountService;
import com.reddy.smartatm.service.TransactionService;

@Controller
public class StatementController {

    private final TransactionService transactionService;
    private final AccountService accountService;

    public StatementController(TransactionService transactionService,
                               AccountService accountService) {

        this.transactionService = transactionService;
        this.accountService = accountService;
    }

    @GetMapping("/statement")
    public String statement(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        Account account = accountService.findByUser(user);

        model.addAttribute("transactions",
                transactionService.getTransactions(account));

        model.addAttribute("account", account);

        return "statement";
    }

    @GetMapping("/statement/pdf")
    public void downloadPdf(HttpSession session,
                            HttpServletResponse response)
            throws IOException {

        User user =
                (User) session.getAttribute("loggedInUser");

        if (user == null) {

            response.sendRedirect("/login");

            return;
        }

        Account account =
                accountService.findByUser(user);

        List<Transaction> transactions =
                transactionService.getTransactions(account);

        response.setContentType("application/pdf");

        response.setHeader(
                "Content-Disposition",
                "attachment; filename=MiniStatement.pdf");

        Document document = new Document();

        PdfWriter.getInstance(
                document,
                response.getOutputStream());

        document.open();
            // ---------------- Title ----------------

Font titleFont = FontFactory.getFont(
        FontFactory.HELVETICA_BOLD,
        20,
        Color.BLUE);

Paragraph title =
        new Paragraph("SMART ATM - MINI STATEMENT", titleFont);

title.setAlignment(Element.ALIGN_CENTER);

document.add(title);

document.add(new Paragraph(" "));


// ---------------- Account Details ----------------

Font normalFont =
        FontFactory.getFont(FontFactory.HELVETICA, 12);

document.add(new Paragraph(
        "Account Number : " + account.getAccountNumber(),
        normalFont));

document.add(new Paragraph(
        "Customer Name : " + user.getFullName(),
        normalFont));

document.add(new Paragraph(
        "Current Balance : ₹" + account.getBalance(),
        normalFont));

document.add(new Paragraph(" "));


// ---------------- Table ----------------

PdfPTable table = new PdfPTable(5);

table.setWidthPercentage(100);

table.setSpacingBefore(10);

table.setWidths(new float[]{2f,2f,2f,4f,3f});


// Header

PdfPCell cell;

cell = new PdfPCell(new Phrase("Type"));
cell.setBackgroundColor(Color.LIGHT_GRAY);
table.addCell(cell);

cell = new PdfPCell(new Phrase("Amount"));
cell.setBackgroundColor(Color.LIGHT_GRAY);
table.addCell(cell);

cell = new PdfPCell(new Phrase("Status"));
cell.setBackgroundColor(Color.LIGHT_GRAY);
table.addCell(cell);

cell = new PdfPCell(new Phrase("Description"));
cell.setBackgroundColor(Color.LIGHT_GRAY);
table.addCell(cell);

cell = new PdfPCell(new Phrase("Date"));
cell.setBackgroundColor(Color.LIGHT_GRAY);
table.addCell(cell);


// ---------------- Data ----------------

for (Transaction t : transactions) {

    table.addCell(
            t.getTransactionType().toString());

    table.addCell(
            "₹" + t.getAmount());

    table.addCell(
            t.getStatus().toString());

    table.addCell(
            t.getDescription() == null
                    ? "-"
                    : t.getDescription());

    table.addCell(
            t.getTransactionDate().toString());

}

document.add(table);

document.add(new Paragraph(" "));

document.add(
        new Paragraph(
                "Total Transactions : "
                        + transactions.size(),
                normalFont));

document.close();
        }
    }

    