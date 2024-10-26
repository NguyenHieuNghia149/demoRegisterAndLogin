<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Double Slider Login / Registration Form</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
    <link rel="stylesheet" href="./assets/css/style.css">
</head>
<body>
<div class="container" id="container">
    <div class="form-container register-container">
        <form action="do-register" method="post" id="registerForm">
            <h1>Register here.</h1>
            <input type="hidden" name="action" value="registerUser"> <!-- Thiết lập action cho việc đăng ký -->
            <input type="text" name="name" placeholder="Name" value="${customer.firstName}" required>

            <div class="email-container">
                <input type="email" name="email" placeholder="Email" value="${customer.email}" required>
                <button type="button" class="send-code" onclick="sendCode()">Send Code</button>
            </div>

            <input type="password" name="password" placeholder="Password" value="${customer.password}" required>
            <input type="text" name="verifyNumber" placeholder="Enter Code" required> <!-- Trường nhập mã xác nhận -->

            <button type="submit">Register</button>
            <span>or use your account</span>

            <div class="social-container">
                <a href="#" class="social"><i class="lni lni-facebook-fill"></i></a>
                <a href="#" class="social"><i class="lni lni-google"></i></a>
                <a href="#" class="social"><i class="lni lni-linkedin-original"></i></a>
            </div>
            <p class="message">${message}</p>

        </form>
    </div>

    <div class="form-container login-container">
        <form action="do-register" method="post">
            <input type="hidden" name="action" value="checkUser"> <!-- Thiết lập action cho việc kiểm tra đăng nhập -->
            <h1>Login here.</h1>
            <input type="email" name="email" placeholder="Email" value="${customer.email}" required>
            <input type="password" name="password" placeholder="Password" required>
            <div class="content">
                <div class="checkbox">
                    <input type="checkbox" name="rememberMe" id="checkbox">
                    <label for="checkbox">Remember me</label>
                </div>
                <div class="pass-link">
                    <a href="#">Forgot password?</a>
                </div>
            </div>
            <button type="submit">Login</button>
            <span>or use your account</span>
            <div class="social-container">
            </div>

            <p class="message">${message}</p>
        </form>
    </div>

    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-left">
                <h1 class="title">Hello <br> friends</h1>
                <p>If you have an account, login here and have fun.</p>
                <button class="ghost" id="login">Login
                    <i class="lni lni-arrow-left login"></i>
                </button>
            </div>
            <div class="overlay-panel overlay-right">
                <h1 class="title">Start your <br> journey now</h1>
                <p>If you don't have an account yet, join us and start your journey.</p>
                <button class="ghost" id="register">Register
                    <i class="lni lni-arrow-right register"></i>
                </button>
            </div>
            <p>${message}</p>
        </div>
    </div>
</div>

<script src="./js/index.js"></script>
<script src="./js/script.js"></script>
</body>
</html>
