package package1;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class StudentGUI {
	
	
	
	public void GUI() {
		JFrame GUI=new JFrame("StudentForm");
		Font font=new Font("TimesRoman",Font.BOLD,15);
		
		////
		JTextField sid=new JTextField("请输入学号",20);
		DefaultTableModel scoretable=new DefaultTableModel(0,2);

		JTable scoreTable=new JTable(scoretable);
		JScrollPane SscoreTable=new JScrollPane(scoreTable);
	
		
		GUI.setFont(font);
		GUI.setSize(500,580);
		JLabel accl=new JLabel("学号");
		JButton search=new JButton("查询");
		JButton exit=new JButton("退出");

		scoreTable.getColumn("A").setHeaderValue("CourseName");
		scoreTable.getColumn("B").setHeaderValue("Score");


		scoreTable.getTableHeader().setFont(font);
		scoreTable.setFont(font);
        scoreTable.setRowHeight(30);
		
		
		
		
		accl.setFont(font);
		SscoreTable.setFont(font);
		//scoreTable
		sid.setFont(font);
		search.setFont(font);
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
		GUI.add(exit);
		

		//layout.setVgap(150);
		GUI.setLayout(layout);
		GUI.setVisible(true);
		
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				scoretable.setRowCount(0);
				scoreTable.revalidate();
				HashMap<String,Integer> result;
				
				String id=sid.getText();
				result=StudentManager.searchStudentScoreByID(id);
				Set<String> key=result.keySet();
				for(String s:key) {
					Vector<String> scorelist=new Vector<String>();
					scorelist.add(s);
					scorelist.add(result.get(s).toString());
					//System.out.println(s+result.get(s).toString());
					scoretable.addRow(scorelist);
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
