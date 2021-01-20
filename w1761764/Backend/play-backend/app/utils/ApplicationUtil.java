/*
 * Name     :   Radhika Ranasinghe
 * UoW ID   :   w1761764
 * "I confirm that I understand what plagiarism / collusion / contract cheating is and have read and understood the
 * section on Assessment Offences in the Essential Information for Students. The work that I have submitted is entirely
 * my own. Any work from other authors is duly referenced and acknowledged."
 */
package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;

/**
 * This is class includes the methods for the Application to use as utilities
 *
 * @author Radhika Ranasinghe
 * @version 1.0
 * @since 2020-12-12
 */

public class ApplicationUtil {
    /**
     * This methods is utilized in every instance of creating a response to Json file
     * @param response from the Object type
     * @param ok boolean to show the state of response
     * @return ObjectNode containing the response and the status
     */
    public static ObjectNode createResponse(Object response, boolean ok) {
        ObjectNode result = Json.newObject();
        result.put("status", ok);
        // Check if the object is in an instance of a string
        if (response instanceof String)
            // then put the response as a String
            result.put("response", (String) response);
        // Else set the response as a JsonNode
        else result.set("response", (JsonNode) response );
        return result;
    }

}