
#include "pins.h"
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
  fdButton.setOnClick(toggle_fd);
  ilsButton.setOnClick(toggle_ils);
  locButton.setOnClick(toggle_loc);
  ap1Button.setOnClick(toggle_ap_1);
  ap2Button.setOnClick(toggle_ap_2);
  aThrButton.setOnClick(toggle_a_thr);
  expedButton.setOnClick(toggle_exped);
  apprButton.setOnClick(toggle_appr);

  baroRotaryEncoder.setOnClick(baro_set_std);
  baroRotaryEncoder.setOnRotateClockwise(baro_increment);
  baroRotaryEncoder.setOnRotateCounterClockwise(baro_decrement);
  
  spdRotaryEncoder.setOnClick(set_spd_selected);
  spdRotaryEncoder.setOnLongPress(set_spd_managed);
  spdRotaryEncoder.setOnRotateClockwise(increment_spd);
  spdRotaryEncoder.setOnRotateCounterClockwise(decrement_spd);

  hdgRotaryEncoder.setOnClick(set_hdg_selected);
  hdgRotaryEncoder.setOnLongPress(set_hdg_managed);
  hdgRotaryEncoder.setOnRotateClockwise(increment_hdg);
  hdgRotaryEncoder.setOnRotateCounterClockwise(decrement_hdg);

  altRotaryEncoder.setOnClick(set_alt_selected);
  altRotaryEncoder.setOnLongPress(set_alt_managed);
  altRotaryEncoder.setOnRotateClockwise(increment_alt);
  altRotaryEncoder.setOnRotateCounterClockwise(decrement_alt);

  vsRotaryEncoder.setOnClick(level_off);
  vsRotaryEncoder.setOnRotateClockwise(increment_vs);
  vsRotaryEncoder.setOnRotateCounterClockwise(decrement_vs);

  Serial.begin(9600);
}

void loop() {
  fdButton.tick();
  ilsButton.tick();
  locButton.tick();
  ap1Button.tick();
  ap2Button.tick();
  aThrButton.tick();
  expedButton.tick();
  apprButton.tick();

  baroRotaryEncoder.tick();
  spdRotaryEncoder.tick();
  hdgRotaryEncoder.tick();
  altRotaryEncoder.tick();
  vsRotaryEncoder.tick();
}


// ======================================== COMMANDS ========================================

void send_command(const char *command) {
  Serial.println(command);
}

void toggle_fd() {
  send_command(COMMAND_FD_TOGGLE);
}

void toggle_ils() {
  send_command(COMMAND_ILS_TOGGLE);
}

void toggle_loc() {
  send_command(COMMAND_LOC_TOGGLE);
}

void toggle_ap_1() {
  send_command(COMMAND_AP_1_TOGGLE);
}

void toggle_ap_2() {
  send_command(COMMAND_AP_2_TOGGLE);
}

void toggle_a_thr() {
  send_command(COMMAND_A_THR_TOGGLE);
}

void toggle_exped() {
  send_command(COMMAND_EXPED_TOGGLE);
}

void toggle_appr() {
  send_command(COMMAND_APPR_TOGGLE);
}

void baro_set_std() {
  send_command(COMMAND_BARO_SET_STD);
}

void baro_increment(int boost) {
  send_command(COMMAND_BARO_INCREMENT);
}

void baro_decrement(int boost) {
  send_command(COMMAND_BARO_DECREMENT);
}

void set_spd_selected() {
  send_command(COMMAND_SPD_SELECT);
}

void set_spd_managed() {
  send_command(COMMAND_SPD_MANAGED);
}

void increment_spd(int boost) {
  send_command(COMMAND_SPD_INCREMENT);
}

void decrement_spd(int boost) {
  send_command(COMMAND_SPD_DECREMENT);
}

void set_hdg_selected() {
  send_command(COMMAND_HDG_SELECT);
}

void set_hdg_managed() {
  send_command(COMMAND_HDG_MANAGED);
}

void increment_hdg(int boost) {
  send_command(COMMAND_HDG_INCREMENT);
}


void decrement_hdg(int boost) {
  send_command(COMMAND_HDG_DECREMENT);
}

void set_alt_selected() {
  send_command(COMMAND_ALT_SELECT);
}

void set_alt_managed() {
  send_command(COMMAND_ALT_MANAGED);
}

void increment_alt(int boost) {
  send_command(COMMAND_ALT_INCREMENT);
}

void decrement_alt(int boost) {
  send_command(COMMAND_ALT_DECREMENT);
}

void level_off() {
  send_command(COMMAND_LEVEL_OFF);
}

void increment_vs(int boost) {
  send_command(COMMAND_VS_INCREMENT);
}

void decrement_vs(int boost) {
  send_command(COMMAND_VS_DECREMENT);
}
