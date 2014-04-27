package ut;
public class Grad implements Graded {

	private LetterGrade[] grades;
	private int[] credits;
	int totalCredits;

	public Grad()// Instantiates variables as an empty array
	{
		grades = new LetterGrade[0];
		credits = new int[0];
	}

	public void assignGrade(short credit, LetterGrade grade) {

		assert (1 <= credit && credit <= 5);
		LetterGrade[] gradeTemp = new LetterGrade[grades.length + 1];
		int[] creditTemp = new int[grades.length + 1];
		System.arraycopy(grades, 0, gradeTemp, 1, grades.length);
		System.arraycopy(credits, 0, creditTemp, 1, grades.length);
		// Assign the new grades to the empty spots to complete new array
		gradeTemp[0] = grade;
		creditTemp[0] = credit;
		grades = gradeTemp;
		credits = creditTemp;

		totalCredits += credit;
	}

	public float getGPA() {
		assert (totalCredits > 0); // Check for wrong input values (requires clause), continue if valid
		float result = 0;

		for (int i = 0; i < grades.length; i++) {
			LetterGrade gradeTotal = grades[i];
			int credit = credits[i];
			switch (gradeTotal) {
			case APURE:
				result += 4.0 * credit;
				break;
			case AMINUS:
				result += 3.7 * credit;
				break;
			case BPLUS:
				result += 3.3 * credit;
				break;
			case BPURE:
				result += 3.0 * credit;
				break;
			case BMINUS:
				result += 2.7 * credit;
				break;
			case CPLUS:
				result += 2.3 * credit;
				break;
			case CPURE:
				result += 2.0 * credit;
				break;
			case CMINUS:
				result += 1.7 * credit;
				break;
			case DPLUS:
				result += 1.3 * credit;
				break;
			case DPURE:
				result += 1.0 * credit;
				break;
			}
		}

		result /= totalCredits;
		return result;
	}

	public LetterGrade getLastGrade() {
		assert (grades.length > 0 && credits.length > 0);// Check for wrong input values (requires clause), continue if valid
		LetterGrade result = grades[0];
		return result;
	}

	public int getTotalCredits() {
		return totalCredits;
	}
}
