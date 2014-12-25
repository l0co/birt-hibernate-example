package com.blogspot.lifeinide.birtexample;

import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.*;
import org.eclipse.core.internal.registry.RegistryProviderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * Runs birt report.
 *
 * @author l0co@wp.pl
 */
public class BirtRunner {

	public static final Logger logger = LoggerFactory.getLogger(BirtRunner.class);

	private HashMap datasets = new HashMap();

	@SuppressWarnings("unchecked")
	public void addPojoDataset(String key, Collection collection) {
		datasets.put(key, collection);
	}

	public InputStream generatePDFReport(String name) throws Exception {
		InputStream is = getClass().getClassLoader().getResourceAsStream(name);
		if (is==null)
			throw new RuntimeException(String.format("Error load %s report", name));

		// init birt
		logger.debug("Initializing BIRT...");
		IReportEngine engine = null;

		try {

			EngineConfig config = new EngineConfig();
			config.setEngineHome(System.getProperty("java.io.tmpdir"));
			Platform.startup(config);
			IReportEngineFactory factory = (IReportEngineFactory) Platform
				.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
			engine = factory.createReportEngine( config );
			engine.changeLogLevel( Level.WARNING );

			// load report
			logger.debug(String.format("Loading report %s...", name));
			IReportRunnable runnable = engine.openReportDesign(is);

			// execute report
			logger.debug("Generating report ...");
			RenderOption renderOption = null;
			renderOption = new PDFRenderOption();
			renderOption.setOutputFormat("pdf");
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			renderOption.setOutputStream(out);

			IRunAndRenderTask task = engine.createRunAndRenderTask(runnable);
			task.setAppContext(datasets);
			task.setRenderOption(renderOption);
			task.run();
			return new ByteArrayInputStream(out.toByteArray());

		} finally {

			// stop birt
			if (engine!=null) {

				logger.debug("Shutting down BIRT...");
				engine.destroy();
				Platform.shutdown();
				//Bugzilla 351052
				RegistryProviderFactory.releaseDefault();
				engine = null;

			}

		}
	}

}
