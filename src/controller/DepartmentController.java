package controller;

import dao.DepartmentDAO;
import dao.DoctorDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Department;
import model.Doctor;
import ui.AlertUtils;

import java.util.List;
import java.util.logging.Logger;

public class DepartmentController {
//    private static final Logger logger = LogManager.getLogger(DepartmentController.class);

    private final DepartmentDAO departmentDAO = new DepartmentDAO();
    private final DoctorDAO doctorDAO = new DoctorDAO();
    private static ObservableList<Department> departmentList;
    private FilteredList<Department> filteredDepartments;
    private ObservableList<Doctor> doctorList;
    private static TableView<Department> tableDepartments;
    private TableColumn<Department, String> colDepartmentCode;
    private TableColumn<Department, String> colName;
    private TableColumn<Department, String> colBuilding;
    private TableColumn<Department, Integer> colDirector;


    public static void loadDepartmentData() {
        try {
            List<Department> departments = DepartmentDAO.getAllDepartments();
            departmentList = FXCollections.observableArrayList(departments);
            tableDepartments.setItems(departmentList);
//            logger.info("Loaded {} departments from database", departments.size());
        } catch (Exception e) {
            System.out.println("Error loading department data");
            AlertUtils.showError("Failed to load department data"+ e.getMessage());
        }
    }


}
