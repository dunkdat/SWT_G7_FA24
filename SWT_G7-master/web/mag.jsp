<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager Page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-image: url(image/background2.jpg);
                background-size: cover;
                background-position: center;
                margin: 0;
                padding: 20px;
            }
            h1,h2{
                text-align: center;
                color: orange;
            }
            .container {
                margin: 0 auto;
                background-color: rgba(255, 255, 255, 0.8);
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0,0,0,0.5);
            }
            .table-container {
                overflow-x: auto;
                margin-bottom: 20px;
                margin-top: 20px;
                background-color: rgba(255, 255, 255, 0.6);
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0,0,0,0.3);
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
                table-layout: fixed;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: center;
                word-wrap: break-word;
                overflow: hidden;
            }
            th {
                background-color: #f2f2f2;
                color: orange;
            }
            form {
                margin-top: 20px;
            }
            label {
                display: block;
                margin-bottom: 5px;
                color: #555;
            }
            input[type="text"],
            input[type="date"],
            textarea {
                width: 90%;
                padding: 8px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
            textarea {
                resize: vertical;
            }
            .button {
                text-align: center;
            }
            button {
                padding: 10px 20px;
                background-color: #28a745;
                border: none;
                border-radius: 4px;
                color: #fff;
                font-size: 16px;
                cursor: pointer;
            }
            button:hover {
                background-color: #218838;
            }
            img {
                cursor: pointer;
            }
            .search-form,
            .add-form {
                margin-bottom: 20px;
            }
            .search-form {
                margin-left: 10%;
                text-align: left;
            }
            .add-form {
                margin-right: 25%;
            }
            .search-form select,
            .search-form input[type="text"] {
                display: inline-block;
                width: auto;
                margin-right: 10px;
            }
            .search-form button {
                display: inline-block;
            }
            .func form {
                width: 200%;
                background-color: rgba(255, 255, 255, 0.6);
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0,0,0,0.3);
            }
            .search-form form {
                width: 100%;
            }
            .func {
                display: flex;
                justify-content: space-between;
                flex-wrap: wrap;
            }
            .getAll {
                text-align: left;
            }
            a {
                color: orange;
                text-decoration: none;
                padding: 10px 20px;
                background-color: rgba(255, 255, 255, 0.6);
                border-radius: 4px;
                margin-right: 10px;
            }

            a:hover {
                background-color: rgba(255, 255, 255, 0.8);
            }
        </style>
        <script>
            function confirmDelete() {
                return confirm("Are you sure you want to delete this task?");
            }

            function confirmFix() {
                return confirm("Are you sure you want to fix this task?");
            }

            function filterDoneTask() {
                var tableType = document.getElementById("doneTableType").value; // Get selected filter value
                var table = document.getElementById("doneTasksTable"); // Get the table
                var rows = table.getElementsByTagName("tr"); // Get all rows in the table

                // Loop through each row (start from index 1 to skip header row)
                for (var i = 1; i < rows.length; i++) {
                    var dataType = rows[i].getAttribute("data-type"); // Get data-type attribute value

                    // Determine whether to display the row based on tableType
                    if (tableType === "all" || dataType === tableType) {
                        rows[i].style.display = ""; // Show the row
                    } else {
                        rows[i].style.display = "none"; // Hide the row
                    }
                }
            }
            
            function filterTask() {
                var tableType = document.getElementById("TaskType").value; // Get selected filter value
                var table = document.getElementById("TasksTable"); // Get the table
                var rows = table.getElementsByTagName("tr"); // Get all rows in the table

                // Loop through each row (start from index 1 to skip header row)
                for (var i = 1; i < rows.length; i++) {
                    var dataType = rows[i].getAttribute("data-type"); // Get data-type attribute value

                    // Determine whether to display the row based on tableType
                    if (tableType === "all" || dataType === tableType) {
                        rows[i].style.display = ""; // Show the row
                    } else {
                        rows[i].style.display = "none"; // Hide the row
                    }
                }
            }

            function toggleReassignForm(id) {
                var form = document.getElementById("reassignForm");
                form.style.display = form.style.display === "none" ? "block" : "none";
                if (form.style.display === "block") {
                    document.getElementById("reassign_id").value = id;
                }
            }
        </script>
    </head>
    <body>
        
        <div class="container">
        <a href="mag">Main Page</a>
        <a href="acc?id=${id}&role=mag">Account Manage</a>
        <h1>MANAGER: ${sessionScope.name}</h1>

            <div class="table-container">
                <div class="search-form">
                    <h3>Search</h3>
                    <form action="mag" method="post">
                        <label>Search By ID:</label>
                        <select name="searchBy">
                            <option value="1" ${param.searchBy == "1" ? 'selected' : ''}>Project's ID</option>
                            <option value="2" ${param.searchBy == "2" ? 'selected' : ''}>Staff's ID</option>
                        </select>
                        <input type="text" name="searchVal" value="${param.searchVal}" required/>
                        <button type="submit" name="submit" value="search">Search</button>
                    </form>
                    <form action="mag" method="post" class="getAll">
                        <button type="submit" name="submit" value="getAll">Get All/Refresh</button>
                    </form>
                </div>

                <!-- First Table -->
                <h2>Done Tasks Table</h2>
                <div>
                    <label>Items: </label>
                    <select id="doneTableType" onchange="filterDoneTask()">
                        <option value="all">All</option>
                        <option value="file">File</option>
                        <option value="text">Text</option>
                    </select>
                </div>
                <table id="doneTasksTable">
                    <thead>
                        <tr>
                            <th>Project ID</th>
                            <th>Name</th>
                            <th>Staff ID</th>
                            <th>Submit Date</th>
                            <th>File Name</th>
                            <th>Download</th>
                            <th>ReAssign Task</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.sdata}" var="n">
                            <tr data-type="${n.file_name != null ? 'file' : 'text'}">
                                <td>${n.id}</td>
                                <td>${n.name}</td>
                                <td>${n.emp_id}</td>
                                <td style="color: ${n.check_deadline == 1 ? 'greenyellow' : 'red'}">${n.current_date}</td>
                                <c:choose>
                                    <c:when test="${n.file_name != null}">
                                        <td>${n.file_name}</td>
                                        <td><a href="download?name=${n.file_name}"><img src="image/download.png" alt="Download" width="40px"/></a></td>
                                            </c:when>
                                            <c:otherwise>
                                        <td colspan="2">${n.text}</td>
                                    </c:otherwise>
                                </c:choose>
                                        <td><img src="image/reass.jpg" alt="Reassign Task" width="30px" onclick="toggleReassignForm('${n.id}')"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div style="color: red; font-style: italic">${requestScope.error1}</div>
                <div id="reassignForm" style="display: none;">
                    <h3>Reassign Task</h3>
                    <form action="mag" method="post">
                        <input type="hidden" id="reassign_id" name="id" readonly>
                      
                        <div>
                            <label for="reassign_dead_line">Deadline:</label>
                            <input type="date" id="reassign_dead_line" name="dead_line" required/>
                        </div>
                        <div class="button">
                            <button type="submit" name="submit" value="reass">Reassign</button>
                        </div>
                    </form>
                </div>

                <!-- Second Table -->
                <h2>Assigned Tasks Table</h2>
                <div>
                    <label>Items: </label>
                    <select id="TaskType" onchange="filterTask()">
                        <option value="all">All</option>
                        <option value="notdone">Not Done</option>
                        <option value="done">Done</option>
                    </select>
                </div>
                <table id="TasksTable">
                    <thead>
                        <tr>
                            <th>Project ID</th>
                            <th>Name Project</th>
                            <th>Staff ID</th>
                            <th>Send Date</th>
                            <th>Deadline</th>
                            <th>Delete</th>
                            <th>Fix</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.tdata}" var="n">
                            <c:set var="dataType" value="notdone"/>
                            <c:forEach items="${requestScope.sdata}" var="l">
                                <c:if test="${n.id == l.id}">
                                    <c:set var="dataType" value="done"/>
                                </c:if>
                            </c:forEach>
                            <tr data-type="${dataType}">
                                <td>${n.id}</td>
                                <td>${n.name}</td>
                                <td>${n.emp_id}</td>
                                <td>${n.current_date}</td>
                                <td>${n.dead_line}</td>
                                <c:if test="${dataType == 'notdone'}">
                                    <td><a href="mag?id=${n.id}&submit=del" onclick="return confirmDelete()"><img src="image/del.png" width="20"></a></td>
                                    <td><a href="fix.jsp?id=${n.id}&pro_name=${n.name}&emp_id=${n.emp_id}&details=${n.details}&current_date=${n.current_date}&dead_line=${n.dead_line}" onclick="return confirmFix()"><img src="image/fix.png" width="20"></a></td>
                                        </c:if>
                                        <c:if test="${dataType == 'done'}">
                                    <td><a href="mag?id=${n.id}&submit=del" onclick="return confirmDelete()"><img src="image/del.png" width="20"></a></td>
                                    <td>Done Task</td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>
                        
            <div class="func">
                <!-- Search Form -->
                <img style="margin-top: 40px" src="image/logo.png" alt="alt" width="50%"/>
                <!-- Add Form -->
                <div class="add-form">
                    <h3>Send Task</h3>
                    <form action="mag" method="post">
                        <div style="color: red; font-style: italic">${requestScope.error}</div>
                        <div>
                            <label for="id">ID:</label>
                            <input type="text" id="id" name="id" required>
                        </div>
                        <div>
                            <label for="name">Name:</label>
                            <input type="text" id="name" name="pro_name" maxlength="100" required>
                        </div>
                        <div>
                            <label for="emp_id">Emp ID:</label>
                            <input type="text" id="emp_id" name="emp_id" maxlength="20" required>
                        </div>
                        <div>
                            <label for="details">Details:</label>
                            <textarea id="details" name="details" maxlength="250" required></textarea>
                        </div>
                        <div>
                            <label for="dead_line">Deadline:</label>
                            <input type="date" id="dead_line" name="dead_line" required/>
                        </div>
                        <div class="button">
                            <button type="submit" name="submit" value="addPro">Send</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
