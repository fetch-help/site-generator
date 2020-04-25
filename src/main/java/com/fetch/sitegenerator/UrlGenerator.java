package com.fetch.sitegenerator;

import com.fetch.sitegenerator.model.ProductCatalogDb;
import org.apache.velocity.VelocityContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UrlGenerator {

    public List<String> generateLevel1Urls(ProductCatalogDb db){
        return db.getLevel1().stream().map(
                l->"categories/${level1}".replace("${level1}",l)).collect(Collectors.toList());
    }

    public List<String> generateLevel2Urls(ProductCatalogDb db){
        List<String> level2 = db.getLevel2();
        return level2.stream().map(
                l->"categories/${level1}/${level2}".replace("${level1}/${level2",
                        db.getLevel1GivenLevel2(l)+"/"+l)).collect(Collectors.toList());
    }

    public List<String> generateLevel3Urls(ProductCatalogDb db){
        List<String> level3 = db.getLevel3();
        return level3.stream().map(
                l->"products/${level1}/${level2}/${level3}".replace("${level1}/${level2/${level3}",
                        db.getLevel1GivenLevel3(l)+"/"+db.getLevel2GivenLevel3(l)+"/"+l)).collect(Collectors.toList());
    }

    public List<String> generateProductUrls(ProductCatalogDb db){
        List<Long> productIds = db.getProductIds();
        return productIds.stream().map(
                l->"product/${id}".replace("${id}",l.toString())).collect(Collectors.toList());
    }
}
