/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import command.group.GetUsersGroups;
import java.sql.ResultSet;
import model.DataBaseConnection;

import java .util.Vector;
import java .util.Iterator;
/**
 *
 * @author Siriquelle
 */
public class pachumeBean {

    private String pachume;
    private int pachumeId;

    public pachumeBean(int userId) {

        try {
            Vector usersGroups = GetUsersGroups.run(userId);
            String groupList = "";
            Iterator groupIterator = usersGroups.iterator();
            while (groupIterator.hasNext()) {
                groupList += ("'" + groupIterator.next().toString() + "',");
            }

            DataBaseConnection pachumeBean_db = new DataBaseConnection();
            pachumeBean_db.connect();
            String pachumeBean_sql = "SELECT pachume, pachumeId FROM pachume WHERE userId = " + userId + " AND pachume.groupId NOT IN (" + groupList + "'-1000')  ORDER BY pachumeId DESC LIMIT 1;";
            ResultSet pachumeBean_rs = pachumeBean_db.execSQL(pachumeBean_sql);

            if (pachumeBean_rs.next()) {
                pachume = pachumeBean_rs.getString("pachume");
                pachumeId = pachumeBean_rs.getInt("pachumeId");
            }
            pachumeBean_db.close();
        } catch (Exception e) {
            pachume = e.getMessage();
            pachumeId = -1;
        }
    }

    public String getPachume() {
        return pachume;
    }

    public int getPachumeId() {
        return pachumeId;
    }
}
