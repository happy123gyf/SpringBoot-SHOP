package com.gyf.shopping.service.impl;

import com.gyf.shopping.mapper.ContentMapper;
import com.gyf.shopping.pojo.Content;
import com.gyf.shopping.pojo.ContentExample;
import com.gyf.shopping.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public List<Content> queryAllContent() {
        return contentMapper.selectByExample(null);
    }

    @Override
    public Content queryContentById(Long id) {

        return contentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void createContent(Content content) {
        if (content.getStatus() == null) {
            content.setStatus("0");
        }

        contentMapper.insert(content);
        /*从redis缓存中删除旧的categoryId指向的集合数据*/
        redisTemplate.boundHashOps("content").delete(content.getCategoryId());
    }

    @Override
    public void updateContent(Content content) {
        contentMapper.updateByPrimaryKey(content);
        /*从redis缓存中删除旧的categoryId指向的集合数据*/
        redisTemplate.boundHashOps("content").delete(content.getCategoryId());

    }

    @Override
    public void deleteManyContent(Long[] Ids) {
        for (Long id : Ids) {
            Content content = contentMapper.selectByPrimaryKey(id);
            redisTemplate.boundHashOps("content").delete(content.getCategoryId());
            contentMapper.deleteByPrimaryKey(id);
            /*从redis缓存中删除旧的categoryId指向的集合数据*/

        }


    }

    @Override
    public void updataStartContentStatus(Long[] ids) {
        for (Long id : ids) {
            Content content = queryContentById(id);
            content.setStatus("1");
            contentMapper.updateByPrimaryKey(content);
            /*从redis缓存中删除旧的categoryId指向的集合数据*/
            redisTemplate.boundHashOps("content").delete(content.getCategoryId());

        }

    }

    @Override
    public void updataShieldContentStatus(Long[] ids) {
        for (Long id : ids) {
            Content content = queryContentById(id);
            content.setStatus("0");
            contentMapper.updateByPrimaryKey(content);
            /*从redis缓存中删除旧的categoryId指向的集合数据*/
            redisTemplate.boundHashOps("content").delete(content.getCategoryId());
        }
    }


    /*
         广告以hashmap方式存储在redis数据库中
            大key : content
                     小key:field       value
                          categoryId   List<Content>

     */
    @Override
    public List<Content> findContentsByCategoryId(Long categoryId) {
        /*redis查询*/
        List<Content> contentList = (List<Content>) redisTemplate.boundHashOps("content").get(categoryId);

        if (contentList == null) {
            System.out.println("查询mysql数据库");
            ContentExample contentExample = new ContentExample();
            ContentExample.Criteria criteria = contentExample.createCriteria();
            /*有效广告*/
            criteria.andStatusEqualTo("1");
            criteria.andCategoryIdEqualTo(categoryId);
            contentList = contentMapper.selectByExample(contentExample);

            /*redis插入*/
            redisTemplate.boundHashOps("content").put(categoryId, contentList);
        } else {
            System.out.println("从redis查询成功");
        }


        return contentList;
    }
}
