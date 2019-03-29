package com.aladen.entity.sys;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 数据字典表
 * </p>
 *
 * @author ${author}
 * @since 2019-03-28
 */
public class SysFieldInfo extends Model<SysFieldInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 业务编号
     */
    private String busiId;

    /**
     * 字典名称
     */
    private String fieldName;

    /**
     * 字典名称描述
     */
    private String cnName;

    /**
     * 字典值
     */
    private String fieldValue;

    /**
     * 字典值描述
     */
    private String fieldDesc;

    /**
     * 顺序
     */
    private Integer fieldOrder;

    /**
     * 备注
     */
    private String remark;


    public String getBusiId() {
        return busiId;
    }

    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getFieldDesc() {
        return fieldDesc;
    }

    public void setFieldDesc(String fieldDesc) {
        this.fieldDesc = fieldDesc;
    }

    public Integer getFieldOrder() {
        return fieldOrder;
    }

    public void setFieldOrder(Integer fieldOrder) {
        this.fieldOrder = fieldOrder;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.busiId;
    }

    @Override
    public String toString() {
        return "SysFieldInfo{" +
        "busiId=" + busiId +
        ", fieldName=" + fieldName +
        ", cnName=" + cnName +
        ", fieldValue=" + fieldValue +
        ", fieldDesc=" + fieldDesc +
        ", fieldOrder=" + fieldOrder +
        ", remark=" + remark +
        "}";
    }
}
