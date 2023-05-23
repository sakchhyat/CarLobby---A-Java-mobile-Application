package com.example.carlobby;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    // Database version
    private static final int DATABASE_VERSION = 1;

    // Database name
    private static final String DATABASE_NAME = "car_dealership";

    // Table names
    private static final String TABLE_CARS = "cars";
    private static final String TABLE_COMPANIES = "companies";

    // Table columns for cars table
    private static final String KEY_ID = "id";
    private static final String KEY_MAKE = "make";
    private static final String KEY_MODEL = "model";
    private static final String KEY_CONDITION = "condition";
    private static final String KEY_CYLINDERS = "cylinders";
    private static final String KEY_YEAR = "year";
    private static final String KEY_DOORS = "doors";
    private static final String KEY_PRICE = "price";
    private static final String KEY_COLOR = "color";
    private static final String KEY_IMAGE_THUMBNAIL = "image_thumbnail";
    private static final String KEY_IMAGE_FULL = "image_full";
    private static final String KEY_DATE_SOLD = "date_sold";
    private static final String KEY_IS_SOLD = "is_sold";

    // Table columns for companies table
    private static final String KEY_COMPANY_ID = "company_id";
    private static final String KEY_COMPANY_NAME = "company_name";
    private static final String KEY_COMPANY_ADDRESS = "company_address";
    private static final String KEY_COMPANY_PHONE = "company_phone";
    private static final String KEY_COMPANY_EMAIL = "company_email";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create cars table
        String CREATE_CARS_TABLE = "CREATE TABLE " + TABLE_CARS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_MAKE + " TEXT,"
                + KEY_MODEL + " TEXT,"
                + KEY_CONDITION + " TEXT,"
                + KEY_CYLINDERS + " TEXT,"
                + KEY_YEAR + " INTEGER,"
                + KEY_DOORS + " INTEGER,"
                + KEY_PRICE + " REAL,"
                + KEY_COLOR + " TEXT,"
                + KEY_IMAGE_THUMBNAIL + " BLOB,"
                + KEY_IMAGE_FULL + " BLOB,"
                + KEY_DATE_SOLD + " INTEGER,"
                + KEY_IS_SOLD + " INTEGER"
                + ")";
        db.execSQL(CREATE_CARS_TABLE);

        // Create companies table
        String CREATE_COMPANIES_TABLE = "CREATE TABLE " + TABLE_COMPANIES + "("
                + KEY_COMPANY_ID + " INTEGER PRIMARY KEY,"
                + KEY_COMPANY_NAME + " TEXT,"
                + KEY_COMPANY_ADDRESS + " TEXT,"
                + KEY_COMPANY_PHONE + " TEXT,"
                + KEY_COMPANY_EMAIL + " TEXT"
                + ")";
        db.execSQL(CREATE_COMPANIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPANIES);
        onCreate(db);
    }

    public void addCar(Car car) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MAKE, car.getMake());
        values.put(KEY_MODEL, car.getModel());
        values.put(KEY_CONDITION, car.getCondition());
        values.put(KEY_CYLINDERS, car.getCylinders());
        values.put(KEY_YEAR, car.getYear());
        values.put(KEY_DOORS, car.getDoors());
        values.put(KEY_PRICE, car.getPrice());
        values.put(KEY_COLOR, car.getColor());
        values.put(KEY_IS_SOLD, car.getIsSold() ? 1 : 0);

        db.insert(TABLE_CARS, null, values);

    }

    public Car getCar(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CARS, new String[] { KEY_ID, KEY_MAKE, KEY_MODEL,
                        KEY_CONDITION, KEY_CYLINDERS, KEY_YEAR, KEY_DOORS, KEY_PRICE, KEY_COLOR,
                        KEY_IMAGE_THUMBNAIL, KEY_IMAGE_FULL, KEY_DATE_SOLD, KEY_IS_SOLD }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Car car = new Car(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4), Integer.parseInt(cursor.getString(5)),
                Integer.parseInt(cursor.getString(6)), Double.parseDouble(cursor.getString(7)),
                cursor.getString(8), cursor.getInt(11)!=0);

        cursor.close();


        return car;
    }

    public ArrayList<Car> getAllCars() {
        ArrayList<Car> carList = new ArrayList<Car>();

        String selectQuery = "SELECT  * FROM " + TABLE_CARS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Car car = new Car();
                car.setId(Integer.parseInt(cursor.getString(0)));
                car.setMake(cursor.getString(1));
                car.setModel(cursor.getString(2));
                car.setCondition(cursor.getString(3));
                car.setCylinders(cursor.getString(4));
                car.setYear(Integer.parseInt(cursor.getString(5)));
                car.setDoors(Integer.parseInt(cursor.getString(6)));
                car.setPrice(Double.parseDouble(cursor.getString(7)));
                car.setColor(cursor.getString(8));
                car.setIsSold(cursor.getInt(12) != 0);

                carList.add(car);
            } while (cursor.moveToNext());
        }

        cursor.close();


        return carList;
    }


    public ArrayList<Car> getSoldCars() {
        ArrayList<Car> carList = new ArrayList<Car>();

        String selectQuery = "SELECT  * FROM " + TABLE_CARS + " WHERE " + KEY_IS_SOLD + " = 1";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Car car = new Car();
                car.setId(Integer.parseInt(cursor.getString(0)));
                car.setMake(cursor.getString(1));
                car.setModel(cursor.getString(2));
                car.setCondition(cursor.getString(3));
                car.setCylinders(cursor.getString(4));
                car.setYear(Integer.parseInt(cursor.getString(5)));
                car.setDoors(Integer.parseInt(cursor.getString(6)));
                car.setPrice(Double.parseDouble(cursor.getString(7)));
                car.setColor(cursor.getString(8));
                car.setIsSold(cursor.getInt(11) != 0);

                carList.add(car);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return carList;
    }

    public ArrayList<Car> getAvailableCars() {
        ArrayList<Car> carList = new ArrayList<Car>();

        String selectQuery = "SELECT  * FROM " + TABLE_CARS + " WHERE " + KEY_IS_SOLD + " = 0";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Car car = new Car();
                car.setId(Integer.parseInt(cursor.getString(0)));
                car.setMake(cursor.getString(1));
                car.setModel(cursor.getString(2));
                car.setCondition(cursor.getString(3));
                car.setCylinders(cursor.getString(4));
                car.setYear(Integer.parseInt(cursor.getString(5)));
                car.setDoors(Integer.parseInt(cursor.getString(6)));
                car.setPrice(Double.parseDouble(cursor.getString(7)));
                car.setColor(cursor.getString(8));
                car.setIsSold(cursor.getInt(11) != 0);

                carList.add(car);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return carList;
    }


    public int updateCar(Car car, boolean isSold) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MAKE, car.getMake());
        values.put(KEY_MODEL, car.getModel());
        values.put(KEY_CONDITION, car.getCondition());
        values.put(KEY_CYLINDERS, car.getCylinders());
        values.put(KEY_YEAR, car.getYear());
        values.put(KEY_DOORS, car.getDoors());
        values.put(KEY_PRICE, car.getPrice());
        values.put(KEY_COLOR, car.getColor());
        values.put(KEY_IS_SOLD, isSold ? 1 : 0);

        int rowsAffected = db.update(TABLE_CARS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(car.getId()) });

        return rowsAffected;
    }
    public long addCompany(Company company) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_COMPANY_NAME, company.getName());
        values.put(KEY_COMPANY_ADDRESS, company.getAddress());
        values.put(KEY_COMPANY_PHONE, company.getPhone());
        values.put(KEY_COMPANY_EMAIL, company.getEmail());

        long id = db.insert(TABLE_COMPANIES, null, values);
        db.close();

        return id;
    }


    public int updateCompany(Company company) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_COMPANY_NAME, company.getName());
        values.put(KEY_COMPANY_ADDRESS, company.getAddress());
        values.put(KEY_COMPANY_PHONE, company.getPhone());
        values.put(KEY_COMPANY_EMAIL, company.getEmail());

        int rowsAffected = db.update(TABLE_COMPANIES, values, KEY_COMPANY_ID + " = ?",
                new String[] { String.valueOf(company.getId()) });

        return rowsAffected;
    }

    public Company getCompany(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_COMPANIES, new String[] {
                KEY_COMPANY_ID,
                KEY_COMPANY_NAME,
                KEY_COMPANY_ADDRESS,
                KEY_COMPANY_PHONE,
                KEY_COMPANY_EMAIL
        }, KEY_COMPANY_ID+ "=?", new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        @SuppressLint("Range") Company company = new Company(
                cursor.getInt(cursor.getColumnIndex(KEY_COMPANY_ID)),
                cursor.getString(cursor.getColumnIndex(KEY_COMPANY_NAME)),
                cursor.getString(cursor.getColumnIndex(KEY_COMPANY_ADDRESS)),
                cursor.getString(cursor.getColumnIndex(KEY_COMPANY_PHONE)),
                cursor.getString(cursor.getColumnIndex(KEY_COMPANY_EMAIL))
        );

        cursor.close();
        db.close();

        return company;
    }

    public int getNumberOfCarsSold() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_CARS + " WHERE " + KEY_IS_SOLD + " = 1", null);

        if (cursor != null)
            cursor.moveToFirst();

        int count = cursor.getInt(0);

        cursor.close();

        return count;
    }

    public double getProfitFromCarSales() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT SUM(" + KEY_PRICE + ") FROM " + TABLE_CARS + " WHERE " + KEY_IS_SOLD + " = 1", null);

        if (cursor != null)
            cursor.moveToFirst();

        double profit = cursor.getDouble(0);

        cursor.close();

        return profit;
    }

}