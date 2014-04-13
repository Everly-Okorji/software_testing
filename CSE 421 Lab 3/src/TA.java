import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class TA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String input = JOptionPane.showInputDialog("Enter number of professors: ");
		int profNo = Integer.parseInt(input);
		
		Graded[] students = new Graded[10];

		// Create 10 students
		for (int i = 0; i < 10; i++) {
			students[i] = new Undergrad();
		}

		for (int k = 0; k < profNo; k++) new Prof().evaluate(students);
		
		for (int i = 0; i < 10; i++) {
			System.out.println(" Student " + (i + 1) + " had a GPA of "
					+ new DecimalFormat().format(students[i].getGPA()) + " from " + students[i].getTotalCredits() + " credit hours.");
		}
	}

}
