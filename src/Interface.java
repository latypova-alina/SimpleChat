import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;


import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * Created by alina on 24.12.16.
 */
public class Interface extends JFrame {
    private JLabel name_label;
    private JLabel enter_message;
    private JTextArea online;
    private JLabel message_from;
    private JLabel message;
    private JTextField name_input;
    private JTextArea chatter;
    private JTextField input;
    String user_input;
    String chatter_name;
    int i;

    public void start_working(){
        final JFrame frame = new JFrame("Simple Chat Room");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 4, 4));
        name_label = new JLabel("Введите ваше имя");
        name_input = new JTextField();
        chatter = new JTextArea();
        message_from = new JLabel("Сообщение от: ");
        online = new JTextArea("online \n");
        enter_message = new JLabel("Введите ваше сообщение");
        message = new JLabel("");
        input = new JTextField();
        i = 0;

        panel.add(name_label);
        panel.add(message_from);
        panel.add(name_input);
        panel.add(chatter);
        panel.add(enter_message);
        panel.add(message);
        panel.add(input);
        panel.add(online);
        enter_message.setVisible(false);
        message_from.setVisible(false);
        message.setVisible(false);
        chatter.setVisible(false);
        online.setVisible(false);
        input.setVisible(false);


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
        input.setVisible(true);
        name_label.setText("Кому:");
    }

    public void addName(ArrayList<String> name){
        online.setText("");
        for (int i=0; i<name.size(); i++){
            online.setText(online.getText() + name.get(i) + "\n" );
        }
        online.setText("ONLINE: \n" + online.getText());
     }

    public boolean userNameHasInput(){
        name_input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatter_name = name_input.getText();
            }

        });
        if ((chatter_name == null)||(chatter_name.equals(""))) return false;
        else return true;
    }

    public boolean nameAndInput(){
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatter_name = name_input.getText();
                user_input = input.getText();
            }

        });
        if (chatter_name != null && user_input != null) return true;
        else return false;
    }

    public void changeLabelText(String answer) {
        name_label.setText(answer);
    }

    public String getClientName(){
        return name_input.getText();
    }

    public void clear(){
        name_input.setText("");
        input.setText("");
        chatter_name = null;
        user_input = null;
    }

    public String getChatterName(){
        return chatter_name;
    }

    public String getMessage(){
        return user_input;
    }

    public void messageFrom(String s){
        chatter.setText(s);
    }

    public void setMessage(String s){
        message.setText(s);
    }



}
