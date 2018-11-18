package softwaremetrics;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class DC extends VoidVisitorAdapter<Object>{
	public void visit(ClassOrInterfaceDeclaration node, Object arg){
		int javaDocLinesOfCode = 0;
		if (node.getJavaDoc() != null) {
			javaDocLinesOfCode = (node.getJavaDoc().getEnd().line - node.getJavaDoc().getBegin().line + 1);
		}
		int commentLinesOfCode = node.getAllContainedComments().size();
		float totalCLOC = commentLinesOfCode + javaDocLinesOfCode;
		float sourceLinesOfCode = (node.getEnd().line - node.getBegin().line);
		float dcRatio = totalCLOC/sourceLinesOfCode;
		float dcPercentage = dcRatio * 100;
		int roundedResult = Math.round(dcPercentage);
		System.out.println("Density of Comments (DC) = " + roundedResult + "%");
		if (roundedResult < 20) {
			System.out.println("The Density of Comments is too low, more comments or JavaDoc should be added to the class.");
		} else if (roundedResult > 40){
			System.out.println("The Density of Comments is too high, there are either too many comments/JavaDoc or not enough code in the class!");
		} else {
			System.out.println("The Density of Comments is at an optimal range.");
		}
		System.out.println();
	}
}
