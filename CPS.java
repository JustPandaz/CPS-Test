import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;
import java.util.Timer;

public class CPS {

    JFrame frame;
    JPanel panel;
    JButton btnCPS;
    JLabel cpsLabel;
    Timer cpsTimer;

    boolean isCpsRunning = false;
    int seconds = 5;
    double cps = 0, clicks;

    CPS() {

        frame = new JFrame("CPS Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(400, 250);

        UISetup();

        frame.setVisible(true);
    }

    void UISetup() {

        btnCPS = new JButton("");
        cpsLabel = new JLabel("Click!");

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 100);
        panel.setLayout(new GridLayout(1,2,10,10));

        panel.add(btnCPS);
        panel.add(cpsLabel);

        btnFunctions();

        frame.add(panel);
    }

    void btnFunctions() {

        btnCPS.addActionListener(e1 -> {

            if(!isCpsRunning) {

                cpsTimer = new Timer();
                cpsTimer.schedule(new CPSTimer(), seconds * 1000L);
                isCpsRunning = true;
            } else {

                clicks++;
                cpsLabel.setText("Clicks: " + clicks);
            }
        });
    }

    class CPSTimer extends TimerTask {
        @Override
        public void run() {

            cps = clicks / seconds;
            cpsLabel.setText("Your CPS is: " + cps);
            clicks = 0;
            isCpsRunning = false;

            if(cps != 0) {
                int result = JOptionPane.showConfirmDialog(frame, "Your cps is: " + cps, "CPS Test",
                        JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE);
            }
            cpsTimer.cancel();
        }
    }
}
