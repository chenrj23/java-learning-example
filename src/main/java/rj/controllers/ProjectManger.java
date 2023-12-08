package rj.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;
import rj.classes.Clue;
import rj.classes.Develop;
import rj.classes.Project;
import rj.daoes.ProjectDao;

public class ProjectManger {
    private static final Logger logger = LogManager.getLogger(ProjectManger.class);
    private static final String configLocation = "applicationContext-simple.xml";
    private static final ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocation);
    private static final ProjectDao projectDao = ctx.getBean(ProjectDao.class);
    public static void createProject(Clue clue){
        logger.info(clue.getName() + "线索尝试建立项目");
        if(clue.getProjectId() == 0){
            Project project = new Project(clue.getName());
            int projectId = projectDao.insertProject(project);
            clue.setProjectId(projectId);
            ClueManager.updateClue(clue);
            logger.info(clue.getName() + "线索尝试建立项目成功");
        }else{
            logger.info(clue.getName() + "线索已创建项目，不可以重复创建");
        };
    }
    public static void removeProject(Develop develop, Clue clue){
        List<Integer> clueIdList = PrivateSeaManager.getCluesId(develop);
        if(clueIdList.contains(clue.getId())) {
            PrivateSeaManager.deleteClue(develop, clue);
            OpenSeaSingletonManager.put(clue);
        }else{
            logger.info(clue.getName() + "线索不存在私海");
        }

    }
}
