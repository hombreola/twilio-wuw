# twilio-wuw

Twilio conference room with with a Wake-up Word service.

You will need a voice capable Twilio number to use this service.

This service is continuously listening to the conference conversation and will trigger an event every time a specific word is pronounced. 
In my sample app the event will just play a beep to acknowledge the wake-up word recognition: ABRACADABRA

The idea behind is very simple: there is a main servlet that runs when the Twilio number receives a call.
The main servlet then starts a conference call and calls in with the same Twilio number (one nice feature of Twilio numbers is that you can use the same number for simultaneously receiving and making calls).
When the Twilio number joins the conference call, it uses Twilio's speech recognition API to detect the WuW and throw an event.