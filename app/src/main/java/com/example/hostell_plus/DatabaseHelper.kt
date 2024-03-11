package com.example.hostell_plus

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.File

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "students.db"
        private const val DATABASE_VERSION = 3

        const val TABLE_NAME = "students"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PHONE = "phone"
        const val COLUMN_DOB = "date_of_birth"
        const val COLUMN_ADDRESS = "address"
        const val COLUMN_REG_NUMBER = "registration_number"
        const val COLUMN_FATHER_NAME = "father_name"
        const val COLUMN_MOTHER_NAME = "mother_name"
        const val COLUMN_ROOM_NUMBER = "room_number"
        const val COLUMN_YEAR = "year"
        const val COLUMN_PARENT_NUM = "parent_phone"



        // table for complaints
        const val TABLE_COMPLAINTS = "complaints"
        const val COLUMN_COMPLAINT_ID = "complaint_id"
        const val COLUMN_COMPLAINT_TEXT = "complaint_text"
        const val COLUMN_STUDENT_ID = "student_id"
        // Table for users
        const val TABLE_USERS = "users"
        const val COLUMN_USER_ID = "user_id"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_USER_TYPE = "user_type"
        const val COLUMN_PASSWORD = "password"


        const val TABLE_ATTENDANCE = "attendance"
        const val COLUMN_STUDENT_NAME = "studentName"
        const val COLUMN_TIMESTAMP = "timestamp"
        const val COLUMN_SCANNED_DATA="scanneddata"

        const val TABLE_PAYMENTS = "payments"
        const val COLUMN_PAYMENT_ID = "payment_id"
        const val COLUMN_STUD_NAME = "student_name"
        const val COLUMN_AMOUNT_PAID = "amount_paid"
        const val COLUMN_PAYMENT_DATE = "payment_date"

        private const val TABLE_STUDENT = "students"
        private const val COLUMN_STUD_ID = "id"
        private const val COLUMN_STU_NAME = "name"
        private const val COLUMN_PARENT_PHONE = "parent_phone"

        const val TABLE_NOTICES = "notices"
        const val COLUMN__ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_CONTENT = "content"
        const val COLUMN_TIME_STAMP = "timestamp"

    }

    override fun onCreate(db: SQLiteDatabase) {
        val createStudentTableQuery = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER ," +
                "$COLUMN_NAME TEXT," +
                "$COLUMN_EMAIL TEXT," +
                "$COLUMN_PHONE TEXT," +
                "$COLUMN_ADDRESS TEXT," +
                "$COLUMN_REG_NUMBER TEXT PRIMARY KEY ," +
                "$COLUMN_FATHER_NAME TEXT," +
                "$COLUMN_MOTHER_NAME TEXT," +
                "$COLUMN_ROOM_NUMBER INTEGER," +
                "$COLUMN_PARENT_NUM INTEGER,"+
                "$COLUMN_YEAR INTEGER," +
                "$COLUMN_DOB VARCHAR" +// Include the "year" column
                ")"
        db.execSQL(createStudentTableQuery)



        val createUserTableQuery = "CREATE TABLE $TABLE_USERS (" +
                "$COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_USERNAME TEXT," +
                "$COLUMN_USER_TYPE TEXT," +
                "$COLUMN_PASSWORD TEXT "+
                ")"
        db.execSQL(createUserTableQuery)

        val createTableQuery = ("CREATE TABLE $TABLE_ATTENDANCE (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_STUDENT_NAME TEXT, " +
                "$COLUMN_SCANNED_DATA TEXT,"+
                "$COLUMN_TIMESTAMP DATETIME DEFAULT CURRENT_TIMESTAMP)")
        db.execSQL(createTableQuery)

        val createComplaintsTableQuery = ("CREATE TABLE $TABLE_COMPLAINTS (" +
                "$COLUMN_COMPLAINT_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_COMPLAINT_TEXT TEXT, " +
                "$COLUMN_STUDENT_ID INTEGER, " +
                "FOREIGN KEY($COLUMN_STUDENT_ID) REFERENCES $TABLE_NAME($COLUMN_ID))")
        db.execSQL(createComplaintsTableQuery)

        val createPaymentsTableQuery = ("CREATE TABLE $TABLE_PAYMENTS (" +
                "$COLUMN_PAYMENT_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_STUDENT_NAME TEXT, " +
                "$COLUMN_AMOUNT_PAID REAL, " +
                "$COLUMN_PAYMENT_DATE DATETIME DEFAULT CURRENT_TIMESTAMP)")
        db.execSQL(createPaymentsTableQuery)

        val createNoticeBoardTableQuery = "CREATE TABLE $TABLE_NOTICES (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_TITLE TEXT," +
                "$COLUMN_CONTENT TEXT," +
                "$COLUMN_TIMESTAMP DATETIME DEFAULT CURRENT_TIMESTAMP)"
        db.execSQL(createNoticeBoardTableQuery)


    }
    override fun getWritableDatabase(): SQLiteDatabase {
        if (!databaseFile.exists()) {
            onCreate(writableDatabase)
        }
        return super.getWritableDatabase()
    }

    private val databaseFile: File by lazy {
        context.getDatabasePath(DATABASE_NAME)
    }

    // ... other functions ...



    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PAYMENTS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NOTICES")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ATTENDANCE")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_COMPLAINTS")




        onCreate(db)
        if (oldVersion < newVersion) {
            when (oldVersion) {
                1 -> {
                    db.execSQL("ALTER TABLE $TABLE_NAME ADD COLUMN $COLUMN_YEAR INTEGER")
                }
                // Add more cases for other version upgrades if needed
            }
        }
    }
    // Update your import statement for ContentValues

    // Then use fully qualified name in your DatabaseHelper functions


    fun insertUser(username: String, userType: String, password: String): Long {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_USER_TYPE, userType)
            put(COLUMN_PASSWORD, password)
        }
        return db.insert(TABLE_USERS, null, contentValues)
    }

    fun readUser(username: String, password: String, userType: String): Boolean {
        val db = readableDatabase
        val selection = "$COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ? AND $COLUMN_USER_TYPE = ?"
        val selectionArgs = arrayOf(username, password, userType)
        val cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)
        val userExists = cursor.count > 0
        cursor.close()
        return userExists
    }




    fun insertStudent(student: StudentData): Long {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_NAME, student.name)
            put(COLUMN_EMAIL, student.email)
            put(COLUMN_PHONE, student.phone)
            put(COLUMN_ADDRESS, student.address)
            put(COLUMN_REG_NUMBER, student.registrationNumber)
            put(COLUMN_FATHER_NAME, student.fatherName)
            put(COLUMN_MOTHER_NAME, student.motherName)
            put(COLUMN_ROOM_NUMBER, student.roomNumber)
            put(COLUMN_YEAR, student.year)
            put(COLUMN_DOB,student.dob)// Include the "year" column
            // Include the image URI
        }
        return db.insert(TABLE_NAME, null, contentValues)
    }



    fun readStudent(student: StudentData):Boolean{
        val db=readableDatabase
        val selection = "${DatabaseHelper.COLUMN_YEAR} = ?"
        val selectionArgs = arrayOf(student.year.toString())
        val cursor= db.query(TABLE_NAME,null,selection,selectionArgs,null,null,null)
        val userExists = cursor.count>0
        cursor.close()
        return userExists

    }
    // In DatabaseHelper.kt
    fun isValidCredentials(username: String, password: String, userType: String): Boolean {
        val db = readableDatabase
        val selectionArgs = arrayOf(username, password, userType)
        val query = "SELECT * FROM $TABLE_USERS WHERE $COLUMN_USERNAME=? AND $COLUMN_PASSWORD=? AND $COLUMN_USER_TYPE=?"
        val cursor = db.rawQuery(query, selectionArgs)
        val isValid = cursor.count > 0
        cursor.close()
        return isValid
    }

    fun insertComplaint(complaintText: String, studentId: Long): Long {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_COMPLAINT_TEXT, complaintText)
            put(COLUMN_STUDENT_ID, studentId)
        }
        return db.insert(TABLE_COMPLAINTS, null, contentValues)
    }
    fun insertPayment(studentName: String, amountPaid: Double, paymentDate: String): Long {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_STUDENT_NAME, studentName)
            put(COLUMN_AMOUNT_PAID, amountPaid)
            put(COLUMN_PAYMENT_DATE, paymentDate)
        }
        return db.insert(TABLE_PAYMENTS, null, contentValues)
    }
    fun addStudent(student: Student) {
        val values = ContentValues().apply {
            put(COLUMN_NAME, student.name)
            put(COLUMN_PARENT_PHONE, student.parentPhoneNumber)
        }
        writableDatabase.insert(TABLE_STUDENT, null, values)
    }
    fun insertNotice(title: String, content: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, title)
            put(COLUMN_CONTENT, content)
        }
        return db.insert(TABLE_NOTICES, null, values)
    }


    @SuppressLint("Range")
    fun getAllNotices(): List<Notice> {
        val noticeList = mutableListOf<Notice>()
        val db = readableDatabase
        val cursor = db.query(TABLE_NOTICES, null, null, null, null, null, "$COLUMN_TIMESTAMP DESC")

        cursor.use {
            while (it.moveToNext()) {
                val id = it.getLong(it.getColumnIndex(COLUMN__ID))
                val title = it.getString(it.getColumnIndex(COLUMN_TITLE))
                val content = it.getString(it.getColumnIndex(COLUMN_CONTENT))
                val timestamp = it.getString(it.getColumnIndex(COLUMN_TIME_STAMP))
                noticeList.add(Notice(id, title, content, timestamp))
            }
        }

        return noticeList
    }

}

