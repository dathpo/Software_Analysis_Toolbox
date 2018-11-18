package softwaremetrics;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class NOP extends VoidVisitorAdapter<Object> {
	
	public void visit(ClassOrInterfaceDeclaration c, Object arg){
		System.out.println("Number Of Parents (NOP) = " + c.getExtends().size());
	}
}
