<%@ page import="command.user.*, command.pachume.*,  model.DataBaseConnection, java.sql.ResultSet, java.util.Vector, java.util.Iterator, command.comment.*, command.verification.*; "%>

<%
            Vector hotTopic_list = new Vector();
            String hotTopic_sql = "SELECT COUNT(commentId) AS commentCount, pachumeId FROM comment GROUP BY pachumeId ORDER BY commentCount DESC LIMIT 5";

            DataBaseConnection hotTopic_db = new DataBaseConnection();
            hotTopic_db.connect();

            ResultSet hotTopic_rs = hotTopic_db.execSQL(hotTopic_sql);

            while (hotTopic_rs.next())
              {
                hotTopic_list.add(hotTopic_rs.getInt("pachumeId"));
              }

            Iterator iter = hotTopic_list.listIterator();

            String pachume = "";
            int pachumeId = -1;
            int pachume_userId = -1;

            String pachume_screenName = "";

            while (iter.hasNext())
              {
                hotTopic_sql = "SELECT * FROM pachume WHERE pachumeId = " + iter.next() + " AND pachume.groupId IN ('00000000-0000-0000-0000-000000000000')";
                hotTopic_rs = hotTopic_db.execSQL(hotTopic_sql);

                if (hotTopic_rs.next())
                  {
                    pachume = hotTopic_rs.getString("pachume.pachume");
                    pachumeId = hotTopic_rs.getInt("pachume.pachumeId");
                    pachume_userId = hotTopic_rs.getInt("pachume.userId");
                    pachume_screenName = GetScreenName.run(pachume_userId).trim();
%>
<div class="hottopic_presence_body_container" >
    
    <div class="hottopic_presence_body_pachume">
        <a href="/comment/<%=pachumeId%>" class="inlink" ><%= pachume%> </a>
    </div>
    
    <div class="stream_presence_body_meta">
        
        <%= pachume_screenName%>  
        <a href="/comment/<%=pachumeId%>"  >
            <%if (CountComments.run(pachumeId) > 0)
          {%>
            [<span class="stream_presence_body_meta_comment_count" ><%= CountComments.run(pachumeId)%></span>]
            <%}%>
            
            add comment 
            
        </a>
        
        <%= IsDeleatable.run(pachumeId, session)%>
        
        <%= IsSpamable.run(5, pachumeId, session)%>
        
    </div>
</div>
<%
                  }
              }
            hotTopic_db.close();
%>                       
