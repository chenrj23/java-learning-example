package rj.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jfaster.mango.jdbc.exception.DuplicateKeyException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import rj.classes.Clue;
import rj.classes.Develop;
import rj.classes.Project;
import rj.daoes.DevelopDao;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class DevelopManger {
    private static final Logger logger = LogManager.getLogger(DevelopManger.class);
    private static final String configLocation = "applicationContext-simple.xml";
    private static final ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocation);
    private static final DevelopDao developDao = ctx.getBean(DevelopDao.class);

    public static Develop createDevelop(String name) {
        Develop myDevelop = new Develop();
        myDevelop.setName(name);
        myDevelop.setId(developDao.insertDevelop(myDevelop));
        logger.info(name + "开发 id" + myDevelop.getId() + "被创建");
        return myDevelop;
    }

    public static void putClue(Develop develop, Clue clue) {
        logger.info(develop.getName() + "尝试捡入" + clue.getName() + "线索");
        if (clue.getLastDevelopDate() == null) {
                PrivateSeaManager.put(develop, clue);
        } else {
            LocalDate nowLocalDate = LocalDate.now();
            LocalDate lastDevelopLocalDate = clue.getLastDevelopDate().toLocalDate();
            long daysDifference = ChronoUnit.DAYS.between(lastDevelopLocalDate, nowLocalDate);
            boolean developAreEqual = Objects.equals(clue.getLastDevelop(), develop.getName());
            //开发15天内不能重复捡入线索，需要等15天
            if (developAreEqual && daysDifference < 15) {
                logger.info(clue.getName() + "线索距离" + develop.getName() + "BD上次掉出线索差 " + daysDifference + "天，无法捡入");
            } else {
                PrivateSeaManager.put(develop, clue);
            }
        }
    }

    public static void removeClue(Develop develop, Clue clue) {
        logger.info(develop.getName() + "开发捡出" + clue.getName() + "线索");
        PrivateSeaManager.deleteClue(develop, clue);
        clue.setLastDevelop(develop.getName());
        LocalDate currentLocalDate = LocalDate.now();
        Date currentDate = Date.valueOf(currentLocalDate);
        clue.setLastDevelopDate(currentDate);
    }

    public static void checkState(Develop develop) {
        LocalDate nowLocalDate = LocalDate.now();
        for (int clueId : PrivateSeaManager.getCluesId(develop)) {
            Clue clue = ClueManager.getClueById(clueId);
            LocalDate putLocalDate = clue.getPutDate().toLocalDate();
            long daysDifference = ChronoUnit.DAYS.between(putLocalDate, nowLocalDate);
            if (daysDifference >= 180) {
                logger.info(clue.getName() + "线索距离BD捡入满 " + daysDifference + "天");
                Project clueProject = ClueManager.getProject(clue);
                if (clueProject == null) {
                    logger.info(clue.getName() + "线索BD未签约，捡出至公海");
                    DevelopManger.removeClue(develop, clue);
                } else {
                    boolean operateStateAreEquals = Objects.equals(clueProject.getOperateState(),"待筹开");
                    boolean projectStateAreEquals = Objects.equals(clueProject.getProjectState(),"评审中");
                    if (operateStateAreEquals && projectStateAreEquals) {
                        logger.info(clueProject + "项目超期未推进，，捡出至公海");
                        DevelopManger.removeClue(develop, clue);
                    } else {
                        return;
                    }
                    ;
                }
                ;
            } else {
                logger.info(clue.getName() + "线索距离BD捡入不满180天，已捡入 " + daysDifference + "天");
            }
        }
    }
}