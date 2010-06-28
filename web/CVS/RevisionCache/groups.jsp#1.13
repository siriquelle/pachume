<%@ page import="model.DataBaseConnection, bean.*, java.util.Vector, java.util.Iterator, java.sql.*,  command.group.*" %>
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
        
        <title>pachume | group </title>
        
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
                    $(this).animate( { backgroundColor: 'lightblue' }, 150);
                    $(this).fadeTo(100, 0.8);
                }, function () {
                    $(this).animate( { backgroundColor: 'white' }, 150);
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
                
                <%
            if (session.getAttribute("thisUser") == null)
              {
                %>
                <jsp:include flush="true" page="/include/head_inactive.jsp" >
                    <jsp:param name="requestFrom" value="groups" />
                </jsp:include>
                <%                  } else
                  {
                %>
                <jsp:include flush="true" page="/include/head_active.jsp" >
                    <jsp:param name="requestFrom" value="groups" />
                </jsp:include>
                <%              }
                %>
                
                <div class="viewport_outer_container_body">
                    
                    <div class="default_right_column_container">
                        <jsp:include flush="true" page="/include/default_right.jsp" >
                            <jsp:param name="q" value="<%=q %>" />
                        </jsp:include>
                    </div>
                    
                    <%
            if (session.getAttribute("thisUser") != null)
              {
                    %>
                    
                    <div class="default_left_column_container">
                        
                        
                        <div class="default_left_column_head"> <span style="cursor:pointer;" >+</span> my groups</div>
                        
                        <%
                          String groupName = "";
                          String groupAvatarLocation = "";
                          User loggedUser = (User) session.getAttribute("thisUser");
                          int userId = loggedUser.getUserId();
                          Vector contactList = GetUsersGroups.run(userId);
                          Vector contactAvatarLocationList = new Vector();
                          Vector contactScreenNameList = new Vector();

                          String groupId = "";

                          Iterator contactIter = contactList.listIterator();
                          int c = 0;
                          while (contactIter.hasNext())
                            {
                              c++;
                              groupId = contactIter.next().toString();

                              contactAvatarLocationList.add(GetGroupAvatarLocation.run(groupId));
                              contactScreenNameList.add(GetGroupName.run(groupId));
                            }

                          Iterator iterContactAvatarLocation = contactAvatarLocationList.listIterator();
                          Iterator iterContactScreenName = contactScreenNameList.listIterator();
                          if (c == 0)
                            {
                        %>
                        
                        <div class="join_left_column_form_container">
                            
                            <div class="join_left_column_form_input_container">
                                <div class="join_left_column_form_input_label" >pachume Groups:</div>
                                <div style="height:10px;" ></div>
                                <div class="join_left_column_form_verification_output" >pachume groups are private</div>
                                <div style="height:10px;" ></div>
                                <div class="join_left_column_form_verification_output">so, you can discuss sensitive stuff</div>
                                <div style="height:10px;" ></div>
                                <div class="join_left_column_form_verification_output" >like, what to buy dad for his birthday</div>
                                <div style="height:10px;" ></div>
                                <div class="join_left_column_form_verification_output" >and, how to set up an ebay account</div>
                                
                            </div>
                            
                        </div>
                        
                        <%                          } else
                          {
                            while (iterContactAvatarLocation.hasNext())
                              {
                                groupName = (String) iterContactScreenName.next();
                                groupAvatarLocation = (String) iterContactAvatarLocation.next();
                        %>
                        
                        
                        <div class="people_left_column_image_container">
                            <a href="/group/<%=groupName.trim()%>">
                                <img height="102" width="102" alt="<%=groupName%>" src="<%=groupAvatarLocation%>" class="people_left_column_image" />
                                
                                <span class="people_left_column_screen_name" ><%=groupName%></span>
                            </a>        
                        </div>
                        
                        <%
                              }
                        %> <%
                            }
                        %>
                    </div>
                    
                    <div class="default_left_column_container">
                        <div class="default_left_column_head"> <span style="cursor:pointer;" >+</span> create a group</div>
                        
                        <div class="join_left_column_form_container">
                            <%
                            if (GetGroupAuthorCount.run(userId) < 4)
                            {
                            %>
                            <form method="post" action="/CreateGroup">
                                
                                <div class="join_left_column_form_input_container">
                                    <label class="join_left_column_form_input_label" for="groupName">Group Name:
                                        <span class="join_left_column_form_verification_output" id="groupName_verification_output"></span>
                                    </label>
                                    <input class="join_left_column_form_input_text" type="text" name="groupName" id="groupName" />
                                </div>
                                
                                <div class="join_left_column_form_input_container">
                                    <label class="join_left_column_form_input_label" for="groupDescription">Group Description:
                                        <span class="join_left_column_form_verification_output" id="groupDescription_verification_output"></span>
                                    </label>
                                    <textarea class="join_left_column_form_input_text" name="groupDescription" id="groupDescription" cols="1" rows="1"></textarea>
                                </div>
                                
                                <div class="join_left_column_form_input_container">
                                    <hr />
                                    <input class="join_left_column_form_input_submit" type="submit" value="Continue.." name="submit" id="submit" />
                                </div>
                            </form>
                            <%                              } else
                              {
                            %>
                            <div class="join_left_column_form_input_container">
                                <label class="join_left_column_form_input_label" for="channelName">Author Level Reached
                                    <span class="join_left_column_form_verification_output" id="channelName_verification_output">Apologies, but, at this time, four is the maximum number of groups a single user can author</span>
                                </label>
                            </div>
                            <%                          }
                            %>
                        </div>
                    </div>
                    <%
                      } else
                      {
                    %>
                    <div class="default_left_column_container">
                        <div class="default_left_column_head"> <span style="cursor:pointer;" id="myChanels">+</span> Groups</div>
                        
                        <div class="join_left_column_form_container">
                            
                            <div class="join_left_column_form_input_container">
                                <div class="join_left_column_form_input_label" >pachume Groups:</div>
                                <div style="height:10px;" ></div>
                                <div class="join_left_column_form_verification_output" >pachume groups are private</div>
                                <div style="height:10px;" ></div>
                                <div class="join_left_column_form_verification_output">so, you can discuss sensitive stuff</div>
                                <div style="height:10px;" ></div>
                                <div class="join_left_column_form_verification_output" >like, what to buy dad for his birthday</div>
                                <div style="height:10px;" ></div>
                                <div class="join_left_column_form_verification_output" >and, how to set up an ebay account</div>
                                
                            </div>
                            
                        </div>
                    </div>
                    <%              }
                    %>
                    
                    <jsp:include flush="true" page="/include/footer.jsp" />
                </div>
            </div>
        </div>
    <jsp:include flush="true" page="/include/feedback.jsp" /></body>
</html>
