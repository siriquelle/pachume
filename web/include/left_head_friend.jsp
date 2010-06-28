<%@ page import="bean.userBean, model.User, command.user.IsUsersFriend" %>

<%
            String user = request.getParameter("user");
            userBean thisUser = new userBean(user);

%>

<% if (session.getAttribute("thisUser") != null) {%>


<%if (IsUsersFriend.run(thisUser.getUserId(), session)) {%>
<div class="profile_left_column_head_friend_container" >
    <a  href="/removeFriend?id=<%=thisUser.getUserId()%>">Remove <%=thisUser.getScreenName()%> as a contact</a> 
</div>
<%} else {

    User loggedUser = (User) session.getAttribute("thisUser");
    if (!loggedUser.getScreenName().equals(thisUser.getScreenName())) {%>
<div class="profile_left_column_head_friend_container" >
    <a  href="/addFriend?id=<%=thisUser.getUserId()%>">Add <%=thisUser.getScreenName()%> as a contact</a>
</div>  
<%}%>

<%}%>

<%}%>
