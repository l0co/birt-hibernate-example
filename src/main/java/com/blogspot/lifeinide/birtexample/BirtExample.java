package com.blogspot.lifeinide.birtexample;

import com.blogspot.lifeinide.birtexample.model.Company;
import com.blogspot.lifeinide.birtexample.model.Department;
import com.blogspot.lifeinide.clibernate.respository.IRepository;
import com.blogspot.lifeinide.clibernate.services.BaseCliService;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

/**
 * @author l0co@wp.pl
 */
@Service
public class BirtExample extends BaseCliService {

	@Autowired
	protected IRepository<Company> testRepository;

	@Override
	protected void addOptions(Options options) {
		options.addOption("a", "add", true, "add companies <count>");
		options.addOption("l", "list", false, "list companies");
		options.addOption("d", "delete", false, "delete companies");
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void run(CommandLine cmd, Options options) {
		if (cmd.hasOption("a")) {

			int count = Integer.valueOf(cmd.getOptionValue("a"));
			System.out.println(String.format("Inserting %s random companies", count));
			for (int i = 0; i < count; i++) {
				Company company = PojoHelper.createCompany("Company");
				testRepository.save(company);
			}

		} else if (cmd.hasOption("l")) {

			System.out.println("Listing all companies\n");

			for (Company company : (List<Company>) testRepository.findAll().list())
				System.out.println(company.toString());

		} else if (cmd.hasOption("d")) {

			System.out.println("Deleting all companies\n");
			testRepository.deleteAll();

		} else {

			HelpFormatter helpFormatter = new HelpFormatter();
			helpFormatter.printHelp(getClass().getSimpleName(), options);

		}

	}

}
