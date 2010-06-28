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
public class GetPachumeYearDay {
    //<editor-fold defaultstate="collapsed" desc="getPachumeYearDay">
    public static int run(int dateYearDay) {
        Calendar currentYearDay = Calendar.getInstance();
        int yearDay = 0;

        int thisYearDay = currentYearDay.get(Calendar.DAY_OF_YEAR);
        int pachumeYearDay = thisYearDay - dateYearDay;

        yearDay = pachumeYearDay;

        return yearDay;
    }// </editor-fold>
}
