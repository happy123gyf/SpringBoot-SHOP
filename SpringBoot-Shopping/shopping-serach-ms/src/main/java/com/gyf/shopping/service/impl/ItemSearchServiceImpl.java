package com.gyf.shopping.service.impl;

import com.gyf.shopping.pojo.Item;
import com.gyf.shopping.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemSearchServiceImpl implements ItemSearchService {
    @Autowired
    private SolrTemplate solrTemplate;


    @Override
    public Map search(Map searchMap) {


        Map map = new HashMap();

        Query query = new SimpleQuery("*:*");

        Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords"));
        query.addCriteria(criteria);

        ScoredPage<Item> page = solrTemplate.query("collection1", query, Item.class);
        List<Item> itemList = page.getContent();
        for (Item item : itemList) {
            System.out.println(item.getId()+item.getTitle()+item.getPrice());
        }

        map.put("rows", itemList);


        return map;
    }

    @Override
    public void importList(List list) {
        solrTemplate.saveBeans("collection1", list );
        solrTemplate.commit("collection1");


    }

    @Override
    public void deleteByGoodsIds(List goodsIds) {
        Query query=new SimpleQuery("*:*");
        Criteria criteria=new Criteria("item_goodsid").in(goodsIds);
        query.addCriteria(criteria);
        solrTemplate.delete("collection1",query);
        solrTemplate.commit("collection1");
    }
}
