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

import com.twilio.twiml.Gather;
import com.twilio.twiml.Say;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WuwServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Start listening for the wake up word.
        // Instead of waiting for the final result we will focus on partial results.
        Say say = new Say
                .Builder(Constants.WUW_PROMPT)
                .build();
        Gather gather = new Gather.Builder()
                .input("speech")
                .hints(Constants.WUW)
                .timeout(10)
                .partialResultCallback(Constants.WUW_DETECTED_CALLBACK)
                .say(say)
                .build();
        VoiceResponse voiceResponse = new VoiceResponse.Builder()
                .gather(gather)
                .build();

        response.setContentType("application/xml");
        try {
            System.out.println(voiceResponse.toXml());
            response.getWriter().print(voiceResponse.toXml());
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
    }
}