package com.gyf.shopping.service.impl;

import com.alibaba.fastjson.JSON;
import com.gyf.shopping.mapper.SpecificationOptionMapper;
import com.gyf.shopping.mapper.TypeTemplateMapper;
import com.gyf.shopping.pojo.SpecificationOption;
import com.gyf.shopping.pojo.SpecificationOptionExample;
import com.gyf.shopping.pojo.TypeTemplate;
import com.gyf.shopping.service.TypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {

    @Autowired
    private TypeTemplateMapper typeTemplateMapper;
    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;


    @Override
    public List<TypeTemplate> queryAllTypeTemplate() {

        return typeTemplateMapper.selectByExample(null);
    }

    @Override
    public TypeTemplate queryTypeTemplateById(Long id) {

        return typeTemplateMapper.selectByPrimaryKey(id);
    }

    @Override
    public void createTypeTemplate(TypeTemplate typeTemplate) {
        typeTemplateMapper.insert(typeTemplate);
    }

    @Override
    public void updateTypeTemplate(TypeTemplate typeTemplate) {
        typeTemplateMapper.updateByPrimaryKey(typeTemplate);
    }

    @Override
    public void deleteManyTypeTemplate(Long[] Ids) {
        for (Long id : Ids) {
            typeTemplateMapper.deleteByPrimaryKey(id);
        }
    }


    @Override
    public List<Map> findSpecList(Long id) {
        //根据ID查询到模板对象
        TypeTemplate typeTemplate = typeTemplateMapper.selectByPrimaryKey(id);
        // 获得规格的数据spec_ids
        String specIds = typeTemplate.getSpecIds();// [{"id":27,"text":"网络"},{"id":32,"text":"机身内存"}]
        // 将specIds的字符串转成JSON的List<Map>
        List<Map> list = JSON.parseArray(specIds, Map.class);
        // 获得每条记录:
        for (Map map : list) {
            // 根据规格的ID 查询规格选项的数据:
            // 设置查询条件:
            SpecificationOptionExample example = new SpecificationOptionExample();
            SpecificationOptionExample.Criteria criteria = example.createCriteria();
            criteria.andSpecIdEqualTo(new Long((Integer)map.get("id")));

            List<SpecificationOption> specOptionList = specificationOptionMapper.selectByExample(example);
            //查询tb_specification_oprion表，加载option_name字段，然后封装到map中
            map.put("options", specOptionList);//{"id":27,"text":"网络",options:[{id：xxx,optionName:移动2G}]}
        }
        return list;
    }
}
