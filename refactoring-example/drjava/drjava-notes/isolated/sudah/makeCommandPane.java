// read makeCommandPane.md
class ExecuteExternalDialog{
	// makeCommandPane depend on setFocusListener, getBottom, getMain
	private JPanel makeCommandPane() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel main = getMain();
		panel.add(main, BorderLayout.CENTER);
		panel.add(getBottom(), BorderLayout.SOUTH);
		setFocusListener();
		return panel;
	}

	private void setFocusListener(){
		// update the preview of the actual command line post substitution
		_documentListener = new DocumentListener() {
		  public void update(DocumentEvent e) {
		    assert EventQueue.isDispatchThread();
		    try {
		      // preview
		      _commandLineDoc.remove(0,_commandLineDoc.getLength());
		      String text = StringOps.replaceVariables(_commandLine.getText(), _props, PropertyMaps.GET_LAZY);
		      _commandLineDoc.insertString(_commandLineDoc.getLength(), StringOps.unescapeFileName(text), null);
		      
		      // command line
		      colorVariables(_commandLine,
		                     _props,
		                     this,
		                     _commandLineCmdAS,
		                     _varCommandLineCmdStyle,
		                     _varErrorCommandLineCmdStyle);
		      _commandLinePreviewLabel.setText("<html>Command line preview:<br>(" + _commandLinePreview.getText().length()+
		                                       " characters)</html>");
		    }
		    catch(BadLocationException ble) { _commandLinePreview.setText("Error."); }
		  }
		  public void changedUpdate(DocumentEvent e) { update(e); }
		  public void insertUpdate(DocumentEvent e) { update(e); }
		  public void removeUpdate(DocumentEvent e)  { update(e); }
		};

		_commandLine.getDocument().addDocumentListener(_documentListener);
		_documentListener.changedUpdate(null);

		// update the preview of the actual work directory post substitution
		_workDirDocumentListener = new DocumentListener() {
		  public void update(DocumentEvent e) {
		    assert EventQueue.isDispatchThread();
		    try {
		      // preview
		      _commandWorkDirLineDoc.remove(0,_commandWorkDirLineDoc.getLength());
		      String text = StringOps.replaceVariables(_commandWorkDirLine.getText(), _props, PropertyMaps.GET_LAZY);
		      _commandWorkDirLineDoc.insertString(0, StringOps.unescapeFileName(text), null);
		      
		      // command line
		      colorVariables(_commandWorkDirLine,
		                     _props,
		                     this,
		                     _commandLineCmdAS,
		                     _varCommandLineCmdStyle,
		                     _varErrorCommandLineCmdStyle);
		    }
		    catch(BadLocationException ble) { _commandLinePreview.setText("Error: " + ble); }
		  }
		  public void changedUpdate(DocumentEvent e) { update(e); }
		  public void insertUpdate(DocumentEvent e) { update(e); }
		  public void removeUpdate(DocumentEvent e)  { update(e); }
		};

		_commandWorkDirLine.getDocument().addDocumentListener(_workDirDocumentListener);
		_commandWorkDirLine.setText("${drjava.working.dir}");
		_workDirDocumentListener.changedUpdate(null);

		// update the preview of the actual enclosing .djapp file post substitution
		_enclosingFileDocumentListener = new DocumentListener() {
		  public void update(DocumentEvent e) {
		    assert EventQueue.isDispatchThread();
		    try {
		      // preview
		      _commandEnclosingFileLineDoc.remove(0,_commandEnclosingFileLineDoc.getLength());
		      String text = StringOps.replaceVariables(_commandEnclosingFileLine.getText(), _props, PropertyMaps.GET_LAZY);
		      _commandEnclosingFileLineDoc.insertString(0, StringOps.unescapeFileName(text), null);
		      
		      // command line
		      colorVariables(_commandEnclosingFileLine,
		                     _props,
		                     this,
		                     _commandLineCmdAS,
		                     _varCommandLineCmdStyle,
		                     _varErrorCommandLineCmdStyle);
		    }
		    catch(BadLocationException ble) {
		      _commandLinePreview.setText("Error: " + ble);
		    }
		  }
		  public void changedUpdate(DocumentEvent e) { update(e); }
		  public void insertUpdate(DocumentEvent e) { update(e); }
		  public void removeUpdate(DocumentEvent e)  { update(e); }
		};

		_commandEnclosingFileLine.getDocument().addDocumentListener(_enclosingFileDocumentListener);
		_commandEnclosingFileLine.setText("");
		_enclosingFileDocumentListener.changedUpdate(null);

		_lastCommandFocus = _commandLine;
		// do not allow preview to have focus
		_commandLine.addFocusListener(new FocusAdapter() {
		  public void focusGained(FocusEvent e) {
		    _lastCommandFocus = (JTextPane)e.getComponent();
		    _insertCommandButton.setEnabled(true);
		  }
		  public void focusLost(FocusEvent e) {
		    if ((e.getOppositeComponent() == _commandLinePreview) || 
		        (e.getOppositeComponent() == _commandWorkDirLinePreview)) {
		      _commandLine.requestFocus();
		    }
		  }
		});
		_commandWorkDirLine.addFocusListener(new FocusAdapter() {
		  public void focusGained(FocusEvent e) {
		    _lastCommandFocus = (JTextPane)e.getComponent();
		    _insertCommandButton.setEnabled(true);
		  }
		  public void focusLost(FocusEvent e) {
		    if ((e.getOppositeComponent() == _commandLinePreview) || 
		        (e.getOppositeComponent() == _commandWorkDirLinePreview)) {
		      _commandWorkDirLine.requestFocus();
		    }
		  }
		});
	}

	private JPanel getBottom(){
		JPanel bottom = new JPanel();
		bottom.setBorder(new EmptyBorder(5, 5, 5, 5));
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
		bottom.add(Box.createHorizontalGlue());
		if (!_editMode) {
		  bottom.add(_runCommandButton);
		}
		bottom.add(_saveCommandButton);
		bottom.add(_insertCommandButton);
		bottom.add(_cancelCommandButton);
		bottom.add(Box.createHorizontalGlue());
		return bottom;
	}

	private JPanel getMain(){
		GridBagLayout gridbag = new GridBagLayout();
		JPanel main = new JPanel(gridbag);
		main.setLayout(gridbag);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		Insets labelInsets = new Insets(5, 10, 0, 0);
		Insets compInsets  = new Insets(5, 5, 0, 10);

		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = labelInsets;
		JLabel commandLineLabel = new JLabel("Command line:");
		gridbag.setConstraints(commandLineLabel, c);
		main.add(commandLineLabel);

		c.weightx = 1.0;
		c.weighty = 32.0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = compInsets;

		_commandLine = new JTextPane();
		_commandLine.addKeyListener(new KeyListener() {
		  public void keyPressed(KeyEvent e) {
		    if (e.getKeyCode() == KeyEvent.VK_ENTER) e.consume();
		    else if (e.getKeyCode() == KeyEvent.VK_TAB) {
		      e.consume();
		      if (e.isShiftDown()) {
		        _insertCommandButton.setEnabled(false);
		        _cancelCommandButton.requestFocus();
		      }
		      else _commandWorkDirLine.requestFocus();
		    }
		  }
		  public void keyReleased(KeyEvent e) { }
		  public void keyTyped(KeyEvent e) { }
		});
		JScrollPane commandLineSP = new JScrollPane(_commandLine);
		commandLineSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		gridbag.setConstraints(commandLineSP, c);
		main.add(commandLineSP);

		c.weightx = 0.0;
		c.weighty = 0.0;
		c.gridwidth = 1;
		c.insets = labelInsets;
		_commandLinePreviewLabel = new JLabel("<html>Command line preview:<br>(0 characters)</html>");
		_commandLinePreviewLabel.setToolTipText(STALE_TOOLTIP);
		gridbag.setConstraints(_commandLinePreviewLabel, c);
		main.add(_commandLinePreviewLabel);

		c.weightx = 1.0;
		c.weighty = 32.0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = compInsets;

		_commandLinePreview = new JTextPane();
		_commandLinePreview.setToolTipText(STALE_TOOLTIP);
		_commandLineDoc = (StyledDocument)_commandLinePreview.getDocument();

		// Create a style object and then set the style attributes
		_varCommandLineCmdStyle = new SimpleAttributeSet();
		StyleConstants.setBackground(_varCommandLineCmdStyle, DrJava.getConfig().getSetting(DEFINITIONS_MATCH_COLOR));

		_commandLineCmdAS = new SimpleAttributeSet();
		StyleConstants.setForeground(_commandLineCmdAS, DrJava.getConfig().getSetting(DEFINITIONS_NORMAL_COLOR));
		_varCommandLineCmdStyle = new SimpleAttributeSet();
		StyleConstants.setBackground(_varCommandLineCmdStyle, DrJava.getConfig().getSetting(DEFINITIONS_MATCH_COLOR));
		_varErrorCommandLineCmdStyle = new SimpleAttributeSet();
		StyleConstants.setBackground(_varErrorCommandLineCmdStyle, DrJava.getConfig().getSetting(DEBUG_BREAKPOINT_COLOR));
		_varCommandLineCmdStyle = new SimpleAttributeSet();
		StyleConstants.setBackground(_varCommandLineCmdStyle, DrJava.getConfig().getSetting(DEFINITIONS_MATCH_COLOR));

		_commandLinePreview.setEditable(false);
		_commandLinePreview.setBackground(Color.LIGHT_GRAY);
		_commandLinePreview.setSelectedTextColor(Color.LIGHT_GRAY);
		JScrollPane commandLinePreviewSP = new JScrollPane(_commandLinePreview);
		commandLinePreviewSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		gridbag.setConstraints(commandLinePreviewSP, c);
		main.add(commandLinePreviewSP);

		// work directory
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.gridwidth = 1;
		c.insets = labelInsets;
		JLabel workDirLabel = new JLabel("Work directory:");
		gridbag.setConstraints(workDirLabel, c);
		main.add(workDirLabel);

		c.weightx = 1.0;
		c.weighty = 8.0;
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = compInsets;

		_commandWorkDirLine = new JTextPane();
		// do not allow a newline
		_commandWorkDirLine.addKeyListener(new KeyListener() {
		  public void keyPressed(KeyEvent e) {
		    if (e.getKeyCode() == KeyEvent.VK_ENTER) e.consume();
		    else if (e.getKeyCode() == KeyEvent.VK_TAB) {
		      e.consume();
		      if (e.isShiftDown()) {
		        _commandLine.requestFocus();
		      }
		      else _commandEnclosingFileLine.requestFocus();
		    }
		  }
		  public void  keyReleased(KeyEvent e) { }
		  public void  keyTyped(KeyEvent e) { }
		});
		JScrollPane commandWorkDirLineSP = new JScrollPane(_commandWorkDirLine);
		commandWorkDirLineSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		gridbag.setConstraints(commandWorkDirLineSP, c);
		main.add(commandWorkDirLineSP);

		c.weightx = 0.0;
		c.weighty = 0.0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = compInsets;

		_commandWorkDirBtn = new JButton("...");
		_commandWorkDirBtn.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) { chooseFile(_commandWorkDirLine); }
		});
		gridbag.setConstraints(_commandWorkDirBtn, c);
		main.add(_commandWorkDirBtn);

		c.weightx = 0.0;
		c.weighty = 0.0;
		c.gridwidth = 1;
		c.insets = labelInsets;
		JLabel commandWorkDirLinePreviewLabel = new JLabel("Work directory preview:");
		commandWorkDirLinePreviewLabel.setToolTipText(STALE_TOOLTIP);
		gridbag.setConstraints(commandWorkDirLinePreviewLabel, c);
		main.add(commandWorkDirLinePreviewLabel);

		c.weightx = 1.0;
		c.weighty = 8.0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = compInsets;

		_commandWorkDirLinePreview = new JTextPane();
		_commandWorkDirLinePreview.setToolTipText(STALE_TOOLTIP);
		_commandWorkDirLineDoc = (StyledDocument)_commandWorkDirLinePreview.getDocument();

		_commandWorkDirLinePreview.setEditable(false);
		_commandWorkDirLinePreview.setBackground(Color.LIGHT_GRAY);
		_commandWorkDirLinePreview.setSelectedTextColor(Color.LIGHT_GRAY);
		JScrollPane commandWorkDirLinePreviewSP = new JScrollPane(_commandWorkDirLinePreview);
		commandWorkDirLinePreviewSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		gridbag.setConstraints(commandWorkDirLinePreviewSP, c);
		main.add(commandWorkDirLinePreviewSP);

		// enclosing .djapp file
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.gridwidth = 1;
		c.insets = labelInsets;
		JLabel enclosingFileLabel = new JLabel("Enclosing .djapp file:");
		gridbag.setConstraints(enclosingFileLabel, c);
		main.add(enclosingFileLabel);

		c.weightx = 1.0;
		c.weighty = 8.0;
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = compInsets;

		_commandEnclosingFileLine = new JTextPane();
		// do not allow a newline
		_commandEnclosingFileLine.addKeyListener(new KeyListener() {
		  public void keyPressed(KeyEvent e) {
		    if (e.getKeyCode() == KeyEvent.VK_ENTER)  e.consume();
		    else if (e.getKeyCode() == KeyEvent.VK_TAB) {
		      e.consume();
		      if (e.isShiftDown()) _commandWorkDirLine.requestFocus();
		      else {
		        _insertCommandButton.setEnabled(false);
		        if (_editMode) {
		          _saveCommandButton.requestFocus();
		        }
		        else _runCommandButton.requestFocus();
		      }
		    }
		  }
		  public void  keyReleased(KeyEvent e) { }
		  public void  keyTyped(KeyEvent e) { }
		});
		JScrollPane commandEnclosingFileLineSP = new JScrollPane(_commandEnclosingFileLine);
		commandEnclosingFileLineSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		gridbag.setConstraints(commandEnclosingFileLineSP, c);
		main.add(commandEnclosingFileLineSP);

		c.weightx = 0.0;
		c.weighty = 0.0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = compInsets;

		_commandEnclosingFileBtn = new JButton("...");
		_commandEnclosingFileBtn.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) { chooseFile(_commandEnclosingFileLine); }
		});
		gridbag.setConstraints(_commandEnclosingFileBtn, c);
		main.add(_commandEnclosingFileBtn);

		c.weightx = 0.0;
		c.weighty = 0.0;
		c.gridwidth = 1;
		c.insets = labelInsets;
		JLabel commandEnclosingFileLinePreviewLabel = new JLabel("Enclosing .djapp file preview:");
		commandEnclosingFileLinePreviewLabel.setToolTipText(STALE_TOOLTIP);
		gridbag.setConstraints(commandEnclosingFileLinePreviewLabel, c);
		main.add(commandEnclosingFileLinePreviewLabel);

		c.weightx = 1.0;
		c.weighty = 8.0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = compInsets;

		_commandEnclosingFileLinePreview = new JTextPane();
		_commandEnclosingFileLinePreview.setToolTipText(STALE_TOOLTIP);
		_commandEnclosingFileLineDoc = (StyledDocument)_commandEnclosingFileLinePreview.getDocument();

		_commandEnclosingFileLinePreview.setEditable(false);
		_commandEnclosingFileLinePreview.setBackground(Color.LIGHT_GRAY);
		_commandEnclosingFileLinePreview.setSelectedTextColor(Color.LIGHT_GRAY);
		JScrollPane commandEnclosingFileLinePreviewSP = new JScrollPane(_commandEnclosingFileLinePreview);
		commandEnclosingFileLinePreviewSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		gridbag.setConstraints(commandEnclosingFileLinePreviewSP, c);
		main.add(commandEnclosingFileLinePreviewSP);
		
		return main;
	}	

}