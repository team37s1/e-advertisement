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
        row_headers=[x[0] for x in cursor.description] #this will extract row headers
        rv = cursor.fetchall()
        json_data=[]
        for result in rv:
             json_data.append(dict(zip(row_headers, result)))

        return json.dumps(json_data)

    elif request.method == 'POST':
        name = request.json['title']
        surname = request.json['content']
        login = request.json['area']
        password = request.json['category']
        cursor = conn.cursor()
        cursor.execute("INSERT INTO news (title, description, area_id, Category_id)VALUES(%s,%s,%s, %s)",
                       (name, surname, login, password))
        conn.commit()

        return "Новину створено"

@app.route('/api/news/<category>', methods=['GET'])
def get_news(category):
    if category == "voda":
        result = get_category(1)

    elif category == "gaz":
        result = get_category(2)

    elif category == "electro":
        result = get_category(3)

    elif category == "storm":
        result = get_category(4)

    elif category == "street":
        result = get_category(5)

    return result


def get_category(category_id):
    cursor = conn.cursor()
    script = """select news.Title, news.description, category.name_of_category, area.name_of_area  from news 
                left join area on news.area_id = area.id 
                inner join category 
                on news.Category_id=""" + str(category_id) +"  GROUP BY news.Category_id";
    cursor.execute(script)
    row_headers = [x[0] for x in cursor.description]  # this will extract row headers
    rv = cursor.fetchall()
    json_data = []
    for result in rv:
        json_data.append(dict(zip(row_headers, result)))
    return json.dumps(json_data)


if __name__ == "__main__":
    app.run(debug=True)

