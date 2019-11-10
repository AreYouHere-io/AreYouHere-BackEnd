package model;

import config.Configuration;

import java.io.*;

public class ModelHelper {
	public static Student getStudentById(String id) {

		return null;
	}

	public void ModelBuilder() {
		File file = new File(Configuration.CLASS_FILE_PATH); 
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		String st; 
		try {
			while ((st = br.readLine()) != null) 
				System.out.println(st);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public static Object getTeacherById(String readLine) {
		// TODO Auto-generated method stub
		return null;
	}
}
