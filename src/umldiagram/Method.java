package umldiagram;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


public class Method extends VoidVisitorAdapter<Object> {

	public void visit(MethodDeclaration method, Object arg){
		for (Parameter p : method.getParameters()) {
			if (method.isPrivate()) {
				System.out.println("-" + method.getName() + "(" + p.getName() + ":" + p.getType() + ")" + ":" + method.getType().toString());
			} else {
				System.out.println("+" + method.getName() + "(" + p.getName() + ":" + p.getType() + ")" + ":" + method.getType().toString());
			}
		}
	}
}
