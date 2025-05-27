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
        private final List<Integer> mathGrades;
        private final List<Integer> physicsGrades;
        private final List<Integer> historyGrades;

        private final List<Integer> biologyGrades;
        private final List<Integer> literatureGrades;
        private final List<Integer> digitalCultureGrades;

        public Student(String name) {
            this.name = new SimpleStringProperty(name);
            this.mathGrades = new ArrayList<>();
            this.physicsGrades = new ArrayList<>();
            this.historyGrades = new ArrayList<>();
            this.biologyGrades = new ArrayList<>();
            this.literatureGrades = new ArrayList<>();
            this.digitalCultureGrades = new ArrayList<>();
        }

        // Legacy constructor for backwards compatibility
        public Student(String name, int mathGrade, int physicsGrade, int historyGrade) {
            this(name);
            this.mathGrades.add(mathGrade);
            this.physicsGrades.add(physicsGrade);
            this.historyGrades.add(historyGrade);
        }

        public String getName() { return name.get(); }
        public void setName(String name) { this.name.set(name); }
        public SimpleStringProperty nameProperty() { return name; }

        public List<Integer> getMathGrades() { return mathGrades; }
        public List<Integer> getPhysicsGrades() { return physicsGrades; }
        public List<Integer> getHistoryGrades() { return historyGrades; }

        public List<Integer> getBiologyGrades() { return biologyGrades; }
        public List<Integer> getLiteratureGrades() { return literatureGrades; }
        public List<Integer> getDigitalCultureGrades() { return digitalCultureGrades; }

        public void addMathGrade(int grade) { mathGrades.add(grade); }
        public void addPhysicsGrade(int grade) { physicsGrades.add(grade); }
        public void addHistoryGrade(int grade) { historyGrades.add(grade); }

        public void addBiologyGrade(int grade) { biologyGrades.add(grade); }
        public void addLiteratureGrade(int grade) { literatureGrades.add(grade); }
        public void addDigitalCultureGrade(int grade) { digitalCultureGrades.add(grade); }

        public double getMathAverage() {
            return mathGrades.isEmpty() ? 0.0 : mathGrades.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        }

        public double getPhysicsAverage() {
            return physicsGrades.isEmpty() ? 0.0 : physicsGrades.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        }

        public double getHistoryAverage() {
            return historyGrades.isEmpty() ? 0.0 : historyGrades.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        }

        public double getBiologyAverage() {
            return biologyGrades.isEmpty() ? 0.0 : biologyGrades.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        }
        public double getLiteratureAverage() {
            return literatureGrades.isEmpty() ? 0.0 : literatureGrades.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        }
        public double getDigitalCultureAverage() {
            return digitalCultureGrades.isEmpty() ? 0.0 : digitalCultureGrades.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        }

        // Legacy methods for backwards compatibility
        public int getMathGrade() { return (int) Math.round(getMathAverage()); }
        public int getPhysicsGrade() { return (int) Math.round(getPhysicsAverage()); }
        public int getHistoryGrade() { return (int) Math.round(getHistoryAverage()); }

        public int getBiologyGrade() { return (int) Math.round(getBiologyAverage()); }
        public int getLiteratureGrade() { return (int) Math.round(getLiteratureAverage()); }
        public int getDigitalCultureGrade() { return (int) Math.round(getDigitalCultureAverage()); }

        public SimpleIntegerProperty mathGradeProperty() {
            return new SimpleIntegerProperty(getMathGrade());
        }
        public SimpleIntegerProperty physicsGradeProperty() {
            return new SimpleIntegerProperty(getPhysicsGrade());
        }
        public SimpleIntegerProperty historyGradeProperty() {
            return new SimpleIntegerProperty(getHistoryGrade());
        }

        public SimpleIntegerProperty biologyGradeProperty() {
            return new SimpleIntegerProperty(getBiologyGrade());
        }
        public SimpleIntegerProperty literatureGradeProperty() {
            return new SimpleIntegerProperty(getLiteratureGrade());
        }
        public SimpleIntegerProperty digitalCultureGradeProperty() {
            return new SimpleIntegerProperty(getDigitalCultureGrade());
        }

        public double getAverage() {
            double mathAvg = getMathAverage();
            double physicsAvg = getPhysicsAverage();
            double historyAvg = getHistoryAverage();
            double biologyAvg = getBiologyAverage();
            double literatureAvg = getLiteratureAverage();
            double digitalCultureAvg = getDigitalCultureAverage();

            int subjects = 0;
            double total = 0.0;

            if (!mathGrades.isEmpty()) {
                total += mathAvg;
                subjects++;
            }
            if (!physicsGrades.isEmpty()) {
                total += physicsAvg;
                subjects++;
            }
            if (!historyGrades.isEmpty()) {
                total += historyAvg;
                subjects++;
            }

            if (!biologyGrades.isEmpty()) {
                total += biologyAvg;
                subjects++;
            }
            if (!literatureGrades.isEmpty()) {
                total += literatureAvg;
                subjects++;
            }
            if (!digitalCultureGrades.isEmpty()) {
                total += digitalCultureAvg;
                subjects++;
            }

            return subjects > 0 ? total / subjects : 0.0;
        }

        public String getGradesSummary() {
            StringBuilder sb = new StringBuilder();
            sb.append("Math: ").append(mathGrades.size()).append(" grades");
            if (!mathGrades.isEmpty()) {
                sb.append(" (avg: ").append(String.format("%.2f", getMathAverage())).append(")");
            }
            sb.append(", Physics: ").append(physicsGrades.size()).append(" grades");
            if (!physicsGrades.isEmpty()) {
                sb.append(" (avg: ").append(String.format("%.2f", getPhysicsAverage())).append(")");
            }
            sb.append(", History: ").append(historyGrades.size()).append(" grades");
            if (!historyGrades.isEmpty()) {
                sb.append(" (avg: ").append(String.format("%.2f", getHistoryAverage())).append(")");
            }
            sb.append(", Biology: ").append(biologyGrades.size()).append(" grades");
            if (!biologyGrades.isEmpty()) {
                sb.append(" (avg: ").append(String.format("%.2f", getBiologyAverage())).append(")");
            }
            sb.append(", Literature: ").append(literatureGrades.size()).append(" grades");
            if (!literatureGrades.isEmpty()) {
                sb.append(" (avg: ").append(String.format("%.2f", getLiteratureAverage())).append(")");
            }
            sb.append(", Digital Culture: ").append(digitalCultureGrades.size()).append(" grades");
            if (!digitalCultureGrades.isEmpty()) {
                sb.append(" (avg: ").append(String.format("%.2f", getDigitalCultureAverage())).append(")");
            }
            return sb.toString();
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
                    setGraphic(null);
                    setText(null);
                } else {
                    VBox vbox = new VBox(2);
                    Label nameLabel = new Label(student.getName() + " (Overall Avg: " + String.format("%.2f", student.getAverage()) + ")");
                    nameLabel.setStyle("-fx-font-weight: bold;");
                    Label gradesLabel = new Label(student.getGradesSummary());
                    gradesLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: gray;");

                    HBox buttonBox = new HBox(5);
                    Button addMathBtn = new Button("+ Math");
                    Button addPhysicsBtn = new Button("+ Physics");
                    Button addHistoryBtn = new Button("+ History");
                    Button addBiologyBtn = new Button("+ Biology");
                    Button addLiteratureBtn = new Button("+ Literature");
                    Button addDigitalCultureBtn = new Button("+ Digital Culture");

                    addMathBtn.setStyle("-fx-font-size: 10px;");
                    addPhysicsBtn.setStyle("-fx-font-size: 10px;");
                    addHistoryBtn.setStyle("-fx-font-size: 10px;");
                    addBiologyBtn.setStyle("-fx-font-size: 10px;");
                    addLiteratureBtn.setStyle("-fx-font-size: 10px;");
                    addDigitalCultureBtn.setStyle("-fx-font-size: 10px;");

                    addMathBtn.setOnAction(e -> addGradeToStudent(student, "Math"));
                    addPhysicsBtn.setOnAction(e -> addGradeToStudent(student, "Physics"));
                    addHistoryBtn.setOnAction(e -> addGradeToStudent(student, "History"));
                    addBiologyBtn.setOnAction(e -> addGradeToStudent(student, "Biology"));
                    addLiteratureBtn.setOnAction(e -> addGradeToStudent(student, "Literature"));
                    addDigitalCultureBtn.setOnAction(e -> addGradeToStudent(student, "Digital Culture"));


                    buttonBox.getChildren().addAll(addMathBtn, addPhysicsBtn, addHistoryBtn, addBiologyBtn, addLiteratureBtn, addDigitalCultureBtn);
                    vbox.getChildren().addAll(nameLabel, gradesLabel, buttonBox);
                    setGraphic(vbox);
                    setText(null);
                }
            }
        });
        studentList.setPrefHeight(200);

        // Buttons
        HBox buttonBox = new HBox(10);
        Button addButton = new Button("Add Student");
        Button deleteButton = new Button("Delete Student");
        Button viewGradesButton = new Button("View All Grades");

        addButton.setOnAction(e -> addStudent());
        deleteButton.setOnAction(e -> deleteStudent(studentList));
        viewGradesButton.setOnAction(e -> showDetailedGradesView());

        buttonBox.getChildren().addAll(addButton, deleteButton, viewGradesButton);

        // Grades summary table
        TableView<Student> gradesTable = new TableView<>(currentClassroom.getStudents());

        TableColumn<Student, String> nameCol = new TableColumn<>("Student Name");
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        nameCol.setPrefWidth(120);

        TableColumn<Student, String> mathCol = new TableColumn<>("Math Avg");
        mathCol.setCellValueFactory(cellData -> {
            Student student = cellData.getValue();
            return new SimpleStringProperty(String.format("%.2f (%d)", student.getMathAverage(), student.getMathGrades().size()));
        });
        mathCol.setPrefWidth(80);

        TableColumn<Student, String> physicsCol = new TableColumn<>("Physics Avg");
        physicsCol.setCellValueFactory(cellData -> {
            Student student = cellData.getValue();
            return new SimpleStringProperty(String.format("%.2f (%d)", student.getPhysicsAverage(), student.getPhysicsGrades().size()));
        });
        physicsCol.setPrefWidth(80);

        TableColumn<Student, String> historyCol = new TableColumn<>("History Avg");
        historyCol.setCellValueFactory(cellData -> {
            Student student = cellData.getValue();
            return new SimpleStringProperty(String.format("%.2f (%d)", student.getHistoryAverage(), student.getHistoryGrades().size()));
        });
        historyCol.setPrefWidth(80);

        TableColumn<Student, String> biologyCol = new TableColumn<>("Biology Avg");
        biologyCol.setCellValueFactory(cellData -> {
            Student student = cellData.getValue();
            return new SimpleStringProperty(String.format("%.2f (%d)", student.getBiologyAverage(), student.getBiologyGrades().size()));
        });
        biologyCol.setPrefWidth(80);

        TableColumn<Student, String> literatureCol = new TableColumn<>("Literature Avg");
        literatureCol.setCellValueFactory(cellData -> {
            Student student = cellData.getValue();
            return new SimpleStringProperty(String.format("%.2f (%d)", student.getLiteratureAverage(), student.getLiteratureGrades().size()));
        });
        literatureCol.setPrefWidth(80);

        TableColumn<Student, String> digitalCultureCol = new TableColumn<>("Digital Culture Avg");
        digitalCultureCol.setCellValueFactory(cellData -> {
            Student student = cellData.getValue();
            return new SimpleStringProperty(String.format("%.2f (%d)", student.getDigitalCultureAverage(), student.getDigitalCultureGrades().size()));
        });
        digitalCultureCol.setPrefWidth(100);

        TableColumn<Student, String> avgCol = new TableColumn<>("Average");
        avgCol.setCellValueFactory(cellData -> {
            Student student = cellData.getValue();
            return new SimpleStringProperty(String.format("%.2f", student.getAverage()));
        });
        avgCol.setPrefWidth(70);

        gradesTable.getColumns().addAll(nameCol, mathCol, physicsCol, historyCol, biologyCol, literatureCol, digitalCultureCol, avgCol);
        gradesTable.setPrefHeight(200);

        // Grade counts
        Label gradeCountsLabel = new Label("Grade Counts:");
        gradeCountsLabel.setStyle("-fx-font-weight: bold;");

        Label mathCountLabel = new Label();
        Label physicsCountLabel = new Label();
        Label historyCountLabel = new Label();
        Label biologyCountLabel = new Label();
        Label literatureCountLabel = new Label();
        Label digitalCultureCountLabel = new Label();
        Label overallAvgLabel = new Label();

        updateGradeCounts(mathCountLabel, physicsCountLabel, historyCountLabel, biologyCountLabel, literatureCountLabel, digitalCultureCountLabel, overallAvgLabel);


        // Update counts when students change
        currentClassroom.getStudents().addListener((javafx.collections.ListChangeListener<Student>) c -> {
            updateGradeCounts(mathCountLabel, physicsCountLabel, historyCountLabel, biologyCountLabel, literatureCountLabel, digitalCultureCountLabel, overallAvgLabel);
        });

        root.getChildren().addAll(
                backButton, title, studentList, buttonBox,
                new Label("Grade Summary:"), gradesTable,
                gradeCountsLabel, mathCountLabel, physicsCountLabel, historyCountLabel,
                biologyCountLabel, literatureCountLabel, digitalCultureCountLabel, overallAvgLabel
        );

        Scene scene = new Scene(root, 600, 700);
        primaryStage.setScene(scene);
    }

    private void addGradeToStudent(Student student, String subject) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add " + subject + " Grade");
        dialog.setHeaderText("Add a new " + subject.toLowerCase() + " grade for " + student.getName() + ":");
        dialog.setContentText("Grade (1-5):");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(gradeStr -> {
            try {
                int grade = Integer.parseInt(gradeStr.trim());
                if (grade >= 1 && grade <= 5) {
                    switch (subject) {
                        case "Math" -> student.addMathGrade(grade);
                        case "Physics" -> student.addPhysicsGrade(grade);
                        case "History" -> student.addHistoryGrade(grade);
                        case "Biology" -> student.addBiologyGrade(grade);
                        case "Literature" -> student.addLiteratureGrade(grade);
                        case "Digital Culture" -> student.addDigitalCultureGrade(grade);
                    }
                    updateSummaryTable();
                    showStudentView(); // Refresh the view
                } else {
                    showAlert("Invalid Grade", "Grade must be between 1 and 5.");
                }
            } catch (NumberFormatException e) {
                showAlert("Invalid Input", "Please enter a valid number.");
            }
        });
    }

    private void showDetailedGradesView() {
        Stage detailStage = new Stage();
        detailStage.setTitle("Detailed Grades - " + currentClassroom.getName());

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        Label title = new Label("All Grades for " + currentClassroom.getName());
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        TextArea gradesArea = new TextArea();
        gradesArea.setEditable(false);
        gradesArea.setPrefRowCount(20);
        gradesArea.setPrefColumnCount(50);

        StringBuilder gradesText = new StringBuilder();
        for (Student student : currentClassroom.getStudents()) {
            gradesText.append("=== ").append(student.getName()).append(" ===\n");

            gradesText.append("Math Grades (").append(student.getMathGrades().size()).append("): ");
            if (student.getMathGrades().isEmpty()) {
                gradesText.append("No grades");
            } else {
                gradesText.append(student.getMathGrades().toString());
                gradesText.append(" | Average: ").append(String.format("%.2f", student.getMathAverage()));
            }
            gradesText.append("\n");

            gradesText.append("Physics Grades (").append(student.getPhysicsGrades().size()).append("): ");
            if (student.getPhysicsGrades().isEmpty()) {
                gradesText.append("No grades");
            } else {
                gradesText.append(student.getPhysicsGrades().toString());
                gradesText.append(" | Average: ").append(String.format("%.2f", student.getPhysicsAverage()));
            }
            gradesText.append("\n");

            gradesText.append("History Grades (").append(student.getHistoryGrades().size()).append("): ");
            if (student.getHistoryGrades().isEmpty()) {
                gradesText.append("No grades");
            } else {
                gradesText.append(student.getHistoryGrades().toString());
                gradesText.append(" | Average: ").append(String.format("%.2f", student.getHistoryAverage()));
            }
            gradesText.append("\n");

            gradesText.append("Biology Grades (").append(student.getBiologyGrades().size()).append("): ");
            if (student.getBiologyGrades().isEmpty()) {
                gradesText.append("No grades");
            } else {
                gradesText.append(student.getBiologyGrades().toString());
                gradesText.append(" | Average: ").append(String.format("%.2f", student.getBiologyAverage()));
            }
            gradesText.append("\n");

            gradesText.append("Literature Grades (").append(student.getLiteratureGrades().size()).append("): ");
            if (student.getLiteratureGrades().isEmpty()) {
                gradesText.append("No grades");
            } else {
                gradesText.append(student.getLiteratureGrades().toString());
                gradesText.append(" | Average: ").append(String.format("%.2f", student.getLiteratureAverage()));
            }
            gradesText.append("\n");

            gradesText.append("Digital Culture Grades (").append(student.getDigitalCultureGrades().size()).append("): ");
            if (student.getDigitalCultureGrades().isEmpty()) {
                gradesText.append("No grades");
            } else {
                gradesText.append(student.getDigitalCultureGrades().toString());
                gradesText.append(" | Average: ").append(String.format("%.2f", student.getDigitalCultureAverage()));
            }
            gradesText.append("\n");

            gradesText.append("Overall Average: ").append(String.format("%.2f", student.getAverage()));
            gradesText.append("\n\n");
        }

        gradesArea.setText(gradesText.toString());

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> detailStage.close());

        root.getChildren().addAll(title, gradesArea, closeButton);

        Scene scene = new Scene(root, 500, 600);
        detailStage.setScene(scene);
        detailStage.show();
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
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Student");
        dialog.setHeaderText("Enter student name:");
        dialog.setContentText("Name:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            if (!name.trim().isEmpty()) {
                currentClassroom.getStudents().add(new Student(name.trim()));
                updateSummaryTable();
            }
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

    private void updateGradeCounts(Label mathLabel, Label physicsLabel, Label historyLabel, Label biologyLabel, Label literatureLabel, Label digitalCultureLabel, Label avgLabel)
    {
        if (currentClassroom.getStudents().isEmpty()) {
            mathLabel.setText("Math grades: 0 students, 0 total grades");
            physicsLabel.setText("Physics grades: 0 students, 0 total grades");
            historyLabel.setText("History grades: 0 students, 0 total grades");
            biologyLabel.setText("Biology grades: 0 students, 0 total grades");
            literatureLabel.setText("Literature grades: 0 students, 0 total grades");
            digitalCultureLabel.setText("Digital Culture grades: 0 students, 0 total grades");
            avgLabel.setText("Overall average: 0.00");
            return;
        }

        List<Student> students = currentClassroom.getStudents();

        int mathStudents = (int) students.stream().filter(s -> !s.getMathGrades().isEmpty()).count();
        int physicsStudents = (int) students.stream().filter(s -> !s.getPhysicsGrades().isEmpty()).count();
        int historyStudents = (int) students.stream().filter(s -> !s.getHistoryGrades().isEmpty()).count();

        int totalMathGrades = students.stream().mapToInt(s -> s.getMathGrades().size()).sum();
        int totalPhysicsGrades = students.stream().mapToInt(s -> s.getPhysicsGrades().size()).sum();
        int totalHistoryGrades = students.stream().mapToInt(s -> s.getHistoryGrades().size()).sum();

        int biologyStudents = (int) students.stream().filter(s -> !s.getBiologyGrades().isEmpty()).count();
        int literatureStudents = (int) students.stream().filter(s -> !s.getLiteratureGrades().isEmpty()).count();
        int digitalCultureStudents = (int) students.stream().filter(s -> !s.getDigitalCultureGrades().isEmpty()).count();

        int totalBiologyGrades = students.stream().mapToInt(s -> s.getBiologyGrades().size()).sum();
        int totalLiteratureGrades = students.stream().mapToInt(s -> s.getLiteratureGrades().size()).sum();
        int totalDigitalCultureGrades = students.stream().mapToInt(s -> s.getDigitalCultureGrades().size()).sum();

        double mathAvg = mathStudents > 0 ? students.stream()
                .filter(s -> !s.getMathGrades().isEmpty())
                .mapToDouble(Student::getMathAverage)
                .average().orElse(0) : 0;

        double physicsAvg = physicsStudents > 0 ? students.stream()
                .filter(s -> !s.getPhysicsGrades().isEmpty())
                .mapToDouble(Student::getPhysicsAverage)
                .average().orElse(0) : 0;

        double historyAvg = historyStudents > 0 ? students.stream()
                .filter(s -> !s.getHistoryGrades().isEmpty())
                .mapToDouble(Student::getHistoryAverage)
                .average().orElse(0) : 0;

        double biologyAvg = biologyStudents > 0 ? students.stream()
                .filter(s -> !s.getBiologyGrades().isEmpty())
                .mapToDouble(Student::getBiologyAverage)
                .average().orElse(0) : 0;

        double literatureAvg = literatureStudents > 0 ? students.stream()
                .filter(s -> !s.getLiteratureGrades().isEmpty())
                .mapToDouble(Student::getLiteratureAverage)
                .average().orElse(0) : 0;

        double digitalCultureAvg = digitalCultureStudents > 0 ? students.stream()
                .filter(s -> !s.getDigitalCultureGrades().isEmpty())
                .mapToDouble(Student::getDigitalCultureAverage)
                .average().orElse(0) : 0;

        double overallAvg = currentClassroom.getClassAverage();

        mathLabel.setText(String.format("Math: %d students, %d total grades (avg: %.2f)", mathStudents, totalMathGrades, mathAvg));
        physicsLabel.setText(String.format("Physics: %d students, %d total grades (avg: %.2f)", physicsStudents, totalPhysicsGrades, physicsAvg));
        historyLabel.setText(String.format("History: %d students, %d total grades (avg: %.2f)", historyStudents, totalHistoryGrades, historyAvg));
        biologyLabel.setText(String.format("Biology: %d students, %d total grades (avg: %.2f)", biologyStudents, totalBiologyGrades, biologyAvg));
        literatureLabel.setText(String.format("Literature: %d students, %d total grades (avg: %.2f)", literatureStudents, totalLiteratureGrades, literatureAvg));
        digitalCultureLabel.setText(String.format("Digital Culture: %d students, %d total grades (avg: %.2f)", digitalCultureStudents, totalDigitalCultureGrades, digitalCultureAvg));
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