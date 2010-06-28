<%@ page import="model.*, java.sql.*,  bean.groupBean, command.group.IsUserJoinedGroup" %>
<%@ page errorPage="/error.jsp" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
            String groupName = request.getParameter("groupName");
            groupBean thisGroup = new groupBean(groupName);

            if (thisGroup.getUserId() <= 0)
              {
                response.sendRedirect("/");
              }
            if (!IsUserJoinedGroup.run(thisGroup.getGroupId(), session))
              {
                response.sendRedirect("/?message=You are not authorized to view this group");
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
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js" type="text/javascript"></script>
        
        <script src="/media/js/autogrow.js" type="text/javascript"></script>
        
    </head>
    
    <body  style="background-image:url(<%= thisGroup.getBackgroundImagelocation()%>); background-repeat: <%=thisGroup.getBackgroundImageRepeat()%>;  background-color: <%= thisGroup.getBackgroundColor()%>;">
        
        <div class="viewport_outer_container_page">
            
            <div class="head_background_layer" ></div>
            
            <div class="viewport_inner_container_page">
                
                <%if (session.getAttribute("thisUser") == null)
              {%>
                <jsp:include flush="true" page="/include/head_inactive.jsp" >
                    <jsp:param name="requestFrom" value="group" />
                </jsp:include>
                <%} else
  {
    User loggedUser = (User) session.getAttribute("thisUser");

    int loggedUserId = loggedUser.getUserId();
    int groupOwnerId = thisGroup.getUserId();

                %>
                <%if (loggedUserId == groupOwnerId)
                      {%>
                <jsp:include flush="true" page="/include/head_active.jsp" >
                    <jsp:param name="requestFrom" value="group" />
                    <jsp:param name="groupName" value="<%=thisGroup.getGroupName()%>" />
                </jsp:include>
                <%} else
  {%>
                <jsp:include flush="true" page="/include/head_active.jsp" >
                    <jsp:param name="requestFrom" value="groups" />
                </jsp:include>
                
                <%}%>
                <%}%>
                
                <div></div>
                
                <div class="viewport_outer_container_body">
                    
                    <div class="default_right_column_container">
                        
                        <jsp:include flush="true" page="/include/group_right.jsp" >
                            <jsp:param name="groupName" value="<%=thisGroup.getGroupName() %>" /> 
                        </jsp:include>
                        
                    </div>
                    
                    <div class="body_left_column_container">
                        <div class="default_left_column_head"><span style="cursor:pointer;" id="activeChannels">+</span> <%=thisGroup.getGroupName()%></div>
                        <div class="body_left_column_top_image_container">
                            
                        <img class="body_left_column_top_image" src="<%= thisGroup.getAvatarLocation()%>" alt="<%= thisGroup.getGroupName()%>" width="102px" height="102px" />                        </div>
                        
                        <%if (session.getAttribute("thisUser") != null)
              {%>
                        <form method="post" action="/Pachume" >
                            <textarea class="body_left_column_top_input_textarea" rows="1" cols="1" name="pachume" id="pachume"></textarea>
                            
                            <input class="body_left_column_top_input_text" name="tags" id="tags" value="(split tags with spaces)" />
                            
                            <input name="groupName" id="groupName" type="hidden" value="<%=thisGroup.getGroupName().trim()%>" />
                            
                            <input type="submit" class="body_left_column_top_input_submit" value="pachume" />
                            <%if (!IsUserJoinedGroup.run(thisGroup.getGroupId(), session))
                              {%>
                            <span class="body_left_column_top_input_submit" ><a href="/JoinGroup?groupId=<%=thisGroup.getGroupId()%>">join <%=thisGroup.getGroupName()%></a> </span>
                            <%} else
  {%>
                            <span class="body_left_column_top_input_submit" ><a href="/LeaveGroup?groupId=<%=thisGroup.getGroupId()%>">leave <%=thisGroup.getGroupName()%></a> </span>
                            <%}%>
                        </form>
                        <%} else
  {%>
                        <textarea disabled="disabled" class="body_left_column_top_input_textarea" rows="10" cols="10" name="pachume" id="pachume" >login or join pachume to use this group:<%='\n' + thisGroup.getGroupDescription()%></textarea>
                        <%}%>
                    </div>
                    
                    <div class="body_left_column_container" >
                        
                        
                        
                        <jsp:include flush="true" page="/include/stream.jsp" >
                            <jsp:param name="groupId" value="<%= thisGroup.getGroupId()%>" />
                            <jsp:param name="requestFrom" value="group" />
                            <jsp:param name="p" value="<%= p %>" />
                        </jsp:include>
                        
                          
                    </div>
                    
                    
                    <jsp:include flush="true" page="/include/footer.jsp" />
                </div>
            </div>
        </div>
    <jsp:include flush="true" page="/include/feedback.jsp" /></body>
</html>
