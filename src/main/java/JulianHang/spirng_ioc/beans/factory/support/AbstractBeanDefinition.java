package JulianHang.spirng_ioc.beans.factory.support;

import JulianHang.spirng_ioc.beans.factory.MutablePropertyValues;

/**
 *  负责存储bean的信息
 * @author Julian
 * @since 2018/3/9
 *
 */
public abstract class AbstractBeanDefinition {
	
	/**
	 * 存储property节点的属性值
	 */
	public MutablePropertyValues pvs ;
	
	/**
	 * 存储bean节点的class对象
	 */
	public Class beanClass ;
	
	/**
	 * bean节点singleton的属性值
	 */
	public boolean singleton ;
	
	/**
	 * bean节点ID的属性值
	 */
	private String beanName;
	
	/**
	 * 有参构造函数
	 * @param pvs property节点的属性值
	 * @param beanClass bean节点的class属性值
	 */
	public AbstractBeanDefinition(MutablePropertyValues pvs , Class beanClass , String beanName) {
		this.pvs = pvs ;
		this.beanClass = beanClass ;
		this.beanName = beanName ;
	}
	
	public MutablePropertyValues getPvs() {
		return pvs;
	}

	public Class getBeanClass() {
		return beanClass;
	}

	public boolean isSingleton() {
		return singleton;
	}

	public void setSingleton(boolean singleton) {
		this.singleton = singleton;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	
}
