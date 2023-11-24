package rj.classes;
import java.util.HashMap;
//私海类
public class PrivateSea {
	static HashMap<Integer, Clue> clueMap = new HashMap<Integer, Clue>();
	private OpenSeaSingleton openSeaSingleton = OpenSeaSingleton.getInstance();

	final static int maxNumber = 1;

	public static Clue get(int id){
		return clueMap.get(id);
	}
	
	public void put(Clue clue){
		if(clueMap.size() >= maxNumber){
			System.out.println(clue.getName() + "超过最大线索限制，无法添加线索");
		}else{
			//System.out.println(clueMap.size()); 
			openSeaSingleton.remove(clue);
			clueMap.put(clue.getId(),clue);
			System.out.println(clue.getName() + "线索捡入成功");
		}
	}	
	
	public void remove(Clue clue){
        if(clueMap.containsKey(clue.getId())) {
			clueMap.remove(clue.getId());
			openSeaSingleton.put(clue);

        }else{
		    System.out.println(clue.getName() + "线索不存在私海");	
		}

	}

	public HashMap<Integer, Clue> getClueMap() {
		return clueMap;
	}

	public int clueNumber(){
		return clueMap.size();
	}
}