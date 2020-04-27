package com.fetch.sitegenerator.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

public class ProductCatalogDb implements Serializable {

    AtomicLong productIdCounter = new AtomicLong();

    private List<ProductCatalog> pdb = new ArrayList<>();

    private Map<Long, List<Long>> productIds = new HashMap<>();

    private Map<Long, String> productNames = new HashMap<>();

    public Set<String> getCat(){
        return pdb.stream().map(ProductCatalog::getCat).collect(Collectors.toSet());
    }

    public Set<String> getSubCat(){
        return pdb.stream().map(ProductCatalog::getSubCat).collect(Collectors.toSet());
    }

    public Set<String> getNestedSubCat(){
        return pdb.stream().map(ProductCatalog::getNestedSubCat).collect(Collectors.toSet());
    }

    public Set<String> getSubCatGivenCat(String cat){
        return pdb.stream().filter(p->p.getCat().equals(cat)).map(ProductCatalog::getSubCat).collect(
                Collectors.toSet());
    }

    public Set<String> getNestedSubCatGivenCatAndSubCat(String cat, String subCat){
        return pdb.stream().filter(p->p.getCat().equals(cat)&&
                p.getSubCat().equals(subCat)).map(ProductCatalog::getNestedSubCat).collect(
                Collectors.toSet());
    }

    public Long getCatId(String cat, String subCat, String nestedSubCat) {
        return pdb.stream().filter(p->p.getCat().equals(cat)&&
                p.getSubCat().equals(subCat)&&
                p.getNestedSubCat().equals(nestedSubCat)).map(ProductCatalog::getCatId).findFirst().get();
    }

    public List<ProductCatalog> getPdb() {
        return pdb;
    }

    public void setPdb(List<ProductCatalog> pdb) {
        this.pdb = pdb;
    }

    public Map<Long, List<Long>> getProductIds() {
        return productIds;
    }

    public void setProductIds(Map<Long, List<Long>> productIds) {
        this.productIds = productIds;
    }

    public void setProductIds(Long catId, List<Long> productIds) {
        this.productIds.put(catId, productIds);
    }

    public void addProductId(Long catId, String productName) {
        if(!productIds.containsKey(catId)){
            productIds.put(catId, new ArrayList<>());
        }
        Long productId = productIdCounter.incrementAndGet();
        this.productIds.get(catId).add(productId);
        this.productNames.put(productId, productName);
    }

    public String getProductName(Long productId){
        return productNames.get(productId);
    }

    public List<Long> getProductIds(Long catId) {
        return this.productIds.get(catId);
    }

}
