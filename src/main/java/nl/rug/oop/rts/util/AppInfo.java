package nl.rug.oop.rts.util;

import lombok.Getter;

import java.awt.*;

/**
 * Holds the constants of the application for easy changes.
 * */
public class AppInfo {
    @Getter private static final String APPNAME = "RTS";
    @Getter private static int WINDOWX = 1000;
    @Getter private static int WINDOWY = 600;
    @Getter private static final Color TOOLBARCOLOR = Color.decode("#32302f");
    @Getter private static final Color SIDEPABELCOLOR = Color.decode("#32302f");
    @Getter private static final Color TOOLBARTEXTCOLOR = Color.decode("#cfbe87");
    @Getter private static final int NODESIZE = 70;
    @Getter private static final int ARMYSIZE = 30;
    @Getter private static final int NODESPAWNX = WINDOWX / 2;
    @Getter private static final int NODESPAWNY = WINDOWY / 2;
}
