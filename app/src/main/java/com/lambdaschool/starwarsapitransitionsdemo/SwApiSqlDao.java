package com.lambdaschool.starwarsapitransitionsdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SwApiSqlDao {
    private SQLiteDatabase db;

    public SwApiSqlDao(Context context) {
        SwApiDbHelper dbHelper = new SwApiDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public Planet getPlanet(int i) {
        final Cursor cursor = db.rawQuery("SELECT * FROM d" + SwApiDbContract.PlanetsEntry.TABLE_NAME + " WHERE _id=?", new String[]{Integer.toString(i)});

//        int id, String name, String rotationPeriod, String orbitalPeriod, String diameter, String climate, String gravity, String terrain
        cursor.moveToFirst();

        int index = cursor.getColumnIndexOrThrow(SwApiDbContract.PlanetsEntry._ID);
        int id    = cursor.getInt(index);

        index = cursor.getColumnIndexOrThrow(SwApiDbContract.PlanetsEntry.COLUMN_NAME_NAME);
        String name = cursor.getString(index);

        index = cursor.getColumnIndexOrThrow(SwApiDbContract.PlanetsEntry.COLUMN_NAME_ROTATION_PERIOD);
        int rotationPeriod = cursor.getInt(index);

        index = cursor.getColumnIndexOrThrow(SwApiDbContract.PlanetsEntry.COLUMN_NAME_ORBITAL_PERIOD);
        int orbitalPeriod = cursor.getInt(index);

        index = cursor.getColumnIndexOrThrow(SwApiDbContract.PlanetsEntry.COLUMN_NAME_DIAMETER);
        int diameter = cursor.getInt(index);

        index = cursor.getColumnIndexOrThrow(SwApiDbContract.PlanetsEntry.COLUMN_NAME_CLIMATE);
        String climate = cursor.getString(index);

        index = cursor.getColumnIndexOrThrow(SwApiDbContract.PlanetsEntry.COLUMN_NAME_GRAVITY);
        String gravity = cursor.getString(index);

        index = cursor.getColumnIndexOrThrow(SwApiDbContract.PlanetsEntry.COLUMN_NAME_TERRAIN);
        String terrain = cursor.getString(index);

        return new Planet(id, name, Integer.toString(rotationPeriod),
                          Integer.toString(orbitalPeriod),
                          Integer.toString(diameter),
                          climate, gravity, terrain);
    }

    public void createPerson(Person person) {
        ContentValues values = getContentValues(person);

        final long insert = db.insert(SwApiDbContract.PeopleEntry.TABLE_NAME, null, values);
    }

    public void updatePerson(Person person) {
        int affectedRows = db.update(
                SwApiDbContract.PeopleEntry.TABLE_NAME,
                getContentValues(person),
                SwApiDbContract.PeopleEntry._ID + "=?",
                new String[]{Integer.toString(person.getId())});
    }

    public void deletePerson(Person person) {
        int affectedRows = db.delete(SwApiDbContract.PeopleEntry.TABLE_NAME,
                                     SwApiDbContract.PeopleEntry._ID + "=?",
                                     new String[]{Integer.toString(person.getId())});
    }

    private ContentValues getContentValues(Person person) {
        ContentValues values = new ContentValues();
//        int id, String name, String height, String mass, String hairColor, String skinColor, String eyeColor

        values.put(SwApiDbContract.PeopleEntry._ID, person.getId());
        values.put(SwApiDbContract.PeopleEntry.COLUMN_NAME_NAME, person.getName());
        values.put(SwApiDbContract.PeopleEntry.COLUMN_NAME_HEIGHT, Integer.parseInt(person.getHeight()));
        values.put(SwApiDbContract.PeopleEntry.COLUMN_NAME_MASS, Integer.parseInt(person.getMass()));
        values.put(SwApiDbContract.PeopleEntry.COLUMN_NAME_HAIR_COLOR, person.getHairColor());
        values.put(SwApiDbContract.PeopleEntry.COLUMN_NAME_SKIN_COLOR, person.getSkinColor());
        values.put(SwApiDbContract.PeopleEntry.COLUMN_NAME_EYE_COLOR, person.getEyeColor());
        return values;
    }

    public List<Person> getAllPeople() {
        final Cursor cursor = db.rawQuery("SELECT * FROM " + SwApiDbContract.PeopleEntry.TABLE_NAME,
                                          new String[]{});

        List<Person> rows = new ArrayList<>();
        while (cursor.moveToNext()) {
            rows.add(getPerson(cursor));
        }

        cursor.close();
        return rows;
    }

    private Person getPerson(Cursor cursor) {
        int index;
        index = cursor.getColumnIndexOrThrow(SwApiDbContract.PeopleEntry._ID);
        int id = cursor.getInt(index);

        index = cursor.getColumnIndexOrThrow(SwApiDbContract.PeopleEntry.COLUMN_NAME_NAME);
        String name = cursor.getString(index);

        index = cursor.getColumnIndexOrThrow(SwApiDbContract.PeopleEntry.COLUMN_NAME_HEIGHT);
        int height = cursor.getInt(index);

        index = cursor.getColumnIndexOrThrow(SwApiDbContract.PeopleEntry.COLUMN_NAME_MASS);
        int mass = cursor.getInt(index);

        index = cursor.getColumnIndexOrThrow(SwApiDbContract.PeopleEntry.COLUMN_NAME_HAIR_COLOR);
        String hairColor = cursor.getString(index);

        index = cursor.getColumnIndexOrThrow(SwApiDbContract.PeopleEntry.COLUMN_NAME_EYE_COLOR);
        String eyeColor = cursor.getString(index);

        index = cursor.getColumnIndexOrThrow(SwApiDbContract.PeopleEntry.COLUMN_NAME_SKIN_COLOR);
        String skinColor = cursor.getString(index);

        return new Person(id, name, Integer.toString(height),
                          Integer.toString(mass), hairColor,
                          skinColor, eyeColor);
    }
}
