from flask import Flask, jsonify, abort
import json
import os

# Charger les données JSON
with open('db.json') as f:
    db = json.load(f)

app = Flask(__name__)

# Configuration du port
PORT = 3323

# Route pour récupérer tous les événements
@app.route('/events', methods=['GET'])
def get_events():
    return jsonify(db.get("events", []))

# Route pour récupérer le calendrier
@app.route('/calendar', methods=['GET'])
def get_calendar():
    return jsonify(db.get("calendar", {}))

# Route pour récupérer tous les cours
@app.route('/courses', methods=['GET'])
def get_courses():
    return jsonify(db.get("courses", []))

# Route pour récupérer les informations d'un étudiant par matricule
@app.route('/student/<matricule>', methods=['GET'])
def get_student(matricule):
    students = db.get("students", [])
    student = next((s for s in students if s["id"] == matricule), None)
    if student:
        return jsonify(student)
    else:
        abort(404, description="Student not found")

@app.route('/students', methods=['GET'])
def get_students():
    return jsonify(db.get("students", []))



# Route pour récupérer les notes d'un étudiant par matricule
@app.route('/grades/<matricule>', methods=['GET'])
def get_grades(matricule):
    grades = db.get("grades", [])
    student_grades = next((g for g in grades if g["student_id"] == matricule), None)
    if student_grades:
        return jsonify(student_grades)
    else:
        abort(404, description="Grades not found for the student")

if __name__ == '__main__':
    # On tente d'utiliser le port 3323, mais si indisponible, Flask choisit un autre port
    try:
        app.run(host='localhost', port=PORT)
    except OSError:
        app.run(host='localhost', port=os.getenv("PORT", 0))
