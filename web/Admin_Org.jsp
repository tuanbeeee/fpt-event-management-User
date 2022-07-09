<%@page import="sample.users.UserDTO"%>
<%@page import="java.util.List"%>
<%@page import="sample.organization.OrganizationDTO"%>
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>

<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>F.E.M - FPT Event Admin</title>
        <!-- Favicon icon -->
        <link rel="icon" type="image/png" sizes="16x16" href="./css_Admin/images/Biểu-tượng-không-chữ.png">
        <link rel="stylesheet" href="./css_Admin/vendor/chartist/css/chartist.min.css">
        <link href="./css_Admin/vendor/bootstrap-select/dist/css/bootstrap-select.min.css" rel="stylesheet">
        <link href="./css_Admin/vendor/datatables/css/jquery.dataTables.min.css" rel="stylesheet">
        <link href="./css_Admin/css/style.css" rel="stylesheet">
        <link
            href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&family=Roboto:wght@100;300;400;500;700;900&display=swap"
            rel="stylesheet">

        <style>
            .status {
                vertical-align: middle;
                font-size: 35px;
            }
        </style>
    </head>

    <body>
        <%
            UserDTO user = (UserDTO) session.getAttribute("userdata");
            if (user == null || !user.getRoleID().equals("ADM")) {
                response.sendRedirect("Admin_Login.jsp");
                return;
            }
        %>
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
                <a href="index.html" class="brand-logo">
                    <!-- <img class="logo-abbr" src="./images/logo.png" alt="">
                    <img class="logo-compact" src="./images/logo-text.png" alt=""> -->
                    <img class="brand-title" src="./images/TEXT+biểu-tượng.png" alt="">
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
                                    Club Information
                                </div>
                            </div>
                            <ul class="navbar-nav header-right">
                                <li class="nav-item">
                                    <form action="MainController">
                                        <div class="input-group search-area d-xl-inline-flex d-none">
                                            <input type="text" class="form-control" name="search" placeholder="Search here...">
                                            <div class="input-group-append">
                                                <button class="input-group-text" name="action" value="SearchOrg"><i class="flaticon-381-search-2"></i></button>
                                            </div>
                                        </div>
                                    </form>
                                </li>
                                <li class="nav-item dropdown notification_dropdown">
                                    <a class="nav-link  ai-icon" href="javascript:void(0)" role="button"
                                       data-toggle="dropdown">
                                        <svg width="28" height="28" viewBox="0 0 28 28" fill="none"
                                             xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" clip-rule="evenodd"
                                              d="M12.8333 5.91732V3.49998C12.8333 2.85598 13.356 2.33331 14 2.33331C14.6428 2.33331 15.1667 2.85598 15.1667 3.49998V5.91732C16.9003 6.16698 18.5208 6.97198 19.7738 8.22498C21.3057 9.75681 22.1667 11.8346 22.1667 14V18.3913L23.1105 20.279C23.562 21.1831 23.5142 22.2565 22.9822 23.1163C22.4513 23.9761 21.5122 24.5 20.5018 24.5H15.1667C15.1667 25.144 14.6428 25.6666 14 25.6666C13.356 25.6666 12.8333 25.144 12.8333 24.5H7.49817C6.48667 24.5 5.54752 23.9761 5.01669 23.1163C4.48469 22.2565 4.43684 21.1831 4.88951 20.279L5.83333 18.3913V14C5.83333 11.8346 6.69319 9.75681 8.22502 8.22498C9.47919 6.97198 11.0985 6.16698 12.8333 5.91732ZM14 8.16664C12.4518 8.16664 10.969 8.78148 9.87469 9.87581C8.78035 10.969 8.16666 12.453 8.16666 14V18.6666C8.16666 18.8475 8.12351 19.026 8.04301 19.1881C8.04301 19.1881 7.52384 20.2265 6.9755 21.322C6.88567 21.5028 6.89501 21.7186 7.00117 21.8901C7.10734 22.0616 7.29517 22.1666 7.49817 22.1666H20.5018C20.7037 22.1666 20.8915 22.0616 20.9977 21.8901C21.1038 21.7186 21.1132 21.5028 21.0234 21.322C20.475 20.2265 19.9558 19.1881 19.9558 19.1881C19.8753 19.026 19.8333 18.8475 19.8333 18.6666V14C19.8333 12.453 19.2185 10.969 18.1242 9.87581C17.0298 8.78148 15.547 8.16664 14 8.16664Z"
                                              fill="#FE634E" />
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
                                                <li>
                                                    <div class="timeline-panel">
                                                        <div class="media mr-2 media-info">
                                                            KG
                                                        </div>
                                                        <div class="media-body">
                                                            <h6 class="mb-1">Resport created successfully</h6>
                                                            <small class="d-block">29 July 2020 - 02:26 PM</small>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="timeline-panel">
                                                        <div class="media mr-2 media-success">
                                                            <i class="fa fa-home"></i>
                                                        </div>
                                                        <div class="media-body">
                                                            <h6 class="mb-1">Reminder : Treatment Time!</h6>
                                                            <small class="d-block">29 July 2020 - 02:26 PM</small>
                                                        </div>
                                                    </div>
                                                </li>
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
                                                <li>
                                                    <div class="timeline-panel">
                                                        <div class="media mr-2 media-danger">
                                                            KG
                                                        </div>
                                                        <div class="media-body">
                                                            <h6 class="mb-1">Resport created successfully</h6>
                                                            <small class="d-block">29 July 2020 - 02:26 PM</small>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="timeline-panel">
                                                        <div class="media mr-2 media-primary">
                                                            <i class="fa fa-home"></i>
                                                        </div>
                                                        <div class="media-body">
                                                            <h6 class="mb-1">Reminder : Treatment Time!</h6>
                                                            <small class="d-block">29 July 2020 - 02:26 PM</small>
                                                        </div>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                        <a class="all-notification" href="javascript:void(0)">See all notifications <i
                                                class="ti-arrow-right"></i></a>
                                    </div>
                                </li>

                                <li class="nav-item dropdown header-profile">
                                    <a class="nav-link" href="javascript:void(0)" role="button" data-toggle="dropdown">
                                        <img src="<%= user.getPicture() %>" width="20" alt="" />
                                        <div class="header-info">
                                            <span class="text-black"><strong><%= user.getName() %></strong></span>
                                            <p class="fs-12 mb-0">Admin</p>
                                        </div>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right">
                                        <a href="./app-profile.html" class="dropdown-item ai-icon">
                                            <svg id="icon-user1" xmlns="http://www.w3.org/2000/svg" class="text-primary"
                                                 width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor"
                                                 stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                            <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                                            <circle cx="12" cy="7" r="4"></circle>
                                            </svg>
                                            <span class="ml-2">Profile </span>
                                        </a>

                                        <a href="./page-login.html" class="dropdown-item ai-icon">
                                            <svg id="icon-logout" xmlns="http://www.w3.org/2000/svg" class="text-danger"
                                                 width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor"
                                                 stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                            <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path>
                                            <polyline points="16 17 21 12 16 7"></polyline>
                                            <line x1="21" y1="12" x2="9" y2="12"></line>
                                            </svg>
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
                    <ul class="metismenu" id="menu">
                        <li><a class="has-arrow ai-icon" href="javascript:void()" aria-expanded="false">
                                <i class="flaticon-381-networking"></i>
                                <span class="nav-text">Dashboard</span>
                            </a>
                            <ul aria-expanded="false">
                                <li><a href="MainController?action=AdminManagerPage">Club's Members</a></li>
                                <li><a href="MainController?action=AdminOrgPage">Club Information</a></li>
                                <li><a href="MainController?action=AdminUserPage">Account Information</a></li>
                            </ul>
                        </li>
                        <li><a class="has-arrow ai-icon" href="javascript:void()" aria-expanded="false">
                                <i class="flaticon-381-television"></i>
                                <span class="nav-text">Apps</span>
                            </a>
                            <ul aria-expanded="false">
                                <li><a href="./app-profile.html">Profile</a></li>
                                <li><a href="./post-details.html">Post Details</a></li>
                                <li><a class="has-arrow" href="javascript:void()" aria-expanded="false">Email</a>
                                    <ul aria-expanded="false">
                                        <li><a href="./email-compose.html">Compose</a></li>
                                        <li><a href="./email-inbox.html">Inbox</a></li>
                                        <li><a href="./email-read.html">Read</a></li>
                                    </ul>
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

                                    <div class="d-flex flex-wrap mb-2 align-items-center justify-content-between">
                                        <div class="mb-3">
                                            <h6 class="fs-16 text-black font-w600 mb-0">3 Total Club's</h6>
                                            <span class="fs-14">Based your preferences</span>
                                        </div>
                                        <div class="event-tabs mb-3">
                                            <ul class="nav nav-tabs" role="tablist">
                                                <li class="nav-item">
                                                    <a class="nav-link active" data-toggle="tab" href="#All" role="tab"
                                                       aria-selected="true">
                                                        All
                                                    </a>
                                                </li>


                                            </ul>
                                        </div>
                                        <div class="mb-3 d-flex">

                                            <a href="Admin_OrgForm.jsp" class="btn btn-primary text-nowrap"><i
                                                    class="fa fa-file-text scale5 mr-3" aria-hidden="true"></i>Create new Club</a>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-xl-12">
                                            <div class="tab-content">
                                                <div id="All" class="tab-pane active">
                                                    <div class="table-responsive fs-14">
                                                        <table class="table mb-4 dataTablesCard no-hover card-table fs-14" id="example5">
                                                            <thead>
                                                                <tr>

                                                                    <th style="width:450px;">Img Club</th>
                                                                    <th style="width: 100px">ID Club</th>
                                                                    <th>Information Cub</th>
                                                                    <th>Status</th>
                                                                    <th>Action</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>

                                                                <%
                                                                    List<OrganizationDTO> listOrg = (List) request.getAttribute("LIST_ORG");
                                                                    if (listOrg != null) {
                                                                        for (OrganizationDTO list : listOrg) {
                                                                %> 


                                                                <tr>

                                                                    <td>
                                                                        <div class="media align-items-center">
                                                                            <img class="img-fluid rounded mr-3 d-none d-xl-inline-block"
                                                                                 width="70" src="<%= list.getImgUrl()%>"
                                                                                 alt="DexignZone">
                                                                            <div class="media-body">
                                                                                <h4 class="font-w600 mb-1 wspace-no"><a
                                                                                        href="javascript:void(0)" class="text-black"> <%= list.getOrgName()%></a></h4>
                                                                                <span>Create Date: <%= list.getCreateDate()%></span>
                                                                            </div>
                                                                        </div>
                                                                    </td>
                                                                    <td class="d-none d-lg-table-cell"><%= list.getOrgID()%></td>
                                                                    <td>                   
                                                                        <p class="mb-0 d-none d-xl-inline-block"><%= list.getDescription()%></p>
                                                                    </td>
                                                                    <td>

                                                                        <%
                                                                            if (list.isStatus() == true) {
                                                                        %>
                                                                        <span class="status text-success">&bull;</span>
                                                                        <%
                                                                        } else {
                                                                        %>
                                                                        <span class="status text-danger">&bull;</span>
                                                                        <% }%>
                                                                        <span class=" text-primary"><%= list.isStatus()%></span>
                                                                    </td>
                                                                    <td>
                                                                        <div class="d-flex">
                                                                            <a href="MainController?action=UpdateOrg&id=<%= list.getOrgID()%>"
                                                                               class="btn btn-info btn-sm light px-4">Update</a>
                                                                            <a href="MainController?action=DeleteOrg&id=<%= list.getOrgID()%>"
                                                                               class="btn btn-danger  btn-sm light ml-2 px-4">Delete</a>
                                                                        </div>
                                                                    </td>
                                                                </tr>

                                                                <%
                                                                        }
                                                                    }
                                                                %>
                                                            </tbody>
                                                        </table>



                                                    </div>
                                                </div>
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
                            <!-- Datatable -->
                            <script src="./css_Admin/vendor/datatables/js/jquery.dataTables.min.js"></script>
                            <script>
                                (function ($) {
                                    var table = $('#example5').DataTable({
                                        searching: false,
                                        paging: true,
                                        select: false,
                                        //info: false,         
                                        lengthChange: false

                                    });
                                    var table = $('#example3').DataTable({
                                        searching: false,
                                        paging: true,
                                        select: false,
                                        //info: false,         
                                        lengthChange: false

                                    });
                                    var table = $('#example2').DataTable({
                                        searching: false,
                                        paging: true,
                                        select: false,
                                        //info: false,         
                                        lengthChange: false

                                    });
                                    var table = $('#example4').DataTable({
                                        searching: false,
                                        paging: true,
                                        select: false,
                                        //info: false,         
                                        lengthChange: false

                                    });
                                    $('#example tbody').on('click', 'tr', function () {
                                        var data = table.row(this).data();

                                    });
                                })(jQuery);
                            </script>
                            </body>

                            </html>