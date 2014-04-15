/*
 * Name: <your name>
 * EID: <your EID>
 */

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Your solution goes in this class.
 * 
 * Please do not modify the other files we have provided for you, as we will use
 * our own versions of those files when grading your project. You are
 * responsible for ensuring that your solution works with the original version
 * of all the other files we have provided for you.
 * 
 * That said, please feel free to add additional files and classes to your
 * solution, as you see fit. We will use ALL of your additional files when
 * grading your solution.
 */
public class Program1 extends AbstractProgram1 {
    /**
     * Determines whether a candidate Matching represents a solution to the
     * Stable Marriage problem. Study the description of a Matching in the
     * project documentation to help you with this.
     */
    public boolean isStableMatching(Matching marriage) {

    	int hospitalIndex = 0, filledSlots = 0, isMatch = 0, assignedHospital = 0;
    	int filledSlots2 = 0;
		int countingIndex = 0;
		int thisHospitalIndex = 0;
    	
    	// Checks to see whether all hospital slots filled or not.
    	//Returns false if any hospital slot(s) vacant
    	for(int match: marriage.getResidentMatching()){
    		if(match != -1){
    			isMatch++;
    		} 		
    	}
    	if(isMatch < marriage.totalHospitalSlots()){
			System.out.println("Part A");
    		return false;
		}
    	
    	
    	//First condition of stability if a preferred resident is not assigned a hospital
    	
    	
    	for(ArrayList<Integer> hospitalPref: marriage.getHospitalPreference()){

    		filledSlots = 0;
    		filledSlots2 = 0;
    		for(int preferredRes: hospitalPref){
    			
    			if(marriage.getResidentMatching().get(preferredRes) == -1 && filledSlots < marriage.getHospitalSlots().get(hospitalIndex)){  				
    				System.out.println("Part B");

    				return false;  				    				
    			}
    			
    			if(marriage.getResidentMatching().get(preferredRes)==hospitalIndex){
    				filledSlots++;}
    			
    			

			if(marriage.getResidentMatching().get(preferredRes) == hospitalIndex && filledSlots2 <= marriage.getHospitalSlots().get(hospitalIndex)){
    			filledSlots2++;

				if(filledSlots2 == marriage.getHospitalSlots().get(hospitalIndex)){break;}
				continue;
			}
    	

			
			for(int ResPref: marriage.getResidentPreference().get(preferredRes)){
				
			
				int assigned = marriage.getResidentMatching().get(preferredRes);
				if(marriage.getResidentPreference().get(preferredRes).indexOf(hospitalIndex) < marriage.getResidentPreference().get(preferredRes).indexOf(assigned)){
					
					return false;
				}
			}
    	
    		}
    			hospitalIndex++;
    			
    	}
    		
    	
    
    // **********************************        End of first condition for stability	****************************************************************
    	
    	
    	
    		
    //Second condition of stability if both hospital and resident prefer each other (over assignment) yet not paired together
    	
   
    	
    	
    	 // **********************************        End of second condition for stability	****************************************************************
    	
        return true; //Returns true if both conditions are passed
    }

    /**
     * Determines a solution to the Stable Marriage problem from the given input
     * set. Study the project description to understand the variables which
     * represent the input to your solution.
     * 
     * @return A stable Matching.
     */
    public Matching stableMarriageGaleShapley(Matching marriage) {
    	
    	System.out.println(System.nanoTime());
    	ArrayList<Integer> stableMatch = new ArrayList<Integer>();
    	for(int i = 0; i < marriage.getResidentCount(); i++)
    		stableMatch.add(-1);
    	
    	marriage.setResidentMatching(stableMatch);
    	
    	int totalslotsfilled = 0;
    	int hospitalIndex = 0;
		int assignedHospital;
		int localhospitalslots;
		int [] hospitalslots = new int [marriage.getHospitalCount()]; 
		int [] hospitalposition = new int [marriage.getHospitalCount()];
		int preferredResIndex;
		int preferredResident;
    	while(totalslotsfilled < marriage.totalHospitalSlots()){    		
    		
    		//System.out.println("Hello");

        		if(hospitalIndex == marriage.getHospitalCount()){
        			hospitalIndex = 0;
        		}
        		else if (marriage.getHospitalSlots().get(hospitalIndex) == hospitalslots[hospitalIndex]){
        			hospitalIndex++;
        			continue;
        		}
        		
        		ArrayList<Integer> hospitalPref = marriage.getHospitalPreference().get(hospitalIndex);
        		
        		localhospitalslots = hospitalslots[hospitalIndex];
        		
        		/*if(localhospitalslots == marriage.getHospitalSlots().get(hospitalIndex)){
        			hospitalIndex++;
        			continue;}*/
        		
        		preferredResIndex = 0;
        		for(int j = hospitalposition[hospitalIndex]; j < hospitalPref.size(); j++){
        			
        			 preferredResident = hospitalPref.get(j);
        			if(localhospitalslots == marriage.getHospitalSlots().get(hospitalIndex)){
            			hospitalposition[hospitalIndex] = preferredResIndex;
        				break;}
        			 if(marriage.getResidentMatching().get(preferredResident) == -1){
        				stableMatch.set(preferredResident, hospitalIndex);
        				totalslotsfilled++;
        				localhospitalslots++;
        				//System.out.println("Added resident " + preferredResident + " to hospital " + hospitalIndex );
        			}
        			else if (marriage.getResidentMatching().get(preferredResident) != hospitalIndex){
        				
        				assignedHospital = marriage.getResidentMatching().get(preferredResident);
        				
        				if(marriage.getResidentPreference().get(preferredResident).indexOf(hospitalIndex) < marriage.getResidentPreference().get(preferredResident).indexOf(assignedHospital)){
        					
        					hospitalslots[assignedHospital] = hospitalslots[assignedHospital] - 1;
        					stableMatch.set(preferredResident, hospitalIndex);
        					localhospitalslots++;
        					//System.out.println("Hospital " + hospitalIndex + "Stole resident " + preferredResident + "from : " + assignedHospital);
        					
        					
        				}
        			}
        			 preferredResIndex++;
        			
        		}
        		hospitalslots[hospitalIndex] = localhospitalslots;
        			
        			
        		hospitalIndex++;	
        			
    	}
    	
    	
    	marriage.setResidentMatching(stableMatch);
    	System.out.println(System.nanoTime());
        return marriage;  
    }
}
