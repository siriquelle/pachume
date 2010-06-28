/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.time;
import java.util.Calendar;
/**
 *
 * @author Siriquelle
 */
public class GetPachumeHour {
   //<editor-fold defaultstate="collapsed" desc="getPachumeHour">
    public static int run(int dateHour) {
        Calendar currentHour = Calendar.getInstance();

        int thisHour = currentHour.get(Calendar.HOUR_OF_DAY);
        int pachumeHour = thisHour - dateHour;

        int hour = 0;

        if (pachumeHour >= 0)
        {
            hour = pachumeHour;

        } else if (pachumeHour == -12)
        {
            hour = (pachumeHour * -1) + (pachumeHour * 2);
        }

        return hour;
    }// </editor-fold>
}
