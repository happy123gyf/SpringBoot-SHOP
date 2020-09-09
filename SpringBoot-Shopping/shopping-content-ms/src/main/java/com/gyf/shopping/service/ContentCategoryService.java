package com.gyf.shopping.service;


import com.gyf.shopping.pojo.ContentCategory;

import java.util.List;

public interface ContentCategoryService {

	public List<ContentCategory> queryAllContentCategory();

	public ContentCategory queryContentCategoryById(Long id);

	public void createContentCategory(ContentCategory contentCategory);

	public void updateContentCategory(ContentCategory contentCategory);

	public void deleteManyContentCategory(Long[] Ids);

}
