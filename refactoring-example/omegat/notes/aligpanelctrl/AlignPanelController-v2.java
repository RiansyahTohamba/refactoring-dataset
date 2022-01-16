// versi after refactoring
public class AlignPanelController {
	// CDISP = 1
	// CINT = 6
    // FANOUT = 6
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
        // Q: kenapa saya tidak refactoring 'frame' nya ?
        // A: karena tidak ada if-else
        
        panel = new AlignPanel();
        setComparisonListener();
        setAlgoListener();
        setCalcListener();
        setCounterListener();
        setSegmentingLis();
        setFileFilter();
        setPanelRenderer();
        setOneAdjustLis();
        setMergeLis();
        setSplitLis();
        setEditListener();
        setSelectionLis();
        setSaveListener();
        setResetLis();
        setReloadLis();
        setRemoveTagLis();

        panel.continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                phase = Phase.EDIT;
                updatePanel();
            }
        });
        
        setHighlightListener();
        setFrameLis();
        setPinPoint();

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

        setAccelerator();
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
        setFont();
        setInitialState(parent);
    }

     private void setRemoveTagLis() {
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
    }

    private void setResetLis() {
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
    }

    private void setSelectionLis() {
        ListSelectionListener selectionListener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                updateCommandAvailability(panel, frame);
            }
        };
        panel.table.getColumnModel().getSelectionModel().addListSelectionListener(selectionListener);
        panel.table.getSelectionModel().addListSelectionListener(selectionListener);
    }

    private void setEditListener() {
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
    }

    private void setSplitLis() {
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
    }

    private void setSaveListener() {
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
    }

    private void setOneAdjustLis() {
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
    }

    private void setMergeLis() {
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
    }

    private void setPanelRenderer() {
        TableCellRenderer renderer = new MultilineCellRenderer();
        panel.table.setDefaultRenderer(Object.class, renderer);
        panel.table.setDefaultRenderer(Boolean.class, renderer);
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeRows(panel.table);
            }
        });
    }

    private void setFileFilter() {
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
    }

    private void setSegmentingLis() {
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
    }

    private void setCounterListener() {
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
    }

    private void setCalcListener() {
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
    }

    private void setAlgoListener() {
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
    }

    private void setComparisonListener() {
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
    }
}


