package steps;

import io.cucumber.java.en.When;
import utils.CommonMethods;
import utils.Constants;
import utils.ExcelReader;

import java.util.List;
import java.util.Map;

public class AddEmployeeFromExcel extends CommonMethods {
    @When("user adds multiple employee from excel using {string} add verify it")
    public void user_adds_multiple_employee_from_excel_using_add_verify_it(String sheetName) {
        List<Map<String, String>> empFromExcel = ExcelReader.excelListIntoMap(Constants.TESTDATA_FILEPATH, sheetName);
        for(Map<String,String> employName:empFromExcel){
            String fName=employName.get("firstName");
            String mName= employName.get("middleName");
            String lName= employName.get("lastName");

            System.out.println(fName);
            System.out.println(mName);
            System.out.println(lName);
        }


    }


}
