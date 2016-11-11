package com.thisatmind.appingpot.tracker;

import android.util.Log;

import com.thisatmind.appingpot.models.Event;
import com.thisatmind.appingpot.models.Usage;
import com.thisatmind.appingpot.models.User;
import com.thisatmind.appingpot.rest.model.UsageList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;


/**
 * Created by patrick on 2016-09-19.
 */
public class TrackerDAO {

    public List<Event> getAllEvent(Realm realm){
        final List<Event> list = new ArrayList<>();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmQuery<Event> query = realm.where(Event.class);
                RealmResults<Event> results = query.findAll();
                for(Event result : results){
                    list.add(result);
//                    Log.d("TEST", result.getPackageName() + " time : " + new Date(result.getDate()).toString()
//                            + " count : " + Integer.toString(result.getCount()));
                }
            }
        });
        return list;
    }

    public static void saveEventPerHour(HashMap<Tracker.ForegroundEvent, Integer> eventMap){

        Realm realm = Realm.getDefaultInstance();
        final HashMap<Tracker.ForegroundEvent, Integer> map = eventMap;
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    RealmQuery<Event> query = realm.where(Event.class);
                    boolean isFirst = true;
                    for (Tracker.ForegroundEvent e : map.keySet()) {
                        if (!isFirst) query.or();
                        query.equalTo("packageName", e.getPackageName())
                                .equalTo("date", e.getDate());
                        isFirst = false;
                    }
                    RealmResults<Event> result = query.findAll();

                    Tracker.ForegroundEvent keyE = new Tracker.ForegroundEvent();

                    for (Event event : result) {
                        keyE.setPackageName(event.getPackageName());
                        keyE.setDate(Tracker.getCleanMillisecond(event.getDate()));
                        event.setCount(event.getCount() + map.get(keyE));
                        map.remove(keyE);
                    }

                    for (Tracker.ForegroundEvent restE : map.keySet()) {
                        Event updateE = realm.createObject(Event.class);
                        updateE.setKey(updateE.genKey(restE.getPackageName(),restE.getDate()));
                        updateE.setPackageName(restE.getPackageName());
                        updateE.setDate(restE.getDate());
                        updateE.setCount(map.get(restE));
                    }
                } catch (Exception e) {
                    Log.d("TEST", e.toString());
                    Log.d("TEST", "exception here : Tracker DAO");
                }
            }
        });
    }

    public static void addUser(final String userType, final String firebaseToken, final String facebookToken){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
            try {
                User user = realm.createObject(User.class);
                user.setKey("root");
                user.setUserType(userType);
                user.setFirebaseToken(firebaseToken);
                user.setFacebookToken(facebookToken);

            } catch (Exception e) {
                Log.d("TEST", e.toString());
                Log.d("TEST", "exception here : Tracker DAO");
            }
            }
        });
    }

    public static User getUser() {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<User> query = realm.where(User.class);
        query.equalTo("key", "root");
        RealmResults<User> result = query.findAll();
        return result.get(0);
    };

    public static void saveUsagePerHour(HashMap<Tracker.ForegroundEvent, Long> eventMap){

        Realm realm = Realm.getDefaultInstance();
        final HashMap<Tracker.ForegroundEvent, Long> map = eventMap;
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
            try {
                RealmQuery<Usage> query = realm.where(Usage.class);
                boolean isFirst = true;
                for (Tracker.ForegroundEvent e : map.keySet()) {
                    if (!isFirst) query.or();
                    query.equalTo("packageName", e.getPackageName())
                            .equalTo("date", e.getDate());
                    isFirst = false;
                }
                RealmResults<Usage> result = query.findAll();

                Tracker.ForegroundEvent keyE = new Tracker.ForegroundEvent();

                for (Usage usage : result) {
                    keyE.setPackageName(usage.getPackageName());
                    keyE.setDate(Tracker.getCleanMillisecond(usage.getDate()));
                    usage.setUsage(usage.getUsage() + map.get(keyE));
                    map.remove(keyE);
                }

                for (Tracker.ForegroundEvent restE : map.keySet()) {
                    Usage updateE = realm.createObject(Usage.class);
                    updateE.setKey(updateE.genKey(restE.getPackageName(),restE.getDate()));
                    updateE.setPackageName(restE.getPackageName());
                    updateE.setDate(restE.getDate());
                    updateE.setUsage(map.get(restE));
                }
            } catch (Exception e) {
                Log.d("TEST", e.toString());
                Log.d("TEST", "exception here : Tracker DAO");
            }
            }
        });
    }

    public static Event getMaxEvent(){
        RealmQuery<Event> query = Realm.getDefaultInstance()
                .where(Event.class)
                .not()
                .beginsWith("packageName","com.sec");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        query.greaterThan("date", cal.getTimeInMillis());
        RealmResults<Event> result = query.findAll();

        if(result.size() == 0) return null;
        result = result.sort("count", Sort.DESCENDING);
        return result.get(0);
    }

    public static List<Usage> getUsageList(long startTime) {
        Log.d("getUsageList", "it's here");
        RealmQuery<Usage> query = Realm.getDefaultInstance()
                .where(Usage.class).greaterThan("date", startTime);

        RealmResults<Usage> result = query.findAll().sort("packageName");
        for(int i = 0; i < result.size(); i++){
            Log.d("getUsageList", result.get(i).toString());
        }
        return result;
    }

    public static UsageList modelUsageListToRestUsageList(List<Usage> list){
        String firebaseToken = getUser().getFirebaseToken();
        List<com.thisatmind.appingpot.rest.model.Usage> result = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            Usage data = list.get(i);
            com.thisatmind.appingpot.rest.model.Usage usage = new com.thisatmind.appingpot.rest.model.Usage();
            usage.setUserId(firebaseToken);
            usage.setPackageName(data.getPackageName());
            usage.setUsage(data.getUsage());
            usage.setDate(data.getDate());
            result.add(usage);
        }
        return new UsageList(result);
    }



}
