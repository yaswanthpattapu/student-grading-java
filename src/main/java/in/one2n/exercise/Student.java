package in.one2n.exercise;

public class Student {

    private String firstname;
    private String lastname;
    private String university;
    private Double test1Score;
    private Double test2Score;
    private Double test3Score;
    private Double test4Score;

    // computed fields
    private Double finalScore;
    private Grade grade;

    public Student()
    {
    	super();
    }
    
    public Student(String firstname, String lastname, String university) {
        super();
    	this.firstname = firstname;
    	this.lastname = lastname;
    	this.university = university;
    	this.test1Score = null;
    	this.test2Score = null;
    	this.test3Score = null;
    	this.test4Score = null;
    	this.finalScore = null;
    	this.grade = null;
    }

    public Student(String firstname, String lastname, String university, Double test1Score, Double test2Score, Double test3Score, Double test4Score) {
    	super();
    	this.firstname = firstname;
    	this.lastname = lastname;
    	this.university = university;
    	this.test1Score = test1Score;
    	this.test2Score = test2Score;
    	this.test3Score = test3Score;
    	this.test4Score = test4Score;
    	this.finalScore = (this.test1Score+this.test2Score+this.test3Score+this.test4Score)/4;
    	this.grade = null;
    }

    public Double getFinalScore() {
    	return finalScore;
    }

    public Grade getGrade() { 	
        return grade;
    }

	public String getUniversity() {
		return university;
	}
	
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
	public Grade get_grade()
	{
		double f_score = this.getFinalScore();
		if(f_score < 35)
			return Grade.F;
		else if(f_score >= 35 && f_score<50)
			return Grade.C;
		else if(f_score >= 50 && f_score<70)
			return Grade.B;
		else
			return Grade.A;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null || this.getClass() != obj.getClass())
			return false;
		Student std = (Student) obj;
		
		return this.firstname.equals(std.firstname)
				&& this.lastname.equals(std.lastname)
				&& this.university.equals(std.university);
	}	
}