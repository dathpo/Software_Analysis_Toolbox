package softwaremetrics;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


public class NOF extends VoidVisitorAdapter<Object> {

	public void visit(ClassOrInterfaceDeclaration node, Object arg){
		int numberOfFields = node.getFields().size();
		System.out.println("Number Of Fields (NOF) = " + numberOfFields);
		if (numberOfFields > 10) {
			System.out.println("The Number Of Fields is too high, the class may be too big or have too many responsibilities.");
		} else {
			System.out.println("There are no issues with the amount of fields in the class.");
		}
		System.out.println();
	}
}
