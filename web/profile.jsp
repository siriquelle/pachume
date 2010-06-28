<%@ page import="command.pachume.*, model.*, java.sql.*, java.util.Vector, java.util.Enumeration, command.time.GetTime,  bean.*,command.comment.*, command.verification.*" %>
<%@ page errorPage="/error.jsp" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%

            String user = request.getParameter("user");
            userBean thisUser = new userBean(user);

            if (thisUser.getUserId() <= 0)
              {
                response.sendRedirect("/");
              }

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        
        <title>pachume | <%=thisUser.getFirstName() %> <%=thisUser.getLastName() %> </title>
        
        <link rel="shortcut icon" href="/media/images/favicon.png" />
        <link rel="stylesheet" type="text/css" href="/media/css/feedback.css" />
        <link rel="stylesheet" type="text/css" href="/media/css/viewport.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/head.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/logo.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/default.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/body.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/stream.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/footer.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/profile.css" />
        
        <link rel="alternate" type="application/rss+xml" title="pachume.com" href="/feed/user/rss.jsp?user=<%=thisUser.getScreenName().trim()%>&amp;apiKey=<%=thisUser.getApiKey()%>&amp;requestFrom=profile" />
    </head>
    
    <body  style="background:url(' <%=thisUser.getBackgroundImageLocation()%> ') <%=thisUser.isBackgroundImageRepeat()%> <%=thisUser.getBackgroundColor()%>;" >
        
        <div class="viewport_outer_container_page">
            
            <div class="head_background_layer" ></div>
            
            <div class="viewport_inner_container_page">
                
                <%if (session.getAttribute("thisUser") == null)
              {%>
                <jsp:include flush="true" page="/include/head_inactive.jsp" >
                    <jsp:param name="requestFrom" value="comment" />
                </jsp:include>
                <%} else
  {%>
                <jsp:include flush="true" page="/include/head_active.jsp" >
                    <jsp:param name="requestFrom" value="comment" />
                </jsp:include>
                <%}%>
                
                <div ></div>
                
                <div class="viewport_outer_container_body">
                    <div class="default_right_column_container">
                        <jsp:include flush="true" page="/include/body_right.jsp" >
                            <jsp:param name="screenName" value="<%=thisUser.getScreenName() %>" /> 
                        </jsp:include>
                        
                    </div>
                    
                    <div class="body_left_column_container">
                        <div class="default_left_column_head"><span style="cursor:pointer;" id="activeChannels">+</span> <%=thisUser.getFirstName()%> <%=thisUser.getLastName()%></div>
                        
                        <div class="body_left_column_top_image_container">
                        <a href="/profile/<%= thisUser.getScreenName().trim()%>"><img class="body_left_column_top_image" src="<%= thisUser.getAvatarLocation()%>" alt="<%= thisUser.getScreenName()%>" width="102px" height="102px" /></a>                        </div>
                        
                        <div class="profile_presence_container" >
                            
                            <%

            pachumeBean thisPachume = new pachumeBean(thisUser.getUserId());

            String pachume = thisPachume.getPachume();
            String pachume_screenName = thisUser.getScreenName();
            int pachumeId = thisPachume.getPachumeId();
            int thisUser_userId = thisUser.getUserId();

                            %>
                            
                            <div class="profile_presence_body_container" >
                                
                                <div class="stream_presence_body_pachume">
                                    
                                    <a class="inlink" href="/comment/<%= pachumeId%>"><%= pachume%> </a>
                                </div>
                                
                                <div class="stream_presence_body_meta">
                                    
                                    By <%= pachume_screenName%>  
                                    <a href="/comment/<%=pachumeId%>"  >
                                        <%if (CountComments.run(pachumeId) > 0)
              {%>
                                        [<span class="stream_presence_body_meta_comment_count" ><%= CountComments.run(pachumeId)%></span>]
                                        <%}%>
                                        
                                        add comment 
                                        
                                    </a>
                                    <%= GetTime.run("pachume", "pachumeId", pachumeId)%>
                                    <%= GetPacumeLocation.run(pachumeId)%>
                                    
                                    <%= IsDeleatable.run(pachumeId, session)%>
                                    <%= IsSpamable.run(thisUser_userId, pachumeId, session)%>
                                    <%%>
                                </div>
                                
                                <div class="stream_presence_body_tags">
                                    
                                    <%
            Vector pachumeTags = GetPachumeTags.run(pachumeId);

            Enumeration e = pachumeTags.elements();

            while (e.hasMoreElements())
              {
                String nextElement = e.nextElement().toString();

                                    %>    
                                    <span class="stream_presence_body_tags_default"  > <a href="/search.jsp?requestFrom=explore&amp;q=<%= nextElement%>" ><%= nextElement%></a></span>
                                    <%}%>
                                    
                                </div>
                                
                            </div>
                        </div>
                        
                        <jsp:include flush="true" page="/include/left_head_friend.jsp" >
                            <jsp:param name="user" value="<%= pachume_screenName %>" />
                        </jsp:include>
                        
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
                            <jsp:param name="requestFrom" value="profile" />
                            <jsp:param name="p" value="<%= 1 %>" />
                        </jsp:include>
                        
                        <% if (session.getAttribute("thisUser") == null)
              {%>
                        <div class="profile_left_column_container_comment_login" > <span class="profile_left_column_container_comment_login_message" >login or join to follow <%=thisUser.getScreenName()%></span></div>
                        <%}%>
                    </div>
                    
                    
                    <jsp:include flush="true" page="/include/footer.jsp" />
                </div>
            </div>
        </div>
    <jsp:include flush="true" page="/include/feedback.jsp" /></body>
</html>
