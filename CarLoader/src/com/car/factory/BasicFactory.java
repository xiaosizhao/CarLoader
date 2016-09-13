package com.car.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BasicFactory {
	private static BasicFactory factory = new BasicFactory();
	private static Properties prop;
	private BasicFactory(){
		
	}
	
	static{
		prop = new Properties();
		try {
			prop.load(new FileReader(new File(BasicFactory.class.getClassLoader().getResource("config.properties").getPath())));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static BasicFactory getFactory(){
		return factory;
	}
	
	public static  <T> T getInstance(Class<T> clazz){	
		try {
			String name = clazz.getSimpleName();
			System.out.println(name);
			String clazzName = prop.getProperty(name);
			System.out.println(clazzName);
			return (T) Class.forName(clazzName).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}
	
}
