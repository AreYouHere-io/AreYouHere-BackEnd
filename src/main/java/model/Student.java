package model;

public class Student {
	String id, name;
	public Student(String _id, String _name) {
		id = _id;
		name = _name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		// null check
		if (o == null) return false;
		// instanceof Check and actual value check
		if ((o instanceof Student) && (((Student) o).getId().equals(this.getId()))) {
			return true;
		} else {
			return false;
		}
	}
 
	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}
}
