package package1;

public class Student {
	private String SID;
	private String name;
	private String deptment;
	private int age;
	private String Gender;
	
	public String getSID() {
		return SID;
	}
	
	public void setSID(String sID) {
		SID = sID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDeptment() {
		return deptment;
	}
	
	public void setDeptment(String deptment) {
		this.deptment = deptment;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getGender() {
		return Gender;
	}
	
	public void setGender(String gender) {
		Gender = gender;
	}
	
	public String toString() {
		return "Student:  "+SID+"---"+name+"---"+deptment+"---"+age+"---"+Gender;
	}	
		
}
