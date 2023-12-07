package rj.daoes;

import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.ReturnGeneratedId;
import org.jfaster.mango.annotation.SQL;
import org.springframework.stereotype.Component;
import rj.classes.Clue;
import rj.classes.Project;

import java.sql.Date;

@DB
public interface ClueDao {
    @ReturnGeneratedId
    @SQL("insert into clues(name, lastDevelop, lastDevelopDate, putDate, projectId) values(:1, :2, :3, :4, :5)")
    public int insertClue(String name, String lastDevelop, Date lastDevelopDate, Date putDate, int projectId);
    @ReturnGeneratedId
    @SQL("insert into clues(name, lastDevelopDate, putDate) values(:1, :2, :3)")
    public int insertClue(String name, Date lastDevelopDate, Date putDate);
    @ReturnGeneratedId
    @SQL("insert into clues(name, lastDevelop, lastDevelopDate, putDate, projectId) " +
            "values(:1.name, :1.lastDevelop, :1.lastDevelopDate, :1.putDate, :1.projectId)")
    public int insertClue(Clue clue);
    @SQL("select id, name, lastDevelop, lastDevelopDate, putDate, projectId from clues where id = :1")
    public Clue getClue(int id);
    @SQL("select projectId from clues where id = :1.id")
    public int getProjectId(Clue clue);
    @SQL("update clues set name=:1.name, lastDevelop=:1.lastDevelop, lastDevelopDate=:1.lastDevelopDate, " +
            "putDate=:1.putDate, projectId=:1.projectId where id=:1.id")
    public int updateClue(Clue clue);
}
