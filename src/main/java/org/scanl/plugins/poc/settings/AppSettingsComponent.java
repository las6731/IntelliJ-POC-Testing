package org.scanl.plugins.poc.settings;

import com.intellij.ui.components.JBCheckBox;
import com.intellij.util.ui.FormBuilder;
import com.sun.istack.NotNull;
import org.scanl.plugins.poc.common.PluginResourceBundle;
import org.scanl.plugins.poc.model.SmellType;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class AppSettingsComponent {

    private final JPanel panel;
    private final Map<SmellType, JBCheckBox> checkBoxes = new HashMap<>();

    public AppSettingsComponent() {
        FormBuilder formBuilder = FormBuilder.createFormBuilder();

        for (SmellType smell : SmellType.values()) {
            String label = PluginResourceBundle.message(PluginResourceBundle.Type.INSPECTION,"inspection.smell." + smell.toString() + ".name.display");
            JBCheckBox checkBox = new JBCheckBox(label);
            checkBoxes.put(smell, checkBox);
            formBuilder.addComponent(checkBox);
        }

        panel = formBuilder
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    public JComponent getPreferredFocusedComponent() {
        return checkBoxes.values().stream().findFirst().get();
    }

    public boolean getValue(SmellType type) {
        return checkBoxes.get(type).isSelected();
    }

    public void setValue(SmellType type, boolean value) {
        checkBoxes.get(type).setSelected(value);
    }

}
