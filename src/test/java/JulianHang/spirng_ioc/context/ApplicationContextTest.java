package JulianHang.spirng_ioc.context;

import org.junit.Test;

import JulianHang.spirng_ioc.HelloWorldService;
import JulianHang.spirng_ioc.context.support.ApplicationContext;
import JulianHang.spirng_ioc.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextTest {

    @Test
    public void test() throws Exception {
    	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    	HelloWorldService hs = (HelloWorldService)ac.getBean("helloWorldService");
    	hs.helloWorld();
    }

}
