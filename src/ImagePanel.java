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
    int speed = 1;
    final int sprint = 2;
    boolean isSprint = false;
    public void setSprint(boolean sprint) {
        isSprint = sprint;
    }
    boolean checkBorderXL() {
        return (this.x < (bounds.getMinX()));
    }
    boolean checkBorderXR() { return (this.x > (bounds.getWidth() - width)); }
    boolean checkBorderYL(){ return (this.y < (bounds.getMinY())); }
    boolean checkBorderYR() {return (this.y > (bounds.getHeight() - height));}

    public void shiftX(int x, int width) {
        if(checkBorderXR())
            x = -this.x;
        if(checkBorderXL())
            x = width - this.width;
            //x = -x;
        this.x = (this.x + x) * (isSprint ? speed * sprint : speed);
        repaint();
    }

    public void shiftY(int y, int height) {
        if(checkBorderYR())
            y = -this.y;
        if(checkBorderYL())
            y = height - this.height;
            //y = -y;
        this.y = (this.y + y) * (isSprint ? speed * sprint : speed);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image,x,y, width, height, this);
        g.dispose();
        super.paintComponent(g);
    }
}
