package com.demo;

import java.io.Serializable;

/**
 * User: lanxinghua
 * Date: 2019/10/18 09:25
 * Desc:
 */
public class BunkType implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 是否可用
     */
    private Boolean isValid = true;

    /**
     * 登录者
     */
    private String createUser;

    /**
     * 登录时间
     */
    private Long createTime;

    /**
     * 更新者
     */
    private String opUser;

    /**
     * 更新时间
     */
    private Long opTime;

    /**
     * 版本号
     */
    private Integer lastVer;

    /**
     * 商场id
     */
    private String mallEntityId;
    /**
     * 铺位类型编号
     */
    private Integer bunkTypeNo;
    /**
     * 铺位类型名称
     */
    private  String bunkTypeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getOpUser() {
        return opUser;
    }

    public void setOpUser(String opUser) {
        this.opUser = opUser;
    }

    public Long getOpTime() {
        return opTime;
    }

    public void setOpTime(Long opTime) {
        this.opTime = opTime;
    }

    public Integer getLastVer() {
        return lastVer;
    }

    public void setLastVer(Integer lastVer) {
        this.lastVer = lastVer;
    }

    public String getMallEntityId() {
        return mallEntityId;
    }

    public void setMallEntityId(String mallEntityId) {
        this.mallEntityId = mallEntityId;
    }

    public Integer getBunkTypeNo() {
        return bunkTypeNo;
    }

    public void setBunkTypeNo(Integer bunkTypeNo) {
        this.bunkTypeNo = bunkTypeNo;
    }

    public String getBunkTypeName() {
        return bunkTypeName;
    }

    public void setBunkTypeName(String bunkTypeName) {
        this.bunkTypeName = bunkTypeName;
    }
}
