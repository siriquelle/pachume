<%@page import="command.conversion.ConvertToSHA1, model.DataBaseConnection, java.sql.ResultSet" %>
<%            /*uncomment and run to convert all passwords to SHA1 encryption.
            DataBaseConnection db = new DataBaseConnection();
            db.connect();
            String userPassword = "";
            int userId = -1;
            String sql = "SELECT userId,userPassword FROM user ORDER BY userId";
            ResultSet rs = db.execSQL(sql);
            while (rs.next())
            {
            userPassword = rs.getString("userPassword");
            userPassword = ConvertToSHA1.run(userPassword);
            userId = rs.getInt("userId");
            sql = "UPDATE user SET userPassword = '" + userPassword + "' WHERE userId = " + userId;
            db.execUpdate(sql);
            }
            db.close();
             */
%>
