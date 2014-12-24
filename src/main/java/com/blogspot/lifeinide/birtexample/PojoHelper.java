package com.blogspot.lifeinide.birtexample;

import com.blogspot.lifeinide.birtexample.model.Company;
import com.blogspot.lifeinide.birtexample.model.Department;

import java.math.BigInteger;
import java.security.SecureRandom;

public class PojoHelper {

	protected static SecureRandom random = new SecureRandom();

	public static Company createCompany(String prefix) {
		String name = prefix+" "+new BigInteger(130, random).toString(32);
		Company company = new Company(name);

		company.getDepartments().add(new Department(company, "IT"));
		company.getDepartments().add(new Department(company, "HR"));
		company.getDepartments().add(new Department(company, "Sales"));

		return company;
	}

}
