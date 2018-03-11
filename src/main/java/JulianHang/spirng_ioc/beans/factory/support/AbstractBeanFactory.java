package JulianHang.spirng_ioc.beans.factory.support;

/**
 * 接口化开发
 * @author Julian
 * @since 2018/3/9
 */
public abstract class AbstractBeanFactory implements BeanFactory{

	public abstract void registerBeanDefinition(String beanName , AbstractBeanDefinition abd) ;
	public abstract void preInstantiateSingletons();
}
