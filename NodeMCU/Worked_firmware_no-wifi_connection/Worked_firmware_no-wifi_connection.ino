 // add ports
#define LEVEL1 5 
#define LEVEL2 4
#define LEVEL3 0
#define LEDPIN 16

void setup() {
// initialize levels
  pinMode(LEVEL1, INPUT_PULLUP);
  pinMode(LEVEL2, INPUT_PULLUP);
  pinMode(LEVEL3, INPUT_PULLUP);

  pinMode(LEDPIN, OUTPUT);
  Serial.begin(9600);   // opens the serial port for communicating with the computer
}

void loop(){
  if(digitalRead(LEVEL1) == 0){
      if(digitalRead(LEVEL2) == 0 && digitalRead(LEVEL1) == 0){       
        if(digitalRead(LEVEL3) == 0 && digitalRead(LEVEL1) == 0 && digitalRead(LEVEL2) == 0){
           digitalWrite(LEDPIN, HIGH);
           Serial.println("Level 3"); 
           //send data to db that bottle filled with water on 99,9%
           }        
        else{
          Serial.println("Level 2");
          //send data to db that bottle filled with water on 66,6%
        }
        } 
      else{
        Serial.println("Level 1");
        //send data to db that bottle filled with water on 33,3%
      }
      
   }
  else{
    Serial.println("Bottle filled less than 33,3%");
  }
  delay(200);
}

