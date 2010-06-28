<%@ page import="command.user.*, model.*, bean.userBean, java.sql.*" %>
<%@ page errorPage="/error.jsp" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<% User loggedUser = (User) session.getAttribute("thisUser");%>
<% userBean thisUser = new userBean(loggedUser.getScreenName());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        
        <title>pachume | settings</title>
        
        <link rel="shortcut icon" href="/media/images/favicon.png" />
        <link rel="stylesheet" type="text/css" href="/media/css/feedback.css" />
        <link rel="stylesheet" type="text/css" href="/media/css/viewport.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/head.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/logo.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/default.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/settings.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/footer.css" />
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js" type="text/javascript"></script>
        <script src="/media/js/tabs_settings.js" type="text/javascript"></script>
        
        <script type="text/javascript">
            $(document).ready(function(){
                
                //<%if (request.getParameter("tab") == null || request.getParameter("tab").equals("1"))
              {%>
                      $("#changeBackgroundFold").hide();
                      $("#changeNotificationFold").hide();
                      $("#changeAvatarFoldLink").css("border-color","#800");
                      //<%} else if (request.getParameter("tab").equals("2"))
  {%>
          $("#changeAvatarFold").hide();
          $("#changeNotificationFold").hide();
          $("#changeBackgroundFoldLink").css("border-color","#800");
          //<%} else if (request.getParameter("tab").equals("3"))
  {%>
          $("#changeAvatarFold").hide();
          $("#changeBackgroundFold").hide();
          $("#changeNotificationFoldLink").css("border-color","#800");
          //<%}%>
          return false;
      });
        </script>
        
    </head>
    
    <body style="background-image:url(<%= thisUser.getBackgroundImageLocation()%>); background-repeat: <%= thisUser.isBackgroundImageRepeat()%>;  background-color: <%= thisUser.getBackgroundColor()%>;">
        <div class="viewport_outer_container_page">
            
            <div class="head_background_layer" ></div>
            
            <div class="viewport_inner_container_page">
                
                <jsp:include flush="true" page="/include/head_active.jsp" >
                    <jsp:param name="requestFrom" value="settings" />
                </jsp:include>
                
                <div class="viewport_outer_container_body">
                    
                    <div class="default_right_column_container">
                        <div class="default_right_column_top_head">my statistics</div>
                        
                        <%

            int pachumeCount = GetUsersPachumeCount.run(thisUser.getUserId());
            int commentCount = GetUsersCommentCount.run(thisUser.getUserId());
            int following = GetUsersContactCount.run(thisUser.getUserId());
            int followers = GetUsersFollowerCount.run(thisUser.getUserId());
            int awesomeness = pachumeCount * 2 + commentCount * 2 + following + followers * 3;

            if (awesomeness >= 100)
              {
                awesomeness = 100;
              }
                        %>
                        
                        <div class="default_right_column_body_container" >
                            <div class="settings_right_column_statistics_title" >pachumes :</div> 
                            <div class="settings_right_column_statistics_statistic" ><span class="settings_right_column_statistics_number"><%=pachumeCount%></span></div>
                        </div>
                        
                        <div class="default_right_column_body_container" >
                            <div class="settings_right_column_statistics_title" >comments :</div> 
                            <div class="settings_right_column_statistics_statistic" ><span class="settings_right_column_statistics_number"><%=commentCount%></span></div>
                        </div>
                        
                        <div class="default_right_column_body_container" >
                            <div class="settings_right_column_statistics_title" >following :</div> 
                            <div class="settings_right_column_statistics_statistic" ><span class="settings_right_column_statistics_number"><%=following%></span></div>
                        </div>
                        
                        <div class="default_right_column_body_container" >
                            <div class="settings_right_column_statistics_title" >followers :</div> 
                            <div class="settings_right_column_statistics_statistic" ><span class="settings_right_column_statistics_number"><%=followers%></span></div>
                        </div>
                        
                        <div class="default_right_column_body_container" >
                            <div class="settings_right_column_statistics_title" >awesomeness :</div> 
                            <div class="settings_right_column_statistics_statistic" ><span class="settings_right_column_statistics_number" ><%=awesomeness + "%"%></span></div>
                        </div>
                        
                    </div>
                    
                    
                    <div class="default_left_column_container">
                        <div class="default_left_column_head">
                        <a href="/profile/<%=thisUser.getScreenName().trim()%>" class="settings_left_column_top_head_link">my</a> settings                        </div>
                        <div class="settings_left_column_tab_container" >
                            <div class="settings_left_column_tab_item" id="changeAvatarFoldLink">User Settings</div>
                            <div class="settings_left_column_tab_item" id="changeBackgroundFoldLink"> Background Settings</div>
                            <div class="settings_left_column_tab_item" id="changeNotificationFoldLink"> Notification Options</div>
                        </div>
                        
                        <div id="changeAvatarFold">
                            <div class="settings_left_column_item_container">
                                
                                <form action="/uploadImage" method="post" enctype="multipart/form-data" id="upformUploadImage">  
                                    <p class="viewport_valid_element_container">
                                        <label class="settings_left_column_input_label" for="uploadImage2">upload avatar:</label>
                                    </p>
                                    
                                    <div class="settings_left_column_input_file_container" >
                                        
                                        <div class="settings_left_column_input_file" >
                                            <p class="viewport_valid_element_container">
                                                <input type="file" name="uploadImage" id="uploadImage2" />
                                            </p>
                                        </div>
                                        <p class="viewport_valid_element_container">
                                            <input class="settings_left_column_input_file_text" id="smoke2" />
                                        </p>
                                        <p class="viewport_valid_element_container">
                                            <input class="settings_left_column_input_file_button" type="button" value="file.." />  
                                        </p>
                                    </div>
                                    <p class="viewport_valid_element_container">
                                        <input class="settings_left_column_input_file_submit" type="submit" name="submit1" id="submit1" value="upload.." />  
                                    </p>
                                </form>  
                            </div>
                            
                            <div class="settings_left_column_avatar">
                            <img src="<%=thisUser.getAvatarLocation()%>" alt="<%=thisUser.getScreenName()%>"/>                            </div>
                            
                            <div class="settings_left_column_avatar">
                                <div class="settings_left_column_avatar_developer" >developer Key:</div>
                            </div>
                            <div class="settings_left_column_avatar">
                                <div class="settings_left_column_avatar_developer" ><%=thisUser.getApiKey()%></div>
                            </div>
                            
                        </div>
                        <!-- /changeAvatarFold !-->
                        
                        <div id="changeBackgroundFold">
                            <div class="settings_left_column_item_container">
                                <form method="post" action="/changeBackgroundColor" >  
                                    <p class="viewport_valid_element_container">
                                        <label class="settings_left_column_input_label" for="backgroundColor">background color:</label>
                                    </p>
                                    <div class="settings_left_column_input_text_container" >
                                        <p class="viewport_valid_element_container">
                                            <input class="settings_left_column_input_text" style="background-color: <%= thisUser.getBackgroundColor()%>;" type="text" name="backgroundColor" id="backgroundColor" value="<%= thisUser.getBackgroundColor()%>" />
                                        </p>
                                    </div>
                                    <p class="viewport_valid_element_container">
                                        <input class="settings_left_column_input_text_submit" type="submit" name="submit2" id="submit2" value="change.." />  
                                    </p>
                                </form>  
                            </div>
                            
                            <div class="settings_left_column_item_container">
                                <form method="post" action="/changeBackgroundImageRepeat" >
                                    <p class="viewport_valid_element_container">
                                        <label class="settings_left_column_input_label" for="backgroundImageRepeat">background repeat:</label>
                                    </p>
                                    <%

            String checked = "";

            if (thisUser.isBackgroundImageRepeat().equalsIgnoreCase("repeat"))
              {
                checked = "checked=\"checked\"";
              }

                                    %>                                   
                                    <div class="settings_left_column_input_text_container" >
                                        <p class="viewport_valid_element_container">
                                        <input class="settings_left_column_input_text" type="checkbox" value="repeat" name="backgroundImageRepeat" id="backgroundImageRepeat" <%= checked%> />
                                               </p>
                                    </div>
                                    <p class="viewport_valid_element_container">
                                        <input class="settings_left_column_input_checkbox_submit" type="submit" name="submit3" id="submit3" value="change.." />  
                                    </p>
                                </form>  
                            </div>
                            
                            <div class="settings_left_column_item_container">
                                <form action="/uploadBackgroundImage" method="post" enctype="multipart/form-data" id="upformUploadBackgroundImage">  
                                    <p class="viewport_valid_element_container">
                                        <label class="settings_left_column_input_label" for="uploadImage1">background image:</label>
                                    </p>
                                    <div class="settings_left_column_input_file_container" >
                                        
                                        <div class="settings_left_column_input_file" >
                                            <p class="viewport_valid_element_container">
                                                <input type="file" name="uploadImage" id="uploadImage1" />
                                            </p>
                                        </div>
                                        <p class="viewport_valid_element_container">
                                            <input class="settings_left_column_input_file_text" id="smoke1" />
                                        </p>
                                        <p class="viewport_valid_element_container">
                                            <input class="settings_left_column_input_file_button" type="button" value="file.." />  
                                        </p>
                                    </div>
                                    <p class="viewport_valid_element_container">
                                    <input class="settings_left_column_input_file_submit" type="submit" name="submit4" id="submit4" value="upload.." />  
                                </p>
                                </form>  
                            </div>
                            
                        </div><!-- /changeBackgroundFold !-->
                        
                        <div id="changeNotificationFold">
                            <div class="settings_left_column_item_container" >
                                <form method="post" action="/UpdateEmail" >  
                                    <p class="viewport_valid_element_container">
                                    <label class="settings_left_column_input_label" for="email">Email: </label>
                                </p>
                                    <div class="settings_left_column_input_text_container" >
                                        <p class="viewport_valid_element_container">
                                            <input class="settings_left_column_input_text" type="text" name="email" id="email" value="<%= thisUser.getEmail()%>" />
                                        </p>
                                    </div>
                                    <p class="viewport_valid_element_container">
                                        <input class="settings_left_column_input_text_submit" type="submit" name="submit5" id="submit5" value="change.." />  
                                    </p>
                                </form>  
                            </div>
                            
                            <div class="settings_left_column_item_container">
                                <form method="post" action="/changeNotification" >
                                    <p class="viewport_valid_element_container">
                                    <label class="settings_left_column_input_label" for="notifiable">comment notification: </label>
                                </p>
                                    <%

            String notifiable = "";

            if (thisUser.isNotifiable())
              {
                notifiable = "checked=\"checked\"";
              }

                                    %>                                    
                                    <div class="settings_left_column_input_text_container" >
                                        
                                        
                                        <p class="viewport_valid_element_container">
                                        <input class="settings_left_column_input_text" type="checkbox" value="true" name="notifiable" id="notifiable" <%= notifiable%> />
                                               </p>
                                    </div>
                                    <p class="viewport_valid_element_container">
                                        <input class="settings_left_column_input_checkbox_submit" type="submit" name="submit6" id="submit6" value="change.." />  
                                    </p>
                                </form>  
                            </div>
                            
                        </div>
                        <!-- /changeNotificationFold !-->
                        
                    </div>
                    
                    <jsp:include flush="true" page="/include/footer.jsp" />
                    
                </div>
            </div>
        </div>
    <jsp:include flush="true" page="/include/feedback.jsp" /></body>
</html>
