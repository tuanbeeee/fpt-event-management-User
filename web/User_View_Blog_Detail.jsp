<%@page import="sample.notification.NotificationDTO"%>
<%@page import="sample.notification.NotificationDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sample.blog.BlogDTO"%>
<%@page import="sample.blog.BlogDTO"%>
<%@page import="sample.users.UserDTO"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>F.E.M</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Fav icon -->
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
                                    <%ArrayList<NotificationDTO> list = new ArrayList<>();
                                        list = (ArrayList<NotificationDTO>) request.getAttribute("GET_NOTIFICATION"); %>
                                    <div class="dropdown">
                                        <a  onclick="hamDropdown()"  class="nut_dropdown fa fa-bell"></a>
                                        <div class="noidung_dropdown">                                               
                                            <div class="cont">
                                                <%for (NotificationDTO n : list) {%>
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
        <%BlogDTO blogDetail = new BlogDTO();
            blogDetail = (BlogDTO) request.getAttribute("USER_VIEW_BLOG_DETAIL");%>
        <section class="page-header padding-tb">
            <div class="overlay"></div>
            <div class="container">
                <div class="page-header-content-area">
                    <h4 class="ph-title">Blog</h4>
                    <ul class="lab-ul">
                        <li><a href="MainController?action=homepage">Home</a></li>
                        <li><a class="active">Blog Single</a></li>
                    </ul>
                </div>
            </div>
        </section>
        <!-- Page Header Section Ending Here -->

        <!-- Blog Section Start Here -->
        <div class="blog-section blog-page padding-tb aside-bg" style="background: #f9eefb">
            <div class="container">
                <div class="section-wrapper">
                    <div class="row justify-content-center pb-15">
                        <div class="col-lg-8 col-12">
                            <div class="post-item-2">
                                <div class="post-inner">
                                    <div class="post-thumb rounded">
                                        <img src="<%=blogDetail.getImgUrl()%>" alt="blog">
                                    </div>
                                    <div class="post-content">
                                        <h3 style="color: #5a1592;"><%=blogDetail.getTitle()%></h3>
                                        <ul class="lab-ul post-date">
                                            <li>
                                                <span>
                                                    <i class="icofont-ui-calendar">

                                                    </i> <%=blogDetail.getCreateDate()%>
                                                </span>
                                            </li>
                                            <li><span><i class="icofont-user"></i><a href="#"><%=blogDetail.getOrgName()%></a></span>
                                            </li>
                                            <li><span><p><%=blogDetail.getNumberOfView()%> views</p></span></li>
                                        </ul>
                                        <p class="context"><%=blogDetail.getContent()%>
                                        </p>
                                        <div class="tags-area">
                                            <ul class="tags lab-ul justify-content-center">
                                                <li>
                                                    <a href="#" class="active">Event</a>
                                                </li>
                                                <li>
                                                    <a href="#" class="active">NoneProfit</a>
                                                </li>
                                                <li>
                                                    <a href="#" class="active">Admission</a>
                                                </li>
                                                <li>
                                                    <a href="#" class="active">Exams</a>
                                                </li>
                                            </ul>

                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div id="respond" class="comment-respond rounded">
                                <h6 class="h7">Leave a Comment</h6>
                                <div class="add-comment">
                                    <form action="#" method="post" id="commentform" class="comment-form">
                                        <input name="name" type="text" value="" placeholder="Name*">
                                        <input name="email" type="text" value="" placeholder="Email*">
                                        <input name="url" type="text" value="" placeholder="Website*" class="w-100">
                                        <textarea id="comment-reply" name="comment" rows="7"
                                                  placeholder="Type Here Your Comment*"></textarea>
                                        <button type="submit" class="lab-btn"><span>Send Comment</span></button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Blog Section ENding Here -->

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
                                                <button type="submit" class="lab-btn"><span>Send Massage</span><i
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