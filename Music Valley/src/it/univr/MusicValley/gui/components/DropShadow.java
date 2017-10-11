package it.univr.MusicValley.gui.components;

import java.awt.Color;
import org.jdesktop.swingx.border.DropShadowBorder;

public class DropShadow extends DropShadowBorder {

    public DropShadow() {
    	
        setShadowColor(Color.GRAY);
        setShowLeftShadow(true);
        setShowRightShadow(true);
        setShowBottomShadow(true);
        setShowTopShadow(true);
        setShadowSize(4);
        setShadowOpacity(0.1f);
        
    }
}