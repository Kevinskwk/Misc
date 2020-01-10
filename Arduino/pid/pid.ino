// Initialising pins and other variables

// DC Motor
int enA = 8;
int in1 = 7;
int in2 = 6;

// Quadrature Encoder
int encoderPin1 = 2;  //yellow
int encoderPin2 = 3;  //white

volatile int lastEncoded = 0;
volatile long encoderValue = 0;

long lastencoderValue = 0;

int lastMSB = 0;
int lastLSB = 0;

// Variables for controller
float motor_degrees = 0;
float desired_degrees = 0;
float error = 0;

float control_input = 0;

// Serial input
unsigned int integerValue=0;
char incomingByte;

float kp = 0;
float ki = 0;
float kd = 0;

float control_output = 0;
float accumulator = 0;
float prev_error = 0;

float i_control = 0;
float p_control = 0;
float d_control = 0;

void setup() {
  // put your setup code here, to run once:

  Serial.begin(9600);
  
  pinMode(enA, OUTPUT);
  pinMode(in1, OUTPUT);
  pinMode(in2, OUTPUT);

  pinMode(encoderPin1, INPUT_PULLUP); 
  pinMode(encoderPin2, INPUT_PULLUP);

  digitalWrite(encoderPin1, HIGH); //turn pullup resistor on
  digitalWrite(encoderPin2, HIGH); //turn pullup resistor on

  //call updateEncoder() when any high/low changed seen
  //on interrupt 0 (pin 2), or interrupt 1 (pin 3) 
  attachInterrupt(0, updateEncoder, CHANGE); 
  attachInterrupt(1, updateEncoder, CHANGE);

  //Controller tuning
  kp = 5;
  ki = 0.1;
  kd = 0.06;
  
  // Wait 2 seconds before starting loop()
  delay(2000);
}
    
// line trace on right edge forever
void loop() {
  // put your main code here, to run repeatedly:
  
  //when user hits ENTER
  if (Serial.available() > 0) {  
    integerValue = 0;        
    while(1) {           
      incomingByte = Serial.read();
      if (incomingByte == '\n') break;   
      if (incomingByte == -1) continue;  
      integerValue *= 10;  
      integerValue = ((incomingByte - 48) + integerValue);
      desired_degrees = integerValue;
    }
  }
  getError();
  
  Serial.print("Desired Position: ");
  Serial.print(desired_degrees);
  Serial.print(", Current Position: ");
  Serial.println(motor_degrees);
  
  motorControl();
}

void motorControl(){
  // Write codes here to control motor
  p_control = error * kp;

  accumulator += error;

  //windup protection
  if (accumulator > 1500) {
    accumulator = 1500;
  }
  if (accumulator < -1500) {
    accumulator = -1500;
  }

  i_control = accumulator * ki;

  d_control = kd * (error - prev_error);
  prev_error = error;

  control_output = p_control + i_control + d_control;
  
  if(control_output > 0){
    digitalWrite(in1, HIGH);
    digitalWrite(in2, LOW);
  }
  
   else {
    digitalWrite(in1, LOW);
    digitalWrite(in2, HIGH);
   }
   
  if (abs(control_output) < 128){
    analogWrite(enA, 0);
  } else if (abs(control_output) > 255) {
    analogWrite(enA, 255);
  } else {
    analogWrite(enA, abs(control_output)); 
  }
}

void getError(){
  // function to produce error based on desired angle
  error = desired_degrees - motor_degrees;

  // tolerance
  if (abs(error)<10){
    error = 0;
  }
}

void updateEncoder(){
  int MSB = digitalRead(encoderPin1); //MSB = most significant bit
  int LSB = digitalRead(encoderPin2); //LSB = least significant bit

  int encoded = (MSB << 1) |LSB; //converting the 2 pin value to single number
  int sum  = (lastEncoded << 2) | encoded; //adding it to the previous encoded value

  if(sum == 0b1101 || sum == 0b0100 || sum == 0b0010 || sum == 0b1011) encoderValue ++;
  if(sum == 0b1110 || sum == 0b0111 || sum == 0b0001 || sum == 0b1000) encoderValue --;
  
  motor_degrees = encoderValue/3.3;
  
  lastEncoded = encoded; //store this value for next time
}
