package me.wega.toolkit.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Location;

@UtilityClass
public class LocationUtils {

    public static Location lookAt(Location from, Location to) {
        Location fromClone = from.clone();
        double dx = to.getX() - fromClone.getX();
        double dy = to.getY() - fromClone.getY();
        double dz = to.getZ() - fromClone.getZ();

        double yaw = Math.atan2(dz, dx) * 180 / Math.PI;

        yaw = yaw - 90;
        if (yaw < 0) {
            yaw = yaw + 360;
        }

        double distance = Math.sqrt(dx * dx + dz * dz);
        double pitch = Math.atan2(-dy, distance) * 180 / Math.PI;

        fromClone.setPitch((float) pitch);
        fromClone.setYaw((float) yaw);

        return fromClone;
    }
}
