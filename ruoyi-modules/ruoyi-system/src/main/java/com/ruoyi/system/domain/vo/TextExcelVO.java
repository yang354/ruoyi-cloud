package com.ruoyi.system.domain.vo;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.annotation.Excels;
import com.ruoyi.common.core.utils.poi.ExcelHandlerAdapter;
import com.ruoyi.common.core.web.domain.BaseEntity;
import com.ruoyi.system.api.domain.SysDept;
import lombok.Data;

import java.util.List;


/**
 * @Author yzz
 * @Date 2023/5/29 下午1:51
 */

@Data
public class TextExcelVO extends BaseEntity {

    @Excel(name = "用户名称")
    private String name;


    /** 部门对象 */
    @Excels({
            @Excel(name = "部门名称", targetAttr = "deptName", type = Excel.Type.EXPORT),
            @Excel(name = "部门负责人", targetAttr = "leader", type = Excel.Type.EXPORT)
    })
    private SysDept dept;
}
