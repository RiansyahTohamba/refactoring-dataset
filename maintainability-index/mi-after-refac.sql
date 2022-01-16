-- class dulu
-- fungsi kemudian 

-- seluruhnya
create view fungsi_terdampak as 
-- part 1
select name,mindex from miexp2_sample where 
name like 'AlignPanelController' or
name like 'AlignFilePickerController' or
name like 'BrowseTaasCollectionsController' or

select name,mindex from miexp2_sample where 
name like 'CalcMatchStatistics' or
name like 'DictionariesTextArea' or
name like 'EntryListPane' or
name like 'EditorController' or
name like 'GlossaryTextArea' or
name like 'Loader' or
name like 'Main' or
name like 'PreferencesWindowController' or
name like 'ProjectUICommands'


-- part 2
select name,mindex from miexp2_sample where 
name like 'RealProject' or
name like 'SegmentPropertiesTableView' or
name like 'SegmentPropertiesArea' or
name like 'SegmentBuilder' or
name like 'SearchWindowController' or
name like 'ScriptingWindow' or
name like 'TransTipsMarker' or
name like 'TaaSPlugin' 

-- part 3 method
select name,mindex from miexp2_sample where 
name like 'activateEntry(CaretPosition)' or
name like 'calcPerFile(%' or
name like 'createActiveSegmentElement(%' or

select name,mindex from miexp2_sample where 
name like 'JMenuBar createMenuBar(%' or
name like 'RealProject selectProjectConsoleMode(%' or

name like 'DefaultMutableTreeNode createNodeTree(%' or
name like 'DictionariesTextArea(%' or
name like 'doSearch(%' or
name like 'doReplace(%' or
name like 'doExternalCommand(%' or
name like 'doWikiImport(%' or
name like 'EditorController(%' or
name like 'EntryListPane(%' or
name like 'MatchStatCounts forFile(%' 
name like 'getMarksForEntry(%' or
name like 'GlossaryTextArea(%' or


select name,mindex from miexp2_sample where 
name like 'install(SegmentPropertiesArea' or
name like 'loadSourceFiles(%' or
name like 'mergeTMX(%' or
name like 'booelan onEntry(%'

select name,mindex from miexp2_sample where 
name like 'onApplicationStartup(%' or
name like 'projectCreateMED(%' or
name like 'projectEditProperties(%' or
name like 'projectImportFiles(String, File[], boolean)' or
name like 'projectOpen(File, boolean)' or
name like 'rebaseAndCommitProject(%' or
name like 'replaceEditText(%' or
name like 'int runConsoleAlign(%' or
name like 'setFoundResult(%' or


-- ini belum ada
name like 'populatePaneMenu(%' or
-- 

select name,mindex from miexp2_sample where 
name like 'saveProject(boolean%' or
name like 'SearchWindowController(%' or
name like 'SegmentPropertiesArea(%' or
name like 'showCreateGlossaryEntryDialog(%' or
name like 'showStat(%'

-- ini spesial karena duplikat
name like 'show(Window, Class%' or
name like 'show(Component%' or
name like 'show()' 

-- show(Window punya PreferencesWindowController
-- show(Component% milik AlignPanelController dan AlignFilePickerController harus dicek dulu di GUI nya
-- 