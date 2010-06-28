/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Siriquelle
 */
public class streamBean {

    private int thisUser_userId = 0;
    private String requestFrom = "";
    private String q = "";
    private int p = 0;
    private int chanelId = 0;
    private String groupId = "";

    public streamBean(HttpServletRequest request) {
        try {
            this.thisUser_userId = Integer.parseInt(request.getParameter("thisUser_userId"));
        } catch (Exception e) {
            System.out.print(e);
            this.thisUser_userId = -1;
        }

        try {
            this.requestFrom = request.getParameter("requestFrom");
        } catch (Exception e) {
            System.out.print(e);
            this.requestFrom = "";
        }

        try {
            this.q = request.getParameter("q");
        } catch (Exception e) {
            System.out.print(e);
            this.q = "";
        }

        try {
            this.chanelId = Integer.parseInt(request.getParameter("channelId"));
        } catch (Exception e) {
            System.out.print(e);
            this.chanelId = -1;
        }

        try {
            this.groupId = request.getParameter("groupId");
        } catch (Exception e) {
            System.out.print(e);
            this.groupId = "";
        }

        try {
            String pString = request.getParameter("p");
            if (pString == null) {
                this.p = 0;
            } else {
                this.p = Integer.parseInt(pString);
                if (this.p < 0) {
                    this.p = 0;
                }
            }
        } catch (Exception e) {
            System.out.print(e);
            this.p = 0;
        }

    }

    public int getThisUser_userId() {
        return this.thisUser_userId;
    }

    public String getQ() {
        return this.q;
    }

    public String getRequestFrom() {
        return this.requestFrom;
    }

    public int getP() {
        return p;
    }

    public int getChanelId() {
        return chanelId;
    }

    public String getGroupId() {
        return groupId;
    }
}
