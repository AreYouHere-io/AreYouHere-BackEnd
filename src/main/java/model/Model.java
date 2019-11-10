package model;

import config.Configuration;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Model {
	private static Model instance = null;
	private static Random rd = new Random();
	private String privateKey = "";

	Set<Class> classes;
	Set<Student> students;

	public static Model getInstance() {
		if (instance == null) {
			instance = new Model();
		}
		return instance;
	}

	public void genPrivateKey()  {
		privateKey =  Integer.toString(Math.abs(rd.nextInt()));
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public Set<Class> getClasses() {
		return classes;
	}

	public void setClasses(Set<Class> classes) {
		this.classes = classes;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}
	
	public Session getDummySession() {
		for(Class aClass : this.classes) {
			for( Session aSess : aClass.sessions) {
				return aSess;
			}
		}
		return null;
	}

	Set<Teacher> teachers;
	public Model() {
		//Build student list
		classes = new HashSet<>();
		students = new HashSet<>();
		teachers = new HashSet<>();
		buildStudent();
		buildTeacher();
		buildClass();
	}

	private String readFileFromServer(String link){
		StringBuilder sb = new StringBuilder();
		try {
			URL u = new URL(link);
			Scanner s = new Scanner(u.openStream());
			while (s.hasNext()) {
				sb.append(s.next()).append("\n");
			}

		} catch (Exception e) {
			sb.append("Error file reading");
		}
		return sb.toString();
	}
	
	public int getNumOfStudent() {
		return students.size();
	}
	
	public void buildTeacher() {
		String file = readFileFromServer(Configuration.TEACHER_FILE_PATH); 
		String[] lines = file.split("\n");
		for ( String st : lines ) {
			String[] splits = st.split(",");
//			System.out.println(st);
			teachers.add(new Teacher(splits[0],splits[1]));
		}
		System.out.println("Done Loading teachers ----------------------");
	}

	public void buildStudent() {
		String file = readFileFromServer(Configuration.STUDENT_FILE_PATH);
//		System.out.println(file);
		String[] lines = file.split("\n");
//		System.out.println(lines.length);
		for ( String st : lines ) {
//			System.out.println(st);
			String[] splits = st.split(",");
			students.add(new Student(splits[0],splits[1]));
		}
		System.out.println("Done Reading students ---------------------");
	}
	
	public void buildClass() {
		String file = readFileFromServer(Configuration.CLASS_FILE_PATH);
		String[] lines = file.split("\n");
		for(int i = 0; i < lines.length; i++) {
			String st = lines[i];
			Class newClass = new Class(st);
			i++;
			String teacherID = lines[i];
			newClass.addProfessor( getTeacherById(teacherID) ); //TODO: add multiple teachers
			i++;
			
			HashSet<String> studentIDs = new HashSet<String>(Arrays.asList(lines[i].split(",")));
			for(Student student : students) {
				if ( studentIDs.contains(student.id) ) {
					newClass.addStudent(student);
				}
			}
			i++;
			String[] sessionsString = lines[i].split(",");
			for( String session : sessionsString ) {
				int year = Integer.parseInt(session.substring(0,4));
				int month = Integer.parseInt(session.substring(4,6));
				int date = Integer.parseInt(session.substring(6));
				Session ss = new Session(year, month, date);
				for(Student student : students) {
					if ( studentIDs.contains(student.id) ) {
						ss.addDefaultAttendant(student, false);
//						ss.attendants.put(student, false);
					}
				}
				newClass.addSession(ss);
			}
			classes.add(newClass);
		}
		System.out.println("Done Reading classes ---------------------");
	}

	private Teacher getTeacherById(String teacherId) {
		for(Teacher teacher : teachers) {
			if ( teacher.getId().equals(teacherId) ) return teacher;
		}
		return null;
	}
	
	public void updateSession(Session newSession) {
		Session thisSession = this.getDummySession();
//		for ()
//		for(Student s : newSession.attendants.keySet()) {
//			thisSession.attendants.put(s, newSession.attendants.get(s));
//		}
	}

	public boolean isMatchedKey(String key) {
		return privateKey.equals(key);
	}
}
