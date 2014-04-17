import java.text.DecimalFormat;
import java.util.Scanner;

public class TA {

	public static void main(String[] args) {
		
		int numProfs;
		int numStudents;
		
		Scanner in = new Scanner(System.in);
		System.out.println("Enter number of professors: ");
		String input = in.nextLine();
		
		numProfs = Integer.parseInt(input);
		
		System.out.println("Enter number of students: ");
		input = in.nextLine();
		numStudents = Integer.parseInt(input);
		
		in.close();
		
		Graded[] students = new Graded[numStudents];

		// Create 10 students
		for (int i = 0; i < numStudents; i++) {
			students[i] = new Undergrad();
		}

		for (int k = 0; k < numProfs; k++) {
			new Prof(students).evaluate();
		}
		
		for (int i = 0; i < numStudents; i++) {
			System.out.println(" Student " + (i + 1) + " had a GPA of "
					+ new DecimalFormat().format(students[i].getGPA()) + " from " + students[i].getTotalCredits() + " credit hours.");
		}
	}

}
