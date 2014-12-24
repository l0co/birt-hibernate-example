package com.blogspot.lifeinide.clibernate.test;

import com.blogspot.lifeinide.clibernate.respository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author l0co@wp.pl
 */
@Repository
public class TestRepository extends BaseRepository<TestEntity> {

	public TestRepository() {
		super(TestEntity.class);
	}
}
