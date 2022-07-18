<%@page import="sample.notification.NotificationDTO"%>
<%@page import="sample.users.UserError"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sample.users.UserDTO"%>
<!DOCTYPE html>
<html lang="zxx">
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <head>
        <title>F.E.M</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="UserCSS/kagont/assets/images/x-icon/Biểu-tượng-không-chữ1.png">

        <link rel="stylesheet" href="UserCSS/kagont/assets/css/animate.css">
        <link rel="stylesheet" href="UserCSS/kagont/assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="UserCSS/kagont/assets/css/all.min.css">
        <link rel="stylesheet" href="UserCSS/kagont/assets/css/icofont.min.css">
        <link rel="stylesheet" href="UserCSS/kagont/assets/css/lightcase.css">
        <link rel="stylesheet" href="UserCSS/kagont/assets/css/swiper.min.css">
        <link rel="stylesheet" href="UserCSS/kagont/assets/css/style.css">
        <link href='https://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css' />
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css'>
        <script src='//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.0-beta.1/angular.js'></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.0-beta.1/angular-animate.js'></script>
        <script src="UserCSS/kagont/assets/css/script.js"></script>
        <style>
            .nut_dropdown {
                color: white;
                padding: 16px;
                font-size: 16px;
                border: none;
            }
            /* Thiết lập vị trí cho thẻ div với class dropdown*/
            .dropdown {
                position: relative;
                display: inline-block;
            }
            /* Nội dung Dropdown */
            .noidung_dropdown {
                /*Ẩn nội dụng các đường dẫn*/
                display: none;
                position: absolute;
                background-color: #f1f1f1;
                min-width: 400px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
                height: 400px;
                overflow: auto;
            }
            /* Thiết kế style cho các đường dẫn tronng Dropdown */
            .noidung_dropdown a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }
            .hienThi{
                display:block;
            }
            .avatar-upload {
                position: relative;
                max-width: 205px;
                margin: 50px auto;
            }
            .avatar-upload .avatar-edit {
                position: absolute;
                right: 12px;
                z-index: 1;
                top: 10px;
            }
            .avatar-upload .avatar-edit input {
                display: none;
            }
            .avatar-upload .avatar-edit input + label {
                display: inline-block;
                width: 40px;
                height: 40px;
                margin-bottom: 0;
                margin-top: 142px;
                margin-right: 13px;
                border-radius: 100%;
                background: #FFFFFF;
                border: 1px solid transparent;
                box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.12);
                cursor: pointer;
                font-weight: normal;
                transition: all 0.2s ease-in-out;
            }
            .avatar-upload .avatar-edit input + label:hover {
                background: #f1f1f1;
                border-color: #d6d6d6;
            }
            .avatar-upload .avatar-edit input + label:after {
                content: "\f030";
                font-family: 'FontAwesome';
                color: #757575;
                position: absolute;
                top: 150px;
                left: 0;
                right: 13px;
                text-align: center;
                margin: auto;
            }
            .avatar-upload .avatar-preview {
                width: 192px;
                height: 192px;
                position: relative;
                border-radius: 100%;
                border: 6px solid #F8F8F8;
                box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.1);
            }
            .avatar-upload .avatar-preview > div {
                width: 100%;
                height: 100%;
                border-radius: 100%;
                background-size: cover;
                background-repeat: no-repeat;
                background-position: center;
            }
        </style>

    </head>

    <body>

        <!-- preloader start here -->
        <div class="preloader">
            <div class="preloader-inner">
                <div class="preloader-icon">
                    <span></span>
                    <span></span>
                </div>
            </div>
        </div>
        <!-- preloader ending here -->


        <!-- ==========Header Section Starts Here========== -->
        <header class="header-section">
            <div class="header-bottom">
                <div class="container">
                    <div class="header-wrapper">
                        <div class="logo">
                            <a href="MainController?action=homepage">
                                <img src="UserCSS/kagont/assets/images/resize-logo/TEXT-(153x50)1.png" alt="logo">
                            </a>
                        </div>
                        <%
                            UserDTO user = (UserDTO) request.getSession().getAttribute("LOGIN_USER");
                        %>
                        <div class="menu-area">
                            <ul class="menu">
                                <li>
                                    <a href="MainController?action=homepage"><i class="fa fa-home"></i>Home</a>
                                </li>

                                <li>
                                    <a href="MainController?action=clubList&userID=<%=user.getId()%>"><i class="fa fa-cube"></i> Club</a>
                                </li>
                                <li>
                                    <a href="MainController?action=viewEventList&userID=<%=user.getId()%>"><i class="fa fa-calendar-check"></i>Event</a>
                                </li>                              
                                <li>
                                    <a href="MainController?action=ViewBlogList&userID=<%=user.getId()%>"><i class="fa fa-newspaper"></i>Blog</a>
                                </li>
                                <li><a href="contact.html"><i class="icofont-ui-message"></i>Contact</a></li>

                            </ul>
                            <ul class="menu">
                                <li>
                                    <a href="#0"><i class="icofont-user"></i><%= user.getName()%></a>
                                    <ul class="submenu">
                                        <li><a href="MainController?action=profilepage">Profile<i class="icofont-pixels"></i></a></li>

                                        <li><a href="MainController?action=logout&page=US" onclick="return confirm('Do you want to log out ?')" >Logout<i class="icofont-delivery-time"></i></a>
                                        </li>
                                    </ul>
                                </li>

                                <li>
                                    <%ArrayList<NotificationDTO> noti = new ArrayList<>();
                                        noti = (ArrayList<NotificationDTO>) request.getAttribute("GET_NOTIFICATION"); %>
                                    <div class="dropdown">
                                        <a  onclick="hamDropdown()"  class="nut_dropdown fa fa-bell"></a>
                                        <div class="noidung_dropdown">                                               
                                            <div class="cont">
                                                <%for (NotificationDTO n : noti) {%>
                                                <div class="sec new">
                                                    <a>
                                                        <div class="profCont">
                                                            <img class="profile" src="<%=n.orgImg%>">
                                                        </div>
                                                        <div class="txt textnotification"><%=n.evtContent%>: "<%=n.notiContent%>"</div>
                                                        <div class="txt sub textnotification"><%=n.notiDate%></div>
                                                    </a>
                                                </div>
                                                <%}%>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>


                        <!-- toggle icons -->
                        <div class="header-bar d-lg-none">
                            <span></span>
                            <span></span>
                            <span></span>
                        </div>

                    </div>

                </div>
            </div>
        </div>
    </header>

    <!-- ==========Header Section Ends Here========== -->

    <!-- Page Header Section Start Here -->
    <section class="page-header padding-tb">
        <div class="overlay"></div>
        <div class="container">
            <div class="page-header-content-area">
                <h4 class="ph-title">Login for Event</h4>
                <ul class="lab-ul">
                    <li><a href="MainController?action=homepage">Home</a></li>
                    <li><a href="#">Features</a></li>
                    <li><a class="active">login</a></li>
                </ul>
            </div>
        </div>
    </section>


    <div class="section">
        <div class="container">


            <div class="page-content page-container" id="page-content">
                <div class="padding1">
                    <div class="row container d-flex justify-content-center">
                        <div class="col-xl-6 col-md-12 fix">
                            <div class="card user-card-full">




                                <%
                                    String test = (String) request.getAttribute("edit");
                                %>
                                <%UserError error = (UserError) request.getAttribute("ERROR");%>

                                <%if (test == null) {%>
                                <div class="row m-l-0 m-r-0">
                                    <div class="col-sm-4 bg-c-lite-green user-profile">
                                        <div class="d-flex flex-column align-items-center text-center">
                                            <div class="avatar-upload">
                                                <div class="avatar-preview">
                                                    <div id="imagePreview" style="background-image: url(<%=user.getPicture()%>);">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="mt-3">
                                                <h4 style="color: white"><%=user.getName()%></h4>
                                                <ul
                                                    class="social-media social-color justify-content-center d-flex lab-ul">
                                                    <li>
                                                        <a href="https://www.facebook.com/anhtuan141001"
                                                           class="facebook"><i class="icofont-facebook" style="color: rgb(254, 254, 254)"></i></a>
                                                    </li>
                                                    <li>
                                                        <a href="https://www.instagram.com/anhtuan_1410/"
                                                           class=""><i class="icofont-instagram" style="color: rgb(254, 254, 254)"></i></a>
                                                    </li>
                                                    <li>
                                                        <a href="mailto:tuananhtran1410@gmail.com"
                                                           class=" "><i class="fa fa-envelope" style="color: rgb(254, 254, 254)"></i></a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="card-block">
                                            <div class="row">
                                                <div class="col-sm-3">
                                                    <h6 class="m-b-10 f-w-600 mau "><i class="fa fa-user"></i> Full Name</h6>
                                                </div>                                                        
                                                <div class="col-sm-9 text-secondary">
                                                    <h6 class="text-muted f-w-400 font"><%=user.getName()%> </h6>
                                                </div> 
                                            </div>                                     
                                            <h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600 mau"></h6>


                                            <div class="row">
                                                <div class="col-sm-3">
                                                    <h6 class="m-b-10 f-w-600 mau"><i class="fa fa-envelope"></i> Email</h6>
                                                </div>
                                                <div class="col-sm-9 text-secondary">
                                                    <h6 class="text-muted f-w-400 font"><%=user.getEmail()%></h6>
                                                </div>
                                            </div>
                                            <h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600 mau"></h6>


                                            <div class="row">
                                                <div class="col-sm-3">
                                                    <h6 class="m-b-10 f-w-600 mau"><i class="icofont-phone"></i> Phone</h6>
                                                </div>
                                                <div class="col-sm-9 text-secondary">
                                                    <h6 class="text-muted f-w-400 font"><%=user.getPhoneNumber()%></h6>
                                                </div>
                                            </div>
                                            <h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600 mau"></h6>


                                            <div class="row">
                                                <div class="col-sm-3">
                                                    <h6 class="m-b-10 f-w-600 mau"><i class="fa fa-user"></i> Type</h6>
                                                </div>
                                                <div class="col-sm-9 text-secondary">
                                                    <h6 class="text-muted f-w-400 font"><%=user.getTypeName()%></h6>
                                                </div>
                                            </div>
                                            <h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600 mau"></h6>


                                            <div class="row">
                                                <div class="col-sm-3">
                                                    <h6 class="m-b-10 f-w-600 mau"> <i class="fa fa-user"></i> Gender</h6>
                                                </div>
                                                <div class="col-sm-9 text-secondary">
                                                    <h6 class="text-muted f-w-400 font"><%=user.getGender()%></h6>
                                                </div>
                                            </div>
                                            <h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600 mau"></h6>
                                            <div>
                                                <span style="color:green"><%=(request.getAttribute("GetMessage") == null) ? "" : request.getAttribute("GetMessage")%></span>
                                            </div>

                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <a class="btn btn-info " target="__blank"
                                                       href="MainController?action=dataType&page=UserProfileController">Edit</a>&emsp;  
                                                    <a class="btn btn-info " target="__blank"
                                                       href="MainController?action=dataType&page=changePassword">Change Password</a>                                                    
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <%} else if (test.equals("Can Edit")) {%>
                                <form action="MainController" method="POST" enctype="multipart/form-data">
                                    <div class="row m-l-0 m-r-0">
                                        <div class="col-sm-4 bg-c-lite-green user-profile">
                                            <div class="d-flex flex-column align-items-center text-center">
                                                <div class="container">
                                                    <div class="avatar-upload">
                                                        <div class="avatar-edit">
                                                            <input type='file' id="imageUpload" accept=".png, .jpg, .jpeg" name="image" />
                                                            <label for="imageUpload"></label>
                                                        </div>
                                                        <div class="avatar-preview">
                                                            <div id="imagePreview" style="background-image: url(<%=user.getPicture()%>);">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="mt-3">
                                                    <h4 style="color: white"><%=user.getName()%></h4>
                                                    <ul
                                                        class="social-media social-color justify-content-center d-flex lab-ul">
                                                        <li>
                                                            <a href="https://www.facebook.com/anhtuan141001"
                                                               class="facebook"><i class="icofont-facebook" style="color: rgb(254, 254, 254)"></i></a>
                                                        </li>
                                                        <li>
                                                            <a href="https://www.instagram.com/anhtuan_1410/"
                                                               class=""><i class="icofont-instagram" style="color: rgb(254, 254, 254)"></i></a>
                                                        </li>
                                                        <li>
                                                            <a href="mailto:tuananhtran1410@gmail.com"
                                                               class=" "><i class="fa fa-envelope" style="color: rgb(254, 254, 254)"></i></a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-8">
                                            <div class="card-block">

                                                <div class="row">
                                                    <div class="col-sm-3">
                                                        <h6 class="m-b-10 f-w-600 mau "><i class="fa fa-user"></i> Full Name</h6>
                                                    </div>                    
                                                    <div class="col-sm-9 text-secondary">
                                                        <div class="col-sm-9 text-secondary">
                                                            <input type="text" value="<%=user.getName()%>" class="form-control" name="name">
                                                        </div>
                                                        <div>
                                                            <span style="color:red"><%=(request.getAttribute("ERROR") == null) ? "" : error.getNameError()%></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600 mau"></h6>


                                                <div class="row">
                                                    <div class="col-sm-3">
                                                        <h6 class="m-b-10 f-w-600 mau"><i class="fa fa-envelope"></i> Email</h6>
                                                    </div>        
                                                    <div class="col-sm-9 text-secondary">
                                                        <div class="col-sm-9 text-secondary">
                                                            <input type="text" value="<%=user.getEmail()%>" class="form-control" name="email">
                                                        </div>
                                                        <div>
                                                            <span style="color:red"><%=(request.getAttribute("ERROR") == null) ? "" : error.getEmailError()%></span>
                                                        </div>
                                                    </div>         
                                                </div>
                                                <h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600 mau"></h6>


                                                <div class="row">
                                                    <div class="col-sm-3">
                                                        <h6 class="m-b-10 f-w-600 mau"><i class="icofont-phone"></i> Phone</h6>
                                                    </div>
                                                    <div class="col-sm-9 text-secondary">
                                                        <div class="col-sm-9 text-secondary">
                                                            <%if (user.getPhoneNumber() != null) {%>
                                                            <input type="text" class="form-control" value="<%=user.getPhoneNumber()%>" name="phone">
                                                            <%} else {%>
                                                            <input type="text" class="form-control" name="phone">
                                                            <%}%>
                                                        </div>
                                                        <div>
                                                            <span style="color:red"><%=(request.getAttribute("ERROR") == null) ? "" : error.getPhoneNumberError()%></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600 mau"></h6>

                                                <div class="row">
                                                    <div class="col-sm-3">
                                                        <h6 class="m-b-10 f-w-600 mau"><i class="fa fa-user"></i> Type</h6>
                                                    </div>
                                                    <div class="col-sm-9 text-secondary">
                                                        <select class="form-select " style="color: black" name="usertype">
                                                            <%
                                                                ArrayList<UserDTO> list = new ArrayList<UserDTO>();
                                                                list = (ArrayList<UserDTO>) request.getAttribute("listUserType");
                                                                for (UserDTO c : list) {
                                                            %>
                                                            <%if (c.getTypeName().equals(user.getTypeName())) {%>
                                                            <option selected="" hidden="" value="<%= user.getTypeID()%>"><%= user.getTypeName()%> </option>
                                                            <%}%>
                                                            <option value="<%=c.getTypeID()%>" class="others"><%=c.getTypeName()%> </option>
                                                            <%}%>
                                                        </select>
                                                        <div>
                                                            <span style="color:red"><%=(request.getAttribute("ERROR") == null) ? "" : error.getTypeIDError()%></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600 mau"></h6>

                                                <div class="row">
                                                    <div class="col-sm-3">
                                                        <h6 class="m-b-10 f-w-600 mau"> <i class="fa fa-user"></i> Gender</h6>
                                                    </div>          
                                                    <div class="col-sm-9 text-secondary">

                                                        <select  class="form-select" style="color: black" name="gender">
                                                            <% if (user.getGender().equals("Male")) {%>
                                                            <option selected="" hidden="" value="<%= user.getGender()%>"><%= user.getGender()%> </option>
                                                            <%} else if (user.getGender().equals("Female")) {%>
                                                            <option selected="" hidden="" value="<%= user.getGender()%>"><%= user.getGender()%> </option>
                                                            <%}%>
                                                            <option  value="Male" class="others">Male</option>
                                                            <option  value="Female" class="others">Female</option>
                                                        </select>
                                                        <span style="color:red" ><%=(request.getAttribute("ERROR") == null) ? "" : error.getGenderError()%></span>
                                                    </div>
                                                    <div class="fa-align-center" >

                                                    </div>
                                                </div>
                                                <h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600 mau"></h6>

                                                <div class="row">
                                                    <input type="hidden" value="<%=user.getId()%>" name="uid">
                                                    <input type="hidden" value="<%=user.getPassword()%>" name="password" >
                                                    <div class="col-sm-12">
                                                        <a class="btn btn-info " target="__blank"
                                                           href="MainController?action=dataType&page=changePassword">Change Password</a>&emsp;
                                                        <button type="submit" name="action" value="updateprofile" class="btn btn-info " target="__blank">Save                              
                                                            <input type="hidden" name="page" value="UserProfileController" />
                                                        </button>&emsp;
                                                        <a class="btn btn-info " target="__blank" href="MainController?action=profilepage">Cancel</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <%} else if (test.equals("Can Change Pass")) {%>
                                <form action="MainController" method="POST">
                                    <%if ((String) request.getAttribute("check") == null || (String) request.getAttribute("check") == "wrong") {%>
                                    <div class="row m-l-0 m-r-0">
                                        <div class="col-sm-4 bg-c-lite-green user-profile">
                                            <div class="d-flex flex-column align-items-center text-center">
                                                <div class="avatar-upload">
                                                    <div class="avatar-preview">
                                                        <div id="imagePreview" style="background-image: url(<%=user.getPicture()%>);">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="mt-3">
                                                    <h4 style="color: white"><%=user.getName()%></h4>
                                                    <ul
                                                        class="social-media social-color justify-content-center d-flex lab-ul">
                                                        <li>
                                                            <a href="https://www.facebook.com/anhtuan141001"
                                                               class="facebook"><i class="icofont-facebook" style="color: rgb(254, 254, 254)"></i></a>
                                                        </li>
                                                        <li>
                                                            <a href="https://www.instagram.com/anhtuan_1410/"
                                                               class=""><i class="icofont-instagram" style="color: rgb(254, 254, 254)"></i></a>
                                                        </li>
                                                        <li>
                                                            <a href="mailto:tuananhtran1410@gmail.com"
                                                               class=" "><i class="fa fa-envelope" style="color: rgb(254, 254, 254)"></i></a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-8">
                                            <div class="card-block">
                                                <div class="row">
                                                    <div class="col-sm-3">
                                                        <h6 class="m-b-10 f-w-600 mau "><i class="fa fa-user"></i> Old Password</h6>
                                                    </div>                    
                                                    <div class="col-sm-9 text-secondary">
                                                        <div class="col-sm-9 text-secondary">
                                                            <input type="password" class="form-control" name="oldPass">
                                                        </div>
                                                        <div>
                                                            <span style="color:red"><%=(request.getAttribute("ERROR") == null) ? "" : error.getOldPasswordError()%></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600 mau"></h6>

                                                <%} else if ((String) request.getAttribute("check") == "correct") {%>
                                                <div class="row m-l-0 m-r-0">
                                                    <div class="col-sm-4 bg-c-lite-green user-profile">
                                                        <div class="d-flex flex-column align-items-center text-center">
                                                            <div class="avatar-upload">
                                                                <div class="avatar-preview">
                                                                    <div id="imagePreview" style="background-image: url(<%=user.getPicture()%>);">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="mt-3">
                                                                <h4 style="color: white"><%=user.getName()%></h4>
                                                                <ul
                                                                    class="social-media social-color justify-content-center d-flex lab-ul">
                                                                    <li>
                                                                        <a href="https://www.facebook.com/anhtuan141001"
                                                                           class="facebook"><i class="icofont-facebook" style="color: rgb(254, 254, 254)"></i></a>
                                                                    </li>
                                                                    <li>
                                                                        <a href="https://www.instagram.com/anhtuan_1410/"
                                                                           class=""><i class="icofont-instagram" style="color: rgb(254, 254, 254)"></i></a>
                                                                    </li>
                                                                    <li>
                                                                        <a href="mailto:tuananhtran1410@gmail.com"
                                                                           class=" "><i class="fa fa-envelope" style="color: rgb(254, 254, 254)"></i></a>
                                                                    </li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-8">
                                                        <div class="card-block">
                                                            <div class="row">
                                                                <div class="col-sm-3">
                                                                    <h6 class="m-b-10 f-w-600 mau "><i class="fa fa-user"></i> New Password</h6>
                                                                </div>                    
                                                                <div class="col-sm-9 text-secondary">
                                                                    <div class="col-sm-9 text-secondary">
                                                                        <input type="password" class="form-control" name="newPass">
                                                                    </div>
                                                                    <div>
                                                                        <span style="color:red"><%=(request.getAttribute("ERROR") == null) ? "" : error.getPasswordError()%></span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600 mau"></h6>
                                                            <div class="row">
                                                                <div class="col-sm-3">
                                                                    <h6 class="m-b-10 f-w-600 mau "><i class="fa fa-user"></i> Confirm New Password</h6>
                                                                </div>                    
                                                                <div class="col-sm-9 text-secondary">
                                                                    <div class="col-sm-9 text-secondary">
                                                                        <input type="password" class="form-control" name="confirmNewPass">
                                                                    </div>
                                                                    <div>
                                                                        <span style="color:red"><%=(request.getAttribute("ERROR") == null) ? "" : error.getPasswordConfirmError()%></span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600 mau"></h6>
                                                            <%}%>
                                                            <div class="row">
                                                                <input type="hidden" value="<%=user.getId()%>" name="uid">
                                                                <input type="hidden" value="<%=user.getPassword()%>" name="password" >
                                                                <div class="col-sm-12">
                                                                    <a class="btn btn-info " target="__blank" href="UserDataController?page=UserProfileController">Edit</a>&emsp;
                                                                    <%if ((String) request.getAttribute("check") == null || (String) request.getAttribute("check") == "wrong") {%>
                                                                    <button type="submit" name="action" value="changePassword" class="btn btn-info " target="__blank">Change
                                                                        <input type="hidden" name="userID" value="<%=user.getId()%>" />
                                                                        <input type="hidden" name="page" value="changePassword" />
                                                                    </button>
                                                                    <%} else if ((String) request.getAttribute("check") == "correct") {%>
                                                                    <%String getCorrectOldPass = (String) request.getAttribute("getCorrectOldPass");%>
                                                                    <button type="submit" name="action" value="changePassword" class="btn btn-info " target="__blank">Save
                                                                        <input type="hidden" name="userID" value="<%=user.getId()%>" />
                                                                        <input type="hidden" name="page" value="changePassword" />
                                                                        <input type="hidden" name="oldPassWhenChange" value="<%=getCorrectOldPass%>" />
                                                                    </button>
                                                                    <%}%>
                                                                    &emsp;
                                                                    <a class="btn btn-info " target="__blank" href="MainController?action=profilepage">Cancel</a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                </form>
                                                <%}%>
                                                <!--                                                    <h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600 mau"></h6>-->
                                            </div>
                                        </div>
                                    </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <footer class="footer-section" style="background-image: url(UserCSS/kagont/assets/images/bg-images/footer-bg.png);">
                <div class="footer-top">
                    <div class="container">
                        <div class="row g-3 justify-content-center g-lg-0">
                            <div class="col-lg-4 col-sm-6 col-12">
                                <div class="footer-top-item lab-item">
                                    <div class="lab-inner">
                                        <div class="lab-thumb">
                                            <img src="UserCSS/kagont/assets/images/footer/footer-top/01.png" alt="Phone-icon">
                                        </div>
                                        <div class="lab-content">
                                            <a href="#">Phone Number : +84 528 68 71 48</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-sm-6 col-12">
                                <div class="footer-top-item lab-item">
                                    <div class="lab-inner">
                                        <div class="lab-thumb">
                                            <img src="UserCSS/kagont/assets/images/footer/footer-top/02.png" alt="email-icon">
                                        </div>
                                        <div class="lab-content">
                                            <a href="emailto: daihoc.hcm@fpt.edu.vn">Email : daihoc.hcm@fpt.edu.vn</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-sm-6 col-12">
                                <div class="footer-top-item lab-item">
                                    <div class="lab-inner">
                                        <div class="lab-thumb">
                                            <img src="UserCSS/kagont/assets/images/footer/footer-top/03.png" alt="location-icon">
                                        </div>
                                        <div class="lab-content">
                                            <a href="#">Address: Tp. Thủ Đức, TP.HCM.</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="footer-middle padding-tb">
                    <div class="container">
                        <div class="row shape-c">
                            <div class="col-lg-4 col-md-6 col-12">
                                <div class="footer-middle-item-wrapper">
                                    <div class="footer-middle-item mb-5 mb-lg-0">
                                        <div class="fm-item-title">
                                            <h5>About F.E.M</h5>
                                        </div>
                                        <div class="fm-item-content">
                                            <p class="mb-30">Trang thông tin chính thức về chương trình dành cho FPTU
                                                campus
                                                HCM: hoạt động CLB, chuyên đề cập nhật kiến thức mới, training kỹ năng
                                                sinh
                                                viên, hoạt động trải nghiệm thực tế.</p>
                                            <img class="rounded footer-abt-img"
                                                 src="UserCSS/kagont/assets/images/footer/footer-middle/370X200.jpg" alt="about-image">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-6 col-12">
                                <div class="footer-middle-item-wrapper">
                                    <div class="footer-middle-item mb-5 mb-lg-0">
                                        <div class="fm-item-title">
                                            <h5>our Recent news</h5>
                                        </div>
                                        <div class="fm-item-content">
                                            <div class="fm-item-widget lab-item">
                                                <div class="lab-inner">
                                                    <div class="lab-thumb">
                                                        <a
                                                            href="https://hcmuni.fpt.edu.vn/o-dai-hoc-fpt-co-nhung-cau-lac-bo-nao">
                                                            <img src="UserCSS/kagont/assets/images/footer/footer-middle/100X80.01.jpg"
                                                                 alt="footer-widget-img"></a>
                                                    </div>
                                                    <div class="lab-content">
                                                        <h6><a
                                                                href="https://hcmuni.fpt.edu.vn/o-dai-hoc-fpt-co-nhung-cau-lac-bo-nao">
                                                                Ở Đại học FPT có những Câu lạc bộ nào?</a></h6>
                                                        <p>September 20, 2022</p>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="fm-item-widget lab-item">
                                                <div class="lab-inner">
                                                    <div class="lab-thumb">
                                                        <a
                                                            href="https://hcmuni.fpt.edu.vn/3-dieu-khien-nhieu-sinh-vien-ngung-mo-fvc"><img
                                                                src="UserCSS/kagont/assets/images/footer/footer-middle/100X80.02.jpg"
                                                                alt="footer-widget-img"></a>
                                                    </div>
                                                    <div class="lab-content">
                                                        <h6><a
                                                                href="https://hcmuni.fpt.edu.vn/3-dieu-khien-nhieu-sinh-vien-ngung-mo-fvc">3
                                                                điều khiến nhiều sinh viên “ngưỡng mộ” FVC.</a></h6>
                                                        <p>December 23, 2021</p>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="fm-item-widget lab-item">
                                                <div class="lab-inner">
                                                    <div class="lab-thumb">
                                                        <a
                                                            href="https://hcmuni.fpt.edu.vn/an-tuong-voi-cau-lac-bo-truyen-thong-gao-coi-cua-truong-dai-hoc-fpt"><img
                                                                src="UserCSS/kagont/assets/images/footer/footer-middle/100X80.03.jpg"
                                                                alt="footer-widget-img"></a>
                                                    </div>
                                                    <div class="lab-content">
                                                        <h6><a
                                                                href="https://hcmuni.fpt.edu.vn/an-tuong-voi-cau-lac-bo-truyen-thong-gao-coi-cua-truong-dai-hoc-fpt">Ấn
                                                                tượng với Câu lạc bộ Truyền thông "gạo cội" của trường
                                                                Đại
                                                                học
                                                                FPT
                                                            </a></h6>
                                                        <p>July 29, 2021</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-6 col-12">
                                <div class="footer-middle-item-wrapper">
                                    <div class="footer-middle-item-3 mb-5 mb-lg-0">
                                        <div class="fm-item-title">
                                            <h5>OUR NEWSLETTER</h5>
                                        </div>
                                        <div class="fm-item-content">
                                            <p>F.E.M is a event organization supported
                                                by community leaders</p>

                                            <div class="form-group">
                                                <input type="email" class="form-control" placeholder="Enter email">
                                            </div>
                                            <a href="mailto:daihoc.hcm@fpt.edu.vn">
                                                <button type="submit" class="lab-btn">Send Massage <i
                                                        class="icofont-paper-plane"></i></button>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="footer-bottom">
                    <div class="container">
                        <div class="row">
                            <div class="col-12">
                                <div class="footer-bottom-content text-center">
                                    <p>&copy;2022 <a href="index.html">F.E.M</a> - Best For Event Management Schedule.
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </footer>
            <!-- Footer Section end here -->



            <!-- scrollToTop start here -->
            <a href="#" class="scrollToTop"><i class="icofont-bubble-up"></i><span class="pluse_1"></span><span
                    class="pluse_2"></span></a>
            <!-- scrollToTop ending here -->
            <script src="UserCSS/kagont/assets/js/jquery.js"></script>
            <script src="UserCSS/kagont/assets/js/fontawesome.min.js"></script>
            <script src="UserCSS/kagont/assets/js/waypoints.min.js"></script>
            <script src="UserCSS/kagont/assets/js/bootstrap.bundle.min.js"></script>
            <script src="UserCSS/kagont/assets/js/swiper.min.js"></script>
            <script src="UserCSS/kagont/assets/js/circularProgressBar.min.js"></script>
            <script src="UserCSS/kagont/assets/js/isotope.pkgd.min.js"></script>
            <script src="UserCSS/kagont/assets/js/lightcase.js"></script>
            <script src="UserCSS/kagont/assets/js/functions.js"></script>

            <script> window.onclick = function (e) {
                                                if (!e.target.matches('.nut_dropdown')) {
                                                    var noiDungDropdown = document.querySelector(".noidung_dropdown");
                                                    if (noiDungDropdown.classList.contains('hienThi')) {
                                                        noiDungDropdown.classList.remove('hienThi');
                                                    }
                                                }
                                            };
            </script>

            <script>  function hamDropdown() {
                    document.querySelector(".noidung_dropdown").classList.toggle("hienThi");
                }
            </script>
            <script> window.onclick = function (e) {
                    if (!e.target.matches('.nut_dropdown')) {
                        var noiDungDropdown = document.querySelector(".noidung_dropdown");
                        if (noiDungDropdown.classList.contains('hienThi')) {
                            noiDungDropdown.classList.remove('hienThi');
                        }
                    }
                };
            </script>
            <script>
                function readURL(input) {
                    if (input.files && input.files[0]) {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            $('#imagePreview').css('background-image', 'url(' + e.target.result + ')');
                            $('#imagePreview').hide();
                            $('#imagePreview').fadeIn(650);
                        }
                        reader.readAsDataURL(input.files[0]);
                    }
                }
                $("#imageUpload").change(function () {
                    readURL(this);
                });
            </script>
            </body>

            </html>