/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package template;

/**
 *
 * @author Siriquelle
 */
public interface Email {

    String lb1 = "<br />";
    String lb2 = "<br /><br />";
    String style = "<style>.style{font-family:'Courier New'; font-size:12px;width:480px;}</style>";

    /**
     * Gets the subject of the eMail
     * @return
     */
    String getSubject();

    /**
     * Gets the body of the eMail
     * @return
     */
    String getBody();
}
