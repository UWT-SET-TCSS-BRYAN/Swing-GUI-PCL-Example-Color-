package view;

import static model.PropertyChangeEnabledMutableColor.PROPERTY_COLOR;
import static model.PropertyChangeEnabledMutableColor.PROPERTY_RED;
import static model.PropertyChangeEnabledMutableColor.PROPERTY_GREEN;
import static model.PropertyChangeEnabledMutableColor.PROPERTY_BLUE;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

/**
 * A demo of Observable/Observer. 
 * 
 * @author Charles Bryan
 * @version Autumn 2015
 */
public class ColorPanel extends JPanel implements PropertyChangeListener {

    /**  
     * A generated serial version UID for object Serialization. 
     * http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
     */
    private static final long serialVersionUID = 8385732728740430466L;

    /** The background color. */
//    private Color myColor;
    
    /**
     * Create a color panel with the supplied color.
     * @param theColor the color for the background
     */
    public ColorPanel(final Color theColor) {
        super();
        setBackground(theColor);
//        myColor = theColor;
    }
    
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        Color color = getBackground();
        if (PROPERTY_COLOR.equals(theEvent.getPropertyName())) {
            color = (Color) theEvent.getNewValue();
        } else if (PROPERTY_RED.equals(theEvent.getPropertyName())) {
            final int r = (Integer) theEvent.getNewValue();
            color = new Color(r, color.getGreen(), color.getBlue());
        } else if (PROPERTY_GREEN.equals(theEvent.getPropertyName())) {
            final int g = (Integer) theEvent.getNewValue();
            color = new Color(color.getRed(), g, color.getBlue());
        } else if (PROPERTY_BLUE.equals(theEvent.getPropertyName())) {
            final int b = (Integer) theEvent.getNewValue();
            color = new Color(color.getRed(), color.getGreen(), b);
        }  
        setBackground(color);
    }
}
