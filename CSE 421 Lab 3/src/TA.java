import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import ut.LetterGrade;
import ut.Prof;
import ut.Student;

import gov.nasa.jpf.vm.Verify;

public class TA {

	public static void main(String[] args) {

		Map<String, LetterGrade> creditValue = new HashMap<String, LetterGrade>();
		creditValue.put("A", LetterGrade.APURE);
		creditValue.put("A-", LetterGrade.AMINUS);
		creditValue.put("B+", LetterGrade.BPLUS);
		creditValue.put("B", LetterGrade.BPURE);
		creditValue.put("B-", LetterGrade.BMINUS);
		creditValue.put("C+", LetterGrade.CPLUS);
		creditValue.put("C", LetterGrade.CPURE);
		creditValue.put("C-", LetterGrade.CMINUS);
		creditValue.put("D+", LetterGrade.DPLUS);
		creditValue.put("D", LetterGrade.DPURE);
		creditValue.put("E", LetterGrade.EPURE);

		Scanner in;
		int count = 0;
		
		try {
			in = new Scanner(new File("inputs/jpf1"));

			while (in.hasNextLine()) {
				int numProfs;
				int numStudents;

				short[] credits;

				// Get number of professor
				//System.out.print("Enter number of professors: ");
				String input = in.nextLine();

				numProfs = Integer.parseInt(input);
				credits = new short[numProfs];

				// Get credits for each professor
				for (int i = 0; i < numProfs; i++) {
					//System.out.print("Enter number of credits for Professor "
						//	+ (i + 1) + ": ");
					input = in.nextLine();
					short val = (short) Integer.parseInt(input);
					credits[i] = val;
				}

				// Get number of students
				//System.out.print("Enter number of students: ");
				input = in.nextLine();
				numStudents = Integer.parseInt(input);
				
				Student[] students = new Student[numStudents];

				// Get student information
				for (int i = 0; i < numStudents; i++) {
					//System.out
					//		.print("Enter full name and rank in the form \"firstname lastname [g or u]\": ");
					input = in.nextLine();
					String[] splitRes = input.trim().split(" ");
					Student.Rank rank = null;
					if (splitRes[2].equals("g")) {
						rank = Student.Rank.GRAD;
					} else if (splitRes[2].equals("u")) {
						rank = Student.Rank.UNDERGRAD;
					} else {
						System.err.println("Invalid Rank!");
						System.exit(0);
					}
					students[i] = new Student(splitRes[0], splitRes[1], rank);
				}

				// Create grade array
				LetterGrade[] grades = new LetterGrade[numStudents];

				for (int i = 0; i < numProfs; i++) {
					//System.out.print("Enter grades for Professor " + (i + 1)
					//		+ " in the form \"A- A B+ C...\": ");
					input = in.nextLine();
					String[] splitRes = input.trim().split(" ");
					for (int j = 0; j < numStudents; j++) {
						grades[j] = creditValue.get(splitRes[j]);
					}
					new Prof(students, credits[i]).evaluate(grades);
				}

				for (int i = 0; i < numStudents; i++) {
					System.out.println(students[i].getName() + " ("
							+ students[i].getEmailAddress() + ") had a GPA of "
							+ new DecimalFormat().format(students[i].getGPA()));
				}
				System.out.println("");
				count++;
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("Ran " + count + " test cases.");

	}

}
