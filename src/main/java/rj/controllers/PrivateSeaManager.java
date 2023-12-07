package rj.controllers;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.jfaster.mango.jdbc.exception.DuplicateKeyException;
import java.util.List;
import rj.classes.Clue;
import rj.classes.Develop;
import rj.daoes.OpenSeaSingletonDao;
import rj.daoes.PrivateSeaDao;

public class PrivateSeaManager {
    private static final String configLocation = "applicationContext-simple.xml";
    private static final ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocation);
    private static final PrivateSeaDao privateSeaDao = ctx.getBean(PrivateSeaDao.class);
    private static final OpenSeaSingletonDao openSeaSingletonDao = ctx.getBean(OpenSeaSingletonDao.class);
    public static void put(Develop develop, Clue clue)  {
        try{
            privateSeaDao.insertPrivatesea(develop, clue);
            openSeaSingletonDao.deleteOpenSea(clue);
        }
        catch (DuplicateKeyException e){
            System.out.println("DuplicateKeyException" + e.getMessage());
            System.out.println(develop.getName() + "尝试捡入" + clue.getName() + "线索失败");
        }
    }
    public static List<Integer> getCluesId(Develop develop){
        return  privateSeaDao.getCluesId(develop);
    }
    public static void deleteClue(Develop develop, Clue clue){
        privateSeaDao.deleteClue(develop,clue);
        openSeaSingletonDao.insertOpenSea(clue);
    }
}