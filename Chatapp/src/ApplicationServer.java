
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.*;
import java.io.*;
/**
 *
 * @author user
 */

public class ApplicationServer extends Frame implements ActionListener,Runnable 
{
    Button b1;
    TextField tf;
    TextArea ta;
    ServerSocket ss;
    Socket s;
    PrintWriter pw;
    BufferedReader br;
    Thread th;
    public ApplicationServer()
    {
        Frame f=new Frame("Server side chatting");
        f.setLayout(new FlowLayout());
        f.setBackground(Color.yellow);
        b1=new Button("send");
        b1.setBackground(Color.blue);
        b1.addActionListener((ActionListener) this);
        tf=new TextField(15);
        ta=new TextArea(12,20);
        ta.setBackground(Color.red);
        f.addWindowListener(new W1());
        f.add(b1);
        f.add(tf);
        f.add(ta);
        System.out.println("Waiting for client");
        try
        {
            ss=new ServerSocket(2000);
            s=ss.accept();
            br=new BufferedReader(new InputStreamReader(s.getInputStream()));
            pw=new PrintWriter(s.getOutputStream(),true);
        }catch(Exception e)
        {
        }
        th=new Thread(this);
        th.setDaemon(true);
        th.start();
        setFont(new Font("Arial",Font.BOLD,20));
        f.setSize(200, 200);
        f.setLocation(300, 300);
        f.setVisible(true);
        f.validate();
    }
    private class W1 extends WindowAdapter
    {
        public void windowClosing(WindowEvent we)
        {
            System.exit(0);
        }
    }
public void actionPerformed(ActionEvent ae)
{
    pw.println(tf.getText());
    tf.setText("");
}
public void run()
{
    while(true)
    {
    try
    {
        
        	ta.append(br.readLine()+"\n");
        
    }catch(Exception e)
    {}
    }
}
public static void main(String args[])
{
    ApplicationServer a=new ApplicationServer();
}
}

