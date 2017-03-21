package com.educsystem.database.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Denis on 21.03.2017.
 */
@Entity
@Table(name = "lvlproperties")
public class Competency {
    @Id
    @Column(name = "lvl_id")
    private int lvl_id;
    @Column(name = "levelname")
    private String levelname;
    @Column(name = "competency")
    private int competency;

    public Competency(String levelname, int competency) {
        this.levelname = levelname;
        this.competency = competency;
    }
    @OneToMany(fetch = FetchType.LAZY)
    public int getLvl_id() {
        return lvl_id;
    }

    public void setLvl_id(int lvl_id) {
        this.lvl_id = lvl_id;
    }

    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname;
    }

    public int getCompetency() {
        return competency;
    }

    public void setCompetency(int competency) {
        this.competency = competency;
    }
}
