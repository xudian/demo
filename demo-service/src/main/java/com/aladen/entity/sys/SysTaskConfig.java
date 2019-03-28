package com.aladen.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 定时任务配置表
 * </p>
 *
 * @author ${author}
 * @since 2019-03-27
 */
public class SysTaskConfig extends Model<SysTaskConfig> {

    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 时间配置
     */
    private String timeCron;

    /**
     * 对应的bean
     */
    private String beanRef;

    /**
     * 对应函数
     */
    private String method;

    /**
     * 是否启用：0.禁用；1.启用
     */
    private Integer status;

    /**
     * 线程池中的线程数量
     */
    private Integer threads;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /**
     * 允许执行的机器ip
     */
    private String ips;

    /**
     * 所属应用名称，与spring.application.name属性对应
     */
    private String appName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeCron() {
        return timeCron;
    }

    public void setTimeCron(String timeCron) {
        this.timeCron = timeCron;
    }

    public String getBeanRef() {
        return beanRef;
    }

    public void setBeanRef(String beanRef) {
        this.beanRef = beanRef;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getThreads() {
        return threads;
    }

    public void setThreads(Integer threads) {
        this.threads = threads;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysTaskConfig{" +
        "id=" + id +
        ", name=" + name +
        ", timeCron=" + timeCron +
        ", beanRef=" + beanRef +
        ", method=" + method +
        ", status=" + status +
        ", threads=" + threads +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", ips=" + ips +
        ", appName=" + appName +
        "}";
    }
}
