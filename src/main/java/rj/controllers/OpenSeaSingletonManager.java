package rj.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import rj.classes.Clue;
import rj.classes.Develop;
import rj.daoes.OpenSeaSingletonDao;

import java.util.List;

public class OpenSeaSingletonManager {
    private static final String configLocation = "applicationContext-simple.xml";
    private static final ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocation);
    private static final OpenSeaSingletonDao openSeaSingletonDao = ctx.getBean(OpenSeaSingletonDao.class);
    public static void put(Clue clue) {
        int clueCount = openSeaSingletonDao.countCluesById(clue);
        if(clueCount != 0) {
            System.out.println(clue.getName() + "线索已存在公海");
        }else{
            openSeaSingletonDao.insertOpenSea(clue);
            System.out.println(clue.getName() + "线索放入公海");
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
            System.out.println(clue.getName() + "线索不存在公海");
        }
    }
}
