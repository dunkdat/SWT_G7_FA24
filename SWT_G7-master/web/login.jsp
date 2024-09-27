<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url(image/background2.jpg); /* Background image */
                background-size: cover; /* Cover the whole page */
                background-position: center; /* Center the image */
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-container {
            background-color: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            width: 300px;
            text-align: center;
        }
        h2 {
            margin-bottom: 20px;
            color: #333;
        }
        .message {
            color: red;
            margin-bottom: 15px;
        }
        form {
            display: none;
            text-align: left;
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: #555;
        }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #28a745;
            border: none;
            border-radius: 4px;
            color: #fff;
            font-size: 16px;
        }
        button:hover {
            background-color: #218838;
        }
        select {
            width: 100%;
            padding: 8px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
    </style>
    <script>
        function showLoginForm(role) {
            var staffForm = document.getElementById("staffForm");
            var managementForm = document.getElementById("managementForm");

            if (role === "staff") {
                staffForm.style.display = "block";
                managementForm.style.display = "none";
            } else if (role === "manager") {
                staffForm.style.display = "none";
                managementForm.style.display = "block";
            } else {
                staffForm.style.display = "none";
                managementForm.style.display = "none";
            }
        }

        window.onload = function() {
            // Automatically show the correct form if the role is pre-selected
            var role = document.getElementById("role").value;
            if (role) {
                showLoginForm(role);
            }
        }
    </script>
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>
        <div class="message">
            ${message}
        </div>

        <!-- Role Selection -->
        <label>Select Role:</label>
        <select id="role" onchange="showLoginForm(this.value)" required>
            <option value="">Select Role</option>
            <option value="staff">Staff</option>
            <option value="manager">Manager</option>
        </select>

        <!-- Form for Staff -->
        <form id="staffForm" action="login" method="post">
            <label for="staffUsername">Staff Username:</label>
            <input type="text" id="staffUsername" name="acc" required /><br><br>
    
            <label for="staffPassword">Staff Password:</label>
            <input type="password" id="staffPassword" name="pass" required /><br><br>
    
            <button type="submit" name="log" value="staff">Login as Staff</button>
        </form>
    
        <!-- Form for Management -->
        <form id="managementForm" action="login" method="post">
            <label for="managementUsername">Management Username:</label>
            <input type="text" id="managementUsername" name="acc" required /><br><br>
    
            <label for="managementPassword">Management Password:</label>
            <input type="password" id="managementPassword" name="pass" required /><br><br>
    
            <button type="submit" name="log" value="mag">Login as Manager</button>
        </form>
    </div>
</body>
</html>
