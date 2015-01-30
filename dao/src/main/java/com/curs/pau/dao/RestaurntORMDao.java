package com.curs.pau.dao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by pau on 30/01/15.
 */
@DatabaseTable(tableName = "RESTAURANTSORM")
public class RestaurantORMDao {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private int rate;
    @DatabaseField
    private String type;

    RestaurantORMDao() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RestaurntORMDao(String name, int rate, String type) {
        this.name = name;
        this.rate = rate;
        this.type = type;
    }
}
