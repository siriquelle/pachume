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
public class GetPachumeSecond {
    //<editor-fold defaultstate="collapsed" desc="getPachumeSecond">
    public static int run(int dateSecond) {
        Calendar currentSecond = Calendar.getInstance();

        int thisSecond = currentSecond.get(Calendar.SECOND);
        int pachumeSecond = thisSecond - dateSecond;

        int second = 0;

        if (pachumeSecond >= 0)
        {
            second = pachumeSecond;
        } else if (pachumeSecond <= 0)
        {
            second = (pachumeSecond + 59);
        }

        return second;
    }// </editor-fold>
}
