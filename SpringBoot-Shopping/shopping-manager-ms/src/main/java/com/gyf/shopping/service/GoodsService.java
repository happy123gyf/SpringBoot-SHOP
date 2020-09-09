package com.gyf.shopping.service;

import com.gyf.shopping.pojo.Item;
import com.gyf.shopping.pojo.TbGoods;
import com.gyf.shopping.pojoGrouip.Goods;

import java.util.List;

public interface GoodsService {

    public void add(Goods goods);

    public Goods findOne(Long id);

    public List<TbGoods> findAll();

    public void deleteManyGoods(Long[] ids);

    public void updateGoodsStatus(Long[] ids, String auditStatus );


    List<Item> findItemListByGoodsIdListAndStatus(Long[] ids, String auditStatus);
}
