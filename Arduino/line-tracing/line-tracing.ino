const int irPins[5] = {8,9,10,11,12};
int irDigital[5] = {0,0,0,0,0};
int irSensors = B00000;

//Motor control pins
const int in1 = 4;
const int in2 = 5;
const int in3 = 6;
const int in4 = 7;

int state = 0; //0 for stop, 1 for forward, 2 for slow left, 3 for slow right, 4 for fast left, 5 for fast right

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  //setting up motor pins
  pinMode(in1, OUTPUT);
  pinMode(in2, OUTPUT);
  pinMode(in3, OUTPUT);
  pinMode(in4, OUTPUT);

  //setting up ir sensor pins
  for (int i = 0; i<=4; i++){
    pinMode(irPins[i], INPUT); 
  }

  delay(1000);
  forward();
  delay(10);
  
}

void loop() {
  // put your main code here, to run repeatedly:
  ir_detect();
  react();
  //to prevent overshooting
  delay(10);
  pause();
  delay(15);
}

void ir_detect(){
  //update int irSensors
  for (byte count = 0; count < 5; count++){
    bitWrite(irSensors, count, !digitalRead(irPins[count]));
  }
  //check state
  if (irSensors==B11111 or irSensors==B01110 or irSensors==B11110 or irSensors==B01111) {state = 0;}
  else if (irSensors==B00000 or irSensors==B00100) {state = 1;}
  else if (irSensors==B01000 or irSensors==B01100) {state = 3;}
  else if (irSensors==B00010 or irSensors==B00110) {state = 2;}
  else if (irSensors==B11100 or irSensors==B11000 or irSensors==B10000) {state = 5;}
  else if (irSensors==B00111 or irSensors==B00011 or irSensors==B00001) {state = 4;}
  else {state = 6;}  
}

void react(){
  switch(state){

    case 0:
    pause();
    break;

    case 6:
    pause();
    break;

    case 1:
    forward();
    break;

    case 2:
    slow_left();
    break;

    case 3:
    slow_right();
    break;

    case 4:
    fast_left();
    break;

    case 5:
    fast_right();
    break;
  }
}

//defining functions of different motions
void forward(){
  digitalWrite(in1,HIGH);
  digitalWrite(in2,LOW);
  digitalWrite(in3,HIGH);
  digitalWrite(in4,LOW);
}

void backward(){
  digitalWrite(in1,LOW);
  digitalWrite(in2,HIGH);
  digitalWrite(in3,LOW);
  digitalWrite(in4,HIGH);
}

void slow_left(){
  digitalWrite(in1,LOW);
  digitalWrite(in2,LOW);
  digitalWrite(in3,HIGH);
  digitalWrite(in4,LOW);
}

void slow_right(){
  digitalWrite(in1,HIGH);
  digitalWrite(in2,LOW);
  digitalWrite(in3,LOW);
  digitalWrite(in4,LOW);
}

void fast_left(){
  digitalWrite(in1,LOW);
  digitalWrite(in2,HIGH);
  digitalWrite(in3,HIGH);
  digitalWrite(in4,LOW);
}

void fast_right(){
  digitalWrite(in1,HIGH);
  digitalWrite(in2,LOW);
  digitalWrite(in3,LOW);
  digitalWrite(in4,HIGH);
}

void pause(){
  digitalWrite(in1,LOW);
  digitalWrite(in2,LOW);
  digitalWrite(in3,LOW);
  digitalWrite(in4,LOW);
}
