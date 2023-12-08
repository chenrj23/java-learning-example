package rj.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import rj.classes.Clue;
import rj.classes.Develop;
import rj.daoes.OpenSeaSingletonDao;

import java.util.List;

public class OpenSeaSingletonManager {
    private static final Logger logger = LogManager.getLogger(OpenSeaSingletonManager.class);
    private static final String configLocation = "applicationContext-simple.xml";
    private static final ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocation);
    private static final OpenSeaSingletonDao openSeaSingletonDao = ctx.getBean(OpenSeaSingletonDao.class);
    public static void put(Clue clue) {
        int clueCount = openSeaSingletonDao.countCluesById(clue);
        if(clueCount != 0) {
            logger.info(clue.getName() + "线索已存在公海");
        }else{
            openSeaSingletonDao.insertOpenSea(clue);
            logger.info(clue.getName() + "线索放入公海");
        }
    }
    public static List<Integer> getAllCluesId(){
        return  openSeaSingletonDao.getAllCluesId();
    }
    public static void remove(Clue clue){
        int clueCount = openSeaSingletonDao.countCluesById(clue);
        if( clueCount != 0) {
            openSeaSingletonDao.deleteOpenSea(clue);;
        }else{
            logger.info(clue.getName() + "线索不存在公海");
        }
    }
}
