package clients.packing;

import javax.swing.*;
import java.awt.*;

/**
 * A custom component that displays an image with support for clearing
 * and scaling the image to fit the component's dimensions.
 *
 * <h2>Key Features:</h2>
 * <ul>
 *   <li>Extends JComponent to support Swing functionality like tooltips</li>
 *   <li>Supports setting and clearing images</li>
 *   <li>Automatically scales images to fit the component size</li>
 * </ul>
 *
 * @author Liz Tipper
 * @version 1.0
 */
public class Picture extends JComponent {
    private ImageIcon image = null;
    private final int width;
    private final int height;

    /**
     * Constructs a Picture component with specified dimensions.
     *
     * @param width The width of the picture component
     * @param height The height of the picture component
     */
    public Picture(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
    }

    /**
     * Sets a new image to be displayed.
     *
     * @param image The ImageIcon to be displayed
     */
    public void set(ImageIcon image) {
        this.image = image;
        repaint();
    }

    /**
     * Clears the current image.
     */
    public void clear() {
        image = null;
        repaint();
    }

    /**
     * Paints the component, scaling the image if one is set.
     *
     * @param g The Graphics context in which to paint
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            Image scaled = image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            g.drawImage(scaled, 0, 0, this);
        }
    }
}