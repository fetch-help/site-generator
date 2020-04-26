package com.fetch.sitegenerator.model;

import java.io.Serializable;

public class ProductCatalog implements Serializable {

    String cat;
    String subCat;
    String nestedSubCat;
    long catId;

    public ProductCatalog(String cat, String subCat, String nestedSubCat, long catId){
        this.cat = cat;
        this.subCat = subCat;
        this.nestedSubCat = nestedSubCat;
        this.catId = catId;
    }

    public long getCatId() {
        return catId;
    }

    public void setCatId(long catId) {
        this.catId = catId;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getSubCat() {
        return subCat;
    }

    public void setSubCat(String subCat) {
        this.subCat = subCat;
    }

    public String getNestedSubCat() {
        return nestedSubCat;
    }

    public void setNestedSubCat(String nestedSubCat) {
        this.nestedSubCat = nestedSubCat;
    }
}
