package me.xmrvizzy.skyblocker.skyblock.tabhud.widget;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.xmrvizzy.skyblocker.skyblock.tabhud.util.Ico;
import me.xmrvizzy.skyblocker.skyblock.tabhud.util.PlayerListMgr;
import me.xmrvizzy.skyblocker.skyblock.tabhud.widget.component.IcoTextComponent;
import me.xmrvizzy.skyblocker.skyblock.tabhud.widget.component.ProgressComponent;
import me.xmrvizzy.skyblocker.skyblock.tabhud.widget.component.TableComponent;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

// this widget shows info about your skills while in the garden

public class GardenSkillsWidget extends Widget {

    private static final MutableText TITLE = Text.literal("Skill Info").formatted(Formatting.YELLOW,
            Formatting.BOLD);

    // match the skill entry
    // group 1: skill name and level
    // group 2: progress to next level (without "%")
    private static final Pattern SKILL_PATTERN = Pattern
            .compile("\\S*: (?<skill>[A-Za-z]* [0-9]*): (?<progress>\\S*)%");
    // same, but with leading space
    private static final Pattern MS_PATTERN = Pattern.compile("\\S*: (?<skill>[A-Za-z]* [0-9]*): (?<progress>\\S*)%");

    public GardenSkillsWidget() {
        super(TITLE, Formatting.YELLOW.getColorValue());

        ProgressComponent pc;
        Matcher m = PlayerListMgr.regexAt(66, SKILL_PATTERN);
        if (m == null) {
            pc = new ProgressComponent();
        } else {

            String strpcnt = m.group("progress");
            String skill = m.group("skill");

            float pcnt = Float.parseFloat(strpcnt);
            pc = new ProgressComponent(Ico.LANTERN, Text.of(skill), pcnt,
                    Formatting.GOLD.getColorValue());
        }

        this.addComponent(pc);

        Text speed = Widget.simpleEntryText(67, "SPD", Formatting.WHITE);
        IcoTextComponent spd = new IcoTextComponent(Ico.SUGAR, speed);
        Text farmfort = Widget.simpleEntryText(68, "FFO", Formatting.GOLD);
        IcoTextComponent ffo = new IcoTextComponent(Ico.HOE, farmfort);

        TableComponent tc = new TableComponent(2, 1, Formatting.YELLOW.getColorValue());
        tc.addToCell(0, 0, spd);
        tc.addToCell(1, 0, ffo);
        this.addComponent(tc);

        ProgressComponent pc2;
        m = PlayerListMgr.regexAt(69, MS_PATTERN);
        if (m == null) {
            pc2 = new ProgressComponent();
        } else {
            String strpcnt = m.group("progress");
            String skill = m.group("skill");

            float pcnt = Float.parseFloat(strpcnt);
            pc2 = new ProgressComponent(Ico.MILESTONE, Text.of(skill), pcnt,
                    Formatting.GREEN.getColorValue());

        }
        this.addComponent(pc2);

        this.pack();
    }

}
