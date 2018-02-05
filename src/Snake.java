import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Dominic on 04-02-2018.
 */
public class Snake implements ActionListener {

    public ArrayList<Point> snakeParts = new ArrayList<Point>();

    public JFrame jFrame;

    public RenderPanel renderPanel;

    public Timer timer = new Timer(20,this);

    public static Snake snake;

    public final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;

    public int ticks, direction = DOWN, score =0, tailLength;

    public Point head, cherry;

    public Dimension dim;

    public Random random;

    public boolean over = false;

    public Snake(){
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame = new JFrame("Snake Game");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setSize(800,600);
        jFrame.add(renderPanel = new RenderPanel());
        head = new Point(0 ,0);
        random = new Random();
        cherry = new Point(dim.width / SCALE, dim.height / SCALE);
        timer.start();
        System.out.println(dim.height/10);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        renderPanel.repaint();
        ticks++;

        if( ticks % 10 == 0 && head !=null && over != true ) {
            snakeParts.add(new Point(head.x, head.y));
            if (direction == UP)
                if (head.y - 1 > 0)
                    head = new Point(head.x, head.y - 1);
                else
                    over = true;

            if (direction == DOWN)
                if (head.y + 1 < dim.height / SCALE)
                    head = new Point(head.x, head.y + 1);
                else
                    over = true;

            if (direction == LEFT)
                if (head.x - 1 > 0)
                    head = new Point(head.x - 1, head.y);
                else
                    over = true;

            if (direction == RIGHT)
                if (head.x + 1 < dim.width / SCALE)
                    head = new Point(head.x + 1, head.y);
                else
                    over = true;

            snakeParts.remove(0);
            
            if (cherry != null)
                if (head.equals(cherry)) {
                    score+=10;
                    tailLength++;
                    cherry.setLocation(dim.width / SCALE, dim.height / SCALE);
                }
        }
    }


    public static  void main(String [] args){
        snake = new Snake();
    }

}
