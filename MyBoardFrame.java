package core1.Wire.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Maksim Kapralov on 4/13/2017.
 */
public class MyBoardFrame extends JFrame {
    private MyPanel myPanel;
    private JPanel butPanel;
    private JButton goButton;
    private JButton exitButton;
    public MyBoardFrame()
    {
        setTitle("Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(600, 600);
        myPanel = new MyPanel();
        add(myPanel, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel();
        goButton = new JButton("Go");
        exitButton = new JButton("Exit");
        buttonPanel.add(goButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);
        ActionListener taskPerf = new ActionListener()
        {
            public void actionPerformed(ActionEvent evn)
            {
                myPanel.refresh();
                myPanel.repaint();
            }
        };
        int delay = 500;
        Timer timer = new Timer(delay, taskPerf);
        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    myPanel.createBoard();
                    if(goButton.getText().equals("Go"))
                    {
                        if(myPanel.hasElectron())
                        {
                            timer.start();
                            goButton.setText("Stop");
                        }
                        else
                            JOptionPane.showMessageDialog(null, "Please set electron's head and electron's tail!", "Warning!", JOptionPane.WARNING_MESSAGE);
                    }
                    else
                    {
                        timer.stop();
                        goButton.setText("Go");
                    }

            }

        });
        exitButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
    }
    public MyBoardFrame(File file)
    {
        setTitle("Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(600, 600);
        myPanel = new MyPanel(file);
        add(myPanel, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel();
        goButton = new JButton("Go");
        exitButton = new JButton("Exit");
        buttonPanel.add(goButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);
        ActionListener taskPerf = new ActionListener()
        {
            public void actionPerformed(ActionEvent evn)
            {
                myPanel.refresh();
                myPanel.repaint();
            }
        };
        int delay = 500;
        Timer timer = new Timer(delay, taskPerf);
        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myPanel.createBoard();
                if(goButton.getText().equals("Go"))
                {
                    timer.start();
                    goButton.setText("Stop");
                }
                else
                {
                    timer.stop();
                    goButton.setText("Go");
                }
            }
        });
        exitButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });

    }
}
