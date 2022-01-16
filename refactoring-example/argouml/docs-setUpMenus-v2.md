
    protected void setUpMenus() {
/*
	docs-setUpMenus-v2.md

      kode inisiasi belum mengeluarkan CINT walaupun FANOUT sudah 1.
      bagaimana jika hanya inisiasi?
      JMenu men = file.add()
      karena tipenya sama, pasti FANOUT
      new \w+, ada 13 match
*/
        JMenu file = getFile();
        setAccelaratorExitItem(file);

        JMenu edit = getEdit();

        setView();

        JMenu arrange = new JMenu(Localizer.localize("GefBase", "Arrange"));
        _menubar.add(arrange);
        arrange.setMnemonic('A');

        JMenuItem groupItem = arrange.add(new CmdGroup());
        groupItem.setMnemonic('G');


        setAlign(arrange);
        setDistribute(arrange);

        JMenu reorder = new JMenu(Localizer.localize("GefBase", "Reorder"));
        arrange.add(reorder);

        setNudge(arrange);
        setDeleteItem(edit);
        setUndoItem(edit);
        setRedoItem(edit);
        setCopyItem(edit);
        setPasteItem(edit);
        setBackItem(reorder);

        KeyStroke ctrlG = KeyStroke.getKeyStroke(KeyEvent.VK_G,KeyEvent.CTRL_MASK);
        groupItem.setAccelerator(ctrlG);

        KeyStroke ctrlU = KeyStroke.getKeyStroke(KeyEvent.VK_U,KeyEvent.CTRL_MASK);
        JMenuItem ungroupItem = arrange.add(new CmdUngroup());
        ungroupItem.setMnemonic('U');
        ungroupItem.setAccelerator(ctrlU);

        JMenuItem toFrontItem = reorder.add(new CmdReorder(CmdReorder.BRING_TO_FRONT));
        KeyStroke sCtrlF = KeyStroke.getKeyStroke(KeyEvent.VK_F,KeyEvent.CTRL_MASK | KeyEvent.SHIFT_MASK);
        toFrontItem.setAccelerator(sCtrlF);

        setBackwardItem(reorder);
        setAccelaratorCtrlF(reorder);
    }
