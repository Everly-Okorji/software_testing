Link to assignment:

http://www.cse.ohio-state.edu/~bucci/421/labs/lab3.shtml

EMMA:

1) Compiling and running JUnit tests

- Include all source files, the emma.jar file and the junit-4.11.jar file in the same directory.

- On the command line, navigate to that directory.

- The process for running tests is as follows:
% javac -cp junit-4.11.jar;. *.java
% java -cp junit-4.11.jar:. AllTests

2) Emma exercise

- Run Emma - from the command line you run as follows:
% java -XX:-UseSplitVerifier -cp emma.jar emmarun -sp . -r html -cp .;junit-4.11.jar RationalTest

