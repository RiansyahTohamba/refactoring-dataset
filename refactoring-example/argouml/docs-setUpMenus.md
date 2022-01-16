protected void setUpMenus() {
/*
      kode inisiasi belum mengeluarkan CINT walaupun FANOUT sudah 1.
      bagaimana jika hanya inisiasi?
      JMenu men = file.add()
      karena tipenya sama, pasti FANOUT

      new \w+, ada 13 match
      new JMenu( = 4 match      
      9 match ini sisanya apa? FANOUT=9
      new UndoAction(
      new CmdCopy(
      new CmdPaste(
      new CmdRemoveFromGraph(
      new CmdUseReshape()
      new CmdUseResize()
      new CmdUseRotate()
      new CmdGroup
      new CmdUngroup
      new CmdReorder
*/
        JMenu file = getFile();
        setAccelaratorExitItem(file);

        JMenu edit = new JMenu(Localizer.localize("GefBase", "Edit"));
        edit.setMnemonic('E');
        _menubar.add(edit);

        JMenuItem undoItem = edit.add(new UndoAction(Localizer.localize("GefBase", "Undo")));
        undoItem.setMnemonic(Localizer.localize("GefBase", "UndoMnemonic").charAt(0));

        setSelect(edit);

        edit.addSeparator();
        JMenuItem copyItem = edit.add(new CmdCopy());
        copyItem.setMnemonic('C');

        JMenuItem pasteItem = edit.add(new CmdPaste());
        pasteItem.setMnemonic('P');

        JMenuItem deleteItem = edit.add(new CmdRemoveFromGraph());

        edit.addSeparator();
        edit.add(new CmdUseReshape());
        edit.add(new CmdUseResize());
        edit.add(new CmdUseRotate());

        setView();

        JMenu arrange = new JMenu(Localizer.localize("GefBase", "Arrange"));
        _menubar.add(arrange);
        arrange.setMnemonic('A');

        JMenuItem groupItem = arrange.add(new CmdGroup());
        groupItem.setMnemonic('G');

        JMenuItem ungroupItem = arrange.add(new CmdUngroup());
        ungroupItem.setMnemonic('U');

        setAlign(arrange);
        setDistribute(arrange);

        JMenu reorder = new JMenu(Localizer.localize("GefBase", "Reorder"));
        arrange.add(reorder);

        JMenuItem toFrontItem = reorder.add(new CmdReorder(CmdReorder.BRING_TO_FRONT));

        setNudge(arrange);

        KeyStroke delKey = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0);
        deleteItem.setAccelerator(delKey);

        KeyStroke ctrlZ = KeyStroke.getKeyStroke(KeyEvent.VK_Z,KeyEvent.CTRL_MASK);
        undoItem.setAccelerator(ctrlZ);

        setRedoItem(edit);

        KeyStroke ctrlC = KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_MASK);
        copyItem.setAccelerator(ctrlC);

        KeyStroke ctrlV = KeyStroke.getKeyStroke(KeyEvent.VK_V,KeyEvent.CTRL_MASK);
        pasteItem.setAccelerator(ctrlV);

        KeyStroke ctrlU = KeyStroke.getKeyStroke(KeyEvent.VK_U,KeyEvent.CTRL_MASK);


        setBackItem(reorder);

        KeyStroke sCtrlF = KeyStroke.getKeyStroke(KeyEvent.VK_F,KeyEvent.CTRL_MASK | KeyEvent.SHIFT_MASK);

        KeyStroke ctrlG = KeyStroke.getKeyStroke(KeyEvent.VK_G,KeyEvent.CTRL_MASK);
        groupItem.setAccelerator(ctrlG);

        ungroupItem.setAccelerator(ctrlU);

        toFrontItem.setAccelerator(sCtrlF);

        setBackwardItem(reorder);
        setAccelaratorCtrlF(reorder);
}