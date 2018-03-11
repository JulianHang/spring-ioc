package JulianHang.spirng_ioc.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源接口
 * @author Julian
 * @since 2018/3/8
 */
public interface Resource {

	/**
	 * 将资源以输出流的方式读取
	 */
	InputStream getInputStream() throws IOException;
}
