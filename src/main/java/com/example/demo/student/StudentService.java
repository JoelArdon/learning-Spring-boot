package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private  final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }
   public void addNewStudent(Student student){
        if(studentRepository.findStudentsByEmail(student.getEmail()).isPresent()){
            throw  new IllegalStateException("Email has already been taken");
       }
        studentRepository.save(student);
   }
   public void deleteStudent(Long studentId){
        if(!studentRepository.existsById(studentId)){
            throw  new IllegalStateException("Student with id "+studentId+" does not exist");
       }
        studentRepository.deleteById(studentId);
   }
   @Transactional
    public void updateStudent(String name,String email,Long studentId){
       Student student= studentRepository.findById(studentId).orElseThrow(()->new IllegalStateException("Student with id: " + studentId+" does not exist."));
      if(name!=null && name.length()> 0 && !student.getName().equals(name)) {
          student.setName(name);
      }
       if(email!=null && email.length()> 0 && !student.getEmail().equals(email)) {
           if(studentRepository.findStudentsByEmail(email).isPresent()){
               throw new IllegalStateException("Email already taken");
           }
           student.setEmail(email);
       }

   }

}
