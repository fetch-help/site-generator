package com.fetch.sitegenerator;


import com.fetch.sitegenerator.context.FetchLayoutContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

public class Application {

	public static void main(String[] args)throws IOException {
		Properties props = new Properties();
		props.setProperty("file.resource.loader.class","org.apache.velocity.runtime.resource.loader.FileResourceLoader");
		props.setProperty("file.resource.loader.path","C:\\Users\\spoof\\Documents\\GitHub\\site\\site-generator\\src\\templates");
		VelocityEngine ve = new VelocityEngine(props);
		ve.init();

		Map<String, String> pathTemplate = new HashMap<>();

		Stream<String> lines = Files.lines(Paths.get("C:\\Users\\spoof\\Documents\\GitHub\\site\\site-generator\\src\\templates\\urls.vm"));
		lines.forEach(line->{
			System.out.println(line);
			String tokens[] = line.split(",");
			String urlPattern = tokens[0].trim();
			String layout = tokens[1].trim();
			pathTemplate.put(urlPattern, layout);
		});

		UrlGenerator ug = new UrlGenerator();

		/*

		String html = new Generator().run(new FetchLayoutContext().getContext(ve),
				ve.getTemplate("layout/level1.vm"));

		System.out.println(html);
		*/

	}

}
