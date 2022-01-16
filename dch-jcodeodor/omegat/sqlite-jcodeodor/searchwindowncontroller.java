/*
doSearch(
java.lang.String, 
java.lang.String, 
org.omegat.core.search.SearchExpression, org.omegat.core.search.Searcher, 
java.io.File, 
org.omegat.gui.search.EntryListPane, 
java.lang.String)
*/
private void doSearch() {
        UIThreadsUtil.mustBeSwingThread();
        if (thread != null) {
            // stop old search thread
            thread.fin();
        }

        EntryListPane viewer = (EntryListPane) form.m_viewer;

        String queryString = form.m_searchField.getEditor().getItem().toString();
        queryString = StringUtil.normalizeUnicode(queryString);

        HistoryManager.addSearchItem(queryString);
        form.m_searchField.setModel(new DefaultComboBoxModel<>(HistoryManager.getSearchItems()));
        form.m_searchField.requestFocus();

        viewer.reset();
        String root = null;
        if (form.m_rbDir.isSelected()) {
            // make sure it's a valid directory name
            root = form.m_dirField.getText();
            if (!root.endsWith(File.separator)) {
                root += File.separator;
            }
            File f = new File(root);
            if (!f.exists() || !f.isDirectory()) {
                String error = StringUtil.format(OStrings.getString("SW_ERROR_BAD_DIR"),
                        form.m_dirField.getText());
                form.m_viewer.setText(error);
                Log.log(error);
                return;
            }
            // if (m_dirCB.isSelected()) {
            // Preferences.setPreference(Preferences.SEARCHWINDOW_DIR, root);
            // // need to explicitly save preferences because project
            // // might not be open
            // Preferences.save();
            // }
        }

        // save user preferences
        savePreferences();

        if (StringUtil.isEmpty(queryString)) {
            form.setTitle(OStrings.getString("SW_TITLE"));
        } else {
            form.setTitle(queryString + " - OmegaT");
        }

        SearchExpression s = new SearchExpression();
        s.mode = mode;
        s.text = queryString;
        s.rootDir = root;
        s.recursive = form.m_recursiveCB.isSelected();

        switch (mode) {
        case SEARCH:
            if (form.m_searchExactSearchRB.isSelected()) {
                s.searchExpressionType = SearchExpression.SearchExpressionType.EXACT;
            } else if (form.m_searchKeywordSearchRB.isSelected()) {
                s.searchExpressionType = SearchExpression.SearchExpressionType.KEYWORD;
            } else if (form.m_searchRegexpSearchRB.isSelected()) {
                s.searchExpressionType = SearchExpression.SearchExpressionType.REGEXP;
            }
            s.caseSensitive = form.m_searchCase.isSelected();
            s.spaceMatchNbsp = form.m_searchSpaceMatchNbsp.isSelected();
            s.glossary = mode == SearchMode.SEARCH ? form.m_cbSearchInGlossaries.isSelected() : false;
            s.memory = mode == SearchMode.SEARCH ? form.m_cbSearchInMemory.isSelected() : true;
            s.tm = mode == SearchMode.SEARCH ? form.m_cbSearchInTMs.isSelected() : false;
            s.allResults = mode == SearchMode.SEARCH ? form.m_allResultsCB.isSelected() : true;
            s.fileNames = mode == SearchMode.SEARCH ? form.m_fileNamesCB.isSelected() : true;
            s.searchSource = form.m_searchSource.isSelected();
            s.searchTarget = form.m_searchTranslation.isSelected();
            if (form.m_searchTranslatedUntranslated.isSelected()) {
                s.searchTranslated = true;
                s.searchUntranslated = true;
            } else if (form.m_searchTranslated.isSelected()) {
                s.searchTranslated = true;
                s.searchUntranslated = false;
            } else if (form.m_searchUntranslated.isSelected()) {
                s.searchTranslated = false;
                s.searchUntranslated = true;
            }
            s.widthInsensitive = form.m_fullHalfWidthInsensitive.isSelected();
            s.excludeOrphans = form.m_excludeOrphans.isSelected();
            s.replacement = null;
            break;
        case REPLACE:
            if (form.m_replaceExactSearchRB.isSelected()) {
                s.searchExpressionType = SearchExpression.SearchExpressionType.EXACT;
            } else if (form.m_replaceRegexpSearchRB.isSelected()) {
                s.searchExpressionType = SearchExpression.SearchExpressionType.REGEXP;
            }
            s.caseSensitive = form.m_replaceCase.isSelected();
            s.spaceMatchNbsp = form.m_replaceSpaceMatchNbsp.isSelected();
            s.glossary = false;
            s.memory = true;
            s.tm = false;
            s.allResults = true;
            s.fileNames = Core.getProject().getProjectFiles().size() > 1;
            s.searchSource = false;
            s.searchTarget = false;
            s.searchTranslated = false;
            s.searchUntranslated = false;
            s.replaceTranslated = true;
            s.replaceUntranslated = form.m_replaceUntranslated.isSelected();
            s.widthInsensitive = form.m_fullHalfWidthInsensitive.isSelected();
            s.excludeOrphans = true;
            s.replacement = form.m_replaceField.getEditor().getItem().toString();
            break;
        }

        s.searchNotes = form.m_searchNotesCB.isSelected();
        s.searchComments = form.m_searchCommentsCB.isSelected();
        s.searchAuthor = form.m_authorCB.isSelected();
        s.author = form.m_authorField.getText();
        s.searchDateAfter = form.m_dateFromCB.isSelected();
        s.dateAfter = dateFromModel.getDate().getTime();
        s.searchDateBefore = form.m_dateToCB.isSelected();
        s.dateBefore = dateToModel.getDate().getTime();
        s.numberOfResults = mode == SearchMode.SEARCH ? ((Integer) form.m_numberOfResults.getValue())
                : Integer.MAX_VALUE;

        Searcher searcher = new Searcher(Core.getProject(), s);
        // start the search in a separate thread
        thread = new SearchThread(this, searcher);
        thread.start();
    }

