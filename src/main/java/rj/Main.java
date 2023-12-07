package rj;


import rj.classes.*;
import rj.controllers.*;
import java.sql.Date;

public class Main {
   public static void main(String[] args) {
	   //创建四个线索
	   Clue clue1 = ClueManager.createClue("上海虹桥机场");
	   Clue clue2 = ClueManager.createClue("浙江绍兴");
	   Clue clue3 = ClueManager.createClue("南京雨花台");
	   Clue clue4 = ClueManager.createClue("北京长城");

	   //第三个线索的最后开发在私海的时间为11月1日，开发为张三
	   clue3.setLastDevelopDate(Date.valueOf("2023-12-01"));
	   clue3.setLastDevelop("张三");
	   ClueManager.updateClue(clue3);

	   //线索放入公海
	   System.out.println("在线索放入公海前，公海线索有共" + OpenSeaSingletonManager.getAllCluesId().size() + "条");
	   OpenSeaSingletonManager.put(clue1);
	   OpenSeaSingletonManager.put(clue2);
	   OpenSeaSingletonManager.put(clue3);
	   OpenSeaSingletonManager.put(clue4);
	   System.out.println("所有线索放入公海，公海线索有共" + OpenSeaSingletonManager.getAllCluesId().size() + "条");

	   //创建两个开发
	   Develop myFirstDevelop = DevelopManger.createDevelop("张三");
	   Develop mySecondDevelop = DevelopManger.createDevelop("李四");

	   //张三捡入四个线索
	   System.out.println("张三尝试捡入四个线索前，公海线索有" + OpenSeaSingletonManager.getAllCluesId().size() + "条");
	   DevelopManger.putClue(myFirstDevelop,clue1);
	   DevelopManger.putClue(myFirstDevelop,clue2);
	   DevelopManger.putClue(myFirstDevelop,clue3);
	   DevelopManger.putClue(myFirstDevelop,clue4);
	   System.out.println("张三尝试捡入四个线索后，公海线索有" + OpenSeaSingletonManager.getAllCluesId().size() + "条");

	   //从线索创建项目
	   ProjectManger.createProject(clue1);
	   ProjectManger.createProject(clue2);
	   //同一个线索不能重复创建项目
	   ProjectManger.createProject(clue1);

	   //张三废弃项目
	   DevelopManger.removeClue(myFirstDevelop,clue2);
	   System.out.println("公海线索有" + OpenSeaSingletonManager.getAllCluesId().size() + "条");
	   System.out.println("张三的私海线索有" + PrivateSeaManager.getCluesId(myFirstDevelop).size() + "条");

	   DevelopManger.putClue(myFirstDevelop,clue1);
	   clue1.setPutDate(Date.valueOf("2023-01-01"));
	   clue4.setPutDate(Date.valueOf("2023-01-01"));
	   ClueManager.updateClue(clue1);
	   ClueManager.updateClue(clue4);
	   System.out.println("上海虹桥机场 北京长城 调整开始持有线索为01月1日");
	   System.out.println("进行180天检查");
	   System.out.println("张三的私海线索有"+PrivateSeaManager.getCluesId(myFirstDevelop).size()+"条");
		   System.out.println("张三的私海线索有"+PrivateSeaManager.getCluesId(myFirstDevelop));
	   DevelopManger.checkState(myFirstDevelop);
	   System.out.println("张三的私海线索有"+PrivateSeaManager.getCluesId(myFirstDevelop).size()+"条");
   }
}

