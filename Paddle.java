import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

public class Paddle {
  int x = 0;
  int y = 0;
  int width = 0;
  int height = 0;
  final int delta = 10;
  boolean directionDown = true;

  public Paddle(int x, int y, int width, int height) {
    super();
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public boolean paddleAtBottom() {
    return y >= (385 - delta);
  }

  public boolean paddleAtTop() {
    return y <= 0;
  }

  public void moveUp() {
    if (!paddleAtTop())
      this.y -= delta;
  }

  public void moveDown() {
    if (!paddleAtBottom())
      this.y += delta;
  }

  public void move() {
    if (directionDown) {
      if (!paddleAtBottom())
        moveDown();
      else
        directionDown = false;
    } else if (!directionDown) {
      if (!paddleAtTop())
        moveUp();
      else
        directionDown = true;
    }
  }

  public void draw(Graphics g) {
    g.setColor(Color.BLACK);
    g.fillRect(x, y, width, height);
  }
}