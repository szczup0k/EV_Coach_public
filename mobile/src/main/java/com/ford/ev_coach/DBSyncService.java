/*
Copyright © 2018 Ford Motor Company

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.ford.ev_coach;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A intent service launched by the StarterActivity and then saves all the data processed
 * to the local database.
 */
public class DBSyncService extends IntentService {
    // Write a message to the database


    private static final String TAG = DBSyncService.class.getSimpleName();

    public DBSyncService() {
        super("DBSyncService");
    }


    /**
     * Processes database information.
     *
     * @param workIntent - the intent passed and data that should be processed
     */
    @Override
    protected void onHandleIntent(Intent workIntent) {


        /* Grab the extra doubles for scores put in the intent */
        double speedScore = workIntent.getDoubleExtra(DBTableContract.OverviewTableEntry.COLUMN_VEHICLE_SPEED_SCORE, 0);
        double engineScore = workIntent.getDoubleExtra(DBTableContract.OverviewTableEntry.COLUMN_ENGINE_SPEED_SCORE, 0);
        double mpgeScore = workIntent.getDoubleExtra(DBTableContract.OverviewTableEntry.COLUMN_MPGE_SCORE, 0);
        double accelScore = workIntent.getDoubleExtra(DBTableContract.OverviewTableEntry.COLUMN_ACCELERATOR_SCORE, 0);
        double totalScore = speedScore + engineScore + mpgeScore + accelScore;

        /* Grab the date and put it in a nice format */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.US);
        String date = simpleDateFormat.format(new Date());

        /* Get the database reference */
        EVCoachDBHelper dbHelper = new EVCoachDBHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        /* Put the values within an object to be sent to the database */
        ContentValues values = new ContentValues();
        values.put(DBTableContract.OverviewTableEntry.COLUMN_ACCELERATOR_SCORE, accelScore);
        values.put(DBTableContract.OverviewTableEntry.COLUMN_ENGINE_SPEED_SCORE, engineScore);
        values.put(DBTableContract.OverviewTableEntry.COLUMN_MPGE_SCORE, mpgeScore);
        values.put(DBTableContract.OverviewTableEntry.COLUMN_TIMESTAMP, date);
        values.put(DBTableContract.OverviewTableEntry.COLUMN_TOTAL_SCORE, totalScore);
        values.put(DBTableContract.OverviewTableEntry.COLUMN_VEHICLE_SPEED_SCORE, speedScore);

        //TODO do the firebase stuff here

        /* Insert the values within the database */
        Log.d(TAG, "Inserting values vehicle_speed = " + speedScore + "  mpge = " + mpgeScore +  " engine = " + engineScore + " accel = " + accelScore);
        db.insert(DBTableContract.OverviewTableEntry.TABLE_NAME, null, values);
        db.close();
    }
}
