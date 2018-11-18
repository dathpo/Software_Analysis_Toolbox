package umldiagram;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class Generalization extends VoidVisitorAdapter<Object> {

	public void visit(ClassOrInterfaceDeclaration node, Object arg) {
		if (!(node.getExtends().isEmpty())) {
			for (ClassOrInterfaceType coi : node.getExtends()) {
				System.out.println("Generalization: " + coi.getName());
			}
		}
	}
}
