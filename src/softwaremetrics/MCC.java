package softwaremetrics;

import java.util.StringTokenizer;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MCC extends VoidVisitorAdapter<Object>{

	private int mccResult;
	private int wmc = 0;

	public void visit(ClassOrInterfaceDeclaration node, Object arg) {
		CharSequence[] keywords = {"if","else","while","case","for","switch","do","continue","break","&&","||","?",":","catch","finally","throw","throws","default","return"};
		for (MethodDeclaration m : node.getMethods()) {
			int complexity = 1;
			mccResult = complexity;
			for (int i=0; i<keywords.length; i++) {
				StringTokenizer stTokenizer = new StringTokenizer(m.toStringWithoutComments());
				while (stTokenizer.hasMoreTokens()) {
					String words = stTokenizer.nextToken();
					if (keywords[i].toString().equals(words)) {
						complexity++;
						wmc++;
					}
				}
			}
			wmc++;
			printOutput(m, complexity);
			if (complexity > 11) {
			}  
		}
	}

	public int calculateCC(ClassOrInterfaceDeclaration node) {
		CharSequence[] keywords = {"if","else","while","case","for","switch","do","continue","break","&&","||","?",":","catch","finally","throw","throws","default","return"};
		for (MethodDeclaration m : node.getMethods()) {
			int complexity = 1;
			mccResult = complexity;
			for (int i=0; i<keywords.length; i++) {
				StringTokenizer stTokenizer = new StringTokenizer(m.toStringWithoutComments());
				while (stTokenizer.hasMoreTokens()) {
					String words = stTokenizer.nextToken();
					if (keywords[i].toString().equals(words)) {
						complexity++;
						wmc++;
					}
				}
			}
			wmc++;
			if (complexity > 11) {
			}  
		}
		return mccResult;
	}

	public int getMCCResult() {
		return mccResult;
	}

	public int getWMCResult() {
		return wmc;
	}

	public void printOutput(MethodDeclaration m, int complexity) {
		System.out.println("\nMethod Analysed: " + m.getName());
		int numberOfParameters = m.getParameters().size();
		System.out.println("Number of Parameters (NP) = " + numberOfParameters);
		if (numberOfParameters > 3) {
			System.out.println("There are too many parameters passed into the method, a solution could be to replace parameters with Method Call.");
		} else {
			System.out.println("There are no issues with the amount of parameters for the method.");
		}
		System.out.println("McCabe's Cyclomatic Complexity (MCC) = "+ complexity);
		if (complexity > 50) {
			System.out.print("McCabe's Cyclomatic Complexity is very high, the method is very complex and highly unstable");
		} else if (complexity >= 21 && complexity <=50) {
			System.out.print("McCabe's Cyclomatic Complexity is high, the method presents high risk.");
		} else if (complexity >= 11 && complexity <=20) {
			System.out.print("McCabe's Cyclomatic Complexity is slightly high, consider splitting up the method.");
		} else {
			System.out.print("McCabe's Cyclomatic Complexity is at an optimal value.");
		}
		System.out.println();
	}
}
