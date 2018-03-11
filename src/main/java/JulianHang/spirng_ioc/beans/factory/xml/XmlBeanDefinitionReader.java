package JulianHang.spirng_ioc.beans.factory.xml;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import JulianHang.spirng_ioc.beans.factory.MutablePropertyValues;
import JulianHang.spirng_ioc.beans.factory.config.RuntimeBeanReference;
import JulianHang.spirng_ioc.beans.factory.support.AbstractBeanDefinition;
import JulianHang.spirng_ioc.beans.factory.support.AbstractBeanDefinitionReader;
import JulianHang.spirng_ioc.beans.factory.support.AbstractBeanFactory;
import JulianHang.spirng_ioc.beans.factory.support.BeanDefinitionReaderUtils;
import JulianHang.spirng_ioc.beans.factory.support.RootBeanDefinition;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader{

	public static final String BEAN_ELEMENT = "bean";
	public static final String ID_ATTRIBUTE = "id" ;
	public static final String CLASS_ATTRIBUTE ="class";
	public static final String SINGLETON_ATTRIBUTE ="singleton";
	public static final String PROPERTY_ELEMENT = "property";
	public static final String NAME_ATTRIBUTE = "name";
	public static final String REF_ATTRIBUTE = "ref";
	public static final String VALUE_ATTRIBUTE = "value";
	public static final String TRUE_VALUE = "true" ;
	
	public XmlBeanDefinitionReader(AbstractBeanFactory beanFactory) {
		super(beanFactory);
	}
	
	/**
	 * 根据资源路径获取资源，并以输出流的方式读取
	 * @param location 资源路径
	 */
	public void loadBeanDefinitions(String location) throws Exception{
		//根据路径获取资源
		InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
		//开始解析xml文件
		doLoadBeanDefinitions(inputStream);
	}
	
	/**
	 * 创建Document对象并解析xml文件
	 * @param inputStream 资源的输出流
	 */
	public void doLoadBeanDefinitions(InputStream inputStream) throws Exception{
		//创建DocumentBuilder工厂对象
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//创建DocumentBuilder对象
		DocumentBuilder builder = factory.newDocumentBuilder();
		//创建Document对象
		Document document = builder.parse(inputStream);
		//解析xml
		registerBeanDefinitions(document);
		inputStream.close();
	}
	
	/**
	 * 获取根节点并解析xml
	 * @param document 能够解析xml文件的document对象
	 */
	public void registerBeanDefinitions(Document document) {
		//获取xml文件的根节点
		Element element = document.getDocumentElement();
		//解析xml
		parseBeanDefinitions(element);
	}
	
	/**
	 * 获取所有bean及其每个bean的属性值，并将其存储在对象中
	 * @param root
	 */
	public void parseBeanDefinitions(Element root) {
		//当前节点下的子节点列表
		NodeList nl = root.getChildNodes();
		for(int i = 0 ; i < nl.getLength() ; i ++) {
			//获取当前角标下的子节点
			Node node = nl.item(i);
			if(node instanceof Element) {
				Element ele = (Element) node;
				//判断是否属于<bean>节点
				if(BEAN_ELEMENT.equals(node.getNodeName())) {
					//存储bean信息
					AbstractBeanDefinition abd = parseBeanDefinitionElement(ele);
					//将bean信息注册到工厂对象中
					getBeanFactory().registerBeanDefinition(abd.getBeanName(), abd);;
				}
			}
		}
	}
	
	/**
	 * 获取每个bean的各个属性，并将其存储在对象中
	 * @param ele 当前节点
	 */
	public AbstractBeanDefinition parseBeanDefinitionElement(Element ele) {
		//在当前节点下根据属性获取属性值
		String id = ele.getAttribute(ID_ATTRIBUTE);
		String beanName = id ;
		
		String className = null ;
		//判断当前节点是否有class属性
		if(ele.hasAttribute(CLASS_ATTRIBUTE)) {
			//获取property节点的class属性值
			className = ele.getAttribute(CLASS_ATTRIBUTE);
		}
		
		//解析当前节点下的子节点-property节点，并将属性名与属性值进行存储
		MutablePropertyValues pvs = parsePropertyElements(ele , beanName);
		//存储bean的属性信息
		AbstractBeanDefinition abd = BeanDefinitionReaderUtils.createBeanDefinition(pvs, className , beanName); 
		
		//判断当前节点是否有singleton属性
		if(ele.hasAttribute(SINGLETON_ATTRIBUTE)) {
			//存储property节点的singleton属性值
			abd.setSingleton(TRUE_VALUE.equals(ele.getAttribute(SINGLETON_ATTRIBUTE)));
		}
		
		//else attribute operation
		
		return abd ;
	}
	
	/**
	 * 解析property节点的属性名与属性值，并将其存入对象中
	 * @param beanEle 当前节点
	 * @param beanName 当前bean节点的ID属性值
	 * @return 返回储存属性名与属性值的对象
	 */
	public MutablePropertyValues parsePropertyElements(Element beanEle , String beanName) {
		//获取当前节点下的子节点列表
		NodeList nl = beanEle.getChildNodes();
		//创建用来存储property节点及其子节点的属性名与属性值
		MutablePropertyValues pvs = new MutablePropertyValues();
		for(int i = 0 ; i < nl.getLength() ; i ++) {
			Node node = nl.item(i);
			if(node instanceof Element && PROPERTY_ELEMENT.equals(node.getNodeName())) {
				//存储property及其子节点的属性名与属性值
				parsePropertyElement((Element)node , pvs , beanName);
			}
		}
		return pvs ; 
	}
	
	/**
	 * 解析property节点及其子节点的属性名与属性值，并将其存储
	 * @param ele property节点
	 * @param pvs 用来存储property节点及其子节点的属性名与属性值
	 */
	public void parsePropertyElement(Element ele , MutablePropertyValues pvs , String beanName) {
		//获取property节点的name属性值
		String propertyName = ele.getAttribute(NAME_ATTRIBUTE);
		if(propertyName != null && propertyName !="") {
			//设置property节点中name属性的属性值
			Object val = parsePropertyValue(ele , beanName ,  propertyName);
			//存储property节点及其子节点下的属性值与属性名
			pvs.addPropertyValue(propertyName, val);
		}
	}
	
	/**
	 * 解析property节点中的name属性对应的属性值类型，并返回其值
	 * @param ele property节点
	 * @param beanName 当前bean节点的ID属性值
	 * @param propertyName 当前property节点的Name属性值
	 * @return 当前property节点的属性值
	 */
	public Object parsePropertyValue(Element ele, String beanName , String propertyName) {
		//property节点下的子节点列表
		NodeList nl = ele.getChildNodes() ;
		Element subElement = null ;
		for(int i = 0 ; i < nl.getLength() ; i ++) {
			if(nl.item(i) instanceof Element) {
				Element candidateEle = (Element) nl.item(i);
				//设置property节点的子节点
				subElement = candidateEle;
			}
		}
		
		//判断property节点的是否有ref属性
		boolean hasRefAttribute = ele.hasAttribute(REF_ATTRIBUTE);
		//判断property节点的是否有value属性
		boolean hasValueAttribute = ele.hasAttribute(VALUE_ATTRIBUTE);
		if(hasRefAttribute) {
			//获取property节点的有ref属性
			String refName = ele.getAttribute(REF_ATTRIBUTE);
			//创建一个对象来存储ref的属性值
			return new RuntimeBeanReference(refName);
		}else if (hasValueAttribute) {
			//直接返回value的属性值
			return ele.getAttribute(VALUE_ATTRIBUTE);
		}
		//解析property节点下的子节点
		return parsePropertySubElement(subElement, beanName); 
	}
	
	/**
	 * 解析property节点下的子节点-list map set等子节点，并返回其值
	 * @param ele property下的子节点
	 * @param beanName 当前bean节点的ID属性值
	 */
	public Object parsePropertySubElement(Element ele , String beanName) {
		//do something
		return null ;
	}
}

