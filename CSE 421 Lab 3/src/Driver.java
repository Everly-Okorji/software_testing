import java.util.HashMap;
import java.util.Map;

import ut.LetterGrade;


class Driver {

	public static Map<LetterGrade, Float> gradeLookup = fetchGrades();
	
	private static Map<LetterGrade, Float> fetchGrades() {
		Map<LetterGrade, Float> lookup = new HashMap<LetterGrade, Float>();
		gradeLookup.put(LetterGrade.APURE, 4.0f);
		gradeLookup.put(LetterGrade.AMINUS, 3.7f);
		gradeLookup.put(LetterGrade.BPLUS, 3.3f);
		gradeLookup.put(LetterGrade.BPURE, 3.0f);
		gradeLookup.put(LetterGrade.BMINUS, 2.7f);
		gradeLookup.put(LetterGrade.CPLUS, 2.3f);
		gradeLookup.put(LetterGrade.CPURE, 2.0f);
		gradeLookup.put(LetterGrade.CMINUS, 1.7f);
		gradeLookup.put(LetterGrade.DPLUS, 1.3f);
		gradeLookup.put(LetterGrade.DPURE, 1.0f);
		gradeLookup.put(LetterGrade.EPURE, 0.0f);
		return lookup;
	}
	
}
