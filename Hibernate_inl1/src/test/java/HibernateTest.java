import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import se.yrgo.domain.Student;
import se.yrgo.domain.Tutor;

import java.util.List;

public class HibernateTest {

    private static SessionFactory sessionFactory = null;

    public static void main(String[] args) {


        SessionFactory sf = getSessionFactory();
        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();
        Tutor theTutor = new Tutor("123ABC", "Nahid Vafaie", 50324);
        Student student1 = new Student("Amanda Vasilis");
        Student student2 = new Student("Adam Johansson");
        Student student3 = new Student("Linnéa Woxinger Sköld");

        session.save(student1);
        session.save(student2);
        session.save(student3);
        session.save(theTutor);

        theTutor.addStudentToTeachingGroup(student1);
        theTutor.addStudentToTeachingGroup(student2);
        theTutor.addStudentToTeachingGroup(student3);

        Tutor tutor_from_database = session.get(Tutor.class, 4);
        List<Student> students = tutor_from_database.getTeachingGroup();
        for(Student student: students) {
            System.out.println(student);
        }

        tx.commit();
        session.close();

    }
    private static SessionFactory getSessionFactory() {
        if(sessionFactory ==null) {
            Configuration configuration = new Configuration();
            configuration.configure();

            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }

}

