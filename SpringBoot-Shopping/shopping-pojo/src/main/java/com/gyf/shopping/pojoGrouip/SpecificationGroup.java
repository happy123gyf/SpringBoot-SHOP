package com.gyf.shopping.pojoGrouip;


import com.gyf.shopping.pojo.Specification;
import com.gyf.shopping.pojo.SpecificationOption;

import java.io.Serializable;
import java.util.List;





/**
 *    这个类用来接受 前端穿过来的Json数据  ,
 *       传过来 entity = {specificationOptionList:[] }
 *       这个specificationOptionList:[] 代表规格选项的json数据
 */
public class SpecificationGroup implements Serializable {

    private Specification specification;

    private List<SpecificationOption> specificationOptionList;

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public List<SpecificationOption> getSpecificationOptionList() {
        return specificationOptionList;
    }

    public void setSpecificationOptionList(List<SpecificationOption> specificationOptionList) {
        this.specificationOptionList = specificationOptionList;
    }
}
