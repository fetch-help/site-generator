package com.fetch.sitegenerator;

import org.apache.velocity.VelocityContext;

import java.util.List;
import java.util.Set;

public enum Urls {

    cat("categories","layout/cat.vm"),
    subCat("categories/${cat}","layout/subCat.vm"),
    nestedSubCat("categories/${cat}/${subCat}","layout/nestedSubCat.vm"),
    products("categories/${cat}/${subCat}/${nestedSubCat}","layout/products.vm"),
    product("product/${id}", "layout/product.vm");


    Urls(String pattern, String layout){
        this.pattern = pattern;
        this.layout = layout;
    }

    private final String pattern;
    private final String layout;

    public static String getSubCatUrl(String cat){
        return subCat.getPattern().replace("${cat}", cat);
    }
    public static String getNestedSubCatUrl(String cat, String subCat){
        return nestedSubCat.getPattern().replace("${cat}/${subCat}", cat+"/"+subCat);
    }

    public static String getProductsUrl(String cat, String subCat, String nestedSubCat){
        return products.getPattern().replace("${cat}/${subCat}/${nestedSubCat}", cat+"/"+subCat+"/"+nestedSubCat);
    }

    public static VelocityContext getSubCatContext(String activeCat, Set<String> cat, Set<String> subCat){
        VelocityContext vc = new VelocityContext();
        vc.put("activeCat", activeCat);
        vc.put("cats", cat);
        vc.put("subCats", subCat);
        return vc;
    }

    public static VelocityContext getNestedSubCatContext(String activeCat, String activeSubCat,
                                            Set<String> cat, Set<String> subCat, Set<String> nestedSubCat){
        VelocityContext vc = new VelocityContext();
        vc.put("activeCat", activeCat);
        vc.put("activeSubCat", activeSubCat);
        vc.put("cats", cat);
        vc.put("subCats", subCat);
        vc.put("nestedSubCats", nestedSubCat);
        return vc;
    }

    public static VelocityContext getProductsContext(String activeCat, String activeSubCat, String activeNestedSubCat,
                                                        Set<String> nestedSubCat,
                                                         List<Long> productIds){
        VelocityContext vc = new VelocityContext();
        vc.put("activeCat", activeCat);
        vc.put("activeSubCat", activeSubCat);
        vc.put("activeNestedSubCat", activeNestedSubCat);
        vc.put("nestedSubCats", nestedSubCat);
        vc.put("productIds", productIds);
        return vc;
    }


    public String getPattern() {
        return pattern;
    }

    public String getLayout() {
        return layout;
    }
}
