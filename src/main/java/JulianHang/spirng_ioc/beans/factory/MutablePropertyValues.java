package JulianHang.spirng_ioc.beans.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * 负责存储property节点中的属性名与属性值
 * @author Administrator
 *
 */
public class MutablePropertyValues {

	private final List<PropertyValue> propertyValueList ;
	
	public MutablePropertyValues() {
		this.propertyValueList = new ArrayList<PropertyValue>();
	}
	
	/**
	 * 存储节点的属性名与属性值
	 * @param propertyName 节点下的属性名
	 * @param propertyValue 节点下的属性值
	 */
	public void addPropertyValue(String propertyName , Object propertyValue) {
		addPropertyValue(new PropertyValue(propertyName , propertyValue));
	}
	
	public MutablePropertyValues addPropertyValue(PropertyValue pv) {
		for (int i = 0; i < this.propertyValueList.size(); i++) {
			PropertyValue currentPv = (PropertyValue) this.propertyValueList.get(i);
			if (currentPv.getName().equals(pv.getName())) {
				this.propertyValueList.set(i, pv);
				return this;
			}
		}
		this.propertyValueList.add(pv);
		return this;
	}

	public List<PropertyValue> getPropertyValueList() {
		return propertyValueList;
	}
	
	
}
