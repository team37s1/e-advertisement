from flask import Flask, jsonify, request
from flask_pymongo import PyMongo

app = Flask(__name__)

app.config['MONGO_DBNAME'] = '37_1'
app.config['MONGO_URI'] = 'mongodb://admin:admin@ds135574.mlab.com:35574/37_1'

mongo = PyMongo(app)


@app.route('/')
def default():
    return 'Ви на головній'


@app.route('/api/news', methods=['GET', 'POST'])
def get_all_news():
    news = mongo.db.news
    output = []
    if request.method == 'GET':
        for q in news.find():
            output.append({'title': q['title'],
                           'content': q['content'],
                           'area': q['area'],
                           'category': q['category']})

    elif request.method == 'POST':
        title = request.json['title']
        content = request.json['content']
        area = request.json['area']
        category = request.json['category']

        news_id = news.insert({'title': title,
                               'content': content,
                               'area': area,
                               'category': category, })
        new_news = news.find_one({'_id': news_id})

        output = {'title': new_news['title'],
                  'content': new_news['content'],
                  'area': new_news['area'],
                  'category': new_news['category']}

    return jsonify(output)


def get_category(category):
    category_name = mongo.db.news
    result = []
    for q in category_name.find({'category': category}):
        result.append({'title': q['title'],
                       'content': q['content'],
                       'area': q['area'],
                       'category': q['category']})
    return result


@app.route('/api/news/<category>', methods=['GET'])
def get_news(category):
    if category == "storm":
        result = get_category("Штормове попередження")

    elif category == "gaz":
        result = get_category("Газопостачання")

    elif category == "voda":
        result = get_category("Водопостачання")

    elif category == "street":
        result = get_category("Перекриття вулиць")

    elif category == "electro":
        result = get_category("Електроенергія")

    return jsonify(result)


@app.route('/api/news/mcu', methods=['POST'])
def add_level():
    news = mongo.db.news

    title = request.json['title']
    news_id = news.insert({'title': title})
    new_news = news.find_one({'_id': news_id})
    output = {'title': new_news['title']}
    return jsonify({'result': output})


if __name__ == '__main__':
    app.debug = True

    app.run(host='172.16.85.180', port=8000)
