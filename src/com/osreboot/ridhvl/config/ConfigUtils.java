package com.osreboot.ridhvl.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ConfigUtils {
	
	public static final String delimeter = ":";
	public static final String regexDelim = Pattern.quote(delimeter);
	
	public static <TConfigType> TConfigType loadConfig(Class<? extends TConfigType> type, String path) {
		Scanner scan = null;
		
		Field[] fields = type.getFields();
		
		try {
			TConfigType toReturn = type.newInstance();
			
			scan = new Scanner(new FileInputStream(path));

			while (scan.hasNextLine()) {
				String ln = scan.nextLine();
				
				String[] split = ln.split(regexDelim);
				String propName = split[0];
				
				for (int i = 0; i < fields.length; i++)
				{					
					if (fields[i].getName().equals(propName))
					{
						if (fields[i].getType().equals(int.class) ||
								fields[i].getType().equals(Integer.class))
						{
							try {
								toReturn.getClass().getField(propName).setInt(toReturn,
										Integer.parseInt(split[1]));
							} catch (IllegalArgumentException
									| NoSuchFieldException | SecurityException e) {
							}
						}
						
						if (fields[i].getType().equals(float.class) ||
								fields[i].getType().equals(Float.class))
						{
							try {
								toReturn.getClass().getField(propName).setFloat(toReturn,
										Float.parseFloat(split[1]));
							} catch (IllegalArgumentException
									| NoSuchFieldException | SecurityException e) {
							}
						}
						
						if (fields[i].getType().equals(double.class) ||
								fields[i].getType().equals(Double.class))
						{
							try {
								toReturn.getClass().getField(propName).setDouble(toReturn,
										Double.parseDouble(split[1]));
							} catch (IllegalArgumentException
									| NoSuchFieldException | SecurityException e) {
							}
						}
						
						if (fields[i].getType().equals(boolean.class) ||
								fields[i].getType().equals(Boolean.class))
						{
							try {
								toReturn.getClass().getField(propName).setBoolean(toReturn,
										Boolean.parseBoolean(split[1]));
							} catch (IllegalArgumentException
									| NoSuchFieldException | SecurityException e) {
							}
						}
						
						if (fields[i].getType().equals(String.class))
						{
							try {
								toReturn.getClass().getField(propName).set(toReturn,
										split[1]);
							} catch (IllegalArgumentException
									| NoSuchFieldException | SecurityException e) {
							}
						}
					}
				}
			}
			return toReturn;
		} catch (FileNotFoundException | InstantiationException | IllegalAccessException e) {
			return null;
		} finally {
			if (scan != null)
				scan.close();
		}
	}

	public static <TConfigType> TConfigType loadConfig(TConfigType obj, String path) {
		Scanner scan = null;
		
		Field[] fields = obj.getClass().getFields();

		TConfigType toReturn = obj;
		
		try {
			scan = new Scanner(new FileInputStream(path));

			while (scan.hasNextLine()) {
				String ln = scan.nextLine();
				
				String[] split = ln.split(regexDelim);
				String propName = split[0];
				
				for (int i = 0; i < fields.length; i++)
				{					
					if (fields[i].getName().equals(propName))
					{
						if (fields[i].getType().equals(int.class) ||
								fields[i].getType().equals(Integer.class))
						{
							try {
								toReturn.getClass().getField(propName).setInt(toReturn,
										Integer.parseInt(split[1]));
							} catch (IllegalArgumentException
									| NoSuchFieldException | SecurityException e) {
							}
						}
						
						if (fields[i].getType().equals(float.class) ||
								fields[i].getType().equals(Float.class))
						{
							try {
								toReturn.getClass().getField(propName).setFloat(toReturn,
										Float.parseFloat(split[1]));
							} catch (IllegalArgumentException
									| NoSuchFieldException | SecurityException e) {
							}
						}
						
						if (fields[i].getType().equals(double.class) ||
								fields[i].getType().equals(Double.class))
						{
							try {
								toReturn.getClass().getField(propName).setDouble(toReturn,
										Double.parseDouble(split[1]));
							} catch (IllegalArgumentException
									| NoSuchFieldException | SecurityException e) {
							}
						}
						
						if (fields[i].getType().equals(boolean.class) ||
								fields[i].getType().equals(Boolean.class))
						{
							try {
								toReturn.getClass().getField(propName).setBoolean(toReturn,
										Boolean.parseBoolean(split[1]));
							} catch (IllegalArgumentException
									| NoSuchFieldException | SecurityException e) {
							}
						}
						
						if (fields[i].getType().equals(String.class))
						{
							try {
								toReturn.getClass().getField(propName).set(toReturn,
										split[1]);
							} catch (IllegalArgumentException
									| NoSuchFieldException | SecurityException e) {
							}
						}
					}
				}
			}
			return toReturn;
		} catch (FileNotFoundException | IllegalAccessException e) {
			return toReturn;
		} finally {
			if (scan != null)
				scan.close();
		}
	}

	public static void loadStaticConfig(Class<? extends Object> type, String path)
	{
		Scanner scan = null;
		
		Field[] allFields = type.getFields();
		List<Field> fields = new LinkedList<Field>();
		for (Field f : allFields)
		{
			if (Modifier.isStatic(f.getModifiers()))
				fields.add(f);
		}
		
		try {
			scan = new Scanner(new FileInputStream(path));

			while (scan.hasNextLine()) {
				String ln = scan.nextLine();
				
				String[] split = ln.split(regexDelim);
				String propName = split[0];
				
				for (Field f : fields)
				{
					if (f.getName().equals(propName))
					{
						if (f.getType().equals(int.class) ||
								f.getType().equals(Integer.class))
						{
							try {
								type.getClass().getField(propName).setInt(null,
										Integer.parseInt(split[1]));
							} catch (IllegalArgumentException
									| NoSuchFieldException | SecurityException e) {
							}
						}
						
						if (f.getType().equals(float.class) ||
								f.getType().equals(Float.class))
						{
							try {
								type.getClass().getField(propName).setFloat(null,
										Float.parseFloat(split[1]));
							} catch (IllegalArgumentException
									| NoSuchFieldException | SecurityException e) {
							}
						}
						
						if (f.getType().equals(double.class) ||
								f.getType().equals(Double.class))
						{
							try {
								type.getClass().getField(propName).setDouble(null,
										Double.parseDouble(split[1]));
							} catch (IllegalArgumentException
									| NoSuchFieldException | SecurityException e) {
							}
						}
						
						if (f.getType().equals(boolean.class) ||
								f.getType().equals(Boolean.class))
						{
							try {
								type.getClass().getField(propName).setBoolean(null,
										Boolean.parseBoolean(split[1]));
							} catch (IllegalArgumentException
									| NoSuchFieldException | SecurityException e) {
							}
						}
						
						if (f.getType().equals(String.class))
						{
							try {
								type.getClass().getField(propName).set(null,
										split[1]);
							} catch (IllegalArgumentException
									| NoSuchFieldException | SecurityException e) {
							}
						}
					}
				}
			}
			return;
		} catch (FileNotFoundException | IllegalAccessException e) {
			return;
		} finally {
			if (scan != null)
				scan.close();
		}
	}
}