from  flask import Flask, jsonify, request
from  flask_pymongo import PyMongo

app = Flask(__name__)

app.config['MONGO_DBNAME'] = '37_1'
app.config['MONGO_URI'] = 'mongodb://admin:admin@ds135574.mlab.com:35574/37_1'


mongo = PyMongo(app)



@app.route('/')
def default():
    return 'Ви на головній'


@app.route('/api/news', methods=['GET'])
def get_all_news():
    news = mongo.db.news
    output = []

    for q in news.find():
        output.append({'title' : q['title'],
                       'content' : q['content'],
                       'area': q['area'],
                       'category': q['category']})

    return jsonify( output)


@app.route('/api/news/gaz', methods=['GET'])
def get_gaz():
    gaz = mongo.db.news
    result = []
    for q in gaz.find({'category' : 'Газопостачання'}):
        result.append({'title': q['title'],
                       'content': q['content'],
                       'area': q['area'],
                       'category': q['category']})

    return jsonify(result)


@app.route('/api/news/voda', methods=['GET'])
def get_voda():
    voda = mongo.db.news
    result = []
    for q in voda.find({'category' : 'Водопостачання'}):
        result.append({'title': q['title'],
                       'content': q['content'],
                       'area': q['area'],
                       'category': q['category']})

    return jsonify(result)

@app.route('/api/news/electro', methods=['GET'])
def get_electro():
    electro = mongo.db.news
    result = []
    for q in electro.find({'category' : 'Електроенергія'}):
        result.append({'title': q['title'],
                       'content': q['content'],
                       'area': q['area'],
                       'category': q['category']})

    return jsonify(result)

@app.route('/api/news/street', methods=['GET'])
def get_street():
    street = mongo.db.news
    result = []
    for q in street.find({'category' : 'Перекриття вулиць'}):
        result.append({'title': q['title'],
                       'content': q['content'],
                       'area': q['area'],
                       'category': q['category']})

    return jsonify(result)


@app.route('/api/news/storm', methods=['GET'])
def get_storm():
    storm = mongo.db.news
    result = []
    for q in storm.find({'category' : 'Штормові попередження'}):
        result.append({'title': q['title'],
                       'content': q['content'],
                       'area': q['area'],
                       'category': q['category']})

    return jsonify(result)

@app.route('/api/news/test', methods=['GET'])
def test():
    storm = mongo.db.news
    result = []
    for q in storm.find({'category' : 'Штормові попередження'}):
        result.append({'title': q['title'],
                       'content': q['content'],
                       'area': q['area'],
                       'category': q['category']})

    return jsonify(result)

@app.route('/api/news', methods=['POST'])
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
    return jsonify({'result': output})


if __name__ == '__main__':
    app.debug = True

    app.run(host='192.168.0.101', port=8000)