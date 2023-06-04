<%-- 
    Document   : SignUpPage
    Created on : 4 Mar 2023, 18:39:43
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
        <style>
            form{
                display: flex;
                flex-direction: column;
                width:fit-content;
            }
            body{
                font-family: Arial, Helvetica, sans-serif;
                background:gray;
                color:white;
            }
            h1{
                margin-top:100px;
                margin-bottom:60px;

            }
            .signUpBtn{
                margin-top:20px;
            }
        </style>
    </head>
    <body>
        <div align="center" class="container">
            <div>
                <h1>Sign Up</h1>
                <form method="post" action="saveUser">
                    Name: <input type="text" name="name" required/>
                    Family-name: <input type="text" name="fname" required/>
                    Age: <input type="number" name="age" required/>
                    <br/>
                    UserName: <input type="text" name="userName" required/>
                    Password: <input type="password" name="password" minlength="8" required/>
                    <input type="submit" value="Sign Up" class="signUpBtn"/>
                </form>
            </div>
        </div> 

    </body>
</html>
