package net.moopa.calling.console;

import net.moopa.calling.common.constants.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LeeAutumn on 17/01/2017.
 * blog: leeautumn.net
 *
 * @autuor : LeeAutumn
 */
public class CommandExecutor {
    private static Logger logger = LoggerFactory.getLogger(Constant.COMMAND_EXECUTOR_LOGGERNAME);

    private static Map<String,ConsoleCommand> commandList = new HashMap<String, ConsoleCommand>();

    //add all console commands
    static {
        initCommand(null);
    }

    static void executeCommand(String commandName){
        ConsoleCommand consoleCommand = findCommand(commandName);

        if(commandName == null){
            logger.error("No such command: {} exists.",commandName);
        }else {
            //use network to contact with the servicesrv
            //still use rpc?

        }
    }

    private static void initCommand(ConsoleCommand consoleCommand){
        commandList.put(consoleCommand.getCommandName(),consoleCommand);
    }

    private static ConsoleCommand findCommand(String name){
        return commandList.get(name);
    }
}
