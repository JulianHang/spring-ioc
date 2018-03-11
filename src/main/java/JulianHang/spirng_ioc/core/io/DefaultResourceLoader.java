package JulianHang.spirng_ioc.core.io;

import java.net.URL;

/**
 * 能根据路径加载各类资源
 * @author Julian
 * @since 2018/3/8
 *
 */
public class DefaultResourceLoader implements ResourceLoader{

	/**
	 * 根据资源url获取对应的资源对象
	 */
	public Resource getResource(String location) {
		//类加载器来生成资源url对象
		URL url = getClass().getClassLoader().getResource(location);
		return new UrlResource(url);
	}

}
