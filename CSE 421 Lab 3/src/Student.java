class Student {

	enum Rank {
		GRAD, UNDERGRAD
	};

	private String first_name;
	private String last_name;
	private int dotNo;
	private static String[] lastnames = new String[0];
	private static int[] maxDotNo = new int[0];

	private Graded grade;

	// this constructor takes two String arguments, the first being the person's
	// first name and the second being the person's last name
	public Student(String first, String last, Rank rank) {
		first_name = first;
		last_name = last;
		int i;
		boolean foundAMatch = false;

		switch (rank) {
		case UNDERGRAD:
			grade = new Undergrad();
			break;
		case GRAD:
			grade = new Grad();
			break;
		default:
			break;
		}

		// Search the array of last names to see if we already have someone else
		// with the same last name or we're at the end of the array
		for (i = 0; i < lastnames.length && !foundAMatch; i++) {
			if (last == lastnames[i]) {
				foundAMatch = true;
			}
		}
		// If there's no match, copy the array into a larger array with the
		// current last name included. Also update the dot number array.
		if (i == lastnames.length && !foundAMatch) {
			String[] arrayTemp = new String[lastnames.length + 1];
			int[] intTemp = new int[lastnames.length + 1];
			System.arraycopy(lastnames, 0, arrayTemp, 1, lastnames.length);
			System.arraycopy(maxDotNo, 0, intTemp, 1, lastnames.length);
			arrayTemp[0] = last;
			intTemp[0] = 1;
			lastnames = arrayTemp;
			maxDotNo = intTemp;
			dotNo = maxDotNo[0]; // Assign a dot number for the current user.
		} else if (foundAMatch)// If there's a match, update the dot number of
								// the name and assign it to the user.
		{
			maxDotNo[i - 1]++;
			dotNo = maxDotNo[i - 1];
		}
	}

	// The getName() method returns a String that is the person's full name
	public String getName() {
		String name;
		name = first_name + " " + last_name;
		return name;
	}

	// The getEmailAddress() method returns a String that is the person's full
	// email address
	public String getEmailAddress() {
		String x = last_name.toLowerCase();
		String name = x + "." + dotNo + "@utexas.edu";
		return name;
	}
	
	public void assignGrade (short credit, LetterGrade lettergrade) {
		grade.assignGrade(credit, lettergrade);
	}
	
	public float getGPA () {
		return grade.getGPA();
	}
	
	public int getTotalCredits() {
		return grade.getTotalCredits();
	}
}
