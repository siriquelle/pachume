<%@ page errorPage="/error.jsp" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        
        <title>pachume | your college microblog </title>
        
        <link rel="shortcut icon" href="/media/images/favicon.png" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/feedback.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/viewport.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/head.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/logo.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/footer.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/default.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/login.css" />
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js" type="text/javascript"></script>
        
        <script src="http://jqueryjs.googlecode.com/svn-history/r5326/trunk/plugins/color/jquery.color.js" type="text/javascript"></script>
        
        <script type="text/javascript">
            $(document).ready(function(){
                
                $(".login_left_column_form_input_text").focus(function(){
                    
                    $(this).animate( { backgroundColor: '#faff8a' }, 150);
                });
                
                $(".login_left_column_form_input_text").blur(function(){
                    $(this).animate( { backgroundColor: '#faffbd' }, 150);
                });
                
                return false;
            });
        </script>
    </head>
    
    <body  style="background-image:url(/media/images/bg.png); background-repeat: repeat;" >
        
        <div class="viewport_outer_container_page">
            
            <div class="head_background_layer" ></div>
            
            <div class="viewport_inner_container_page">
                
                <jsp:include flush="true" page="/include/head_inactive.jsp" >
                    <jsp:param name="requestFrom" value="login" /> 
                </jsp:include>
                
                <div class="viewport_outer_container_body">
                    
                    <div class="default_right_column_container">
                        
                        <div class="default_right_column_top_head" >Why pachume?</div>
                        
                        <div class="login_right_column_body">
                            
                            <div class="login_right_column_body_bullet"></div>
                        forgot password                        </div>
                        
                        <div class="login_right_column_body">
                            
                            <div class="login_right_column_body_bullet"></div>
                        people                        </div>
                        
                        <div class="login_right_column_body">
                            
                            <div class="login_right_column_body_bullet"></div>
                        Make a classroom                        </div>
                    </div>
                    
                    <div class="default_left_column_container">
                        
                        <div class="default_left_column_head" >Login to your account </div>
                        
                        <div class="login_left_column_form_container">
                            
                            <form method="post" action="/Login">
                                
                                <div class="login_left_column_form_input_container">
                                    
                                    <label class="login_left_column_form_input_label" for="screenName">Screen Name:                                    </label>
                                    
                                    <input class="login_left_column_form_input_text" type="text" name="screenName" id="screenName" />
                                </div>
                                
                                <div class="login_left_column_form_input_container">
                                    <label class="login_left_column_form_input_label" for="userPassword">Password:</label>
                                    <input class="login_left_column_form_input_text" type="password" name="userPassword" id="userPassword" />
                                </div>
                                
                                <div class="login_left_column_form_input_container">
                                    <label class="login_left_column_form_input_label_checkbox" for="saveSession">Remember me on this computer:</label>
                                    <input class="login_left_column_form_input_checkbox" type="checkbox" name="saveSession" id="saveSession" value="true" />
                                </div>
                                
                                <div class="login_left_column_form_input_container">
                                    <input class="login_left_column_form_input_submit" type="submit" value="Continue.." name="submit" id="submit" />
                                </div>
                            </form>
                        </div>
                    </div>
                    
                    <jsp:include flush="true" page="/include/footer.jsp" />
                </div>
            </div>
        </div>
    <jsp:include flush="true" page="/include/feedback.jsp" /></body>
</html>
