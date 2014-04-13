class Prof {
	public void evaluate(Graded[] gradeArray) {
		/*
		 * // Declare final variables since we decide to assign the same grade
		 * to every object in the array final short CREDIT = 3; final
		 * LetterGrade GRADE = LetterGrade.CPURE; // Loop through the array,
		 * assigning the grades to each object for (int i = 0; i <
		 * gradeArray.length; i++){ gradeArray[i].assignGrade(CREDIT, GRADE); }
		 */

		// Randomly assign grades
		LetterGrade grade;
		short credits = (short) ((Math.random() * 5) + 1);

		for (int j = 0; j < gradeArray.length; j++) {
			
			int value = (int) (Math.random() * 10);
			
			switch (value) {
			case 0:
				grade = LetterGrade.APURE;
				break;
			case 1:
				grade = LetterGrade.AMINUS;
				break;
			case 2:
				grade = LetterGrade.BPLUS;
				break;
			case 3:
				grade = LetterGrade.BPURE;
				break;
			case 4:
				grade = LetterGrade.BMINUS;
				break;
			case 5:
				grade = LetterGrade.CPLUS;
				break;
			case 6:
				grade = LetterGrade.CPURE;
				break;
			case 7:
				grade = LetterGrade.CMINUS;
				break;
			case 8:
				grade = LetterGrade.DPURE;
				break;
			case 9:
				grade = LetterGrade.EPURE;
				break;
			default:
				grade = LetterGrade.EPURE;
			}

			gradeArray[j].assignGrade(credits, grade);
		}
	}
}
