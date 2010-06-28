<%@page import="command.user.*, command.channel.*, bean.channelBean"%>
<%@ page import="java.util.ListIterator, java.util.Collections, java.util.Vector, java.util.List, java.util.ArrayList" %>
<%
            channelBean thisChannel = new channelBean(request.getParameter("channelName"));
%>
<div class="default_right_column_top_head" >
    <span class="default_right_column_head_text" ><%=thisChannel.getChannelName() %></span>
</div>

<div class="default_right_column_body_container" >
    <div class="default_right_column_input_text" >
        <%= thisChannel.getChannelDescription()%>
    </div>
</div>


<div class="default_right_column_middle_head">
    <span class="default_right_column_head_text">Explore tags</span>
</div>

<div class="default_right_column_body_container">
    <form method="post" action="/search.jsp">
        <input type="text" class="default_right_column_input_text" name="q" value="" />
        <input type="submit" class="default_right_column_input_submit" name="requestFrom" value="explore" />
    </form>
</div>

<div class="default_right_column_middle_head" >
    <span class="default_right_column_head_text">Members</span>
</div>

<div class="default_right_column_body_container" >
    
    <%
            Vector membersList = GetChannelMembers.run(thisChannel.getChannelId());
            ListIterator shuffleList = membersList.listIterator();
            List arrayList = new ArrayList();

            while (shuffleList.hasNext())
            {
                arrayList.add(shuffleList.next());
            }
            Collections.shuffle(arrayList);
            ListIterator list = arrayList.listIterator();

            int userId = -1;
            String screenName = "";
            String contactImageLocation = "";

            if (list.hasNext())
            {
                while (list.hasNext())
                {
                    userId = Integer.parseInt(list.next().toString());
                    screenName = GetScreenName.run(userId).trim();
                    contactImageLocation = GetContactImageLocation.run(userId);
    %>
    
    <div class="body_right_column_contacts_image_container">
        <a href="/profile/<%=screenName%>" title="<%=screenName%>">
            <img src="<%=contactImageLocation%>" alt="<%=screenName%>" height="27px" width="27px" />  
        </a>  
    </div>
    <%
                }
            }
    %>
    
</div>

<div class="default_right_column_middle_head">
    <span class="default_right_column_head_text">Search</span>
</div>

<div class="default_right_column_body_container" >
    <form method="post" action="/search.jsp">
        <input type="text" class="default_right_column_input_text" name="q" value="" />
        
        <input type="submit" class="default_right_column_input_search" name="requestFrom" value="pachume" />
        
        <input type="submit" class="default_right_column_input_search" name="requestFrom" value="people" />
    </form>
</div>

<%
            if (session.getAttribute("thisUser") != null)
            {
                if (IsUserChannelsAuthor.run(thisChannel.getChannelId(), session))
                {
%>
<div class="default_right_column_middle_head">
    <span class="default_right_column_head_text">Manage</span>
</div>

<div class="default_right_column_body_container" >
    <span class="default_right_column_input_submit" >
        <a href="/DeleteChannel?channelId=<%=thisChannel.getChannelId() %>">delete <%=thisChannel.getChannelName() %></a>
    </span>
</div>
<%
                }
            }
%>

