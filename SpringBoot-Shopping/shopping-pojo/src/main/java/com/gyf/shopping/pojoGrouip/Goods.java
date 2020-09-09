package com.gyf.shopping.pojoGrouip;

import com.gyf.shopping.pojo.Item;
import com.gyf.shopping.pojo.TbGoods;
import com.gyf.shopping.pojo.TbGoodsDesc;

import java.io.Serializable;
import java.util.List;

public class Goods implements Serializable {

    /*SPU = Standard Product Unit （标准产品单位）*/
    private TbGoods goods;
    private TbGoodsDesc goodsDesc;

    /*SKU=Stock Keeping Unit（库存量单位）*/
    private List<Item> itemList;

    public TbGoods getGoods() {
        return goods;
    }

    public void setGoods(TbGoods goods) {
        this.goods = goods;
    }

    public TbGoodsDesc getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(TbGoodsDesc goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
