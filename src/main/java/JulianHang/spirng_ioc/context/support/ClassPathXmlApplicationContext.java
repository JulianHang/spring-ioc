package JulianHang.spirng_ioc.context.support;


import java.io.IOException;

import JulianHang.spirng_ioc.beans.factory.support.AbstractBeanFactory;
import JulianHang.spirng_ioc.beans.factory.support.DefaultListableBeanFactory;
import JulianHang.spirng_ioc.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 创建、加载、实例化Bean的主要领跑者
 * @author JulianHang
 * @since 2018/3/3
 *
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext{

	
	/**
	 * 存储多个配置文件的路径
	 */
	private String[] configLocation ;
	
	
	/**
	 * 有参构造函数，加载给定路径的配置文件
	 * @param configLocation 配置文件路径
	 */
	public ClassPathXmlApplicationContext(String configLocation) {
		this(new String[] {configLocation});
	}
	
	/**
	 * 有参构造函数，加载给定多个路径下的配置文件
	 * @param configLocations 多个配置文件的路径
	 */
	public ClassPathXmlApplicationContext(String[] configLocations) {
		this(configLocations , true);
	}
	
	/**
	 * 有参构造函数，是否重新加载给定多个路径下的配置文件
	 * @param configLocations 多个配置文件的路径
	 * @param refresh 是否重新加载
	 */
	public  ClassPathXmlApplicationContext(String[] configLocations , boolean refresh) {
		this.configLocation = configLocations ;
		//是否重新加载多个配置文件
		if(refresh) {
			refresh();
		}
	}
	
	/**
	 * 初始化工厂对象
	 */
	@Override
	protected  DefaultListableBeanFactory refreshBeanFactory() {
		//创建默认工厂类
		return new DefaultListableBeanFactory();
	}
	
	/**
	 * 创建解析器对象来解析xml
	 */
	@Override
	protected void loadBeanDefinitions(AbstractBeanFactory beanFactory){
		//创建xml解析器对象
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory); 
		//获取多个配置文件的路径
		String[] configLocations = getConfigLocation();
		if (configLocations != null) {
			for (int i = 0; i < configLocations.length; i++) {
				try {
					//根据配置文件的路径获取xml并解析，为xml文件中的bean进行注册
					beanDefinitionReader.loadBeanDefinitions(configLocations[i]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	

	public String[] getConfigLocation() {
		return configLocation;
	}
}
