package net.cedu.payment;

import java.util.List;

import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.biz.finance.PendingFeePaymentBiz;
import net.cedu.common.enums.CodeEnum;
import net.cedu.common.enums.UserEnum;
import net.cedu.entity.finance.PendingFeePayment;
import net.cedu.quartz.Task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 定时生成待缴费记录
 * @author Jack
 *
 */
@Component
public class PaymentTask {
	
	//*******************批量生成**********************//
	@Autowired
	private PendingFeePaymentBiz pendingFeePaymentBiz;//待缴费单业务接口
	
	@Autowired
	private BuildCodeBiz buildCodeBiz;//code生成业务接口
	
	
	private final Log log = LogFactory.getLog(Task.class);
	
	public void work() 
	{
		try
		{
			List<PendingFeePayment> pendingList=this.pendingFeePaymentBiz.findAddPendingFeePaymentByAcademyIdFeeSubjectIdFortask();
			if(pendingList!=null && pendingList.size()>0)
			{
				for(PendingFeePayment pending:pendingList)
				{
					pending.setCode(buildCodeBiz.getCodes(CodeEnum.PendingFeePaymentTB.getCode(),CodeEnum.PendingFeePaymentPrefix.getCode()));
					pending.setCreatorId(UserEnum.Root.value());
					pending.setUpdaterId(UserEnum.Root.value());
				}
				pendingFeePaymentBiz.addBatchPendingFeePayment(pendingList);
			}
			log.info("Three in the morning every day tasks");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
}
