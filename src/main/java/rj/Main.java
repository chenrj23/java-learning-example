package rj;

import rj.classes.*;
import rj.controllers.*;
import java.sql.Date;
import org.jfaster.mango.util.logging.MangoLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class Main {
	protected static final Logger mainLogger = LogManager.getLogger();
	static Marker mainTips = MarkerManager.getMarker("流程模拟信息");

	public static void main(String[] args) {
		MangoLogger.useLog4J2Logger();
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
		mainLogger.info(mainTips,"在线索放入公海前，公海线索有共" + OpenSeaSingletonManager.getAllCluesId().size() + "条");
		OpenSeaSingletonManager.put(clue1);
		OpenSeaSingletonManager.put(clue2);
		OpenSeaSingletonManager.put(clue3);
		OpenSeaSingletonManager.put(clue4);
		mainLogger.info(mainTips,"所有线索放入公海，公海线索有共" + OpenSeaSingletonManager.getAllCluesId().size() + "条");
		System.out.println();

		//创建两个开发
		mainLogger.info(mainTips,"创建2个开发");
		Develop myFirstDevelop = DevelopManger.createDevelop("张三");
		Develop mySecondDevelop = DevelopManger.createDevelop("李四");

		//张三捡入四个线索
		mainLogger.info(mainTips,"张三尝试捡入四个线索前，公海线索有" + OpenSeaSingletonManager.getAllCluesId().size() + "条");
		System.out.println();
		DevelopManger.putClue(myFirstDevelop,clue1);
		DevelopManger.putClue(myFirstDevelop,clue2);
		DevelopManger.putClue(myFirstDevelop,clue3);
		DevelopManger.putClue(myFirstDevelop,clue4);
		mainLogger.info(mainTips,"张三尝试捡入四个线索后，公海线索有" + OpenSeaSingletonManager.getAllCluesId().size() + "条");

		//从线索创建项目
		mainLogger.info(mainTips,"从线索创建项目");
		ProjectManger.createProject(clue1);
		ProjectManger.createProject(clue2);
		//同一个线索不能重复创建项目
		ProjectManger.createProject(clue1);

		//张三废弃项目
		mainLogger.info(mainTips,"张三废弃项目");
		DevelopManger.removeClue(myFirstDevelop,clue2);
		mainLogger.info(mainTips,"公海线索有" + OpenSeaSingletonManager.getAllCluesId().size() + "条");
		mainLogger.info(mainTips,"张三的私海线索有" + PrivateSeaManager.getCluesId(myFirstDevelop).size() + "条");


		DevelopManger.putClue(myFirstDevelop,clue1);
		clue1.setPutDate(Date.valueOf("2023-01-01"));
		clue4.setPutDate(Date.valueOf("2023-01-01"));
		ClueManager.updateClue(clue1);
		ClueManager.updateClue(clue4);
		mainLogger.info(mainTips,"上海虹桥机场 北京长城 调整开始持有线索为01月1日");
		mainLogger.info(mainTips,"进行180天检查");
		mainLogger.info(mainTips,"张三的私海线索有"+PrivateSeaManager.getCluesId(myFirstDevelop).size()+"条");
		mainLogger.info(mainTips,"张三的私海线索有"+PrivateSeaManager.getCluesId(myFirstDevelop));
		DevelopManger.checkState(myFirstDevelop);
		mainLogger.info(mainTips,"张三的私海线索有"+PrivateSeaManager.getCluesId(myFirstDevelop).size()+"条");
		System.out.println();
	}
}

