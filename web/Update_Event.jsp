<%@page import="sample.posts.EventPostError"%>
<%@page import="sample.posts.EventLocation"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.List"%>
<%@page import="sample.posts.EventType"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en" >
    <head>
        <meta charset="UTF-8">
        <title>Update An Event - FPT Event Management</title>
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
        <link rel="stylesheet" href="./css_FormCreate/css/style.css">
        <link rel="stylesheet" href="./css_FormCreate/css/all.css">
        <script src="https://your-site-or-cdn.com/fontawesome/v5.15.4/js/all.js" data-auto-replace-svg></script>
        <script src="https://use.fontawesome.com/releases/vVERSION/js/all.js" data-auto-replace-svg="nest"></script>

    </head>

    <%  List<EventType> listEvtType = (List<EventType>) request.getAttribute("listEventTypes");
        List<EventLocation> listEvtLocation = (List<EventLocation>) request.getAttribute("listEventLocations");
        
        EventPostError evtError = (EventPostError)request.getAttribute("ERROR");
        if(evtError == null)
            evtError = new EventPostError();
    %>



    <body>
        <!-- partial:index.partial.html -->
        <div class="container">
            <form action="MainController" method="POST">
                <h1 class="Information">Add Event</h1>
                <div class="row">
                    <h4><i class="fa-solid fa-users" style="width: 25px"></i>Event's Information</h4>
                    <div class="input-group input-group-icon">
                        <input required="" type="text" value="" name="title" placeholder="Title"/>
                        <div class="input-icon"><i class="fa-solid fa-id-card"></i></div>
                    </div>

                    <div class="input-group input-group-icon">
                        <input type="text" value="" name="speaker" placeholder="Speaker"/>
                        <div class="input-icon"><i class="fa fa-user"></i></div>
                    </div>

                    <div class="input-group input-group-icon">
                        <input required="" type="url" value="" name="imgUrl" placeholder="ImgURL"/>
                        <div class="input-icon"><i class="fa-solid fa-link"></i></div>
                    </div>


                    <h4><i class="fa-solid fa-file-pen" style="width: 25px;"></i>Event's Type and Location</h4>

                    <select name="location">
                        <%for (int i = 0; i < listEvtLocation.size(); i++) {
                        %>
                        <option value="<%=listEvtLocation.get(i).getLocationID()%>"><%=listEvtLocation.get(i).getLocaitonName()%></option>
                        <%
                            }
                        %>
                    </select>
                    <select name="eventType">
                        <%for (int i = 0; i < listEvtType.size(); i++) {
                        %>
                        <option value="<%=listEvtType.get(i).getEventTypeID()%>"><%=listEvtType.get(i).getEventTypeName()%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <h4><i class="fa-solid fa-calendar-days" style="width: 25px;"></i>Take Place Date</h4>
                
                <%= evtError.getTakePlaceDate() %>
                <div class="input-group input-group-icon" style="font-family: 'Open Sans','Helvetica Neue',Helvetica, Arial, sans-serif;">
                    <input required="" type="date" value="" name="takePlaceDate" class="font-color"/>
                    <div class="input-icon"><i class="fa-solid fa-file-signature"></i></div>
                </div>

                <h4><i class="fa-solid fa-file-pen" style="width: 25px;"></i>Event Summary</h4>
                <div class="update-content">
                    <textarea required="" name="summary" id="role" cols="30" rows="9" style="width: 480px; border-color: #dddada; font-family: 'Open Sans','Helvetica Neue',Helvetica, Arial, sans-serif;"
                              placeholder="Enter Event's Summary Here*"></textarea>
                </div>
                <h4><i class="fa-solid fa-file-pen" style="width: 25px;"></i>Event Description</h4>
                <div class="update-content">
                    <textarea required="" name="content" id="role" cols="30" rows="9" style="width: 480px; border-color: #dddada; font-family: 'Open Sans','Helvetica Neue',Helvetica, Arial, sans-serif;"
                              placeholder="Enter Event's Description Here*"></textarea>
                </div>

                <button type="submit" style="cursor: pointer" class="login-box" name="action" value="CreateEvent">
                    Create Event
                </button>

            </form>
        </div>
        <!-- partial -->
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script><script  src="./css_FormCreate/js/script.js"></script>

    </body>
</html>
