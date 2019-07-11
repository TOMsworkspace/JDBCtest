package package1;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TeacherGUI {
	
	public void GUI() {
		JFrame GUI=new JFrame("TeacherForm");
		Font font=new Font("TimesRoman",Font.BOLD,15);
		
		////
		JTextField sid=new JTextField("请输入学号",20);
		DefaultTableModel scoretable=new DefaultTableModel(0,5);

		JTable scoreTable=new JTable(scoretable);
		JScrollPane SscoreTable=new JScrollPane(scoreTable);
	
		
		GUI.setFont(font);
		GUI.setSize(500,580);
		JLabel accl=new JLabel("学号");
		JButton search=new JButton("查询");
		JButton insert=new JButton("新增行");
		JButton port=new JButton("提交更改");
		JButton exit=new JButton("退出");

		scoreTable.getColumn("A").setHeaderValue("StundentID");
		scoreTable.getColumn("B").setHeaderValue("Student");
		scoreTable.getColumn("C").setHeaderValue("CourseID");
		scoreTable.getColumn("D").setHeaderValue("Course");
		scoreTable.getColumn("E").setHeaderValue("Score");

		scoreTable.getTableHeader().setFont(font);
		scoreTable.setFont(font);
        scoreTable.setRowHeight(30);
		
		
		
		
		accl.setFont(font);
		SscoreTable.setFont(font);
		sid.setFont(font);
		search.setFont(font);
		insert.setFont(font);
		port.setFont(font);
		exit.setFont(font);
		
		FlowLayout layout=new FlowLayout(FlowLayout.CENTER);
		//layout.setAlignment(GridL);
		layout.setHgap(20);
		//layout.setVgap(150);
		
		
		GUI.add(accl);
		//layout.setAlignOnBaseline(true);
		GUI.add(sid);
		GUI.add(search);
		GUI.add(SscoreTable);
		GUI.add(insert);
		GUI.add(port);
		GUI.add(exit);
		

		//layout.setVgap(150);
		GUI.setLayout(layout);
		GUI.setVisible(true);
		
		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				Vector<String> s=new Vector<String>();
				
				scoretable.addRow(s);
				scoreTable.revalidate();
			}

		});
		
		port.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				Vector<Vector<String>>  prescore=new Vector<Vector<String>>();
				Vector<Vector<String>>  score=new Vector<Vector<String>>();
				for(int i=0;i<scoreTable.getRowCount();i++) {
					Vector<String>  scorei=new Vector<String>(5);
					for(int j=0;j<scoreTable.getColumnCount();j++) {
						scorei.add((String) scoreTable.getValueAt(i, j));
					}
					score.add(scorei);
				}
				
				String id=sid.getText();
				prescore=StudentManager.searchStudentScoreCoureseByID(id);
				
				StudentManager.changeByTeacher(score,prescore);
				//System.out.println(flag);
				
				JOptionPane.showMessageDialog(null, "修改完成");
			}

		});
		
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				scoretable.setRowCount(0);
				scoreTable.revalidate();
				
				Vector<Vector<String>>  score;
							
				String id=sid.getText();
				id=id.isEmpty() ? "*" :id;
				score=StudentManager.searchStudentScoreCoureseByID(id);
				
				for(Vector<String> s:score) {
					
					scoretable.addRow(s);
					scoreTable.revalidate();					
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

}
