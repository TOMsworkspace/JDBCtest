package package1;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class ManagerGUI {
	
	
	private JTextArea text=new JTextArea(10,35);
	private JTextArea result=new JTextArea(10,35);
	private int inputbegin=0;
	
	public void GUI() {
		JFrame GUI=new JFrame("ManagerForm");
		Font font=new Font("TimesRoman",Font.BOLD,15);
		JScrollPane ptext = new JScrollPane(text);
		JScrollPane presult = new JScrollPane(result);
		////
		GUI.setFont(font);
		GUI.setSize(500,580);

		JButton exit=new JButton("ÍË³ö");

		text.setFont(font);
		result.setFont(font);
		text.setLineWrap(true);
		result.setLineWrap(true);
		exit.setFont(font);
		
		inputbegin=dos();
		
		FlowLayout layout=new FlowLayout(FlowLayout.CENTER);
		//layout.setAlignment(GridL);
		layout.setHgap(20);
		//layout.setVgap(150);
		GUI.add(ptext);
		GUI.add(presult);

		GUI.add(exit);		

		//layout.setVgap(150);
		GUI.setLayout(layout);
		GUI.setVisible(true);
		text.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			    //result.append(" hahahaha");
				try {
					DefaultCaret caret = (DefaultCaret) text.getCaret();    
					caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);  
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						String sql=text.getText(inputbegin, text.getDocument().getLength()-inputbegin);
						//System.out.println(sql);
						//ResultSet rs=null;
						Connection conn=StudentManager.getConnection();
						PreparedStatement st=conn.prepareStatement(sql);
						
						st.execute();
					
						ResultSet rs=st.getResultSet();
						//ResultSet rs=StudentManager.exesql(sql);
						
						while(rs.next()) {
							ResultSetMetaData resultdata=rs.getMetaData();
							int col=resultdata.getColumnCount();
							for(int j=1;j<=col;j++) {
								switch (resultdata.getColumnType(j))                     //translate the column of table type to java type then write to vector  
			                      {
				                      case Types.VARCHAR:{
				                    	  result.append("| "+rs.getString(resultdata.getColumnName(j))+" |");
				                    	  break;
				                      }
				                      case Types.CHAR:{
				                    	  result.append("| "+rs.getString(resultdata.getColumnName(j))+" |");
				                    	  break;
				                      }
				                      case Types.INTEGER:{
				                    	  result.append("|"+rs.getInt(resultdata.getColumnName(j))+"|");
				                    	  break;
				                      }
				                      case Types.DOUBLE:{
				                    	  result.append("|"+rs.getDouble(resultdata.getColumnName(j))+"|");
				                    	  break;
				                      }
				                      case Types.FLOAT:{
				                    	  result.append("|"+rs.getFloat(resultdata.getColumnName(j))+"|");
				                          break;
				                      }
				                      case Types.DECIMAL:{
				                    	  result.append("|"+rs.getDouble(resultdata.getColumnName(j))+"|");
				                          break;
				                      }
				
				                      default:
				                    	  result.append("|"+"null"+"|");
			                      }
								//result.append("|"+rs.rowgetNString(i)+"|");
							}
							result.append("\n");
							// result.paintImmediately(result.getBounds());
						}
						
	                    //result.append();
	                    result.paintImmediately(result.getBounds());
	                    
	                    text.append("\n");
	                    inputbegin=dos();
	                   // text.setSelectionStart(text.getDocument().getLength());
	                    
	                }
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					result.append("You have an error in your SQL syntax;\n");
					text.append("\n");
					inputbegin=dos();
					//e1.printStackTrace();
				}
				
			}
		
		});
		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				GUI.setVisible(false);
				GUI.dispose();
			}

		});
	}
	
	private int dos() {
		///ÃüÁîÐÐ
		text.append("@root->");
		text.setCaretPosition(text.getDocument().getLength());
		return text.getDocument().getLength();
	}
	
}
