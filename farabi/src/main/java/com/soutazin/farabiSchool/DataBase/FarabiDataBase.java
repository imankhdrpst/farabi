package com.soutazin.farabiSchool.DataBase;

/**
 * Created by User on 5/27/2017.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.soutazin.farabiSchool.Model.Part;
import com.soutazin.farabiSchool.Model.PictureInSubject;
import com.soutazin.farabiSchool.Model.Section;
import com.soutazin.farabiSchool.Model.Subject;
import com.soutazin.farabiSchool.utilities.PreferencesHelper;

import java.util.ArrayList;

public class FarabiDataBase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "farabidb.db";
    private static final int DATABASE_VERSION = 1;
    private static Context _context = null;

    public FarabiDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        _context= context;

    }

    public ArrayList<Section> getSections(int partId){
        SQLiteDatabase db = getWritableDatabase();
        String[] columns = {"ID","Title"};
        Cursor cursor = db.query("Section", columns , " PartID = " + partId , null, null, null, null);
//        Cursor cursor=db.query(MyDatabase.TABLE_NAME, columns, null,null, null, null, null);
        ArrayList<Section> allSections=new ArrayList<>();
//
        int id = 0;
        while(cursor.moveToNext()){
            Section section=new Section();
            section.id=cursor.getInt(cursor.getColumnIndex("ID"));
            section.title=cursor.getString(cursor.getColumnIndex("Title"));
            id = (PreferencesHelper.getInstance().isNowPersian() ? section.id : section.id - 22);
            section.picResId =  _context.getResources().getIdentifier("inst" + id, "drawable", _context.getPackageName());
            allSections.add(section);
        }

        return allSections;
    }


    public ArrayList<Part> getParts()
    {
        SQLiteDatabase db = getWritableDatabase();
        String[] columns = { "ID" , "Title"};
        Cursor cursor = db.query("Part" , columns, " Language = " + (PreferencesHelper.getInstance().isNowPersian() ? " 0 " : " 1 "), null , null , null, null);
        ArrayList<Part> allParts = new ArrayList<>();
        while (cursor.moveToNext())
        {
            Part part = new Part();
            part.id = cursor.getInt(cursor.getColumnIndex("ID"));
            part.title = cursor.getString(cursor.getColumnIndex("Title"));
            allParts.add(part);
        }

        return allParts;
    }

    public Part[] getPartsAsArray()
    {
        ArrayList<Part> allPartsList = getParts();
        return (Part[]) allPartsList.toArray();
    }


    public ArrayList<Subject> getSubjectsOfSection(int id) {
        ArrayList<Subject> result = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String[] columns = { "ID" , "Title", "Text" };
        Cursor cursor = db.query("Subject" , columns, " SectionID = " + id, null , null , null, null);
        while (cursor.moveToNext())
        {
            Subject subject = new Subject();
            subject.id = cursor.getInt(cursor.getColumnIndex("ID"));
            subject.title = cursor.getString(cursor.getColumnIndex("Title"));
            subject.text = cursor.getString(cursor.getColumnIndex("Text"));
            result.add(subject);
        }


        for (Subject subject : result)
        {
            subject.pictures = getPicturesOfASubject(subject.id);
        }

        return  result;
    }

    private ArrayList<PictureInSubject> getPicturesOfASubject(int id) {
        ArrayList<PictureInSubject> result = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM PicturesInSubjects picInSub\n" +
                "INNER JOIN Picture pic ON picInSub.PictureId = pic.ID\n" +
                "WHERE picInSub.SubjectID = ?" ;

        Cursor cursor =  db.rawQuery(query, new String[]{String.valueOf(id)});

        //db.query("Subject" , columns, " SectionID = " + id, null , null , null, null);
        while (cursor.moveToNext())
        {
            PictureInSubject pictureInSubject = new PictureInSubject();
            pictureInSubject.id = cursor.getInt(cursor.getColumnIndex("ID"));
            pictureInSubject.description = cursor.getString(cursor.getColumnIndex("Title"));
            pictureInSubject.url = cursor.getString(cursor.getColumnIndex("Address"));
            result.add(pictureInSubject);
        }
        return  result;
    }

}


