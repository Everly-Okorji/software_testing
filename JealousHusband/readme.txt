1.
a)

Initial state:
	s = (3,3,1)				// (men_on_left, women_on_left, boat_on_left i.e. 1 = true, 0 = false)

Possible actions:
- Cross one person (man or woman) to the other side.
- Cross 2 people (a couple, two men or two women) to the other side.
	
Transition model:
	State is updated to reflect those who crossed to the other side. It is assumed that each man never leaves his
	wife with another man. Thus, a step is valid if for each side of the river, there is no man, or the number of
	men is less than those of women. In other words, a step is valid if:
			(men_on_left == 0 || men_on_left <= women_on_left) && (men_on_left == 3 || women_on_left <= men_on_left)

Goal test:
	All people in the environment as well as the boat are on the right side, i.e. g = (0,0,0)

Path cost:
	Each step costs 1.
	
b)
	(3,3,1)	--> {(3,2,0), (2,2,0), (3,1,0)}
	(3,2,0) --> {(3,3,1)}
	(2,2,0) --> {(3,2,1), (3,3,1)}
	(3,1,0) --> {(3,2,1), (3,3,1)}
	(3,2,1) --> {(2,2,0), (3,1,0), (3,0,0)}
	(3,0,0) --> {(3,1,1), (3,2,1)}
	(3,1,1) --> {(3,0,0), (1,1,0)}
	(1,1,0) --> {(3,1,1), (2,2,1)}
	(2,2,1) --> {(0,2,0), (1,1,0)}
	(0,2,0) --> {(0,3,1), (2,2,1)}
	(0,3,1) --> {(0,2,0), (0,1,0)}
	(0,1,0) --> {(1,1,1), (0,2,1), (0,3,1)}
	(1,1,1) --> {(0,1,0), (0,0,0)}
	(0,2,1) --> {(0,1,0), (0,0,0)}
	(0,0,0) --> GOAL STATE!
	
	All other states are invalid based on the condition of the problem or they cannot occur by following any valid
	sequence of steps.