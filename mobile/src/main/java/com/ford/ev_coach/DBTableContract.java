package com.ford.ev_coach;

/**
 * Created by Ben Vesel on 11/25/2016.
 */

import android.provider.BaseColumns;

/**
 * Defines table constraints for the local database stored for this application
 */
public final class DBTableContract {

    //Prevent someone from instantiating the contract class
    private DBTableContract() {}

    /**
     * A static class which provides the "contract" or column names between the SQLite table
     */
    protected static class OverviewTableEntry implements BaseColumns {
        protected static final String TABLE_NAME = "score_overview";
        protected static final String COLUMN_TOTAL_SCORE = "total_score"; // Datatype REAL
        protected static final String COLUMN_VEHICLE_SPEED_SCORE = "vehicle_speed_score"; // Datatype REAL
        protected static final String COLUMN_ENGINE_SPEED_SCORE = "engine_speed_score"; // Datatype REAL
        protected static final String COLUMN_ACCELERATOR_SCORE = "accelerator_score"; // Datatype REAL
        protected static final String COLUMN_MPGE_SCORE = "mpge_score"; // Datatype REAL
        protected static final String COLUMN_TIMESTAMP = "timestamp"; // Datatype STRING stored as DD-MM-YYYY HH:MM:SS
    }

    /**
      * A static class providing the contract for the remote syncing database table
      */
    protected static class SyncingTableEntry implements BaseColumns {
        protected static final String TABLE_NAME = "sync_table";
        protected static final String COLUMN_SYNC_FLAG = "sync_flag"; //Datatype CHARACTER(1)
        protected static final String COLUMN_VARIABLE = "variable"; //Datatype VARCHAR(30)
        protected static final String COLUMN_VALUE = "value"; //Datatype REAL
        protected static final String COLUMN_FREQUENCY = "frequency"; //Datatype INT
    }
}
