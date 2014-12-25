package com.blogspot.lifeinide.birtexample.birt.mock;

import com.blogspot.lifeinide.birtexample.PojoHelper;
import com.blogspot.lifeinide.birtexample.model.Company;

public class MockCompanyDataSet {

	protected int idx = 10;

	public Object next() {
		if (idx--==0) // stop iteration on 10 objects
			return null;

		Company company = PojoHelper.createCompany("Mock ");
		company.setId(10-idx);
		return company;
	}

}
