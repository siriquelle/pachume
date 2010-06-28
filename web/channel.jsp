<%@ page import="model.*, java.sql.*,  bean.channelBean, command.channel.IsUserJoinedChannel" %>
<%@ page errorPage="/error.jsp" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%
            String channelName = request.getParameter("channelName");
            channelBean thisChannel = new channelBean(channelName);

            if (thisChannel.getUserId() <= 0)
              {
                response.sendRedirect("/");
              }

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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        
        <title>pachume channel | <%=thisChannel.getChannelDescription() %></title>
        
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
        
        <link rel="alternate" type="application/rss+xml" title="pachume.com" href="/feed/channel/rss.jsp?channel=<%=thisChannel.getChannelName().trim()%>" />
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js" type="text/javascript"></script>
        <script src="/media/js/alert_confirm.js" type="text/javascript"></script>
    </head>
    
    <body  style="background-image:url(<%= thisChannel.getBackgroundImagelocation()%>); background-repeat: <%=thisChannel.getBackgroundImageRepeat()%>;  background-color: <%= thisChannel.getBackgroundColor()%>;">
        
        <div class="viewport_outer_container_page">
            
            <div class="head_background_layer" ></div>
            
            <div class="viewport_inner_container_page">
                
                <%if (session.getAttribute("thisUser") == null)
              {%>
                <jsp:include flush="true" page="/include/head_inactive.jsp" >
                    <jsp:param name="requestFrom" value="channel" />
                </jsp:include>
                <%} else
  {
    User loggedUser = (User) session.getAttribute("thisUser");

    int loggedUserId = loggedUser.getUserId();
    int channelOwnerId = thisChannel.getUserId();

                %>
                <%if (loggedUserId == channelOwnerId)
                      {%>
                <jsp:include flush="true" page="/include/head_active.jsp" >
                    <jsp:param name="requestFrom" value="channel" />
                    <jsp:param name="channelName" value="<%=thisChannel.getChannelName()%>" />
                </jsp:include>
                <%} else
  {%>
                <jsp:include flush="true" page="/include/head_active.jsp" >
                    <jsp:param name="requestFrom" value="channels" />
                </jsp:include>
                
                <%}%>
                <%}%>
                
                <div></div>
                
                <div class="viewport_outer_container_body">
                    
                    <div class="default_right_column_container">
                        
                        <jsp:include flush="true" page="/include/channel_right.jsp" >
                            <jsp:param name="channelName" value="<%=thisChannel.getChannelName() %>" /> 
                        </jsp:include>
                        
                    </div>
                    
                    <div class="body_left_column_container">
                        <div class="default_left_column_head"><span style="cursor:pointer;" id="activeChannels">+</span> <%=thisChannel.getChannelName()%></div>
                        
                        <div class="body_left_column_top_image_container">
                            
                        <img class="body_left_column_top_image" src="<%= thisChannel.getAvatarLocation()%>" alt="<%= thisChannel.getChannelName()%>" width="102px" height="102px" />                        </div>
                        
                        <%if (session.getAttribute("thisUser") != null)
              {
                        %>
                        <form method="post" action="/Pachume" >
                            <textarea class="body_left_column_top_input_textarea" rows="1" cols="1" name="pachume" id="pachume"></textarea>
                            
                            <input class="body_left_column_top_input_text" name="tags" id="tags" value="(split tags with spaces)" />
                            
                            <input name="channelId" id="channelId" type="hidden" value="<%=thisChannel.getChannelId()%>" />
                            
                            <input type="submit" class="body_left_column_top_input_submit" value="pachume" />
                            <%if (!IsUserJoinedChannel.run(thisChannel.getChannelId(), session))
                              {%>    
                            <span class="body_left_column_top_input_submit" ><a href="/JoinChannel?channelId=<%=thisChannel.getChannelId()%>">join <%=thisChannel.getChannelName()%></a> </span>
                            <%} else
  {%>
                            <span class="body_left_column_top_input_submit" ><a href="/LeaveChannel?channelId=<%=thisChannel.getChannelId()%>">leave <%=thisChannel.getChannelName()%></a> </span>
                            <%}%>
                        </form>
                        <%} else
  {%>
                        <div class="profile_presence_container">
                            <div class="profile_presence_body_container" >
                                <div class="stream_presence_body_pachume">
                                    <%='\n' + thisChannel.getChannelDescription()%>
                                </div>
                            </div>
                        </div>
                        <%}%>
                    </div>
                    
                    <div class="body_left_column_container" >
                        
                        
                        
                        <jsp:include flush="true" page="/include/stream.jsp" >
                            <jsp:param name="channelId" value="<%= thisChannel.getChannelId()%>" />
                            <jsp:param name="requestFrom" value="channel" />
                            <jsp:param name="p" value="<%= p %>" />
                        </jsp:include>
                        
                        <% if (session.getAttribute("thisUser") == null)
              {%>
                        <div class="profile_left_column_container_comment_login" > <span class="profile_left_column_container_comment_login_message" >login or join to contrabute :)</span></div>
                        <%}%>
                    </div>
                    
                    
                    <jsp:include flush="true" page="/include/footer.jsp" />
                </div>
            </div>
        </div>
    <jsp:include flush="true" page="/include/feedback.jsp" /></body>
</html>
