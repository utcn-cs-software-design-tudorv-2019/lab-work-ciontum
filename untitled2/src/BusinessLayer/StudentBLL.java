package BusinessLayer;

import dao.ProfileDao;
import Model.NoteData;
import Model.StudentData;
import dao.StudentDao;

import java.util.ArrayList;
import java.util.List;

public class StudentBLL {
    public StudentBLL(){

    }
    public static void addStudent2(String id, String nume, String grupa)
    {
        StudentDao.addStudent(new StudentData(id,nume,grupa));
    }
    public static void enroll(String id,String examen,String grupa)
    {
        StudentDao.enroll(new NoteData(id,examen,grupa));
    }
    public static  List<StudentData> viewProfile()
    {

        return ProfileDao.View();
    }
    public static ArrayList view2(ArrayList<StudentData> obj)
    {
        ArrayList s=new ArrayList();
        s.add(((StudentData)obj.get(0)).getID());
        s.add(((StudentData)obj.get(0)).getName());
        s.add(((StudentData)obj.get(0)).getGrupa());
        return s;
    }
    public static  String ViewGrade(String examen) {
        return ProfileDao.ViewNota(new NoteData(null, examen, null),null);

    }
}
