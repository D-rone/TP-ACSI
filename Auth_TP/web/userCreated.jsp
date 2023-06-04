<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Created</title>
        <style>body{
            background:gray;
            color:black;
        }</style>

    </head>
    <body>
        <div class="container" align="center">
            <h1>User Created Successfully</h1>
            <p>name: ${name}</p>
            <p>Family-name: ${fname}</p>
            <p>Age: ${age}</p>
            <p>UserName: ${userName}</p>
            <button onclick="window.location.href = `index.html`"> Go Back</button>
        </div>
    </body>
</html>
