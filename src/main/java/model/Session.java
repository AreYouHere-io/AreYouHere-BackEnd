package model;

import java.time.LocalDate;
import java.util.*;

public class Session {
	private LocalDate date;
	ArrayList<Attendant> atts;
//	HashMap<Student, Boolean> attendants;
	
//	public Session (LocalDate thisDate, Set<Student> students) {
//		date = thisDate;
//		for (Student s : students) {
//			attendants.put(s, false);
//		}
//	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public ArrayList<Attendant> getAtts() {
		return atts;
	}

	public void setAtts(ArrayList<Attendant> atts) {
		this.atts = atts;
	}
	//	public Set<String> getAttendants() {
//		Set<String> res = new HashSet<>();
//		for (Student st : attendants.keySet()) {
//			StringBuilder sb = new StringBuilder();
//			sb.append(st.id).append(",").append(st.name).append(",").append(attendants.get(st));
//			res.add(sb.toString());
//		}
//		return res;
//	}

	public boolean setAttendant(String studentID, boolean attendant) {
		for (Attendant att : atts)
			if (att.getStudent().getId().equals(studentID)) {
				att.setStatus(attendant);
				return true;
			}
//		for(Student s: attendants.keySet()) {
//			if ( s.id.equals(studentID) ) {
//				attendants.put(s, attendant);
//				return true;
//			}
//		}
		return false;
	}

	public Session(int year, int month, int day) {
		date = LocalDate.of(year, month, day);
		atts = new ArrayList<>();
//		attendants = new HashMap<>();
	}

	public void addDefaultAttendant(Student st, boolean status) {
		Attendant att = new Attendant(st, status);
		atts.add(att);
	}

	public void updateSession(Session session) {
		for (int i = 0; i < atts.size(); ++i) {
			atts.get(i).setStatus(session.atts.get(i).status);
		}
	}

	public void resetSession() {
		for (int i = 0; i < atts.size(); ++i) {
			atts.get(i).setStatus(false);
		}
	}
	@Override
	public boolean equals(Object o) {
		// null check
		if (o == null) return false;
		// instanceof Check and actual value check
		if ((o instanceof Session) && (((Session) o).getDate().compareTo(this.date)  ) == 0 ) {
			return true;
		} else {
			return false;
		}
	}


	@Override
	public int hashCode() {
		return this.date.hashCode();
	}
}
