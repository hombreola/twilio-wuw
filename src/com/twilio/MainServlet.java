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

import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.twiml.Conference;
import com.twilio.twiml.Dial;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import com.twilio.type.PhoneNumber;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

public class MainServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VoiceResponse voiceResponse;
        if(!request.getParameter("From").equals(Constants.TWILIO_NUMBER)) {
            // Someone is calling us, dial the conference room
            Conference conference = new Conference.Builder(Constants.CONFERENCE_NAME)
                    .beep(Conference.Beep.FALSE)
                    .startConferenceOnEnter(true)
                    .endConferenceOnExit(true)
                    .build();
            Dial dial = new Dial.Builder().conference(conference).build();
            voiceResponse = new VoiceResponse.Builder()
                    .dial(dial)
                    .build();

            // Make a loop call to the same Twilio number.
            // This new caller will also be added to the conference
            // and will implement the WuW service
            TwilioRestClient client = new TwilioRestClient.Builder(Constants.ACCOUNT_SID, Constants.AUTH_TOKEN).build();
            PhoneNumber to = new PhoneNumber(Constants.TWILIO_NUMBER);
            PhoneNumber from = new PhoneNumber(Constants.TWILIO_NUMBER);
            URI uri = URI.create(Constants.WUW_CALL_ENDPOINT);
            // We need to send some digit after 10 seconds because of the trial message
            Call call = Call.creator(to, from, uri).setSendDigits("wwwwwwwwwwwwwwwwwwww#").create(client);
            // Save this call sid
            Constants.CALL_SID = call.getSid();
        } else {
            // This is the loop call where we will run the WuW service
            Conference conference = new Conference.Builder(Constants.CONFERENCE_NAME)
                    .beep(Conference.Beep.FALSE)
                    .startConferenceOnEnter(true)
                    .endConferenceOnExit(true)
                    .build();
            Dial dial = new Dial.Builder().conference(conference).build();
            voiceResponse = new VoiceResponse.Builder()
                    .dial(dial)
                    .build();
        }

        response.setContentType("application/xml");
        try {
            response.getWriter().print(voiceResponse.toXml());
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
    }
}