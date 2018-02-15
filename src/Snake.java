import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Dominic on 04-02-2018.
 */
public class Snake implements ActionListener, KeyListener {

    public ArrayList<Point> snakeParts = new ArrayList<Point>();

    public JFrame jFrame;

    public RenderPanel renderPanel;

    public Timer timer = new Timer(20,this);

    public static Snake snake;

    public final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;

    public int ticks = 0, direction = DOWN, score = 0, tailLength = 20;

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
        jFrame.setResizable(false);
        jFrame.add(renderPanel = new RenderPanel());
        jFrame.addKeyListener(this);
        gameStart();
    }

    public void gameStart(){
        score = 0;
        tailLength = 1;
        direction = DOWN;
        over = false;
        snakeParts.clear();
        head = new Point(79/2 ,57/2);
        random = new Random();
        cherry = new Point(random.nextInt(79), random.nextInt(57));
        for(int i = 0 ; i < tailLength ; i++){
            snakeParts.add(new Point(head.x, head.y));
        }
        timer.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        renderPanel.repaint();
        ticks++;


        if( ticks % 2 == 0 && head != null && over != true ) {
            snakeParts.add(new Point(head.x, head.y));
            if(direction == UP)
                if(head.y - 1 >= 0)
                    head = new Point(head.x, head.y-1);
                else
                    over = true;

            if(direction == DOWN)
                if(head.y + 1 < 57)
                    head = new Point(head.x, head.y+1 );
                else
                    over = true;

            if(direction == RIGHT)
                if(head.x + 1 < 79)
                    head = new Point(head.x + 1 , head.y);
                else
                    over = true;

            if(direction == LEFT)
                if(head.x - 1 >= 0)
                    head = new Point(head.x -1 , head.y);
                else
                    over = true;

            if(snakeParts.size() > tailLength)
                snakeParts.remove(0);

            for(Point point : snakeParts){
                if(head.equals(point))
                    over = true;
            }
            if (cherry != null)
                if (head.equals(cherry)) {
                    score+=10;
                    tailLength++;
                    cherry.setLocation(random.nextInt(79), random.nextInt(57));
                }
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        if(i == KeyEvent.VK_UP && direction != UP)
            direction = UP;
        if(i == KeyEvent.VK_DOWN && direction != DOWN)
            direction = DOWN;
        if(i == KeyEvent.VK_LEFT && direction != LEFT)
            direction = LEFT;
        if(i == KeyEvent.VK_RIGHT && direction != RIGHT)
            direction = RIGHT;
        if(i == KeyEvent.VK_SPACE && over)
            gameStart();
    }


    public static  void main(String [] args){
        snake = new Snake();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }


    @Override
    public void keyReleased(KeyEvent e) {
    }
}
