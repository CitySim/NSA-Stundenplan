package server.persistence;

import server.entities.Teacher;

public class TeacherTest {
	
	public static Teacher getTeacher() {
		Teacher teacher = new Teacher();
		teacher.setName("Wurst");
		return teacher;
	}
}
