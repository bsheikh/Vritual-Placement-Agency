/*
 * Programmer: Bilawal Sheikh
 * File: VirtualPlacementAgency.java
 */

import javax.swing.*; 
import java.awt.event.*;
import java.awt.*;

public class VirtualPlacementAgency implements ActionListener {
  JFrame frame;
  JPanel contentPane;
  JLabel options;
  JButton app, comp, posit;
  public int frameStatus;
  
  /**
   * Main GUI is Opened
   * pre: none
   * post: New Frame is openeded up
   */
  
  public VirtualPlacementAgency() {
    frame = new JFrame("Virtual Placement Agency");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    contentPane = new JPanel();
    contentPane.setLayout(new GridLayout(1,4, 10, 10));
    contentPane.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    
    options = new JLabel("Pick One:");
    contentPane.add(options);
    
    comp = new JButton("Companies");
    comp.setActionCommand("Companies");
    comp.addActionListener(this);
    contentPane.add(comp);
    
    app = new JButton("Applicants");
    app.setActionCommand("Applicants");
    app.addActionListener(this);
    contentPane.add(app);
    
    posit = new JButton("Positions");
    posit.setActionCommand("Positions");
    posit.addActionListener(this);
    contentPane.add(posit);
    
    frame.setContentPane(contentPane);
    frame.pack();
    frame.setVisible(true);
  }
  
  public void actionPerformed(ActionEvent event) {
    /**
   * Handles Button Click
   * pre: none
   * post: New Frame is openeded up
   */
    String eventName = event.getActionCommand();
    frame.setVisible(false);
    if (eventName.equals("Companies")) {
      frameStatus = 1;
      AddEditDelete load = new AddEditDelete(frameStatus);
    } else if (eventName.equals("Applicants")) {
      frameStatus = 2;
      AddEditDelete load = new AddEditDelete(frameStatus);
    } else if (eventName.equals("Positions")) {
      frameStatus = 3;
      AddEditDelete load = new AddEditDelete(frameStatus);
    }
  }
  
  private static void runGUI() {
    JFrame.setDefaultLookAndFeelDecorated(true);
    VirtualPlacementAgency mainScreen = new VirtualPlacementAgency();
  }
  
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        runGUI();
      }
    });
  }
}