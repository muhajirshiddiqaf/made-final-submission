package com.ids.madesubmission4.data.realm;

import android.util.Log;

import java.util.ArrayList;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 15,January,2020.
 * Email : infinitydsolution@gmail.com
 * Company : Infinity Digital Solution
 */
public class RealmHelper<T extends RealmObject> {
    @Inject
    protected Class<T> objectClass;
    Realm realm;

    public RealmHelper(Realm realm, Class<T> clazz) {
        this.realm = realm;
        this.objectClass = clazz;
    }

    public void save(final T data) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null) {
                    realm.copyToRealm(data);
                } else {
                    Log.e("Database", "execute: Database not Exist");
                }
            }
        });
    }


    public ArrayList<T> getAllData() {
        ArrayList<T> data = new ArrayList<>();
        try {
            //realm = Realm.getDefaultInstance();
            RealmResults<T> results = realm.where(objectClass).findAll();
            data.addAll(realm.copyFromRealm(results));
        } finally {
            if (realm != null) {
                //realm.close();
            }
        }

        return data;
    }


    public void delete(String field, Integer id) {
        final RealmResults<T> model = realm.where(objectClass).equalTo(field, id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }


    public T getDataById(String field, Integer id) {
        T results = realm.where(objectClass).equalTo(field, id).findFirst();
        return results;
    }

    public ArrayList<T> getWidgetData() {
        ArrayList<T> data = new ArrayList<>();
        try {
            RealmResults<T> results = realm.where(objectClass).findAll();
            data.addAll(realm.copyFromRealm(results));
        } finally {
            if (realm != null) {
                realm.close();
            }
        }

        return data;
    }

}
