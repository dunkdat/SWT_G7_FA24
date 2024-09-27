<%-- 
    Document   : staff
    Created on : Jun 11, 2024, 9:30:06 PM
    Author     : DAT
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff Page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-image: url(image/background2.jpg); /* Adjust the path as needed */
                background-size: cover; /* Cover the whole page */
                background-position: center; /* Center the image */
                margin: 0;
                padding: 20px;
            }
            h2 {
                text-align: center;
                color: orange;
            }
            .container {
                background-color: rgba(255, 255, 255, 0.3);
                margin: 0 auto;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0,0,0,0.5);
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: center;
            }
            th {
                background-color: #f2f2f2;
                color: orange;
            }
            form {
                display: inline;
            }
            input[type="file"] {
                margin-bottom: 10px;
            }
            button, .Editor {
                padding: 5px 10px;
                background-color: #28a745;
                border: none;
                border-radius: 4px;
                color: #fff;
                font-size: 14px;
                cursor: pointer;
            }
            button:hover {
                background-color: #218838;
            }
            .Editor:hover {
                background-color: #218838;
            }
            .editor-container {
                margin-top: 20px;
                display: none; /* Initially hide the editor container */
            }
            .editor-label {
                font-weight: bold;
                margin-bottom: 10px;
                display: block;
            }
            .editor {
                background-color: rgba(255, 255, 255, 0.9);
                border: 1px solid #ddd;
                border-radius: 4px;
                padding: 10px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
            }
            .editor-submit-button {
                margin-top: 10px;
                padding: 5px 10px;
                background-color: #007bff;
                border: none;
                border-radius: 4px;
                color: #fff;
                font-size: 14px;
                cursor: pointer;
            }
            .editor-submit-button:hover {
                background-color: #0056b3;
            }
             a {
    color: orange; /* Màu chữ là màu cam */
    text-decoration: none; /* Không gạch chân cho liên kết */
    padding: 10px 20px; /* Khoảng cách giữa các liên kết */
    background-color: rgba(255, 255, 255, 0.6); /* Màu nền hơi trong suốt */
    border-radius: 4px; /* Bo tròn góc */
    margin-right: 10px; /* Khoảng cách phía bên phải */
}

a:hover {
    background-color: rgba(255, 255, 255, 0.8); /* Màu nền hơi sáng hơn khi hover */
}
        </style>
        <script src="https://cdn.ckeditor.com/4.16.2/standard/ckeditor.js"></script>
    </head>
    <body>
        <div class="container">
            <a href="staff">Main Page</a>
        <a href="acc?id=${id}&role=staff">Account Manage</a>
            <h2>STAFF: ${sessionScope.name}</h2>
            <div>
                <table>
                    <thead>
                        <tr>
                            <th>Project ID</th>
                            <th>Name</th>
                            <th>Details</th>
                            <th>Send Date</th>
                            <th>Dead Line</th>
                            <th>Upload</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="n" items="${requestScope.sdata}">
                            <c:set var="exists" value="false" />
                            <c:forEach var="l" items="${requestScope.tdata}">
                                <c:if test="${n.id == l.id}">
                                    <c:set var="exists" value="true" />
                                </c:if>
                            </c:forEach>
                            <c:if test="${exists == false}">
                                <tr>
                                    <td>${n.id}</td>
                                    <td>${n.name}</td>
                                    <td>${n.details}</td>
                                    <td>${n.current_date}</td>
                                    <td>${n.dead_line}</td>
                                    <td>
                                        <form action="upload?id=${id}&name=${n.name}&pro_id=${n.id}" method="post" enctype="multipart/form-data">
                                            <input type="file" required name="file">
                                            <button type="submit">Submit</button>
                                            <input class="Editor" onclick="toggleEditor('${id}', '${n.name}', '${n.id}')" type="button" value="Editor">
                                        </form>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
             
            <div class="editor-container">
                <form action="upload" method="post" enctype="multipart/form-data">
                    <input type="hidden" id="hiddenId" name="emp_id">
                    <input type="hidden" id="hiddenName" name="pro_name">
                    <input type="hidden" id="hiddenProId" name="id">
                    <label class="editor-label" for="editor1">Text Editor:</label>
                    <textarea name="text" id="editor1" class="editor"></textarea>
                    <script>
                        CKEDITOR.replace('editor1');
                    </script>
                    <button type="submit" name="submit" class="editor-submit-button">Submit</button>
                </form>
            </div>
        </div>
           
        <script>
            function toggleEditor(id, name, proId) {
                var editorContainer = document.querySelector('.editor-container');
                document.getElementById('hiddenId').value = id;
                document.getElementById('hiddenName').value = name;
                document.getElementById('hiddenProId').value = proId;

                if (editorContainer.style.display === 'none' || editorContainer.style.display === '') {
                    editorContainer.style.display = 'block';
                } else {
                    editorContainer.style.display = 'none';
                }
            }
        </script>
    </body>
</html>
