import ut.LetterGrade;
import ut.Prof;
import ut.Student;


public class ProfDriver {

	StudentDriver[] students;
	short credits;
	
	public ProfDriver(StudentDriver[] allStudents) {
		students = allStudents;
		credits = (short) ((Math.random() * 5) + 1);
	}
	
	public ProfDriver(StudentDriver[] allStudents, short creditHours) {
		students = allStudents;
		credits = creditHours;
	}
	
	public StudentDriver[] evaluate() {
		for (int k = 0; k < students.length; k++) {
			students[k].assignGrade(credits, LetterGrade.BPURE);
		}
		return students;
	}
	
	public StudentDriver[] evaluate(LetterGrade[] grades) {
		for (int k = 0; k < students.length; k++) {
			students[k].assignGrade(credits, grades[k]);
		}
		return students;
	}
	
	public short getCredits() {
		return credits;
	}

}
