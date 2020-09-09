package com.gyf.shopping.controller;

import com.gyf.shopping.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ItemSearchController {

    @Autowired
    private ItemSearchService itemSearchService;

    @PostMapping("/search")
    public Map search(@RequestBody Map searchMap) {
        System.out.println("进入查询/search");
        System.out.println(searchMap.get("keywords"));
        return itemSearchService.search(searchMap);


    }


}
