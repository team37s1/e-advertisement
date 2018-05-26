import pymongo
from pymongo import MongoClient
import psycopg2


def create_leveldatas(results):

#    Take the query outcome and convert to a list and return it.
#    It also defines headers and return it.

    records = []
    headers = ['date','leveldata','level']
    for record in results:
        for leveldata in record['leveldatas']:
            tmp = []
            tmp.append(str(leveldata['date']).split("(")[0].replace('T', ' ').replace('Z', ' '))
            tmp.append(leveldata['grade'])
            tmp.append(str(leveldata['level']))
            records.append(tmp)
    return records, headers
    

    
def create_table(records, headers, file_path):
#      "Take a list of records and headers and generate csv"
    f = open(file_path, 'w', encoding='utf-8')
    row_len = len(headers)
    f.write(format_list(headers, row_len, ',', '"'))
    for record in records:
        f.write(format_list(record, row_len, ',', '"'))
    f.close()
    print('CSV file successfully created: {}'.format(file_path))

def format_list(list, length, delimiter, quote):
    counter = 1
    string = ''
    for record in list:
        if counter == length:
            string += quote + record + quote + '\n'
        else:
            string += quote + record + quote + delimiter
        counter += 1
    return string
    
def pg_load_table(file_path, table_name):
    try:
        conn = psycopg2.connect("dbname='mydatahack' user='mydatahack' host='localhost' password='Password1'")
        print("Connecting to Database")
        cur = conn.cursor()
        f = open(file_path, "r", encoding='utf-8')
        cur.execute("Truncate {} Cascade;".format(table_name))
        print("Truncated {}".format(table_name))
        cur.copy_expert("copy {} from STDIN CSV HEADER".format(table_name), f)
        cur.execute("commit;")
        print("Loaded data into {}".format(table_name))
        conn.close()
        print("DB connection closed.")
    except Exception as e:
        print("Error: {}".format(str(e)))
        

def main():
    # (1) Connect to MongoDB instance
    client = MongoClient('mongodb://admin:admin@ds135574.mlab.com:35574/37_1')
    # (2) Choose database and create the db object
    db = client.test
    # (3) Choose the collection and create Collection Object
    smart_bottle = db.smart_bottle
    print('Total Record for the collection: ' + str(smart_bottle.count()))
    # (4) Retrive all data again for grades
    results = smart_bottle.find()
    # (4-1) Transform data for grades
    leveldata_tup = create_leveldatas(results)
    leveldata_records = leveldata_tup[0]
    leveldata_headers = leveldata_tup[1]
    create_table(leveldata_records, leveldata_headers, './leveldata.csv')
    # (5) Load data into Postgres
    pg_load_table('./leveldata.csv', 'mongodb.smart_bottle')

if __name__ == "__main__":
    main()