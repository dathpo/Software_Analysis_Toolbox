package softwaremetrics;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class CLOC extends VoidVisitorAdapter<Object>{
	public void visit(ClassOrInterfaceDeclaration node, Object arg){
		int javaDocLinesOfCode = 0;
		if (node.getJavaDoc() != null) {
			javaDocLinesOfCode = (node.getJavaDoc().getEnd().line - node.getJavaDoc().getBegin().line + 1);
		}
		int commentLinesOfCode = node.getAllContainedComments().size();
		int totalCLOC = commentLinesOfCode + javaDocLinesOfCode;
		System.out.println("Comment Lines Of Code (CLOC) = " + javaDocLinesOfCode + " (JavaDoc) + " +
				commentLinesOfCode + " (Comments) = " + totalCLOC);
	}
}
