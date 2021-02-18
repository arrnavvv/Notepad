import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class About extends JFrame implements ActionListener {
    JButton button;
    About(){
        this.setBounds(600,200,700,600);
        this.setLayout(null);
        ImageIcon icon = new ImageIcon("windows.png");
        Image iconImage = icon.getImage().getScaledInstance(400,80,Image.SCALE_DEFAULT);
        ImageIcon icon2 = new ImageIcon(iconImage);
        JLabel label = new JLabel();
        label.setBounds(50, 180, 70, 70);
        this.add(label);


        JLabel innerLabel = new JLabel("<html>Arnav Mahajan<br>IIITDMJ  2021<br>2021 Arnav Mahajan. All rights reserved<br><br>Notepad is a word processing program, <br>which allows changing of text in a computer file.<br>Notepad is a simple text editor for basic text-editing program<br> which enables computer users to create documents. </html>");
        innerLabel.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
        innerLabel.setBounds(150, 130, 500, 300);
        add(innerLabel);

         button = new JButton("OK");
        button.setBounds(580, 500, 80, 25);
        button.addActionListener(this);
        button.setFocusable(false);
        this.add(button);
        this.add(innerLabel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button){
            this.dispose();
        }
    }
}
