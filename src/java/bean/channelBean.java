/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.sql.ResultSet;
import model.DataBaseConnection;

/**
 *
 * @author Siriquelle
 */
public class channelBean {

    private int channelId = -1;
    private String channelName = "";
    private int userId = -1;
//    
//    
    private String channelDescription = "";
    private String avatarLocation = "";
    private String backgroundColor = "";
    private String backgroundImagelocation = "";
    private String backgroundImageRepeat = "";
//    
//    
    private String channelBean_sql;
    private DataBaseConnection channelBean_db;
    private ResultSet channelBean_rs;
//
    public channelBean(String channelName) {
        try {
            this.channelBean_sql = "SELECT * FROM channel WHERE channelName = '" + channelName + "';";
            this.channelBean_db = new DataBaseConnection();
            this.channelBean_db.connect();
            this.channelBean_rs = this.channelBean_db.execSQL(this.channelBean_sql);

            if (channelBean_rs.next()) {
                this.channelId = channelBean_rs.getInt("channelId");
                this.channelName = channelBean_rs.getString("channelName");
                this.userId = channelBean_rs.getInt("userId");
//   
                this.channelDescription = channelBean_rs.getString("channelDescription");
                this.avatarLocation = channelBean_rs.getString("avatarLocation");
                this.backgroundColor = channelBean_rs.getString("backgroundColor");
                this.backgroundImagelocation = channelBean_rs.getString("backgroundImagelocation");
                this.backgroundImageRepeat = channelBean_rs.getString("backgroundImageRepeat");
            }

            this.channelBean_db.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public int getChannelId() {
        return this.channelId;
    }

    public String getChannelName() {
        return this.channelName;
    }

    public int getUserId() {
        return this.userId;
    }

    public String getChannelDescription() {
        return this.channelDescription;
    }

    public String getAvatarLocation() {
        return this.avatarLocation;
    }

    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    public String getBackgroundImagelocation() {
        return this.backgroundImagelocation;
    }

    public String getBackgroundImageRepeat() {
        return this.backgroundImageRepeat;
    }
}
