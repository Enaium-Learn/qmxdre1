package cn.enaium.learn.qmxdre.module;

import cn.enaium.learn.qmxdre.QMXDRE;
import cn.enaium.learn.qmxdre.event.Events;
import cn.enaium.learn.qmxdre.event.Events.KeyboardEvent;
import cn.enaium.learn.qmxdre.module.modules.movement.Sprint;
import cn.enaium.learn.qmxdre.module.modules.other.Test;
import cn.enaium.learn.qmxdre.module.modules.render.ClickGUI;
import cn.enaium.learn.qmxdre.module.modules.render.FullBright;
import cn.enaium.learn.qmxdre.module.modules.render.HUD;
import cn.enaium.learn.qmxdre.module.modules.render.Hitbox;
import com.google.common.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author Enaium
 */
public class ModuleManager {
    private final ArrayList<Module> modules;

    public ModuleManager() {
        modules = new ArrayList<>();
        QMXDRE.INSTANCE.event.register(this);
    }

    public void load() {
        modules.add(new Sprint());
        modules.add(new FullBright());
        modules.add(new Hitbox());
        modules.add(new ClickGUI());
        modules.add(new Test());

        modules.add(new HUD());
    }

    @Subscribe
    public void onKey(KeyboardEvent event) {
        for (Module module : modules) {
            if (module.getKey() == event.getKey()) {
                module.enable();
            }
        }
    }

    public Module getModuleByName(String name) {
        for (Module module : modules) {
            if (module.getName().equalsIgnoreCase(name)) {
                return module;
            }
        }
        return null;
    }

    public ArrayList<Module> getModuleByType(Type type) {
        return getModules().stream().filter(it -> it.getType().equals(type)).collect(Collectors.toCollection(ArrayList::new));
    }

    public Setting getSettingByName(Module module, String name) {
        for (Module module1 : modules) {
            if (!module1.equals(module)) continue;
            for (Setting setting : module1.getSetting()) {
                if (setting.getName().equalsIgnoreCase(name)) {
                    return setting;
                }
            }
        }
        return null;
    }

    public ArrayList<Module> getModules() {
        return modules;
    }
}
