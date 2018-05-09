from flask import Flask, jsonify, request
import mysql.connector

conn = mysql.connector.connect(user="root",
                               password="0000",
                               host="localhost",
                               port="3307",
                               database="37_1")
app = Flask(__name__)


@app.route("/")
def index():
    return "Ви на головній"


@app.route("/api/user/signUp", methods=["POST"])
def signUp():
    name = request.json['name']
    surname = request.json['surname']
    login = request.json['login']
    password = request.json['password']
    cursor = conn.cursor()
    cursor.execute("INSERT INTO users ( name , surname, login , password)VALUES(%s,%s,%s, %s)",
                   (name, surname, login, password))
    conn.commit()
    return "Користувача створено"


@app.route("/api/user/checkUser", methods=["POST"])
def check():
    login = request.json['login']
    password = request.json['password']

    cursor = conn.cursor()
    cursor.execute("SELECT NAME FROM users WHERE login=  '" + login + "' and  password ='" + password + "'")
    user = cursor.fetchone()

    if len(user) is 1:
        return user
    else:
        return "failed"


def get_json(script):
    cursor = conn.cursor()

    cursor.execute(script)
    row_headers = [x[0] for x in cursor.description]
    rv = cursor.fetchall()
    json_data = []
    for result in rv:
        json_data.append(dict(zip(row_headers, result)))
    return jsonify(json_data)


@app.route('/api/news', methods=['GET', 'POST'])
def get_all_news():
    if request.method == 'GET':
        script = "select news.title, news.description, area.name_of_area, category.name_of_category " \
                 "from news left join area on news.area_id = area.id " \
                 "join category on news.Category_id=category.id"
        result = get_json(script)

        return result

    elif request.method == 'POST':
        title = request.json['title']
        description = request.json['description']
        area_id = request.json['area_id']
        category_id = request.json['category_id']
        cursor = conn.cursor()
        cursor.execute("INSERT INTO news (title, description, area_id, Category_id)VALUES(%s,%s,%s, %s)",
                       (title, description, area_id, category_id))
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
    script = """select news.Title, news.description, category.name_of_category, area.name_of_area  from news 
                left join area on news.area_id = area.id 
                inner join category 
                on news.Category_id= category.id where news.Category_id =""" + str(category_id)

    result = get_json(script)
    return result


@app.route("/api/mcu/add_device", methods=["POST"])
def add_device():
    user = request.json['user_id']
    level = request.json['level']
    cursor = conn.cursor()
    cursor.execute("INSERT INTO users (Users_id , level)VALUES(%s, %s)",
                   (user, level))
    conn.commit()
    return "Девайс додано до користувача"


@app.route("/api/mcu/check_level/<id>", methods=["GET"])
def checklevel(id):
    script = """select level  from smart_bottle
				where Users_id = """ + id

    cursor = conn.cursor()
    cursor.execute(script)
    row_headers = [x[0] for x in cursor.description]
    rv = cursor.fetchall()
    json_data = []
    for result in rv:
        json_data.append(dict(zip(row_headers, result)))
    res = {"levels": json_data}
    return jsonify(res)


@app.route("/api/mcu/update_level&device_id=<id>", methods=["POST"])
def uplevel(id):
    level = request.json['level']

    cursor = conn.cursor()
    get_level = """select smart_bottle.level  from smart_bottle
                left join users on smart_bottle.Users_id = users.id
				where smart_bottle.id = """ + id
    cursor.execute(get_level)
    val = cursor.fetchone()

    if str(val[0]) != str(level):
        cursor1 = conn.cursor()
        cursor1.execute("UPDATE smart_bottle SET level=" + level + " WHERE id=" + id)
        conn.commit()
        return "Дані успішно оновллено"
    else:
        return "Шось не так"


if __name__ == "__main__":
    app.run(host="172.16.85.180", debug=True)
