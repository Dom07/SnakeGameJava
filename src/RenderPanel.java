import javax.swing.*;
import java.awt.*;

/**
 * Created by Dominic on 04-02-2018.
 */
public class RenderPanel extends JPanel {

    public Color black = Color.BLACK;

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(black);
        g.fillRect(0,0,800,600);
    }



}
