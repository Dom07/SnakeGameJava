import javax.swing.*;
import java.awt.*;

/**
 * Created by Dominic on 04-02-2018.
 */
public class RenderPanel extends JPanel {

    public final Color black = Color.BLACK;

    public final Color red = Color.RED;

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(black);
        g.fillRect(0,0,800,600);
        Snake snake = Snake.snake;
        g.setColor(red);
        for(Point point : snake.snakeParts){
            g.fillRect(point.x * snake.SCALE, point.y * snake.SCALE, snake.SCALE, snake.SCALE);
        }

        g.fillRect(snake.head.x, snake.head.y, snake.SCALE, snake.SCALE);
    }



}
