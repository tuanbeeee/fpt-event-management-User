<%@page import="sample.users.UserDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sample.users.UserError"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <script src="https://kit.fontawesome.com/64d58efce2.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="UserCSS/kagont/assets/css/style2.css" />
        <link rel="shortcut icon" type="image/x-icon" href="UserCSS/kagont/assets/images/x-icon/Biểu-tượng-không-chữ1.png">
        <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.3.1/css/all.css'>
        <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.3.1/css/fontawesome.css'>
        <title>F.E.M</title>
    </head>

    <body>
        <%UserError error = (UserError) request.getAttribute("ERROR");
            String getRequest = (String) request.getAttribute("request"); %>

        <%if (error == null && request.getAttribute("Message") == null && getRequest.equals("LOGIN")) {%> 
        <div class="container">
            <%} else if (error != null || request.getAttribute("Message") != null || getRequest.equals("REGISTER")) {%>
            <div class="container sign-up-mode">
                <%}%>
                <div forms-container>
                    <div class="signin-signup">
                        <form action="MainController" method="POST" class="sign-in-form">
                            <h2 class="title">Sign in</h2>
                            <span style="color:red"><%=(request.getAttribute("errMsg") == null) ? "" : request.getAttribute("errMsg")%></span>
                            <div class="input-field">
                                <i class="fas fa-user"></i>
                                <input type="text" placeholder="User Name" name="username">
                            </div>
                            <div class="input-field">
                                <i class="fas fa-lock"></i>
                                <input type="password" placeholder="Password" name="password">
                            </div>
                            <div style="display: flex;">
                                <label style="margin-right: 100px;">
                                    <input type="checkbox" checked>
                                    Remember me
                                </label>
                                <a style="color: blue" href="">Forget Password</a>
                            </div>
                            <input type="submit" name="action" value="Login" class="btn solid" />                       
                            <p class="social-text">Or Sign in with social platforms</p>
                            <div class="social-media">
                                <a href="#" class="social-icon">
                                    <i class="fab fa-facebook-f"></i>
                                </a>
                                <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8084/EventManagement/LoginGoogleHandler&response_type=code
                                   &client_id=963569854032-6n5p7l7nc4hl12lo5d1s7feg6qs796ue.apps.googleusercontent.com&approval_prompt=force" class="social-icon">
                                    <i class="fab fa-google"></i>
                                </a>
                            </div>
                        </form>

                        <form action="MainController" method="POST" class="sign-up-form">

                            <h2 class="title">Sign up</h2>


                            <div class="input-field">
                                <i class="fas fa-user"></i>
                                <input type="text" placeholder="User Name" required="" name="username">

                            </div>
                            <span style="color:red"><%=(request.getAttribute("ERROR") == null) ? "" : error.getIdError()%></span>


                            <div class="input-field">
                                <i class="fas fa-user"></i>
                                <input type="text" placeholder="Full Name" required="" name="fullName">

                            </div>
                            <span style="color:red"><%=(request.getAttribute("ERROR") == null) ? "" : error.getNameError()%></span>


                            <div class="input-field">
                                <i class="fa fa-user-check"></i>
                                <select class="form-select"  name="userType">
                                    <option  selected disabled>Select type of user</option>
                                    <%
                                        ArrayList<UserDTO> list = new ArrayList<UserDTO>();
                                        list = (ArrayList<UserDTO>) request.getAttribute("listUserType");
                                        for (UserDTO c : list) {
                                    %>

                                    <option value="<%=c.getTypeID()%>" class="others"><%=c.getTypeName()%> </option>

                                    <%}%>
                                </select>

                            </div>
                            <span style="color:red"><%=(request.getAttribute("ERROR") == null) ? "" : error.getTypeIDError()%></span>


                            <div class="input-field">
                                <i class="fa fa-transgender"></i>
                                <select type="text" placeholder="Gender" name="gender">
                                    <option selected disabled>Select Gender</option>
                                    <option value="Male" class="others">Male</option>
                                    <option value="Female" class="others">Female</option>
                                </select>

                            </div>
                            <span style="color:red"><%=(request.getAttribute("ERROR") == null) ? "" : error.getGenderError()%></span>


                            <div class="input-field">
                                <i class="fas fa-envelope"></i>
                                <input type="text" required="" placeholder="Email" name="email">

                            </div>
                            <span style="color:red"><%=(request.getAttribute("ERROR") == null) ? "" : error.getEmailError()%></span>

                            <div class="input-field">
                                <i class="fas fa-lock"></i>
                                <input type="text" required="" placeholder="Phone" name="phone">

                            </div>
                            <span style="color:red"><%=(request.getAttribute("ERROR") == null) ? "" : error.getPhoneNumberError()%></span>


                            <div class="input-field">
                                <i class="fas fa-lock"></i>
                                <input type="password" required="" placeholder="Password" name="password">

                            </div>
                            <span style="color:red"><%=(request.getAttribute("ERROR") == null) ? "" : error.getPasswordError()%></span>


                            <div class="input-field">
                                <i class="fa fa-key"></i>
                                <input type="password" required="" placeholder="Confirm Password" name="confirm">

                            </div>
                            <span style="color:red"><%=(request.getAttribute("ERROR") == null) ? "" : error.getPasswordConfirmError()%></span>


                            <input type="submit" class="btn" name="action" value="Register" />
                            <input type="hidden" name="page" value="User_Login_And_Register.jsp" />
                            <span style="color:green"><%=(request.getAttribute("Message") == null) ? "" : request.getAttribute("Message")%></span>
                            <p class="social-text">Or Sign up with social platforms</p>
                            <div class="social-media">
                                <a href="#" class="social-icon">
                                    <i class="fab fa-facebook-f"></i>
                                </a>
                                <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8084/EventManagement/LoginGoogleHandler&response_type=code
                                   &client_id=963569854032-6n5p7l7nc4hl12lo5d1s7feg6qs796ue.apps.googleusercontent.com&approval_prompt=force" class="social-icon">
                                    <i class="fab fa-google"></i>
                                </a>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="panels-container">
                    <div class="panel left-panel">
                        <div class="content">
                            <h3>New here ?</h3>
                            <p>
                                You are a new person. Don't have an account please:
                            </p>
                            <button class="btn transparent" id="sign-up-btn">
                                Sign up
                            </button>
                        </div>
                        <img src="UserCSS/kagont/assets/images/img/log.svg" class="image" alt="" />
                    </div>
                    <div class="panel right-panel">
                        <div class="content">
                            <h3>One of us ?</h3>
                            <p>
                                You already have an account, please login and experience:
                            </p>
                            <button class="btn transparent" id="sign-in-btn">
                                Sign in
                            </button>
                        </div>
                        <img src="UserCSS/kagont/assets/images/img/register.svg" class="image" alt="" />
                    </div>
                </div>
            </div>

            <script src="UserCSS/kagont/assets/js/app.js"></script>
    </body>

</html>