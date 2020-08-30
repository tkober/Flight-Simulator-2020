
#include "pins.h"
#include "actions.h"
#include "Button.h"
#include "RotaryEncoder.h"
#include "Commands.h"

Button fdButton     = Button(FD_BUTTON_PIN, 1);
Button ilsButton    = Button(ILS_BUTTON_PIN, 1);
Button locButton    = Button(LOC_BUTTON_PIN, 1);
Button ap1Button    = Button(AP_1_BUTTON_PIN, 1);
Button ap2Button    = Button(AP_2_BUTTON_PIN, 1);
Button aThrButton   = Button(A_THR_BUTTON_PIN, 1);
Button expedButton  = Button(EXPED_BUTTON_PIN, 1);
Button apprButton   = Button(APPR_BUTTON_PIN, 1);

PushableRotaryEncoder baroRotaryEncoder   = PushableRotaryEncoder(BARO_ROTARY_ENCODER_PIN_A, BARO_ROTARY_ENCODER_PIN_B, BARO_ROTARY_BUTTON_PIN, 1);
PushableRotaryEncoder spdRotaryEncoder    = PushableRotaryEncoder(SPD_ROTARY_ENCODER_PIN_A, SPD_ROTARY_ENCODER_PIN_B, SPD_ROTARY_BUTTON_PIN, 1);
PushableRotaryEncoder hdgRotaryEncoder    = PushableRotaryEncoder(HDG_ROTARY_ENCODER_PIN_A, HDG_ROTARY_ENCODER_PIN_B, HDG_ROTARY_BUTTON_PIN, 1);
PushableRotaryEncoder altRotaryEncoder    = PushableRotaryEncoder(ALT_ROTARY_ENCODER_PIN_A, ALT_ROTARY_ENCODER_PIN_B, ALT_ROTARY_BUTTON_PIN, 1);
PushableRotaryEncoder vsRotaryEncoder     = PushableRotaryEncoder(VS_ROTARY_ENCODER_PIN_A, VS_ROTARY_ENCODER_PIN_B, VS_ROTARY_BUTTON_PIN, 1);

void setup() {
}

void loop() {
}
