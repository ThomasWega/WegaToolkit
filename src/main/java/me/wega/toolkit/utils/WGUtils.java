package me.wega.toolkit.utils;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class WGUtils {

    public static Optional<ProtectedRegion> getRegionAt(@NotNull Location loc) {
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(loc));

        return set.getRegions().stream()
                .max(Comparator.comparingInt(ProtectedRegion::getPriority))
                .stream().findFirst();
    }

    public static Optional<ProtectedRegion> getRegionAt(@NotNull LivingEntity entity) {
        return getRegionAt(entity.getLocation());
    }

    public static Map<String, ProtectedRegion> getRegions() {
        return WorldGuard.getInstance().getPlatform().getRegionContainer().getLoaded().stream()
                .flatMap(regionManager -> regionManager.getRegions().entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Set<String> getRegionNames() {
        return getRegions().keySet();
    }

    public static Map<String, ProtectedRegion> getRegions(@NotNull World world) {
        return WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(world)).getRegions();
    }

    public static Set<String> getRegionNames(@NotNull World world) {
        return getRegions(world).keySet();
    }

    public static Optional<ProtectedRegion> getRegion(@NotNull World world, @NotNull String id) {
        return Optional.ofNullable(WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(world)).getRegion(id));
    }

    @SuppressWarnings("DataFlowIssue")
    public static Optional<ProtectedRegion> getRegion(@NotNull String id) {
        return WorldGuard.getInstance().getPlatform().getRegionContainer().getLoaded().stream()
                .filter(regionManager -> regionManager.hasRegion(id))
                .map(regionManager -> regionManager.getRegion(id))
                .findAny();
    }

    public static @NotNull Location getRegionCenter(@NotNull World world, @NotNull ProtectedRegion region) {
        // Get top and bottom locations
        Location top = new Location(world, region.getMaximumPoint().getX(), region.getMaximumPoint().getY(), region.getMaximumPoint().getZ());
        Location bottom = new Location(world, region.getMinimumPoint().getX(), region.getMinimumPoint().getY(), region.getMinimumPoint().getZ());

        // Calculate and return center location
        return new Location(world, (bottom.getX() + top.getX()) / 2, (bottom.getY() + top.getY()) / 2, (bottom.getZ() + top.getZ()) / 2);
    }

    public static boolean isValid(@NotNull ProtectedRegion region) {
        Optional<ProtectedRegion> optReg = getRegion(region.getId());
        return optReg.isPresent() && optReg.get().getMaximumPoint().equals(region.getMaximumPoint()) && optReg.get().getMinimumPoint().equals(region.getMinimumPoint());
    }
}
