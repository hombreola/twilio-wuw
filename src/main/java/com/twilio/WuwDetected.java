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

import com.twilio.http.HttpMethod;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WuwDetected extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Initialize Twilio
        Twilio.init(Constants.ACCOUNT_SID, Constants.AUTH_TOKEN);

        String result = request.getParameter("UnstableSpeechResult").trim().toLowerCase();
        //System.out.println("UnstableSpeechResult = " + result);
        if (result.equals(Constants.WUW)) {
            // WuW detected, we have to redirect the call to the URL playing the beep
            Call call = Call.updater(Constants.CALL_SID)
                    .setUrl(Constants.BEEP_PLAYER_URL)
                    .setMethod(HttpMethod.POST)
                    .update();
        }

        VoiceResponse voiceResponse = new VoiceResponse.Builder().build();

        response.setContentType("application/xml");
        try {
            System.out.println(voiceResponse.toXml());
            response.getWriter().print(voiceResponse.toXml());
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
    }
}