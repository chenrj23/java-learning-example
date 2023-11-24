package rj.classes;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
//开发BD类
public class Develop {
	private final String name;
	private final PrivateSea privateSea;
	public Develop(String name){
		System.out.println("开发" + name + "被创建");
		this.name = name;
		privateSea = new PrivateSea();
	}

	public PrivateSea getPrivateSea(){
		return privateSea;
	}
	public void putClue(Clue clue){
		System.out.println(name + "尝试捡入" + clue.getName() + "线索");

		if (clue.lastDevelopDate == null) {
			privateSea.put(clue);
		}else {
			LocalDate nowDate = LocalDate.now();
			long daysDifference = ChronoUnit.DAYS.between(clue.lastDevelopDate,nowDate);
			//开发15天内不能重复捡入线索，需要等15天
			if (clue.lastDevelop.equals(name) && daysDifference < 15){
				System.out.println(clue.getName() + "线索距离BD上次掉出线索差 "+daysDifference+"天，无法捡入");
			}else{
				privateSea.put(clue);
			}

		}

	}
	
	public void removeClue(Clue clue){
		System.out.println(name + "开发捡出" + clue.getName() + "线索");
		privateSea.remove(clue);
		clue.setLastDevelop(name);
	}
	
	public void checkState(){

		LocalDate nowDate = LocalDate.now();
		for (Clue clue: privateSea.getClueMap().values()) {
			long daysDifference = ChronoUnit.DAYS.between(clue.putDate,nowDate);
			if(daysDifference >= 180){
				System.out.println(clue.getName() + "线索距离BD捡入满 "+daysDifference+"天");
				Project clueProject = clue.getProject();
				if(clueProject == null){
					System.out.println(clue.getName() + "线索BD未签约，捡出至公海");
					removeClue(clue);
				}else{
					if(clueProject.operateState.equals("待筹开") && clueProject.projectState.equals("评审中")){
						System.out.println(clueProject + "项目超期未推进，，捡出至公海");
						removeClue(clue);
					}
					else {
						return;
					};
				};
			}else{
				System.out.println(clue.getName() + "线索距离BD捡入不满180天，已捡入 "+daysDifference+"天");
			}
		}

	}

}
