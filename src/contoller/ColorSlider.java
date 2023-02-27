 
package contoller;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import model.MutableColor;
import model.ObservableColor;
import view.ColorPanel;

/**
 *  A controller for ObservableColor.
 * 
 *  @author Charles Bryan
 *  @version Autumn 2015 
 */
public class ColorSlider extends JPanel {
    
    /**  
     * A generated serial version UID for object Serialization. 
     * http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
     */
    private static final long serialVersionUID = 8452917670991316606L;
    
    /** The number of rows. */
    private static final int ROW = 3;
    
    /** The number of columns. */
    private static final int COL = 2;
    
    /** Amount in Pixels for the Horizontal margin. */
    private static final int HORIZONATAL_MARGIN = 20; 
    
    /** Amount in Pixels for the Vertical margin. */
    private static final int VERTICALL_MARGIN = 10; 
    
    /** The default size of the color panel. */
    private static final int WINDOW_SIZE = 200;
    
    /** The Color object this class controls. */
    private final MutableColor myColor;
    
    /**
     * Constructs a ColorSlider.
     * 
     * @param theColor the color object this class controls
     */
    public ColorSlider(final ObservableColor theColor) {
        super(new GridLayout(ROW, COL, 0, HORIZONATAL_MARGIN));
        setBorder(BorderFactory.createEmptyBorder(VERTICALL_MARGIN, 
                                                  HORIZONATAL_MARGIN, 
                                                  VERTICALL_MARGIN, 
                                                  VERTICALL_MARGIN));
        myColor = theColor;
        layoutComponents();
    }
    
    /**
     * Lay out the components.
     */
    private void layoutComponents() {

        buildSlider("Red",
            myColor::setRed, 
            myColor::getRed);
        
        buildSlider("Green",
            myColor::setGreen, 
            myColor::getGreen);
        
        //Using lambdas instead of method references. 
        //Notice this code is more verbose. 
        buildSlider("Blue", 
            x -> myColor.setBlue(x), 
            () -> myColor.getBlue());

    }
    
    /**
     * Build the slider "row" based on a given RGB value. 
     * 
     * @param theLabel The name for the slider
     * @param theColor the function for which RGB value to SET in the color
     * @param theColorValue the function to GET specific R,G, or B color value
     *          R, G, or B value included
     */
    private void buildSlider(final String theLabel,
                             final IntConsumer theColor,
                             final IntSupplier theColorValue) {
        final JSlider slider = 
                        new JSlider(JSlider.HORIZONTAL, 0, 255, theColorValue.getAsInt());
        slider.addChangeListener(theEvent -> theColor.accept(slider.getValue()));

        final JLabel label = new JLabel(theLabel);

        add(label);
        add(slider); 
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     * 
     * NOTE: This is the place where all of the parts and pieces of this project are in 
     *      the same place. This is where we should instantiate our MOdel and add it to the
     *      controller and view.  
     */
    public static void createAndShowGUI() {
        //Create and set up the window.
        final JFrame frame = new JFrame("Observer Design Pattern Demo (Color)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final ObservableColor color = new ObservableColor(51, 0, 111);
        
        //Add a PCL that prints to the console. 
        color.addPropertyChangeListener(theEvent -> {
                if (ObservableColor.PROPERTY_COLOR.equals(theEvent.getPropertyName())) {
                    System.out.println(theEvent.getNewValue().toString());
                }
            }
        );

        color.addPropertyChangeListener(theEvent -> {
                    if (ObservableColor.PROPERTY_COLOR.equals(theEvent.getPropertyName())) {
                        System.err.println(theEvent.getNewValue().toString());
                    }
                }
        );

        
        //Create and set up the content pane.
        final ColorSlider pane = new ColorSlider(color);
        pane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(pane);
        
        //Create a color panel to listen to and demonstrate our 
        //ObservableColor.
        final ColorPanel cop = new ColorPanel(color.getColor());
        cop.setPreferredSize(new Dimension(WINDOW_SIZE, WINDOW_SIZE));
        color.addPropertyChangeListener(cop);
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final JFrame colorFrame = new JFrame();
                colorFrame.setContentPane(cop);
                colorFrame.pack();
                colorFrame.setVisible(true);
            }
        });
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}