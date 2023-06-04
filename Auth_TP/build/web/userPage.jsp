<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title><style>
            body{
                background:gray;
                color:white;  font-family: Arial, Helvetica, sans-serif;
            }
            #disconenct{
                position:relative;
                left:1100px;
                heigth:50px;
            }
            h2{
                position:relative;
                right:-40px;
            }
            #headerCont{
                display:flex;
                flex-direction:row;
            }
            #submitInput{
                heigth:50px;
            }
            #container{
                margin-left: 540px;
                background:white;
                color:black;
                width:300px;
            }
        </style>
    </head>
    <body align="center">

        <div id="headerCont">        <h2>Profile  ${userName} </h2> <button onclick="window.location.href = `index.html`" id="disconenct"> Disconnect </button>
        </div>
        <div id="container">
        <p>User ID : ${id}</p>
        <p>Type : ${type}</p>
        <p>Name: ${name}</p>
        <p>Family-Name: ${fname}</p>
        <p>Age: ${age}</p></div>
        <form action="submitReview?user=${name}%20${fname}&type=${type}#to" method="POST"  ><input id="submitInput" value="Reviews" type="submit"/></form>
    </body>
</html>
