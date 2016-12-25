import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;


import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * Created by alina on 24.12.16.
 */
public class Interface extends JFrame {
    private AbstractButton start;
    private JLabel label;
    private JTextField input;
    String user_input;

    public Interface() throws IOException{
        /*final JFrame frame = new JFrame("Simple Chat Room");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel panel = new JPanel(new BorderLayout());
        label = new JLabel("");
        input = new JTextArea();
        start = new JButton("Start chatting");
        label.setVisible(false);
        input.setVisible(false);
        panel.add(label, BorderLayout.NORTH);
        panel.add(input, BorderLayout.CENTER);
        panel.add(start, BorderLayout.SOUTH);
        frame.add(panel);
        frame.setBounds(100, 100, 400, 200);
        frame.setVisible(true); */
    }
    public void start_working(){
        final JFrame frame = new JFrame("Simple Chat Room");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel panel = new JPanel(new BorderLayout());
        label = new JLabel("");
        input = new JTextField();
        start = new JButton("Start chatting");
        label.setVisible(true);
        input.setVisible(true);
        panel.add(label, BorderLayout.NORTH);
        panel.add(input, BorderLayout.CENTER);
        panel.add(start, BorderLayout.SOUTH);
        frame.add(panel);
        frame.setBounds(100, 100, 400, 200);
        frame.setVisible(true);
        /*start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setVisible(true);
                input.setVisible(true);
                //System.out.println("Yey");

            }
        }); */



    }
    public boolean hasInput(){
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_input = input.getText();
            }

        });
        if (user_input != null) return true;
        else return false;
    }

    public void changeLabelText(String answer){
        label.setText(answer);
    }

    public String getCommand(){
        return user_input;
    }

    public void clear(){ input.setText(""); }



}
