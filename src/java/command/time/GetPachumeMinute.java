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
public class GetPachumeMinute {
    //<editor-fold defaultstate="collapsed" desc="getPachumeMinute">
    public static int run(int dateMinute) {
        Calendar currentMinute = Calendar.getInstance();

        int thisMinute = currentMinute.get(Calendar.MINUTE);
        int pachumeMinute = thisMinute - dateMinute;

        int minute = 0;

        if (pachumeMinute >= 0)
        {
            minute = pachumeMinute;
        } else if (pachumeMinute <= 0)
        {
            minute = (pachumeMinute + 60);
        }

        return minute;
    }// </editor-fold>
}
