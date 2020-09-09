package com.gyf.shopping.service.impl;

import com.gyf.shopping.mapper.ContentCategoryMapper;
import com.gyf.shopping.pojo.ContentCategory;
import com.gyf.shopping.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private ContentCategoryMapper categoryMapper;


    @Override
    public List<ContentCategory> queryAllContentCategory() {
        return categoryMapper.selectByExample(null);
    }

    @Override
    public ContentCategory queryContentCategoryById(Long id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void createContentCategory(ContentCategory contentCategory) {
        categoryMapper.insert(contentCategory);

    }

    @Override
    public void updateContentCategory(ContentCategory contentCategory) {
        categoryMapper.updateByPrimaryKey(contentCategory);
    }

    @Override
    public void deleteManyContentCategory(Long[] Ids) {
        for (Long id : Ids) {
            categoryMapper.deleteByPrimaryKey(id);
        }
    }
}
