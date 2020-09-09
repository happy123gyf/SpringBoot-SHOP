package com.gyf.shopping.service;

import com.gyf.shopping.pojo.TbItemCat;

import java.util.List;

public interface ItemCatService {

    public List<TbItemCat> findByParentId(Long parentId);

    public TbItemCat findOne(Long id);

    public List<TbItemCat> findAllItemCat();

    public void deleteManyItemCat(Long[] ids);

}
