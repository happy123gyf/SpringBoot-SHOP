package com.gyf.shopping.service.impl;

import com.gyf.shopping.mapper.SpecificationMapper;
import com.gyf.shopping.mapper.SpecificationOptionMapper;
import com.gyf.shopping.pojo.Specification;
import com.gyf.shopping.pojo.SpecificationOption;
import com.gyf.shopping.pojo.SpecificationOptionExample;
import com.gyf.shopping.pojoGrouip.SpecificationGroup;
import com.gyf.shopping.service.SpecificationGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificationGroupServiceImpl implements SpecificationGroupService {
    @Autowired
    private SpecificationMapper specificationMapper;
    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;

    @Override
    public List<Specification> findAll() {

        return specificationMapper.selectByExample(null);
    }


    @Override
    public void add(SpecificationGroup specificationGroup) {

        specificationMapper.insert(specificationGroup.getSpecification());
        System.out.println("插入后id:" + specificationGroup.getSpecification().getId());

        for (SpecificationOption option : specificationGroup.getSpecificationOptionList()) {
            option.setSpecId(specificationGroup.getSpecification().getId());
            specificationOptionMapper.insert(option);
        }
    }

    @Override
    public void update(SpecificationGroup specificationGroup) {
        //更新规格
        specificationMapper.updateByPrimaryKey(specificationGroup.getSpecification());
        //删除多个规格选项
        SpecificationOptionExample optionExample = new SpecificationOptionExample();
        SpecificationOptionExample.Criteria criteria = optionExample.createCriteria();
        criteria.andSpecIdEqualTo(specificationGroup.getSpecification().getId());
        specificationOptionMapper.deleteByExample(optionExample);
        //增加规格选项
        for (SpecificationOption option : specificationGroup.getSpecificationOptionList()) {
            option.setSpecId(specificationGroup.getSpecification().getId());
            specificationOptionMapper.insert(option);
        }

        
    }

    @Override
    public SpecificationGroup findOne(Long id) {
        SpecificationGroup group = new SpecificationGroup();
        //根据id查询规模对象
        Specification specification = specificationMapper.selectByPrimaryKey(id);
        group.setSpecification(specification);


        // 根据规格的ID查询规格选项
        SpecificationOptionExample example = new SpecificationOptionExample();
        SpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(specification.getId());
        List<SpecificationOption> optionList = specificationOptionMapper.selectByExample(example);
        group.setSpecificationOptionList(optionList);

        return group;
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            specificationMapper.deleteByPrimaryKey(id);

            SpecificationOptionExample optionExample = new SpecificationOptionExample();
            SpecificationOptionExample.Criteria criteria = optionExample.createCriteria();
            criteria.andSpecIdEqualTo(id);
            specificationOptionMapper.deleteByExample(optionExample);

        }
    }
}
