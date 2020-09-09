package com.gyf.shopping.service.impl;

import com.gyf.shopping.mapper.SpecificationMapper;
import com.gyf.shopping.pojo.Specification;
import com.gyf.shopping.pojo.SpecificationExample;
import com.gyf.shopping.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SpecificationServiceImpl implements SpecificationService {
    @Autowired
    private SpecificationMapper specificationMapper;


    @Override
    public List<Specification> queryAllSpecification() {
        System.out.println("impl查询全部规格");
        return specificationMapper.selectByExample(null);
    }

    @Override
    public Specification querySpecificationById(Long id) {
        System.out.println("impl根据id查询规格");
        return specificationMapper.selectByPrimaryKey(id);
    }

    @Override
    public void createSpecification(Specification specification) {
        System.out.println("impl增加规格");
        specificationMapper.insert(specification);

    }

    @Override
    public void updateSpecification(Specification specification) {
        System.out.println("impl根据更新规格");
        specificationMapper.updateByPrimaryKey(specification);
    }

    @Override
    public void deleteManySpecification(Long[] ids) {
        System.out.println("impl根据ids批量删除规格");
        for (Long id : ids) {
            specificationMapper.deleteByPrimaryKey(id);
        }
    }


    @Override
    public List<Specification> findSpecificationByName(String specName) {
        System.out.println("impl根据名字查找规格" + specName);
        SpecificationExample example = new SpecificationExample();
        SpecificationExample.Criteria criteria = example.createCriteria();
        criteria.andSpecNameLike("%" + specName + "%");
        return specificationMapper.selectByExample(example);
    }

    @Override
    public List<Map> selectOptionList() {

        return specificationMapper.selectOptionList();
    }
}
