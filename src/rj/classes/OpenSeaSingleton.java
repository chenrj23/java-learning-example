package rj.classes;
import java.util.HashMap;
//公海类
public class OpenSeaSingleton {
   private static OpenSeaSingleton instance = new OpenSeaSingleton();
   private HashMap<Integer, Clue> clueMap = new HashMap<Integer, Clue>();

   private OpenSeaSingleton(){}

   public static OpenSeaSingleton getInstance(){
      return instance;
   }

   public HashMap getClueMap(){
	   return clueMap;
   }

   public void put(Clue clue){
	   clueMap.put(clue.getId(),clue);
	   System.out.println(clue.getName() + "线索放入公海");
   }
   
   public void remove(Clue clue){
        if(clueMap.containsKey(clue.getId())) {
			clueMap.remove(clue.getId());		
        }else{
		    System.out.println(clue.getName() + "线索不存在公海");	
		}
   }

   public int clueNumber(){
	   return clueMap.size();
   }
}