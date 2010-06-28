<%@ page import="model.*, java.sql.*,  bean.userBean, java.util.Random" %>
<%@ page errorPage="/error.jsp?message=An Error has occured" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<% User loggedUser = (User) session.getAttribute("thisUser");%>
<% userBean thisUser = new userBean(loggedUser.getScreenName());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        
        <title>pachume | your college microblog</title>
        
        <link rel="shortcut icon" href="/media/images/favicon.png" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/feedback.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/viewport.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/head.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/logo.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/default.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/body.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/stream.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/footer.css" />
        <link rel="alternate" type="application/rss+xml" title="pachume.com" href="/feed/user/rss.jsp?user=<%=thisUser.getScreenName().trim()%>&amp;apiKey=<%=thisUser.getApiKey()%>&amp;requestFrom=stream" />
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js" type="text/javascript"></script>
        <script src="/media/js/alert_confirm.js" type="text/javascript"></script>
        
    </head>
    
    <body  style="background-image:url(<%= thisUser.getBackgroundImageLocation()%>); background-repeat: <%= thisUser.isBackgroundImageRepeat()%>;  background-color: <%= thisUser.getBackgroundColor()%>;">
        
        <div class="viewport_outer_container_page">
            
            <div class="head_background_layer" ></div>
            
            <div class="viewport_inner_container_page">
                
                <jsp:include flush="true" page="/include/head_active.jsp" >
                    <jsp:param name="requestFrom" value="userPage" />
                </jsp:include>
                
                <div ></div>
                
                <div class="viewport_outer_container_body">
                    
                    <div class="default_right_column_container">
                        
                        <jsp:include flush="true" page="/include/body_right.jsp" >
                            <jsp:param name="screenName" value="<%=thisUser.getScreenName() %>" /> 
                        </jsp:include>
                    </div>
                    
                    <%
            Random random = new Random();
            String welcome[] =
              {
                "What's the craic?", "Hvad sker der?",
                "Que se passe-t-il?", "Laget?", "Cosa succede?", "Wat is er?", "What's up?"
              };
                    %>
                    
                    <div class="body_left_column_container">
                        <div class="default_left_column_head"><span style="cursor:pointer;" id="activeChannels">+</span> <%=welcome[random.nextInt(welcome.length)]%></div>
                        <div class="body_left_column_top_image_container">
                            <a href="/" >
                                <img class="body_left_column_top_image" src="<%= thisUser.getAvatarLocation()%>" alt="<%= thisUser.getScreenName()%>" width="102px" height="102px" />                        
                            </a>     
                        </div>
                        
                        
                        <form method="post" action="/Pachume" >
                            <textarea class="body_left_column_top_input_textarea" rows="1" cols="1" name="pachume" id="pachume"></textarea>
                            
                            <input class="body_left_column_top_input_text" name="tags" id="tags" value="(split tags with spaces)" />
                            
                            <input type="submit" class="body_left_column_top_input_submit" value="pachume" />
                        </form>
                    </div>
                    
                    <div class="body_left_column_container" >
                        
                        <%

            int p = 0;

            String pString = request.getParameter("p");

            if (pString == null)
              {
                p = 0;
              } else
              {
                p = Integer.parseInt(pString);

                if (p < 0)
                  {
                    p = 0;
                  }
              }

                        %>  
                        
                        <jsp:include flush="true" page="/include/stream.jsp" >
                            <jsp:param name="thisUser_userId" value="<%= thisUser.getUserId() %>" />
                            <jsp:param name="requestFrom" value="stream" />
                            <jsp:param name="p" value="<%= p %>" />
                        </jsp:include>
                        
                    </div>
                    
                    <jsp:include flush="true" page="/include/footer.jsp" />
                </div>
            </div>
        </div>
    <jsp:include flush="true" page="/include/feedback.jsp" /></body>
</html>
