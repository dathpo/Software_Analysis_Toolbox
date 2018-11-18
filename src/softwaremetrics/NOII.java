package softwaremetrics;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class NOII extends VoidVisitorAdapter<Object> {
	
	public void visit(ClassOrInterfaceDeclaration c, Object arg){
		System.out.println("Number Of Implemented Interfaces (NOII) = " + c.getImplements().size());
		System.out.println();
	}
}
