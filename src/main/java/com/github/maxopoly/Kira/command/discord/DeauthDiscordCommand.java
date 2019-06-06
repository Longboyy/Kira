package com.github.maxopoly.Kira.command.discord;

import com.github.maxopoly.Kira.command.model.discord.ArgumentBasedCommand;
import com.github.maxopoly.Kira.command.model.top.InputSupplier;
import com.github.maxopoly.Kira.user.DiscordRoleManager;
import com.github.maxopoly.Kira.user.KiraUser;
import com.github.maxopoly.Kira.user.UserManager;
import com.github.maxopoly.kira.KiraMain;

public class DeauthDiscordCommand extends ArgumentBasedCommand {

	public DeauthDiscordCommand() {
		super("deauth", 1, "unrole");
	}

	@Override
	public String getFunctionality() {
		return "Removes a users auth connection";
	}

	@Override
	public String getRequiredPermission() {
		return "admin";
	}

	@Override
	public String getUsage() {
		return "deauth [user]";
	}

	@Override
	public String handle(InputSupplier sender, String[] args) {
		StringBuilder reply = new StringBuilder();
		UserManager userManager = KiraMain.getInstance().getUserManager();
		DiscordRoleManager authManager = KiraMain.getInstance().getRoleManager();
		KiraUser user = userManager.parseUser(args[0], reply);
		if (user == null) {
			reply.append("User not found, no action was taken\n");
		} else {
			boolean worked = authManager.takeDiscordRole(user);
			reply.append("Unregistered user with given id found in discord, role removal "
					+ (worked ? "successfull" : "unsuccessfull") + "\n");
			if (worked) {
				user.updateIngame(null, null);
				KiraMain.getInstance().getDAO().updateUser(user);
			}
		}
		return reply.toString();
	}
}
