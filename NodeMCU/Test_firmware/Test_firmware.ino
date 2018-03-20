int ledpin = 16; // D1(gpio5)
int button = 4; //D2(gpio4)
int buttonState=0;
void setup() {
 pinMode(ledpin, OUTPUT);
 pinMode(button, INPUT);
}
void loop() {

 buttonState=digitalRead(button); 
 if (buttonState == 0)
 {
 digitalWrite(ledpin, LOW); 
 delay(200);
  Serial.print("Level MIN");
 }
 if (buttonState==1)
 {
 digitalWrite(ledpin, HIGH); 
  Serial.print("Level MAX");
 delay(200);
 }
}
