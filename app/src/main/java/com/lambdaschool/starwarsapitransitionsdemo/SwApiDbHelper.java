package com.lambdaschool.starwarsapitransitionsdemo;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class SwApiDbHelper extends SQLiteOpenHelper {

    public static final int    DATABASE_VERSION = 1;
    public static final String DATABASE_NAME    = "SwApiDatabase.db";

    public SwApiDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SwApiDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        this(context);
    }

    public SwApiDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        this(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public SwApiDbHelper(Context context, String name, int version, SQLiteDatabase.OpenParams openParams) {
        this(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SwApiDbContract.PeopleEntry.SQL_CREATE_TABLE);
        db.execSQL(SwApiDbContract.SpeciesEntry.SQL_CREATE_TABLE);
        db.execSQL(SwApiDbContract.PlanetsEntry.SQL_CREATE_TABLE);
        populateStarterData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SwApiDbContract.PeopleEntry.SQL_DELETE_TABLE);
        db.execSQL(SwApiDbContract.SpeciesEntry.SQL_DELETE_TABLE);
        db.execSQL(SwApiDbContract.PlanetsEntry.SQL_DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void populateStarterData(SQLiteDatabase db) {
        db.execSQL(String.format("INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)" +
                   "VALUES (2, \"Alderaan\", 24, 364, 12500, \"temperate\", \"1 standard\", \"grasslands, mountains\", 40, 2000000000);",
                                 SwApiDbContract.PlanetsEntry.TABLE_NAME,
                                 SwApiDbContract.PlanetsEntry._ID,
                                 SwApiDbContract.PlanetsEntry.COLUMN_NAME_NAME,
                                 SwApiDbContract.PlanetsEntry.COLUMN_NAME_ROTATION_PERIOD,
                                 SwApiDbContract.PlanetsEntry.COLUMN_NAME_ORBITAL_PERIOD,
                                 SwApiDbContract.PlanetsEntry.COLUMN_NAME_DIAMETER,
                                 SwApiDbContract.PlanetsEntry.COLUMN_NAME_CLIMATE,
                                 SwApiDbContract.PlanetsEntry.COLUMN_NAME_GRAVITY,
                                 SwApiDbContract.PlanetsEntry.COLUMN_NAME_TERRAIN,
                                 SwApiDbContract.PlanetsEntry.COLUMN_NAME_SURFACE_WATER,
                                 SwApiDbContract.PlanetsEntry.COLUMN_NAME_POPULATION));

        db.execSQL(String.format("INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)" +
                   "VALUES (3, \"Yavin IV\", 24, 4818, 10200, \"temperate, tropical\", \"1 standard\", \"jungle, rainforests\", 8, 1000);",
                                 SwApiDbContract.PlanetsEntry.TABLE_NAME,
                                 SwApiDbContract.PlanetsEntry._ID,
                                 SwApiDbContract.PlanetsEntry.COLUMN_NAME_NAME,
                                 SwApiDbContract.PlanetsEntry.COLUMN_NAME_ROTATION_PERIOD,
                                 SwApiDbContract.PlanetsEntry.COLUMN_NAME_ORBITAL_PERIOD,
                                 SwApiDbContract.PlanetsEntry.COLUMN_NAME_DIAMETER,
                                 SwApiDbContract.PlanetsEntry.COLUMN_NAME_CLIMATE,
                                 SwApiDbContract.PlanetsEntry.COLUMN_NAME_GRAVITY,
                                 SwApiDbContract.PlanetsEntry.COLUMN_NAME_TERRAIN,
                                 SwApiDbContract.PlanetsEntry.COLUMN_NAME_SURFACE_WATER,
                                 SwApiDbContract.PlanetsEntry.COLUMN_NAME_POPULATION));

        db.execSQL("INSERT INTO planets (_id, name, rotation_period, orbital_period, diameter, climate, gravity, terrain, surface_water, population)\n" +
        "VALUES (4, \"Hoth\", 23, 549, 7200, \"frozen\", \"1.1 standard\", \"tundra, ice caves, mountain ranges\", 100, -1);\n");

        db.execSQL("INSERT INTO planets (_id, name, rotation_period, orbital_period, diameter, climate, gravity, terrain, surface_water, population)\n" +
        "VALUES (5, \"Dagobah\", 23, 341, 8900, \"murky\", \"n/a\", \"swamp, jungles\", 8, -1);");
        db.execSQL("INSERT INTO planets (_id, name, rotation_period, orbital_period, diameter, climate, gravity, terrain, surface_water, population)\n" +
        "VALUES (6, \"Bespin\", 12, 5110, 118000, \"temperate\", \"1.5 (surface), 1 standard (Cloud City)\", \"gas giant\", 0, 6000000);\n");

        db.execSQL("INSERT INTO planets (_id, name, rotation_period, orbital_period, diameter, climate, gravity, terrain, surface_water, population)\n" +
        "VALUES (7, \"Endor\", 18, 402, 4900, \"temperate\", \"0.85 standard\", \"forests, mountains, lakes\", 8, 30000000);\n");

        db.execSQL("INSERT INTO planets (_id, name, rotation_period, orbital_period, diameter, climate, gravity, terrain, surface_water, population)\n" +
        "VALUES (8, \"Naboo\", 26, 312, 12120, \"temperate\", \"1 standard\", \"grassy hills, swamps, forests, mountains\", 12, 4500000000);\n");

        db.execSQL("INSERT INTO planets (_id, name, rotation_period, orbital_period, diameter, climate, gravity, terrain, surface_water, population)\n" +
        "VALUES (9, \"Coruscant\", 24, 368, 12240, \"temperate\", \"1 standard\", \"cityscape, mountains\", -1, 1000000000000);\n");

        db.execSQL("INSERT INTO planets (_id, name, rotation_period, orbital_period, diameter, climate, gravity, terrain, surface_water, population)\n" +
        "VALUES (10, \"Kamino\", 27, 463, 19720, \"temperate\", \"1 standard\", \"ocean\", 100, 1000000000);\n");

        db.execSQL("INSERT INTO planets (_id, name, rotation_period, orbital_period, diameter, climate, gravity, terrain, surface_water, population)\n" +
        "VALUES (11, \"Geonosis\", 30, 256, 11370, \"temperate, arid\", \"0.9 standard\", \"rock, desert, mountain, barren\", 5, 100000000000);");

        db.execSQL("INSERT INTO species\n" +
        "(name, classification, designation, average_height, skin_colors, hair_colors, eye_colors, average_lifespan, native_language)\n" +
        "\tVALUES\n" +
        "    ('Yoda''s species', 'mammal', 'sentient', 66, 'green, yellow', 'borwn, white', 'brown, green, yellow', 900, 'Galactic basic');");

        db.execSQL("INSERT INTO species (name, classification, designation, average_height, skin_colors, hair_colors, eye_colors, average_lifespan, native_language) VALUES (\"Hutt\", \"gastropod\", \"sentient\", 300, \"green, brown, tan\", \"n/a\", \"yellow, red\", 1000, \"Huttese\");");

        db.execSQL("INSERT INTO species (name, classification, designation, average_height, skin_colors, hair_colors,eye_colors, average_lifespan, native_language)\n" +
        "VALUES (\"Trandoshan\", \"reptile\", \"sentient\", 200, \"brown, green\", \"none\",\"yellow, orange\", \"unknown\", \"Dosh\");");

        db.execSQL(String.format("\tINSERT INTO %s(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES (84, \"Finn\", -1, -1, \"black\", \"dark\", \"dark\", \"unknown\", \"male\", 28); ",
                                 SwApiDbContract.PeopleEntry.TABLE_NAME,
                                 SwApiDbContract.PeopleEntry._ID,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_NAME,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_HEIGHT,
                                 SwApiDbContract.PeopleEntry. COLUMN_NAME_MASS,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_HAIR_COLOR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_SKIN_COLOR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_EYE_COLOR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_BIRTH_YEAR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_GENDER,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_HOMEWORLD));

        db.execSQL(String.format("\tINSERT INTO %s(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES (85, \"Rey\", -1, -1, \"brown\", \"light\", \"hazel\", \"unknown\", \"female\", 28",
                                 SwApiDbContract.PeopleEntry.TABLE_NAME,
                                 SwApiDbContract.PeopleEntry._ID,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_NAME,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_HEIGHT,
                                 SwApiDbContract.PeopleEntry. COLUMN_NAME_MASS,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_HAIR_COLOR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_SKIN_COLOR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_EYE_COLOR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_BIRTH_YEAR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_GENDER,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_HOMEWORLD));

        db.execSQL(String.format("\tINSERT INTO %s(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES (86, \"Poe Dameron\", -1, -1, \"brown\", \"light\", \"brown\", \"unknown\", \"male\", 28",
                                 SwApiDbContract.PeopleEntry.TABLE_NAME,
                                 SwApiDbContract.PeopleEntry._ID,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_NAME,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_HEIGHT,
                                 SwApiDbContract.PeopleEntry. COLUMN_NAME_MASS,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_HAIR_COLOR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_SKIN_COLOR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_EYE_COLOR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_BIRTH_YEAR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_GENDER,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_HOMEWORLD));

        db.execSQL(String.format("\tINSERT INTO %s(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES (87, \"BB8\", -1, -1, \"none\", \"none\", \"black\", \"unknown\", \"none\", 28",
                                 SwApiDbContract.PeopleEntry.TABLE_NAME,
                                 SwApiDbContract.PeopleEntry._ID,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_NAME,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_HEIGHT,
                                 SwApiDbContract.PeopleEntry. COLUMN_NAME_MASS,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_HAIR_COLOR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_SKIN_COLOR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_EYE_COLOR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_BIRTH_YEAR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_GENDER,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_HOMEWORLD));

        db.execSQL(String.format("\tINSERT INTO %s(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES (33, \"Nute Gunray\", 191, 90, \"none\", \"mottled green\", \"red\", \"unknown\", \"male\", 18",
                                 SwApiDbContract.PeopleEntry.TABLE_NAME,
                                 SwApiDbContract.PeopleEntry._ID,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_NAME,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_HEIGHT,
                                 SwApiDbContract.PeopleEntry. COLUMN_NAME_MASS,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_HAIR_COLOR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_SKIN_COLOR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_EYE_COLOR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_BIRTH_YEAR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_GENDER,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_HOMEWORLD));

        db.execSQL(String.format("\tINSERT INTO %s(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES (32, \"Qui-Gon Jinn\", 193, 89, \"brown\", \"fair\", \"blue\", \"92BBY\", \"male\", 28",
                                 SwApiDbContract.PeopleEntry.TABLE_NAME,
                                 SwApiDbContract.PeopleEntry._ID,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_NAME,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_HEIGHT,
                                 SwApiDbContract.PeopleEntry. COLUMN_NAME_MASS,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_HAIR_COLOR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_SKIN_COLOR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_EYE_COLOR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_BIRTH_YEAR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_GENDER,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_HOMEWORLD));

        db.execSQL(String.format("\tINSERT INTO %s(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES (31, \"Nien Nunb\", 160, 68, \"none\", \"grey\", \"black\", \"unknown\", \"male\", 33",
                                 SwApiDbContract.PeopleEntry.TABLE_NAME,
                                 SwApiDbContract.PeopleEntry._ID,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_NAME,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_HEIGHT,
                                 SwApiDbContract.PeopleEntry. COLUMN_NAME_MASS,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_HAIR_COLOR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_SKIN_COLOR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_EYE_COLOR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_BIRTH_YEAR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_GENDER,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_HOMEWORLD));

        db.execSQL(String.format("\tINSERT INTO %s(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES (30, \"Wicket Systri Warrick\", 88, 20, \"brown\", \"brown\", \"brown\", \"8BBY\", \"male\", 7",
                                 SwApiDbContract.PeopleEntry.TABLE_NAME,
                                 SwApiDbContract.PeopleEntry._ID,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_NAME,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_HEIGHT,
                                 SwApiDbContract.PeopleEntry. COLUMN_NAME_MASS,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_HAIR_COLOR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_SKIN_COLOR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_EYE_COLOR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_BIRTH_YEAR,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_GENDER,
                                 SwApiDbContract.PeopleEntry.COLUMN_NAME_HOMEWORLD));
    }
}
