package io.github.rjhaytree.simpleclans.utility;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import java.util.List;

public class TextUtils {
    public static void sendPagination(Text title, List<Text> contents, Player player) {
        PaginationList.builder()
                .title(Text.of(TextColors.LIGHT_PURPLE, TextStyles.BOLD, title))
                .contents(contents)
                .padding(Text.of(TextColors.DARK_GRAY, TextStyles.STRIKETHROUGH, "-"))
                .sendTo(player);
    }
}
