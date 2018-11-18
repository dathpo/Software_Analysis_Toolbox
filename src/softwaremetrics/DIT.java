package softwaremetrics;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class DIT extends VoidVisitorAdapter<Object> {

	public void visit(ClassOrInterfaceDeclaration c, Object arg){
		int depth = 1;
		if(!(c.getExtends().isEmpty())) {
			depth = 2;
			System.out.println("Depth of Inheritance Tree (DIT) = " + depth);
			System.out.println("The Depth of Inheritance Tree is at an optimal value.");
		} else {
			System.out.println("Depth of Inheritance Tree (DIT) = " + depth);
			System.out.println("The Depth of Inheritance Tree is low, there is a poor exploitation of the advantages of OO design and inheritance.");
		}
		System.out.println();
	}
}
