package org.scanl.plugins.poc.settings;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;
import org.scanl.plugins.poc.model.SmellType;

import javax.swing.*;

public class AppSettingsConfigurable implements Configurable {

    private AppSettingsComponent component;

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "TSDetect: Application Settings";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return component.getPreferredFocusedComponent();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        component = new AppSettingsComponent();
        return component.getPanel();
    }

    @Override
    public boolean isModified() {
        AppSettingsState settings = AppSettingsState.getInstance();
        for (SmellType smell : settings.settings.keySet()) {
            if (settings.settings.get(smell) != component.getValue(smell)) return true;
        }
        return false;
    }

    @Override
    public void apply() {
        AppSettingsState settings = AppSettingsState.getInstance();
        for (SmellType smell : settings.settings.keySet()) {
            settings.settings.put(smell, component.getValue(smell));
        }
    }

    @Override
    public void reset() {
        AppSettingsState settings = AppSettingsState.getInstance();
        for (SmellType smell : settings.settings.keySet()) {
            component.setValue(smell, settings.settings.get(smell));
        }
    }

    @Override
    public void disposeUIResources() {
        component = null;
    }

}
