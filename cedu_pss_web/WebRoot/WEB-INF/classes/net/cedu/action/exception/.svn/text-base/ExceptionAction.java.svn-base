package net.cedu.action.exception;

import net.cedu.action.BaseAction;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 异常捕获Action
 * 
 * @author yangdongdong
 * 
 */
public class ExceptionAction extends BaseAction implements MethodInterceptor {

	public Object invoke(MethodInvocation arg0) throws Throwable {
		Object returnValue = null;
		try {
			
			returnValue = arg0.proceed();
			System.out.println("执行方法");
		} catch (Exception e) {
			System.out.println("error");
		}
		return returnValue;
	}

}
