<?php
include('db.php'); // Include database connection

// Handle the attendance submission
if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['students'])) {
    $class_id = $_POST['class_id'];
    $date = $_POST['date'];
    $students = $_POST['students']; // array of student IDs
    $statuses = $_POST['statuses']; // array of statuses (present/absent)

    // Loop through each student and insert attendance data into the database
    for ($i = 0; $i < count($students); $i++) {
        $status = $statuses[$i];
        $student_id = $students[$i];

        $sql = "INSERT INTO attendance (student_id, date, status) VALUES ('$student_id', '$date', '$status')";
        if ($conn->query($sql) === TRUE) {
            // Attendance recorded successfully
        } else {
            echo "Error: " . $sql . "<br>" . $conn->error;
        }
    }

    echo "<script>alert('Attendance recorded successfully for class section $class_id on $date.');</script>";
}

// Fetch class data based on the section (for example, section 'A')
if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['section'])) {
    $section = $_POST['section'];

    // Query to fetch class details by section
    $classQuery = "SELECT * FROM classes WHERE section = '$section'";
    $classResult = $conn->query($classQuery);

    if ($classResult->num_rows > 0) {
        $classData = $classResult->fetch_assoc();
        $class_id = $classData['class_id'];
        $total_students = $classData['total_students'];

        // Query to fetch students for the selected class
        $studentsQuery = "SELECT * FROM students WHERE class_id = $class_id";
        $studentsResult = $conn->query($studentsQuery);
    } else {
        echo "No class found with section: $section";
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Take Attendance</title>
    <style>
        /* General Reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 20px;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        .attendance-entry {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
        }

        .attendance-entry label {
            font-size: 16px;
        }

        .attendance-entry select {
            padding: 5px 10px;
            font-size: 14px;
            margin-left: 10px;
        }

        .submit-btn {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
            margin-top: 20px;
        }

        .submit-btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Take Attendance for Class Section</h2>

        <!-- Class Section Search Form -->
        <form method="POST" action="take_attendance.php">
            <label for="section">Class Section: </label>
            <input type="text" id="section" name="section" required>
            <button type="submit">Search</button>
        </form>

        <?php if (isset($studentsResult) && $studentsResult->num_rows > 0): ?>
            <h3>Students in Class Section: <?php echo $section; ?></h3>
            <form method="POST" action="take_attendance.php">
                <input type="hidden" name="class_id" value="<?php echo $class_id; ?>">
                <input type="hidden" name="date" value="<?php echo date('Y-m-d'); ?>">

                <?php while ($student = $studentsResult->fetch_assoc()): ?>
                    <div class="attendance-entry">
                        <label>
                            <input type="checkbox" name="students[]" value="<?php echo $student['student_id']; ?>">
                            <?php echo $student['name']; ?>
                        </label>
                        <select name="statuses[]">
                            <option value="present">Present</option>
                            <option value="absent">Absent</option>
                        </select>
                    </div>
                <?php endwhile; ?>

                <button type="submit" class="submit-btn">Submit Attendance</button>
            </form>
        <?php endif; ?>
    </div>
</body>
</html>