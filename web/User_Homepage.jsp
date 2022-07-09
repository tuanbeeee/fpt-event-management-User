<%@page import="sample.notification.NotificationDTO"%>
<%@page import="sample.blog.BlogDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sample.posts.EventPost"%>
<%@page import="sample.users.UserDTO"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>F.E.M</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="UserCSS/kagont/assets/images/x-icon/Biểu-tượng-không-chữ1.png">

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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
        <!-- RS5.0 Main Stylesheet -->
        <link rel="stylesheet" type="text/css" href="UserCSS/kagont/assets/css/revolution/css/settings.css">
        <link rel="stylesheet" type="text/css" href="UserCSS/kagont/assets/css/revolution/css/layers.css">
        <link rel="stylesheet" type="text/css" href="UserCSS/kagont/assets/css/revolution/css/navigation.css">

        <style>
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
            .cut {
                display:inline-block;
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
                max-width: 35ch;
            }
        </style>
    </head>

    <body>
        <form action="MainController">
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
                                        <% if (session.getAttribute("LOGIN_USER") == null) {%>
                                        <a href="MainController?action=loginpage&page=User_Login_And_Register.jsp&request=LOGIN"><i class="fa fa-cube"></i> Club</a>
                                        <%} else {%>
                                        <a href="MainController?action=clubList&userID=<%=user.getId()%>"><i class="fa fa-cube"></i> Club</a>
                                        <%}%>
                                    </li>
                                    <li>
                                        <% if (session.getAttribute("LOGIN_USER") == null) {%>
                                        <a href="MainController?action=loginpage&page=User_Login_And_Register.jsp&request=LOGIN"><i class="fa fa-calendar-check"></i>Event</a>
                                        <%} else {%>
                                        <a href="MainController?action=viewEventList&userID=<%=user.getId()%>"><i class="fa fa-calendar-check"></i>Event</a>
                                        <%}%>
                                    </li>
                                    <li>
                                        <% if (session.getAttribute("LOGIN_USER") == null) {%>
                                        <a href="MainController?action=loginpage&page=User_Login_And_Register.jsp&request=LOGIN"><i class="fa fa-newspaper"></i>Blog</a>
                                        <%} else {%>
                                        <a href="MainController?action=ViewBlogList&userID=<%=user.getId()%>"><i class="fa fa-newspaper"></i>Blog</a>
                                        <%}%>
                                    </li>
                                    <li><a href="contact.html"><i class="icofont-ui-message"></i>Contact</a></li>

                                    <% if (session.getAttribute("LOGIN_USER") == null) {%>
                                    <li>
                                        <a href="MainController?action=loginpage&page=User_Login_And_Register.jsp&request=LOGIN"><i class="icofont-login"></i>Log In</a>
                                    </li>
                                    <li>
                                        <a href="#0"><i class="icofont-user"></i>Sign Up</a>
                                        <ul class="submenu">
                                            <li><a href="MainController?action=loginpage&page=User_Login_And_Register.jsp&request=REGISTER"><i class="icofont-user">&emsp;</i>Sign Up With User</a></li>
                                            <li><a href="Org_Register.jsp"><i class="icofont-user">&emsp;</i>Sign Up With Organization</a></li>
                                        </ul>                              
                                    </li>
                                    <%} else {%>
                                    <li>
                                        <a href="#0"><i class="icofont-user"></i><%= user.getName()%></a>
                                        <ul class="submenu">
                                            <li><a href="MainController?action=profilepage">Profile<i class="icofont-pixels"></i></a></li>
                                            <li><a href="MainController?action=logout&page=US" onclick="return confirm('Do you want to log out ?')" >Logout<i class="icofont-delivery-time"></i></a>
                                            </li>
                                        </ul>
                                    </li>

                                    <li>
                                        <%ArrayList<NotificationDTO> list = new ArrayList<>();
                                            list = (ArrayList<NotificationDTO>) request.getAttribute("GET_NOTIFICATION"); %>
                                        <div class="dropdown">
                                            <a  onclick="hamDropdown()"  class="nut_dropdown fa fa-bell"></a>
                                            <div class="noidung_dropdown">                                               
                                                <div class="cont">
                                                    <%for (NotificationDTO n : list) {%>
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

                                    <%}%>
                                </ul>
                                <!-- partial:index.partial.html -->

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

            <!-- Hero Section====================================== -->
            <section id="slider" class="slider slide-overlay-dark">
                <!-- START REVOLUTION SLIDER 5.0 -->
                <div class="rev_slider_wrapper">
                    <div id="slider1" class="rev_slider " data-version="5.0">
                        <ul>
                            <!-- slide 1 -->
                            <li data-transition="zoomin" data-slotamount="default" data-easein="Power4.easeInOut"
                                data-easeout="Power4.easeInOut" data-masterspeed="2000">
                                <!-- MAIN IMAGE -->
                                <img src="UserCSS/kagont/assets/images/header/banner1.jpg" alt="Slide Background Image">
                                <!-- LAYER NR. 1 -->
                                <section class="banner-section">
                                    <div class="container">
                                        <div class="banner-wrapper shape-a">
                                            <div class="row gy-5 align-items-center">
                                                <div class="col-lg-6 col-12">
                                                    <div class="banner-content">
                                                        <ul id="countdown" class="countdown count-down"
                                                            data-date="June 31, 2022 21:14:01">
                                                            <li class="clock-item"><span class="count-number days">56</span>
                                                                <p class="count-text">Days</p>
                                                            </li>

                                                            <li class="clock-item"><span class="count-number hours">16</span>
                                                                <p class="count-text">Hour</p>
                                                            </li>

                                                            <li class="clock-item"><span class="count-number minutes">25</span>
                                                                <p class="count-text">Min</p>
                                                            </li>

                                                            <li class="clock-item"><span class="count-number seconds">19</span>
                                                                <p class="count-text">Sec</p>
                                                            </li>
                                                        </ul>
                                                        <h1>FBT Event Management</h1>
                                                        <p>Chơi đi ngại chi</p>
                                                        <a href="MainController?action=loginpage&page=User_Login_And_Register.jsp&request=REGISTER" class="lab-btn"><span>Register Now</span>
                                                        </a>

                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </section>

                            </li>

                            <!-- slide 2 -->
                            <li data-transition="slideoverdown" data-slotamount="default" data-easein="Power4.easeInOut"
                                data-easeout="Power4.easeInOut" data-masterspeed="2000">
                                <!-- MAIN IMAGE -->
                                <!-- MAIN IMAGE -->
                                <img src="UserCSS/kagont/assets/images/header/11-7285.jpg" style="width: 100%;" alt="">
                                <!-- LAYER NR. 1 -->
                                <section class="banner-section">
                                    <div class="container">
                                        <div class="banner-wrapper shape-a">
                                            <div class="row gy-5 align-items-center">
                                                <div class="col-lg-6 col-12">
                                                    <div class="banner-content">
                                                        <ul id="countdown" class="countdown count-down"
                                                            data-date="June 31, 2022 21:14:01">
                                                            <li class="clock-item"><span class="count-number days">56</span>
                                                                <p class="count-text">Days</p>
                                                            </li>

                                                            <li class="clock-item"><span class="count-number hours">16</span>
                                                                <p class="count-text">Hour</p>
                                                            </li>

                                                            <li class="clock-item"><span class="count-number minutes">25</span>
                                                                <p class="count-text">Min</p>
                                                            </li>

                                                            <li class="clock-item"><span class="count-number seconds">19</span>
                                                                <p class="count-text">Sec</p>
                                                            </li>
                                                        </ul>
                                                        <h1>FBT Event Management</h1>
                                                        <p>Chơi đi ngại chi</p>
                                                        <a href="MainController?action=loginpage&page=User_Login_And_Register.jsp&request=REGISTER" class="lab-btn"><span>Register Now</span>
                                                        </a>

                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </section>
                            </li>

                            <!-- slide 3 -->
                            <li data-transition="zoomout" data-slotamount="default" data-easein="Power4.easeInOut"
                                data-easeout="Power4.easeInOut" data-masterspeed="2000">
                                <!-- MAIN IMAGE -->
                                <img src="UserCSS/kagont/assets/images/header/12.jpg" alt="Slide Background Image" width="1920" height="1280">
                                <!-- LAYER NR. 1 -->
                                <div class="tp-caption" data-x="center" data-hoffset="0" data-y="center" data-voffset="-65"
                                     data-whitespace="nowrap" data-width="none" data-height="none"
                                     data-frames='[{"delay":1500,"speed":1500,"frame":"0","from":"x:[-100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;","mask":"x:0px;y:0px;s:inherit;e:inherit;","to":"o:1;","ease":"Power3.easeInOut"},{"delay":"wait","speed":300,"frame":"999","to":"auto:auto;","ease":"Power3.easeInOut"}]'
                                     data-splitin="none" data-splitout="none" data-responsive_offset="on">
                                    <div class="slide--subheadline">Steak In Offers You</div>
                                </div>

                                <!-- LAYER NR. 2 -->
                                <div class="tp-caption" data-x="center" data-hoffset="0" data-y="center" data-voffset="0"
                                     data-whitespace="nowrap" data-width="none" data-height="none"
                                     data-frames='[{"delay":1750,"speed":1500,"frame":"0","from":"z:0;rX:0;rY:0;rZ:0;sX:0.9;sY:0.9;skX:0;skY:0;opacity:0;","to":"o:1;","ease":"Power3.easeInOut"},{"delay":"wait","speed":300,"frame":"999","to":"auto:auto;","ease":"Power3.easeInOut"}]'
                                     data-splitin="none" data-splitout="none" data-responsive_offset="on">
                                    <div class="slide--headline">Fresh & Tasty Meals</div>
                                </div>

                                <!-- LAYER NR. 3 -->
                                <div class="tp-caption" data-x="center" data-hoffset="0" data-y="center" data-voffset="100"
                                     data-width="none" data-height="none"
                                     data-frames='[{"delay":2000,"speed":2000,"frame":"0","from":"y:[100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;opacity:0;","mask":"x:0px;y:[100%];s:inherit;e:inherit;","to":"o:1;","ease":"Power2.easeInOut"},{"delay":"wait","speed":300,"frame":"999","to":"auto:auto;","ease":"Power3.easeInOut"}]'
                                     data-splitin="none" data-splitout="none" data-responsive_offset="on">
                                    <div class="slide--bio text--center">Steak In has the perfect place to enjoy fine food and
                                        great cocktails with <br>excellent service, in comfortable atmospheric surroundings.
                                    </div>
                                </div>
                            </li>

                        </ul>
                    </div>
                    <!-- END REVOLUTION SLIDER -->
                </div>
                <!-- END OF SLIDER WRAPPER -->
            </section>

            <!-- ==========About Section start Here========== -->
            <section class="about-section padding-tb padding-b">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-lg-10">
                            <div class="about-image">
                                <img src="https://i1.sndcdn.com/avatars-000299868797-urkzpd-t500x500.jpg" alt="about image">
                            </div>
                        </div>
                        <div class="col-lg-10">
                            <div class="section-header text-center">
                                <h2>Description</h2>
                                <p>About FEM</p>
                            </div>
                            <div class="section-wrapper text-center">
                                <p class="context2">Description right here
                                </p>
                                <div class="about-btn-grp">
                                    <a href="MainController?action=loginpage&page=User_Login_And_Register.jsp&request=REGISTER" class="lab-btn"><span>Register Now</span> </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- ==========About Section end Here========== -->

            <!-- ==========schedule Section start Here========== -->

            <%ArrayList<EventPost> list = new ArrayList<>(); %>
            <section class="schedule-section padding-tb padding-b bg-image">
                <div class="container">
                    <div class="section-header">
                        <h2>Event Schedule</h2>
                        <p>Event schedule for 2 following days</p>
                    </div>

                    <%list = (ArrayList<EventPost>) request.getAttribute("EVENT_TOMOROW");
                        String tomorrow = (String) request.getAttribute("GET_TOMORROW");%>


                    <div class="section-wrapper shape-b">
                        <div class="row gx-4 gy-5">
                            <div class="col-lg-6">
                                <div class="schedule-left schedule-pack">
                                    <h5><%=tomorrow%></h5>
                                    <div class="schedule-list" id="accordionExample">
                                        <%for (EventPost eventTomorrow : list) {%>
                                        <div class="accordion-item">
                                            <div class="accordion-header" id="headingOne">
                                                <button class="accordion-button collapsed" type="button"
                                                        data-bs-toggle="collapse" data-bs-target="#<%=eventTomorrow.getId()%>" aria-expanded="true"
                                                        aria-controls="<%=eventTomorrow.getId()%>">
                                                    <span class="accor-header-inner d-flex flex-wrap align-items-center">
                                                        <% if (session.getAttribute("LOGIN_USER") == null) {%>
                                                        <a href="MainController?action=loginpage&page=User_Login_And_Register.jsp&request=LOGIN"><span class="h7"><img src="<%=eventTomorrow.getImgURLCLB()%>" width="50px" height="50px" alt="speaker">&emsp;<%=eventTomorrow.getTitle()%></span></a>
                                                                <%} else {%>
                                                        <a href="MainController?action=viewEventDetail&EVENT_ID=<%=eventTomorrow.getId()%>&username=<%=user.getId()%>"><span class="h7"><img src="<%=eventTomorrow.getImgURLCLB()%>" width="50px" height="50px" alt="speaker">&emsp;<%=eventTomorrow.getTitle()%></span></a>
                                                                <%}%>                          
                                                    </span>

                                                </button>
                                            </div>
                                            <div id="<%=eventTomorrow.getId()%>" class="accordion-collapse collapse"
                                                 aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                                                <div class="accordion-body">
                                                    <p><%=eventTomorrow.getSummary()%></p>
                                                    <ul class="ev-schedule-meta d-flex flex-wrap">
                                                        <li><span><i class="icofont-user"></i></span><%=eventTomorrow.getSpeaker()%></li>
                                                        <li><span><i class="icofont-clock-time"></i></span><%=eventTomorrow.getEventTypeName()%></li>
                                                        <li><span><i class="icofont-location-pin"></i></span><%=eventTomorrow.getLocationName()%></li>

                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <%}%>
                                    </div>
                                </div>
                            </div>
                            <%list = (ArrayList<EventPost>) request.getAttribute("EVENT_THE_DAY_AFTER_TOMORROW");
                                String theDayAfterTomorrow = (String) request.getAttribute("GET_THE_DAY_AFTER_TOMORROW");%>
                            <div class="col-lg-6">
                                <div class="schedule-right schedule-pack">
                                    <h5><%=theDayAfterTomorrow%></h5>
                                    <div class="schedule-list" id="accordionExample-2">
                                        <%for (EventPost getTheDayAfterTomorrow : list) {%>
                                        <div class="accordion-item">
                                            <div class="accordion-header" id="heading-One">
                                                <button class="accordion-button collapsed" type="button"
                                                        data-bs-toggle="collapse" data-bs-target="#<%=getTheDayAfterTomorrow.getId()%>"
                                                        aria-expanded="true" aria-controls="<%=getTheDayAfterTomorrow.getId()%>">
                                                    <span class="accor-header-inner d-flex flex-wrap align-items-center">

                                                        <% if (session.getAttribute("LOGIN_USER") == null) {%>

                                                        <a href="MainController?action=loginpage&page=User_Login_And_Register.jsp&request=LOGIN"><span class="h7"><img src="<%=getTheDayAfterTomorrow.getImgURLCLB()%>" width="50px" height="50px" alt="speaker">&emsp;<%=getTheDayAfterTomorrow.getTitle()%></span></a>

                                                        <%} else {%>

                                                        <a href="MainController?action=viewEventDetail&EVENT_ID=<%=getTheDayAfterTomorrow.getId()%>&username=<%=user.getId()%>"><span class="h7"><img src="<%=getTheDayAfterTomorrow.getImgURLCLB()%>" width="50px" height="50px" alt="speaker">&emsp;<%=getTheDayAfterTomorrow.getTitle()%></span></a>

                                                        <%}%>
                                                    </span>

                                                </button>
                                            </div>
                                            <div id="<%=getTheDayAfterTomorrow.getId()%>" class="accordion-collapse collapse"
                                                 aria-labelledby="heading-One" data-bs-parent="#accordionExample-2">
                                                <div class="accordion-body">
                                                    <p><%=getTheDayAfterTomorrow.getSummary()%></p>
                                                    <ul class="ev-schedule-meta d-flex flex-wrap">
                                                        <li><span><i class="icofont-user"></i></span><%=getTheDayAfterTomorrow.getSpeaker()%></li>
                                                        <li><span><i class="icofont-clock-time"></i></span><%=getTheDayAfterTomorrow.getEventTypeName()%></li>
                                                        <li><span><i class="icofont-location-pin"></i></span><%=getTheDayAfterTomorrow.getLocationName()%></li>

                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <%}%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- ==========schedule Section end Here========== -->

            <!-- Speakers section start here -->

            <%list = (ArrayList<EventPost>) request.getAttribute("GET_NEWEST_EVENT");%>
            <section class="speakers-section padding-tb padding-b">
                <div class="container">
                    <div class="section-header">
                        <h2>Newest Event</h2>
                    </div>
                    <div class="section-wrapper shape-b">
                        <div class="row g-5">
                            <%for (EventPost getNewestEvent : list) {%>
                            <div class="speaker-item">
                                <div class="speaker-inner">
                                    <div class="speaker-thumb">
                                        <% if (session.getAttribute("LOGIN_USER") == null) {%>

                                        <a href="MainController?action=loginpage&page=User_Login_And_Register.jsp&request=LOGIN"><img src="<%=getNewestEvent.getImgUrl()%>" width="547px" height="273px" alt="speaker"></a>
                                            <%} else {%>


                                        <a href="MainController?action=viewEventDetail&EVENT_ID=<%=getNewestEvent.getId()%>&username=<%=user.getId()%>"><img src="<%=getNewestEvent.getImgUrl()%>" width="547px" height="273px" alt="speaker"></a>
                                            <%}%>
                                    </div>
                                    <div class="speaker-content">
                                        <div class="spkr-content-title">
                                            <% if (session.getAttribute("LOGIN_USER") == null) {%>
                                            <h5><a href="MainController?action=loginpage&page=User_Login_And_Register.jsp&request=LOGIN"><%=getNewestEvent.getTitle()%></a> </h5>
                                            <%} else {%>
                                            <h5><a href="MainController?action=viewEventDetail&EVENT_ID=<%=getNewestEvent.getId()%>&username=<%=user.getId()%>"><%=getNewestEvent.getTitle()%></a> </h5>
                                                    <%}%>
                                            <p><%=getNewestEvent.getClbName()%></p>
                                        </div>
                                        <div class="spkr-content-details">
                                            <p><%=getNewestEvent.getSummary()%></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%}%>
                        </div>                   
                    </div>
                </div>
            </section>
            <!-- Speakers section end here -->

            <!-- ======= Sponsor sectin start here ======== -->
            <section class="sponsor-section padding-tb padding-b bg-image">
                <div class="container">
                    <div class="section-header">
                        <h2>Event Sponsors</h2>
                    </div>
                    <%list = (ArrayList<EventPost>) request.getAttribute("IMG_CLB");%>
                    <div class="section-wrapper text-center shape-b">
                        <div class="all-sponsor">
                            <div class="platinum-sponsor">
                                <div class="swiper-wrapper">
                                    <%for (EventPost imgCLB : list) {%>
                                    <div class="swiper-slide">
                                        <img src="<%=imgCLB.getImgURLCLB()%>" alt="sponsor">
                                    </div>
                                    <%}%>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- ======= Sponsor sectin end here ======== -->


            <!-- ===== News Section Start here  ==== -->

            <%ArrayList<BlogDTO> listBlog = new ArrayList<BlogDTO>();
                listBlog = (ArrayList<BlogDTO>) request.getAttribute("GET_NEWEST_BLOG");%>
            <section class="news-section padding-tb padding-b">
                <div class="container">
                    <div class="section-header">
                        <h2>FBT Blog</h2>
                        <p>Start Your Remarkable Journey</p>
                    </div>
                    <div class="section-wrapper">
                        <div class="row g-4 justify-content-center">
                            <%for (BlogDTO bl : listBlog) {%>
                            <div class="col-lg-4 col-sm-6 col-xs-12">
                                <div class="news-item">
                                    <div class="news-inner">
                                        <div class="news-thumb">
                                            <img src="<%=bl.getImgUrl()%>" width="370px" height="270px" alt="Image">
                                        </div>
                                        <div class="news-content">
                                            <% if (session.getAttribute("LOGIN_USER") == null) {%>
                                            <h6><a class="cut" href="MainController?action=loginpage&page=User_Login_And_Register.jsp&request=LOGIN"><%=bl.getTitle()%></a>
                                            </h6>
                                            <p><span><i class="fa fa-calendar" aria-hidden="true"></i>
                                                </span> <%=bl.getCreateDate()%></p>
                                                <%} else {%>
                                            <h6><a class="cut" href="MainController?action=ViewBlogDetail&BLOG_ID=<%=bl.getId()%>&userID=<%=user.getId()%>"><%=bl.getTitle()%></a>
                                            </h6>
                                            <p><span><i class="fa fa-calendar" aria-hidden="true"></i>
                                                </span> <%=bl.getCreateDate()%></p>
                                                <%}%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%}%>
                        </div>
                    </div>
                </div>
            </section>
            <!-- ===== News Section end here  ==== -->

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

                                            <div class="form-group">
                                                <input type="email" class="form-control" placeholder="Enter email">
                                            </div>
                                            <a href="mailto:daihoc.hcm@fpt.edu.vn">
                                                <button type="submit" class="lab-btn"><span>Send Massage<i
                                                            class="icofont-paper-plane"></i></span></button>
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

            <!-- RS5.0 Core JS Files -->
            <script src="UserCSS/kagont/assets/css/revolution/js/jquery.themepunch.tools.min.js?rev=5.0"></script>
            <script src="UserCSS/kagont/assets/css/revolution/js/jquery.themepunch.revolution.min.js?rev=5.0"></script>
            <script src="UserCSS/kagont/assets/css/revolution/js/extensions/revolution.extension.video.min.js"></script>
            <script src="UserCSS/kagont/assets/css/revolution/js/extensions/revolution.extension.slideanims.min.js"></script>
            <script src="UserCSS/kagont/assets/css/revolution/js/extensions/revolution.extension.actions.min.js"></script>
            <script src="UserCSS/kagont/assets/css/revolution/js/extensions/revolution.extension.layeranimation.min.js"></script>
            <script src="UserCSS/kagont/assets/css/revolution/js/extensions/revolution.extension.kenburn.min.js"></script>
            <script src="UserCSS/kagont/assets/css/revolution/js/extensions/revolution.extension.navigation.min.js"></script>
            <script src="UserCSS/kagont/assets/css/revolution/js/extensions/revolution.extension.migration.min.js"></script>
            <script src="UserCSS/kagont/assets/css/revolution/js/extensions/revolution.extension.parallax.min.js"></script>

            <!-- RS Configration JS Files -->
            <script src="UserCSS/kagont/assets/js/rsconfig.js"></script>
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
                };
            </script>
        </form>
    </body>