// package com.reddy.smartatm.controller;

// import java.io.ByteArrayInputStream;

// import org.springframework.core.io.InputStreamResource;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;

// import com.reddy.smartatm.entity.Account;
// import com.reddy.smartatm.entity.User;
// import com.reddy.smartatm.service.AccountService;
// import com.reddy.smartatm.service.PdfService;
// import com.reddy.smartatm.service.TransactionService;

// import jakarta.servlet.http.HttpSession;

// @Controller
// public class PdfController {

//     private final PdfService pdfService;
//     private final AccountService accountService;
//     private final TransactionService transactionService;

//     public PdfController(PdfService pdfService,
//                          AccountService accountService,
//                          TransactionService transactionService) {

//         this.pdfService = pdfService;
//         this.accountService = accountService;
//         this.transactionService = transactionService;
//     }

//     @GetMapping("/statement/pdf")
//     public ResponseEntity<InputStreamResource> downloadStatement(
//             HttpSession session) {

//         User user = (User) session.getAttribute("loggedInUser");

//         if (user == null) {
//             return ResponseEntity.badRequest().build();
//         }

//         Account account = accountService.findByUser(user);

//         ByteArrayInputStream pdf = pdfService.generateStatement(
//                 account,
//                 transactionService.getTransactions(account));

//         HttpHeaders headers = new HttpHeaders();

//         headers.add(HttpHeaders.CONTENT_DISPOSITION,
//                 "attachment; filename=MiniStatement.pdf");

//         return ResponseEntity.ok()
//                 .headers(headers)
//                 .contentType(MediaType.APPLICATION_PDF)
//                 .body(new InputStreamResource(pdf));
//     }
// }