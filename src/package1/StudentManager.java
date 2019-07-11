package package1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class StudentManager {
	

	public static Connection getConnection() throws Exception {
		String url="jdbc:mysql://localhost:3306/StudentClass";
		String user="root";
		String pwd="123456";
		String driver="com.mysql.jdbc.Driver";
		Class.forName(driver);
		
		Connection conn=DriverManager.getConnection(url, user, pwd);
		
		//System.out.println("connection is"+conn);
		return conn;
		
	}
	
	public static boolean addStudent(String sid,String sname,String deptment,int age,String gender) {
		try {
			Connection conn=getConnection();
			PreparedStatement st=conn.prepareStatement("insert into student values(?,?,?,?,?)");
			
			st.setString(1, sid);
			st.setString(2, sname);
			st.setString(3, deptment);
			st.setInt(4, age);
			st.setString(5, gender);
			st.execute();
			conn.close();
			return true;	
			
		}catch (Exception e) {
			return false;
		}
	}
	
	
	public static boolean deleteStudent(String id) {
		try {
			Connection conn=getConnection();
			
			PreparedStatement st=conn.prepareStatement("delete student where SID=?");
			
			st.setString(1, id);
			st.execute();
			
			conn.close();
			return true;	
			
		}catch (Exception e) {
			return false;
		}
	}
	
	
	public static boolean updateStudent(String sid,String name,String deptment,int age,String gender) {
		try {
			Connection conn=getConnection();
			PreparedStatement st=conn.prepareStatement(
			"update student set SID=?,sname=?,deptment=?,age=?,gender=?");
			
			st.setString(1, sid);
			st.setString(2, name);
			st.setString(3, deptment);
			st.setInt(4, age);
			st.setString(5, gender);
			st.execute();
			conn.close();
			return true;	
			
		}catch (Exception e) {
			return false;
		}
	}
	
	public static Student getStudent(String sid) {
		Student student=null;
		try {
			Connection conn=getConnection();
			PreparedStatement st=conn.prepareStatement(
			"select * from student where SID=?");
			
			st.setString(1, sid);
			st.execute();
			ResultSet rs=st.getResultSet();
			if(rs.next()) {
				student=new Student();
				student.setSID(rs.getString("SID"));
				student.setName(rs.getString("sname"));
				student.setDeptment(rs.getString("deptment"));
				student.setAge(rs.getInt("age"));
				student.setGender(rs.getString("gender"));
			}

			conn.close();
	
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}
	
	
	public static ArrayList<Student> getAllStudents() {
		ArrayList<Student> students= new ArrayList<Student>();
		try {
			Connection conn=getConnection();
			PreparedStatement st=conn.prepareStatement(
			"select * from student");
			
			st.execute();
			ResultSet rs=st.getResultSet();
			while(rs.next()) {
				Student student=new Student();
				student.setSID(rs.getString("SID"));
				student.setName(rs.getString("sname"));
				student.setDeptment(rs.getString("deptment"));
				student.setAge(rs.getInt("age"));
				student.setGender(rs.getString("gender"));
				students.add(student);
			}

			conn.close();
	
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}
	
	public static HashMap<String,Integer> searchStudentScoreByID(String id) {
		HashMap<String,Integer> score=new HashMap<String,Integer>();
		try{
			Connection conn=getConnection();
			PreparedStatement st=conn.prepareStatement("select cname,score from "
					+ "selectcourse natural join course where SID=?");
			st.setString(1, id);
			
			st.execute();
			ResultSet rs=st.getResultSet();
			
			while(rs.next()) {
				score.put(rs.getString("cname"), rs.getInt("score"));
				//System.out.println(rs.getString("cname")+rs.getInt("score"));
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return score;
		
	}
	
	
	public static Vector<Vector<String>> searchStudentScoreCoureseByID(String id) {
		Vector<Vector<String>>  score=new Vector<Vector<String>>();
		try{
			Connection conn=getConnection();
			PreparedStatement st=conn.prepareStatement("select student.SID,sname,course.CID,cname,score from "
					+ "selectcourse,course,student where student.SID=? and selectcourse.SID="
					+ "student.SID and selectcourse.CID=course.CID");
			st.setString(1, id);
			
			st.execute();
			ResultSet rs=st.getResultSet();
			//st.close();
			
	
			while(rs.next()) {
				Vector<String>  scorei=new Vector<String>(5);
				
				scorei.add(rs.getString("SID"));
				scorei.add(rs.getString("sname"));
				scorei.add(rs.getString("CID"));
				scorei.add(rs.getString("cname"));
				scorei.add(String.valueOf(rs.getInt("score")));
			
				//System.out.println(rs.getString("SID")+rs.getString("sname")+
					//	rs.getString("CID")+rs.getString("cname")+rs.getInt("score"));
				score.add(scorei);

			}
			rs.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return score;
		
	}
	
	public static boolean changeByTeacher(Vector<Vector<String>> score,Vector<Vector<String>> prescore) {
		
		try{
			Connection conn=getConnection();
			
			for(int i=0;i<prescore.size();i++) {
				Vector<String> scorei=score.get(i);
				Vector<String> prescorei=prescore.get(i);
				String SID=scorei.get(0);
				String CID=scorei.get(2);
				if(SID==null||CID==null)
					continue;
				PreparedStatement st=conn.prepareStatement("update student set SID=?,sname=? where SID=?");
				st.setString(1, SID);
				st.setString(2, scorei.get(1));
				st.setString(3, prescorei.get(0));
				st.execute();
				//st.close();
				PreparedStatement st1=conn.prepareStatement("update course set CID=?,cname=? where CID=?");
				st1.setString(1, CID);
				st1.setString(2, scorei.get(3));
				st1.setString(3, prescorei.get(2));
				st1.execute();
				//st1.close();
				
				PreparedStatement st2=conn.prepareStatement("update selectcourse set SID=?,CID=?,score=? where SID=? and CID=?");
				st2.setString(1, SID);
				st2.setString(2, CID);
				st2.setString(3, scorei.get(4));
				st2.setString(4, prescorei.get(0));
				st2.setString(5, prescorei.get(2));
				st2.execute();
			}
			
			for(int i=score.size();i>prescore.size();i--) {
				
				Vector<String> scorei=score.get(i-1);
				String SID=scorei.get(0);
				String CID=scorei.get(2);
				if(SID==null||CID==null)
					continue;
				PreparedStatement st=conn.prepareStatement("insert into student values(?,?,null,null,null)");
				st.setString(1, SID);
				st.setString(2, scorei.get(1));
				st.execute();
		
				PreparedStatement st1=conn.prepareStatement("insert into course values(?,14001,?,null,null)");
				st1.setString(1, CID);
				st1.setString(2, scorei.get(3));
				st1.execute();
					
				PreparedStatement st2=conn.prepareStatement("insert into selectcourse values(?,?,?)");
				st2.setString(1, SID);
				st2.setString(2, CID);
				st2.setString(3, scorei.get(4));
				st2.execute();
				
			}
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return true;
		
	}

}
