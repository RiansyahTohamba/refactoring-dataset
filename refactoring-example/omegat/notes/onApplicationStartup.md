onApplicationStartup

# catatan
memang ini adalah konfigurasi, maka wajar jika dcoh tinggi.
ini termasuk false positive dcoh

# banyak Preference
1. Preferences.isPreferenceDefault
2. 

# beberapa bisa di hide delegate
1. Core.getGlossaryManager().addGlossaryProvider(glossary);
2. Core.getMainWindow().getMainMenu().getGlossaryMenu().add(lookup);

# lambda

~~~
JMenuItem lookup = new JCheckBoxMenuItem();
lookup.setSelected(
        Preferences.isPreferenceDefault(Preferences.TAAS_LOOKUP, false)
);
Mnemonics.setLocalizedText(lookup, OStrings.getString("TAAS_MENU_LOOKUP"));
lookup.addActionListener(e -> {
    if (client.isAllowed()) {
        Preferences.setPreference(
            Preferences.TAAS_LOOKUP,
            lookup.isSelected()
        );
        Preferences.save();
    } else {
        lookup.setSelected(false);
        new PreferencesWindowController().show(
            Core.getMainWindow().getApplicationFrame(),
            TaaSPreferencesController.class
        );
    }
});
Core.getMainWindow().getMainMenu().getGlossaryMenu().add(lookup);
PreferencesControllers.addSupplier(TaaSPreferencesController::new);
Preferences.addPropertyChangeListener(
    Preferences.TAAS_LOOKUP,
    e -> lookup.setSelected((Boolean) e.getNewValue())
);
if (client.isAllowed()) {
    Core.getGlossaryManager().addGlossaryProvider(glossary);
}
~~~