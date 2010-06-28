/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.pachume;

import command.user.IsUsersPost;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Siriquelle
 */
public class IsDeleatable {
    //<editor-fold defaultstate="collapsed" desc="isDeleatable">
    public static String run(int pachumeId, HttpSession session) {
        String delete = "";

        if (IsUsersPost.run(pachumeId, session))
          {
            delete = "<a class=\"delete_pachume\" href=\"/DeletePachume?pachumeId=" + pachumeId + "\" > [delete] </a>";
          }

        return delete;
    }// </editor-fold>
}
