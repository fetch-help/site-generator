package com.fetch.sitegenerator.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProductCatalogDb {

    private List<ProductCatalog> pdb = new ArrayList<>();

    private List<Long> productIds = new ArrayList<>();

    public List<String> getLevel1(){
        return pdb.stream().map(ProductCatalog::getLevel1).collect(Collectors.toList());
    }

    public List<String> getLevel2(){
        return pdb.stream().map(ProductCatalog::getLevel2).collect(Collectors.toList());
    }

    public List<String> getLevel3(){
        return pdb.stream().map(ProductCatalog::getLevel3).collect(Collectors.toList());
    }

    public List<String> getLevel1GivenLevel2(String level2){
        return pdb.stream().filter(p->p.getLevel2().equals(level2)).map(ProductCatalog::getLevel1).collect(
                Collectors.toList());
    }

    public List<String> getLevel1GivenLevel3(String level3){
        return pdb.stream().filter(p->p.getLevel3().equals(level3)).map(ProductCatalog::getLevel1).collect(
                Collectors.toList());
    }

    public List<String> getLevel2GivenLevel3(String level3){
        return pdb.stream().filter(p->p.getLevel3().equals(level3)).map(ProductCatalog::getLevel2).collect(
                Collectors.toList());
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public List<ProductCatalog> getPdb() {
        return pdb;
    }

    public void setPdb(List<ProductCatalog> pdb) {
        this.pdb = pdb;
    }
}
