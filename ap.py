import unittest
import mysql_connector
import requests
import json
import sys


class TestFlaskApiUsingRequests(unittest.TestCase):
    def test_hello_world(self):
        response = requests.get(' http://127.0.0.1:5000/')
        self.assertEqual(response.json(), {"Test ": "Test"})

    def test_voda(self):
        response = requests.get(' http://127.0.0.1:5000/api/news/voda')
        self.assertEqual(response.json(), [{
            "Title": "Нема води",
            "description": "Хз де вода",
            "name_of_area": "Шевченківський",
            "name_of_category": "Водопостачання"
        }])

    def test_gaz(self):
        response = requests.get(' http://127.0.0.1:5000/api/news/gaz')
        self.assertEqual(response.json(), [
            {
                "Title": "Де газ",
                "description": "Газ пропав",
                "name_of_area": "Галицький",
                "name_of_category": "Газопостачання"
            },
            {
                "Title": "Вулиця",
                "description": "Перекрита",
                "name_of_area": "Сихівський",
                "name_of_category": "Газопостачання"
            }
        ])

    def test_electro(self):
        response = requests.get(' http://127.0.0.1:5000/api/news/electro')
        self.assertEqual(response.json(), [
            {
                "Title": "Електро",
                "description": "Світло",
                "name_of_area": "Шевченківський",
                "name_of_category": "Електроенергія"
            }
        ])


class TestFlaskApi(unittest.TestCase):
    def setUp(self):
        self.app = mysql_connector.app.test_client()

    def test_hello_world(self):
        response = self.app.get("/")

    # def test_hello_world(self):
    #     response = self.app.get("/api/news/voda")
    # self.assertEqual(json.loads(response.get_news(1).decode(sys.getdefaultencoding())), {
    #     "Title": "Нема води",
    #     "description": "Хз де вода",
    #     "name_of_area": "Шевченківський",
    #     "name_of_category": "Водопостачання"
    # })


if __name__ == "__main__":
    unittest.main()
