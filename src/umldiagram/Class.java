package umldiagram;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class Class extends VoidVisitorAdapter<Object> {

	public void visit(ClassOrInterfaceDeclaration node, Object arg) {
		System.out.println(node.getName());
	}
}
