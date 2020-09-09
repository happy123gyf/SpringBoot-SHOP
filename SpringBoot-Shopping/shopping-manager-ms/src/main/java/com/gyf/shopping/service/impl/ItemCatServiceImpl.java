package com.gyf.shopping.service.impl;

import com.gyf.shopping.mapper.TbItemCatMapper;
import com.gyf.shopping.pojo.TbItemCat;
import com.gyf.shopping.pojo.TbItemCatExample;
import com.gyf.shopping.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<TbItemCat> findByParentId(Long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);

        List<TbItemCat> list = itemCatMapper.selectByExample(example);

        return list;
    }

    @Override
    public TbItemCat findOne(Long id) {


        return itemCatMapper.selectByPrimaryKey(id);

    }


    @Override
    public List<TbItemCat> findAllItemCat() {

        return itemCatMapper.selectByExample(null);
    }

    @Override
    public void deleteManyItemCat(Long[] ids) {

        for (Long id : ids) {
            itemCatMapper.deleteByPrimaryKey(id);
        }
    }
}
