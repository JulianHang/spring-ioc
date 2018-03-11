package JulianHang.spirng_ioc.beans.factory.support;

import JulianHang.spirng_ioc.beans.factory.MutablePropertyValues;

/**
 * 运用接口化开发
 * @author Julian
 * @since 2018/3/9
 */
public class RootBeanDefinition extends AbstractBeanDefinition{

	public RootBeanDefinition(MutablePropertyValues pvs , Class beanClass , String beanName) {
		super(pvs,beanClass , beanName);
	}
}
