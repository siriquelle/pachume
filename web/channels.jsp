<%@ page import="model.DataBaseConnection, bean.*, java.util.Vector, java.util.Iterator, java.sql.*,  command.channel.*" %>
<%@page import="model.User"%>
<%@ page errorPage="/error.jsp" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
//
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
        
        <title>pachume | channel </title>
        
        <link rel="shortcut icon" href="/media/images/favicon.png" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/feedback.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/viewport.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/head.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/logo.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/default.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/people.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/footer.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/join.css" />
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js" type="text/javascript"></script>
        
        <script src="/media/js/autogrow.js" type="text/javascript"></script>
        
        <script src="http://jqueryjs.googlecode.com/svn-history/r5326/trunk/plugins/color/jquery.color.js" type="text/javascript"></script>
        
        <script type="text/javascript">
            $(document).ready(function(){
                $('textarea').autogrow();
                
                $(".people_left_column_image_container").hover(function () {
                    $(this).animate( { backgroundColor: 'lightblue' }, 100);
                    $(this).fadeTo(100, 0.8);
                }, function () {
                    $(this).animate( { backgroundColor: 'white' }, 100);
                    $(this).fadeTo(100, 1.0);
                });
                
                $(".join_left_column_form_input_text").focus(function () {
                    $(this).animate( { backgroundColor: '#faff8a' }, 150);
                });
                    
                $(".join_left_column_form_input_text").blur(function () {
                    $(this).animate( { backgroundColor: '#faffbd' }, 150);
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
                    <jsp:param name="requestFrom" value="channels" />
                </jsp:include>
                <%} else
  {%>
                <jsp:include flush="true" page="/include/head_active.jsp" >
                    <jsp:param name="requestFrom" value="channels" />
                </jsp:include>
                <%}%>
                
                <div class="viewport_outer_container_body">
                    
                    <div class="default_right_column_container"> 
                        <jsp:include flush="true" page="/include/default_right.jsp" >
                            <jsp:param name="q" value="<%=q %>" />
                        </jsp:include>
                        
                    </div>
                    
                    <div class="default_left_column_container">
                        
                        <div class="default_left_column_head"><span style="cursor:pointer;">+</span>  most active channels</div>
                        
                        <%

            String mostActiveChannel_sql = "SELECT COUNT(channelId) AS pachumeCount, channelId FROM pachume WHERE channelId NOT IN (-1) GROUP BY channelId ORDER BY pachumeCount DESC LIMIT 20;";

            DataBaseConnection mostActiveChannel_db = new DataBaseConnection();
            mostActiveChannel_db.connect();
            ResultSet mostActiveChannel_rs = mostActiveChannel_db.execSQL(mostActiveChannel_sql);

            Vector mostActiveChannelAvatarLocationList = new Vector();
            Vector mostActiveChannelNameList = new Vector();

            int mostActiveChannelId = 0;
            String channelName = "";
            String channelAvatarLocation = "";

            while (mostActiveChannel_rs.next())
              {
                mostActiveChannelId = mostActiveChannel_rs.getInt("channelId");

                mostActiveChannelAvatarLocationList.add(GetChannelAvatarLocation.run(mostActiveChannelId));
                mostActiveChannelNameList.add(GetChannelName.run(mostActiveChannelId));
              }
            mostActiveChannel_db.close();

            Iterator iterChannelName = mostActiveChannelNameList.listIterator();
            Iterator iterChennelAvatarLocation = mostActiveChannelAvatarLocationList.listIterator();


            while (iterChennelAvatarLocation.hasNext())
              {
                channelName = (String) iterChannelName.next();
                channelAvatarLocation = (String) iterChennelAvatarLocation.next();
                        %>
                        
                        <div class="people_left_column_image_container">
                            <a href="/channel/<%=channelName.trim()%>">
                                <img height="102" width="102" alt="<%=channelName%>" src="<%=channelAvatarLocation%>" class="people_left_column_image" />
                                
                                <span class="people_left_column_screen_name" ><%=channelName%></span>
                            </a>        
                        </div>
                        <%}%>
                    </div>
                </div>
                
                <%if (session.getAttribute("thisUser") != null)
              {%>
                              
                <div class="default_left_column_container">
                    
                    
                    <div class="default_left_column_head"> <span style="cursor:pointer;">+</span> my channels</div>
                    
                    <%
                    User loggedUser = (User) session.getAttribute("thisUser");
                    int userId = loggedUser.getUserId();
                    Vector contactList = GetUsersChannels.run(userId);
                    Vector contactAvatarLocationList = new Vector();
                    Vector contactScreenNameList = new Vector();

                    int channelId = 0;

                    Iterator contactIter = contactList.listIterator();

                    while (contactIter.hasNext())
                      {
                        channelId = Integer.parseInt(contactIter.next().toString());

                        contactAvatarLocationList.add(GetChannelAvatarLocation.run(channelId));
                        contactScreenNameList.add(GetChannelName.run(channelId));
                      }

                    Iterator iterContactAvatarLocation = contactAvatarLocationList.listIterator();
                    Iterator iterContactScreenName = contactScreenNameList.listIterator();

                    while (iterContactAvatarLocation.hasNext())
                      {
                        channelName = (String) iterContactScreenName.next();
                        channelAvatarLocation = (String) iterContactAvatarLocation.next();
                    %>
                    
                    <div class="people_left_column_image_container">
                        <a href="/channel/<%=channelName.trim()%>">
                            <img height="102" width="102" alt="<%=channelName%>" src="<%=channelAvatarLocation%>" class="people_left_column_image" />
                            
                            <span class="people_left_column_screen_name" ><%=channelName%></span>
                        </a>        
                    </div>
                    <%}%>
                </div>
                
                <div class="default_left_column_container">
                    <div class="default_left_column_head"> <span style="cursor:pointer;" >+</span> create a channel</div>
                    
                    <div class="join_left_column_form_container">
                        <%if (GetChannelAuthorCount.run(userId) < 4)
                      {%>
                        <form method="post" action="/CreateChannel">
                            
                            <div class="join_left_column_form_input_container">
                                <label class="join_left_column_form_input_label" for="channelName">Channel Name:
                                    <span class="join_left_column_form_verification_output" id="channelName_verification_output"></span>
                                </label>
                                <input class="join_left_column_form_input_text" type="text" name="channelName" id="channelName" />
                            </div>
                            
                            <div class="join_left_column_form_input_container">
                                <label class="join_left_column_form_input_label" for="channelDescription">Channel Description:
                                    <span class="join_left_column_form_verification_output" id="channelDescription_verification_output"></span>
                                </label>
                                <textarea class="join_left_column_form_input_text" name="channelDescription" id="channelDescription" cols="1" rows="1" ></textarea>
                            </div>
                            
                            <div class="join_left_column_form_input_container">
                                <hr />
                                <input class="join_left_column_form_input_submit" type="submit" value="Continue.." name="submit" id="submit" />
                            </div>
                        </form>
                        <%} else
  {%>
                        <div class="join_left_column_form_input_container">
                            <label class="join_left_column_form_input_label" for="channelName">Author Level Reached
                                <span class="join_left_column_form_verification_output" id="channelName_verification_output">Apologies, but, at this time, four is the maximum number of channels a single user can author</span>
                            </label>
                        </div>
                        <%}%>
                    </div>
                </div>
                
                <%}%>
                
                <jsp:include flush="true" page="/include/footer.jsp" />
            </div>
        </div>
    <jsp:include flush="true" page="/include/feedback.jsp" /></body>
</html>
