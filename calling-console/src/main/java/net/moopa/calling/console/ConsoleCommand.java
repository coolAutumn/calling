package net.moopa.calling.console;

/**
 * Created by LeeAutumn on 17/01/2017.
 * blog: leeautumn.net
 *
 * @autuor : LeeAutumn
 */
public interface ConsoleCommand {

    String getCommandName();

    String getCommandDesc();

    void execute();

    String getHelp();
}
