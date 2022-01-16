// versi before refactoring
public class AlignPanelController {
	// CDISP = 0.968
	// CINT = 32
    // FANOUT = 31
    // berarti ada 31 kelas yang terlibat dalam method show() ini
	public void show(Component parent) {
        frame = new AlignMenuFrame();
        frame.setTitle(OStrings.getString("ALIGNER_PANEL"));
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeFrame(frame);
            }
        });
        
        panel = new AlignPanel();
        ActionListener comparisonListener = e -> {
            ComparisonMode newValue = (ComparisonMode) ((JComboBox<?>) e.getSource()).getSelectedItem();
            if (newValue != aligner.comparisonMode && confirmReset(frame)) {
                aligner.comparisonMode = newValue;
                reloadBeads();
            } else {
                panel.comparisonComboBox.setSelectedItem(aligner.comparisonMode);
            }
        };
        panel.comparisonComboBox.addActionListener(comparisonListener);
        panel.comparisonComboBox
                .setRenderer(new EnumRenderer<ComparisonMode>("ALIGNER_ENUM_COMPARISON_MODE_"));

        ActionListener algorithmListener = e -> {
            AlgorithmClass newValue = (AlgorithmClass) ((JComboBox<?>) e.getSource()).getSelectedItem();
            if (newValue != aligner.algorithmClass && confirmReset(frame)) {
                aligner.algorithmClass = newValue;
                reloadBeads();
            } else {
                panel.algorithmComboBox.setSelectedItem(aligner.algorithmClass);
            }
        };
        panel.algorithmComboBox.addActionListener(algorithmListener);
        panel.algorithmComboBox
                .setRenderer(new EnumRenderer<AlgorithmClass>("ALIGNER_ENUM_ALGORITHM_CLASS_"));

        ActionListener calculatorListener = e -> {
            CalculatorType newValue = (CalculatorType) ((JComboBox<?>) e.getSource()).getSelectedItem();
            if (newValue != aligner.calculatorType && confirmReset(frame)) {
                aligner.calculatorType = newValue;
                reloadBeads();
            } else {
                panel.calculatorComboBox.setSelectedItem(aligner.calculatorType);
            }
        };
        panel.calculatorComboBox.addActionListener(calculatorListener);
        panel.calculatorComboBox
                .setRenderer(new EnumRenderer<CalculatorType>("ALIGNER_ENUM_CALCULATOR_TYPE_"));

        ActionListener counterListener = e -> {
            CounterType newValue = (CounterType) ((JComboBox<?>) e.getSource()).getSelectedItem();
            if (newValue != aligner.counterType && confirmReset(frame)) {
                aligner.counterType = newValue;
                reloadBeads();
            } else {
                panel.counterComboBox.setSelectedItem(aligner.counterType);
            }
        };
        panel.counterComboBox.addActionListener(counterListener);
        panel.counterComboBox.setRenderer(new EnumRenderer<CounterType>("ALIGNER_ENUM_COUNTER_TYPE_"));

        ActionListener segmentingListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean newValue = ((AbstractButton) e.getSource()).isSelected();
                if (newValue != aligner.segment && confirmReset(frame)) {
                    aligner.segment = newValue;
                    reloadBeads();
                } else {
                    panel.segmentingCheckBox.setSelected(aligner.segment);
                    frame.segmentingItem.setSelected(aligner.segment);
                }
            }
        };
        panel.segmentingCheckBox.addActionListener(segmentingListener);
        frame.segmentingItem.addActionListener(segmentingListener);

        ActionListener segmentingRulesListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (confirmReset(frame)) {
                    SegmentationCustomizer customizer = new SegmentationCustomizer(false, SRX.getDefault(),
                            Core.getSegmenter().getSRX(), null);
                    if (customizer.show(frame)) {
                        customizedSRX = customizer.getResult();
                        Core.setSegmenter(new Segmenter(customizedSRX));
                        reloadBeads();
                    }
                }
            }
        };
        panel.segmentingRulesButton.addActionListener(segmentingRulesListener);
        frame.segmentingRulesItem.addActionListener(segmentingRulesListener);

        ActionListener filterSettingsListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (confirmReset(frame)) {
                    FiltersCustomizer customizer = new FiltersCustomizer(false,
                            FilterMaster.createDefaultFiltersConfig(), Core.getFilterMaster().getConfig(),
                            null);
                    if (customizer.show(frame)) {
                        customizedFilters = customizer.getResult();
                        Core.setFilterMaster(new FilterMaster(customizedFilters));
                        aligner.clearLoaded();
                        reloadBeads();
                    }
                }
            }
        };
        panel.fileFilterSettingsButton.addActionListener(filterSettingsListener);
        frame.fileFilterSettingsItem.addActionListener(filterSettingsListener);

        TableCellRenderer renderer = new MultilineCellRenderer();
        panel.table.setDefaultRenderer(Object.class, renderer);
        panel.table.setDefaultRenderer(Boolean.class, renderer);
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeRows(panel.table);
            }
        });

        ActionListener oneAdjustListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] rows = panel.table.getSelectedRows();
                int col = panel.table.getSelectedColumn();
                boolean up = e.getSource().equals(panel.moveUpButton) || e.getSource().equals(frame.moveUpItem);
                BeadTableModel model = (BeadTableModel) panel.table.getModel();
                if ((e.getModifiers() & Java8Compat.getMenuShortcutKeyMaskEx()) != 0) {
                    int trgRow = up ? model.prevBeadFromRow(rows[0])
                            : model.nextBeadFromRow(rows[rows.length - 1]);
                    moveRows(rows, col, trgRow);
                } else {
                    int offset = up ? -1 : 1;
                    slideRows(rows, col, offset);
                }
            }
        };
        panel.moveUpButton.addActionListener(oneAdjustListener);
        frame.moveUpItem.addActionListener(oneAdjustListener);
        panel.moveDownButton.addActionListener(oneAdjustListener);
        frame.moveDownItem.addActionListener(oneAdjustListener);

        ActionListener mergeListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] rows = panel.table.getSelectedRows();
                int col = panel.table.getSelectedColumn();
                BeadTableModel model = (BeadTableModel) panel.table.getModel();
                if (rows.length == 1) {
                    rows = new int[] { rows[0], model.nextNonEmptyCell(rows[0], col) };
                }
                int beads = model.beadsInRowSpan(rows);
                if (beads < 1) {
                    // Do nothing
                } else if (beads == 1) {
                    mergeRows(rows, col);
                } else {
                    moveRows(rows, col, rows[0]);
                }
            }
        };
        panel.mergeButton.addActionListener(mergeListener);
        frame.mergeItem.addActionListener(mergeListener);

        ActionListener splitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] rows = panel.table.getSelectedRows();
                int col = panel.table.getSelectedColumn();
                BeadTableModel model = (BeadTableModel) panel.table.getModel();
                int beads = model.beadsInRowSpan(rows);
                if (beads != 1) {
                    // Do nothing
                } else if (rows.length == 1) {
                    splitRow(rows[0], col);
                } else {
                    splitBead(rows, col);
                }
            }
        };
        panel.splitButton.addActionListener(splitListener);
        frame.splitItem.addActionListener(splitListener);

        ActionListener editListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int row = panel.table.getSelectedRow();
                int col = panel.table.getSelectedColumn();
                editRow(row, col);
            }
        };
        panel.editButton.addActionListener(editListener);
        frame.editItem.addActionListener(editListener);

        ListSelectionListener selectionListener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                updateCommandAvailability(panel, frame);
            }
        };
        panel.table.getColumnModel().getSelectionModel().addListSelectionListener(selectionListener);
        panel.table.getSelectionModel().addListSelectionListener(selectionListener);

        ActionListener saveListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!confirmSaveTMX(panel)) {
                    return;
                }
                while (true) {
                    JFileChooser chooser = new JFileChooser();
                    chooser.setSelectedFile(new File(defaultSaveDir, getOutFileName()));
                    chooser.setDialogTitle(OStrings.getString("ALIGNER_PANEL_DIALOG_SAVE"));
                    if (JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(frame)) {
                        File file = chooser.getSelectedFile();
                        if (file.isFile()) {
                            if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(frame,
                                    StringUtil.format(OStrings.getString("ALIGNER_PANEL_DIALOG_OVERWRITE"),
                                            file.getName()),
                                    OStrings.getString("ALIGNER_DIALOG_WARNING_TITLE"), JOptionPane.WARNING_MESSAGE)) {
                                continue;
                            }
                        }
                        List<MutableBead> beads = ((BeadTableModel) panel.table.getModel()).getData();
                        try {
                            aligner.writePairsToTMX(file,
                                    MutableBead.beadsToEntries(aligner.srcLang, aligner.trgLang, beads));
                            modified = false;
                        } catch (Exception ex) {
                            Log.log(ex);
                            JOptionPane.showMessageDialog(frame, OStrings.getString("ALIGNER_PANEL_SAVE_ERROR"),
                                    OStrings.getString("ERROR_TITLE"), JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
                }
            }
        };
        panel.saveButton.addActionListener(saveListener);
        frame.saveItem.addActionListener(saveListener);

        ActionListener resetListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (confirmReset(frame)) {
                    if (phase == Phase.ALIGN) {
                        aligner.restoreDefaults();
                    }
                    reloadBeads();
                }
            }
        };
        panel.resetButton.addActionListener(resetListener);
        frame.resetItem.addActionListener(resetListener);

        ActionListener reloadListener = e -> {
            if (confirmReset(frame)) {
                aligner.clearLoaded();
                reloadBeads();
            }
        };
        frame.reloadItem.addActionListener(reloadListener);

        ActionListener removeTagsListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean newValue = ((AbstractButton) e.getSource()).isSelected();
                if (newValue != aligner.removeTags && confirmReset(frame)) {
                    aligner.removeTags = newValue;
                    aligner.clearLoaded();
                    reloadBeads();
                } else {
                    panel.removeTagsCheckBox.setSelected(aligner.removeTags);
                    frame.removeTagsItem.setSelected(aligner.removeTags);
                }
            }
        };
        panel.removeTagsCheckBox.addActionListener(removeTagsListener);
        frame.removeTagsItem.addActionListener(removeTagsListener);

        panel.continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                phase = Phase.EDIT;
                updatePanel();
            }
        });

        ActionListener highlightListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doHighlight = ((AbstractButton) e.getSource()).isSelected();
                updateHighlight();
            }
        };
        panel.highlightCheckBox.addActionListener(highlightListener);
        frame.highlightItem.addActionListener(highlightListener);

        ActionListener highlightPatternListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PatternPanelController patternEditor = new PatternPanelController(highlightPattern);
                highlightPattern = patternEditor.show(frame);
                Preferences.setPreference(Preferences.ALIGNER_HIGHLIGHT_PATTERN, highlightPattern.pattern());
                updateHighlight();
            }
        };
        panel.highlightPatternButton.addActionListener(highlightPatternListener);
        frame.highlightPatternItem.addActionListener(highlightPatternListener);

        frame.markAcceptedItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setStatus(MutableBead.Status.ACCEPTED, panel.table.getSelectedRows());
            }
        });

        frame.markNeedsReviewItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setStatus(MutableBead.Status.NEEDS_REVIEW, panel.table.getSelectedRows());
            }
        });

        frame.clearMarkItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setStatus(MutableBead.Status.DEFAULT, panel.table.getSelectedRows());
            }
        });

        frame.toggleSelectedItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleEnabled(panel.table.getSelectedRows());
            }
        });

        frame.closeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeFrame(frame);
            }
        });

        frame.keepAllItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleAllEnabled(true);
            }
        });

        frame.keepNoneItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleAllEnabled(false);
            }
        });

        frame.realignPendingItem.addActionListener(e -> {
            realignPending();
        });

        frame.pinpointAlignStartItem.addActionListener(e -> {
            phase = Phase.PINPOINT;
            ppRow = panel.table.getSelectedRow();
            ppCol = panel.table.getSelectedColumn();
            panel.table.clearSelection();
            updatePanel();
        });

        frame.pinpointAlignEndItem.addActionListener(e -> {
            pinpointAlign(panel.table.getSelectedRow(), panel.table.getSelectedColumn());
        });

        frame.pinpointAlignCancelItem.addActionListener(e -> {
            phase = Phase.EDIT;
            ppRow = -1;
            ppCol = -1;
            panel.table.repaint();
            updatePanel();
        });

        panel.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (phase == Phase.PINPOINT) {
                    JTable table = (JTable) e.getSource();
                    int row = table.rowAtPoint(e.getPoint());
                    int col = table.columnAtPoint(e.getPoint());
                    pinpointAlign(row, col);
                }
            }
        });

        frame.resetItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_R,
                        Java8Compat.getMenuShortcutKeyMaskEx() | KeyEvent.SHIFT_DOWN_MASK));
        frame.realignPendingItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_R, Java8Compat.getMenuShortcutKeyMaskEx()));
        frame.saveItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_S, Java8Compat.getMenuShortcutKeyMaskEx()));
        frame.closeItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_W, Java8Compat.getMenuShortcutKeyMaskEx()));

        // emacs-like keys for table navigation
        // See javax.swing.plaf.BasicTableUI.Actions for supported action names.
        setKeyboardShortcut(panel.table, "selectNextRow", 'n');
        setKeyboardShortcut(panel.table, "selectNextRowExtendSelection", 'N');
        setKeyboardShortcut(panel.table, "selectPreviousRow", 'p');
        setKeyboardShortcut(panel.table, "selectPreviousRowExtendSelection", 'P');
        setKeyboardShortcut(panel.table, "selectNextColumn", 'f');
        setKeyboardShortcut(panel.table, "selectNextColumnExtendSelection", 'F');
        setKeyboardShortcut(panel.table, "selectPreviousColumn", 'b');
        setKeyboardShortcut(panel.table, "selectPreviousColumnExtendSelection", 'B');

        panel.table.setTransferHandler(new AlignTransferHandler());
        panel.table.addPropertyChangeListener("dropLocation", new DropLocationListener());
        if (Preferences.isPreference(Preferences.PROJECT_FILES_USE_FONT)) {
            try {
                String fontName = Preferences.getPreference(Preferences.TF_SRC_FONT_NAME);
                int fontSize = Integer.parseInt(Preferences.getPreference(Preferences.TF_SRC_FONT_SIZE));
                panel.table.setFont(new Font(fontName, Font.PLAIN, fontSize));
            } catch (Exception e) {
                Log.log(e);
            }
        }

        // Set initial state
        updateHighlight();
        updatePanel();
        reloadBeads();

        frame.add(panel);
        frame.pack();
        frame.setMinimumSize(frame.getSize());
        frame.setLocationRelativeTo(parent);
        frame.setVisible(true);
    }
}