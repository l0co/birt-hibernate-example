package com.blogspot.lifeinide.birtexample;

import com.blogspot.lifeinide.birtexample.model.Company;
import com.blogspot.lifeinide.clibernate.respository.IRepository;
import com.blogspot.lifeinide.clibernate.services.BaseCliService;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @author l0co@wp.pl
 */
@Service
public class BirtExample extends BaseCliService {

	public static final String APP_CONTEXT_KEY_MOCKCOMPANYDATASET = "APP_CONTEXT_KEY_MOCKCOMPANYDATASET";
	public static final String REPORT_NAME = "company.rptdesign";

	@Autowired
	protected IRepository<Company> companyRepository;

	@Override
	protected void addOptions(Options options) {
		options.addOption("a", "add", true, "add companies <count>");
		options.addOption("l", "list", false, "list companies");
		options.addOption("d", "delete", false, "delete companies");
		options.addOption("r", "report", true, "generate companies report <report_output_file>");
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void run(CommandLine cmd, Options options) throws Exception {
		if (cmd.hasOption("a")) {

			int count = Integer.valueOf(cmd.getOptionValue("a"));
			System.out.println(String.format("Inserting %s random companies", count));
			for (int i = 0; i < count; i++) {
				Company company = PojoHelper.createCompany("Company");
				companyRepository.save(company);
			}

		} else if (cmd.hasOption("l")) {

			System.out.println("Listing all companies\n");

			for (Company company : (List<Company>) companyRepository.findAll().list())
				System.out.println(company.toString());

		} else if (cmd.hasOption("d")) {

			System.out.println("Deleting all companies\n");
			companyRepository.deleteAll();

		} else if (cmd.hasOption("r")) {

			System.out.println("Generating companies report\n");
			BirtRunner runner = new BirtRunner();
			runner.addPojoDataset(APP_CONTEXT_KEY_MOCKCOMPANYDATASET, companyRepository.findAll().iterate());
			InputStream is = runner.generatePDFReport(REPORT_NAME);
			IOUtils.copy(is, new FileOutputStream(cmd.getOptionValue("r")));

		} else {

			HelpFormatter helpFormatter = new HelpFormatter();
			helpFormatter.printHelp(getClass().getSimpleName(), options);

		}

	}

}
