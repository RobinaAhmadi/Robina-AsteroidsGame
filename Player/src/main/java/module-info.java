module Player {
    requires Common;
    provides dk.sdu.mmmi.cbse.common.services.GamePluginService
        with dk.sdu.mmmi.cbse.player.PlayerPlugin;
}
