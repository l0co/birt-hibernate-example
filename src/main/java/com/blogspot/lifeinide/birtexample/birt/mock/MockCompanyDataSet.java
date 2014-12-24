package com.blogspot.lifeinide.birtexample.birt.mock;

import com.blogspot.lifeinide.birtexample.PojoHelper;

public class MockCompanyDataSet {

	protected int idx = 10;

	public Object next() {
		if (idx--==0) // stop iteration on 10 objects
			return null;

		return PojoHelper.createCompany("Mock ");
	}

}
