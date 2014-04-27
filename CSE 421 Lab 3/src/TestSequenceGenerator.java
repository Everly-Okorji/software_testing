

import gov.nasa.jpf.vm.Verify;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TestSequenceGenerator {

	int MAX_PROFS = 1;
	int MAX_STUDENTS = 1;
	
	int MAX_CREDITS = 1;
	
	public String genenerateSequence(ValueDomains doms) {
		StringBuilder sb = new StringBuilder();
		int numberProfesors = Verify.getInt(1, MAX_PROFS);
		sb.append(numberProfesors);
		sb.append("\n");
		for (int i = 0; i < numberProfesors; i++) {
			int credits = Verify.getInt(1, MAX_CREDITS);
			sb.append(credits);
			sb.append("\n");
		}
		int numberStudents = Verify.getInt(1, MAX_STUDENTS);
		sb.append(numberStudents);
		sb.append("\n");
		for (int i = 0; i < numberStudents; i++) {
			List<Object> namevalues = doms.getDomain("name");
			String name = (String) namevalues.get(Verify.getInt(0,
					namevalues.size() - 1));
			List<Object> rankvalues = doms.getDomain("rank");
			String rank = (String) rankvalues.get(Verify.getInt(0,
					rankvalues.size() - 1));
			sb.append(name);
			sb.append(" ");
			sb.append(rank);
			sb.append("\n");
		}
		for (int i = 0; i < numberProfesors; i++) {
			List<Object> gradevalues = doms.getDomain("grade");
			for (int k = 0; k < numberStudents; k++) {
				String grade = (String) gradevalues.get(Verify.getInt(0,
						gradevalues.size() - 1));
				sb.append(grade);
				sb.append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	void writeToFile(String content) {
		Verify.incrementCounter(0);
		synchronized (this) {
			try {
				PrintWriter out = new PrintWriter(new BufferedWriter(
						new FileWriter("totalCount")));
				out.println(Verify.getCounter(0));
				out.close();

			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
	
		System.out.print(content);
	}

	public static void main(String[] args) {

		Verify.resetCounter(0);
		
		TestSequenceGenerator tsg = new TestSequenceGenerator();
		String content = tsg.genenerateSequence(new ValueDomains());
		tsg.writeToFile(content);
		
	}

}
