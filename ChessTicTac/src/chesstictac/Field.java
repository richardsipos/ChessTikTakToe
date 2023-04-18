package chesstictac;

import java.awt.Color;

public class Field {

    private String icon;
    private Color color;

    /**
     * Az osztály konstruktora.
     * @param iconom 
     */
    public Field(String iconom) {
        icon=iconom;
        color=null;
    }
    /**
     * Visszadja a cella színét
     * @return cella színe
     */
    public Color getColor() {
        return color;
    }
    /**
     * Beállítja a cella színét.
     * @param color szín
     */
    public void setColor(Color color) {
        this.color = color;
    }
    /**
     * Visszadja a cellán lévő icont. Ha nincs üres stringet add vissza
     * @return cella iconja
     */
    public String getIcon(){
        return this.icon;
    }
    /**
     * Beállítja a cella iconját.
     * @param str Egy icon.
     */
    public void setIcon(String str){
        this.icon = str;
    }
    
    


}