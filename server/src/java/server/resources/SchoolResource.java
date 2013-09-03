﻿package server.resources;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import server.entities.School;
import server.persistence.HibernateUtil;

import com.google.gson.Gson;

@Path("school")
public class SchoolResource {
	School newSchool = new School();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getSchoolJSON() {
		final Gson gson = new Gson();
		final String json = gson.toJson(this.getSchool());
		return json;
	}

	public void changeSchoolJSON() {
	}

	private synchronized School getSchool() {
		return HibernateUtil.getEntityManager().find(School.class, 1);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public synchronized void setSchool(final String schoolJSON) {

		final Gson gson = new Gson();
		final School school = gson.fromJson(schoolJSON, School.class);

		final EntityManager em = HibernateUtil.getEntityManager();
		this.newSchool = em.find(School.class, 1);
		em.getTransaction().begin();
		this.newSchool.setImage(school.getImage());
		this.newSchool.setText(school.getText());
		em.persist(this.newSchool);
		em.getTransaction().commit();

	}
}