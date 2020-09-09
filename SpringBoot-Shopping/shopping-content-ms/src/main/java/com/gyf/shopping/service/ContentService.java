package com.gyf.shopping.service;

import com.gyf.shopping.pojo.Content;

import java.util.List;

public interface ContentService {


    public List<Content> queryAllContent();

    public Content queryContentById(Long id);

    public void createContent(Content content);

    public void updateContent(Content content);

    public void deleteManyContent(Long[] Ids);

    public void updataStartContentStatus(Long[] ids);

    public void updataShieldContentStatus(Long[] ids);

    public List<Content> findContentsByCategoryId(Long categoryId);
}
