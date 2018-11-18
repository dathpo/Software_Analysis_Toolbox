package codesmells;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


public class LargeClass extends VoidVisitorAdapter<Object> {
	
	private static final int MAX = 100;
	
	public void visit(ClassOrInterfaceDeclaration node, Object arg){
		System.out.println("Class Analysed: " + node.getName() + "\n");
		System.out.println("Checking for a Large Class smell...");
		int javaDocLinesOfCode = 0;
		if (node.getJavaDoc() != null) {
			javaDocLinesOfCode = (node.getJavaDoc().getEnd().line - node.getJavaDoc().getBegin().line + 1);
		}
		int commentLinesOfCode = node.getAllContainedComments().size();
		int nonCommentLinesOfCode = (node.getEnd().line - node.getBegin().line - commentLinesOfCode - javaDocLinesOfCode);
		if(nonCommentLinesOfCode > MAX){
			System.out.println("Code Smell Detected!");
			System.out.println("The class is too large, it is recommended to split it up and use delegation.");
		} else {
			System.out.println("No smell detected, there are no issues with the amount of lines of code.");
		}
		if(node.getMethods().size() > 15){
			System.out.println("Code Smell Detected!");
			System.out.println("There are too many methods in this class! A solution could be to split it up into smaller classes.");
		} else {
			System.out.println("No smell detected, there are not too many methods in the class.");
		}
	}
}
