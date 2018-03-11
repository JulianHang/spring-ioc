package JulianHang.spirng_ioc.beans.factory;

/**
 * 负责存储property的name/value/ref的属性值
 * @author Julian
 * @since 2018/3/9
 *
 */
public class PropertyValue {

	/**
	 * property的name属性值
	 */
	private final String name;

	/**
	 * property的value/ref属性值
	 */
	private final Object value;
	
	public PropertyValue(String name ,Object value) {
		this.name = name ;
		this.value = value ;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}
	
	
}
