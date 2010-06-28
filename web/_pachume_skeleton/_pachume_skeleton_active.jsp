<%@ page import="model.*, java.sql.*, bean.userBean" %>
<%@ page errorPage="/login.jsp?message=An Error has occured" %>

<% User user = (User) session.getAttribute("thisUser");%>
<% userBean thisUser = new userBean(user.getScreenName());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        
        <title>pachume | your college microblog</title>
        
        <link rel="shortcut icon" href="/media/images/favicon.png" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/viewport.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/head.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/logo.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/default.css" />
        

        
        <link rel="stylesheet" type="text/css" href="/media/css/footer.css" />
        
    </head>
    
    <body  style="background-image:url(<%= thisUser.getBackgroundImageLocation() %>); background-repeat: <%= thisUser.isBackgroundImageRepeat()%>;  background-color: <%= thisUser.getBackgroundColor()%>;">
        
        <div class="viewport_outer_container_page">
            
            <div class="head_background_layer" ></div>
            
            <div class="viewport_inner_container_page">
                
                <jsp:include flush="true" page="/include/head_active.jsp" >
                    <jsp:param name="requestFrom" value="settings" />
                </jsp:include>
                
                <div class="viewport_outer_container_body">
                    
                    <div class="default_right_column_container">
                        <div class="default_right_column_top_head">Statistics</div>
                    </div>
                    
                    <div class="default_left_column_container">
                        
                        <div class="default_left_column_head">settings</div>
                        
                    </div>
                    
                    <jsp:include flush="true" page="/include/footer.jsp" />
                    
                </div>
            </div>
        </div>
    <jsp:include flush="true" page="/include/feedback.jsp" /></body>
</html>
