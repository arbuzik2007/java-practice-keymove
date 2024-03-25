import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {
    BufferedImage image;
    Rectangle bounds;
    int width, height;
    public ImagePanel(String imagePath, Rectangle bounds){
        this.bounds = bounds;
        try {
            var inImage = ImageIO.read(new File(imagePath));
            MediaTracker mt = new MediaTracker(this);
            mt.addImage(inImage, 1);
            try {
                mt.waitForAll();
            } catch (Exception e) {
                System.out.println("Image not found.");
            }
            ImageIcon icon = new ImageIcon(inImage);
            int scale = 5;
            width = icon.getIconWidth() / scale;
            height = icon.getIconHeight() / scale;
            image = inImage;


        }catch (IOException ex) {
            // handle exception...
        }
    }

    int x=0,y=0;

    boolean checkBorderX() {
        return (this.x < (bounds.getMinX())) || (this.x > (bounds.getWidth() - width));
    }
    boolean checkBorderY(){
        return (this.y < (bounds.getMinY())) || (this.y > (bounds.getHeight() - height));
    }

    public void shiftX(int x) {
        if(checkBorderX())
            x = -x;
        this.x += x;
        repaint();
    }

    public void shiftY(int y) {
        if(checkBorderY())
            y = -y;
        this.y += y;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image,x,y, width, height, this);
        g.dispose();
        super.paintComponent(g);
    }
}
