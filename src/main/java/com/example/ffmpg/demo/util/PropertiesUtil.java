package com.example.ffmpg.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import java.util.Map.Entry;
/**
 * properties配置文件读取
 * @author eguid
 *
 */
public class PropertiesUtil {


	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

	private static Properties props;
	//需要在tomcat启动的时候读取里面的配置
	//静态代码块优于普通代码快优于构造代码块
	//使用静态块读取配置文件
	static {
		String fileName = "loadFFmpeg.properties";
		props = new Properties();
		try {
			props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"));
		} catch (IOException e) {
			logger.error("配置文件读取异常",e);
		}
	}
	/**
	 * 加载properties配置文件并读取配置项
	 * @param path
	 * @param cl
	 * @return
	 */
	public static Object load(String path, Class cl) {

				System.err.println("加载配置文件...");
				System.err.println("加载配置文件完毕");
				return load(props, cl);

	}
	/**
	 * 读取配置项并转换为对应对象
	 * @param pro
	 * @param cl
	 * @return
	 */
	public static Object load(Properties pro, Class cl) {
		try {
			Map<String, Object> map = getMap(pro);
			System.err.println("读取的配置项：" + map);
			Object obj = ReflectUtil.mapToObj(map, cl);
			System.err.println("转换后的对象：" + obj);
			return obj;
		} catch (InstantiationException e) {
			System.err.println("加载配置文件失败");
			return null;
		} catch (IllegalAccessException e) {
			System.err.println("加载配置文件失败");
			return null;
		} catch (IllegalArgumentException e) {
			System.err.println("加载配置文件失败");
			return null;
		} catch (InvocationTargetException e) {
			System.err.println("加载配置文件失败");
			return null;
		}
	}
	/**
	 * 获取对应文件路径下的文件流
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 */
	public static InputStream getInputStream(String path) throws FileNotFoundException {
		return new FileInputStream(path);
	}
	/**
	 * 根据路径获取properties的Map格式内容
	 * @param path
	 * @return
	 */
	public static Map<String, Object> getMap(String path){
		Properties pro=new Properties();
		try {
			pro.load(getInputStream(path));
			return getMap(pro);
		} catch (IOException e) {
			return null;
		}
	}
	/**
	 * 根据路径获取properties的Map格式内容
	 * @param path
	 * @param isRootPath -是否在项目根目录中
	 * @return
	 */
	public static Map<String, Object> getMap(String path,boolean isRootPath){
		return getMap(isRootPath?CommonUtil.getProjectRootPath()+path:path);
	}
	/**
	 * Properties配置项转为Map<String, Object>
	 * @param pro
	 * @return
	 */
	public static Map<String, Object> getMap(Properties pro) {
		if (pro == null || pro.isEmpty() || pro.size() < 1) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		for (Entry<Object, Object> en : pro.entrySet()) {
			String key = (String) en.getKey();
			Object value = en.getValue();
			map.put(key, value);
		}
		return map;
	}
}
