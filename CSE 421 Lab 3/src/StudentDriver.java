import java.util.ArrayList;
import java.util.List;

import ut.LetterGrade;
import ut.Student;


public class StudentDriver extends Student {
	
	List<Float> assignedGrades = new ArrayList<Float>();
	List<Short> assignedCredits = new ArrayList<Short>();
	
	public StudentDriver(String first, String last, Rank rank) {
		super("", "", Rank.UNDERGRAD);
	}

	public StudentDriver() {
		super("", "", Rank.UNDERGRAD);
	}
	
	public String getName() {
		return "Test Student";
	}

	public String getEmailAddress() {
		return "student.0@utexas.edu";
	}
	
	public void assignGrade (short credit, LetterGrade lettergrade) {
		assignedCredits.add(credit);
		assignedGrades.add(Driver.gradeLookup.get(lettergrade));
	}
	
	public float getGPA () {
		float totalgrades = 0.0f;
		int totalcredits = 0;
		for (int k = 0; k < assignedGrades.size(); k++) {
			totalgrades += (assignedGrades.get(k) * assignedCredits.get(k));
			totalcredits += assignedCredits.get(k);
		}
		return totalgrades / totalcredits;
	}
	
	public int getTotalCredits() {
		int result = 0;
		for (short c: assignedCredits) {
			result += c;
		}
		return result;
	}

}
