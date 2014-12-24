package com.blogspot.lifeinide.birtexample.model;

import com.blogspot.lifeinide.clibernate.model.BaseEnity;

import javax.persistence.*;

/**
 * @author l0co@wp.pl
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = BaseEnity.DISCRIMINATOR_COLUMN, discriminatorType = DiscriminatorType.STRING, length = 255)
public class Department extends BaseEnity {

	@ManyToOne
	protected Company company;

	protected String name;

	public Department() {
		super();
	}

	public Department(Company company, String name) {
		this();
		this.company = company;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	protected String internalToString() {
		return String.format("name=%s", name);
	}

}
