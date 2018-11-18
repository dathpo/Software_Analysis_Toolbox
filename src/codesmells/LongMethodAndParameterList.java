package codesmells;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class LongMethodAndParameterList extends VoidVisitorAdapter<Object> {
	
	private static final int MAX = 15;
	
	public void visit(MethodDeclaration method, Object arg) {
		System.out.println("\nMethod Analysed: " + method.getName());
		System.out.println("Checking for a Long Method smell...");
		if((method.getEnd().line - method.getBegin().line) > MAX){
			System.out.println("Code Smell Detected!");
			System.out.println("The method is very long and complex! A suggestion is to split it up into smaller more readable ones." );
		} else{
			System.out.println("No smell detected, there are no issues with the method length.");
		}
		System.out.println("Checking for a Long Parameter List smell...");
		if (method.getParameters().size() > 3) {
			System.out.println("Code Smell Detected!");
			System.out.println("The method has too many parameters! A solution could be to replace parameters with Method Call." );
		} else {
			System.out.println("No smell detected, the amount of parameters for the method is not an issue.");
		}
	}
}
