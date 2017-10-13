/*
 * Copyright (C) 2017 Lucas Gomez Jimenez
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.twilio;

public class Constants {
    final static String ACCOUNT_SID = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"; // Found on your Twilio Dashboard
    final static String AUTH_TOKEN = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"; // Found on your Twilio Dashboard
    final static String TWILIO_NUMBER = "XXXXXXXXXXXX";
    final static String WUW_CALL_ENDPOINT = "https://foo.bar/twilio/wuw";
    final static String CONFERENCE_NAME = "Conference";

    final static String WUW = "abracadabra"; // the magic word
    final static String WUW_PROMPT = "Welcome to the wake up word service. You will hear a beep every time you say the magic word";
    final static String WUW_DETECTED_CALLBACK = "/twilio/wuwdetected";

    final static String BEEP_PLAYER_URL = "https://foo.bar/twilio/beep";
    final static String BEEP_SOUND_URL = "https://www.soundjay.com/button/sounds/beep-01a.wav";
    
    static String CALL_SID;
}