package com.gyf.shopping.service.impl;


import com.alibaba.fastjson.JSON;
import com.gyf.shopping.mapper.*;
import com.gyf.shopping.pojo.*;
import com.gyf.shopping.pojoGrouip.Goods;
import com.gyf.shopping.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private TbGoodsMapper goodsMapper;
    @Autowired
    private TbGoodsDescMapper goodsDescMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private TbItemCatMapper itemCatMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private TbSellerMapper sellerMapper;


    @Override
    public void add(Goods goods) {
        goods.getGoods().setAuditStatus("0"); // 设置商品审核的状态
        goodsMapper.insert(goods.getGoods()); // 插入商品信息   tb_goods   spu（回填主键id到pojo对象中）

        goods.getGoodsDesc().setGoodsId(goods.getGoods().getId()); //设置tb_goods_desc表的主键是tb_goods的外键
        goodsDescMapper.insert(goods.getGoodsDesc()); // 插入商品的扩展信息

        setItemList(goods);

    }


    private void setItemList(Goods goods) {
        System.out.println("是否启用规格:" + goods.getGoods().getIsEnableSpec());
        if ("1".equals(goods.getGoods().getIsEnableSpec())) {
            //启用规格
            //保存SKU列表的信息
            for (Item item : goods.getItemList()) {

                /*
                    这一串是对title操作
                 */
                //设置SKU的数据
                String title = goods.getGoods().getGoodsName();
                //将json字符串转换为 map对象
                Map<String, String> map = JSON.parseObject(item.getSpec(), Map.class);
                //这方法就是获取map的key的名称
                for (String key : map.keySet()) {
                    title += "" + map.get(key);
                }
                System.out.println(title);
                item.setTitle(title);

                setValue(goods, item);
                itemMapper.insert(item);


            }


        } else {
            Item item = new Item();
            item.setTitle(goods.getGoods().getGoodsName());
            item.setPrice(goods.getGoods().getPrice());

            item.setNum(999);

            item.setStatus("0");

            item.setIsDefault("1");
            item.setSpec("{}");

            setValue(goods, item);
            itemMapper.insert(item);

        }


    }

    //setValue()方法实现，设置item（sku）的其它属性值 。
    private void setValue(Goods goods, Item item) {


        List<Map> imageList = JSON.parseArray(goods.getGoodsDesc().getItemImages(), Map.class);

        if (imageList.size() > 0) {
            item.setImage((String) imageList.get(0).get("url"));
        }

        // 保存三级分类的ID:
        item.setCategoryid(goods.getGoods().getCategory3Id());
        item.setCreateTime(new Date());
        item.setUpdateTime(new Date());
        // 设置商品ID
        System.out.println("商品id"+goods.getGoods().getId());
        item.setGoodsId(goods.getGoods().getId());
        System.out.println("sellerid"+goods.getGoods().getSellerId());
       /* item.setSellerId(goods.getGoods().getSellerId());*/
        item.setSellerId("qiandu");
        TbItemCat itemCat = itemCatMapper.selectByPrimaryKey(goods.getGoods().getCategory3Id());

        item.setCategory(itemCat.getName());

        Brand brand = brandMapper.selectByPrimaryKey(goods.getGoods().getBrandId());

        item.setBrand(brand.getName());

        TbSeller seller = null;

            seller = sellerMapper.selectByPrimaryKey("qiandu");

        System.out.println("nickName"+seller.getNickName());
        item.setSeller(seller.getNickName());

    }


    @Override
    public Goods findOne(Long id) {
        Goods goods = new Goods();
        // 查询商品表的信息
        TbGoods tbGoods = goodsMapper.selectByPrimaryKey(id);
        goods.setGoods(tbGoods);
        // 查询商品扩展表的信息
        TbGoodsDesc tbGoodsDesc = goodsDescMapper.selectByPrimaryKey(id);
        goods.setGoodsDesc(tbGoodsDesc);

        // 查询SKU表的信息:
        ItemExample example = new ItemExample();
        ItemExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsIdEqualTo(id);
        List<Item> list = itemMapper.selectByExample(example);
        goods.setItemList(list);

        return goods;
    }

    @Override
    public List<TbGoods> findAll() {


        return goodsMapper.selectByExample(null);
    }

    @Override
    public void deleteManyGoods(Long[] ids) {
        for (Long id : ids) {
            goodsMapper.deleteByPrimaryKey(id);
        }

    }

    @Override
    public void updateGoodsStatus(Long[] ids, String auditStatus) {
        for (Long id : ids) {
            TbGoods tbGoods = goodsMapper.selectByPrimaryKey(id);
            tbGoods.setAuditStatus(auditStatus);
            goodsMapper.updateByPrimaryKey(tbGoods);
        }

    }

    @Override
    public List<Item> findItemListByGoodsIdListAndStatus(Long[] ids, String auditStatus) {
        System.out.println("商品id列表" + ids);
        System.out.println("商品状态" + auditStatus);

        ItemExample example = new ItemExample();
        ItemExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(auditStatus);
        criteria.andGoodsIdIn(Arrays.asList(ids));//指定条件：SPUID集合
        List<Item> itemList = itemMapper.selectByExample(example);
        for (Item item : itemList) {
            System.out.println(item.getId()+item.getPrice()+item.getImage());
        }
        return itemList;
    }
}
