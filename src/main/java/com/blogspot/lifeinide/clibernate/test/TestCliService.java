package com.blogspot.lifeinide.clibernate.test;

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
public class TestCliService extends BaseCliService {

	protected static SecureRandom random = new SecureRandom();

	@Autowired
	protected IRepository<TestEntity> testRepository;

	@Override
	protected void addOptions(Options options) {
		options.addOption("a", "add", true, "add objects <count>");
		options.addOption("l", "list", false, "list objects");
		options.addOption("d", "delete", false, "delete objects");
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void run(CommandLine cmd, Options options) {
		if (cmd.hasOption("a")) {

			int count = Integer.valueOf(cmd.getOptionValue("a"));
			System.out.println(String.format("Inserting %s random objects", count));
			for (int i = 0; i < count; i++) {
				TestEntity testEntity = new TestEntity();
				testEntity.setStringVal(new BigInteger(130, random).toString(32));
				testEntity.setIntVal(new BigInteger(130, random).intValue());
				testRepository.save(testEntity);
			}

		} else if (cmd.hasOption("l")) {

			System.out.println("Listing all objects\n");

			for (TestEntity testEntity: (List<TestEntity>) testRepository.findAll().list())
				System.out.println(testEntity.toString());

		} else if (cmd.hasOption("d")) {

			System.out.println("Deleting all objects\n");
			testRepository.deleteAll();

		} else {

			HelpFormatter helpFormatter = new HelpFormatter();
			helpFormatter.printHelp(getClass().getSimpleName(), options);

		}

	}

}
