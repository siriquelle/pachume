<%@ page import="bean.channelBean, java.sql.*" %>
<%@ page errorPage="/error.jsp" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
            channelBean thisChannel = new channelBean(request.getParameter("channelName"));
            session.setAttribute("channelName", thisChannel.getChannelName());
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        
        <title>pachume | channel settings</title>
        
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
                      $("#changeAvatarFoldLink").css("border-color","#800");
                      //<%} else if (request.getParameter("tab").equals("2"))
  {%>
          $("#changeAvatarFold").hide();
          $("#changeBackgroundFoldLink").css("border-color","#800");
          //<%}%>
          return false;
      });
        </script>
        
    </head>
    
    <body style="background-image:url(<%= thisChannel.getBackgroundImagelocation()%>); background-repeat: <%= thisChannel.getBackgroundImageRepeat()%>;  background-color: <%=thisChannel.getBackgroundColor()%>;">
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
                        
                        <div class="default_left_column_head"><a href="/channel/<%=thisChannel.getChannelName().trim()%>" class="settings_left_column_top_head_link"><%=thisChannel.getChannelName()%></a> settings  </div>
                        <div class="settings_left_column_tab_container" >
                            <div class="settings_left_column_tab_item" id="changeAvatarFoldLink">Chanel Avatar</div>
                            <div class="settings_left_column_tab_item" id="changeBackgroundFoldLink"> Channel Background </div>
                        </div>
                        
                        <div id="changeAvatarFold">
                            <div class="settings_left_column_item_container" >
                                <form action="/uploadChannelAvatar" method="post" enctype="multipart/form-data" id="upform1">  
                                    <p class="viewport_valid_element_container">
                                        <label class="settings_left_column_input_label" for="uploadImage1">avatar upload:</label>
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
                                        <input class="settings_left_column_input_file_submit" type="submit" name="submit1" id="submit1" value="upload.." />  
                                    </p>
                                </form>  
                            </div>
                            <div class="settings_left_column_avatar">
                            <img src="<%=thisChannel.getAvatarLocation()%>" alt="<%=thisChannel.getChannelName()%>"/>                            </div>
                        </div>
                        <!-- /changeAvatarFold !-->
                        
                        <div id="changeBackgroundFold">
                            <div class="settings_left_column_item_container">
                                <form method="post" action="/changeChannelBackgroundColor" >
                                    
                                    <p class="viewport_valid_element_container">
                                        <label class="settings_left_column_input_label" for="backgroundColor">background color:</label>
                                    </p>
                                    <div class="settings_left_column_input_text_container" >
                                        <p class="viewport_valid_element_container">
                                            <input class="settings_left_column_input_text" style="background-color: <%= thisChannel.getBackgroundColor()%>;" type="text" name="backgroundColor" id="backgroundColor" value="<%= thisChannel.getBackgroundColor()%>" />
                                        </p>
                                    </div>
                                    <p class="viewport_valid_element_container">
                                        <input class="settings_left_column_input_text_submit" type="submit" name="submit2" id="submit2" value="change.." />  
                                    </p>
                                </form>  
                            </div>
                            
                            <div class="settings_left_column_item_container">
                                <form method="post" action="/changeChannelBackgroundImageRepeat" >
                                    <p class="viewport_valid_element_container">
                                        <label class="settings_left_column_input_label" for="backgroundImageRepeat">background repeat:</label>
                                    </p>
                                    <div class="settings_left_column_input_text_container" >
                                        
                                        <%

            String checked = "";

            if (thisChannel.getBackgroundImageRepeat().equalsIgnoreCase("repeat"))
              {
                checked = "checked=\"checked\"";
              }

                                        %>
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
                                <form action="/uploadChannelBackgroundImage" method="post" enctype="multipart/form-data" id="upform2">  
                                    <p class="viewport_valid_element_container">
                                        <label class="settings_left_column_input_label" for="uploadImage2">background image:</label>
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
                                        <input class="settings_left_column_input_file_submit" type="submit" name="submit4" id="submit4" value="upload.." />  
                                    </p>
                                </form>  
                            </div>
                            
                            
                        </div><!-- /changeBackgroundFold !-->
                    </div>
                    
                    <jsp:include flush="true" page="/include/footer.jsp" />
                    
                </div>
            </div>
        </div>
        
        
        
    <jsp:include flush="true" page="/include/feedback.jsp" /></body>
</html>
