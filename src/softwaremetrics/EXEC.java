package softwaremetrics;

import java.util.StringTokenizer;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class EXEC extends VoidVisitorAdapter<Object> {
	public void visit(ClassOrInterfaceDeclaration node, Object arg) {
		char sC = ';';
		float exStatements = 0;
		StringTokenizer stTokenizer = new StringTokenizer(node.toStringWithoutComments());
		while (stTokenizer.hasMoreTokens()) {
			char[] word = stTokenizer.nextToken().toCharArray();
			for (int i=0; i<word.length; i++) {
				if (word[i] == sC) {
					exStatements++;
				}
			}
		}
		int javaDocLinesOfCode = 0;
		if (node.getJavaDoc() != null) {
			javaDocLinesOfCode = (node.getJavaDoc().getEnd().line - node.getJavaDoc().getBegin().line + 1);
		}
		int nonStatements = node.getAllContainedComments().size();
		int totalStatements = (node.getEnd().line - node.getBegin().line - nonStatements - javaDocLinesOfCode);
		float executabilityRatio = exStatements/totalStatements;
		float executabilityPercentage = executabilityRatio * 100;
		int executabilityRounded = Math.round(executabilityPercentage);
		System.out.println("Executable Statements (EXEC) = " + (int) exStatements);
		System.out.println("Executability (XQT) = " + executabilityRounded + "%");
		if (executabilityRounded < 25) {
			System.out.println("The executability is too low, the class could be missing implementation, where only the declarative code is in place and the executable code is missing.");
		} else if (executabilityRounded > 75) {
			System.out.println("The executability is too high, it could be an indication of a bad design. The addition of temporary variables, the use of constants and shorter procedures could improve legibility.");
		} else {
			System.out.println("The ratio of executable statements to total statements in the class is optimal.");
		}
		System.out.println();
	}
}

