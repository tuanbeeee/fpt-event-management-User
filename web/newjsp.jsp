<%-- 
    Document   : newjsp
    Created on : Jul 6, 2022, 4:25:23 PM
    Author     : Tuan Be
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .collapse {
                &:not(.show) {
                    display: none;
                }
            }

            .collapsing {
                height: 0;
                overflow: hidden;
                @include transition($transition-collapse);
            }   
        </style>
    </head>
    <body>
        <p>
            <a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                Link with href
            </a>
            <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                Button with data-bs-target
            </button>
        </p>
        <div class="collapse" id="collapseExample">
            <div class="card card-body">
                Some placeholder content for the collapse component. This panel is hidden by default but revealed when the user activates the relevant trigger.
            </div>
        </div>
    </body>
</html>
