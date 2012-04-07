import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

class JTester extends JFrame implements ActionListener
{
	JTextPane tp,tp2;
	JButton btn, clo;
	JLabel lb,lb2;
	Container c;
	JTester()
	{
		setSize(400,400);
		setTitle("Java Teaching Client");

		c = this.getContentPane();
		c.setLayout(null);
		
		tp = new JTextPane();
		tp.setBounds(50,50,300,280);
		tp.setText("class temp{ public static void main(String cm[]){System.out.println(\"HI\");for(int i=0;i<10;i++)System.out.println(i);}}");

		tp2 = new JTextPane();
		tp2.setBounds(450,50,300,280);
		
		btn = new JButton("Compile");
		btn.addActionListener(this);
		btn.setBounds(150,340,100,30);

		clo = new JButton("Back");
		clo.addActionListener(this);
		clo.setBounds(450,340,100,30);

		lb = new JLabel("Enter the code here and click Compile:");
		lb.setBounds(50,20,300,30);

		lb2 = new JLabel("Output:");
		lb2.setBounds(450,20,300,30);

		c.add(tp);
		c.add(btn);
		c.add(lb);	

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}	
	public void actionPerformed(ActionEvent e)
	{
		try{
		if(e.getSource()==btn)
		{
			tp2.setText("");
			String text = tp.getText();
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("temp.java"),false));
			bw.write(text);
			bw.close();
			Process p = Runtime.getRuntime().exec("javac temp.java");
			Scanner s = new Scanner(p.getErrorStream());
			String sb = "",sb2="";
			while(s.hasNextLine())
				sb = sb + s.nextLine() + "\n";

			if(sb.length()>3)
			{
				this.setSize(800,400);
				tp2.setText(sb);
				c.add(tp2);
				c.add(clo);
				lb2.setText("Output: ");
				c.add(lb2);
				repaint();
				sb = "";
			}
			else
			{
				Process p2 = Runtime.getRuntime().exec("java temp");
				Scanner s22 = new Scanner(p2.getInputStream());

				String sb22 = "";
				while(s22.hasNextLine())
					sb22 = sb22 + s22.nextLine() + "\n";
				this.setSize(800,400);
				tp2.setText(sb22);
				c.add(tp2);
				c.add(clo);
				lb2.setText("Output:[Compiled Successfully!]");
				c.add(lb2);
				repaint();
				sb = "";
			}
		}
		else if(e.getSource()==clo)
		{
			this.setSize(400,400);
		}
		}catch(Exception ex){}
	}
	public static void main(String abc[])
	{
		new JTester();
	}
}
