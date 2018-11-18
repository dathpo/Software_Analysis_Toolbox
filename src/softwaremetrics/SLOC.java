package softwaremetrics;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class SLOC extends VoidVisitorAdapter<Object> {
	public void visit(ClassOrInterfaceDeclaration node, Object arg) {
		int sourceLinesOfCode = (node.getEnd().line - node.getBegin().line);
		System.out.println("Class Analysed: " + node.getName() + "\n");
		System.out.println("Source Lines Of Code (SLOC) = " + sourceLinesOfCode);
		if (sourceLinesOfCode < 6) {
			System.out.println("There is not enough code in the class, it needs further implementation.");
		} else if (sourceLinesOfCode > 900) {
			System.out.println("There are too many lines of code for the class to be easily maintainable, it is recommended to split it up and use delegation.");
		} else {
			System.out.println("There are no issues with the amount of Source Lines of Code in the class.");
		}
		System.out.println();
	}
}
