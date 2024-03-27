import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {
    static JFrame getFrame(int width, int height) {
        JFrame var2 = new JFrame();
        var2.setVisible(true);
        var2.setDefaultCloseOperation(3);
        var2.setBounds(0, 0, width, height);
        return var2;
    }
    public static void main(String[] args) {
        JFrame frame = getFrame(500*2, 500*2);
        ImagePanel content = new ImagePanel("resources/sprite.jpg", frame.getBounds());
        int step = 50;

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    content.setSprint(false);
                }
                super.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_UP -> content.shiftY(-step, frame.getBounds().height);
                    case KeyEvent.VK_DOWN -> content.shiftY(step, frame.getBounds().height);
                    case KeyEvent.VK_RIGHT -> content.shiftX(step, frame.getBounds().width);
                    case KeyEvent.VK_LEFT -> content.shiftX(-step, frame.getBounds().width);
                    case KeyEvent.VK_SHIFT -> content.setSprint(true);
                }
                super.keyPressed(e);
            }
        });
        frame.setContentPane(content);
        frame.setVisible(true);
    }
}