/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CPP_Standard_Reviewer;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Faintinger
 */
//&p-Report
public class Report {
    Files fArr;
    Variables vVars;
    Comments comComments;
    Libraries libLibraries;
    Format forFormat;
    ArrayList<File> fList;
    ArrayList<String> sLComments = new ArrayList<String>();
    int iVars [] = new int [2]; //grades of variables
    int iCommentsFunctions[] = new int[2]; // grades of comments
    int iFileName[] = new int[2]; // grades of file name
    int iInitialComments[] = new int [2]; // grade of the initial comments
    int iFunctionHeaders[] = new int [2]; // grade of the function declaration
    int iLibraries [] = new int [2]; // grade of the libraries
    int iIndentation [] = new int [2]; // grade the indentation of the file
    int iOneLineComments[] = new int [2]; // grade of the one line comments
    int iInstructions [] = new int [2]; // grade the instructions per line
    int iBlankSpaces [] = new int [2]; // grade the blank spaces between operations
    double [][] iReportCriterias;
    int [] iGradCrit;
    String sComments[];
    //&i
    public Report(ArrayList<File> fAux, int [] iGC) {
        fList = fAux;
        iGradCrit = iGC;
        iReportCriterias = new double [fAux.size()][13];
        sComments = new String[fAux.size() + 1];
    }
    //&i
    private ArrayList<String> listFiles() {
        ArrayList<String> files = new ArrayList<String>();
        for(int i = 0; i < fList.size(); i++)
            files.add(fList.get(i).getName());
        return files;
    }
    //&i
    private void cleanVariables() {
        iVars = new int [2]; //grades of variables
        iCommentsFunctions = new int[2]; // grades of comments
        iFileName = new int[2]; // grades of file name
        iInitialComments = new int [2]; // grade of the initial comments
        iFunctionHeaders = new int [2]; // grade of the functions
        iLibraries = new int [2]; // grade of the libraries
        iIndentation = new int [2]; // grade the indentation of the file
        iOneLineComments = new int [2]; // grade of the one line comments
        iInstructions = new int [2]; // grade the instructions per line
        iBlankSpaces = new int [2]; // grade the blank spaces between operations
    }
    private double promedioGeneral(int iPos) {
        double dSumaB = 0, dSumaM = 0;
        dSumaB = iVars[0] + iCommentsFunctions[0] + iFileName[0] + iInitialComments[0] 
                + iFunctionHeaders[0] + iLibraries[0] + iIndentation[0] 
                + iOneLineComments[0] + iInstructions[0] + iBlankSpaces[0];
        dSumaM = iVars[1] + iCommentsFunctions[1] + iFileName[1] + iInitialComments[1] 
                + iFunctionHeaders[1] + iLibraries[1] + iIndentation[1] 
                + iOneLineComments[1] + iInstructions[1] + iBlankSpaces[1];
        return (dSumaB + dSumaM == 0) ? 0 : 100 * (dSumaB /(dSumaB + dSumaM));
    }
    //&i
    private double calculateContentGrade(int iPos) {
        double dSum =0;
        if(iGradCrit[3] != 100) {
            for(int i = 0; i < 12; i++) {
                iReportCriterias[iPos][i] = Double.isNaN(iReportCriterias[iPos][i])? iGradCrit[i] : iReportCriterias[iPos][i];
                dSum += iReportCriterias[iPos][i];
            }
            return (iGradCrit[3] * (dSum / (100 - iGradCrit[3])));
        }
        else {
            for(int i = 0; i < 12; i++) 
                iReportCriterias[iPos][i] = Double.isNaN(iReportCriterias[iPos][i])? iGradCrit[i] : iReportCriterias[iPos][i];
            return promedioGeneral(iPos);
        }
    }
    //&i
    public Boolean generateReport(String sPath, String sName) {
        gradeFiles();
        for(int i = 0; i < fList.size(); i++) {
            for (int j = 0; j < 12; j++) {
                System.out.print(iReportCriterias[i][j] + " ");
            }
             System.out.println();
        }
        Excel excReport = new Excel(iReportCriterias, listFiles(), sPath, sName, sComments);
        return true;
    }
    //&i
    public Boolean gradeFiles() {
        //try {
            for(int i = 0; i < fList.size(); i++) {
                cleanVariables();
                Files fAux = new Files();
                fAux.openFile(fList.get(i).getPath());
                fAux.removeBlankLines();
                if(fAux.length() > 0) {
                    try {
                        calculateFile(i);
                    } catch (Exception ex) { }
                iReportCriterias[i][0] = iGradCrit[0] * ((iFileName[0] * 1.0) 
                        / (iFileName[0] + iFileName[1]));
                iReportCriterias[i][1] = iGradCrit[1] * ((iVars[0] * 1.0)/(iVars[0] + iVars[1]));
                iReportCriterias[i][2] = 0;
                iReportCriterias[i][4] = iGradCrit[4] * (iInitialComments[0] * 1.0)
                        / (iInitialComments[0] + iInitialComments[1]);
                iReportCriterias[i][5] = iGradCrit[5] * ((iLibraries[0] * 1.0) 
                        / (iLibraries[0] + iLibraries[1]));
                iReportCriterias[i][6] = iGradCrit[6] * ((iCommentsFunctions[0] * 1.0)
                        / (iCommentsFunctions[0] + iCommentsFunctions[1]));
                iReportCriterias[i][7] = iGradCrit[7] * ((iFunctionHeaders[0] * 1.0)
                        / (iFunctionHeaders[0] + iFunctionHeaders[1]));
                iReportCriterias[i][8] = iGradCrit[8] * ((iIndentation[0] * 1.0)
                        / (iIndentation[0] + iIndentation[1]));
                iReportCriterias[i][9] = iGradCrit[9] * ((iOneLineComments[0] * 1.0)
                        / (iOneLineComments[0] + iOneLineComments[1]));
                iReportCriterias[i][10] = iGradCrit[10] * ((iInstructions[0] * 1.0)
                        / (iInstructions[0] + iInstructions[1]));
                iReportCriterias[i][11] = iGradCrit[11] * ((iBlankSpaces[0] * 1.0)
                        / (iBlankSpaces[0] + iBlankSpaces[1]));
                iReportCriterias[i][3] = calculateContentGrade(i);
                }
            }
        //}
        //catch(Exception ex) {
           // return false;
       // }
        return true;  
    }
    //&i
    private Boolean calculateFile(int iPos) {
        fArr = new Files();
        vVars = new Variables();
        comComments = new Comments();
        libLibraries = new Libraries();
        forFormat = new Format();
        int iCont = 0, iName = 0;
        boolean isAFunction = false;
        if(fArr.openFile(fList.get(iPos).getPath())) {
            System.out.println("Abre Archivo");
            fArr.removeBlankLines();
            System.out.println("Cantidad de lineas: " + fArr.length());
            // Initial Comment
            if(comComments.isAFunctionComment(fArr.getLine(iCont))) {
                    String sLine = fArr.getLine(++iCont).trim();
                    comComments.saveFileName(sLine);
                    int iAux[] = comComments.checkFileName(fList.get(iPos).getName());
                    iFileName[0] = iAux[0];
                    iFileName[1] = iAux[1];
                    while(!comComments.endOfComment(sLine)) {
                        iCont++;
                        sLine = fArr.getLine(iCont).trim();
                        iInitialComments[0]++;
                    }
            }
            // Read the rest of the file
            for(int i = iCont + 1; i < fArr.length(); i++) {
                // System.out.println(fArr.getLine(i));
                //if is an include statement
                if(libLibraries.isALibraryStatement(fArr.getLine(i))) {
                    libLibraries.checkLibrary(fArr.getLine(i));
                    i++;
                }
                //if is a using namespace
                if(libLibraries.isUsing(fArr.getLine(i))) {
                   libLibraries.checkSTD(fArr.getLine(i));
                   i++;
                }
                // if it's a comment
                if(comComments.isAFunctionComment(fArr.getLine(i))) {
                    iName = 0;
                    String sLine = fArr.getLine(i);
                    while(!comComments.endOfComment(sLine)) {
                        i++; sLine = fArr.getLine(i); 
                        //System.out.println(fArr.getLine(i));
                        if(!comComments.startParameters(sLine) && (iName == 0)) {
                            //System.out.println("Funcion: " + sLine);
                            comComments.setFunctionName(sLine);
                            iName++;
                        }
                        else {
                            if(comComments.startParameters(sLine)) {
                                comComments.cleanParameters();
                                i++; sLine = fArr.getLine(i).trim();
                                while(!comComments.startReturn(sLine) && !comComments.endOfComment(sLine)){
                                    //System.out.println("Parametros agregar: " + sLine);
                                    if(!sLine.split(" ")[0].toLowerCase().equals("none"))
                                        comComments.saveParameters(sLine);
                                    i++; sLine = fArr.getLine(i).trim();
                                }
                            }
                        }
                    }
                    sLine = fArr.getLine(++i);
                    if(comComments.isAComment(sLine.trim())) {
                        while(!comComments.endOfComment(sLine.trim()))
                            sLine = fArr.getLine(++i);
                        sLine = fArr.getLine(++i);
                    }
                    int iIndex = (comComments.checkFunctionName(sLine))? 0 : 1;
                    //System.out.println("Index: " + iIndex);
                    int iArrCom[] = comComments.checkDeclarationsOfFunctions(sLine);
                    iCommentsFunctions[iIndex] += 1; 
                    System.out.println("Funcion Name: " + comComments.getFunctionName() 
                            + " Comentarios: " + iCommentsFunctions[0] + " Incorrectas: " + iCommentsFunctions[1]);
                    iCommentsFunctions[0] += iArrCom[0];
                    iCommentsFunctions[1] += iArrCom[1];
                    System.out.println("Funcion: " + comComments.getFunctionName() 
                            + " Comentarios: " + iArrCom[0] + " Incorrectas: " + iArrCom[1]);
                }
                
                String sLine = fArr.getLine(i);
                // Check if the line is or has a one line comment and grade its length and content
                comComments.checkLineComments(sLine);
                // if it's a function
                if(vVars.isAFunction(sLine)) {
                    int iFun[] = vVars.checkFunction(sLine);
                    iFunctionHeaders[0] += iFun[0];
                    iFunctionHeaders[1] += iFun[1];
                    System.out.println("Declaracion de funcion Correctas: " + iFun[0] + " Incorrectas: " + iFun[1]);
                    // viene funcion
                    int iArr[] = vVars.checkDeclarationsOfFunctions(sLine);
                    iVars[0] += iArr[0];
                    iVars[1] += iArr[1];
                    System.out.println("Variables en funciones Correctas: " + iArr[0] + " Incorrectas: " + iArr[1]);
                    
                    // Check the ending of the statement and it is just one per line
                    forFormat.setbFunction(true);
                    forFormat.checkInstructionsPerLine(sLine);
                    // Check the indentation of all the lines of code excluding comments
                    forFormat.checkIndentation(sLine);
                    forFormat.setbFunction(false);
                    isAFunction = true;
                    i++;
                }
                // Check the ending of the statement and it is just one per line
                // Check the indentation of all the lines of code excluding comments
                if(!isAFunction) {
                    forFormat.checkInstructionsPerLine(sLine);
                    forFormat.checkIndentation(sLine);
                }
                isAFunction = false;
                // Check if exist and operation and review the space between operations format
                forFormat.checkSpaces(sLine);
                //if is a variables declaration
                if(vVars.isADeclaration(fArr.getLine(i))) {
                    int iArr[] = vVars.checkDeclarations(fArr.getLine(i));
                    iVars[0] += iArr[0];
                    iVars[1] += iArr[1];
                    System.out.println("Variables Correctas: " + iArr[0] + " Incorrectas: " + iArr[1]);
                }
            }
            iLibraries = libLibraries.getGrades();
            iInstructions = forFormat.getEvalInstructions();
            iBlankSpaces = forFormat.getEvalSpaces();
            iOneLineComments = comComments.getLineCommentsGrade();
            iIndentation = forFormat.getEvalIdentation();
            sComments[iPos] = comComments.getComments() + libLibraries.getComments() + 
                    forFormat.getComments() + vVars.getComments();
            //System.out.println(libLibraries.getComments());
            return true;
        } 
        else {
            return false;
        }
    }
    
}
