<%@ page import="command.user.*, model.*, java.sql.ResultSet, command.time.GetTime,  command.comment.*, java.util.Vector, java.util.Iterator, java.util.Enumeration, command.comment.*; " %>

<%
        String sql1 = "";

        String requestFrom = request.getParameter("requestFrom");

        int thisUser_userId = Integer.parseInt(request.getParameter("thisUser_userId"));

        if (requestFrom.equalsIgnoreCase("comment")) {
            int pachumeId = Integer.parseInt(request.getParameter("pachumeId"));
            sql1 = "SELECT * FROM comment WHERE pachumeId =" + pachumeId + " ORDER BY commentId ASC;";
        }

        DataBaseConnection presence_container_first_db = new DataBaseConnection();
        presence_container_first_db.connect();
        
        ResultSet presence_container_first_rs = presence_container_first_db.execSQL(sql1);

        String comment = "";
        int commentId = -1;
        int pachume_userId = -1;

        String pachume_screenName = "";
        String pachume_pachumeImageLocation = "";

        int presanceCount = 0;

        if (presence_container_first_rs.first()) {
            presence_container_first_rs.previous();
            while (presence_container_first_rs.next()) {
                presanceCount++;

                comment = presence_container_first_rs.getString("comment.comment");
                commentId = presence_container_first_rs.getInt("comment.commentId");
                pachume_userId = presence_container_first_rs.getInt("comment.userId");

                pachume_screenName = GetScreenName.run(pachume_userId).trim();
                pachume_pachumeImageLocation = GetPachumeImageLocation.run(pachume_userId).trim();

%>                       
<div class="stream_presence_container_default" >

    <div class="stream_presence_image_container">
        <a href="/profile/<%=pachume_screenName%>" title="<%=pachume_screenName%>">
    <img class="stream_presence_image" alt="<%= pachume_screenName%>" src="<%= pachume_pachumeImageLocation%>" width="40px" height="40px" />        </a>    </div>

    <div class="stream_presence_body_container" >

        <div class="stream_presence_body_pachume">
            <%= comment%>
        </div>

        <div class="stream_presence_body_meta">

            By <%= pachume_screenName%>
            <%= GetTime.run("comment", "commentId", commentId)%>
            <%= IsCommentDeletable.run(commentId, session)%>
            <%= IsCommentSpamable.run(thisUser_userId, commentId)%>

        </div>
    </div>
</div>
<%}
} else {%>

<div class="stream_presence_container_default" >

    <div class="stream_presence_image_container">
        <a href="/profile/Siriquelle" title="Siriquelle">
    <img class="stream_presence_image" alt="Siriquelle" src="<%=GetAvatarLocation.run(1)%>" width="40px" height="40px" />        </a>    </div>

    <div class="stream_presence_body_container" >

        <div class="stream_presence_body_pachume">
            Nothin' there just yet captain!!
        </div>

        <div class="stream_presence_body_meta">

            By Siriquelle, just now, from aboard the Black Peril,

        </div>

    </div>
</div>

<%}
        presence_container_first_db.close();
%>
