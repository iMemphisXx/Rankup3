package sh.okx.rankup.ranks;

import org.bukkit.configuration.ConfigurationSection;
import sh.okx.rankup.RankupPlugin;
import sh.okx.rankup.ranks.requirements.RankRequirements;
import sh.okx.rankup.ranks.requirements.RankRequirementsFactory;

import java.util.List;

public class Rankup extends Rank {
  public static Rankup deserialize(RankupPlugin plugin, ConfigurationSection section) {
    String next = section.getString("next");
    String rank = section.getString("rank");
    String displayName = section.getString("display-name");

    if (next == null || next.isEmpty()) {
      plugin.getLogger().warning("Having a final rank (for example: \"Z: rank: 'Z'\") from 3.4.2 or earlier should no longer be used.\n"
          + "It is safe to just delete the final rank " + section.getName() + "");
      plugin.getLogger().warning("Rankup section '" + section.getName() + "' has a blank 'next' field, will be ignored.");
      return null;
    }

    return new Rankup(section,
        plugin,
        next,
        rank,
        displayName,
        RankRequirementsFactory.getRequirements(plugin, section),
        section.getStringList("commands"));
  }

  protected Rankup(ConfigurationSection section, RankupPlugin plugin, String next, String rank, String displayName,
      RankRequirements requirements,
      List<String> commands) {
    super(section, plugin, next, rank, displayName, requirements, commands);
  }
}
