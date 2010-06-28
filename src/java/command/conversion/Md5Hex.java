/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.conversion;

import java.security.MessageDigest;

/**
 *
 * @author Siriquelle
 */
public class Md5Hex {
    //<editor-fold defaultstate="collapsed" desc="md5Hex">
    public static String run(String message) {
        try
          {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return hex(md.digest(message.getBytes("CP1252")));
          } catch (Exception e)
          {
            System.out.print(e);
          }
        return null;
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="hex">
    public static String hex(byte[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i)
          {
            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
          }
        return sb.toString();
    }//</editor-fold>
}
