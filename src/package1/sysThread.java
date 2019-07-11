package package1;

import javax.swing.JOptionPane;

public class sysThread extends Thread {
	
	private String account;
	private String password;
	
	public sysThread(String account,String password) {
		this.account=account;
		this.password=password;		
	}
	
	public void run() {
			switch(account){
				case "student" :{
					if(password.equals("student"))
						new StudentGUI().GUI();
						return ;
				}
				case "teacher" :{
					if(password.equals("teacher"))
						new TeacherGUI().GUI();
						return ;
				}
				case "manager" :{
					if(password.equals("manager"))
						new ManagerGUI().GUI();
						return ;
				}
				
			}
			JOptionPane.showMessageDialog(null, "√‹¬Î¥ÌŒÛ");
			return;
	}
	

}
