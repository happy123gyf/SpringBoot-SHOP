package com.gyf.shopping.service;

import com.gyf.shopping.pojo.TypeTemplate;

import java.util.List;
import java.util.Map;

public interface TypeTemplateService {


    public List<TypeTemplate> queryAllTypeTemplate();

    public TypeTemplate queryTypeTemplateById(Long id);

    public void createTypeTemplate(TypeTemplate typeTemplate);

    public void updateTypeTemplate(TypeTemplate typeTemplate);

    public void deleteManyTypeTemplate(Long[] Ids);

    public List<Map> findSpecList(Long id);


}
