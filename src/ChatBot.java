import java.io.File;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.utils.IOUtils;
import resources.bots.pramoj.ResponsePostProcessor;

public class ChatBot {

    private static final boolean TRACE_MODE = false;
    static String botName = "pramoj";

    public static void main(String[] args) {

        try {

            String resourcesPath = getResourcesPath();
            System.out.println(resourcesPath);
            MagicBooleans.trace_mode = TRACE_MODE;
            Bot bot = new Bot(botName, resourcesPath);
            bot.writeAIMLFiles();
            Chat chatSession = new Chat(bot);
            bot.brain.nodeStats();
            String textLine = "";

            while (true) {
                System.out.print("Human : ");
                textLine = IOUtils.readInputTextLine();
                if (null == textLine || textLine.length() < 1) {
                    textLine = MagicStrings.null_input;
                }
                if ("q".equals(textLine)) {
                    System.exit(0);
                } else if ("wq".equals(textLine)) {
                    bot.writeQuit();
                    System.exit(0);
                } else {
                    String request = textLine;
                    if (MagicBooleans.trace_mode) {
                        System.out.println("STATE=" + request + ":THAT=" +
                                ((History) chatSession.thatHistory.get(0)).get(0) +
                                ":TOPIC=" + chatSession.predicates.get("topic"));
                    }
                    String response = chatSession.multisentenceRespond(request);
                    while (response.contains("&lt;")) {
                        response = response.replace("&lt;", "<");
                    }
                    while (response.contains("&gt;")) {
                        response = response.replace("&gt;", ">");
                    }

                    response = ResponsePostProcessor.appendSparqlResult(response);

                    System.out.println("Robot : " + response);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String getResourcesPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        System.out.println(path);
        String resourcesPath = path + File.separator + "src" + File.separator + "resources";
        return resourcesPath;
    }

}
