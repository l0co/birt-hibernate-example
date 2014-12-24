package com.blogspot.lifeinide.birtexample.model;

import com.blogspot.lifeinide.clibernate.model.BaseEnity;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * @author l0co@wp.pl
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = BaseEnity.DISCRIMINATOR_COLUMN, discriminatorType = DiscriminatorType.STRING, length = 255)
public class Company extends BaseEnity {

	protected String name;

	@OneToMany(mappedBy = "company")
	@Cascade(CascadeType.ALL)
	protected List<Department> departments = new ArrayList<Department>();

	public Company() {
		super();
	}

	public Company(String name) {
		this();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	@Override
	protected String internalToString() {
		return String.format("name=%s, departments=%d", name, departments.size());
	}

}
