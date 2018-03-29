function setup() {
  createCanvas(1000, 1000);
  angleMode(DEGREES);
}

function draw() {
  background(0);

  var hr = hour();
  var min = minute();
  var sec = second();

  var ampm = 'AM';
  if (hr / 12 >= 1) {
      ampm = 'PM';
  }

  var displayMin = min;
  var displaySec = sec;

  noStroke();
  fill(255);
  translate(500, 400);
  textFont('Roboto');

  if (min < 10) {
      displayMin = '0' + min;
  }
  if (sec < 10) {
      displaySec = '0' + sec;
  }
  textSize(90);
  textStyle(BOLD);
  fill(0, 80, 0);
  if (hr == 0) {
      text('12', -55, -95);
  } else {
      text(hr % 12, -55, -95);
  }
  fill(0, 145, 0);
  text(displayMin, -55, -15);
  fill(173, 255, 47);
  text(displaySec, -55, 65);
  fill(230, 255, 0);
  textStyle(NORMAL);
  text(ampm, -55, 145);
  // textSize(25);
  // fill(0, 90, 0);
  // text(' hour(s)', -5, -50);
  // fill(0, 128, 0);
  // text(' minute(s)', -5, -10);
  // fill(173, 255, 47);
  // text(' second(s)', -5, 30);



  strokeWeight(12);
  stroke(35);
  noFill();
  ellipse(0, 0, 500, 500);
  ellipse(0, 0, 465, 465);
  ellipse(0, 0, 430, 430);

  rotate(-90);

  var secPos = map(sec, 0, 60, 0, 360);
  var minPos = map(min, 0, 60, 0, 360);
  var hrPos = map(hr % 12, 0, 12, 0, 360);

  stroke(173, 255, 47);
  arc(0, 0, 430, 430, 0, secPos);
  stroke(0, 128, 0);
  arc(0, 0, 465, 465, 0, minPos);
  stroke(0, 90, 0);
  arc(0, 0, 500, 500, 0, hrPos);



}
