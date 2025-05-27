package com.example.kreta_clone;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClassroomManagementApp extends Application {

    // Data models
    public static class Student {
        private final SimpleStringProperty name;
        private final SimpleIntegerProperty mathGrade;
        private final SimpleIntegerProperty physicsGrade;
        private final SimpleIntegerProperty historyGrade;

        public Student(String name, int mathGrade, int physicsGrade, int historyGrade) {
            this.name = new SimpleStringProperty(name);
            this.mathGrade = new SimpleIntegerProperty(mathGrade);
            this.physicsGrade = new SimpleIntegerProperty(physicsGrade);
            this.historyGrade = new SimpleIntegerProperty(historyGrade);
        }

        public String getName() { return name.get(); }
        public void setName(String name) { this.name.set(name); }
        public SimpleStringProperty nameProperty() { return name; }

        public int getMathGrade() { return mathGrade.get(); }
        public void setMathGrade(int mathGrade) { this.mathGrade.set(mathGrade); }
        public SimpleIntegerProperty mathGradeProperty() { return mathGrade; }

        public int getPhysicsGrade() { return physicsGrade.get(); }
        public void setPhysicsGrade(int physicsGrade) { this.physicsGrade.set(physicsGrade); }
        public SimpleIntegerProperty physicsGradeProperty() { return physicsGrade; }

        public int getHistoryGrade() { return historyGrade.get(); }
        public void setHistoryGrade(int historyGrade) { this.historyGrade.set(historyGrade); }
        public SimpleIntegerProperty historyGradeProperty() { return historyGrade; }

        public double getAverage() {
            return (mathGrade.get() + physicsGrade.get() + historyGrade.get()) / 3.0;
        }
    }

    public static class Classroom {
        private final SimpleStringProperty name;
        private final ObservableList<Student> students;

        public Classroom(String name) {
            this.name = new SimpleStringProperty(name);
            this.students = FXCollections.observableArrayList();
        }

        public String getName() { return name.get(); }
        public void setName(String name) { this.name.set(name); }
        public SimpleStringProperty nameProperty() { return name; }

        public ObservableList<Student> getStudents() { return students; }

        public int getStudentCount() { return students.size(); }

        public double getClassAverage() {
            if (students.isEmpty()) return 0.0;
            return students.stream()
                    .mapToDouble(Student::getAverage)
                    .average()
                    .orElse(0.0);
        }
    }

    // Data for table views
    public static class ClassroomSummary {
        private final SimpleStringProperty classroomName;
        private final SimpleIntegerProperty studentCount;
        private final SimpleDoubleProperty classAverage;

        public ClassroomSummary(String classroomName, int studentCount, double classAverage) {
            this.classroomName = new SimpleStringProperty(classroomName);
            this.studentCount = new SimpleIntegerProperty(studentCount);
            this.classAverage = new SimpleDoubleProperty(classAverage);
        }

        public String getClassroomName() { return classroomName.get(); }
        public SimpleStringProperty classroomNameProperty() { return classroomName; }

        public int getStudentCount() { return studentCount.get(); }
        public SimpleIntegerProperty studentCountProperty() { return studentCount; }

        public double getClassAverage() { return classAverage.get(); }
        public SimpleDoubleProperty classAverageProperty() { return classAverage; }
    }

    private final ObservableList<Classroom> classrooms = FXCollections.observableArrayList();
    private final ObservableList<ClassroomSummary> classroomSummaries = FXCollections.observableArrayList();
    private Stage primaryStage;
    private Classroom currentClassroom;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Classroom Management System");
        showClassroomView();
        primaryStage.show();
    }

    private void showClassroomView() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        // Title
        Label title = new Label("Classroom Management");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Classroom list
        ListView<Classroom> classroomList = new ListView<>(classrooms);
        classroomList.setCellFactory(listView -> new ListCell<Classroom>() {
            @Override
            protected void updateItem(Classroom classroom, boolean empty) {
                super.updateItem(classroom, empty);
                if (empty || classroom == null) {
                    setGraphic(null);
                } else {
                    HBox hbox = new HBox(10);
                    Label nameLabel = new Label(classroom.getName());
                    Button enterButton = new Button("Enter");
                    enterButton.setOnAction(e -> {
                        currentClassroom = classroom;
                        showStudentView();
                    });
                    hbox.getChildren().addAll(nameLabel, enterButton);
                    setGraphic(hbox);
                }
            }
        });
        classroomList.setPrefHeight(150);

        // Buttons
        HBox buttonBox = new HBox(10);
        Button createButton = new Button("Create Classroom");
        Button deleteButton = new Button("Delete Classroom");

        createButton.setOnAction(e -> createClassroom());
        deleteButton.setOnAction(e -> deleteClassroom(classroomList));

        buttonBox.getChildren().addAll(createButton, deleteButton);

        // Summary table
        TableView<ClassroomSummary> summaryTable = new TableView<>(classroomSummaries);

        TableColumn<ClassroomSummary, String> nameCol = new TableColumn<>("Classroom Name");
        nameCol.setCellValueFactory(cellData -> cellData.getValue().classroomNameProperty());
        nameCol.setPrefWidth(150);

        TableColumn<ClassroomSummary, Number> countCol = new TableColumn<>("Student Count");
        countCol.setCellValueFactory(cellData -> cellData.getValue().studentCountProperty());
        countCol.setPrefWidth(100);

        TableColumn<ClassroomSummary, Number> avgCol = new TableColumn<>("Class Average");
        avgCol.setCellValueFactory(cellData -> cellData.getValue().classAverageProperty());
        avgCol.setCellFactory(col -> new TableCell<ClassroomSummary, Number>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%.2f", item.doubleValue()));
                }
            }
        });
        avgCol.setPrefWidth(100);

        summaryTable.getColumns().addAll(nameCol, countCol, avgCol);
        summaryTable.setPrefHeight(200);

        root.getChildren().addAll(title, classroomList, buttonBox, new Label("Summary:"), summaryTable);

        Scene scene = new Scene(root, 400, 500);
        primaryStage.setScene(scene);

        updateSummaryTable();
    }

    private void showStudentView() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        // Title
        Label title = new Label("Student Management - " + currentClassroom.getName());
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Back button
        Button backButton = new Button("← Back to Classrooms");
        backButton.setOnAction(e -> showClassroomView());

        // Student list
        ListView<Student> studentList = new ListView<>(currentClassroom.getStudents());
        studentList.setCellFactory(listView -> new ListCell<Student>() {
            @Override
            protected void updateItem(Student student, boolean empty) {
                super.updateItem(student, empty);
                if (empty || student == null) {
                    setText(null);
                } else {
                    setText(student.getName() + " (Avg: " + String.format("%.2f", student.getAverage()) + ")");
                }
            }
        });
        studentList.setPrefHeight(150);

        // Buttons
        HBox buttonBox = new HBox(10);
        Button addButton = new Button("Add Student");
        Button deleteButton = new Button("Delete Student");

        addButton.setOnAction(e -> addStudent());
        deleteButton.setOnAction(e -> deleteStudent(studentList));

        buttonBox.getChildren().addAll(addButton, deleteButton);

        // Grades summary table
        TableView<Student> gradesTable = new TableView<>(currentClassroom.getStudents());

        TableColumn<Student, String> nameCol = new TableColumn<>("Student Name");
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        nameCol.setPrefWidth(120);

        TableColumn<Student, Number> mathCol = new TableColumn<>("Math");
        mathCol.setCellValueFactory(cellData -> cellData.getValue().mathGradeProperty());
        mathCol.setPrefWidth(60);

        TableColumn<Student, Number> physicsCol = new TableColumn<>("Physics");
        physicsCol.setCellValueFactory(cellData -> cellData.getValue().physicsGradeProperty());
        physicsCol.setPrefWidth(60);

        TableColumn<Student, Number> historyCol = new TableColumn<>("History");
        historyCol.setCellValueFactory(cellData -> cellData.getValue().historyGradeProperty());
        historyCol.setPrefWidth(60);

        TableColumn<Student, String> avgCol = new TableColumn<>("Average");
        avgCol.setCellValueFactory(cellData -> {
            Student student = cellData.getValue();
            return new SimpleStringProperty(String.format("%.2f", student.getAverage()));
        });
        avgCol.setPrefWidth(70);

        gradesTable.getColumns().addAll(nameCol, mathCol, physicsCol, historyCol, avgCol);
        gradesTable.setPrefHeight(200);

        // Grade counts
        Label gradeCountsLabel = new Label("Grade Counts:");
        gradeCountsLabel.setStyle("-fx-font-weight: bold;");

        Label mathCountLabel = new Label();
        Label physicsCountLabel = new Label();
        Label historyCountLabel = new Label();
        Label overallAvgLabel = new Label();

        updateGradeCounts(mathCountLabel, physicsCountLabel, historyCountLabel, overallAvgLabel);

        // Update counts when students change
        currentClassroom.getStudents().addListener((javafx.collections.ListChangeListener<Student>) c -> {
            updateGradeCounts(mathCountLabel, physicsCountLabel, historyCountLabel, overallAvgLabel);
        });

        root.getChildren().addAll(
                backButton, title, studentList, buttonBox,
                new Label("Grades:"), gradesTable,
                gradeCountsLabel, mathCountLabel, physicsCountLabel, historyCountLabel, overallAvgLabel
        );

        Scene scene = new Scene(root, 500, 600);
        primaryStage.setScene(scene);
    }

    private void createClassroom() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Create Classroom");
        dialog.setHeaderText("Enter classroom name:");
        dialog.setContentText("Name:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            if (!name.trim().isEmpty()) {
                classrooms.add(new Classroom(name.trim()));
                updateSummaryTable();
            }
        });
    }

    private void deleteClassroom(ListView<Classroom> listView) {
        Classroom selected = listView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Classroom");
            alert.setHeaderText("Are you sure you want to delete this classroom?");
            alert.setContentText("Classroom: " + selected.getName());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                classrooms.remove(selected);
                updateSummaryTable();
            }
        } else {
            showAlert("No Selection", "Please select a classroom to delete.");
        }
    }

    private void addStudent() {
        Dialog<Student> dialog = new Dialog<>();
        dialog.setTitle("Add Student");
        dialog.setHeaderText("Enter student information:");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField nameField = new TextField();
        nameField.setPromptText("Student Name");
        Spinner<Integer> mathSpinner = new Spinner<>(0, 100, 0);
        Spinner<Integer> physicsSpinner = new Spinner<>(0, 100, 0);
        Spinner<Integer> historySpinner = new Spinner<>(0, 100, 0);

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Math Grade:"), 0, 1);
        grid.add(mathSpinner, 1, 1);
        grid.add(new Label("Physics Grade:"), 0, 2);
        grid.add(physicsSpinner, 1, 2);
        grid.add(new Label("History Grade:"), 0, 3);
        grid.add(historySpinner, 1, 3);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                String name = nameField.getText().trim();
                if (!name.isEmpty()) {
                    return new Student(name, mathSpinner.getValue(), physicsSpinner.getValue(), historySpinner.getValue());
                }
            }
            return null;
        });

        Optional<Student> result = dialog.showAndWait();
        result.ifPresent(student -> {
            currentClassroom.getStudents().add(student);
            updateSummaryTable();
        });
    }

    private void deleteStudent(ListView<Student> listView) {
        Student selected = listView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Student");
            alert.setHeaderText("Are you sure you want to delete this student?");
            alert.setContentText("Student: " + selected.getName());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                currentClassroom.getStudents().remove(selected);
                updateSummaryTable();
            }
        } else {
            showAlert("No Selection", "Please select a student to delete.");
        }
    }

    private void updateSummaryTable() {
        classroomSummaries.clear();
        for (Classroom classroom : classrooms) {
            classroomSummaries.add(new ClassroomSummary(
                    classroom.getName(),
                    classroom.getStudentCount(),
                    classroom.getClassAverage()
            ));
        }
    }

    private void updateGradeCounts(Label mathLabel, Label physicsLabel, Label historyLabel, Label avgLabel) {
        if (currentClassroom.getStudents().isEmpty()) {
            mathLabel.setText("Math grades: 0 students");
            physicsLabel.setText("Physics grades: 0 students");
            historyLabel.setText("History grades: 0 students");
            avgLabel.setText("Overall average: 0.00");
            return;
        }

        List<Student> students = currentClassroom.getStudents();
        int mathCount = students.size();
        int physicsCount = students.size();
        int historyCount = students.size();

        double mathAvg = students.stream().mapToInt(Student::getMathGrade).average().orElse(0);
        double physicsAvg = students.stream().mapToInt(Student::getPhysicsGrade).average().orElse(0);
        double historyAvg = students.stream().mapToInt(Student::getHistoryGrade).average().orElse(0);
        double overallAvg = currentClassroom.getClassAverage();

        mathLabel.setText(String.format("Math grades: %d students (avg: %.2f)", mathCount, mathAvg));
        physicsLabel.setText(String.format("Physics grades: %d students (avg: %.2f)", physicsCount, physicsAvg));
        historyLabel.setText(String.format("History grades: %d students (avg: %.2f)", historyCount, historyAvg));
        avgLabel.setText(String.format("Overall class average: %.2f", overallAvg));
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}