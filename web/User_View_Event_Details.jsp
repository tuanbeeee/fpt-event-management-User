<%@page import="sample.notification.NotificationDTO"%>
<%@page import="sample.comment.CommentSectionDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sample.users.ParticipantsDTO"%>
<%@page import="sample.users.ParticipantsDTO"%>
<%@page import="sample.posts.EventPost"%>
<%@page import="sample.users.UserDTO"%>
<!DOCTYPE html>
<html lang="zxx">

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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

        <script src='//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.0-beta.1/angular.js'></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.0-beta.1/angular-animate.js'></script>
        <script src="UserCSS/kagont/assets/css/script.js"></script>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
                integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
                integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
                integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
    </head>
    <style>
        .comments-area {
            background: #f0f0f0;
            padding: 50px 30px;
            margin-top: 50px;
        }

        @media (max-width: 414px) {
            .comments-area {
                padding: 50px 8px;
            }
        }

        .comments-area h4 {
            text-align: center;
            margin-bottom: 50px;
            color: #002347;
            font-size: 18px;
        }

        .comments-area h5 {
            font-size: 16px;
            margin-bottom: 0px;
        }

        .comments-area a {
            color: #002347;
        }

        .comments-area .comment-list {
            padding-bottom: 30px;
        }

        .comments-area .comment-list:last-child {
            padding-bottom: 0px;
        }

        .comments-area .comment-list.left-padding {
            padding-left: 35px;
        }

        @media (max-width: 413px) {
            .comments-area .comment-list .single-comment h5 {
                font-size: 12px;
            }
            .comments-area .comment-list .single-comment .date {
                font-size: 11px;
            }
            .comments-area .comment-list .single-comment .comment {
                font-size: 10px;
            }
        }

        .comments-area .thumb {
            margin-right: 40px;
        }

        .comments-area .date {
            font-size: 13px;
            color: #777777;
            margin-bottom: 13px;
        }

        .comments-area .comment {
            color: #777777;
            margin-bottom: 0px;
        }

        .comments-area .btn-reply {
            background-color: #fff;
            color: #002347;
            border: 1px solid #eee;
            padding: 2px 18px;
            font-size: 12px;
            display: block;
            font-weight: 600;
            -webkit-transition: all 0.3s ease 0s;
            -moz-transition: all 0.3s ease 0s;
            -o-transition: all 0.3s ease 0s;
            transition: all 0.3s ease 0s;
        }

        .comments-area .btn-reply:hover {
            background: #fdc632;
            color: #002347;
        }
        * {
            margin: 0;
            padding: 0;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }

        a {
            color: #03658c;
            text-decoration: none;
            font-family:  Vollkorn;
        }

        ul {
            list-style-type: none;
        }

        body {
            /*            font-family: 'Roboto', Arial, Helvetica, Sans-serif, Verdana;*/
            background: #dee1e3;
        }

        span {
            font-family:  Vollkorn;
        }

        /** ====================
         * Lista de Comentarios
         =======================*/
        .comments-container {
            margin: 60px auto 15px;
            width: 768px;
        }

        .comments-container h1 {
            font-size: 36px;
            color: #283035;
            font-weight: 400;
        }

        .comments-container h1 a {
            font-size: 18px;
            font-weight: 700;
        }

        .comments-list {
            margin-top: 30px;
            position: relative;
        }

        /**
         * Lineas / Detalles
         -----------------------*/
        .comments-list:before {
            /*            content: '';
                        width: 2px;
                        height: 100%;
                        background: #c7cacb;
                        position: absolute;
                        left: 32px;
                        top: 0;*/
        }

        .comments-list:after {
            /*            content: '';
                        position: absolute;
                        background: #c7cacb;
                        bottom: 0;
                        left: 27px;
                        width: 7px;
                        height: 7px;
                        border: 3px solid #dee1e3;
                        -webkit-border-radius: 50%;
                        -moz-border-radius: 50%;
                        border-radius: 50%;*/
        }

        .reply-list:before, .reply-list:after {display: none;}
        .reply-list li:before {
            /*            content: '';
                        width: 60px;
                        height: 2px;
                        background: #c7cacb;
                        position: absolute;
                        top: 25px;
                        left: -55px;*/
        }


        .comments-list li {
            margin-bottom: 15px;
            display: block;
            position: relative;
        }

        .comments-list li:after {
            content: '';
            display: block;
            clear: both;
            height: 0;
            width: 0;
        }

        .reply-list {
            padding-left: 88px;
            clear: both;
            margin-top: 15px;
        }
        /**
         * Avatar
         ---------------------------*/
        .comments-list .comment-avatar {
            width: 65px;
            height: 65px;
            margin-top: -17px;
            position: relative;
            z-index: 99;
            float: left;
            border: 3px solid #FFF;
            vertical-align: middle;
            -webkit-border-radius: 4px;
            -moz-border-radius: 4px;
            border-radius: 50%;
            -webkit-box-shadow: 0 1px 2px rgba(0,0,0,0.2);
            -moz-box-shadow: 0 1px 2px rgba(0,0,0,0.2);
            box-shadow: 0 1px 2px rgba(0,0,0,0.2);
            overflow: hidden;
        }

        .comments-list .comment-avatar img {
            width: 100%;
            height: 100%;
        }

        .reply-list .comment-avatar {
            margin-top: -7px;
            width: 50px;
            height: 50px;
        }

        .comment-main-level:after {
            content: '';
            width: 0;
            height: 0;
            display: block;
            clear: both;
        }
        /**
         * Caja del Comentario
         ---------------------------*/
        .comments-list .comment-box {
            width: 680px;
            float: right;
            position: relative;
            -webkit-box-shadow: 0 1px 1px rgba(0,0,0,0.15);
            -moz-box-shadow: 0 1px 1px rgba(0,0,0,0.15);
            box-shadow: 0 1px 1px rgba(0,0,0,0.15);
        }

        .comments-list .comment-box1 {
            width: 680px;
            float: right;
            position: relative;
            /*            -webkit-box-shadow: 0 1px 1px rgba(0,0,0,0.15);
                        -moz-box-shadow: 0 1px 1px rgba(0,0,0,0.15);
                        box-shadow: 0 1px 1px rgba(0,0,0,0.15);*/
        }

        .comments-list .comment-box:before, .comments-list .comment-box:after {
            content: '';
            height: 0;
            width: 0;
            position: absolute;
            display: block;
            border-width: 10px 12px 10px 0;
            border-style: solid;
            border-color: transparent #FCFCFC;
            top: 8px;
            left: -11px;
        }

        .comments-list .comment-box:before {
            border-width: 11px 13px 11px 0;
            border-color: transparent rgba(0,0,0,0.05);
            left: -12px;
        }

        .reply-list .comment-box {
            width: 610px;
        }
        .comment-box .comment-head {
            background: #FCFCFC;
            padding: 10px 12px;
            border-bottom: 1px solid #E5E5E5;
            overflow: hidden;
            -webkit-border-radius: 4px 4px 0 0;
            -moz-border-radius: 4px 4px 0 0;
            border-radius: 4px 4px 0 0;
        }

        .comment-box .comment-head i {
            float: right;
            margin-left: 14px;
            position: relative;
            top: 2px;
            color: #A6A6A6;
            cursor: pointer;
            -webkit-transition: color 0.3s ease;
            -o-transition: color 0.3s ease;
            transition: color 0.3s ease;
        }

        .comment-box .comment-head i:hover {
            color: #03658c;
        }

        .comment-box .comment-name {
            color: #283035;
            font-size: 14px;
            font-weight: 700;
            float: left;
            margin-right: 10px;
        }

        .comment-box .comment-name a {
            color: #283035;
        }

        .comment-box .comment-head span {
            float: left;
            color: #999;
            font-size: 13px;
            position: relative;
            top: 1px;
        }

        .comment-box .comment-content {
            background: #FFF;
            padding: 12px;
            font-size: 15px;
            color: #595959;
            -webkit-border-radius: 0 0 4px 4px;
            -moz-border-radius: 0 0 4px 4px;
            border-radius: 0 0 4px 4px;
        }

        .comment-box .comment-name.by-author, .comment-box .comment-name.by-author a {color: #03658c;}
        .comment-box .comment-name.by-author:after {
            content: 'autor';
            background: #03658c;
            color: #FFF;
            font-size: 12px;
            padding: 3px 5px;
            font-weight: 700;
            margin-left: 10px;
            -webkit-border-radius: 3px;
            -moz-border-radius: 3px;
            border-radius: 3px;
        }

        /** =====================
         * Responsive
         ========================*/
        @media only screen and (max-width: 766px) {
            .comments-container {
                width: 480px;
            }

            .comments-list .comment-box {
                width: 390px;
            }

            .reply-list .comment-box {
                width: 320px;
            }
        }
        /* Nút Dropdown*/
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

    </style>

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
                                    <a href="MainController?action=homepage&userID=<%=user.getId()%>"><i class="fa fa-home"></i>Home</a>
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
                                        <a  onclick="hamDropdown()"  class="nut_dropdown fa fa-bell" style="color: white"></a>
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
        <%EventPost getEventInfo = new EventPost();
            getEventInfo = (EventPost) request.getAttribute("USER_VIEW_EVENT_DETAIL");%>
        <section class="page-header bg_img padding-tb">
            <div class="overlay"></div>
            <div class="container">
                <div class="page-header-content-area">
                    <h4 class="ph-title">Event Single</h4>
                    <ul class="lab-ul">
                        <li><a href="MainController?action=homepage">Home</a></li>
                        <li><a href="MainController?action=viewEventList">Event</a></li>
                        <li><a class="active">Event Single</a></li>
                    </ul>
                </div>
            </div>
        </section>
        <!-- Page Header Section Ending Here -->

        <!-- Scholar single section start Here -->
        <section class="shop-single padding-tb">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-10 col-12 sticky-widget">
                        <div class="product-details">
                            <div class="row align-items-center">
                                <div class="col-md-6 col-12">
                                    <div class="product-thumb">
                                        <div class="swiper-container gallery-top">
                                            <div class="swiper-wrapper">
                                                <div class="swiper-slide">
                                                    <div class="shop-item">
                                                        <div class="shop-thumb">
                                                            <img src="<%=getEventInfo.getImgUrl()%>" alt="shop-single">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Button trigger modal -->

                                        <%UserDTO getUserID = (UserDTO) request.getSession().getAttribute("LOGIN_USER");
                                            ParticipantsDTO dto = new ParticipantsDTO();
                                            dto = (ParticipantsDTO) request.getAttribute("CHECK_PARTICIPANTS"); %>
                                        <%if (request.getAttribute("CHECK_PARTICIPANTS") == null) {%>
                                        <button type="button" style="width: 415px" class="btn btn-primary primary1" data-toggle="modal"  
                                                data-target="#exampleModalCenter">
                                            Register
                                        </button>
                                        <%} else {%>
                                        <button type="button" style="width: 415px" class="btn btn-info primary1" data-toggle="modal" disabled=""
                                                data-target="#exampleModalCenter">
                                            Register
                                        </button>
                                        <%}%>
                                        <!-- Modal -->
                                        <form action="MainController" method="POST">
                                            <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog"
                                                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" style="color: rgb(255, 255, 255);"
                                                                id="exampleModalLongTitle">Confirm</h5>


                                                        </div>
                                                        <div class="modal-body" style="color: black;">
                                                            DO YOU WANT TO JOIN EVENT ?
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="submit" class="btn btn-primary" name="action" value="UserParticipants">
                                                                <input type="hidden" name="username" value="<%=getUserID.getId()%>">
                                                                <input type="hidden" name="eventID" value="<%=getEventInfo.getId()%>">
                                                                Yes</button>
                                                            <button type="button" class="btn btn-secondary"
                                                                    style="background-color: rgb(207, 6, 6);"
                                                                    data-dismiss="modal">No</button>

                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>

                                    </div>
                                </div>
                                <div class="col-md-6 col-12">
                                    <div class="post-content">
                                        <h4><%=getEventInfo.getTitle()%></h4>
                                        <p class="rating">
                                            <%=getEventInfo.getNumberOfView()%> views
                                        </p>
                                        <p>
                                            <%=getEventInfo.getContent()%><br><br><br>

                                            🕗Đơn vị tổ chức: <%=getEventInfo.getOrgID()%><br>

                                            🕗Thời gian: <%=getEventInfo.getTakePlaceDate()%><br>

                                            📍Địa điểm: <%=getEventInfo.getLocationName()%><br>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="review">

                            <div class="review-content review-content-show">
                                <div class="review-showing">

                                    <%ArrayList<CommentSectionDTO> list = new ArrayList<>();
                                        list = (ArrayList<CommentSectionDTO>) request.getAttribute("COMMENT");%>

                                    <div class="comments-area">
                                        <div class="comments-container">
                                            <h1>Comment</h1>
                                            <form action="MainController" method="POST">
                                                <div class="d-flex flex-row add-comment-section mt-4 mb-4" style="padding: 0px 5px">

                                                    <img class="img-fluid img-responsive rounded-circle mr-2" src="<%=user.getPicture()%>" width="38">
                                                    <input type="text" name="comment" class="form-control mr-3" placeholder="Add comment">
                                                    <button class="btn btn-primary" type="submit" name="action" value="UserCommemt">
                                                        <input type="hidden" name="userName" value="<%=user.getId()%>">
                                                        <input type="hidden" name="eventID" value="<%=getEventInfo.getId()%>">
                                                        Comment</button>

                                                </div>
                                            </form>
                                            <br>
                                            <ul id="comments-list" class="comments-list">                                               

                                                <%for (CommentSectionDTO cmt : list) {
                                                        if (cmt.replyID == 0) {
                                                %>                                             
                                                <li>
                                                    <div class="comment-main-level">

                                                        <div class="comment-avatar"><img src="<%=cmt.img%>" alt=""></div>

                                                        <div class="comment-box">
                                                            <div class="comment-head">
                                                                <h6 class="comment-name"><%=cmt.fullName%></h6>
                                                                <span><%=cmt.time%></span>
                                                                <span style="margin-left: 12px">
                                                                    <a type="" data-toggle="reply-form"
                                                                       data-target="<%=cmt.commentID%>" class="fa fa-reply"></a>
                                                                </span>
                                                            </div>
                                                            <div class="comment-content">
                                                                <%=cmt.content%>
                                                            </div>
                                                        </div>
                                                        <form action="MainController" method="POST"  id="<%=cmt.commentID%>" class="reply-form d-none">
                                                            <div class="comment-box1">
                                                                <div class="d-flex flex-row add-comment-section mt-4 mb-4" style="padding: 0px 5px">

                                                                    <img class="img-fluid img-responsive rounded-circle mr-2" src="<%=user.getPicture()%>" width="38">
                                                                    <textarea style="height: 38px" type="text" name="reply" class="form-control mr-3" placeholder="Add comment"></textarea>
                                                                    <button class="btn btn-primary" type="submit" name="action" value="UserCommemt">Comment
                                                                        <input type="hidden" name="userName" value="<%=user.getId()%>">
                                                                        <input type="hidden" name="eventID" value="<%=getEventInfo.getId()%>">
                                                                        <input type="hidden" name="replyID" value="<%=cmt.commentID%>">
                                                                    </button>

                                                                </div>
                                                            </div>
                                                            <br>
                                                        </form>
                                                    </div>
                                                    <br>
                                                    <ul class="comments-list reply-list">
                                                        <%for (CommentSectionDTO reply : list) {
                                                                if (reply.replyID == cmt.commentID) {
                                                        %>
                                                        <li>
                                                            <div class="comment-avatar"><img src="<%=reply.img%>" alt=""></div>

                                                            <div class="comment-box">
                                                                <div class="comment-head">
                                                                    <h6 class="comment-name"><%=reply.fullName%></h6>
                                                                    <span><%=reply.time%></span>
                                                                </div>
                                                                <div class="comment-content">
                                                                    <%=reply.content%>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <%}%>
                                                        <%}%>
                                                    </ul>
                                                </li>

                                                <%}%>
                                                <%}%>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="client-review">
                                        <div class="review-form">
                                            <div class="review-title">
                                                <h5 class="fw-bold">Add a Feedback</h5>
                                            </div>
                                            <span style="color:green"><%=(request.getAttribute("Message") == null) ? "" : request.getAttribute("Message")%></span>
                                            <form action="MainController" method="POST" class="row">
                                                <div class="col-md-12 col-12">
                                                    <textarea rows="8" name="content" placeholder="Type Here Message"></textarea>
                                                </div>
                                                <div class="col-12">
                                                    <button class="lab-btn" name="action" value="feedback" type="submit">Submit Feedback
                                                        <input type="hidden" name="userID" value="<%=user.getId()%>"> 
                                                        <input type="hidden" name="eventID" value="<%=getEventInfo.getId()%>"> 
                                                    </button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Scholar single section end Here -->

        <!-- Footer Section start here -->
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
                                        <p class="mb-30">Trang thông tin chính thức về chương trình dành cho FPTU campus
                                            HCM: hoạt động CLB, chuyên đề cập nhật kiến thức mới, training kỹ năng sinh
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
                                                            tượng với Câu lạc bộ Truyền thông "gạo cội" của trường Đại học
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
                                        <form>
                                            <div class="form-group">
                                                <input type="email" class="form-control" placeholder="Enter email">
                                            </div>
                                            <a href="mailto:daihoc.hcm@fpt.edu.vn">
                                                <button type="submit" class="lab-btn"><span>Send Massage</span> <i
                                                        class="icofont-paper-plane"></i></button>
                                            </a>
                                        </form>
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
                                <p>&copy;2022 <a href="index.html">F.E.M</a> - Best For Event Management Schedule.</p>
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
        <script>
                                            document.addEventListener(
                                                    "click",
                                                    function (event) {
                                                        var target = event.target;
                                                        var replyForm;
                                                        if (target.matches("[data-toggle='reply-form']")) {
                                                            replyForm = document.getElementById(target.getAttribute("data-target"));
                                                            replyForm.classList.toggle("d-none");
                                                        }
                                                    },
                                                    false
                                                    );
        </script>

        <script>  function hamDropdown() {
                document.querySelector(".noidung_dropdown").classList.toggle("hienThi");
            }</script>
        <script> window.onclick = function (e) {
                if (!e.target.matches('.nut_dropdown')) {
                    var noiDungDropdown = document.querySelector(".noidung_dropdown");
                    if (noiDungDropdown.classList.contains('hienThi')) {
                        noiDungDropdown.classList.remove('hienThi');
                    }
                }
            }
        </script>
    </body>

</html>