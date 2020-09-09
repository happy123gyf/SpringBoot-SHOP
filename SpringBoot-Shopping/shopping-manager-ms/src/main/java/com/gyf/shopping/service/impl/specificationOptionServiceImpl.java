package com.gyf.shopping.service.impl;

import com.gyf.shopping.mapper.SpecificationOptionMapper;
import com.gyf.shopping.pojo.SpecificationOption;
import com.gyf.shopping.service.SpecificationOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class specificationOptionServiceImpl implements SpecificationOptionService {
    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;


    @Override
    public List<SpecificationOption> queryAllSpecificationOption() {

        return specificationOptionMapper.selectByExample(null);
    }

    @Override
    public SpecificationOption querySpecificationOptionById(Long id) {
  return specificationOptionMapper.selectByPrimaryKey(id);
    }

    @Override
    public void createSpecificationOption(SpecificationOption specificationOption) {
        specificationOptionMapper.insert(specificationOption);
    }

    @Override
    public void updateSpecificationOption(SpecificationOption specificationOption) {
        specificationOptionMapper.updateByPrimaryKey(specificationOption);
    }

    @Override
    public void deleteSpecificationOptionById(Long id) {
        specificationOptionMapper.deleteByPrimaryKey(id);
    }
}
