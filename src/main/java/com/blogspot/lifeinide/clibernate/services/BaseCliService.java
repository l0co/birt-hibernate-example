package com.blogspot.lifeinide.clibernate.services;

import org.apache.commons.cli.*;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base class for all command line services.
 *
 * @see ICliService
 * @author l0co@wp.pl
 */
@Transactional
public abstract class BaseCliService implements ICliService {

	@Override
	public final void main(String[] args) throws ParseException {
		Options options = new Options();
		addOptions(options);

		CommandLineParser parser = new PosixParser();
		CommandLine cmd = parser.parse(options, args);
		run(cmd, options);
	}

	protected abstract void addOptions(Options options);

	protected abstract void run(CommandLine cmd, Options options);

}
