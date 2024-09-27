<%-- 
    Document   : fix
    Created on : Jun 12, 2024, 9:30:18 PM
    Author     : DAT
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Fix Task</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url(image/background2.jpg); /* Background image */
            background-size: cover; /* Cover the whole page */
            background-position: center; /* Center the image */
            margin: 0;
            padding: 20px;
        }
        h1 {
            text-align: center;
            color: orange;
        }
        .container {
            margin: 0 auto;
            max-width: 600px;
            background-color: rgba(255, 255, 255, 0.8); /* Slightly transparent background */
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.5);
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-bottom: 5px;
            color: #555;
        }
        input[type="text"],
        input[type="date"],
        textarea {
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            width: 100%; /* Take up full width of container */
            box-sizing: border-box; /* Ensure padding is included in width */
            font-size: 14px; /* Adjust font size as needed */
        }
        textarea {
            resize: vertical;
            min-height: 100px; /* Ensure textarea has a minimum height */
        }
        button {
            padding: 10px 20px;
            background-color: #28a745;
            border: none;
            border-radius: 4px;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
            width: 100%; /* Take up full width of container */
            box-sizing: border-box; /* Ensure padding is included in width */
        }
        button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Fix Task</h1>
        <form action="mag" method="post">
            <input type="hidden" name="id" value="${param.id}">
            <div>
                <label for="name">Name:</label>
                <input type="text" name="pro_name" value="${param.pro_name}" required>
            </div>
            <div>
                <label for="emp_id">Staff ID:</label>
                <input type="text" name="emp_id" value="${param.emp_id}" required>
            </div>
            <div>
                <label for="details">Details:</label>
                <textarea  name="details" rows="4" required>${param.details}</textarea>
            </div>
            
            <div>
                <label for="dead_line">Deadline:</label>
                <input type="date"name="dead_line" value="${param.dead_line}" required>
            </div>
            <div class="button">
                <button type="submit" name="submit" value="fix">Fix</button>
            </div>
        </form>
    </div>
</body>
</html>
