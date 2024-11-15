# Flask App Readme

## Installation and Setup

1. **Install Flask**:  
   Run the following command to install Flask in your Python environment:
   ```bash
   pip install flask
   ```

2. **Run the Application**:  
   Start the server by running the Python script. The application will attempt to run on `localhost:3323`. If the port is unavailable, another port may be used.

---

## Endpoints

### 1. **Retrieve All Events**
   - **URL**: `http://localhost:3323/events`  
   - **Method**: `GET`  
   - **Description**: Returns all attributes under the `events` key from the database.

---

### 2. **Retrieve Calendar**
   - **URL**: `http://localhost:3323/calendar`  
   - **Method**: `GET`  
   - **Description**: Returns all attributes under the `calendar` key from the database.

---

### 3. **Retrieve All Courses**
   - **URL**: `http://localhost:3323/courses`  
   - **Method**: `GET`  
   - **Description**: Returns all attributes under the `courses` key from the database.

---

### 4. **Retrieve All Students**
   - **URL**: `http://localhost:3323/students`  
   - **Method**: `GET`  
   - **Description**: Returns a list of all students in the database.

---

### 5. **Retrieve a Student by Matricule**
   - **URL**: `http://localhost:3323/student/<matricule>`  
   - **Method**: `GET`  
   - **Description**:  
     - Looks up the student in the `students` list where the `id` matches the given `<matricule>`.  
     - **Response**:  
       - Returns the student details if found.
       - Returns a `404` error if no student matches the given `matricule`.

---

### 6. **Retrieve Grades for a Student by Matricule**
   - **URL**: `http://localhost:3323/grades/<matricule>`  
   - **Method**: `GET`  
   - **Description**:  
     - Looks up the grades in the `grades` list where the `student_id` matches the given `<matricule>`.  
     - **Response**:  
       - Returns the grades for the student if found.
       - Returns a `404` error if no grades are found for the given `matricule`.

---

## Database File
The application reads data from a `db.json` file. Ensure the file exists in the same directory as the script and has the required structure:
```json
{
  "events": [...],
  "calendar": {...},
  "courses": [...],
  "students": [...],
  "grades": [...]
}
```

---

## Error Handling
- If an item is not found for a given `matricule`, the server responds with a `404` error and an appropriate message:
  - `"Student not found"`
  - `"Grades not found for the student"`

---

## Running Notes
- By default, the app runs on port `3323`. If the port is in use, an alternative port is automatically chosen, or you can set one using the `PORT` environment variable.

Feel free to customize the app as needed!
