package model;

import java.util.HashSet;

public class Class {
	HashSet<Student> students;
	HashSet<Session> sessions;
	HashSet<Teacher> teachers;
	private String name;
	
	public Class(String _name) {
		name = _name;
		students = new HashSet<>();
		sessions = new HashSet<>();
		teachers = new HashSet<>();
	}
	
	public void addSession(Session ss) {
		sessions.add(ss);
	}

	public void addProfessor(Teacher teacher) {
		teachers.add(teacher);
	}

	public void addStudent(Student student) {
		students.add(student);
		
	}
}
