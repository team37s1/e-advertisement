#define LEVEL_COUNT 3   //кількість рівнів 

//оголошення портів
int level1 = 5; 
int level2 = 4;
int level3 = 0;
int ledpin = 16;

void setup() {
// цикл, який ініціалізує порти на вхід, тобто рівні
  for(int i = 1; i <= LEVEL_COUNT; i++){
    pinMode("level" + i, INPUT_PULLUP);
  }

  pinMode(ledpin, OUTPUT);
  Serial.begin(9600);   //відкриває послідовний порт, для спілкування з компютером
}

void loop() {
  if (digitalRead(level1) == 1){    //умова першого -  мінімального рівня
    if(digitalRead(level2) == 1){   //умова 2 ріня
      if(digitalRead(level3) == 1){ //умова 3 рівня
        digitalWrite(ledpin, HIGH); // якщо максимальний рівень, то загорається дефолтна лампочка на платі
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
