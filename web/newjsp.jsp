<%-- 
    Document   : newjsp
    Created on : Jul 18, 2022, 9:59:58 AM
    Author     : Tuan Be
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .picture-container{
                position: relative;
                cursor: pointer;
                text-align: center;
            }
            .picture{
                width: 1000px;
                height: 1000px;
                background-color: #999999;
                border: 4px solid #CCCCCC;
                color: #FFFFFF;
                border-radius: 50%;
                margin: 0px auto;
                overflow: hidden;
                transition: all 0.2s;
                -webkit-transition: all 0.2s;
            }
            .picture:hover{
                border-color: #2ca8ff;
            }
            .content.ct-wizard-green .picture:hover{
                border-color: #05ae0e;
            }
            .content.ct-wizard-blue .picture:hover{
                border-color: #3472f7;
            }
            .content.ct-wizard-orange .picture:hover{
                border-color: #ff9500;
            }
            .content.ct-wizard-red .picture:hover{
                border-color: #ff3b30;
            }
            .picture input[type="file"] {
                cursor: pointer;
                display: block;
                height: 100%;
                left: 0;
                opacity: 0 !important;
                position: absolute;
                top: 0;
                width: 100%;
            }

            .picture-src{
                width: 100%;

            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="picture-container">
                <div class="picture">
                    <img src="https://scontent.fsgn13-3.fna.fbcdn.net/v/t1.6435-9/195462539_2596085170685401_8094832095637303646_n.jpg?_nc_cat=102&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=_e4-12XcCU4AX8XXmv0&_nc_ht=scontent.fsgn13-3.fna&oh=00_AT-6bQA2RukDFAfznS9bf_id96VQSnfqXmqffS9WY7a2pg&oe=62F87FEC" class="picture-src" id="wizardPicturePreview" title="">
                    <input type="file" id="wizard-picture" class="">
                </div>
                <h6 class="">Choose Picture</h6>

            </div>
        </div>
        <script>
            $(document).ready(function () {
// Prepare the preview for profile picture
                $("#wizard-picture").change(function () {
                    readURL(this);
                });
            });
            function readURL(input) {
                if (input.files && input.files[0]) {
                    var reader = new FileReader();

                    reader.onload = function (e) {
                        $('#wizardPicturePreview').attr('src', e.target.result).fadeIn('slow');
                    }
                    reader.readAsDataURL(input.files[0]);
                }
            }
        </script>
    </body>
</html>
