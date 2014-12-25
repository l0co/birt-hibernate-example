package com.blogspot.lifeinide.birtexample;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Runs birt report.
 *
 * @author l0co@wp.pl
 */
public class BirtRunner {

	private Map<String, Collection> datasets = new HashMap<String, Collection>();

	public void addPojoDataset(String key, Collection collection) {
		datasets.put(key, collection);
	}

	public InputStream generatePDFReport(String name) {
		// TODOLF implement BirtRunner.generateReport
		return new ByteArrayInputStream(new byte[] {0, 1, 2});
	}

}
