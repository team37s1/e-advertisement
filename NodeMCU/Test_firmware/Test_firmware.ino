#define LEVEL_COUNT 3

int level1 = 5;
int level2 = 4;
int level3 = 0;
int ledpin = 16;

void setup() {
  for(int i = 1; i <= LEVEL_COUNT; i++){
    pinMode("level" + i, INPUT_PULLUP);
  }
  pinMode(ledpin, OUTPUT);
  Serial.begin(9600);
}

void loop() {
  if (digitalRead(level1) == 1){
    if(digitalRead(level2) == 1){
      if(digitalRead(level3) == 1){
        digitalWrite(ledpin, HIGH); 
      }
      else{
      Serial.print("Level 3");
    }
    }
    else{
      Serial.print("Level 2");
    }
  }
  else{
    Serial.print("Level 1");
  }

}
