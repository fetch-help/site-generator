package com.fetch.sitegenerator;


import com.fetch.sitegenerator.context.FetchContext;
import com.fetch.sitegenerator.context.FetchLayoutContext;
import com.fetch.sitegenerator.model.ProductCatalog;
import com.fetch.sitegenerator.model.ProductCatalogDb;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Stream;

public class Application {

	public static void main(String[] args)throws IOException {
		Properties props = new Properties();
		props.setProperty("file.resource.loader.class","org.apache.velocity.runtime.resource.loader.FileResourceLoader");
		props.setProperty("file.resource.loader.path","/home/pt/fetch/site-generator/src/templates");
		VelocityEngine ve = new VelocityEngine(props);
		ve.init();

		//TODO use the catalog.csv and products.csv
		List<ProductCatalog> l = new ArrayList<>();
		l.add(new ProductCatalog("grocery", "deli", "prepared meals", 1));
		l.add(new ProductCatalog("grocery", "beverages", "tea", 2));
		l.add(new ProductCatalog("grocery", "personal care", "skin care", 3));
		l.add(new ProductCatalog("grocery", "personal care", "feminine care", 4));
		l.add(new ProductCatalog("grocery", "pets", "dog food", 5));
		ProductCatalogDb db = new ProductCatalogDb();
		db.setPdb(l);
		db.setProductIds(1L, Arrays.asList(10L, 11L, 12L, 13L));
		db.setProductIds(2L, Arrays.asList(20L, 21L, 22L, 23L));
		db.setProductIds(3L, Arrays.asList(30L, 31L, 32L, 33L));
		db.setProductIds(4L, Arrays.asList(40L, 41L, 42L, 43L));
		db.setProductIds(5L, Arrays.asList(50L, 51L, 52L, 53L));

		String HOME = "/home/pt/fetch/site-generator/website";
		Set<String> cats = db.getCat();
		VelocityContext vc = new VelocityContext();
		vc.put("cats", new ArrayList<>(cats));
		doHtml(ve, Urls.cat.getLayout(), vc, HOME+"/index.html");

		for(String cat: cats){
			Set<String> subCats = db.getSubCatGivenCat(cat);
			for(String subCat: subCats) {
				String path = Urls.getSubCatUrl(cat);
				vc = Urls.getSubCatContext(cat, cats, subCats);
				doHtml(ve, Urls.subCat.getLayout(), vc, HOME + "/" + path + ".html");

				if(!new File(HOME, path).exists()) {
					Files.createDirectory(new File(HOME, path).toPath());
				}

				Set<String> nestedSubCats = db.getNestedSubCatGivenCatAndSubCat(cat, subCat);
				for (String nestedSubCat : nestedSubCats) {
					path = Urls.getNestedSubCatUrl(cat, subCat);
					vc = Urls.getNestedSubCatContext(cat, subCat, cats, subCats, nestedSubCats);

					doHtml(ve, Urls.nestedSubCat.getLayout(), vc, HOME + "/" + path + ".html");

					if(!new File(HOME, path).exists()) {
						Files.createDirectory(new File(HOME, path).toPath());
					}

					Long catId = db.getCatId(cat, subCat, nestedSubCat);
					List<Long> productIds = db.getProductIds(catId);
					vc = Urls.getProductsContext(cat, subCat, nestedSubCat, nestedSubCats, productIds);
					path = Urls.getProductsUrl(cat, subCat, nestedSubCat);

					if(!new File(HOME, path).exists()) {
						Files.createDirectory(new File(HOME, path).toPath());
					}

					doHtml(ve, Urls.products.getLayout(), vc, HOME + "/" + path + ".html");

					if(!new File(HOME, "product").exists()) {
						Files.createDirectory(new File(HOME, "product").toPath());
					}
					for(Long productId: productIds){
						vc = Urls.getProductContext(cat, subCat, nestedSubCat, nestedSubCats, productId);
						path = Urls.getProductUrl(productId);
						doHtml(ve, Urls.product.getLayout(), vc, HOME + "/" + path + ".html");
					}

				}
			}
		}


	}

	static void doHtml(VelocityEngine ve, String template, VelocityContext vc, String path)throws IOException{
		String html = new Generator().run(vc, ve.getTemplate(template));

		System.out.println(html);
		Files.writeString(Paths.get(path), html);

	}

}
