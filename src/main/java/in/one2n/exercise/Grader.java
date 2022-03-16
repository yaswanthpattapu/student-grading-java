package in .one2n.exercise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Grader {

    public List <Student> parseCSV(String filepath) {
        List <Student> all_students = new ArrayList <Student> ();

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));
            all_students = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return all_students;
    }

    public List <Student> calculateGrade(List <Student> students) {
        students.stream().forEach(eachstudent -> eachstudent.setGrade(eachstudent.get_grade()));
        return students;
    }

    public Student findOverallTopper(List <Student> gradedStudents) {
        return gradedStudents.stream().max(Comparator.comparingDouble(Student::getFinalScore)).get();
    }

    public Map <String, Student> findTopperPerUniversity(List <Student> gradedStudents) {
        List <String> universities = gradedStudents.stream().map(Student::getUniversity).collect(Collectors.toList());
        List <String> unique_universities = universities.stream().distinct().collect(Collectors.toList());
        Map <String, Student> hashmap = new HashMap<>();        
        unique_universities.forEach((university) -> {
        	hashmap.put(university, findUniversityTopper(gradedStudents,university));
        });       
        return hashmap;
    }

    private Function <String, Student> mapToItem = (line) -> {
        String[] items = line.split(",");
        Student student = new Student(items[0], items[1], items[2], Double.parseDouble(items[3]), Double.parseDouble(items[4]), Double.parseDouble(items[5]), Double.parseDouble(items[6]));
        return student;
    };
    
    private Student findUniversityTopper(List <Student> students, String university)
    {
    	return students.stream().filter(m -> m.getUniversity().equals(university)).max(Comparator.comparingDouble(Student::getFinalScore)).get();
    }
}