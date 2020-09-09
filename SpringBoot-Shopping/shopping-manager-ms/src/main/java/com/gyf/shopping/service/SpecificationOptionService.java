package com.gyf.shopping.service;

import com.gyf.shopping.pojo.SpecificationOption;

import java.util.List;

public interface SpecificationOptionService {

    public List<SpecificationOption> queryAllSpecificationOption();

    public SpecificationOption querySpecificationOptionById(Long id);

    public void createSpecificationOption(SpecificationOption specificationOption);

    public void updateSpecificationOption(SpecificationOption specificationOption);

    public void deleteSpecificationOptionById(Long ids);



}
