import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.*;

public class Draw extends JPanel implements KeyListener, ActionListener {
  int x = 0;
  int y = 0;
  Paddle paddlel;
  Paddle paddler;
  Ball ball;
  int score_left = 0;
  int score_right = 0;
  JFrame frame;
  int speed_delta = 50;
  int initial_speed = 800;

  public Draw(JFrame frame) {
    super();
    super.setFocusable(true);
    super.addKeyListener(this);

    this.frame = frame;

    this.paddlel = new Paddle(20, 175, 5, 75);
    this.paddler = new Paddle(380, 175, 5, 75);

    this.ball = new Ball(200, 200, 15, 10, 5, initial_speed);
    // add button later
    this.startMoveBall();
    //this.startMovePaddler();
  }

  public void paint(Graphics g) {
    g.clearRect(0, 0, getWidth(), getHeight());
    g.setColor(Color.BLUE);
    g.fillRect(x, y, getWidth(), getHeight());

    paddlel.draw(g);
    paddler.draw(g);
    ball.draw(g);
  }

  public void update(KeyEvent e) {
    // Moves left paddle up
    if (e.getKeyCode() == KeyEvent.VK_W) {
      this.paddlel.moveUp();
    }
    // Moves left paddle down
    if (e.getKeyCode() == KeyEvent.VK_S) {
      this.paddlel.moveDown();
    }
    // Moves right paddle up
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      this.paddler.moveUp();
    }
    // Moves right paddle down
    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      this.paddler.moveDown();
    }
    repaint();
  }

  @Override
  public void keyPressed(KeyEvent e) {
    update(e);
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    this.ball.move();
    repaint();
  }

  public void startMoveBall() {
      Timer timer = new Timer(ball.speed, new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
              ball.move();

              // Ball hits right paddle
              if (ball.delta_x > 0 && paddler.x <= (ball.x + ball.diamater)
                  && (ball.y >= paddler.y && ball.y <= (paddler.y + paddler.height))) {
                  ball.delta_x = -ball.delta_x; // Reverse horizontal direction
                  ball.speed -= speed_delta; // Increase speed
                  System.out.println("New speed is " + ball.speed);
                  ((Timer) ae.getSource()).stop();
                  startMoveBall();
              }
              // Ball hits left paddle
              else if (ball.delta_x < 0 && paddlel.x >= ball.x
                  && (ball.y >= paddlel.y && ball.y <= (paddlel.y + paddlel.height))) {
                  ball.delta_x = -ball.delta_x; // Reverse horizontal direction
                  ball.speed -= speed_delta; // Increase speed
                  System.out.println("New speed is " + ball.speed);
                  ((Timer) ae.getSource()).stop();
                  startMoveBall();
              }
              // Ball hits the top or bottom of the screen
              else if (ball.delta_y > 0 && ball.y + ball.diamater >= getHeight()) {
                  ball.delta_y = -ball.delta_y; // Reverse vertical direction
              } 
              else if (ball.delta_y < 0 && ball.y <= 0) {
                  ball.delta_y = -ball.delta_y; // Reverse vertical direction
              }
              // Ball goes out of the right side
              else if ((ball.x + ball.diamater) >= getWidth()) {
                  System.out.println("Left player scores");
                  ball.reset();
                  score_right++;
                  if (score_right == 10) {
                      System.out.println("Left player wins");
                      JOptionPane.showMessageDialog(frame, "Left player wins");
                      System.exit(0);
                  }
              }
              // Ball goes out of the left side
              else if (ball.x <= 0) {
                  System.out.println("Right player scores");
                  ball.reset();
                  score_left++;
                  if (score_left == 10) {
                      System.out.println("Right player wins");
                      JOptionPane.showMessageDialog(frame, "Right player wins");
                      System.exit(0);
                  }
              }
              repaint();
          }
      });
      timer.start();
  }

  public void startMovePaddler() {
    Timer timer = new Timer(300, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        paddler.move();
        repaint();
      }
    });
    timer.start();
  }
}