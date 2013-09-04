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
	public String getClassTimetableJSON(@QueryParam("id") final int classId) {
		return new Gson().toJson(this.getClassTimetable(classId));
	}

	@GET
	@Path("room")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRoomTimetableJSON(@QueryParam("id") final int roomId) {
		return new Gson().toJson(this.getRoomTimetable(roomId));
	}

	@GET
	@Path("teacher")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTeacherTimetableJSON(@QueryParam("id") final int teacherId) {
		return new Gson().toJson(this.getTeacherTimetable(teacherId));
	}

	@SuppressWarnings("unchecked")
	public Timetable getClassTimetable(final int formId) {
		final String timetableSql = "select * from timetable where form_idKlasse = '" + formId + "'";
		final Query timetableQuery = HibernateUtil.getEntityManager().createNativeQuery(timetableSql, Timetable.class);

		if (timetableQuery.getResultList().size() > 0) {
			return (Timetable) timetableQuery.getResultList().get(0);
		}

		final String lessonSql = "select * from klasse_tag_stunde where idKlasse = '" + formId + "'";
		final Query lessonQuery = HibernateUtil.getEntityManager().createNativeQuery(lessonSql, TimetableLesson.class);
		final Timetable timetable = new Timetable();
		timetable.setLessons(lessonQuery.getResultList());

		final String formSql = "select * from klasse where idKlasse = '" + formId + "'";
		final Query formQuery = HibernateUtil.getEntityManager().createNativeQuery(formSql, Form.class);
		timetable.setForm((Form) formQuery.getResultList().get(0));

		this.saveTimetable(timetable);
		return timetable;
	}

	@SuppressWarnings("unchecked")
	private Timetable getRoomTimetable(final int roomId) {
		final String timetableSql = "select * from timetable where room_idRaum = '" + roomId + "'";
		final Query timetableQuery = HibernateUtil.getEntityManager().createNativeQuery(timetableSql, Timetable.class);

		if (timetableQuery.getResultList().size() > 0) {
			return (Timetable) timetableQuery.getResultList().get(0);
		}

		final String lessonSql = "select * from klasse_tag_stunde where idRaum = '" + roomId + "'";
		final Query lessonQuery = HibernateUtil.getEntityManager().createNativeQuery(lessonSql, TimetableLesson.class);
		final Timetable timetable = new Timetable();
		timetable.setLessons(lessonQuery.getResultList());

		final String roomSql = "select * from raum where idRaum = '" + roomId + "'";
		final Query roomQuery = HibernateUtil.getEntityManager().createNativeQuery(roomSql, Room.class);
		timetable.setRoom((Room) roomQuery.getResultList().get(0));

		this.saveTimetable(timetable);
		return timetable;
	}

	@SuppressWarnings("unchecked")
	private Timetable getTeacherTimetable(final int teacherId) {
		final String timetableSql = "select * from timetable where teacher_idLehrer = '" + teacherId + "'";
		final Query timetableQuery = HibernateUtil.getEntityManager().createNativeQuery(timetableSql, Timetable.class);

		if (timetableQuery.getResultList().size() > 0) {
			return (Timetable) timetableQuery.getResultList().get(0);
		}

		final String lessonSql = "select * from klasse_tag_stunde where idLehrer = '" + teacherId + "'";
		final Query lessonQuery = HibernateUtil.getEntityManager().createNativeQuery(lessonSql, TimetableLesson.class);
		final Timetable timetable = new Timetable();
		timetable.setLessons(lessonQuery.getResultList());

		final String teacherSql = "select * from lehrer where idLehrer = '" + teacherId + "'";
		final Query teacherQuery = HibernateUtil.getEntityManager().createNativeQuery(teacherSql, Teacher.class);
		timetable.setTeacher((Teacher) teacherQuery.getResultList().get(0));

		this.saveTimetable(timetable);
		return timetable;
	}

	protected Timetable getTimetable(final int timetableId) {
		final String sql = "select * from timetable where id = '" + timetableId + "'";
		final Query query = HibernateUtil.getEntityManager().createNativeQuery(sql, Timetable.class);
		final Timetable timetable = (Timetable) query.getResultList().get(0);
		return timetable;
	}

	private void saveTimetable(final Timetable timetable) {
		if (timetable != null && timetable.getLessons().size() > 0) {
			this.entitiyManager = HibernateUtil.getEntityManager();
			final EntityTransaction transaction = this.entitiyManager.getTransaction();
			transaction.begin();
			this.entitiyManager.persist(timetable);
			transaction.commit();
		}
	}
}