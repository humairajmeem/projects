function sjfScheduling(attendanceData) {
    attendanceData.sort((a, b) => a.absentDuration - b.absentDuration);
    return attendanceData;
}

// Example data structure for attendance
let attendanceData = [
    { student_id: 1, name: "John", absentDuration: 2 },
    { student_id: 2, name: "Jane", absentDuration: 1 },
    { student_id: 3, name: "Mike", absentDuration: 3 }
];

let sortedData = sjfScheduling(attendanceData);
console.log(sortedData);
