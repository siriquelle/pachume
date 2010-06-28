<?xml version="1.0" encoding="utf-8"?>
<%@ page import="command.user.*, command.pachume.*, model.DataBaseConnection, java.sql.ResultSet, command.time.GetTime, org.safehaus.uuid.UUIDGenerator; " %>
<%@ page contentType="text/xml" %>

<%//all feed for all
            DataBaseConnection db = null;
            ResultSet rs = null;
            UUIDGenerator joe = null;

            int pachumeId = -1;
            int pachume_userId = -1;

            String sql = null;
            String pachume = null;
            String pachume_screenName = null;
            String uuid = null;

            String q = "";

            if (request.getParameter("q") != null)
              {
                q = request.getParameter("q");
              }
%>

<rss version="2.0">
    <channel>
        <title>viewing all pachumes</title>
        <description>pachume's microbloggers stream</description>
        <link>http://pachume.com/explore.jsp</link>
        <lastBuildDate><%=new java.util.Date().toString()%></lastBuildDate>
        <managingEditor>James@pachume.com (James Hogan)</managingEditor>
        
        <%

            sql = "Select * FROM pachume WHERE pachume.groupId IN ('00000000-0000-0000-0000-000000000000') AND pachume.pachume LIKE '%" + q + "%' OR pachume.tags LIKE '%" + q + "%' ORDER BY pachume.pachumeId DESC LIMIT 20";

            db = new DataBaseConnection();
            db.connect();
            rs = db.execSQL(sql);

            while (rs.next())
              {
                pachume = rs.getString("pachume.pachume");
                pachumeId = rs.getInt("pachume.pachumeId");
                pachume_userId = rs.getInt("pachume.userId");
                pachume_screenName = GetScreenName.run(pachume_userId).trim();

                joe = UUIDGenerator.getInstance();
                uuid = joe.generateRandomBasedUUID().toString();
        %>                       
        
        <item>
            <title><![CDATA[<%= pachume%>]]></title>
            <link>http://pachume.com/comment/<%=pachumeId%></link>
            <guid isPermaLink="false">urn:uuid:<%=uuid%></guid>
            <pubDate><%= rs.getTimestamp("dateTime")%></pubDate>
            <description>By <%= pachume_screenName%> <%= GetPacumeLocation.run(pachumeId)%> | <%= GetTime.run("pachume", "pachumeId", pachumeId)%></description>
            
            
            <author><%=GetScreenName.run(pachume_userId)%></author>
            <image><%=GetAvatarLocation.run(pachume_userId)%></image>
            
        </item>
        
        <%
              }
            db.close();
        %>
        
    </channel>
</rss>
