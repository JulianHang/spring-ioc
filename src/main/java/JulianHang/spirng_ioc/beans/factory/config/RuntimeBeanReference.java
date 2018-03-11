package JulianHang.spirng_ioc.beans.factory.config;

/**
 * 负责存储ref的属性值
 * @author Julian
 * @since 2018/3/9
 *
 */
public class RuntimeBeanReference {

	/*
	 * ref的属性值
	 */
	private final String  beanName ; 
	
	public RuntimeBeanReference(String beanName) {
		this.beanName = beanName ;
	}

	public String getBeanName() {
		return beanName;
	}
	
}
