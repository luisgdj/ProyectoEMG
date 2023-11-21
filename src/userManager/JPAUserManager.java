package userManager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import pojos.Role;
import pojos.User;

public class JPAUserManager {

	EntityManager em;

	public JPAUserManager() {

		em = Persistence.createEntityManagerFactory("emg-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();

		// Create the needed roles
		if (this.getRoles().isEmpty()) {
			Role manager = new Role("doctor");
			Role nurse = new Role("patient");
			this.createRole(manager);
			this.createRole(nurse);

			User user = new User("doctor", "default0", "doctor@hospital.com");
			register(user);
			Role role = getRole("doctor");
			assignRole(user, role);
		}
	}

	public void register(User user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}
	
	public void createRole(Role role) {
		em.getTransaction().begin();
		em.persist(role);
		em.getTransaction().commit();
	}

	public void assignRole(User user, Role role) {
		em.getTransaction().begin();
		user.setRole(role);
		role.addUser(user);
		em.getTransaction().commit();
	}
	
	public Role getRole(String name) {
		Query q = em.createNativeQuery("SELECT * FROM roles WHERE name LIKE ?", Role.class);
		q.setParameter(1, name);
		Role r = (Role) q.getSingleResult();
		return r;
	}
		
	public List<Role> getRoles() {
		Query q = em.createNativeQuery("SELECT * FROM roles", Role.class);
		List<Role> roles = (List<Role>) q.getResultList();
		return roles;
	}
		
	public User logIn(String username, String password) {
		try {
			Query q = em.createNativeQuery("SELECT * FROM users WHERE username = ? AND password = ?", User.class);
			q.setParameter(1, username);
			q.setParameter(2, password);
			User user = (User) q.getSingleResult();
			return user;
				
		} catch (NoResultException e) {
			return null;
		}
	}
		
	public User changePassword(User user, String newPassword) {
			
		try {
			Query sql = em.createNativeQuery("SELECT * FROM users WHERE username = ? AND password = ?", User.class);
			sql.setParameter(1, user.getUsername());
			sql.setParameter(2, user.getPassword());
			user = (User) sql.getSingleResult();
			
			em.getTransaction().begin();
			user.setPassword(newPassword);
			em.getTransaction().commit();
			return user;
			
		}catch(NoResultException e) {
			return null;
		}
	}

	public void close() {
		em.close();
	}
}