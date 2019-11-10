package controller;

import model.Model;
import model.ModelHelper;
import model.Student;

public class ModelController {
	public void takeAttendant( String studentId, String sessionID, String classID ) {
		Student s = ModelHelper.getStudentById(studentId);
	}
	
	public static void main(String[] args) {
		Model model = new Model();
	}
}
