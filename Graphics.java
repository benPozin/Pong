import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

class Graphics {
  Graphics() {
    // Window Setup
    JFrame window = new JFrame("Window");
    Draw bg = new Draw(window);
    window.add(bg);
    window.setSize(450, 450);
    window.setVisible(true);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    
  }
}
