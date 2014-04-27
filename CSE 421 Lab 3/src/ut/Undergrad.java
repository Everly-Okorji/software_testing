package ut;
public class Undergrad implements Graded {

	// Declare variables to store incoming grades and calculate scores
	private LetterGrade[] grades;
	private short[] credits;
	private short totalCredits;
	private float totalGradeValue;

	public Undergrad()// Instantiates variables as an empty array
	{
		grades = new LetterGrade[0];
		credits = new short[0];
	}

	public void assignGrade(short credit, LetterGrade grade) // Expand the
																// arrays and
																// include new
																// grades
	{
		// First create new arrays and copy existing data

		assert (1 <= credit && credit <= 5);
		LetterGrade[] gradeTemp = new LetterGrade[grades.length + 1];
		short[] creditTemp = new short[grades.length + 1];
		System.arraycopy(grades, 0, gradeTemp, 1, grades.length);
		System.arraycopy(credits, 0, creditTemp, 1, grades.length);
		// Assign the new grades to the empty spots to complete new array
		gradeTemp[0] = grade;
		creditTemp[0] = credit;
		grades = gradeTemp;
		credits = creditTemp;

		// Update the total amount of credit hours after each assignment
		totalCredits += credit;
		// Update total Grades depending on the grade.
		switch (grade) {
		case APURE:
			totalGradeValue += 4.0 * credit;
			break;
		case AMINUS:
			totalGradeValue += 3.7 * credit;
			break;
		case BPLUS:
			totalGradeValue += 3.3 * credit;
			break;
		case BPURE:
			totalGradeValue += 3.0 * credit;
			break;
		case BMINUS:
			totalGradeValue += 2.7 * credit;
			break;
		case CPLUS:
			totalGradeValue += 2.3 * credit;
			break;
		case CPURE:
			totalGradeValue += 2.0 * credit;
			break;
		case CMINUS:
			totalGradeValue += 1.7 * credit;
			break;
		case DPLUS:
			totalGradeValue += 1.3 * credit;
			break;
		case DPURE:
			totalGradeValue += 1.0 * credit;
			break;
		}

	}

	public float getGPA() {
		assert (totalCredits > 0); // Check for wrong input values (requires
									// clause), continue if valid
		return (totalGradeValue / totalCredits);
	}

	public LetterGrade getLastGrade() {
		assert (grades.length > 0 && credits.length > 0);// Check for wrong
															// input values
															// (requires
															// clause), continue
															// if valid
		LetterGrade result = grades[0];
		return result;
	}

	public int getTotalCredits() {
		int tC = (int) totalCredits;
		return tC;
	}
}
