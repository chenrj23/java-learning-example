package rj.classes;
//项目类
public class Project {
	private final String name;
	String projectState = "评审中";
	String operateState = "待筹开";
	public Project(String name){
		this.name = name;
		System.out.println(name+ "项目建立成功");
	}

	public String toString() {
		return name;
	}

	public void over(){
		projectState = "已废弃";
	}
}