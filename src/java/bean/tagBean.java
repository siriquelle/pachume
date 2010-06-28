/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

/**
 *
 * @author Siriquelle
 */
public class tagBean {

    private int tagCount = 0;
    private String tag = "";

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setTagCount(int tagCount) {
        this.tagCount = tagCount;
    }

    public String getTag() {
        return tag;
    }

    public int getTagCount() {
        return tagCount;
    }
    
}
