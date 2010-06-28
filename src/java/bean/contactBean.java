/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

/**
 *
 * @author Siriquelle
 */
public class contactBean {

    private String name;
    private String link;
    private String image;

    public contactBean(String name, String link, String image) {
        this.name = name;
        this.link = link;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }
    
    
    
}
