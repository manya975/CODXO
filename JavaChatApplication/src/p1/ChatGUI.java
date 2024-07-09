package p1;

import javax.swing.*;

import client.ChatClient;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatGUI extends JFrame {
    private JTextArea message;
    private JTextField textField;
    private ChatClient c;
    private JButton exitbtn;

    public ChatGUI() {
        super("!! Chat Application !!");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Color backgroundColor = new Color(240, 240, 240);
        Color buttonColor = new Color(75, 75, 75);
        Color textColor = new Color(50, 50, 50);
        Font textFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 12);

        message = new JTextArea();
        message.setEditable(false);
        message.setBackground(backgroundColor);
        message.setForeground(textColor);
        message.setFont(textFont);
        add(new JScrollPane(message), BorderLayout.CENTER);

        textField = new JTextField();
        textField.setFont(textFont);
        textField.setForeground(textColor);
        textField.setBackground(backgroundColor);

        String name = JOptionPane.showInputDialog(this, "Enter your name:", "Name Entry", JOptionPane.PLAIN_MESSAGE);
        this.setTitle("Chat Application - " + name);

        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = "[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " + name + ": " + textField.getText();
                c.sendMessage(message);  // Corrected to send formatted message
                textField.setText("");
            }
        });

        exitbtn = new JButton("Exit");
        exitbtn.setFont(buttonFont);
        exitbtn.setBackground(buttonColor);
        exitbtn.setForeground(Color.WHITE);
        exitbtn.addActionListener(ev -> {
            String departmssg = name + " has left the chat.";
            c.sendMessage(departmssg);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            System.exit(0);
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(backgroundColor);
        bottomPanel.add(textField, BorderLayout.CENTER);
        bottomPanel.add(exitbtn, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        try {
            this.c = new ChatClient("127.0.0.1", 5000, this::onMessageReceived);
            c.startClient();
        } catch (IOException ie) {
            ie.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to server", "Connection Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void onMessageReceived(String mssg) {
        SwingUtilities.invokeLater(() -> message.append(mssg + "\n"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ChatGUI().setVisible(true);
        });
    }
}
