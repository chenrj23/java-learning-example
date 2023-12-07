package rj.daoes;

import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.ReturnGeneratedId;
import org.jfaster.mango.annotation.SQL;

import java.util.List;
import rj.classes.*;

/**
 * @author ash
 */
@DB
public interface ProjectDao {

    @SQL("insert into projects(name, projectState, operateState) values(:1, :2, :3)")
    public void insertProject(String name, String projectState, String operateState);

    @ReturnGeneratedId
    @SQL("insert into projects(name, projectState, operateState) " +
            "values(:1.name, :1.projectState, :1.operateState)")
    public int insertProject(Project project);

    @SQL("delete from projects where id=:1")
    public int deleteProject(int id);

    @SQL("update projects set name=:1.name, projectState=:1.projectState, operateState=:1.operateState " +
            "where id=:1.id")
    public int updateProject(Project project);

    @SQL("select name from projects where id = :1")
    public String getName(int id);

    @SQL("select id, name, projectState, operateState from projects where id = :1")
    public Project getProject(int id);

    @SQL("select id, name, projectState, operateState  from projects where id in (:1)")
    public List<Project> getProjectsInList(List<Integer> ids);
}