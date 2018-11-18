package softwaremetrics;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


public class NOM extends VoidVisitorAdapter<Object> {
	
	public void visit(ClassOrInterfaceDeclaration node, Object arg){
		int numberOfMethods = node.getMethods().size();
		System.out.println("Number Of Methods (NOM) = " + numberOfMethods);
		if (numberOfMethods > 15) {
			System.out.println("The amount of methods in the class is too high, it is recommended to split the class up.");
		} else {
			System.out.println("There are no issues with the amount of methods in the class.");
		}
	}
}
