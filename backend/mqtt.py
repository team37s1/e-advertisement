import paho.mqtt.client as mqttClient
import time
import mysql_connector


def on_connect(client, userdata, flags, rc):
    if rc == 0:
        print("Connected to broker")
        global Connected  # Use global variable
        Connected = True  # Signal connection
    else:
        print("Connection failed")


def on_message(client, userdata, message):
    # print("Message received: " + message.payload.decode("utf-8"))
    usersMessage = (message.payload.decode("utf-8")).split(" ")
    id = usersMessage[0]
    level = usersMessage[1]
    print(id, level)
    return mysql_connector.uplevel(id, level)


Connected = False  # global variable for the state of the connection

broker_address = "sheep.rmq.cloudamqp.com"  # Broker address
port = 1883  # Broker port
user = "mxetqdmd:mxetqdmd"  # Connection username
password = "IHESbjw0ACljk2biVi5fa_zv36FUXKLL"  # Connection password

client = mqttClient.Client("Python")  # create new instance
client.username_pw_set(user, password=password)  # set username and password
client.on_connect = on_connect  # attach function to callback
client.on_message = on_message  # attach function to callback

client.connect(broker_address, port=port)  # connect to broker

client.loop_start()  # start the loop

while Connected != True:  # Wait for connection
    time.sleep(0.1)

client.subscribe("bottle_Level/my_bottle")

try:
    while True:
        time.sleep(2)

except KeyboardInterrupt:
    print
    "exiting"
    client.disconnect()
    client.loop_stop()
