package CruiseProj;

import CruiseProj.Business.ControlFlow;
import CruiseProj.Business.TestData;
import CruiseProj.Data.Cruise;

public class Main {
    public static void main(String[] args) {
        Cruise[] cruises = TestData.buildTestData(); // builds test data
        System.out.println("Test data built successfully!");


        
        ControlFlow.menu(cruises); // starts the program
    }
}