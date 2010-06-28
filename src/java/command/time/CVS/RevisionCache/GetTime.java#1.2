/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.time;

import java.sql.ResultSet;
import model.DataBaseConnection;
/**
 *
 * @author Siriquelle
 */
public class GetTime {
    //<editor-fold defaultstate="collapsed" desc="GetTime">
    public static String run(String table, String column, int columnId) {

        String time = "";
        int blood = 0;

        int dateSecond = -1;
        int dateMinute = -1;
        int dateHour = -1;
        int dateDay = -1;
        int dateMonth = -1;
        int dateYear = -1;
        int dateYearDay = -1;

        /* Get the current date*/

        try
        {
            DataBaseConnection timeValues_db = new DataBaseConnection();
            timeValues_db.connect();
            String timeValues_sql = "SELECT * FROM " + table + " WHERE " + column + " = '" + columnId + "';";
            ResultSet timeValues_rs = timeValues_db.execSQL(timeValues_sql);

            for (int i = 0; timeValues_rs.next(); i++)
            {
                dateSecond = timeValues_rs.getInt("dateSecond");
                dateMinute = timeValues_rs.getInt("dateMinute");
                dateHour = timeValues_rs.getInt("dateHour");
                dateDay = timeValues_rs.getInt("dateDay");
                dateMonth = timeValues_rs.getInt("dateMonth");
                dateYearDay = timeValues_rs.getInt("dateYearDay");
                dateYear = timeValues_rs.getInt("dateYear");
            }
            timeValues_db.close();
        } catch (Exception e)
        {
            time = e.toString();
        }


        if (GetPachumeYear.run(dateYear) == 1)
        {
            time = "a year ago";
            if (GetPachumeYearDay.run(dateYearDay) == 0)
            {
                time = "a year ago";
            }
            if (GetPachumeYearDay.run(dateYearDay) == 1)
            {
                time = "1 year, 1 day ago";
            }
            if (GetPachumeYearDay.run(dateYearDay) > 1)
            {
                time = "1 year, " + GetPachumeYearDay.run(dateYearDay) + " days ago";
            }
        }


        if (GetPachumeYearDay.run(dateYearDay) == 1)
        {
            if (GetPachumeHour.run(dateHour) == 0)
            {
                time = "1 day ago";
            }
            if (GetPachumeHour.run(dateHour) == 1)
            {
                time = "1 day, 1 hour ago";
            }
            if (GetPachumeHour.run(dateHour) > 1)
            {
                time = "1 day, " + GetPachumeHour.run(dateHour) + " hours ago";
            }
        }

        if (GetPachumeYearDay.run(dateYearDay) > 1 && GetPachumeYearDay.run(dateYearDay) < 28)
        {
            if (GetPachumeHour.run(dateHour) == 0)
            {
                time = GetPachumeYearDay.run(dateYearDay) + " days ago";
            }
            if (GetPachumeHour.run(dateHour) == 1)
            {
                time = GetPachumeYearDay.run(dateYearDay) + " days, 1 hour ago";
            }
            if (GetPachumeHour.run(dateHour) > 1)
            {
                time = GetPachumeYearDay.run(dateYearDay) + " days, " + GetPachumeHour.run(dateHour) + " hours ago";
            }
        }

        if (GetPachumeYearDay.run(dateYearDay) == 0)
        {
            if (GetPachumeHour.run(dateHour) == 0)
            {
                if (GetPachumeMinute.run(dateMinute) == 0)
                {
                    if (GetPachumeSecond.run(dateSecond) == 0)
                    {
                        time = "just now";
                    }
                    if (GetPachumeSecond.run(dateSecond) == 1)
                    {
                        time = "1 second ago";
                    }
                    if (GetPachumeSecond.run(dateSecond) > 1)
                    {
                        time = GetPachumeSecond.run(dateSecond) + " seconds ago";
                    }
                }

                if (GetPachumeMinute.run(dateMinute) == 1)
                {
                    if (GetPachumeSecond.run(dateSecond) == 0)
                    {
                        time = "1 minute ago";
                    }
                    if (GetPachumeSecond.run(dateSecond) == 1)
                    {
                        time = "1 minute, 1 second ago";
                    }
                    if (GetPachumeSecond.run(dateSecond) > 1)
                    {
                        time = "1 minute, " + GetPachumeSecond.run(dateSecond) + " seconds ago";
                    }
                }

                if (GetPachumeMinute.run(dateMinute) > 1)
                {
                    if (GetPachumeSecond.run(dateSecond) == 0)
                    {
                        time = GetPachumeMinute.run(dateMinute) + " minutes ago";
                    }
                    if (GetPachumeSecond.run(dateSecond) == 1)
                    {
                        time = GetPachumeMinute.run(dateMinute) + " minutes, 1 second ago";
                    }
                    if (GetPachumeSecond.run(dateSecond) > 1)
                    {
                        time = GetPachumeMinute.run(dateMinute) + " minutes, " + GetPachumeSecond.run(dateSecond) + " seconds ago";
                    }
                }
            }

            if (GetPachumeHour.run(dateHour) == 1)
            {
                if (GetPachumeMinute.run(dateMinute) == 0)
                {
                    time = "1 hour ago";
                }
                if (GetPachumeMinute.run(dateMinute) == 1)
                {
                    time = "1 hour, 1 minute ago";
                }
                if (GetPachumeMinute.run(dateMinute) > 1)
                {
                    time = "1 hour, " + GetPachumeMinute.run(dateMinute) + " minutes ago";
                }
            }

            if (GetPachumeHour.run(dateHour) > 1)
            {
                if (GetPachumeMinute.run(dateMinute) == 0)
                {
                    time = GetPachumeHour.run(dateHour) + " hours ago";
                }
                if (GetPachumeMinute.run(dateMinute) == 1)
                {
                    time = GetPachumeHour.run(dateHour) + " hours, 1 minute ago";
                }
                if (GetPachumeMinute.run(dateMinute) > 1)
                {
                    time = GetPachumeHour.run(dateHour) + " hours, " + GetPachumeMinute.run(dateMinute) + " minutes ago";
                }
            }
        }
        return time;
    }// </editor-fold>
}
