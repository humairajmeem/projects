<?php
function calculate_percentage($student_id) {
    global $conn;
    $total_classes = 10; // Example total classes
    $result = $conn->query("SELECT COUNT(*) AS present_count FROM attendance WHERE student_id = $student_id AND status = 'present'");
    $row = $result->fetch_assoc();
    $present_count = $row['present_count'];
    
    $percentage = ($present_count / $total_classes) * 100;
    return $percentage;
}
?>
