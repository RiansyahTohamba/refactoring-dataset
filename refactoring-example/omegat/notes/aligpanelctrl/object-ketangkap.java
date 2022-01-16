panel = 1


ini-dia frame.setTitle(ini-dia OStrings.getString("ALIGNER_PANEL"));
ini-dia frame.setDefaultCloseOperation(ini-dia WindowConstants.DO_NOTHING_ON_CLOSE);
ini-dia frame.addWindowListener(new WindowAdapter() {
ini-dia e.getSource()).getSelectedItem();
ini-dia aligner.comparisonMode && confirmReset(frame)) {
ini-dia aligner.comparisonMode = newValue;
ini-dia panel.ini-dia comparisonComboBox.setSelectedItem(ini-dia aligner.comparisonMode);
ini-dia panel.ini-dia comparisonComboBox.addActionListener(comparisonListener);
ini-dia panel.comparisonComboBox
ini-dia e.getSource()).getSelectedItem();
ini-dia aligner.algorithmClass && confirmReset(frame)) {
ini-dia aligner.algorithmClass = newValue;
ini-dia panel.ini-dia algorithmComboBox.setSelectedItem(ini-dia aligner.algorithmClass);
ini-dia panel.ini-dia algorithmComboBox.addActionListener(algorithmListener);
ini-dia panel.algorithmComboBox
ini-dia e.getSource()).getSelectedItem();
ini-dia aligner.calculatorType && confirmReset(frame)) {
ini-dia aligner.calculatorType = newValue;
ini-dia panel.calculatorComboBox.setSelectedItem(ini-dia aligner.calculatorType);
ini-dia panel.calculatorComboBox
ini-dia e.getSource()).getSelectedItem();
ini-dia aligner.counterType && confirmReset(frame)) {
ini-dia aligner.counterType = newValue;
ini-dia panel.ini-dia counterComboBox.setSelectedItem(ini-dia aligner.counterType);
ini-dia panel.ini-dia counterComboBox.addActionListener(counterListener);
ini-dia panel.ini-dia counterComboBox.setRenderer(new EnumRenderer<CounterType>("ALIGNER_ENUM_COUNTER_TYPE_"));
ini-dia e.getSource()).isSelected();
ini-dia aligner.segment && confirmReset(frame)) {
ini-dia aligner.segment = newValue;
ini-dia panel.ini-dia segmentingCheckBox.setSelected(ini-dia aligner.segment);
ini-dia frame.ini-dia segmentingItem.setSelected(ini-dia aligner.segment);
ini-dia panel.ini-dia segmentingCheckBox.addActionListener(segmentingListener);
ini-dia frame.ini-dia segmentingItem.addActionListener(segmentingListener);
ini-dia SRX.getDefault(),
ini-dia Core.getSegmenter().getSRX(), null);
ini-dia customizer.show(frame)) {
ini-dia customizer.getResult();
ini-dia Core.setSegmenter(new Segmenter(customizedSRX));
ini-dia panel.ini-dia segmentingRulesButton.addActionListener(segmentingRulesListener);
ini-dia frame.ini-dia segmentingRulesItem.addActionListener(segmentingRulesListener);
ini-dia FilterMaster.createDefaultFiltersConfig(), ini-dia Core.getFilterMaster().getConfig(),
ini-dia customizer.show(frame)) {
ini-dia customizer.getResult();
ini-dia Core.setFilterMaster(new FilterMaster(customizedFilters));
ini-dia aligner.clearLoaded();
ini-dia panel.ini-dia fileFilterSettingsButton.addActionListener(filterSettingsListener);
ini-dia frame.ini-dia fileFilterSettingsItem.addActionListener(filterSettingsListener);
ini-dia panel.ini-dia table.setDefaultRenderer(ini-dia Object.class, renderer);
ini-dia panel.ini-dia table.setDefaultRenderer(ini-dia Boolean.class, renderer);
ini-dia panel.addComponentListener(new ComponentAdapter() {
ini-dia panel.table);
ini-dia panel.ini-dia table.getSelectedRows();
ini-dia panel.ini-dia table.getSelectedColumn();
ini-dia e.getSource().equals(ini-dia panel.moveUpButton) || ini-dia e.getSource().equals(ini-dia frame.moveUpItem);
ini-dia panel.ini-dia table.getModel();
ini-dia e.getModifiers() & ini-dia Java8Compat.getMenuShortcutKeyMaskEx()) != 0) {
ini-dia model.prevBeadFromRow(rows[0])
ini-dia model.nextBeadFromRow(rows[ini-dia rows.length - 1]);
ini-dia panel.ini-dia moveUpButton.addActionListener(oneAdjustListener);
ini-dia frame.ini-dia moveUpItem.addActionListener(oneAdjustListener);
ini-dia panel.ini-dia moveDownButton.addActionListener(oneAdjustListener);
ini-dia frame.ini-dia moveDownItem.addActionListener(oneAdjustListener);
ini-dia panel.ini-dia table.getSelectedRows();
ini-dia panel.ini-dia table.getSelectedColumn();
ini-dia panel.ini-dia table.getModel();
ini-dia rows.length == 1) {
ini-dia model.nextNonEmptyCell(rows[0], col) };
ini-dia model.beadsInRowSpan(rows);
ini-dia panel.ini-dia mergeButton.addActionListener(mergeListener);
ini-dia frame.ini-dia mergeItem.addActionListener(mergeListener);
ini-dia panel.ini-dia table.getSelectedRows();
ini-dia panel.ini-dia table.getSelectedColumn();
ini-dia panel.ini-dia table.getModel();
ini-dia model.beadsInRowSpan(rows);
ini-dia rows.length == 1) {
ini-dia panel.ini-dia splitButton.addActionListener(splitListener);
ini-dia frame.ini-dia splitItem.addActionListener(splitListener);
ini-dia panel.ini-dia table.getSelectedRow();
ini-dia panel.ini-dia table.getSelectedColumn();
ini-dia panel.ini-dia editButton.addActionListener(editListener);
ini-dia frame.ini-dia editItem.addActionListener(editListener);
ini-dia panel.ini-dia table.getColumnModel().getSelectionModel().addListSelectionListener(selectionListener);
ini-dia panel.ini-dia table.getSelectionModel().addListSelectionListener(selectionListener);
ini-dia chooser.setSelectedFile(new File(defaultSaveDir, getOutFileName()));
ini-dia chooser.setDialogTitle(ini-dia OStrings.getString("ALIGNER_PANEL_DIALOG_SAVE"));
ini-dia JFileChooser.APPROVE_OPTION == ini-dia chooser.showSaveDialog(frame)) {
ini-dia chooser.getSelectedFile();
ini-dia file.isFile()) {
ini-dia JOptionPane.OK_OPTION != ini-dia JOptionPane.showConfirmDialog(frame,
ini-dia StringUtil.format(ini-dia OStrings.getString("ALIGNER_PANEL_DIALOG_OVERWRITE"),
ini-dia file.getName()),
ini-dia OStrings.getString("ALIGNER_DIALOG_WARNING_TITLE"), ini-dia JOptionPane.WARNING_MESSAGE)) {
ini-dia panel.ini-dia table.getModel()).getData();
ini-dia aligner.writePairsToTMX(file,
ini-dia MutableBead.beadsToEntries(ini-dia aligner.srcLang, ini-dia aligner.trgLang, beads));
ini-dia Log.log(ex);
ini-dia JOptionPane.showMessageDialog(frame, ini-dia OStrings.getString("ALIGNER_PANEL_SAVE_ERROR"),
ini-dia OStrings.getString("ERROR_TITLE"), ini-dia JOptionPane.ERROR_MESSAGE);
ini-dia panel.ini-dia saveButton.addActionListener(saveListener);
ini-dia frame.ini-dia saveItem.addActionListener(saveListener);
ini-dia Phase.ALIGN) {
ini-dia aligner.restoreDefaults();
ini-dia panel.ini-dia resetButton.addActionListener(resetListener);
ini-dia frame.ini-dia resetItem.addActionListener(resetListener);
ini-dia aligner.clearLoaded();
ini-dia frame.ini-dia reloadItem.addActionListener(reloadListener);
ini-dia e.getSource()).isSelected();
ini-dia aligner.removeTags && confirmReset(frame)) {
ini-dia aligner.removeTags = newValue;
ini-dia aligner.clearLoaded();
ini-dia panel.ini-dia removeTagsCheckBox.setSelected(ini-dia aligner.removeTags);
ini-dia frame.ini-dia removeTagsItem.setSelected(ini-dia aligner.removeTags);
ini-dia panel.ini-dia removeTagsCheckBox.addActionListener(removeTagsListener);
ini-dia frame.ini-dia removeTagsItem.addActionListener(removeTagsListener);
ini-dia panel.ini-dia continueButton.addActionListener(new ActionListener() {
ini-dia Phase.EDIT;
ini-dia e.getSource()).isSelected();
ini-dia panel.ini-dia highlightCheckBox.addActionListener(highlightListener);
ini-dia frame.ini-dia highlightItem.addActionListener(highlightListener);
ini-dia patternEditor.show(frame);
ini-dia Preferences.setPreference(ini-dia Preferences.ALIGNER_HIGHLIGHT_PATTERN, ini-dia highlightPattern.pattern());
ini-dia panel.ini-dia highlightPatternButton.addActionListener(highlightPatternListener);
ini-dia frame.ini-dia highlightPatternItem.addActionListener(highlightPatternListener);
ini-dia frame.ini-dia markAcceptedItem.addActionListener(new ActionListener() {
ini-dia MutableBead.ini-dia Status.ACCEPTED, ini-dia panel.ini-dia table.getSelectedRows());
ini-dia frame.ini-dia markNeedsReviewItem.addActionListener(new ActionListener() {
ini-dia MutableBead.ini-dia Status.NEEDS_REVIEW, ini-dia panel.ini-dia table.getSelectedRows());
ini-dia frame.ini-dia clearMarkItem.addActionListener(new ActionListener() {
ini-dia MutableBead.ini-dia Status.DEFAULT, ini-dia panel.ini-dia table.getSelectedRows());
ini-dia frame.ini-dia toggleSelectedItem.addActionListener(new ActionListener() {
ini-dia panel.ini-dia table.getSelectedRows());
ini-dia frame.ini-dia closeItem.addActionListener(new ActionListener() {
ini-dia frame.ini-dia keepAllItem.addActionListener(new ActionListener() {
ini-dia frame.ini-dia keepNoneItem.addActionListener(new ActionListener() {
ini-dia frame.ini-dia realignPendingItem.addActionListener(e -> {
ini-dia frame.ini-dia pinpointAlignStartItem.addActionListener(e -> {
ini-dia Phase.PINPOINT;
ini-dia panel.ini-dia table.getSelectedRow();
ini-dia panel.ini-dia table.getSelectedColumn();
ini-dia panel.ini-dia table.clearSelection();
ini-dia frame.ini-dia pinpointAlignEndItem.addActionListener(e -> {
ini-dia panel.ini-dia table.getSelectedRow(), ini-dia panel.ini-dia table.getSelectedColumn());
ini-dia frame.ini-dia pinpointAlignCancelItem.addActionListener(e -> {
ini-dia Phase.EDIT;
ini-dia panel.ini-dia table.repaint();
ini-dia panel.ini-dia table.addMouseListener(new MouseAdapter() {
ini-dia Phase.PINPOINT) {
ini-dia e.getSource();
ini-dia table.rowAtPoint(ini-dia e.getPoint());
ini-dia table.columnAtPoint(ini-dia e.getPoint());
ini-dia frame.ini-dia resetItem.setAccelerator(
ini-dia KeyStroke.getKeyStroke(ini-dia KeyEvent.VK_R,
ini-dia Java8Compat.getMenuShortcutKeyMaskEx() | ini-dia KeyEvent.SHIFT_DOWN_MASK));
ini-dia frame.ini-dia realignPendingItem.setAccelerator(
ini-dia KeyStroke.getKeyStroke(ini-dia KeyEvent.VK_R, ini-dia Java8Compat.getMenuShortcutKeyMaskEx()));
ini-dia frame.ini-dia saveItem.setAccelerator(
ini-dia KeyStroke.getKeyStroke(ini-dia KeyEvent.VK_S, ini-dia Java8Compat.getMenuShortcutKeyMaskEx()));
ini-dia frame.ini-dia closeItem.setAccelerator(
ini-dia KeyStroke.getKeyStroke(ini-dia KeyEvent.VK_W, ini-dia Java8Compat.getMenuShortcutKeyMaskEx()));
ini-dia javax.ini-dia swing.ini-dia plaf.ini-dia BasicTableUI.Actions for supported action ini-dia names.

ini-dia panel.table, "selectNextRow", 'n');

ini-dia panel.ini-dia table.setTransferHandler(new AlignTransferHandler());
ini-dia panel.ini-dia table.addPropertyChangeListener("dropLocation", new DropLocationListener());
ini-dia Preferences.isPreference(ini-dia Preferences.PROJECT_FILES_USE_FONT)) {
ini-dia Preferences.getPreference(ini-dia Preferences.TF_SRC_FONT_NAME);
ini-dia Integer.parseInt(ini-dia Preferences.getPreference(ini-dia Preferences.TF_SRC_FONT_SIZE));
ini-dia panel.ini-dia table.setFont(new Font(fontName, ini-dia Font.PLAIN, fontSize));
ini-dia Log.log(e);
ini-dia frame.add(panel);
ini-dia frame.pack();
ini-dia frame.setMinimumSize(ini-dia frame.getSize());
ini-dia frame.setLocationRelativeTo(parent);
ini-dia frame.setVisible(true);