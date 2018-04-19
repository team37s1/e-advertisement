from flask import Flask, jsonify, request
import mysql.connector
import json

conn = mysql.connector.connect(user = "root", password = "0000", host="localhost", port = "3307",  database = "37_1")

app = Flask(__name__)

@app.route("/")
def index():
    pass


@app.route("/api/user//signUp", methods=["POST"])
def signUp():
    name = request.json['name']
    surname = request.json['surname']
    login = request.json['login']
    password = request.json['password']

    cursor = conn.cursor()

    cursor.execute("INSERT INTO users ( name , surname, login , password)VALUES(%s,%s,%s, %s)",
                   (name,   surname, login, password))
    conn.commit()
    return "Користувача створено"


@app.route("/login")
def login():
    pass


@app.route("/api/user/checkUser", methods=["POST"])
def check():
    login = request.json['login']
    password = request.json['password']

    cursor = conn.cursor()
    cursor.execute("SELECT NAME FROM users WHERE login=  '" + login + "' and  password ='" + password +"'")
    user = cursor.fetchone()

    if len(user) is 1:
        return user
    else:
        return "failed"




@app.route('/api/news', methods=['GET', 'POST'])
def get_all_news():
    if request.method == 'GET':
        cursor = conn.cursor()
        script = "select news.title, news.description, area.name_of_area, category.name_of_category " \
                 "from news left join area on news.area_id = area.id " \
                 "join category on news.Category_id=category.id"
        cursor.execute(script)
        news  = cursor.fetchall()


    elif request.method == 'POST':
        pass

    return jsonify(news)


@app.route("/home")
def home():
    pass


if __name__ == "__main__":
    app.run(debug=True)

