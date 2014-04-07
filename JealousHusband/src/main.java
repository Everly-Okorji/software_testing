import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


public class main {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		System.out.println("BREADTH FIRST SEARCH\n");
		
		int cost = BreadthFirstSearch();
		if (cost >= 0) {
			System.out.println("\nBreadth First Search was successful! Path cost was " + cost + ".");
		} else {
			System.out.println("\nBreadth First Search failed.");
		}
		
		
		System.out.println("\n\nA* SEARCH\n");
		
		cost = AStarSearch();
		if (cost >= 0) {
			System.out.println("\nA* Search was successful! Path cost was " + cost + ".");
		} else {
			System.out.println("\nA* Search failed.");
		}
	
	}
	
	// BREADTH FIRST SEARCH HELPER METHODS:
	
	private static boolean isGoal (int men, int women, int boat) {
		return (men == 0 && women == 0 && boat == 0);
	}
	
	private static boolean inFrontier(Queue<State> frontier, int m, int w, int b) {
		
		Iterator<State> it = frontier.iterator();
		State temp;
		while(it.hasNext()) {
			temp = it.next();
			if (temp.men == m && temp.women == w && temp.boat == b) return true;
		}
		
		return false;
	}
	
	private static boolean isExplored(Set<State> explored, int m, int w, int b) {
		Iterator<State> it = explored.iterator();
		State temp;
		while(it.hasNext()) {
			temp = it.next();
			if (temp.men == m && temp.women == w && temp.boat == b) return true;
		}
		
		return false;
	}

	private static boolean isValidStep(int m, int w, int b) {
		boolean result = ((m == 0 || w <= m) && (m == 3 || m <= w));
		return result;
	}
	
	// Breadth first search
	public static int BreadthFirstSearch () {
		
		System.out.println("Begin with state (3,3,1):\n");
		
		// Set initial state
		State node = new State ();
		
		// Set initial path cost
		int path_cost = 0;
		
		// Check if the initial state is the goal
		if (isGoal(node.men, node.women, node.boat)) {
			System.out.println("Initial state is goal!");
			return 0;
		}
		
		// Create frontier and explored set
		Queue<State> frontier = new LinkedList<State>();
		Set<State> explored = new HashSet<State> ();
		
		// Add initial state to frontier
		frontier.add(node);
		
		// Temporary variables
		int m, w;
		State temp;
		
		// Loop through nodes in frontier until it is empty
		while (frontier.size() > 0) {
			
			// Remove the first node in queue and add to the explored set
			node = frontier.remove();
			explored.add(node);
			
			System.out.println("Observing state (" + node.men + "," + node.women + "," + node.boat + ") with path cost " + node.path_cost + ":");
			
			path_cost = node.path_cost + 1;
			
			// Check if boat is on the left, and act accordingly
			if (node.boat == 1) {
				
				// Define all possible "next" states
				for (int i = 0; i <= 2; i++) {
					for (int j = 0; j <= 2; j++) {
						if ((i + j) == 1 || (i + j) == 2) {		// Boat allows a maximum of two people
							m = node.men - i;
							w = node.women - j;
							
							// Check if this step is valid and number of men and women is not out of bounds
							// Also check if the new node has been explored
							if (m >= 0 && w >= 0 && isValidStep(m, w, 0) && !isExplored(explored, m, w, 0) && !inFrontier(frontier, m, w, 0)) {
								
								// If the node is the goal, end search
								if (isGoal(m, w, 0)) {
									
									System.out.println("Goal has been found: (" + m + "," + w + ",0)!");
									return path_cost;
									
								} else {	// node is not the goal

									System.out.println ("Added node (" + m + "," + w + ",0) to frontier.");
									
									// Add new state to frontier
									temp = new State (m, w, 0, path_cost);
									frontier.add(temp);
								}
							}
						}
					}
				}
				
			} else if (node.boat == 0) {		// Boat is on the right
				
				// Define all possible next states
				for (int i = 0; i <= 2; i++) {
					for (int j = 0; j <= 2; j++) {
						if ((i + j) == 1 || (i + j) == 2) {		// Boat allows a maximum of two people
							m = node.men + i;
							w = node.women + j;
							
							// Check if this step is valid and number of men and women is not out of bounds
							// Also check if the new node has been explored
							if (m <= 3 && w <= 3 && isValidStep(m, w, 1) && !isExplored(explored, m, w, 1) && !inFrontier(frontier, m, w, 1)) {
								
								System.out.println ("Added node (" + m + "," + w + ",1) to frontier.");
								
								// Add node to frontier
								temp = new State (m, w, 1, path_cost);
								frontier.add(temp);
								
							}
						}
					}
				}
			}
		
			System.out.println ("");
		}
		
		// If the loop ends and the goal was not found, then the search failed
		return -1;
	}

	
	// A* SEARCH HELPER METHODS
	private static int getStateWithLowestCost(Queue<State> frontier) {
		State temp;
		int index = -1, min_path_cost = Integer.MAX_VALUE;
		for (int i = 0; i < frontier.size(); i++) {
			temp = frontier.remove();
			if (temp.path_cost < min_path_cost) {
				index = i;
			}
			frontier.add(temp);
		}
		return index;
	}
	
	/*
	 *  For the A* search, the heuristic function used will relate to the number of people that make a trip.
	 *  h(n) =			0 if 2 additional people cross to the right
	 *  				1 if one person crosses to the right
	 *  				1 if two people cross to the left
	 *  				0 if one person crosses to the left (this is because this step is necessary for progress).
	 */
	public static int AStarSearch () {

		
		System.out.println("Begin with state (3,3,1):\n");
		
		// Set initial state
		State node = new State ();
		
		// Set initial path cost and declare heuristic cost variable
		int path_cost = 0;
		
		// Check if the initial state is the goal
		if (isGoal(node.men, node.women, node.boat)) {
			System.out.println("Initial state is goal!");
			return 0;
		}
		
		// Create frontier and explored set
		Queue<State> frontier = new LinkedList<State>();
		Set<State> explored = new HashSet<State> ();
		
		// Add initial state to frontier
		frontier.add(node);
		
		// Temporary variables
		int m, w;
		State temp;
		
		// Loop through nodes in frontier until it is empty
		while (frontier.size() > 0) {
			
			// Remove the node in queue with the lowest path cost + heuristic cost and add to the explored set
			
			// Get the index of the state with the lowest cost. Cost is stored as the addition of path cost and heuristic cost
			int index = getStateWithLowestCost(frontier);
			
			// Re-arrange the queue such that the state with lowest cost is in head of queue
			for (int j = 0; j < index; j++) {
				node = frontier.remove();
				frontier.add(node);
			}
			// Remove state from queue
			node = frontier.remove();
			// Add state to explored set
			explored.add(node);
			
			System.out.println("Observing state (" + node.men + "," + node.women + "," + node.boat + ") with path cost " + node.path_cost + ":");
			
			path_cost = node.path_cost + 1;
			
			int h_cost = 0;
			
			// Check if boat is on the left, and act accordingly
			if (node.boat == 1) {
				
				// Define all possible "next" states
				for (int i = 0; i <= 2; i++) {
					for (int j = 0; j <= 2; j++) {
						if ((i + j) == 1 || (i + j) == 2) {		// Boat allows a maximum of two people
							m = node.men - i;
							w = node.women - j;
							
							// Check if this step is valid and number of men and women is not out of bounds
							// Also check if the new node has been explored
							if (m >= 0 && w >= 0 && isValidStep(m, w, 0) && !isExplored(explored, m, w, 0) && !inFrontier(frontier, m, w, 0)) {
								
								// If the node is the goal, end search
								if (isGoal(m, w, 0)) {
									// Set heuristic cost to 1 if one person crosses (initial value is 0)
									if ((node.men + node.women) - (m + w) == 1) {
										h_cost = 1;
									}
									
									System.out.println("Goal has been found: (" + m + "," + w + ",0)!");
									return (path_cost + h_cost);
									
								} else {	// node is not the goal
									
									// Set heuristic cost to 1 if one person crosses (initial value is 0)
									if ((node.men + node.women) - (m + w) == 1) {
										h_cost = 1;
									}
									
									System.out.println ("Added node (" + m + "," + w + ",0) to frontier.");
									
									// Add new state to frontier
									temp = new State (m, w, 0, path_cost + h_cost);
									frontier.add(temp);
								}
							}
						}
					}
				}
				
			} else if (node.boat == 0) {		// Boat is on the right
				
				// Define all possible next states
				for (int i = 0; i <= 2; i++) {
					for (int j = 0; j <= 2; j++) {
						if ((i + j) == 1 || (i + j) == 2) {		// Boat allows a maximum of two people
							m = node.men + i;
							w = node.women + j;
							
							// Check if this step is valid and number of men and women is not out of bounds
							// Also check if the new node has been explored
							if (m <= 3 && w <= 3 && isValidStep(m, w, 1) && !isExplored(explored, m, w, 1) && !inFrontier(frontier, m, w, 1)) {
								
								// Set heuristic cost if two people cross to the left
								if ((m + w) - (node.men + node.women) == 2) {
									h_cost = 1;
								}
								
								System.out.println ("Added node (" + m + "," + w + ",1) to frontier.");
								
								// Add node to frontier
								temp = new State (m, w, 1, path_cost + h_cost);
								frontier.add(temp);
								
							}
						}
					}
				}
			}
		
			System.out.println ("");
		}
		
		// If the loop ends and the goal was not found, then the search failed
		return -1;
		
	}

}
