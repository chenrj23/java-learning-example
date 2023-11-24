package rj;
import rj.classes.*;  
import java.time.LocalDate;

public class Main {
   public static void main(String[] args) {
		OpenSeaSingleton openSea = OpenSeaSingleton.getInstance();
		System.out.println("公海初始化"); 
		
		//创建四个线索
		Clue myFirstClue = new Clue("上海顾村公园");
		Clue mySecondClue = new Clue("上海虹桥机场");
		Clue myThirdClue = new Clue("浙江绍兴");
		Clue myFourthClue = new Clue("南京雨花台");
		
		//第一个线索的最后开发在私海的时间为11月1日，开发为张三
		myFirstClue.lastDevelopDate = LocalDate.parse("2023-11-01");
		myFirstClue.lastDevelop = "张三";
		System.out.println("上海顾村公园线索 调整最后开发为张三，最后私海持有线索为11月1日"); 
		
		
		//线索放入公海
		System.out.println("在线索放入公海前，公海线索有共"+openSea.clueNumber()+"条");
		openSea.put(myFirstClue);
		openSea.put(mySecondClue);
		openSea.put(myThirdClue);
		openSea.put(myFourthClue);
		System.out.println("所有线索放入公海，公海线索有共"+openSea.clueNumber()+"条");

		//创建两个开发
		Develop myFirstDevelop = new Develop("张三");
		Develop mySecondDevelop = new Develop("李四");

		//张三捡入三个线索
		myFirstDevelop.putClue(myFirstClue);
		myFirstDevelop.putClue(mySecondClue);
		myFirstDevelop.putClue(myThirdClue);
		System.out.println("张三尝试捡入三个线索后，公海线索有"+openSea.clueNumber()+"条");
	    System.out.println("张三尝试捡入三个线索后，私海线索有"+myFirstDevelop.getPrivateSea().clueNumber()+"条");
		
		//从线索创建项目
		myFirstClue.createProject();
		mySecondClue.createProject();
		//同一个线索不能重复创建项目
		myFirstClue.createProject();


		//张三废弃项目
		myFirstDevelop.removeClue(mySecondClue);
	    System.out.println("公海线索有"+openSea.clueNumber()+"条");
	    System.out.println("张三的私海线索有"+myFirstDevelop.getPrivateSea().clueNumber()+"条");

	    myFirstDevelop.putClue(myFourthClue);
	    myFourthClue.putDate = LocalDate.parse("2023-01-01");
	    System.out.println("南京雨花台 调整开始持有线索为01月1日");
	    System.out.println("进行180天检查");
	    System.out.println("张三的私海线索有"+myFirstDevelop.getPrivateSea().clueNumber()+"条");
		myFirstDevelop.checkState();
	    System.out.println("张三的私海线索有"+myFirstDevelop.getPrivateSea().clueNumber()+"条");
   }
}