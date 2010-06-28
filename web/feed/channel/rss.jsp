<?xml version="1.0" encoding="utf-8"?>
<%@ page import="bean.channelBean, model.DataBaseConnection, java.sql.ResultSet, command.user.*, command.pachume.*,  command.time.GetTime, java.util.Vector, java.util.Iterator, java.util.Enumeration, org.safehaus.uuid.UUIDGenerator, command.channel.GetChannelAvatarLocation ; " %>
<%@ page contentType="text/xml" %>

<%//feed for channel
            channelBean thisChannel = null;
            DataBaseConnection db = null;
            ResultSet rs = null;
            UUIDGenerator joe = null;

            int pachumeId = -1;
            int pachume_userId = -1;

            String sql = null;
            String channelName = null;
            String pachume = null;
            String pachume_screenName = null;
            String uuid = null;
%>

<%
            if (request.getParameter("channel") == null)
              {
                channelName = "pachume";
              } else
              {
                channelName = request.getParameter("channel");
                thisChannel = new channelBean(channelName);
              }

%>
<rss version="2.0">
    <channel>
        <title>viewing the <%=thisChannel.getChannelName()%> channel</title>
        <description><%=thisChannel.getChannelDescription()%></description>
        <link>http://pachume.com/channel/<%=thisChannel.getChannelName()%></link>
        <lastBuildDate><%=new java.util.Date().toString()%></lastBuildDate>
        <managingEditor>James@pachume.com (James Hogan)</managingEditor>
        <%
            sql = "Select * FROM pachume WHERE pachume.channelId IN ('" + thisChannel.getChannelId() + "') ORDER BY pachume.pachumeId DESC LIMIT 20";

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
