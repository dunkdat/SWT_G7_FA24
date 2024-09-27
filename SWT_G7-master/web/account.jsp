
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url(image/background2.jpg);
            background-size: cover;
            background-position: center;
            margin: 0;
            padding: 20px;
            text-align: center;
            display: flex; /* Enable flexbox */
            justify-content: center; /* Center horizontally */
            align-items: center; /* Center vertically */
            height: 100vh; /* Full viewport height */
        }

        .container {
            width: 80%; /* Adjust width as needed */
            max-width: 800px;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            text-align: left;
            display: flex;
        }

        h2 {
            color: #333;
        }

        .account-info {
            margin-bottom: 20px;
        }

        .password-label {
            display: block;
            margin-bottom: 10px;
        }

        .password-input {
            width: 200px;
            padding: 5px;
            font-size: 16px;
        }

        .change-password-btn {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            cursor: pointer;
            margin-bottom: 10px;
        }

        .logout-form {
            margin-bottom: 20px;
        }

        .message {
            margin-top: 10px;
            color: #f44336;
            font-weight: bold;
        }

        .change-password-form {
            display: none;
            margin-top: 20px;
        }

        .form-label {
            display: block;
            margin-bottom: 10px;
        }

        .form-input {
            width: 200px;
            padding: 5px;
            font-size: 16px;
        }

        .submit-btn {
            background-color: #008CBA;
            color: white;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            cursor: pointer;
        }

        .invisible-input {
            background-color: transparent; /* Make background transparent */
            border: none; /* Remove border */
            outline: none; /* Remove default focus outline */
        }
         a {
    color: orange; /* Màu ch? là màu cam */
    text-decoration: none; /* Không g?ch chân cho liên k?t */
    padding: 10px 20px; /* Kho?ng cách gi?a các liên k?t */
    background-color: rgba(255, 255, 255, 0.6); /* Màu n?n h?i trong su?t */
    border-radius: 4px; /* Bo tròn góc */
    margin-right: 10px; /* Kho?ng cách phía bên ph?i */
}

a:hover {
    background-color: rgba(255, 255, 255, 0.8); /* Màu n?n h?i sáng h?n khi hover */
}
    </style>
    <script>
        function toggleChangePassForm() {
            var form = document.getElementById("changePass");
            if (form.style.display === 'none' || form.style.display === '') {
                form.style.display = 'block';
            } else {
                form.style.display = 'none';
            }
        }
    </script>
</head>
<body>
    
    <div class="container">
        
        <div>
            <div>
                    <a href="${param.role == 'mag' ? 'mag' : 'staff'}">Main Page</a>
                    <a href="acc?id=${param.id}&role=${param.role}">Account Manage</a>
            
        
            </div>
        <h2>Account Management</h2>
        <div class="account-info">
            <label>Account: ${account}</label>
            <br>
            <div>
                <label class="password-label">Password:<input type="password" value="${password}" class="password-input invisible-input" readonly/></label>
                
            </div>
        </div>

        <button type="button" class="change-password-btn" onclick="toggleChangePassForm()">Change Password</button>
        
        <form action="logout" method="post" class="logout-form">
            <button type="submit" class="submit-btn">Logout</button>
        </form>
        
        <div class="message">${message}</div>
        
        <form id="changePass" action="acc" method="post" class="change-password-form" style="${empty dp ? 'display: none;' : 'display: block;'}">
            <input type="hidden" name="role" value="${param.role}">
            <input type="hidden" name="id" value="${id}">
            
            <label class="form-label">Old Password:</label>
            <input type="password" name="oldPass" value="" class="form-input" required/>
            
            <label class="form-label">New Password:</label>
            <input type="password" name="newPass" value="" class="form-input" required/>
            
            <button type="submit" class="submit-btn">Submit</button>
        </form>
         </div>   
            <div style="flex: 1;text-align: center"><img src="image/user.png" alt="alt" width="300px"/></div>
    </div>
</body>
</html>
