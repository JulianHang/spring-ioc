package JulianHang.spirng_ioc.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 读取资源
 * @author Julian
 * @since 2018/3/8
 */
public class UrlResource implements Resource{

	/**
	 * 资源url，设置成常量只允许赋值一次
	 */
	private final URL url ;

	/**
	 * 有参构造函数，设置资源url属性值
	 * @param url 资源url
	 */
	public UrlResource(URL url) {
		this.url = url ;
	}
	
	public InputStream getInputStream() throws IOException {
		return url.openStream();
	}
	
	
}
