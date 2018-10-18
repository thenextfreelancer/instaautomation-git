package com.instagram;

import java.awt.Dimension;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import com.instagram.ui.InstaAutomationPanel;

/**
 *
 * @author amishra
 */
public class Application {

    /**
     * @param args
     */
    public static void main(String[] args) {
        JFrame f = new JFrame("Instagram Bot");
        f.setSize(540, 532);
        try {
            FileUtils util = new FileUtils();
            util.copyDriver();
            InstaAutomationPanel mainPanel = new InstaAutomationPanel();
            f.add(mainPanel);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setSize(950, 711);
            f.setMinimumSize(new Dimension(950, 711));
            f.setMaximumSize(new Dimension(950, 711));
            f.setResizable(false);
            f.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
