package net.cedu.quartz;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.cedu.biz.base.TaskBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.properties.Config;
import net.cedu.common.servlet.SingletonServletContext;
import net.cedu.entity.base.UserTask;
import net.cedu.init.dao.InitCacheDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 定时任务完成
 * 
 * @author yangdongdong
 * 
 */

@Component
public class Task {
	@Autowired
	private InitCacheDao initCacheDao;
	@Autowired
	private TaskBiz taskBiz;
	
	private final Log log = LogFactory.getLog(Task.class);
	
	public void work() {
		//1.获取所有正在进行的下载任务
		UserTask tempTask = new UserTask();
		tempTask.setStatus(Constants.EXPORT_EXCEL_STATUS_BEING);
		//设置下载任务超时时间(小时)
		int timeout = 3;//如果配置文件异常则默认为3小时
		if(Config.getProperty("export.timeout")!=null)
		{
			 try {
				timeout = Config.getIntProperty("export.timeout");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			List<UserTask> utList = taskBiz.findUserTaskByEntity(tempTask);
			if(utList!=null && utList.size()>0)
			{
				for(UserTask task : utList)
				{
					//1.1.循环获取path得到绝对路径判断   绝对路径+.zip,判断文件是否存在；如果存在说明导出已完成，更新当前任务为已完成
					String path = Config.getProperty("export.excel.tmp")+ task.getPath();
					File file = new File(SingletonServletContext.newInstance().getRealPath(path).trim()+".zip");
					//如果文件存在则更新为 已完成
					if (file.isFile()) {
						//执行更新操作(已完成)
						task.setStatus(Constants.EXPORT_EXCEL_STATUS_FINISH);
						//完成时间
						task.setCompleteTime(new Date());
						taskBiz.updateTask(task);
					}
					//如果文件不存在则判断时间
					else
					{
						int hours = DateUtil.daysOfTwo(task.getCreateTime(), DateUtil.getNow(), Calendar.HOUR);
						//超时
						if(hours>=timeout)
						{
							//执行更新操作(异常)
							task.setStatus(Constants.EXPORT_EXCEL_STATUS_FAILURE);
							taskBiz.updateTask(task);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("Quartz的任务调度！！！");
		log.info("10s tasks");
	}
}
