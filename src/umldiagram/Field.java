package umldiagram;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;



public class Field extends VoidVisitorAdapter<Object> {

	public void visit(FieldDeclaration field, Object a){ 
		for (VariableDeclarator var : field.getVariables()) {
			if (field.isPrivate()) {
				System.out.println("-" + var.getId().getName() + ":" + field.getType().toString());
			} else {
				System.out.println("+" + var.getId().getName() + ":" + field.getType().toString());
			}
		}
	} 
}