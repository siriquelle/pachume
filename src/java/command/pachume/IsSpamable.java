/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.pachume;

import command.user.IsUsersPost;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author Siriquelle
 */
public class IsSpamable {
    //<editor-fold defaultstate="collapsed" desc="isSpamable">
    public static String run(int userId, int pachumeId, HttpSession session) {
        String spam = "";

        if (session.getAttribute("thisUser") != null)
          {
            if (!IsUsersPost.run(pachumeId, session))
              {
                User loggedUser = (User) session.getAttribute("thisUser");
                userId = loggedUser.getUserId();

                if (IsPachumeSpamable.run(userId, pachumeId))
                  {
                    spam = "<a class=\"spam_pachume\" href=\"/SpamPachume?pachumeId=" + pachumeId + "\" > [spam] </a>";
                  } else
                  {
                    spam = "<a href=\"#\" > [marked as spam] </a>";
                  }
              }
          }
        return spam;
    }// </editor-fold>
}
