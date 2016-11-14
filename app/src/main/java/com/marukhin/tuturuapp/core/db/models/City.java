package com.marukhin.tuturuapp.core.db.models;


import com.marukhin.tuturuapp.core.network.res.CityRes;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;


@Entity(nameInDb = "CITY")
public class City {
    @Id
    Long id;
    String title;
    String searchTitle;
    @ToMany(joinProperties = {
            @JoinProperty(name = "id", referencedName = "cityRemoteId")
    })
    List<Station> stations;
    @ToMany(joinProperties = {
            @JoinProperty(name = "id", referencedName = "cityRemoteId")
    })
    List<Destination> destination;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 448079911)
    private transient CityDao myDao;

    public City(CityRes data) {
        id = data.cityId;
        title = data.countryTitle + ", " + data.cityTitle;
        searchTitle = title.toUpperCase();
    }

    @Generated(hash = 1632837326)
    public City(Long id, String title, String searchTitle) {
        this.id = id;
        this.title = title;
        this.searchTitle = searchTitle;
    }

    @Generated(hash = 750791287)
    public City() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSearchTitle() {
        return this.searchTitle;
    }

    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1636872477)
    public List<Station> getStations() {
        if (stations == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            StationDao targetDao = daoSession.getStationDao();
            List<Station> stationsNew = targetDao._queryCity_Stations(id);
            synchronized (this) {
                if (stations == null) {
                    stations = stationsNew;
                }
            }
        }
        return stations;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 307598014)
    public synchronized void resetStations() {
        stations = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1044019648)
    public List<Destination> getDestination() {
        if (destination == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DestinationDao targetDao = daoSession.getDestinationDao();
            List<Destination> destinationNew = targetDao._queryCity_Destination(id);
            synchronized (this) {
                if (destination == null) {
                    destination = destinationNew;
                }
            }
        }
        return destination;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 865777907)
    public synchronized void resetDestination() {
        destination = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 293508440)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCityDao() : null;
    }


}
