package net.cedu.common.services;

import com.caucho.hessian.client.HessianProxyFactory;


/**
 * 
 * @功能：HessianProxyFactory单例
 *
 * @作者： 杨栋栋
 * @作成时间：2011-11-17 下午05:47:09
 *
 * @修改者：杨栋栋
 * @修改内容：补注释
 * @修改时间：2011-11-17 下午05:47:09
 *
 */
public class HessianProxyFactorySingleton {

	private static HessianProxyFactorySingleton singleton = null;
	private static HessianProxyFactory factory = null;

	private HessianProxyFactorySingleton() {
		factory = new HessianProxyFactory();
	}

	public static HessianProxyFactory newInstance() {
		if (singleton == null) {
			singleton = new HessianProxyFactorySingleton();
		}
		return factory;
	}
}
