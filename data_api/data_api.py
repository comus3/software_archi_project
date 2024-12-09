from flask import Flask, jsonify, abort
import json
import os

# Charger les données JSON
with open('db.json') as f:
    db = json.load(f)

app = Flask(__name__)

# Configuration du port
PORT = 5000

# Route pour récupérer tous les événements
@app.route('/news', methods=['GET'])
def get_events():
    return jsonify(db.get("news", []))

@app.route('orientations', methods=['GET'])
def get_orientations():
    return jsonify(db.get("orientations", []))
@app.route('syllabus', methods=['GET'])
def get_syllabus():
    return jsonify(db.get("syllabus", []))
@app.route('cart', methods=['GET'])
def get_cart():
    return jsonify(db.get("cart", []))
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
    grades = db.get("student_grades", [])
    student_grades = next((g for g in grades if g["student_id"] == matricule), None)
    if student_grades:
        return jsonify(student_grades)
    else:
        abort(404, description="Grades not found for the student")




@app.route('/orientations', methods=['GET'])
def get_orientations():
    return jsonify(db.get("orientations", []))

@app.route('/syllabus', methods=['GET'])
def get_syllabus():
    return jsonify(db.get("syllabus", []))




# Route pour récupérer le panier
@app.route('/cart', methods=['GET'])
def get_cart():
    return jsonify(db.get("cart", []))

# Route pour ajouter un élément au panier
@app.route('/cart', methods=['POST'])
def add_to_cart():
    from flask import request
    item = request.json
    cart = db.get("cart", [])
    existing_item = next((c for c in cart if c["id"] == item["id"]), None)
    if existing_item:
        existing_item["quantity"] += item["quantity"]
    else:
        cart.append(item)
    db["cart"] = cart
    with open('db.json', 'w') as f:
        json.dump(db, f, indent=4)
    return jsonify(cart)

@app.route('/cart/<int:syllabus_id>', methods=['DELETE'])
def remove_from_cart(syllabus_id):
    cart = db.get("cart", [])
    cart = [item for item in cart if item["id"] != syllabus_id]
    db["cart"] = cart
    with open('db.json', 'w') as f:
        json.dump(db, f, indent=4)
    return jsonify(cart)



# Route pour vider le panier
@app.route('/cart', methods=['DELETE'])
def clear_cart():
    db["cart"] = []
    with open('db.json', 'w') as f:
        json.dump(db, f, indent=4)
    return jsonify([])





if __name__ == '__main__':
    # On tente d'utiliser le port 3323, mais si indisponible, Flask choisit un autre port
    try:
        app.run(host='0.0.0.0', port=PORT)
    except OSError:
        app.run(host='localhost', port=os.getenv("PORT", 0))
