<%@ page import="command.user.*, model.DataBaseConnection, bean.*, java.util.Vector, java.util.Iterator, java.sql.*" %>
<%@page import="model.User"%>
<%@ page errorPage="/error.jsp" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%

            String q = request.getParameter("q");

            if (q == null || q.length() <= 0)
              {
                q = "[no match]";
              }
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        
        <title>pachume | people </title>
        
        <link rel="shortcut icon" href="/media/images/favicon.png" />
        <link rel="stylesheet" type="text/css" href="/media/css/feedback.css" />
        <link rel="stylesheet" type="text/css" href="/media/css/viewport.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/head.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/logo.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/default.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/people.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/footer.css" />
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js" type="text/javascript"></script>
        
        <script src="http://jqueryjs.googlecode.com/svn-history/r5326/trunk/plugins/color/jquery.color.js" type="text/javascript"></script>
        
        <script type="text/javascript">
            $(document).ready(function(){

                $(".people_left_column_image_container").hover(function () {
                    $(this).animate( { backgroundColor: 'lightblue' }, 150);
                    $(this).fadeTo(100, 0.8);
                    
                }, function () {
                    $(this).animate( { backgroundColor: 'white' }, 150);
                    $(this).fadeTo(100, 1.0);
                
                });
            
                return false;
            });
        </script>
        
        
    </head>
    
    <body style="background-image:url('/media/images/bg.png'); background-repeat: repeat;  background-color: #000;">
        
        <div class="viewport_outer_container_page">
            
            <div class="head_background_layer" ></div>
            
            <div class="viewport_inner_container_page">
                
                <%if (session.getAttribute("thisUser") == null)
              {%>
                <jsp:include flush="true" page="/include/head_inactive.jsp" >
                    <jsp:param name="requestFrom" value="people" />
                </jsp:include>
                <%} else
  {%>
                <jsp:include flush="true" page="/include/head_active.jsp" >
                    <jsp:param name="requestFrom" value="people" />
                </jsp:include>
                <%}%>
                
                <div class="viewport_outer_container_body">
                    
                    <div class="default_right_column_container">
                        
                        <jsp:include flush="true" page="/include/default_right.jsp" >
                            <jsp:param name="q" value="<%=q %>" />
                        </jsp:include>
                        
                    </div>
                    
                    <div class="default_left_column_container">
                        
                        <div class="default_left_column_head"><span style="cursor:pointer;" id="activePeople">+</span> most active people</div>
                        
                        <%

            String mostActive_sql = "SELECT COUNT(pachumeId) AS pachumeCount, userId FROM pachume GROUP BY userId ORDER BY pachumeCount DESC LIMIT 8";
            DataBaseConnection mostActive_db = new DataBaseConnection();
            mostActive_db.connect();
            ResultSet mostActive_rs = mostActive_db.execSQL(mostActive_sql);
            Vector mostActiveAvatarLocationList = new Vector();
            Vector mostActiveScreenNameList = new Vector();
            int mostActiveUserId = 0;
            String screenName = "";
            String avatarLocation = "";

            while (mostActive_rs.next())
              {
                mostActiveUserId = mostActive_rs.getInt("userId");

                mostActiveAvatarLocationList.add(GetAvatarLocation.run(mostActiveUserId));
                mostActiveScreenNameList.add(GetScreenName.run(mostActiveUserId));
              }
            mostActive_db.close();

            Iterator iterAvatarLocation = mostActiveAvatarLocationList.listIterator();
            Iterator iterScreenName = mostActiveScreenNameList.listIterator();

            while (iterAvatarLocation.hasNext())
              {
                screenName = (String) iterScreenName.next();
                avatarLocation = (String) iterAvatarLocation.next();
                        %>
                        
                        <div class="people_left_column_image_container">
                            <a href="/profile/<%=screenName.trim()%>">
                                <img height="102" width="102" alt="<%=screenName%>" src="<%=avatarLocation%>" class="people_left_column_image" />
                                
                                <span class="people_left_column_screen_name" ><%=screenName%></span>
                            </a>        
                        </div>
                        <%}%>
                        
                    </div>
                    
                    <%if (session.getAttribute("thisUser") != null)
              {%>
                                                        
                    <div class="default_left_column_container">
                        
                        
                        <div class="default_left_column_head"><span style="cursor:pointer;" id="myContacts">+</span>  my contacts</div>
                        
                        <%
                        User loggedUser = (User) session.getAttribute("thisUser");
                        int userId = loggedUser.getUserId();
                        Vector contactList = GetUsersContacts.run(userId);
                        Vector contactAvatarLocationList = new Vector();
                        Vector contactScreenNameList = new Vector();

                        int contactId = 0;

                        Iterator contactIter = contactList.listIterator();

                        while (contactIter.hasNext())
                          {
                            contactId = Integer.parseInt(contactIter.next().toString());

                            contactAvatarLocationList.add(GetAvatarLocation.run(contactId));
                            contactScreenNameList.add(GetScreenName.run(contactId));
                          }

                        Iterator iterContactAvatarLocation = contactAvatarLocationList.listIterator();
                        Iterator iterContactScreenName = contactScreenNameList.listIterator();

                        while (iterContactAvatarLocation.hasNext())
                          {
                            screenName = (String) iterContactScreenName.next();
                            avatarLocation = (String) iterContactAvatarLocation.next();
                        %>
                        
                        <div class="people_left_column_image_container">
                            <a href="/profile/<%=screenName.trim()%>">
                                <img height="102" width="102" alt="<%=screenName%>" src="<%=avatarLocation%>" class="people_left_column_image" />
                                
                                <span class="people_left_column_screen_name" ><%=screenName%></span>
                            </a>        
                        </div>
                        <%}%>
                        
                    </div>
                    <%}%>
                    
                    <jsp:include flush="true" page="/include/footer.jsp" />
                </div>
            </div>
        </div>
    <jsp:include flush="true" page="/include/feedback.jsp" /></body>
</html>
