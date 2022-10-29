import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Lissajous {

    public static void main(String []args) throws InterruptedException {
        int size = 100;
        JFrame frame = new JFrame("Lissajous");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(2*size+1, 2*size+1));
        frame.add(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Graphics g = panel.getGraphics();
        double cycles = 5;
        double angularResolution = 0.001;
        long delay = 80;
        int frames = 64;
        for(;;){
            double frequency = Math.random() * 3.0;
            float phase = 0.0f;
            for( int i = 0; i < frames; i++) {
                g.setColor(Color.BLACK);
                g.fillRect(0,0, panel.getWidth(), panel.getHeight());
                g.setColor(Color.GREEN);
                for(double t = 0.0; t < cycles*2*Math.PI; t+= angularResolution){
                    double x = Math.sin(t);
                    double y = Math.sin(t * frequency + phase);
                    g.drawRect( (size + (int)(x*size+0.5)), (size + (int)(y*size+0.5)),1,1);
                }
                phase += 0.1;
                Thread.sleep(delay);
            }
        }
    }
}
