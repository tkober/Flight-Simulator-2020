#include "Button.h"

#define DEFAULT_LONG_PRESS_CLICK_TICKS    1000
#define DEFAULT_CLICK_DELAY_TICKS         500


Button::Button(int pin, int pullUp) {
  _pin = pin;
  _longPressClickTicks = DEFAULT_LONG_PRESS_CLICK_TICKS;
  _clickDelay = DEFAULT_CLICK_DELAY_TICKS;
  _isPullUp = pullUp;
  pinMode(pin, pullUp == 1 ? INPUT_PULLUP : INPUT);
}


void Button::setOnClick(ButtonEventHandler handler) 
{
  _onClick = handler;
}


void Button::setOnLongPress(ButtonEventHandler handler) 
{
  _onLongPress = handler;
}


void Button::setLongPressClickTicks(unsigned int ticks)
{
  _longPressClickTicks = ticks;
}


void Button::setClickDelayTicks(unsigned int ticks)
{
  _clickDelay = ticks;
}


void Button::tick()
{
  unsigned long now = millis();
  int triggeringState = _isPullUp == 1 ? LOW : HIGH;
  if (digitalRead(_pin) == triggeringState) {
    switch (_state) {
      case Normal:
        _startedTime = now;
        _state = ClickStarted;
        break;
    
      case ClickStarted:
        if (now - _startedTime >= _longPressClickTicks) {
          _state = LongPressed;
          if (_onLongPress != NULL) {
            _onLongPress();
          }
        }
        break;

      case LongPressed:
        break;
     } 
  } else {
    switch (_state) {
      case ClickStarted:
        if (_onClick != NULL && now - _lastClick >= _clickDelay) {
          _lastClick = now;
          _onClick();
        }
      break;
    }
   _state =  Normal;
  }
}
