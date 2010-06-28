<%@ page import="model.DataBaseConnection, bean.*, java.sql.*,  java.util.*" %>
<%@ page errorPage="/error.jsp" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
            String user = "pachume";
            userBean thisUser = new userBean(user);

            String q = request.getParameter("q");

            if (q == null || q.length() <= 0)
              {
                q = "[no match]";
              }

            String requestFrom = request.getParameter("requestFrom");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        
        <title>pachume | search: <%= q%> </title>
        
        <link rel="shortcut icon" href="/media/images/favicon.png" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/feedback.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/viewport.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/head.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/logo.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/default.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/stream.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/footer.css" />
        
        <link rel="stylesheet" type="text/css" href="/media/css/search.css" />
        
        <link rel="alternate" type="application/rss+xml" title="pachume.com" href="/feed/search/rss.jsp?q=<%=q%>" />
        
    </head>
    
    <body style="background-image:url(<%= thisUser.getBackgroundImageLocation()%>); background-repeat: <%= thisUser.isBackgroundImageRepeat()%>;  background-color: <%= thisUser.getBackgroundColor()%>;">
        
        <div class="viewport_outer_container_page">
            
            <div class="head_background_layer" ></div>
            
            <div class="viewport_inner_container_page">
                
                <%if (session.getAttribute("thisUser") == null)
              {%>
                <jsp:include flush="true" page="/include/head_inactive.jsp" >
                    <jsp:param name="requestFrom" value="explore_tags" />
                </jsp:include>
                <%} else
  {%>
                <jsp:include flush="true" page="/include/head_active.jsp" >
                    <jsp:param name="requestFrom" value="explore_tags" />
                </jsp:include>
                <%}%>
                
                <div class="viewport_outer_container_body">
                    
                    <div class="default_right_column_container">
                        <div class="default_right_column_top_head" >
                            <span class="default_right_column_head_text" >Explore Tags</span>
                        </div>
                        
                        <div class="default_right_column_body_container">
                            <form method="post" action="/search.jsp">
                                <p class="viewport_valid_element_container">
                                    <input type="text" class="default_right_column_input_text" name="q" value="<%= q%>" />
                                </p>
                                <p class="viewport_valid_element_container">
                                    <input type="submit" class="default_right_column_input_submit" name="requestFrom" value="explore" />
                                </p>
                            </form>
                        </div>
                        
                        <div class="default_right_column_middle_head">
                            <span class="default_right_column_head_text">Tag Cloud</span>
                        </div>
                        
                        <div class="default_right_column_body_container" >
                            <div class="search_rightcolumn_tag_list_container">
                                
                                <%

            DataBaseConnection taglist_db = new DataBaseConnection();

            taglist_db.connect();

            String taglist_sql = "SELECT DISTINCT tag, COUNT(*) as tag_count FROM tag GROUP BY tag ORDER BY tag_count DESC LIMIT 20;";

            ResultSet taglist_rs = taglist_db.execSQL(taglist_sql);

            String tagDefault = "stream_presence_body_tags_default";
            String tagActive = "stream_presence_body_tags_active";

            String stream_presence_body_tags = tagDefault;

            List tagCloudList = new ArrayList();
            tagBean tagCloud = null;

            while (taglist_rs.next())
              {
                tagCloud = new tagBean();
                tagCloud.setTagCount(taglist_rs.getInt("tag_count"));
                tagCloud.setTag(taglist_rs.getString("tag"));
                tagCloudList.add(tagCloud);
              }

            Collections.shuffle(tagCloudList);

            ListIterator tagCloudIterator = tagCloudList.listIterator();

            while (tagCloudIterator.hasNext())
              {

                tagCloud = (tagBean) tagCloudIterator.next();
                int tag_count = tagCloud.getTagCount();
                String tag_name = tagCloud.getTag();

                if (tag_name.equalsIgnoreCase(q))
                  {
                    stream_presence_body_tags = tagActive;
                  }
                                %>
                                
                                <span class="<%= stream_presence_body_tags%>" style="font-size:<%= 12 + tag_count%>px; display:inline;" ><a href="/search.jsp?requestFrom=explore&amp;q=<%=tag_name%>" ><%=tag_name%></a></span>
                                
                                <%
                stream_presence_body_tags = tagDefault;
              }
            taglist_db.close();
                                %>
                                
                            </div>
                            
                        </div>
                        
                        <div class="default_right_column_middle_head">
                            <span class="default_right_column_head_text">Search</span>
                        </div>
                        
                        <div class="default_right_column_body_container" >
                            <form method="post" action="/search.jsp">
                                <p class="viewport_valid_element_container">
                                    <input type="text" class="default_right_column_input_text" name="q" value="<%= q%>" />
                                </p>
                                <p class="viewport_valid_element_container">
                                    <input type="submit" class="default_right_column_input_search" name="requestFrom" value="pachume" />
                                </p>
                                <p class="viewport_valid_element_container">
                                    <input type="submit" class="default_right_column_input_search" name="requestFrom" value="people" />
                                </p>
                            </form>
                            
                        </div>
                        
                        
                        
                        <%if (request.getServletPath().contains("search"))
              {%>       
                        <jsp:include flush="true" page="/include/right_advertisement.jsp" />
                        
                        <%}%>
                        
                    </div>
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
                    <div class="default_left_column_container">
                        
                        <div class="default_left_column_head">search: <%= q%> </div>
                        
                        <jsp:include flush="true" page="/include/stream.jsp" >
                            <jsp:param name="thisUser_userId" value="<%= thisUser.getUserId() %>" />
                            <jsp:param name="requestFrom" value="<%= requestFrom %>" />
                            <jsp:param name="q" value="<%= q %>" />
                            <jsp:param name="p" value="<%= p %>" />
                        </jsp:include>
                        
                    </div> 
                    
                    <jsp:include flush="true" page="/include/footer.jsp" />
                    
                </div>
            </div>
        </div>
    <jsp:include flush="true" page="/include/feedback.jsp" /></body>
</html>
