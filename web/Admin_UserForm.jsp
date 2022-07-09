<%@page import="sample.users.ManagerDTO"%>
<%@page import="sample.users.UserError"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sample.users.UserDTO"%>
<%@page import="sample.organization.OrganizationDTO"%>
<%@page import="sample.organization.OrganizationError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
    <head>
        <meta charset="UTF-8">
        <title>F.E.M - FPT Event Management</title>
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
        <link rel="stylesheet" href="./css_FormCreate/style.css">
        <link rel="stylesheet" href="./css_FormCreate/css/all.css">
        <script src="https://your-site-or-cdn.com/fontawesome/v5.15.4/js/all.js" data-auto-replace-svg></script>
        <script src="https://use.fontawesome.com/releases/vVERSION/js/all.js" data-auto-replace-svg="nest"></script>


        <style>
            .text-danger {
                color: red;
                font-size: 12px;
                font-weight: bold;
            }
        </style>

    </head>
    <body>
        <!-- partial:index.partial.html -->
        <%
            UserError error = (UserError) request.getAttribute("ERROR");
            if (error == null) {
                error = new UserError();
            }
        %>


        <div class="container">
            <form action="MainController" method="POST">
                <h1 class="Information">Information Member</h1>
                <div style="text-align: center">
                    <img style="max-width: 25%; border-radius: 20px" src="">
                </div>
                <%
                    String message = (String) request.getAttribute("Message");
                    if (message == null) {
                        message = "";
                    }
                    String success = (String) request.getAttribute("SUCCESS");
                    if (success == null) {
                        success = "";
                    }
                %>
                <h3 style="font-weight: bold" class="text-danger"><%= message%></h3>
                <h3 style="font-weight: bold" class="text-danger"><%= success%></h3>

                <div class="row">
                    <h4><i class="fa-solid fa-users" style="width: 25px"></i>Account</h4>

                    <%
                        UserDTO user = (UserDTO) request.getAttribute("USER");
                        if (user == null) {
                            user = new UserDTO();
                    %> 

                    <div class="input-group input-group-icon">
                        <input type="text" required="" name="username" placeholder="User Name"/>
                        <div class="input-icon"><i class="fa fa-user"></i></div>
                        <p class="text-danger"><%= error.getIdError()%></p>
                    </div>                    

                    <div class="input-group input-group-icon">
                        <input type="password" required="" name="password" value="" placeholder="Password"/>
                        <div class="input-icon"><i class="fa-regular fa-user"></i></div>
                        <p class="text-danger"><%= error.getPasswordError()%></p>
                    </div>

                    <div class="input-group input-group-icon">
                        <input type="password" required="" name="confirm" value="" placeholder="Confirm Password"/>
                        <div class="input-icon"><i class="fa-regular fa-user"></i></div>
                        <p class="text-danger"><%= error.getPasswordError()%></p>
                    </div>


                    <% } else {%>

                    <div class="input-group input-group-icon">
                        <input type="text" required="" name="username" readonly="" value="<%= user.getId()%>" placeholder="User Name"/>
                        <div class="input-icon"><i class="fa fa-user"></i></div>
                        <p class="text-danger"><%= error.getIdError()%></p>
                    </div>   


                    <div class="input-group input-group-icon">
                        <input type="password" name="password" value="" placeholder="Password"/>
                        <div class="input-icon"><i class="fa-regular fa-user"></i></div>
                        <p class="text-danger"><%= error.getPasswordError()%></p>
                    </div>

                    <div class="input-group input-group-icon">
                        <input type="password" name="confirm" value="" placeholder="Confirm Password"/>
                        <div class="input-icon"><i class="fa-regular fa-user"></i></div>
                        <p class="text-danger"><%= error.getPasswordError()%></p>
                    </div>

                    <% }%>

                    <div class="input-group input-group-icon">
                        <input type="text" required="" name="fullName" value="<%= user.getName()%>" placeholder="Full Name"/>
                        <div class="input-icon"><i class="fa-solid fa-file-signature"></i></div>
                        <p class="text-danger"><%= error.getNameError()%></p>
                    </div>


                    <div class="input-group input-group-icon">
                        <input type="email" required="" name="email" value="<%= user.getEmail()%>" placeholder="Email"/>
                        <div class="input-icon"><i class="fa fa-envelope"></i></div>
                        <p class="text-danger"><%= error.getEmailError()%></p>
                    </div>

                    <div class="input-group input-group-icon">
                        <input type="text" name="phone" value="<%= user.getPhoneNumber()%>" placeholder="Phone"/>
                        <div class="input-icon"><i class="fa fa-phone"></i></div>
                        <p class="text-danger"><%= error.getPhoneNumberError()%></p>

                    </div>

                    <div class="input-group input-group-icon">
                        <input type="url" name="imgUrl" value="<%= user.getPicture()%>" placeholder="Image URL"/>
                        <div class="input-icon"><i class="fa fa-link"></i></div>
                        <p class="text-danger"><%= error.getPictureError()%></p>
                    </div>
                </div>

                <div class="row">
                    <h4><i class="fa-solid fa-pencil" style="width: 25px"></i>Type</h4>

                    <div class="input-group">
                        <%
                            ArrayList<UserDTO> listUserType = (ArrayList) request.getAttribute("listUserType");
                        %>

                        <select name="userType" class="center">
                            <option hidden="" selected="" disabled="">Select Type</option>
                            <%
                                for (UserDTO list : listUserType) {
                                    if (list.getTypeID().equals(user.getTypeID())) {
                            %>
                            <option selected="" hidden="" value="<%= list.getTypeID()%>"><%= list.getTypeName()%> </option>
                            <% }%>
                            <option value="<%= list.getTypeID()%>"><%= list.getTypeName()%> </option>
                            <% }%>
                        </select>
                        <p class="text-danger">     <%= error.getTypeIDError()%></p>

                    </div>
            </div>


                <div class="row">
                    <h4><i class="fa-solid fa-venus-mars" style="width: 25px"></i>Gender</h4>
                    <div class="input-group">
                        <% if ("Male".equals(user.getGender())) { %>
                        <input id="gender-male" type="radio" name="gender" checked="" value="Male"/>
                        <label for="gender-male"><i class="fa-solid fa-mars"></i>Male</label>
                        <input id="gender-female" type="radio" name="gender"  value="Female"/>
                        <label for="gender-female"><i class="fa-solid fa-venus"></i>Female</label>
                        <% } else { %>
                        <input id="gender-male" type="radio" name="gender" value="Male"/>
                        <label for="gender-male"><i class="fa-solid fa-mars"></i>Male</label>
                        <input id="gender-female" type="radio" checked="" name="gender" value="Female"/>
                        <label for="gender-female"><i class="fa-solid fa-venus"></i>Female</label>
                        <% } %>
                    </div>
                </div>



                <div class="flex-row-reverse">
                    <div class="">

                        <h4>Status</h4>
                        <%
                            if (user.isStatus() == false) {
                        %>
                        <div class="input-group">
                            <input id="status-true" type="radio" name="status" value="true"/>
                            <label for="status-true">Active</label>
                            <input id="status-false" type="radio" name="status" checked="" value="false"/>
                            <label for="status-false">NonActive</label>
                        </div>
                        <%
                        } else {
                        %>
                        <div class="input-group">
                            <input id="status-true" type="radio" checked="" name="status" value="true"/>
                            <label for="status-true">Active</label>
                            <input id="status-false" type="radio" name="status"  value="false"/>
                            <label for="status-false">NonActive</label>
                        </div>
                        <%                     }
                        %>
                    </div>
                </div>

                <%
                    String action = request.getParameter("action");
                    if ("UpdateUser".equals(action)) {
                %>
                <input type="hidden" name="page" value="Admin_UserForm.jsp" />
                <button style="cursor: pointer" class="login-box" type="submit" name="action" value="UpdateUser"> Submit </button>
                <%
                } else {
                %>
                <!--                <p class="text-danger"> </p>
                
                                <input type="hidden" name="oldOrgID" value="">
                                <button style="cursor: pointer" class="login-box" type="submit" name="action" value="UpdateUser"> Submit </button>-->
                <input type="hidden" name="page" value="Admin_UserForm.jsp" />
                <button style="cursor: pointer" class="login-box" type="submit" name="action" value="CreateUser"> Submit </button>
                <%
                    }
                %>
                <div style="text-align: center;margin-top: 15px; font-size: 13px"><a href="AdminUserPageController">Cancel</a> </div> 
            </form>
        </div>
        <!-- partial -->
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src="./css_FormCreate/script.js"></script>
    </body>
</html>
