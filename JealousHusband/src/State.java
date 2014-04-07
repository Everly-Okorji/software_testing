
class State {

	final int INITIAL_COUNT = 3;
	int men, women, boat;
	int path_cost;
	
	State () {
		men = INITIAL_COUNT;
		women = INITIAL_COUNT;
		boat = 1;
		path_cost = 0;
	}
	
	State (int m, int w, int b, int p) {
		men = m;
		women = w;
		boat = b;
		path_cost = p;
	}
	
}
