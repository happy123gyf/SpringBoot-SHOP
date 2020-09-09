package com.gyf.shopping.service;

import com.gyf.shopping.pojo.Specification;
import com.gyf.shopping.pojoGrouip.SpecificationGroup;

import java.util.List;

/*
     组合类的接口
 */
public interface SpecificationGroupService {


    public List<Specification> findAll();
    
    public void add(SpecificationGroup specificationGroup);


    public void update(SpecificationGroup specificationGroup);


    public SpecificationGroup findOne(Long id);


    public void delete(Long[] ids);


}
