package rj.classes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import rj.daoes.ClueDao;

import java.sql.Date;
import java.time.LocalDate;

//线索类
public class Clue {
	private int id;
	private String name;
	private  int projectId;
	private String lastDevelop;
	private Date lastDevelopDate;
	private Date putDate;

	public int getId(){
		return id;
	};
	public void setId(int id) {
		this.id = id;
	}
	public String getName(){
		return name;
	};
	public void  setName(String name) {
		this.name = name;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getLastDevelop() {
		return lastDevelop;
	}
	public void setLastDevelop(String lastDevelop) {
		this.lastDevelop = lastDevelop;
	}
	public Date getLastDevelopDate() {
		return lastDevelopDate;
	}
	public void setLastDevelopDate(Date lastDevelopDate) {this.lastDevelopDate = lastDevelopDate; }
	public Date getPutDate() {
		return putDate;
	}
	public void setPutDate(Date putDate) {
		this.putDate = putDate;
	}

}