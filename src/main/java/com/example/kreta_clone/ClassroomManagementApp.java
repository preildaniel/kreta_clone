package com.example.kreta_clone;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClassroomManagementApp extends Application {

    // Modern color palette
    private static final String PRIMARY_COLOR = "#2563eb";      // Blue
    private static final String PRIMARY_DARK = "#1d4ed8";      // Darker blue
    private static final String SECONDARY_COLOR = "#10b981";   // Green
    private static final String ACCENT_COLOR = "#f59e0b";      // Amber
    private static final String BACKGROUND_COLOR = "#f8fafc";  // Light gray
    private static final String CARD_COLOR = "#ffffff";        // White
    private static final String TEXT_PRIMARY = "#1e293b";      // Dark gray
    private static final String TEXT_SECONDARY = "#64748b";    // Medium gray
    private static final String BORDER_COLOR = "#e2e8f0";     // Light border
    private static final String DANGER_COLOR = "#ef4444";     // Red

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

        public String getName() {
            return name.get();
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public List<Integer> getMathGrades() {
            return mathGrades;
        }

        public List<Integer> getPhysicsGrades() {
            return physicsGrades;
        }

        public List<Integer> getHistoryGrades() {
            return historyGrades;
        }

        public List<Integer> getBiologyGrades() {
            return biologyGrades;
        }

        public List<Integer> getLiteratureGrades() {
            return literatureGrades;
        }

        public List<Integer> getDigitalCultureGrades() {
            return digitalCultureGrades;
        }

        public void addMathGrade(int grade) {
            mathGrades.add(grade);
        }

        public void addPhysicsGrade(int grade) {
            physicsGrades.add(grade);
        }

        public void addHistoryGrade(int grade) {
            historyGrades.add(grade);
        }

        public void addBiologyGrade(int grade) {
            biologyGrades.add(grade);
        }

        public void addLiteratureGrade(int grade) {
            literatureGrades.add(grade);
        }

        public void addDigitalCultureGrade(int grade) {
            digitalCultureGrades.add(grade);
        }

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
        public int getMathGrade() {
            return (int) Math.round(getMathAverage());
        }

        public int getPhysicsGrade() {
            return (int) Math.round(getPhysicsAverage());
        }

        public int getHistoryGrade() {
            return (int) Math.round(getHistoryAverage());
        }

        public int getBiologyGrade() {
            return (int) Math.round(getBiologyAverage());
        }

        public int getLiteratureGrade() {
            return (int) Math.round(getLiteratureAverage());
        }

        public int getDigitalCultureGrade() {
            return (int) Math.round(getDigitalCultureAverage());
        }

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

        public String getName() {
            return name.get();
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public ObservableList<Student> getStudents() {
            return students;
        }

        public int getStudentCount() {
            return students.size();
        }

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

        public String getClassroomName() {
            return classroomName.get();
        }

        public SimpleStringProperty classroomNameProperty() {
            return classroomName;
        }

        public int getStudentCount() {
            return studentCount.get();
        }

        public SimpleIntegerProperty studentCountProperty() {
            return studentCount;
        }

        public double getClassAverage() {
            return classAverage.get();
        }

        public SimpleDoubleProperty classAverageProperty() {
            return classAverage;
        }
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

    private String getAppStyles() {
        return ".root {\n" +
                "    -fx-background-color: " + BACKGROUND_COLOR + ";\n" +
                "    -fx-font-family: 'Segoe UI', 'Helvetica Neue', sans-serif;\n" +
                "}\n" +
                "\n" +
                ".title-label {\n" +
                "    -fx-font-size: 28px;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-text-fill: " + TEXT_PRIMARY + ";\n" +
                "    -fx-padding: 0 0 20 0;\n" +
                "}\n" +
                "\n" +
                ".subtitle-label {\n" +
                "    -fx-font-size: 18px;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-text-fill: " + TEXT_PRIMARY + ";\n" +
                "    -fx-padding: 10 0 10 0;\n" +
                "}\n" +
                "\n" +
                ".card {\n" +
                "    -fx-background-color: " + CARD_COLOR + ";\n" +
                "    -fx-background-radius: 12;\n" +
                "    -fx-border-color: " + BORDER_COLOR + ";\n" +
                "    -fx-border-radius: 12;\n" +
                "    -fx-border-width: 1;\n" +
                "    -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);\n" +
                "}\n" +
                "\n" +
                ".primary-button {\n" +
                "    -fx-background-color: " + PRIMARY_COLOR + ";\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-border-radius: 8;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 14px;\n" +
                "    -fx-padding: 12 24 12 24;\n" +
                "    -fx-cursor: hand;\n" +
                "    -fx-effect: dropshadow(gaussian, rgba(37,99,235,0.3), 8, 0, 0, 2);\n" +
                "}\n" +
                "\n" +
                ".primary-button:hover {\n" +
                "    -fx-background-color: " + PRIMARY_DARK + ";\n" +
                "    -fx-effect: dropshadow(gaussian, rgba(37,99,235,0.4), 12, 0, 0, 4);\n" +
                "}\n" +
                "\n" +
                ".secondary-button {\n" +
                "    -fx-background-color: " + SECONDARY_COLOR + ";\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-border-radius: 8;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 14px;\n" +
                "    -fx-padding: 12 24 12 24;\n" +
                "    -fx-cursor: hand;\n" +
                "    -fx-effect: dropshadow(gaussian, rgba(16,185,129,0.3), 8, 0, 0, 2);\n" +
                "}\n" +
                "\n" +
                ".secondary-button:hover {\n" +
                "    -fx-background-color: #059669;\n" +
                "    -fx-effect: dropshadow(gaussian, rgba(16,185,129,0.4), 12, 0, 0, 4);\n" +
                "}\n" +
                "\n" +
                ".danger-button {\n" +
                "    -fx-background-color: " + DANGER_COLOR + ";\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-border-radius: 8;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 14px;\n" +
                "    -fx-padding: 12 24 12 24;\n" +
                "    -fx-cursor: hand;\n" +
                "    -fx-effect: dropshadow(gaussian, rgba(239,68,68,0.3), 8, 0, 0, 2);\n" +
                "}\n" +
                "\n" +
                ".danger-button:hover {\n" +
                "    -fx-background-color: #dc2626;\n" +
                "    -fx-effect: dropshadow(gaussian, rgba(239,68,68,0.4), 12, 0, 0, 4);\n" +
                "}\n" +
                "\n" +
                ".accent-button {\n" +
                "    -fx-background-color: " + ACCENT_COLOR + ";\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-background-radius: 6;\n" +
                "    -fx-border-radius: 6;\n" +
                "    -fx-font-size: 11px;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-padding: 6 12 6 12;\n" +
                "    -fx-cursor: hand;\n" +
                "}\n" +
                "\n" +
                ".accent-button:hover {\n" +
                "    -fx-background-color: #d97706;\n" +
                "}\n" +
                "\n" +
                ".back-button {\n" +
                "    -fx-background-color: transparent;\n" +
                "    -fx-text-fill: " + PRIMARY_COLOR + ";\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-border-radius: 8;\n" +
                "    -fx-border-color: " + PRIMARY_COLOR + ";\n" +
                "    -fx-border-width: 2;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 14px;\n" +
                "    -fx-padding: 8 16 8 16;\n" +
                "    -fx-cursor: hand;\n" +
                "}\n" +
                "\n" +
                ".back-button:hover {\n" +
                "    -fx-background-color: " + PRIMARY_COLOR + ";\n" +
                "    -fx-text-fill: white;\n" +
                "}\n" +
                "\n" +
                ".modern-list-view {\n" +
                "    -fx-background-color: " + CARD_COLOR + ";\n" +
                "    -fx-background-radius: 12;\n" +
                "    -fx-border-color: " + BORDER_COLOR + ";\n" +
                "    -fx-border-radius: 12;\n" +
                "    -fx-border-width: 1;\n" +
                "    -fx-padding: 8;\n" +
                "}\n" +
                "\n" +
                ".modern-list-view .list-cell {\n" +
                "    -fx-background-color: transparent;\n" +
                "    -fx-border-color: transparent;\n" +
                "    -fx-padding: 12;\n" +
                "    -fx-background-radius: 8;\n" +
                "}\n" +
                "\n" +
                ".modern-list-view .list-cell:selected {\n" +
                "    -fx-background-color: " + PRIMARY_COLOR + "20;\n" +
                "    -fx-border-color: " + PRIMARY_COLOR + ";\n" +
                "    -fx-border-width: 1;\n" +
                "    -fx-border-radius: 8;\n" +
                "}\n" +
                "\n" +
                ".modern-table-view {\n" +
                "    -fx-background-color: " + CARD_COLOR + ";\n" +
                "    -fx-background-radius: 12;\n" +
                "    -fx-border-color: " + BORDER_COLOR + ";\n" +
                "    -fx-border-radius: 12;\n" +
                "    -fx-border-width: 1;\n" +
                "}\n" +
                "\n" +
                ".modern-table-view .column-header {\n" +
                "    -fx-background-color: " + BACKGROUND_COLOR + ";\n" +
                "    -fx-border-color: " + BORDER_COLOR + ";\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-text-fill: " + TEXT_PRIMARY + ";\n" +
                "    -fx-padding: 12;\n" +
                "}\n" +
                "\n" +
                ".modern-table-view .table-row-cell:selected {\n" +
                "    -fx-background-color: " + PRIMARY_COLOR + "20;\n" +
                "}\n" +
                "\n" +
                ".stats-label {\n" +
                "    -fx-text-fill: " + TEXT_SECONDARY + ";\n" +
                "    -fx-font-size: 14px;\n" +
                "    -fx-padding: 4 0 4 0;\n" +
                "}\n" +
                "\n" +
                ".student-name-label {\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 16px;\n" +
                "    -fx-text-fill: " + TEXT_PRIMARY + ";\n" +
                "}\n" +
                "\n" +
                ".student-summary-label {\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-text-fill: " + TEXT_SECONDARY + ";\n" +
                "    -fx-padding: 4 0 8 0;\n" +
                "}";
    }

    private void showClassroomView() {
        VBox root = new VBox(24);
        root.setPadding(new Insets(32));
        root.getStyleClass().add("root");

        // Header
        VBox header = new VBox(8);
        Label title = new Label("Classroom Management");
        title.getStyleClass().add("title-label");
        Label subtitle = new Label("Manage your classrooms and track student progress");
        subtitle.setStyle("-fx-text-fill: " + TEXT_SECONDARY + "; -fx-font-size: 16px;");
        header.getChildren().addAll(title, subtitle);

        // Classroom list card
        VBox classroomCard = new VBox(16);
        classroomCard.getStyleClass().add("card");
        classroomCard.setPadding(new Insets(24));

        Label classroomLabel = new Label("Your Classrooms");
        classroomLabel.getStyleClass().add("subtitle-label");

        ListView<Classroom> classroomList = new ListView<>(classrooms);
        classroomList.getStyleClass().add("modern-list-view");
        classroomList.setCellFactory(listView -> new ListCell<Classroom>() {
            @Override
            protected void updateItem(Classroom classroom, boolean empty) {
                super.updateItem(classroom, empty);
                if (empty || classroom == null) {
                    setGraphic(null);
                } else {
                    HBox container = new HBox();
                    container.setAlignment(Pos.CENTER_LEFT);
                    container.setSpacing(16);

                    VBox info = new VBox(4);
                    Label nameLabel = new Label(classroom.getName());
                    nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: " + TEXT_PRIMARY + ";");
                    Label statsLabel = new Label(classroom.getStudentCount() + " students • Avg: " + String.format("%.2f", classroom.getClassAverage()));
                    statsLabel.setStyle("-fx-text-fill: " + TEXT_SECONDARY + "; -fx-font-size: 14px;");
                    info.getChildren().addAll(nameLabel, statsLabel);

                    Region spacer = new Region();
                    HBox.setHgrow(spacer, Priority.ALWAYS);

                    Button enterButton = new Button("Enter");
                    enterButton.getStyleClass().add("primary-button");
                    enterButton.setOnAction(e -> {
                        currentClassroom = classroom;
                        showStudentView();
                    });

                    container.getChildren().addAll(info, spacer, enterButton);
                    setGraphic(container);
                }
            }
        });
        classroomList.setPrefHeight(200);

        HBox buttonBox = new HBox(12);
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        Button createButton = new Button("Create Classroom");
        createButton.getStyleClass().add("primary-button");
        Button deleteButton = new Button("Delete Classroom");
        deleteButton.getStyleClass().add("danger-button");

        createButton.setOnAction(e -> createClassroom());
        deleteButton.setOnAction(e -> deleteClassroom(classroomList));

        buttonBox.getChildren().addAll(createButton, deleteButton);
        classroomCard.getChildren().addAll(classroomLabel, classroomList, buttonBox);

        // Summary card
        VBox summaryCard = new VBox(16);
        summaryCard.getStyleClass().add("card");
        summaryCard.setPadding(new Insets(24));

        Label summaryLabel = new Label("Summary Statistics");
        summaryLabel.getStyleClass().add("subtitle-label");

        TableView<ClassroomSummary> summaryTable = new TableView<>(classroomSummaries);
        summaryTable.getStyleClass().add("modern-table-view");

        TableColumn<ClassroomSummary, String> nameCol = new TableColumn<>("Classroom Name");
        nameCol.setCellValueFactory(cellData -> cellData.getValue().classroomNameProperty());
        nameCol.setPrefWidth(200);

        TableColumn<ClassroomSummary, Number> countCol = new TableColumn<>("Students");
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
        avgCol.setPrefWidth(120);

        summaryTable.getColumns().addAll(nameCol, countCol, avgCol);
        summaryTable.setPrefHeight(200);

        summaryCard.getChildren().addAll(summaryLabel, summaryTable);

        root.getChildren().addAll(header, classroomCard, summaryCard);

        Scene scene = new Scene(root, 800, 700);
        scene.getStylesheets().add("data:text/css;base64," +
                java.util.Base64.getEncoder().encodeToString(getAppStyles().getBytes()));
        primaryStage.setScene(scene);

        updateSummaryTable();
    }

    private void showStudentView() {
        VBox root = new VBox(24);
        root.setPadding(new Insets(32));
        root.getStyleClass().add("root");

        // Header with back button
        HBox header = new HBox(16);
        header.setAlignment(Pos.CENTER_LEFT);
        Button backButton = new Button("← Back to Classrooms");
        backButton.getStyleClass().add("back-button");
        backButton.setOnAction(e -> showClassroomView());

        VBox titleBox = new VBox(4);
        Label title = new Label(currentClassroom.getName());
        title.getStyleClass().add("title-label");
        Label subtitle = new Label("Manage students and track their academic progress");
        subtitle.setStyle("-fx-text-fill: " + TEXT_SECONDARY + "; -fx-font-size: 16px;");
        titleBox.getChildren().addAll(title, subtitle);

        header.getChildren().addAll(backButton, titleBox);

        // Students card
        VBox studentsCard = new VBox(16);
        studentsCard.getStyleClass().add("card");
        studentsCard.setPadding(new Insets(24));

        Label studentsLabel = new Label("Students");
        studentsLabel.getStyleClass().add("subtitle-label");

        ListView<Student> studentList = new ListView<>(currentClassroom.getStudents());
        studentList.getStyleClass().add("modern-list-view");
        studentList.setCellFactory(listView -> new ListCell<Student>() {
            @Override
            protected void updateItem(Student student, boolean empty) {
                super.updateItem(student, empty);
                if (empty || student == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    VBox container = new VBox(12);

                    HBox studentInfo = new HBox(16);
                    studentInfo.setAlignment(Pos.CENTER_LEFT);

                    VBox info = new VBox(4);
                    Label nameLabel = new Label(student.getName());
                    nameLabel.getStyleClass().add("student-name-label");
                    Label avgLabel = new Label("Overall Average: " + String.format("%.2f", student.getAverage()));
                    avgLabel.setStyle("-fx-text-fill: " + SECONDARY_COLOR + "; -fx-font-weight: bold; -fx-font-size: 14px;");
                            Label summaryLabel = new Label(student.getGradesSummary());
                    summaryLabel.getStyleClass().add("student-summary-label");
                    info.getChildren().addAll(nameLabel, avgLabel, summaryLabel);

                    Region spacer = new Region();
                    HBox.setHgrow(spacer, Priority.ALWAYS);

                    HBox buttonBox = new HBox(8);
                    Button viewGradesButton = new Button("View Grades");
                    viewGradesButton.getStyleClass().add("accent-button");
                    viewGradesButton.setOnAction(e -> showGradeDetailView(student));

                    Button addGradeButton = new Button("Add Grade");
                    addGradeButton.getStyleClass().add("secondary-button");
                    addGradeButton.setOnAction(e -> addGradeDialog(student));

                    buttonBox.getChildren().addAll(viewGradesButton, addGradeButton);
                    studentInfo.getChildren().addAll(info, spacer, buttonBox);

                    container.getChildren().add(studentInfo);
                    setGraphic(container);
                }
            }
        });
        studentList.setPrefHeight(300);

        HBox studentButtonBox = new HBox(12);
        studentButtonBox.setAlignment(Pos.CENTER_LEFT);
        Button addStudentButton = new Button("Add Student");
        addStudentButton.getStyleClass().add("primary-button");
        Button removeStudentButton = new Button("Remove Student");
        removeStudentButton.getStyleClass().add("danger-button");

        addStudentButton.setOnAction(e -> addStudent());
        removeStudentButton.setOnAction(e -> removeStudent(studentList));

        studentButtonBox.getChildren().addAll(addStudentButton, removeStudentButton);
        studentsCard.getChildren().addAll(studentsLabel, studentList, studentButtonBox);

        // Statistics card
        VBox statsCard = new VBox(16);
        statsCard.getStyleClass().add("card");
        statsCard.setPadding(new Insets(24));

        Label statsLabel = new Label("Class Statistics");
        statsLabel.getStyleClass().add("subtitle-label");

        VBox statsContent = new VBox(8);
        Label totalStudentsLabel = new Label("Total Students: " + currentClassroom.getStudentCount());
        totalStudentsLabel.getStyleClass().add("stats-label");
        Label classAvgLabel = new Label("Class Average: " + String.format("%.2f", currentClassroom.getClassAverage()));
        classAvgLabel.getStyleClass().add("stats-label");

        statsContent.getChildren().addAll(totalStudentsLabel, classAvgLabel);
        statsCard.getChildren().addAll(statsLabel, statsContent);

        root.getChildren().addAll(header, studentsCard, statsCard);

        Scene scene = new Scene(root, 900, 700);
        scene.getStylesheets().add("data:text/css;base64," +
                java.util.Base64.getEncoder().encodeToString(getAppStyles().getBytes()));
        primaryStage.setScene(scene);
    }

    private void showGradeDetailView(Student student) {
        VBox root = new VBox(24);
        root.setPadding(new Insets(32));
        root.getStyleClass().add("root");

        // Header
        HBox header = new HBox(16);
        header.setAlignment(Pos.CENTER_LEFT);
        Button backButton = new Button("← Back to Students");
        backButton.getStyleClass().add("back-button");
        backButton.setOnAction(e -> showStudentView());

        VBox titleBox = new VBox(4);
        Label title = new Label(student.getName() + "'s Grades");
        title.getStyleClass().add("title-label");
        Label subtitle = new Label("Detailed grade breakdown by subject");
        subtitle.setStyle("-fx-text-fill: " + TEXT_SECONDARY + "; -fx-font-size: 16px;");
        titleBox.getChildren().addAll(title, subtitle);

        header.getChildren().addAll(backButton, titleBox);

        // Grades grid
        GridPane gradesGrid = new GridPane();
        gradesGrid.setHgap(16);
        gradesGrid.setVgap(16);

        String[] subjects = {"Math", "Physics", "History", "Biology", "Literature", "Digital Culture"};
        List<List<Integer>> gradesList = List.of(
                student.getMathGrades(),
                student.getPhysicsGrades(),
                student.getHistoryGrades(),
                student.getBiologyGrades(),
                student.getLiteratureGrades(),
                student.getDigitalCultureGrades()
        );
        double[] averages = {
                student.getMathAverage(),
                student.getPhysicsAverage(),
                student.getHistoryAverage(),
                student.getBiologyAverage(),
                student.getLiteratureAverage(),
                student.getDigitalCultureAverage()
        };

        int col = 0;
        for (int i = 0; i < subjects.length; i++) {
            VBox subjectCard = new VBox(12);
            subjectCard.getStyleClass().add("card");
            subjectCard.setPadding(new Insets(20));
            subjectCard.setPrefWidth(200);

            Label subjectLabel = new Label(subjects[i]);
            subjectLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: " + TEXT_PRIMARY + ";");

            Label avgLabel = new Label("Average: " + String.format("%.2f", averages[i]));
            avgLabel.setStyle("-fx-text-fill: " + SECONDARY_COLOR + "; -fx-font-weight: bold;");

            Label gradesLabel = new Label("Grades: " + gradesList.get(i).toString().replaceAll("[\\[\\]]", ""));
            gradesLabel.setStyle("-fx-text-fill: " + TEXT_SECONDARY + ";");

            subjectCard.getChildren().addAll(subjectLabel, avgLabel, gradesLabel);
            gradesGrid.add(subjectCard, col, i / 3);
            col = (col + 1) % 3;
        }

        ScrollPane scrollPane = new ScrollPane(gradesGrid);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");

        root.getChildren().addAll(header, scrollPane);

        Scene scene = new Scene(root, 1000, 700);
        scene.getStylesheets().add("data:text/css;base64," +
                java.util.Base64.getEncoder().encodeToString(getAppStyles().getBytes()));
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

    private void deleteClassroom(ListView<Classroom> classroomList) {
        Classroom selected = classroomList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Classroom");
            alert.setHeaderText("Are you sure you want to delete this classroom?");
            alert.setContentText("This action cannot be undone.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                classrooms.remove(selected);
                updateSummaryTable();
            }
        }
    }

    private void addStudent() {
        if (currentClassroom == null) return;

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

    private void removeStudent(ListView<Student> studentList) {
        Student selected = studentList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Remove Student");
            alert.setHeaderText("Are you sure you want to remove this student?");
            alert.setContentText("This action cannot be undone.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                currentClassroom.getStudents().remove(selected);
                updateSummaryTable();
            }
        }
    }

    private void addGradeDialog(Student student) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Add Grade");
        dialog.setHeaderText("Add grade for " + student.getName());

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ComboBox<String> subjectCombo = new ComboBox<>();
        subjectCombo.getItems().addAll("Math", "Physics", "History", "Biology", "Literature", "Digital Culture");
        subjectCombo.setValue("Math");

        Spinner<Integer> gradeSpinner = new Spinner<>(1, 10, 5);
        gradeSpinner.setEditable(true);

        grid.add(new Label("Subject:"), 0, 0);
        grid.add(subjectCombo, 1, 0);
        grid.add(new Label("Grade:"), 0, 1);
        grid.add(gradeSpinner, 1, 1);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String subject = subjectCombo.getValue();
            int grade = gradeSpinner.getValue();

            switch (subject) {
                case "Math":
                    student.addMathGrade(grade);
                    break;
                case "Physics":
                    student.addPhysicsGrade(grade);
                    break;
                case "History":
                    student.addHistoryGrade(grade);
                    break;
                case "Biology":
                    student.addBiologyGrade(grade);
                    break;
                case "Literature":
                    student.addLiteratureGrade(grade);
                    break;
                case "Digital Culture":
                    student.addDigitalCultureGrade(grade);
                    break;
            }
            updateSummaryTable();
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

    public static void main(String[] args) {
        launch(args);
    }
}