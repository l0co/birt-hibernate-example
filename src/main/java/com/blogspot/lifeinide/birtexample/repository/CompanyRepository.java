package com.blogspot.lifeinide.birtexample.repository;

import com.blogspot.lifeinide.birtexample.model.Company;
import com.blogspot.lifeinide.birtexample.model.Department;
import com.blogspot.lifeinide.clibernate.respository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author l0co@wp.pl
 */
@Repository
public class CompanyRepository extends BaseRepository<Company> {

	public CompanyRepository() {
		super(Company.class);
	}

	@Override
	public void deleteAll() {
		getSession().createQuery(String.format("delete from %s", Department.class.getSimpleName())).executeUpdate();
		super.deleteAll();
	}
}
