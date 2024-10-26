package controller;

import java.io.*;
import java.util.Date;
import java.util.Random;
import jakarta.mail.MessagingException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.*;
import data.CustomerDB;
import util.*;

public class RegisterServlet extends HttpServlet {
    private static final String REGISTER_PAGE = "/register.jsp";
    private static final String INDEX_PAGE = "/index.jsp";
    private static final String FROM_EMAIL = "hieunghia484@gmail.com"; // Địa chỉ email hợp lệ

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "join"; // Hành động mặc định
        }

        switch (action) {
            case "registerUser":
                handleRegister(request, response);
                break;
            case "checkUser":
                handleLogin(request, response);
                break;
            case "sendCode":
                sendVerificationCode(request, response);
                break;
            default:
                forwardToPage(REGISTER_PAGE, request, response);
                break;
        }
    }

    private void sendVerificationCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        String email = request.getParameter("email");
        String firstName = request.getParameter("name");
        int verificationCode = generateVerificationCode();

        // Lưu mã xác minh trong session
        HttpSession session = request.getSession();
        session.setAttribute("verificationCode", verificationCode);

        String subject = "Welcome to our email list";
        String body = String.format("Dear %s,\n\nThanks for joining our email list. Your verification code is: %d\nHave a great day!", firstName, verificationCode);

        try {
            MailUtilGmail.sendMail(email, FROM_EMAIL, subject, body, false);
            response.getWriter().write("Verification code sent to your email."); // Phản hồi thành công
        } catch (MessagingException e) {
            response.getWriter().write("ERROR: Unable to send email."); // Phản hồi lỗi
            log("Unable to send email: " + e.getMessage());
        }
    }

    private int generateVerificationCode() {
        Random random = new Random();
        return 100000 + random.nextInt(900000); // Tạo mã xác minh 6 chữ số
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String message;

        if (CustomerDB.emailExists(email) && CustomerDB.verifyPassword(password, email)) {
            forwardToPage(INDEX_PAGE, request, response);
        } else {
            message = "Invalid email or password";
            request.setAttribute("message", message);
            forwardToPage(REGISTER_PAGE, request, response);
        }
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response) {
        String firstName = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String verifyNumber = request.getParameter("verifyNumber");
        HttpSession session = request.getSession();
        Integer sessionCode = (Integer) session.getAttribute("verificationCode");
        String message;

        // Kiểm tra mã xác minh
        if (sessionCode == null || !sessionCode.toString().equals(verifyNumber)) {
            message = "Invalid verification code. Please try again.";
            return;
        }

        // Tiến hành đăng ký nếu mã hợp lệ
        Customer user = new Customer(firstName, "lastname", email, password, "ACTIVE", "", new Date(), new Date());
        if (CustomerDB.emailExists(user.getEmail())) {
            message = "This email address already exists. Please enter another email address.";
        } else {
            message = "Registration successful! You can now log in.";
            CustomerDB.insert(user); // Thêm người dùng mới vào cơ sở dữ liệu
        }
        request.setAttribute("customer", user);
        request.setAttribute("message", message);
        forwardToPage(REGISTER_PAGE, request, response);
    }

    private void forwardToPage(String page, HttpServletRequest request, HttpServletResponse response) {
        try {
            getServletContext().getRequestDispatcher(page).forward(request, response);
        } catch (ServletException | IOException e) {
            log("Error forwarding to page: " + e.getMessage());
        }
    }
}
