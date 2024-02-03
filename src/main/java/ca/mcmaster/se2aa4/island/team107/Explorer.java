package ca.mcmaster.se2aa4.island.team107;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();

    private int flyCount = 0;
    private String direction = "E";
    private String prevDirection = "E";

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        String direction = info.getString("heading");
        Integer batteryLevel = info.getInt("budget");
        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);
    }

    @Override
    public String takeDecision() {
        JSONObject decision = new JSONObject();
        JSONObject params = new JSONObject();
        
        if (flyCount < 50) {
            params.put("direction", "S");
            decision.put("action", "fly");
            if (flyCount % 2 == 0) {
                decision.put("action", "echo");
                decision.put("parameters", params);
            }
            if (!prevDirection.equals(direction)) {
                prevDirection = direction;
                decision.put("action", "heading");
                decision.put("parameters", params);
            }
        } else {
            decision.put("action", "stop"); // we stop the exploration immediately
        }
        logger.info("** Decision: {}",decision.toString());
        flyCount++;
        return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n"+response.toString(2));
        Integer cost = response.getInt("cost");
        logger.info("The cost of the action was {}", cost);
        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getJSONObject("extras");
        logger.info("Additional information received: {}", extraInfo);
        
        if (extraInfo.has("found")) {
            String echoStatus = extraInfo.get("found").toString();

            if (echoStatus.equals("GROUND")) {
                logger.info("GROUND found!");
                direction = "S";
            }
        }
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}
