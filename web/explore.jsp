<%@ page import="model.*, java.sql.*, bean.userBean" %>
<%@ page errorPage="/error.jsp" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
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
        
        <link rel="stylesheet" type="text/css" href="/media/css/stream.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/hottopic.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/footer.css" />
        
        <link rel="alternate" type="application/rss+xml" title="pachume.com" href="/feed/all/rss.jsp" />
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js" type="text/javascript"></script>        
        
        <script src="http://jqueryjs.googlecode.com/svn-history/r5326/trunk/plugins/color/jquery.color.js" type="text/javascript"></script>
        
        <script src="/media/js/alert_confirm.js" type="text/javascript"></script>
        
        
    </head>
    
    <body  style="background-image:url('/media/images/bg.png'); background-repeat: repeat; background-color:#000;" >
        <div class="viewport_outer_container_page">
            
            <div class="head_background_layer" ></div>
            
            <div class="viewport_inner_container_page">
                
                <%if (session.getAttribute("thisUser") == null)
              {%>
                <jsp:include flush="true" page="/include/head_inactive.jsp" >
                    <jsp:param name="requestFrom" value="explore" />
                </jsp:include>
                <%} else
  {%>
                <jsp:include flush="true" page="/include/head_active.jsp" >
                    <jsp:param name="requestFrom" value="explore" />
                </jsp:include>
                <%}%>
                
                <div class="viewport_outer_container_body">
                    
                    <div class="default_right_column_container">
                        <div class="default_right_column_top_head">Hot Topic</div>
                        
                        <jsp:include flush="true" page="/include/explore_right_hottopic.jsp" >
                            <jsp:param name="thisUser_userId" value="-1" />
                        </jsp:include>
                        
                    </div>
                    
                    <div class="default_left_column_container">
                        
                        <div class="default_left_column_head">Explore</div>
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
                            <jsp:param name="thisUser_userId" value="-1" />
                            <jsp:param name="requestFrom" value="viewAll" />
                            <jsp:param name="p" value="<%= p %>" />
                        </jsp:include>
                    </div>
                    
                    <jsp:include flush="true" page="/include/footer.jsp" />
                    
                </div>
            </div>
        </div>
        <jsp:include flush="true" page="/include/feedback.jsp" />
    </body>
</html>
