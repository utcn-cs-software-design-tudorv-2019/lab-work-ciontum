package BusinessLayer;

import Model.NoteData;
import Model.StudentData;
import dao.AdminDao;
import dao.ProfileDao;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class AdminBLL {
    public AdminBLL() {

    }

    public static List<StudentData> load() {
        return AdminDao.loadStudentData();
    }

    public static void addStudent(String id, String nume, String grupa,String username) {
        AdminDao.addStudent(new StudentData(id, nume, grupa),username);
    }

    public static void deleteStudent(int l2,String temp) throws SQLException
    {
        AdminDao.deleteStudent(l2,temp);
    }
    public static void updateStudent(String id,String nume,String grupa,int l2,String temp,String user) throws SQLException
    {
        AdminDao.updateStudent(new StudentData(id,nume,grupa),l2,temp,user);
    }
    public static void addGrade(String nota,int l2,String temp,String temp2) throws SQLException
    {
        AdminDao.addGrade(new NoteData(null,null,nota),l2,temp,temp2);
    }
    public static List<NoteData> loadGrade() throws SQLException{
        return AdminDao.loadStudentData2();
    }

}
