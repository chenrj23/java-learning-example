package rj.classes;
//项目类
public class Project {
	private int id;
	private String name;
	private String projectState = "评审中";
	private String operateState = "待筹开";
	public Project(){}
	public Project(String name){
		this.name = name;
	}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getProjectState() {
        return projectState;
    }
    public void setProjectState(String projectState) {
        this.projectState = projectState;
    }
    public String getOperateState() {
        return operateState;
    }
    public void setOperateState(String operateState) {
        this.operateState = operateState;
    }
	public String toString() {
		return name;
	}
}