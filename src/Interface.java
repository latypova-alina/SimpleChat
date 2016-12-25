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
    private JLabel enter_message;
    private JTextArea online;
    private JLabel message_from;
    private JLabel message;
    private JTextField input;
    private JTextField chatter;
    String user_input;

    public void start_working(){
        final JFrame frame = new JFrame("Simple Chat Room");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 4, 4));
        label = new JLabel("Введите ваше имя");
        input = new JTextField("кому");
        chatter = new JTextField();
        message_from = new JLabel("Сообщение от: ");
        online = new JTextArea("online");
        enter_message = new JLabel("Введите ваше сообщение");
        message = new JLabel("");

        panel.add(label);
        panel.add(enter_message);
        panel.add(online);
        panel.add(chatter);
        panel.add(input);
        panel.add(message_from);
        panel.add(message);
        /*enter_message.setVisible(false);
        message_from.setVisible(false);
        message.setVisible(false);
        chatter.setVisible(false);
        online.setVisible(false);*/

        frame.add(panel);
        frame.setBounds(200, 200, 800, 400);
        frame.setVisible(true);

    }

    public void authorized(){
        enter_message.setVisible(true);
        message_from.setVisible(true);
        message.setVisible(true);
        chatter.setVisible(true);
        online.setVisible(true);
        label.setVisible(false);
    }

    public void addName(String name){
        online.setText(online.getText() + "<html>" + name + "<br></html>" );
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

    public void changeLabelText(String answer) {
        message.setText(answer);
    }

    public String getCommand(){
        return user_input;
    }

    public void clear(){ input.setText(""); }



}
