package com.gyf.shopping.service;

import com.gyf.shopping.pojo.Specification;

import java.util.List;
import java.util.Map;

public interface SpecificationService {

    public List<Specification> queryAllSpecification();

    public Specification querySpecificationById(Long id);

    public void createSpecification(Specification specification);

    public void updateSpecification(Specification specification);

    public void deleteManySpecification(Long[] ids);

    public List<Specification> findSpecificationByName(String specName);

    public List<Map> selectOptionList();

}
