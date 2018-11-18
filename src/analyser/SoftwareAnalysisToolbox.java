package analyser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import codesmells.*;
import softwaremetrics.*;
import umldiagram.*;
import umldiagram.Class;

public class SoftwareAnalysisToolbox {

	public static void main(String[] args) throws Exception {
		SoftwareAnalysisToolbox analyser = new SoftwareAnalysisToolbox();
		System.out.println("Welcome to The Software Analysis Toolbox");
		System.out.println("________________________________________\n");
		System.out.println("Please enter one of the following keys:\n");
		analyser.printMenu();
		String input;
		int i = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		File folder = new File("filestotest/");
		while ((input = br.readLine()) != null) {
			ArrayList<File> files = new ArrayList<File>(Arrays.asList(folder.listFiles()));
			for (File file : files) {
				CompilationUnit cu;
				try (FileInputStream fInS = new FileInputStream(file)) {
					cu = JavaParser.parse(fInS);
					switch (input.toLowerCase()) {
					case "1":
						analyser.runUMLDiagramCreationTool(cu, null);
						break;
					case "2":
						analyser.runSoftwareMetricsTool(cu, null);
						break;
					case "3":
						analyser.runCodeSmellDetectionTool(cu, null);
						break;
					case "4":
						analyser.runAllTools(cu, null);
						break;
					case "q":
						System.out.println("Quitting The Software Analysis Toolbox...");
						System.exit(0);
						break;
					default:
						if (i == 0) {
							System.out.println("The key entered is invalid.");
							i++;
						}
					}
				}
			}
			i = 0;
			System.out.println("\nPlease enter another key out of the following:\n");
			analyser.printMenu();
		}
	}

	private void runUMLDiagramCreationTool(CompilationUnit cu, Object arg) {
		System.out.println("\nRunning the UML Diagram Creation Tool...\n");
		new Class().visit(cu, arg);
		new Field().visit(cu, arg);
		new Method().visit(cu, arg);
		new Generalization().visit(cu, arg);
		new Realization().visit(cu, arg);
		System.out.println();
	}
	
	private void runSoftwareMetricsTool(CompilationUnit cu, Object arg) {
		System.out.println("\nRunning the Software Metrics Tool...\n");
		new SLOC().visit(cu, arg);	
		new CLOC().visit(cu, arg);	
		new NCLOC().visit(cu, arg);	
		new DC().visit(cu, arg);	
		new EXEC().visit(cu, arg);	
		new NOP().visit(cu, arg); 	
		new NOII().visit(cu, arg);	
		new DIT().visit(cu, arg);	
		new NOF().visit(cu, arg);	
		new NOM().visit(cu, arg);	
		new MCC().visit(cu, arg);
		new WMC().visit(cu, arg);	
		System.out.println();
	}

	private void runCodeSmellDetectionTool(CompilationUnit cu, Object arg) {
		System.out.println("\nRunning the Code Smell Detection Tool...\n");
		new LargeClass().visit(cu, arg);
		new LongMethodAndParameterList().visit(cu, arg);
		System.out.println();
	}

	private void runAllTools(CompilationUnit cu, Object arg) {
		System.out.println("\nRunning all Tools...\n");
		runUMLDiagramCreationTool(cu, arg);
		runSoftwareMetricsTool(cu, arg);
		runCodeSmellDetectionTool(cu, arg);
	}
	
	private void printMenu() {
		System.out.println("(1) UML Diagram Creation Tool");
		System.out.println("(2) Software Metrics Tool");
		System.out.println("(3) Code Smell Detection Tool");
		System.out.println("(4) Run all Tools");
		System.out.println("(Q)uit");
	}
}