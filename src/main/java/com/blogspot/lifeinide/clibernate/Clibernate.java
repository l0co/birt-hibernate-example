package com.blogspot.lifeinide.clibernate;

import com.blogspot.lifeinide.clibernate.services.ICliService;
import org.apache.commons.cli.ParseException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main command line interface class.
 *
 * @author l0co@wp.pl
 */
public class Clibernate {

	public static final String APPLICATION_CONTEXT_XML = "applicationContext.xml";

	public static void main(String[] args) throws ParseException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML);
		String serviceName = null;
		if (args.length>0)
			serviceName = args[args.length-1];

		if (serviceName!=null) {

			ICliService cliService = (ICliService) ctx.getBean(serviceName);
			cliService.main(args);

		} else {

			System.out.println("usage: clibernate [options] [service_name]");
			System.out.println("available services:");
			for (String beanName: ctx.getBeansOfType(ICliService.class).keySet()) {
				System.out.println(" "+beanName);
			}

		}
	}

}
