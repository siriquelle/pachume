<%@ page import="model.User" %>

<%
            User thisUser = new User();
            thisUser = (User) session.getAttribute("thisUser");

            String requestFrom = "requestFrom";

            String head_menu_item_link_active = "head_menu_item_link_active";
            String head_menu_item_link = "head_menu_item_link";

            String userPage = head_menu_item_link;
            String settings = head_menu_item_link;
            String signout = head_menu_item_link;

            String home = head_menu_item_link;
            String explore = head_menu_item_link;
            String people = head_menu_item_link;
            String channels = head_menu_item_link;
            String channel = head_menu_item_link;
            String groups = head_menu_item_link;



            if (request.getParameter(requestFrom) != null)
            {
                requestFrom = request.getParameter(requestFrom);

                if (requestFrom.equals("userPage"))
                {
                    userPage = head_menu_item_link_active;
                }

                if (requestFrom.equals("settings"))
                {
                    settings = head_menu_item_link_active;
                }

                if (requestFrom.equals("home"))
                {
                    home = head_menu_item_link_active;
                }

                if (requestFrom.equals("explore"))
                {
                    explore = head_menu_item_link_active;
                }

                if (requestFrom.equals("people"))
                {
                    people = head_menu_item_link_active;
                }

                if (requestFrom.equals("channels"))
                {
                    channels = head_menu_item_link_active;
                }

                if (requestFrom.equals("groups"))
                {
                    groups = head_menu_item_link_active;
                }
            }
%>
<div class="head_menu_container">
    
    <div class="<%= userPage %>"><a href="/profile/<%= thisUser.getScreenName().trim() %>"><%= thisUser.getScreenName()%></a></div>
    
    <%if (requestFrom.equals("channel"))
            {
                String channelName = request.getParameter("channelName");
    %>
    <div class="<%= settings %>"><a href="/channelSettings.jsp?channelName=<%=channelName.trim() %>">settings</a></div>
    <%} else if (requestFrom.equals("group"))
{
    String groupName = request.getParameter("groupName");
    %>
    <div class="<%= settings %>"><a href="/groupSettings.jsp?groupName=<%=groupName%>">settings</a></div>
    <%} else
{%>
    <div class="<%= settings %>"><a href="/settings.jsp">settings</a></div>
<%}%>
    
    <div class="<%= signout %>"><a href="/Logout">signout</a></div>
    
  <div class="head_menu_item_devider">|</div>
    
    <div class="<%= explore %>"><a href="/explore.jsp">explore</a></div>
  <div class="<%= people %>"><a href="/people.jsp">people</a></div>
  <div class="<%= channels %>"><a href="/channels.jsp">channels</a></div>
  <div class="<%= groups %>"><a href="/groups.jsp">groups</a></div>
    
</div>

<jsp:include flush="true" page="/include/head_logo.jsp" />