#include <ESP8266WiFi.h>
#include <PubSubClient.h>


char* ssid = "TP-Link_4G";                                          //info about to Wi-Fi && MQTT
char* password =  "0322752762";
char* mqttServer = "sheep.rmq.cloudamqp.com";
int mqttPort = 8883;
char* mqttUser = "mxetqdmd:mxetqdmd";
char* mqttPassword = "IHESbjw0ACljk2biVi5fa_zv36FUXKLL";  

#define LEVEL1 5                                                    //define ports
#define LEVEL2 4
#define LEVEL3 0
#define LEDPIN 16

WiFiClient espClient;
PubSubClient client(espClient);

void setup() {
  pinMode(LEVEL1, INPUT_PULLUP);
  pinMode(LEVEL2, INPUT_PULLUP);
  pinMode(LEVEL3, INPUT_PULLUP);

  pinMode(LEDPIN, OUTPUT);

  Serial.begin(9600);
  WiFi.begin(ssid, password);                                       //  connect to Wi-Fi

  while (WiFi.status() != WL_CONNECTED) {                           //check Wi-Fi connection
    delay(500);
    Serial.println("Connecting to WiFi..");
  }

  Serial.println("Connected to the WiFi network");

  client.setServer(mqttServer, mqttPort);                           // connect to MQTT

  while (!client.connected()) {
    Serial.println("Connecting to MQTT...");

    if (client.connect("ESP32Client", mqttUser, mqttPassword )) {   //

      Serial.println("connected");

    } else {
      Serial.print("failed with state ");
      Serial.print(client.state());
      delay(1000);
    }
  }
}

void loop() {
                                                                      //send data
  if (digitalRead(LEVEL1) == 0) {
    if (digitalRead(LEVEL2) == 0 && digitalRead(LEVEL1) == 0) {
      if (digitalRead(LEVEL3) == 0 && digitalRead(LEVEL1) == 0 && digitalRead(LEVEL2) == 0) {
        digitalWrite(LEDPIN, HIGH);
        Serial.println("Level 3");
        client.publish("bottle_Level/my_bottle", "Level 3");
                                                                      //send data to db that bottle filled with water on 99,9%
      }
      else {
        Serial.println("Level 2");
        client.publish("bottle_Level/my_bottle", "Level 2");
                                                                      //send data to db that bottle filled with water on 66,6%
      }
    }
    else {
      Serial.println("Level 1");
      client.publish("bottle_Level/my_bottle", "Level 1");
                                                                       //send data to db that bottle filled with water on 33,3%
    }

  }
  else {
    Serial.println("Bottle filled less than 33,3%");
    client.publish("bottle_Level/my_bottle", "Bottle filled less than 33,3%");
  }
  client.loop();
  delay(5000);
}

