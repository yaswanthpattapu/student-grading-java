package in.one2n.exercise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Grader {

    public List<Student> parseCSV(String filepath){
        // TODO: add your implementation here
    	List<Student> all_students = new ArrayList<Student>();
    	BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(filepath)));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
    	try {
    		String line = br.readLine(); //First row (Skipping)
    		while((line = br.readLine()) !=null) {
    			String[] items = line.split(",");
    			try {
    				if (items.length>7) throw new ArrayIndexOutOfBoundsException();
    				Student student= new Student(items[0],items[1],items[2],Double.parseDouble(items[3]),Double.parseDouble(items[4]),Double.parseDouble(items[5]),Double.parseDouble(items[6]));
    				all_students.add(student);
    			}
    			catch(ArrayIndexOutOfBoundsException|NumberFormatException|NullPointerException e)
    			{
    				e.printStackTrace();
    			}
    		}
    	} catch (IOException e1) {
			e1.printStackTrace();
		}
    	finally {
    		try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	
        return all_students;
    }

    public List<Student> calculateGrade(List<Student> students) {
        // TODO: add your implementation here
    	for(int i=0;i<students.size();i++)
    	{
    		//Setting the grade of the student based on given instructions
    		double f_score = students.get(i).getFinalScore();
    		if(f_score < 35)
    			students.get(i).setGrade(Grade.F);
    		else if(f_score >= 35 && f_score<50)
    			students.get(i).setGrade(Grade.C);
    		else if(f_score >= 50 && f_score<70)
    			students.get(i).setGrade(Grade.B);
    		else if(f_score >= 70)
    			students.get(i).setGrade(Grade.A);
    	}
    	
    	return students;
    }

    public Student findOverallTopper(List<Student> gradedStudents) {
        // TODO: add your implementation here
    	int c=0;
    	for(int i=0;i<gradedStudents.size();i++)
    	{
    		//comparing all students final score
    		if(gradedStudents.get(i).getFinalScore()>gradedStudents.get(c).getFinalScore())
    			c=i;
    	}
    	return gradedStudents.get(c);
    }

    public Map<String, Student> findTopperPerUniversity(List<Student> gradedStudents) {
        // TODO: add your implementation here
    	
    	Set<String> unique_universities_set = new HashSet<String>();
    	for(int i=0;i<gradedStudents.size();i++)
    	{
    		unique_universities_set.add(gradedStudents.get(i).getUniversity());
    	}
    	List<String> unique_universities = new ArrayList<String>(unique_universities_set);
    	Map<String, Student> hashmap = new HashMap<>();
    	for(int i=0;i<unique_universities.size();i++)
    	{
    		Student max = new Student();
    		String university = unique_universities.get(i);
    		for(int j=0; j< gradedStudents.size();j++)
    		{
    			if(university.equals(gradedStudents.get(j).getUniversity()))
    			{
    				//First Student matching with university name(to ignore null pointer reference)
    				max = gradedStudents.get(j);  
    				break;
    			}
    			
    		}
    		for(int j=0; j< gradedStudents.size();j++)
    		{
    			Student std = gradedStudents.get(j);
    			//Comparing every Student within same university
    			if(university.equals(std.getUniversity()) && max.getFinalScore()<std.getFinalScore())
    				max = std;
    		}
    		hashmap.put(university, max);
    	}

    	return hashmap;
    }
}
