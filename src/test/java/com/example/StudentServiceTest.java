package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    @Test
    public void testAddAndGetStudents() {
        StudentService service = new StudentService();
        Student student = new Student(101, "Charlie");

        service.addStudent(student);

        assertEquals(1, service.getStudents().size());
        assertEquals("Charlie", service.getStudents().get(0).getName());
    }
}