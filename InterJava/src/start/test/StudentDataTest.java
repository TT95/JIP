package start.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import start.Gender;
import start.StudentData;
import start.ex.IncorrectDate;
import start.ex.IncorrectGender;
import start.ex.IncorrectInput;
import start.ex.IncorrectLastName;
import start.ex.IncorrectName;

/**
 * Created by teo on 11/1/16.
 */
public class StudentDataTest {

    @Test
    public void argumentsExample1() throws IncorrectInput {
        StudentData student = new StudentData(" M; john; Brown; 1989/03/20;2010/09/01");
        assertEquals("John", student.getFirstName());
    }

    @Test
    public void argumentsExample2() throws IncorrectInput {
        StudentData student = new StudentData("Male; John; Brown-Jones; 89/03/20;2010/09/01");
        assertEquals(Gender.MALE, student.getGender());
    }

    @Test
    public void argumentsExample3() throws IncorrectInput {
        StudentData student = new StudentData("F;Mary; Brown; 89/02/28;10/09/01");
        assertEquals("Brown", student.getLastName());
    }

    @Test (expected = IncorrectDate.class)
    public void argumentTest1() throws IncorrectInput {
        new StudentData("Male; John; Brown-Jones; 2009/03/20;2010/09/01");
    }

    @Test (expected = IncorrectName.class)
    public void argumentTest2() throws IncorrectInput {
        new StudentData("Male; Josko; Brown-Jones; 89/03/20;2010/09/01");
    }

    @Test (expected = IncorrectLastName.class)
    public void argumentTest3() throws IncorrectInput {
        new StudentData("Male; John; BrownJones; 89/03/20;2010/09/01");
    }

    @Test (expected = IncorrectGender.class)
    public void argumentTest4() throws IncorrectInput {
        new StudentData("Bird; John; Brown-Jones; 89/03/20;2010/09/01");
    }

    @Test (expected = IncorrectDate.class)
    public void argumentTest5() throws IncorrectInput {
        new StudentData("Male; John; Brown-Jones; 89/20;1990/09/01");
    }

    @Test (expected = IncorrectDate.class)
    public void argumentTest6() throws IncorrectInput {
        new StudentData("Male; John; Brown-Jones; 89/2/30;1990/09/01");
    }

}