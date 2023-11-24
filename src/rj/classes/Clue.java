package rj.classes;
import java.time.LocalDate;

//线索类
public class Clue {
	private static int nextId  = 0;
	private final int id;
	private final String name;
	private Project project;	
	//设置为public，方便测试的时候修改日期时间。
	public String lastDevelop;
	public LocalDate lastDevelopDate;
	public LocalDate putDate;
	
	public Clue(String name) {
		this.id = nextId++;
		this.name = name;
		this.lastDevelopDate = LocalDate.now();
		this.putDate = LocalDate.now();
		System.out.println(name + "线索创建"); 
		
	};
	
	public int getId(){
		return id;
	};
	
	public String getName(){
		return name;
	};
	
	public void createProject(){
		System.out.println(this.name + "线索尝试建立项目");
		if(this.project == null){
			this.project = new Project(name);
		}else{
			System.out.println(this.name + "线索已创建项目，不可以重复创建"); 
		};
	}

	public Project getProject(){
		return project;
	}
	
	public void removeProject(){
		if(this.project == null){
			System.out.println(this.name + "线索已无项目，不可以删除"); 
		}else{
			this.project.over();
			this.project = null;
		};
	}
//	设置线索最后一个开发，和捡出的时间
	public void setLastDevelop(String name){
		lastDevelop = name;
		lastDevelopDate = LocalDate.now();
		
	}


}