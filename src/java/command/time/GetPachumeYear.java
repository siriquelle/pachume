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
public class GetPachumeYear {
    //<editor-fold defaultstate="collapsed" desc="getPachumeYear">
    public static int run(int dateYear) {
        Calendar currentYear = Calendar.getInstance();
        int year = 0;

        int thisYear = currentYear.get(Calendar.YEAR);
        int pachumeYear = thisYear - dateYear;

        year = pachumeYear;

        return year;
    }// </editor-fold>
}
