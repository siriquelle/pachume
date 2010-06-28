/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.pachume;

import command.conversion.MakeCompatable;
import java.io.UnsupportedEncodingException;
import model.DataBaseConnection;

/**
 *
 * @author Siriquelle
 */
public class InsertTags {
    //<editor-fold defaultstate="collapsed" desc="insertTags">
    public static boolean run(int pachumeId, String tags) {

        boolean areTagsInserted = false;

        if (!tags.equalsIgnoreCase("(split tags with spaces)") && !"".equals(tags) && tags != null && tags.length() != 0)
          {
            tags = MakeCompatable.run(tags);
            String[] tagsArray;

            String tag = "";

            try
              {
                DataBaseConnection user_table_connection;
                user_table_connection = new DataBaseConnection();
                user_table_connection.connect();

                tagsArray = tags.split(" ");

                for (int i = 0; i < tagsArray.length; i++)
                  {
                    tag = tagsArray[i];
                    String user_table_connection_sql = "INSERT INTO tag (pachumeId, tag) VALUES (" + pachumeId + ", '" + tag + "');";
                    user_table_connection.execUpdate(user_table_connection_sql);
                  }

                user_table_connection.close();
                areTagsInserted = true;
              } catch (Exception e)
              {
                System.out.print(e.toString());
                areTagsInserted = false;
              }
          }

        return areTagsInserted;
    }// </editor-fold>
}
