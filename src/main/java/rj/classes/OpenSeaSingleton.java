package rj.classes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import rj.daoes.OpenSeaSingletonDao;

//公海类
public class OpenSeaSingleton {
   String configLocation = "applicationContext-simple.xml";
   ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocation);
   OpenSeaSingletonDao openSeaSingletonDao = ctx.getBean(OpenSeaSingletonDao.class);
   private static OpenSeaSingleton instance = new OpenSeaSingleton();

   private OpenSeaSingleton(){}

   public static OpenSeaSingleton getInstance(){
      return instance;
   }

}