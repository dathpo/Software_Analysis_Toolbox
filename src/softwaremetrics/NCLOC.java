package softwaremetrics;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class NCLOC extends VoidVisitorAdapter<Object> {
	public void visit(ClassOrInterfaceDeclaration node, Object arg){
		int javaDocLinesOfCode = 0;
		if (node.getJavaDoc() != null) {
			javaDocLinesOfCode = (node.getJavaDoc().getEnd().line - node.getJavaDoc().getBegin().line + 1);
		}
		int commentLinesOfCode = node.getAllContainedComments().size();
		int nonCommentLinesOfCode = (node.getEnd().line - node.getBegin().line - commentLinesOfCode - javaDocLinesOfCode);
		System.out.println("Non-Comment Lines Of Code (NCLOC) = " + nonCommentLinesOfCode);
	}
}
