package umldiagram;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class Realization extends VoidVisitorAdapter<Object> {

	public void visit(ClassOrInterfaceDeclaration node, Object arg) {
		if (!(node.getImplements().isEmpty())) {
			for (ClassOrInterfaceType coi : node.getImplements()) {
				System.out.println("Realization: " + coi.getName());
			}
		}
	}
}
