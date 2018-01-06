from  flask import Flask, jsonify, request
from  flask_pymongo import PyMongo

app = Flask(__name__)

app.config['MONGO_DBNAME'] = '37_1'
app.config['MONGO_URI'] = 'mongodb://admin:admin@ds135574.mlab.com:35574/37_1'

mongo = PyMongo(app)

# @app.route('/add')
# def add():
#     news = mongo.db.news
#     news.insert({'title' : 'Voda',
#                  'content' : 'vse pogano',
#                  'area' : 'Водопостаччання',
#                  'category' : 'Галицький'})
#     return 'News is added'

@app.route('/news', methods=['GET'])
def get_all_news():
    news = mongo.db.news
    output = []

    for q in news.find():
        output.append({'title' : q['title'],
                       'content' : q['content'],
                       'area': q['area'],
                       'category': q['category']})

    return jsonify( output)


@app.route('/news', methods=['POST'])
def add_news():
    news = mongo.db.news

    title = request.json['title']
    content = request.json['content']
    area = request.json['area']
    category = request.json['category']

    news_id = news.insert({'title' :     title,
                           'content' : content,
                           'area' : area,
                           'category' : category,})
    new_news = news.find_one({'_id' : news_id})

    output = {'title': new_news['title'],
                   'content': new_news['content'],
                   'area': new_news['area'],
                   'category': new_news['category']}

    return jsonify({ output})
if __name__ == '__main__':
    app.run(debug = True)