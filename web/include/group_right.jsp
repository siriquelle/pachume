<%@page import="command.user.*, command.group.*, bean.groupBean"%>
<%@ page import="java.util.ListIterator, java.util.Collections, java.util.Vector, java.util.List, java.util.ArrayList" %>

<%

            groupBean thisGroup = new groupBean(request.getParameter("groupName"));
            
            if (thisGroup.getUserId() <= 0) {
                response.sendRedirect("/");
            }
            if (!IsUserJoinedGroup.run(thisGroup.getGroupId(), session)) {
                response.sendRedirect("/?message=You are notF authorized to view this group");
            }
%>
<div class="default_right_column_top_head" >
    <span class="default_right_column_head_text" ><%=thisGroup.getGroupName() %></span>
</div>

<div class="default_right_column_body_container" >
    <div class="default_right_column_input_text" >
        <%= thisGroup.getGroupDescription()%>
    </div>
</div>
<%
            if (session.getAttribute("thisUser") != null)
            {
                if (IsUserGroupsAuthor.run(thisGroup.getGroupId(), session))
                {
%>
<div class="default_right_column_middle_head">
    <span class="default_right_column_head_text">Invite Members</span>
</div>

<div class="default_right_column_body_container">
    <form method="post" action="/inviteToGroup">
        <input type="text" class="default_right_column_input_text" name="screenName" value="screen name" />
        <input type="hidden" class="default_right_column_input_text" name="groupId" value="<%=thisGroup.getGroupId().trim()%>" />
        <input type="submit" class="default_right_column_input_search" name="requestFrom" value="send" />
    </form>
</div>

<%
                }
            }
%>
<div class="default_right_column_middle_head" >
    <span class="default_right_column_head_text">Members</span>
</div>

<div class="default_right_column_body_container" >
    
    <% Vector membersList = GetGroupMembers.run(thisGroup.getGroupId());
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

    %>
    
<% while (list.hasNext())
            {
                userId = Integer.parseInt(list.next().toString());
                screenName = GetScreenName.run(userId).trim();
                contactImageLocation = GetContactImageLocation.run(userId);
    %>
    
    <div class="body_right_column_contacts_image_container">
        
        <a href="/profile/<%=screenName%>" title="<%=screenName%>">
            
            <img src="<%=contactImageLocation%>" alt="<%=screenName%>" height="28px" width="28px" />        </a>    </div>
    <%}%>
    
</div>

<%
            if (session.getAttribute("thisUser") != null)
            {
                if (IsUserGroupsAuthor.run(thisGroup.getGroupId(), session))
                {
%>
<div class="default_right_column_middle_head">
    <span class="default_right_column_head_text">Manage</span>
</div>
<div class="default_right_column_body_container" >
    <span class="default_right_column_input_submit" ><a href="/DeleteGroup?groupName=<%=thisGroup.getGroupName() %>">delete <%=thisGroup.getGroupName() %></a> </span>
</div>

<%
                }
            }
%>
