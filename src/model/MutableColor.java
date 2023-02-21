package model;

/**
 * Defines mutable behaviors for a Color with RGB values. 
 * 
 * @author Charles Bryan
 * @version 1
 */
public interface MutableColor {

    /**
     * Returns the red component in the range 0-255 in the default RGB space.
     *  
     * @return the red component
     */
    int getRed();
    
    /**
     * Sets Red component with the specified value in the range (0 - 255).
     * This method notifies Observers of a change.
     * 
     * @param theRed the red component
     * @throws IllegalArgumentException if theRed is 
     *          outside the range 0 to 255, inclusive
     */
    void setRed(int theRed);
    
    /**
     * Returns the green component in the range 0-255 in the default RGB space.
     *  
     * @return the green component
     */
    int getGreen();
    
    /**
     * Sets Green component with the specified value in the range (0 - 255).
     * This method notifies Observers of a change. 
     * 
     * @param theGreen the green component
     * @throws IllegalArgumentException if theGreen is 
     *          outside the range 0 to 255, inclusive
     */
    void setGreen(int theGreen);
    
    /**
     * Returns the Blue component in the range 0-255 in the default RGB space.
     *  
     * @return the blue component
     */
    int getBlue();
    
    /**
     * Sets Blue component with the specified value in the range (0 - 255).
     * This method notifies Observers of a change. 
     * 
     * @param theBlue the blue component
     * @throws IllegalArgumentException if theBlue is 
     *          outside the range 0 to 255, inclusive
     */
    void setBlue(int theBlue);
    
}
