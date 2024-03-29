package edu.anu.retrogame2018s2_frogger.frogger.scene.ranking;

import java.util.ArrayList;

import edu.anu.retrogame2018s2_frogger.frogger.RecordInfo;

/*
    Author: Danny Feng
 */

//a interface to manage database. by doing this, we don't need to rely on Android feature
//so that we can easily move the code across different devices
public interface DBManager {
    void addData(RecordInfo playerInfo);

    ArrayList<RecordInfo> getData();

    ArrayList<String> getPlayer();

    Boolean playerExist(String name);

}
