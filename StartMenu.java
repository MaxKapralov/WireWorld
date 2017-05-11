package core1.Wire.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Maksim Kapralov on 4/7/2017.
 */
public class StartMenu extends JFrame{
    private JPanel buttonPanel;
    private JButton fileChooserBut;
    private JButton startButton;
    private JLabel progNameField;
    private JTextField pathTextField;
    private JPanel mainPanel;
    private JButton exitButton;
    private File selectedFile;
    public StartMenu() {
        fileChooserBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(null);
                if(result == JFileChooser.APPROVE_OPTION)
                {
                    pathTextField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedFile = new File(pathTextField.getText());
                if(!selectedFile.exists())
                    JOptionPane.showMessageDialog(null, "Please enter data file","Warning", JOptionPane.WARNING_MESSAGE);
                else
                {
                    JFrame f = new MyBoardFrame(selectedFile);
                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    f.pack();
                    f.setVisible(true);
                }
            }

        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    public JPanel getMainPanel()
    {
        return mainPanel;
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Start menu");
        frame.setContentPane(new StartMenu().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
