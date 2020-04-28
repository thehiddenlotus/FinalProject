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

class ReviewRatingTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private ReviewRating reviewRating;

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
		ReviewRatingId rid = new ReviewRatingId();
		rid.setRatingId(1);
		rid.setReviewId(1);
		reviewRating = em.find(ReviewRating.class, rid);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		reviewRating = null;
	}

	@Test
	void test_for_id_and_values_mapped() {
		assertNotNull(reviewRating);
		assertEquals(5, reviewRating.getRatingValue());
		assertEquals("cleanliness", reviewRating.getRating().getCategory());
	}

}
