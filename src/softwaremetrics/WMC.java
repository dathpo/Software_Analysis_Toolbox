package softwaremetrics;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class WMC extends VoidVisitorAdapter<Object> {

	private MCC mcc;

	public void visit(ClassOrInterfaceDeclaration node, Object arg) {
		mcc = new MCC();
		mcc.calculateCC(node);
		int wmc = mcc.getWMCResult();
		System.out.println("\nWeighted Method Count (WMC) = "+ wmc);
		if (wmc < 50) {
			System.out.println("The Weighted Method Count for the class is optimal.");
		} else {
			System.out.println("The Weighted Method Count is too high, which means that the class is complex, harder to reuse and maintain and more fault-prone.");
		}
	}    	   
}