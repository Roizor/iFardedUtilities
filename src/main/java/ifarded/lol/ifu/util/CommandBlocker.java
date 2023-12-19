package ifarded.lol.ifu.util;

import java.util.ArrayList;
import java.util.List;

import ifarded.lol.ifu.IFUtilities;

public class CommandBlocker {
    public static boolean isBlocked(String incomingCommand, String group) {
        if (group == null)
            return false;
        String section = "groups." + group;
        incomingCommand = incomingCommand.toLowerCase().replaceFirst("/", "");
        String[] command = incomingCommand.split(" ");
        List<String> commandList = new ArrayList<>();
        boolean isListBlocking = IFUtilities.getPlugin().getConfig().getString(section + ".group-mode").equalsIgnoreCase("blacklist");
        if (!IFUtilities.getPlugin().getConfig().getStringList(section + ".included-groups").isEmpty() && !IFUtilities.getPlugin().getConfig().getStringList(section + ".included-groups").contains("none"))
            for (String includedGroup : IFUtilities.getPlugin().getConfig().getStringList(section + ".included-groups"))
                commandList.addAll(IFUtilities.getPlugin().getConfig().getStringList("groups." + includedGroup + ".commands"));
        commandList.addAll(IFUtilities.getPlugin().getConfig().getStringList(section + ".commands"));
        for (int i = 0; i < commandList.size(); i++)
            commandList.set(i, ((String)commandList.get(i)).replace("%space%", " "));
        if (isListBlocking)
            return isCommandInList(commandList, command);
        return !isCommandInList(commandList, command);
    }

    private static boolean isCommandInList(List<String> commandList, String[] command) {
        for (String currentCommandFromList : commandList) {
            int i = 1;
            for (String currentCommandArg : currentCommandFromList.split(" ")) {
                if ((currentCommandFromList.split(" ")).length < i)
                    break;
                if (command.length < i)
                    break;
                if (!currentCommandArg.equalsIgnoreCase(command[i - 1]))
                    break;
                if ((currentCommandFromList.split(" ")).length == i)
                    return true;
                i++;
            }
        }
        return false;
    }
}
