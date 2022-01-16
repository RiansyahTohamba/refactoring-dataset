-- class dulu
-- fungsi kemudian 

-- seluruhnya
create view fungsi_terdampak as 
-- part 1
select name,mindex from micompiled_sample where 
Name like 'AlignPanelController' or
Name like 'AlignFilePickerController' or
Name like 'BrowseTaasCollectionsController' or
Name like 'CalcMatchStatistics' or
Name like 'DictionariesTextArea' or
Name like 'EntryListPane' or
Name like 'EditorController' or
Name like 'GlossaryTextArea' or
Name like 'Loader' or
Name like 'Main' or
Name like 'PreferencesWindowController' or
Name like 'ProjectUICommands'


-- part 2
select name,mindex from micompiled_sample where 
Name like 'VersioningProject' or
Name like 'TransTipsMarker' or
Name like 'TaaSPlugin' or
Name like 'SegmentPropertiesTableView' or
Name like 'SegmentPropertiesArea' or
Name like 'SegmentBuilder' or
Name like 'SearchWindowController' or
Name like 'ScriptingWindow' or
Name like 'RealProject'

-- part xx method
select name,mindex from micompiled_sample where 
Name like 'activateEntry(CaretPosition)' or
Name like 'calcPerFile(%' or
Name like 'createActiveSegmentElement(%' or
Name like 'JMenuBar createMenuBar(%' or
Name like 'DefaultMutableTreeNode createNodeTree(%' or
Name like 'DictionariesTextArea(%' or
Name like 'void doSearch(%' or
Name like 'void doReplace(%'

-- part xx method
select name,mindex from micompiled_sample where 
Name like 'doExternalCommand(%' or
Name like 'doWikiImport(%' or
Name like 'EditorController(%' or
Name like 'EntryListPane(%' or
Name like 'MatchStatCounts forFile(%' or
Name like '%getMarksForEntry(%' or
Name like 'GlossaryTextArea(%' or
Name like 'install(%' or
Name like 'loadSourceFiles(%' or
Name like 'mergeTMX(%'
order by name

-- part xx method
select name,mindex from micompiled_sample where 
Name like 'onApplicationStartup(%' or
Name like 'booelan onEntry(%' or
Name like 'projectCreateMED(%' or
Name like 'projectEditProperties(%' or
Name like 'projectImportFiles(String, File[], boolean)' or
Name like 'projectOpen(File, boolean)' or
Name like 'rebaseAndCommitProject(%' or
Name like 'replaceEditText(%' or
Name like 'int runConsoleAlign(%' or
order by name


-- ini belum ada
Name like 'populatePaneMenu(%' or
-- 

select name,mindex from micompiled_sample where 
Name like 'saveProject(boolean%' or
Name like 'SearchWindowController(%' or
Name like 'SegmentPropertiesArea(%' or
Name like 'setFoundResult(%'
order by name

-- part xx method
select name,mindex from micompiled_sample where 
Name like 'showCreateGlossaryEntryDialog(%' or
Name like 'showStat(%' or
Name like 'startSave(%'

-- ini spesial karena duplikat
Name like 'show(Window, Class%' or
Name like 'show(Component%' or
Name like 'show()' 

-- show(Window punya PreferencesWindowController
-- show(Component% milik AlignPanelController dan AlignFilePickerController harus dicek dulu di GUI nya
-- 