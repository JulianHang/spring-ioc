package JulianHang.spirng_ioc.context.support;

import JulianHang.spirng_ioc.beans.factory.support.AbstractBeanFactory;
import JulianHang.spirng_ioc.beans.factory.support.BeanFactory;
import JulianHang.spirng_ioc.beans.factory.support.DefaultListableBeanFactory;

/**
 * 创建工厂对象、创建、加载bean的驱动者
 * @author Julian
 * @since 2018/3/8
 *
 */
public abstract class AbstractApplicationContext implements ApplicationContext{

	/**
	 * 工厂对象
	 */
	private AbstractBeanFactory beanFactory ;
	
	/**
	 * 创建工厂对象并注册bean
	 */
	public void refresh(){
		//创建默认工厂对象
		this.beanFactory = refreshBeanFactory();
		//在工厂中注册bean信息
		loadBeanDefinitions(beanFactory);
		//预先实例化bean
		beanFactory.preInstantiateSingletons();
	}
	
	/**
	 * 初始化工厂对象
	 */
	protected abstract DefaultListableBeanFactory refreshBeanFactory();
	
	/**
	 * 通过xml解析器将bean加载到指定的工厂对象中
	 * @param 工厂对象
	 */
	protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory);
	
	/**
	 * 获取bean对象
	 * @param beanName bean的ID属性值
	 */
	public Object getBean(String beanName) {
		return this.beanFactory.getBean(beanName) ;
	}

	public AbstractBeanFactory getBeanFactory() {
		return beanFactory;
	}
	
	
}
