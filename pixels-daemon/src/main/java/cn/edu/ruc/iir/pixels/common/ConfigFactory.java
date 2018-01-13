package cn.edu.ruc.iir.pixels.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFactory
{
	private static ConfigFactory instance = null;
	
	private ConfigFactory()
	{
		prop = new Properties();
		String pixelsHome = System.getenv("PIXELS_HOME");
		InputStream in = null;
		if (pixelsHome != null)
		{
			try
			{
				if (!pixelsHome.endsWith("/"))
				{
					pixelsHome += "/";
				}
				pixelsHome += "pixels.properties";
				in = new FileInputStream(pixelsHome);
			} catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			in = this.getClass().getResourceAsStream("/pixels.properties");
		}
		try {
			if (in != null)
			{
				prop.load(in);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ConfigFactory Instance ()
	{
		if (instance == null)
		{
			instance = new ConfigFactory();
		}
		return instance;
	}
	
	private Properties prop = null;
	
	public void LoadProperties (String propFilePath)
	{
		FileInputStream in = null;
		try
		{
			in = new FileInputStream(propFilePath);
			this.prop.load(in);
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (in != null)
			{
				try
				{
					in.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	public void addProperty (String key, String value)
	{
		this.prop.setProperty(key, value);
	}
	
	public String getProperty (String key)
	{
		return this.prop.getProperty(key);
	}
}
