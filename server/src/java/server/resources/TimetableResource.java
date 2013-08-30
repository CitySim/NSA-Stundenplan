package server.resources;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import server.entities.Form;
import server.entities.Room;
import server.entities.Teacher;
import server.entities.Timetable;
import server.entities.TimetableLesson;
import server.persistence.HibernateUtil;

import com.google.gson.Gson;

@Path("timetable")
public class TimetableResource {
	private EntityManager entitiyManager;

	@GET
	@Path("class")
	@Produces(MediaType.APPLICATION_JSON)
	public String getClassTimetableJSON(@QueryParam("id") int classId) {
		final Gson gson = new Gson();
		final String json = gson.toJson(getClassTimetable(classId));
		return json;
	}

	@GET
	@Path("room")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRoomTimetableJSON(@QueryParam("id") int roomId) {
		final Gson gson = new Gson();
		final String json = gson.toJson(getRoomTimetable(roomId));
		return json;
	}

	@GET
	@Path("teacher")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTeacherTimetableJSON(@QueryParam("id") int teacherId) {
		final Gson gson = new Gson();
		final String json = gson.toJson(getTeacherTimetable(teacherId));
		return json;
	}

	@SuppressWarnings("unchecked")
	public Timetable getClassTimetable(int formId) {
		String timetableSql = "select * from timetable where form_idKlasse = '" + formId + "'";
		Query timetableQuery = HibernateUtil.getEntityManager().createNativeQuery(timetableSql, Timetable.class);

		if (timetableQuery.getResultList().size() > 0) {
			return (Timetable) timetableQuery.getResultList().get(0);
		}

		String lessonSql = "select * from klasse_tag_stunde where idKlasse = '" + formId + "'";
		Query lessonQuery = HibernateUtil.getEntityManager().createNativeQuery(lessonSql, TimetableLesson.class);
		Timetable timetable = new Timetable();
		timetable.setLessons(lessonQuery.getResultList());

		String formSql = "select * from klasse where idKlasse = '" + formId + "'";
		Query formQuery = HibernateUtil.getEntityManager().createNativeQuery(formSql, Form.class);
		timetable.setForm((Form) formQuery.getResultList().get(0));

		saveTimetable(timetable);
		return timetable;
	}

	@SuppressWarnings("unchecked")
	public Timetable getRoomTimetable(int roomId) {
		String timetableSql = "select * from timetable where room_idRaum = '" + roomId + "'";
		Query timetableQuery = HibernateUtil.getEntityManager().createNativeQuery(timetableSql, Timetable.class);

		if (timetableQuery.getResultList().size() > 0) {
			return (Timetable) timetableQuery.getResultList().get(0);
		}

		String lessonSql = "select * from klasse_tag_stunde where idRaum = '" + roomId + "'";
		Query lessonQuery = HibernateUtil.getEntityManager().createNativeQuery(lessonSql, TimetableLesson.class);
		Timetable timetable = new Timetable();
		timetable.setLessons(lessonQuery.getResultList());

		String roomSql = "select * from raum where idRaum = '" + roomId + "'";
		Query roomQuery = HibernateUtil.getEntityManager().createNativeQuery(roomSql, Room.class);
		timetable.setRoom((Room) roomQuery.getResultList().get(0));

		saveTimetable(timetable);
		return timetable;
	}

	@SuppressWarnings("unchecked")
	public Timetable getTeacherTimetable(int teacherId) {
		String timetableSql = "select * from timetable where teacher_idLehrer = '" + teacherId + "'";
		Query timetableQuery = HibernateUtil.getEntityManager().createNativeQuery(timetableSql, Timetable.class);

		if (timetableQuery.getResultList().size() > 0) {
			return (Timetable) timetableQuery.getResultList().get(0);
		}

		String lessonSql = "select * from klasse_tag_stunde where idLehrer = '" + teacherId + "'";
		Query lessonQuery = HibernateUtil.getEntityManager().createNativeQuery(lessonSql, TimetableLesson.class);
		Timetable timetable = new Timetable();
		timetable.setLessons(lessonQuery.getResultList());

		String teacherSql = "select * from lehrer where idLehrer = '" + teacherId + "'";
		Query teacherQuery = HibernateUtil.getEntityManager().createNativeQuery(teacherSql, Teacher.class);
		timetable.setTeacher((Teacher) teacherQuery.getResultList().get(0));

		saveTimetable(timetable);
		return timetable;
	}

	public Timetable getTimetable(int timetableId) {
		String sql = "select * from timetable where id = '" + timetableId + "'";
		Query query = HibernateUtil.getEntityManager().createNativeQuery(sql, Timetable.class);
		Timetable timetable = (Timetable) query.getResultList().get(0);
		return timetable;
	}

	private void saveTimetable(Timetable timetable) {
		if (timetable != null && timetable.getLessons().size() > 0) {
			entitiyManager = HibernateUtil.getEntityManager();
			EntityTransaction transaction = entitiyManager.getTransaction();
			transaction.begin();
			entitiyManager.persist(timetable);
			transaction.commit();
		}
	}
}