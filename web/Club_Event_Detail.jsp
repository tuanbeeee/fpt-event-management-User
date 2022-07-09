<%@page import="sample.posts.EventPost"%>
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>F.E.M - Club Event Detail</title>
        <!-- Favicon icon -->
        <link rel="icon" type="image/png" sizes="16x16" href="./css_Admin/images/Biểu-tượng-không-chữ.png">
        <link rel="stylesheet" href="./css_Admin/vendor/chartist/css/chartist.min.css">
        <link href="./css_Admin/vendor/bootstrap-select/dist/css/bootstrap-select.min.css" rel="stylesheet">
        <link href="./css_Admin/vendor/owl-carousel/owl.carousel.css" rel="stylesheet">
        <link href="./css_Admin/css/style.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&family=Roboto:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
    </head>

    <%EventPost evt = (EventPost) request.getAttribute("event");
        int participants = (Integer) request.getAttribute("participants");
    %>

    <body>

        <!--*******************
            Preloader start
        ********************-->
        <div id="preloader">
            <div class="sk-three-bounce">
                <div class="sk-child sk-bounce1"></div>
                <div class="sk-child sk-bounce2"></div>
                <div class="sk-child sk-bounce3"></div>
            </div>
        </div>
        <!--*******************
            Preloader end
        ********************-->

        <!--**********************************
            Main wrapper start
        ***********************************-->
        <div id="main-wrapper">

            <!--**********************************
                Nav header start
            ***********************************-->
            <div class="nav-header">
                <a href="MainController?action=ListEvent" class="brand-logo">
                    <img class="brand-title" src="./css_Admin/images/femLogo.png" alt="">
                </a>

                <div class="nav-control">
                    <div class="hamburger">
                        <span class="line"></span><span class="line"></span><span class="line"></span>
                    </div>
                </div>
            </div>
            <!--**********************************
                Nav header end
            ***********************************-->
            <!--**********************************
        Header start
    ***********************************-->
            <div class="header">
                <div class="header-content">
                    <nav class="navbar navbar-expand">
                        <div class="collapse navbar-collapse justify-content-between">
                            <div class="header-left">
                                <div class="dashboard_bar">
                                    Event Detail
                                </div>
                            </div>
                            <ul class="navbar-nav header-right">
                                <li class="nav-item">
                                    <div class="input-group search-area d-xl-inline-flex d-none">
                                        <input type="text" class="form-control" placeholder="Search here...">
                                        <div class="input-group-append">
                                            <span class="input-group-text"><a href="javascript:void(0)"><i class="flaticon-381-search-2"></i></a></span>
                                        </div>
                                    </div>
                                </li>
                                <li class="nav-item dropdown notification_dropdown">
                                    <a class="nav-link  ai-icon" href="javascript:void(0)" role="button" data-toggle="dropdown">
                                        <svg width="28" height="28" viewBox="0 0 28 28" fill="none" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" clip-rule="evenodd" d="M12.8333 5.91732V3.49998C12.8333 2.85598 13.356 2.33331 14 2.33331C14.6428 2.33331 15.1667 2.85598 15.1667 3.49998V5.91732C16.9003 6.16698 18.5208 6.97198 19.7738 8.22498C21.3057 9.75681 22.1667 11.8346 22.1667 14V18.3913L23.1105 20.279C23.562 21.1831 23.5142 22.2565 22.9822 23.1163C22.4513 23.9761 21.5122 24.5 20.5018 24.5H15.1667C15.1667 25.144 14.6428 25.6666 14 25.6666C13.356 25.6666 12.8333 25.144 12.8333 24.5H7.49817C6.48667 24.5 5.54752 23.9761 5.01669 23.1163C4.48469 22.2565 4.43684 21.1831 4.88951 20.279L5.83333 18.3913V14C5.83333 11.8346 6.69319 9.75681 8.22502 8.22498C9.47919 6.97198 11.0985 6.16698 12.8333 5.91732ZM14 8.16664C12.4518 8.16664 10.969 8.78148 9.87469 9.87581C8.78035 10.969 8.16666 12.453 8.16666 14V18.6666C8.16666 18.8475 8.12351 19.026 8.04301 19.1881C8.04301 19.1881 7.52384 20.2265 6.9755 21.322C6.88567 21.5028 6.89501 21.7186 7.00117 21.8901C7.10734 22.0616 7.29517 22.1666 7.49817 22.1666H20.5018C20.7037 22.1666 20.8915 22.0616 20.9977 21.8901C21.1038 21.7186 21.1132 21.5028 21.0234 21.322C20.475 20.2265 19.9558 19.1881 19.9558 19.1881C19.8753 19.026 19.8333 18.8475 19.8333 18.6666V14C19.8333 12.453 19.2185 10.969 18.1242 9.87581C17.0298 8.78148 15.547 8.16664 14 8.16664Z" fill="#FE634E"/>
                                        </svg>
                                        <div class="pulse-css"></div>
                                    </a>
                                    <div class="dropdown-menu rounded dropdown-menu-right">
                                        <div id="DZ_W_Notification1" class="widget-media dz-scroll p-3 height380">
                                            <ul class="timeline">
                                                <li>
                                                    <div class="timeline-panel">
                                                        <div class="media mr-2">
                                                            <img alt="image" width="50" src="images/avatar/1.jpg">
                                                        </div>
                                                        <div class="media-body">
                                                            <h6 class="mb-1">Dr sultads Send you Photo</h6>
                                                            <small class="d-block">29 July 2020 - 02:26 PM</small>
                                                        </div>
                                                    </div>
                                                </li>

                                            </ul>
                                        </div>
                                        <a class="all-notification" href="javascript:void(0)">See all notifications <i class="ti-arrow-right"></i></a>
                                    </div>
                                </li>
                                <li class="nav-item dropdown header-profile">
                                    <a class="nav-link" href="javascript:void(0)" role="button" data-toggle="dropdown">
                                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSwxT6KPZ9pIt58ps7zv_kStu6qJqEp11nFvA&usqp=CAU" width="20" alt=""/>
                                        <div class="header-info">
                                            <span class="text-black"><strong>Quốc Lâm</strong></span>
                                            <p class="fs-12 mb-0">Moderator</p>
                                        </div>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right">
                                        <a href="./app-profile.html" class="dropdown-item ai-icon">
                                            <svg id="icon-user1" xmlns="http://www.w3.org/2000/svg" class="text-primary" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path><circle cx="12" cy="7" r="4"></circle></svg>
                                            <span class="ml-2">Profile </span>
                                        </a>
                                        <a href="./email-inbox.html" class="dropdown-item ai-icon">
                                            <svg id="icon-inbox" xmlns="http://www.w3.org/2000/svg" class="text-success" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"></path><polyline points="22,6 12,13 2,6"></polyline></svg>
                                            <span class="ml-2">Inbox </span>
                                        </a>
                                        <a href="./page-login.html" class="dropdown-item ai-icon">
                                            <svg id="icon-logout" xmlns="http://www.w3.org/2000/svg" class="text-danger" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path><polyline points="16 17 21 12 16 7"></polyline><line x1="21" y1="12" x2="9" y2="12"></line></svg>
                                            <span class="ml-2">Logout </span>
                                        </a>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
            <!--**********************************
                Header end ti-comment-alt
            ***********************************-->

            <!--**********************************
                Sidebar start
            ***********************************-->
            <div class="deznav">
                <div class="deznav-scroll">
                    <a href="javascript:void(0)" class="add-menu-sidebar" data-toggle="modal" data-target="#addOrderModalside" >+ New Event</a>
                    <ul class="metismenu" id="menu">
                        <li><a class="has-arrow ai-icon" href="javascript:void()" aria-expanded="false">
                                <i class="flaticon-381-networking"></i>
                                <span class="nav-text">Dashboard</span>
                            </a>
                            <ul aria-expanded="false">
                                <li><a href="index.html">Dashboard</a></li>
                                <li><a href="MainController?action=ListEvent">Event</a></li>
                                <li><a href="blog.html">Blog</a></li>		
                            </ul>
                        </li>
                        <li>
                            <!--                            <a class="has-arrow ai-icon" href="javascript:void()" aria-expanded="false">
                                                            <i class="flaticon-381-television"></i>
                                                            <span class="nav-text">Apps</span>
                                                        </a>
                                                        <ul aria-expanded="false">
                            
                                                            <li><a class="has-arrow" href="javascript:void()" aria-expanded="false">Email</a>
                                                                <ul aria-expanded="false">
                                                                    <li><a href="./email-compose.html">Compose</a></li>
                                                                    <li><a href="./email-inbox.html">Inbox</a></li>
                                                                    <li><a href="./email-read.html">Read</a></li>
                                                                </ul>
                                                            </li>
                                                            <li><a href="./app-calender.html">Calendar</a></li>
                                                        </ul>-->
                        </li>
                    </ul>
                    <div class="copyright">
                        <p><strong>FPT Event Management</strong> © 2022 F.E.M</p>
                    </div>
                </div>
            </div>
            <!--**********************************
                Sidebar end
            ***********************************-->

            <!--**********************************
        Content body start
    ***********************************-->
            <div class="content-body">
                <!-- row -->
                <div class="container-fluid">
                    <!-- Add Order -->
                    <div class="modal fade" id="addOrderModalside">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Add Event</h5>
                                    <button type="button" class="close" data-dismiss="modal"><span>&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form>
                                        <div class="form-group">
                                            <label class="text-black font-w500">Event Title</label>
                                            <input type="text" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label class="text-black font-w500">Event Date</label>
                                            <input type="date" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label class="text-black font-w500">Description</label>
                                            <input type="text" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <button type="button" class="btn btn-primary">Create</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xl-9 col-xxl-8">
                            <div class="row">
                                <div class="col-xl-12">
                                    <div class="card event-detail-bx overflow-hidden">
                                        <div class="card-media">
                                            <img src="images/ESL_International_Pan-1-1200x490.jpg" alt="" class="w-100">
                                        </div>
                                        <div class="card-body">
                                            <div class="d-flex flex-wrap align-items-center mb-4">
                                                <h2 class="text-black col-xl-6 p-0 col-xxl-12 mr-auto title mb-3"><%=evt.getTitle()%></h2>
                                                <div class="d-flex align-items-center">

                                                    <div class="media bg-light p-3 rounded align-items-center mr-3">
                                                        <div class="media-body mr-2">
                                                            <span class="fs-12 d-block mb-1">Approval status</span>                                                           
                                                            <span style="color: green" class="fs-16"><%=evt.getStatusTypeName()%></span>                                                           
                                                        </div>                                                       
                                                    </div>
                                                    <div class="media bg-light p-3 rounded align-items-center mr-3">
                                                        <div class="media-body mr-2">
                                                            <span class="fs-12 d-block mb-1">Speaker</span>
                                                            <span class="fs-16 text-black"><%=evt.getSpeaker()%></span>
                                                        </div>                                                       
                                                    </div>

                                                    <div class="dropdown">
                                                        <div class="share-icon" role="button" data-toggle="dropdown" aria-expanded="false">
                                                            <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                            <path d="M12 13C12.5523 13 13 12.5523 13 12C13 11.4477 12.5523 11 12 11C11.4477 11 11 11.4477 11 12C11 12.5523 11.4477 13 12 13Z" stroke="#FE634E" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                                                            <path d="M12 6C12.5523 6 13 5.55228 13 5C13 4.44772 12.5523 4 12 4C11.4477 4 11 4.44772 11 5C11 5.55228 11.4477 6 12 6Z" stroke="#FE634E" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                                                            <path d="M12 20C12.5523 20 13 19.5523 13 19C13 18.4477 12.5523 18 12 18C11.4477 18 11 18.4477 11 19C11 19.5523 11.4477 20 12 20Z" stroke="#FE634E" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                                                            </svg>
                                                        </div>
                                                        <div class="dropdown-menu dropdown-menu-right">

                                                            <a class="dropdown-item" href="javascript:void(0);">Edit</a>
                                                            <a class="dropdown-item" href="javascript:void(0);">Delete</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-lg-3 col-md-6 col-xxl-6 mb-3">
                                                    <div class="media bg-light p-3 rounded align-items-center">	
                                                        <div class="media-body">
                                                            <span class="fs-12 d-block mb-1">Viewer</span>
                                                            <span class="fs-16 text-black"><%=evt.getNumberOfView()%> View</span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-4 col-md-6 col-xxl-6 mb-3">
                                                    <div class="media bg-light p-3 rounded align-items-center">	
                                                        <div class="media-body">
                                                            <span class="fs-12 d-block mb-1">Participants</span>
                                                            <span class="fs-16 text-black"><%=participants%> People</span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-5 col-md-12 col-xxl-12 mb-3">
                                                    <div class="media bg-light p-3 rounded align-items-center">	
                                                        <svg class="mr-4" width="32" height="32" viewBox="0 0 32 32" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                        <g clip-path="">
                                                        <path d="M27.5713 13.4286C27.5713 22.4286 15.9999 30.1428 15.9999 30.1428C15.9999 30.1428 4.42847 22.4286 4.42847 13.4286C4.42847 10.3596 5.6476 7.41638 7.81766 5.24632C9.98772 3.07625 12.931 1.85712 15.9999 1.85712C19.0688 1.85712 22.0121 3.07625 24.1821 5.24632C26.3522 7.41638 27.5713 10.3596 27.5713 13.4286Z" stroke="#FE634E" stroke-width="3" stroke-linecap="round" stroke-linejoin="round"/>
                                                        <path d="M15.9997 17.2857C18.13 17.2857 19.8569 15.5588 19.8569 13.4286C19.8569 11.2983 18.13 9.57141 15.9997 9.57141C13.8695 9.57141 12.1426 11.2983 12.1426 13.4286C12.1426 15.5588 13.8695 17.2857 15.9997 17.2857Z" stroke="#FE634E" stroke-width="3" stroke-linecap="round" stroke-linejoin="round"/>
                                                        </g>
                                                        <defs>
                                                        <clipPath id="clip3">
                                                            <rect width="30.8571" height="30.8571" fill="white" transform="translate(0.571289 0.571411)"/>
                                                        </clipPath>
                                                        </defs>
                                                        </svg>
                                                        <div class="media-body">
                                                            <span class="fs-12 d-block mb-1">Location</span>
                                                            <span class="fs-16 text-black"><%=evt.getLocationName()%></span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <h4 class="fs-20 text-black font-w600">Event Summary</h4>
                                            <p class="fs-14 mb-0"><%=evt.getSummary()%>
                                            </p>

                                            <br>

                                            <h4 class="fs-20 text-black font-w600">Event Description </h4>
                                            <p class="fs-14 mb-0"><%=evt.getContent()%>
                                            </p>
                                        </div>
                                    </div>
                                </div>                               
                            </div>
                        </div>
                        <div class="col-xl-3 col-xxl-4">
                            <div class="row">
                                <div class="col-xl-12 col-sm-6">
                                        <img class="card img-fluid" src="<%=evt.getImgUrl()%>">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--**********************************
                Content body end
            ***********************************-->

            <!--**********************************
                Footer start
            ***********************************-->
            <div class="footer">
                <div class="copyright">
                    <p>© Best For Event Management Schedule
                        &amp; Developed by <a href="http://dexignzone.com/" target="_blank">F.E.M</a> 2022</p>
                </div>
            </div>
            <!--**********************************
                Footer end
            ***********************************-->

            <!--**********************************
       Support ticket button start
    ***********************************-->

            <!--**********************************
               Support ticket button end
            ***********************************-->


        </div>
        <!--**********************************
            Main wrapper end
        ***********************************-->

        <!--**********************************
            Scripts
        ***********************************-->
        <!-- Required vendors -->
        <script src="./css_Admin/vendor/global/global.min.js"></script>
        <script src="./css_Admin/vendor/bootstrap-select/dist/js/bootstrap-select.min.js"></script>
        <script src="./css_Admin/vendor/chart.js/Chart.bundle.min.js"></script>
        <script src="./css_Admin/js/custom.min.js"></script>
        <script src="./css_Admin/js/deznav-init.js"></script>
        <script src="./css_Admin/vendor/owl-carousel/owl.carousel.js"></script>

        <!-- Chart piety plugin files -->
        <script src="./css_Admin/vendor/peity/jquery.peity.min.js"></script>

        <!-- Apex Chart -->
        <script src="./css_Admin/vendor/apexchart/apexchart.js"></script>

        <!-- Dashboard 1 -->
        <script src="./css_Admin/js/dashboard/event-detail.js"></script>

    </body>
</html>