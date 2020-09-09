package com.gyf.shopping.service;

import java.util.List;
import java.util.Map;

public interface ItemSearchService {


    public Map search(Map searchMap);

    public void importList(List list);
    
    public void deleteByGoodsIds(List goodsIds);
}
