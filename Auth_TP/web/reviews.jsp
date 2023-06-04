<%-- 
    Document   : reviews
    Created on : 3 Jun 2023, 23:28:56
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reviews</title>
        <style>
            body{
                font-family: Arial, Helvetica, sans-serif;
                background:gray;
                color:white;
                display:flex;
                justify-content:center;
                align-items:center;
            }
            #container{
                display:flex;
                align-items:center;
                justify-content: center;
                flex-direction:column;
            }
            #reviewsContainer{
                height:450px;
                width:700px;
                border:solid white 1px;
                overflow-y: scroll;
                padding: 20px;
            }
            #title{
                display:flex;
                align-items: center;
                gap: 20px;
            }
            #title>button{
                height:20px
            }
            input{
                height:50px
            }
            #reviewsContainer >div{
                display: flex;
                flex-direction:row;
                align-items: center
            }
            b{
                font-size:15px;
            }
            .comment{
                background:white;
                color:black;
                width:fit-content;
                padding:10px;
                border-radius: 6px;
            }
        </style>
    </head>
    <body>
        <div id="container">
            <div id="title" onclick="history.back()"><button>&lt;</button><h1>Reviews:</h1></div>
            <div id="reviewsContainer">
                ${reviews}
            </div>
            ${input}

        </div>
    </body>
</html>
