package com.skilldistillery.checkahead.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReviewTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Review review;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("CheckAheadPU");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		review = em.find(Review.class, 1);
				
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		review = null;
	}

	@Test
	void test() {
		assertNotNull(review);
		assertEquals(1, review.getId());
		assertEquals("Looked great", review.getContent());
		assertEquals(true, review.isActive());
		assertEquals("admin", review.getUser().getUsername());
		assertEquals(1, review.getRatings().size());
		assertEquals(1, review.getComments().size());
	
		
	}

}
