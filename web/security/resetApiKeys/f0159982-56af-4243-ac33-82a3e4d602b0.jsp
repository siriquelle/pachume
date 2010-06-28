<%@page import="model.DataBaseConnection, java.sql.ResultSet" %>
<%
            /* uncomment to reset all api keys
            DataBaseConnection db = new DataBaseConnection();
            db.connect();
            
            int userId = -1;
            String sql = "SELECT userId FROM user ORDER BY userId";
            ResultSet rs = db.execSQL(sql);
            
            while (rs.next())
            {
            org.safehaus.uuid.UUIDGenerator joe = org.safehaus.uuid.UUIDGenerator.getInstance();
            String apiKey = joe.generateRandomBasedUUID().toString();
            userId = rs.getInt("userId");
            sql = "UPDATE user SET apiKey = '" + apiKey + "' WHERE userId = " + userId;
            db.execUpdate(sql);
            }
            
            db.close();
             */

%>