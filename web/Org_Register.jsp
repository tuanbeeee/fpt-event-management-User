<%@page import="sample.users.UserError"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <script src="https://kit.fontawesome.com/64d58efce2.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="UserCSS/css_Login/style.css" />
        <title>Sign in & Sign up Form</title>
    </head>

    <body>
        <%UserError error = (UserError) request.getAttribute("ERROR");%>
        <div class="container">
            <div class="forms-container">
                <div class="signin-signup">
                    <form action="MainController" method="POST" class="sign-in-form">
                        <h2 class="title">Sign up</h2>
                        <p style="font-weight: bold; color: red"></p>
                        <div class="input-field">
                            <i class="fas fa-user"></i>
                            <input type="text" required="" placeholder="Username/Club Code" name="orgID"/>
                        </div>
                        <span style="color:red"><%=(request.getAttribute("ERROR") == null) ? "" : error.getIdError()%></span>
                        <div class="input-field">
                            <i class="fas fa-user"></i>
                            <input type="text" required="" placeholder="Club Name" name="clubName"/>
                        </div>
                        <div class="input-field">
                            <i class="fas fa-user"></i>
                            <input type="date" required="" placeholder="Create Date" name="createDate"/>
                        </div>
                        <div class="input-field">
                            <i class="fas fa-user"></i>
                            <input type="text" required="" placeholder="Description" name="description"/>
                        </div>
                        <div class="input-field">
                            <i class="fas fa-user"></i>
                            <input type="text" required="" placeholder="Image URL" name="imgURL"/>
                        </div>
                        <div class="input-field">
                            <i class="fas fa-user"></i>
                            <input type="text" required="" placeholder="Email" name="email"/>
                        </div>
                        <span style="color:red"><%=(request.getAttribute("ERROR") == null) ? "" : error.getEmailError()%></span>
                        <div class="input-field" >
                            <i class="fas fa-lock"></i>
                            <input required="" type="password" name="password" placeholder="Password" />
                        </div>
                        <div class="input-field" >
                            <i class="fas fa-lock"></i>
                            <input required="" type="password" name="confirm" placeholder="Confirm Password" />
                        </div>
                        <span style="color:red"><%=(request.getAttribute("ERROR") == null) ? "" : error.getPasswordConfirmError()%></span>
                        <span style="color:green"><%=(request.getAttribute("Message") == null) ? "" : request.getAttribute("Message")%></span>
                        <div style="display: flex;">
                            <a style="color: rgb(255, 166, 0);" href="MainController?action=homepage">Back to home page</a>
                        </div>
                        <input type="submit" name="action" value="Sign Up By Org" class="btn solid" />
                    </form>
                </div>
            </div>
        </div>
        <div class="panels-container">
            <div class="panel left-panel">
                <img width="200px" src="UserCSS/css_Login/img/register.svg" class="image" alt="" />
            </div>
        </div>

        <script src="UserCSS/css_Login/Fapp.js"></script>
    </body>

</html>