package views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ImagePanel extends JPanel {

    private BufferedImage image;

    public ImagePanel(String fileString) {
        try {
            BufferedImage tmp = ImageIO.read(getFileFromResourceAsStream("img/" + fileString));
            image = new BufferedImage(170, 310, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();
            g2d.drawImage(tmp, 0, 0, 170,310, null);
            g2d.dispose();


        } catch (IOException ex) {
            // handle exception...
        }
    }



    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        //InputStream inputStream = getClass().getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

    @Override
    public Dimension getPreferredSize() {
        return image == null ? new Dimension(400, 300): new Dimension(image.getWidth(), image.getHeight());
        //return new Dimension(50, 90);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

}