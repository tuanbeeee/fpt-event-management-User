<%@page import="sample.notification.NotificationDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sample.posts.EventPost"%>
<%@page import="sample.organization.OrganizationFollowerDTO"%>
<%@page import="sample.organization.OrganizationDTO"%>
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
        <script src='//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.0-beta.1/angular.js'></script>
        <script src='https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.0-beta.1/angular-animate.js'></script>
        <script src="UserCSS/kagont/assets/css/script.js"></script>
        <!-- Icon Font CSS -->
        <link rel="stylesheet" href="UserCSS/kagont/assets/css/plugins/fontawesome.min.css">
        <link rel="stylesheet" href="UserCSS/kagont/assets/css/plugins/ionicons.min.css">
        <link rel="stylesheet" href="UserCSS/kagont/assets/css/plugins/line-awesome.min.css">
        <link rel="stylesheet" href="UserCSS/kagont/assets/css/plugins/icofont.min.css">
        <link rel="stylesheet" href="UserCSS/kagont/assets/css/plugins/simple-line-icons.css">

        <!-- Plugins CSS -->
        <link rel="stylesheet" href="UserCSS/kagont/assets/css/plugins/animate.css">
        <link rel="stylesheet" href="UserCSS/kagont/assets/css/plugins/swiper-bundle.min.css">
        <link rel="stylesheet" href="UserCSS/kagont/assets/css/plugins/odometer.min.css">

        <!-- Main Style CSS -->
        <link rel="stylesheet" href="UserCSS/kagont/assets/css/style.css">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="UserCSS/kagont/assets/js/js/vendor/jquery-3.5.1.min.js"></script>
        <script
        src="https://cdnjs.cloudflare.com/ajax/libs/MaterialDesign-Webfont/3.6.95/css/materialdesignicons.css"></script>

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
                                                    <a href="MainController?action=viewEventDetail&EVENT_ID=<%=n.eventID%>&username=<%=user.getId()%>">
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
        <section class="page-header bg_img padding-tb">
            <div class="overlay"></div>
            <div class="container">
                <div class="page-header-content-area">
                    <h4 class="ph-title">Club Single</h4>
                    <ul class="lab-ul">
                        <li><a href="MainController?action=homepage">Home</a></li>
                        <li><a href="#">Feature</a></li>
                        <li><a class="active">Club Single</a></li>
                    </ul>
                </div>
            </div>
        </section>
        <!-- Page Header Section Ending Here -->

        <!-- Shop Page Section start here -->
        <!-- #2 -->
        <!-- Blog Start -->

        <%OrganizationDTO dto = (OrganizationDTO) request.getAttribute("CLB_INFO");
            OrganizationFollowerDTO orgFollow = (OrganizationFollowerDTO) request.getAttribute("CHECK_USER_FOLLOWING");%>
        <div class="section">
            <div class="container">


                <div class="page-content page-container" id="page-content">
                    <div class="padding1">
                        <div class="row container d-flex justify-content-center">
                            <div class="col-xl-6 col-md-12 fix">
                                <div class="card user-card-full">
                                    <div class="row m-l-0 m-r-0">
                                        <div class="col-sm-4 bg-c-lite-green user-profile">
                                            <div class="card-block text-center text-white">
                                                <div class="m-b-25">
                                                    <img src="<%=dto.getImgUrl()%>"
                                                         class="img-radius" alt="User-Profile-Image">
                                                </div>

                                                <i class=" mdi mdi-square-edit-outline feather icon-edit m-t-10 f-16"></i>
                                                <form action="MainController" method="POST">
                                                    <%if (orgFollow == null) {%>
                                                    <button class="btn btn-primary btn-sm follow" type="submit" name="action" value="OrgFollower">Follow
                                                        <input type="hidden" name="USER_ID" value="<%=user.getId()%>">
                                                        <input type="hidden" name="ORG_ID" value="<%=dto.getOrgID()%>">
                                                        <input type="hidden" name="CLUB_ID" value="<%=dto.getOrgID()%>">
                                                        <input type="hidden" name="FOLLOW" value="UserFollow">
                                                    </button>
                                                    <%} else {%>
                                                    <button class="btn btn-primary btn-sm follow" type="submit" name="action" value="OrgFollower">Unfollow
                                                        <input type="hidden" name="USER_ID" value="<%=user.getId()%>">
                                                        <input type="hidden" name="ORG_ID" value="<%=dto.getOrgID()%>">
                                                        <input type="hidden" name="CLUB_ID" value="<%=dto.getOrgID()%>">
                                                        <input type="hidden" name="UNFOLLOW" value="UserUnfollow">
                                                    </button>    
                                                    <%}%>
                                                </form>
                                            </div>
                                        </div>
                                        <div class="col-sm-8">
                                            <div class="card-block">
                                                <h6 class="m-b-10 f-w-600 mau">Name Club's</h6>

                                                <h6 class="text-muted f-w-400 font"><%=dto.getOrgName()%></h6>


                                                <h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600 mau"></h6>
                                                <div class="row">
                                                    <div class="">
                                                        <p class="m-b-10 f-w-600 mau">Infomation</p>
                                                        <h6 class="text-muted f-w-400 font"><%=dto.getDescription()%></h6>
                                                    </div>

                                                </div>
                                                <h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600 mau"></h6>
                                                <div class="row">
                                                    <div class="col-sm-6">
                                                        <p class="m-b-10 f-w-600 mau">Create Date</p>
                                                        <h6 class="text-muted f-w-400 font"><%=dto.getCreateDate()%></h6>
                                                    </div>
                                                    <div class="col-sm-6">
                                                        <p class="m-b-10 f-w-600 mau">Club Code</p>
                                                        <h6 class="text-muted f-w-400 font"><%=dto.getOrgID()%></h6>
                                                    </div>
                                                </div>
                                                <h6 class="m-b-20 m-t-40 p-b-5 b-b-default f-w-600 mau"></h6>
                                                <div class="row">
                                                    <p class="m-b-10 f-w-600 mau">Social Media</p>
                                                    <h6 class="text-muted f-w-400 font"><%=dto.getEmail()%></h6>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <%ArrayList<EventPost> list = (ArrayList<EventPost>) request.getAttribute("EVENT_POST");%>
                <div class="container">
                    <div class="section-header">
                        <h2>Event Of Club </h2>
                        <p>Participate in events of FPT university clubs.</p>
                    </div>
                    <!-- Blog Section Start -->
                    <div class="blog-section mt-n4">
                        <div class="row gx-10 inside">
                            <%for (EventPost e : list) {%>
                            <div class="col-lg-6 news experience">
                                <!-- Single Blog Start -->
                                <div class="single-blog wow zoomIn" data-wow-delay="0.3s" data-wow-duration="0.5s">
                                    <div class="blog-images">
                                        <a href="MainController?action=viewEventDetail&EVENT_ID=<%=e.getId()%>&username=<%=user.getId()%>"><img src="<%=e.getImgUrl()%>"
                                                                                                                                                alt="Blog" width="547px" height="273px" ></a>
                                    </div>
                                    <div class="blog-content">
                                        <ul class="meta">
                                            <li><a href="#"><%=e.getEventTypeName()%></a></li>
                                            <li><a href="#"><%=e.getTakePlaceDate()%></a></li>
                                            <li><a href="#"><%=e.getSpeaker()%></a></li>
                                            <li><a href="#"><%=e.getLocationName()%></a></li>
                                        </ul>
                                        <h3 class="title"><a href="MainController?action=viewEventDetail&EVENT_ID=<%=e.getId()%>&username=<%=user.getId()%>"><%=e.getTitle()%></a>
                                        </h3>
                                        <p class="spkr-content-details font1"><%=e.getSummary()%></p>
                                    </div>
                                </div>

                                <!-- Single Blog End -->
                            </div>
                            <%}%>
                        </div>
                    </div>
                    <!-- Blog Section End -->



                </div>
                <div class="paginations">
                    <ul class="lab-ul d-flex flex-wrap justify-content-center mb-1">
                        <li>
                            <a href="#"><i class="icofont-rounded-double-left"></i></a>
                        </li>
                        <li>
                            <a href="#">1</a>
                        </li>
                        <li>
                            <a href="#">2</a>
                        </li>
                        <li class="d-none d-sm-block">
                            <a href="#">3</a>
                        </li>
                        <li>
                            <a href="#"><i class="icofont-rounded-double-right"></i></a>
                        </li>
                    </ul>
                </div>
            </div>
            <!-- Blog End -->
            <!-- #2 end here -->

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
                                                                tượng với Câu lạc bộ Truyền thông "gạo cội" của trường Đại
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

            <!-- JS
        ============================================ -->

            <!-- Modernizer & jQuery JS -->
            <script src="UserCSS/kagont/assets/js/js/vendor/modernizr-3.11.2.min.js"></script>
            <script src="UserCSS/kagont/assets/js/js/vendor/jquery-3.5.1.min.js"></script>

            <!-- Bootstrap JS -->
            <script src="UserCSS/kagont/assets/js/js/plugins/popper.min.js"></script>
            <script src="UserCSS/kagont/assets/js/js/plugins/bootstrap.min.js"></script>

            <!-- Plugins JS -->
            <script src="UserCSS/kagont/assets/js/js/plugins/swiper-bundle.min.js"></script>
            <script src="UserCSS/kagont/assets/js/js/plugins/appear.js"></script>
            <script src="UserCSS/kagont/assets/js/js/plugins/validate.js"></script>
            <script src="UserCSS/kagont/assets/js/js/plugins/odometer.min.js"></script>
            <script src="UserCSS/kagont/assets/js/js/plugins/isotope.pkgd.min.js"></script>
            <script src="UserCSS/kagont/assets/js/js/plugins/imagesloaded.pkgd.min.js"></script>
            <script src="UserCSS/kagont/assets/js/js/plugins/wow.js"></script>
            <script src="UserCSS/kagont/assets/js/js/plugins/ajax-contact.js"></script>

            <!-- Main JS -->
            <script src="UserCSS/kagont/assets/js/js/main.js"></script>

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