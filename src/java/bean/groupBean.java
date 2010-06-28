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
public class groupBean {

    private String groupId = "";
    private String groupName = "";
    private int userId = -1;
//    
//    
    private String groupDescription = "";
    private String avatarLocation = "";
    private String backgroundColor = "";
    private String backgroundImagelocation = "";
    private String backgroundImageRepeat = "";
//    
//    
    private String groupBean_sql;
    private DataBaseConnection groupBean_db;
    private ResultSet groupBean_rs;
//
    public groupBean(String groupName) {
        try {
            this.groupBean_sql = "SELECT * FROM groups WHERE groupName = '" + groupName + "';";
            this.groupBean_db = new DataBaseConnection();
            this.groupBean_db.connect();
            this.groupBean_rs = this.groupBean_db.execSQL(this.groupBean_sql);

            if (groupBean_rs.next()) {
                this.groupId = groupBean_rs.getString("groupId");
                this.groupName = groupBean_rs.getString("groupName");
                this.userId = groupBean_rs.getInt("userId");
//   
                this.groupDescription = groupBean_rs.getString("groupDescription");
                this.avatarLocation = groupBean_rs.getString("avatarLocation");
                this.backgroundColor = groupBean_rs.getString("backgroundColor");
                this.backgroundImagelocation = groupBean_rs.getString("backgroundImagelocation");
                this.backgroundImageRepeat = groupBean_rs.getString("backgroundImageRepeat");
            }

            this.groupBean_db.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public String getGroupId() {
        return this.groupId;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public int getUserId() {
        return this.userId;
    }

    public String getGroupDescription() {
        return this.groupDescription;
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
