package com.gyf.shopping.service;

import com.gyf.shopping.pojo.Brand;

import java.util.List;
import java.util.Map;

public interface BrandService {

    public List<Brand> queryAllBrand();

    public Brand queryBrandById(Long id);

    public void createBrand(Brand brand);

    public void updateBrand(Brand brand);

    public void deleteManyBrand(Long[] ids);

    public List<Map> selectOptionList();



}
