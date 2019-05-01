package com.lambdaschool.starwarsapitransitionsdemo;

import android.provider.BaseColumns;

public class SwApiDbContract {
    public static class PlanetsEntry implements BaseColumns {
        public static final String TABLE_NAME                  = "planets";
        public static final String COLUMN_NAME_NAME            = "name";
        public static final String COLUMN_NAME_ROTATION_PERIOD = "rotation_period";
        public static final String COLUMN_NAME_ORBITAL_PERIOD  = "orbital_period";
        public static final String COLUMN_NAME_DIAMETER        = "diameter";
        public static final String COLUMN_NAME_CLIMATE         = "climate";
        public static final String COLUMN_NAME_GRAVITY         = "gravity";
        public static final String COLUMN_NAME_TERRAIN         = "terrain";
        public static final String COLUMN_NAME_SURFACE_WATER   = "surface_water";
        public static final String COLUMN_NAME_POPULATION      = "population";

        // This is how a create table query would look if the data types were constants as well
        /*public static final String PRIMARY_KEY = "PRIMARY KEY";
        public static final String INTEGER = "INTEGER";
        public static final String TEXT = "TEXT";

        public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                                                      + " ( " +
                                                      _ID + " " + INTEGER + " " + PRIMARY_KEY + ", " +
                                                      COLUMN_NAME_NAME + " " + TEXT + ", " +
                                                      COLUMN_NAME_ROTATION_PERIOD + " " + INTEGER + ", " +
                                                      COLUMN_NAME_ORBITAL_PERIOD + " " + INTEGER + ", " +
                                                      COLUMN_NAME_DIAMETER + " " + INTEGER + ", " +
                                                      COLUMN_NAME_CLIMATE + " " + TEXT + ", " +
                                                      COLUMN_NAME_GRAVITY + " " + TEXT + ", " +
                                                      COLUMN_NAME_TERRAIN + " " + TEXT + ", " +
                                                      COLUMN_NAME_SURFACE_WATER + " " + INTEGER + ", " +
                                                      COLUMN_NAME_POPULATION + " " + INTEGER + "" +
                                                      " );";*/

        public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                                                      + " ( " +
                                                      _ID + " INTEGER PRIMARY KEY," +
                                                      COLUMN_NAME_NAME + " TEXT," +
                                                      COLUMN_NAME_ROTATION_PERIOD + " INTEGER," +
                                                      COLUMN_NAME_ORBITAL_PERIOD + " INTEGER," +
                                                      COLUMN_NAME_DIAMETER + " INTEGER," +
                                                      COLUMN_NAME_CLIMATE + " TEXT," +
                                                      COLUMN_NAME_GRAVITY + " TEXT," +
                                                      COLUMN_NAME_TERRAIN + " TEXT," +
                                                      COLUMN_NAME_SURFACE_WATER + " INTEGER," +
                                                      COLUMN_NAME_POPULATION + " INTEGER" +
                                                      "  );";

        public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class PeopleEntry implements BaseColumns {

        public static final String TABLE_NAME = "people";

        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_HEIGHT = "height";
        public static final String COLUMN_NAME_MASS = "mass";
        public static final String COLUMN_NAME_HAIR_COLOR = "hair_color";
        public static final String COLUMN_NAME_SKIN_COLOR = "skin_color";
        public static final String COLUMN_NAME_EYE_COLOR = "eye_color";
        public static final String COLUMN_NAME_BIRTH_YEAR = "birth_year";
        public static final String COLUMN_NAME_GENDER = "gender";
        public static final String COLUMN_NAME_HOMEWORLD = "homeworld";

        public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
                                                      _ID + " INTEGER PRIMARY KEY, " +
                                                      COLUMN_NAME_NAME + " TEXT, " +
                                                      COLUMN_NAME_HEIGHT + " INTEGER, " +
                                                      COLUMN_NAME_MASS + " INTEGER, " +
                                                      COLUMN_NAME_HAIR_COLOR + " TEXT, " +
                                                      COLUMN_NAME_SKIN_COLOR + " TEXT, " +
                                                      COLUMN_NAME_EYE_COLOR + " TEXT, " +
                                                      COLUMN_NAME_BIRTH_YEAR + " TEXT, " +
                                                      COLUMN_NAME_GENDER + " TEXT, " +
                                                      COLUMN_NAME_HOMEWORLD + " INTEGER " + ");";


        public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class SpeciesEntry implements BaseColumns {
        public static final String TABLE_NAME = "species";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_CLASSIFICATION = "classification";
        public static final String COLUMN_NAME_DESIGNATION = "designation";
        public static final String COLUMN_NAME_AVERAGE_HEIGHT = "average_height";
        public static final String COLUMN_NAME_SKIN_COLORS = "skin_colors";
        public static final String COLUMN_NAME_HAIR_COLORS = "hair_colors";
        public static final String COLUMN_NAME_EYE_COLORS = "eye_colors";
        public static final String COLUMN_NAME_AVERAGE_LIFESPAN = "average_lifespan";
        public static final String COLUMN_NAME_NATIVE_LANGUAGE = "native_language";

        public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                                                      "  id INTEGER PRIMARY KEY," +
                                                      "  " + COLUMN_NAME_NAME + " TEXT," +
                                                      "  " + COLUMN_NAME_CLASSIFICATION + " TEXT," +
                                                      "  " + COLUMN_NAME_DESIGNATION + " TEXT," +
                                                      "  " + COLUMN_NAME_AVERAGE_HEIGHT + " INTEGER," +
                                                      "  " + COLUMN_NAME_SKIN_COLORS + " INTEGER," +
                                                      "  " + COLUMN_NAME_HAIR_COLORS + " TEXT," +
                                                      "  " + COLUMN_NAME_EYE_COLORS + " TEXT," +
                                                      "  " + COLUMN_NAME_AVERAGE_LIFESPAN + " INTEGER," +
                                                      "  " + COLUMN_NAME_NATIVE_LANGUAGE + " TEXT" +
                                                      "  );";

        public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
