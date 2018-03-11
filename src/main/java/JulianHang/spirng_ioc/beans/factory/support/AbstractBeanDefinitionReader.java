package JulianHang.spirng_ioc.beans.factory.support;

import JulianHang.spirng_ioc.core.io.DefaultResourceLoader;
import JulianHang.spirng_ioc.core.io.ResourceLoader;

/**
 * 根据路径加载配置文件资源
 * @author Julian
 * @since 2017/3/11
 *
 */
public class AbstractBeanDefinitionReader {

	/**
	 * 工厂对象
	 */
	private AbstractBeanFactory beanFactory ;
	
	/**
	 * 用来加载资源的对象
	 */
	private ResourceLoader resourceLoader ;
	
	public AbstractBeanDefinitionReader(AbstractBeanFactory beanFactory) {
		this.beanFactory = beanFactory ;
		//为成员变量设置默认资源加载器
		this.resourceLoader = new DefaultResourceLoader();
	}
	
	/**
	 * 获取beanFactory属性值
	 */
	public AbstractBeanFactory getBeanFactory() {
		return beanFactory;
	}

	/**
	 * 获取resourceLoader属性值
	 */
	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}
	
	
}
