package com.marukhin.tuturuapp.core.db.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.NotNull;

@Entity(nameInDb = "DESTINATION")
public class Destination {
    @Id
    @NotNull
    Long id;
    Integer state;
    Long cityRemoteId;

    public Destination(Integer state, Long cityRemoteId) {
        this.state = state;
        this.cityRemoteId = cityRemoteId;
    }

    @Generated(hash = 72703226)
    public Destination(@NotNull Long id, Integer state, Long cityRemoteId) {
        this.id = id;
        this.state = state;
        this.cityRemoteId = cityRemoteId;
    }

    @Generated(hash = 703305004)
    public Destination() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getCityRemoteId() {
        return this.cityRemoteId;
    }

    public void setCityRemoteId(Long cityRemoteId) {
        this.cityRemoteId = cityRemoteId;
    }
}
