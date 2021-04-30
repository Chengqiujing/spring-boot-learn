package com.chengqj.study.springbootmybatis.entity;

import org.apache.logging.log4j.util.Strings;

import java.sql.Date;

public class VideoEntity {
    private Integer id;

    private String name;

    private String time;

    private String size;

    private String description;

    private Date releaseDate;

    private Date uptime;

    private Date updateTime;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "VideoEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", size='" + size + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate=" + releaseDate +
                ", uptime=" + uptime +
                ", updateTime=" + updateTime +
                '}';
    }
}
