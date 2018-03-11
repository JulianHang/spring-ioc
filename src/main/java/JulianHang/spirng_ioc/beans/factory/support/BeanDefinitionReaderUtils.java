package JulianHang.spirng_ioc.beans.factory.support;

import JulianHang.spirng_ioc.beans.factory.MutablePropertyValues;

/**
 * 操作bean的工具类
 * @author Julian
 * @since 2018/3/9
 *
 */
public class BeanDefinitionReaderUtils {

	/**
	 * 存储bean信息
	 * @param pvs property节点的属性值
	 * @param className bean节点的class属性值
	 * @return 存储bean信息的对象
	 */
	public static AbstractBeanDefinition createBeanDefinition(MutablePropertyValues pvs , String className , String beanName) {
		Class beanClass = null;
		if (className != null) {
			try {
				//创建class对象
				beanClass = Class.forName(className);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new RootBeanDefinition(pvs , beanClass , beanName);
	}
}
