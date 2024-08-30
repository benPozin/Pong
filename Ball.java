import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

public class Ball
{
  //Defines current position
  int x = 0;
  int y = 0;
  int x0 = 0;
  int y0 = 0;
  int diamater = 0;
  int speed = 0;
  //Defines motion vector
  int delta_x = 0;
  int delta_y = 0;
  
  public Ball(int x, int y, int diamater, int delta_x, int delta_y, int speed)
  {
    super();
    this.x = x;
    this.y = y;
    this.x0 = x;
    this.y0 = y;
    this.delta_x = delta_x;
    this.delta_y = delta_y;
    this.diamater = diamater;
    this.speed = speed;
  }

  public void move()
  {
    x += delta_x;
    y += delta_y;
  }

  public void reset()
  {
    this.x = x0;
    this.y = y0;
    this.delta_x = -this.delta_x; // Reverse direction upon reset
  }
  
  public void draw(Graphics g)
  {
    g.setColor(Color.BLACK);
    g.fillOval(x, y, diamater, diamater);
  }
}