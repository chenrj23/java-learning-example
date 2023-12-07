package rj.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import rj.classes.Clue;
import rj.classes.Project;
import rj.daoes.ClueDao;
import rj.daoes.ProjectDao;

import java.sql.Date;
import java.time.LocalDate;

public class ClueManager {
    private static final String configLocation = "applicationContext-simple.xml";
    private static final ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocation);
    private static final ClueDao clueDao = ctx.getBean(ClueDao.class);
    private static final ProjectDao projectDao = ctx.getBean(ProjectDao.class);
    public static Clue createClue (String name){
        LocalDate currentLocalDate = LocalDate.now();
        Date currentDate = Date.valueOf(currentLocalDate);
        Clue myClue = new Clue();
        myClue.setName(name);
        myClue.setLastDevelopDate(currentDate);
        myClue.setPutDate(currentDate);
        myClue.setId(clueDao.insertClue(myClue.getName(), myClue.getLastDevelopDate(), myClue.getPutDate()));
        System.out.println(name + "线索创建");
        return myClue;
    }
    public static Clue getClueById (int id){
        return clueDao.getClue(id);
    }
    public static void  updateClue(Clue clue){
        clueDao.updateClue(clue);
    }
    public static Project getProject(Clue clue){
        int projectId = clueDao.getProjectId(clue);
        return projectDao.getProject(projectId);
    }
}
