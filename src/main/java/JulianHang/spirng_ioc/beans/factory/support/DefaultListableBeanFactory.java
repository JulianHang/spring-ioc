package JulianHang.spirng_ioc.beans.factory.support;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import JulianHang.spirng_ioc.beans.factory.PropertyValue;
import JulianHang.spirng_ioc.beans.factory.config.RuntimeBeanReference;

/**
 * 默认的工厂对象,实例化bean
 * @author Julian
 * @since 2018/3/9
 */
public class DefaultListableBeanFactory extends AbstractBeanFactory{

	/**
	 * 存储bean的ID属性值
	 */
	private final Map<String , AbstractBeanDefinition> beanDefinitionMap = new HashMap<String,AbstractBeanDefinition>();
	
	/**
	 * 存储bean信息
	 */
	private final List<String> beanDefinitionNames = new ArrayList<String>();
	
	/**
	 * 存储单例的缓存实例
	 */
	private final Map<String , Object> singletonCache = new HashMap<String , Object>();
	
	/**
	 * 在工厂对象中注册bean（保存bean信息）
	 * @param beanName bean的ID属性值
	 * @param abd bean的信息
	 */
	@Override
	public void registerBeanDefinition(String beanName, AbstractBeanDefinition abd) {
		//填充数据
		this.beanDefinitionNames.add(beanName);
		this.beanDefinitionMap.put(beanName, abd);
	}

	/**
	 * 预先加载对象
	 */
	@Override
	public void preInstantiateSingletons() {
		for(Iterator<String> it = this.beanDefinitionNames.iterator() ; it.hasNext(); ) {
			//当前索引的ID属性值
			String beanName = it.next();
			//是否包含该ID属性值的对象
			if(this.beanDefinitionMap.containsKey(beanName)) {
				//判断当前bean是否是单例
				boolean isSingleton = this.beanDefinitionMap.get(beanName).isSingleton();
				if(isSingleton) {
					//判断缓存中单例bean是否有实例化
					if(!this.singletonCache.containsKey(beanName)) {
						getBean(beanName);
					}
				}else {
					getBean(beanName);
				}
			}
		}
	}
	
	public Object getBean(String beanName) {
		
		Object beanInstance = null ;
		if(this.singletonCache.containsKey(beanName)) {
			return this.singletonCache.get(beanName);
		}else {
			AbstractBeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
			//创建bean的实例
			beanInstance= createBean(beanDefinition);
			//为实例化bean设置属性值
			applyPropertyValues(beanInstance , beanDefinition);
			//若是单例则放入到缓存中
			boolean isSingleton = this.beanDefinitionMap.get(beanName).isSingleton();
			if(isSingleton) {
				this.singletonCache.put(beanName, beanInstance);
			}
		}
		
		return beanInstance;
	}
	
	/**
	 * 实例化bean
	 * @param beanDefinition 存储bean信息对象
	 * @return bean对象
	 */
	public Object createBean(AbstractBeanDefinition beanDefinition) {
		Object beanInstance = null ;
		try {
			//实例化对象
			beanInstance = beanDefinition.getBeanClass().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return beanInstance ;
	}
	
	/**
	 * 为实例化bean对象设置属性值
	 * @param beanInstance bean对象
	 * @param beanDefinition 存储bean信息对象
	 */
	public void applyPropertyValues(Object beanInstance , AbstractBeanDefinition beanDefinition) {
		for(PropertyValue pv : beanDefinition.getPvs().getPropertyValueList()) { 
			Object value = pv.getValue() ;
			//ref
			if(value instanceof RuntimeBeanReference) {
				RuntimeBeanReference rbr = (RuntimeBeanReference) value ;
				value = getBean(rbr.getBeanName());
			}
			
			try {
				//利用反射机制来设置属性值
				Field declaredField = beanInstance.getClass().getDeclaredField(pv.getName());
				declaredField.setAccessible(true);
				declaredField.set(beanInstance, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public Map<String , AbstractBeanDefinition> getBeanDefinitionMap() {
		return beanDefinitionMap;
	}

	public List<String> getBeanDefinitionNames() {
		return beanDefinitionNames;
	}

	public Map<String, Object> getSingletonCache() {
		return singletonCache;
	}

}
