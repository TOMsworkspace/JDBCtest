package package1;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginGUI{

	public void logGUI() {
		JFrame login=new JFrame("LoginForm");
		Font font=new Font("TimesRoman",Font.BOLD,15);
		JTextField account=new JTextField("«Î ‰»Î’Àªß",30);
		JPasswordField password=new JPasswordField("«Î ‰»Î√‹¬Î",30);
		
		login.setFont(font);
		login.setSize(500,490);
		JLabel accl=new JLabel("’Àªß");
		JLabel passl=new JLabel("√‹¬Î");
		JButton load=new JButton("µ«¬Ω");
		JButton exit=new JButton("ÕÀ≥ˆ");
		//passl.setSize(20,20);
		//password.setSize(30, 5);
		accl.setFont(font);
		passl.setFont(font);
		account.setFont(font);
		password.setFont(font);
		
		FlowLayout layout=new FlowLayout(FlowLayout.CENTER);
		//layout.setAlignment(GridL);
		layout.setHgap(20);
		//layout.setVgap(150);
		login.add(accl);
		login.add(account);
		layout.setAlignOnBaseline(true);
		login.add(passl);
		login.add(password);
		login.add(load);
		login.add(exit);
		

		//layout.setVgap(150);
		login.setLayout(layout);
		login.setVisible(true);
		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				login.setVisible(false);
				login.dispose();
			}

		});
		
		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
	
				String act=account.getText();
				String psd=String.valueOf(password.getPassword());
				sysThread st=new sysThread(act,psd);
				System.out.println("Thread "+st.getId()+"join.....");
				st.start();
				
				//login.setVisible(false);
				//login.dispose();
			}

		});
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginGUI login=new LoginGUI();
		login.logGUI();
		

	}

}
