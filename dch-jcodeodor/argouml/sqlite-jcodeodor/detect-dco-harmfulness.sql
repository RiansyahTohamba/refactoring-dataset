-- jika ingin buat view 
-- 
CREATE VIEW detect_dcoh as
select 
meth.id as method_id,
type.name as type,meth.name,
value
from 
(select ms.id , ms.parent,ms.name,value 
	from Measurables as ms left join Measures as me 
on ms.id = me.measurable
where key ='DispersedCouplingHarmfulness' and value > 0
order by value desc) as meth
left join 
(select id ,name from Measurables  where type ='type' ) as type
on meth.parent = type.id
-- before-refactoring.sqlite
-- hasilnya 77 method,ada di detect-dispersed.sql, order berdasarkan kesamaan type
"8220"	"AlignFilePickerController"	"main(org.omegat.gui.align.AlignFilePickerController, String[])"
"8216"	"AlignFilePickerController"	"show(java.awt.Component, javax.swing.TransferHandler, javax.swing.JFrame, org.omegat.gui.align.AlignFilePicker)"
"8328"	"AlignPanelController"	"show(int, java.lang.String, java.awt.Component, java.awt.event.ActionListener, java.awt.event.ActionListener, java.awt.event.ActionListener, javax.swing.table.TableCellRenderer, java.awt.event.ActionListener, java.awt.event.ActionListener, java.awt.event.ActionListener, java.awt.event.ActionListener, javax.swing.event.ListSelectionListener, java.awt.event.ActionListener, java.awt.event.ActionListener, java.awt.event.ActionListener, java.awt.event.ActionListener, java.awt.event.ActionListener, java.awt.event.ActionListener, java.awt.event.ActionListener, java.awt.event.ActionListener, java.awt.event.ActionListener, java.awt.event.ActionListener, java.lang.Exception)"
"5791"	"BaseStatisticsPanel"	"generateTableDisplay(java.awt.Font, String[][], java.lang.String, String[], org.omegat.gui.stat.TitledTablePanel)"
"3156"	"BrowseTaasCollectionsController"	"show(java.util.List, org.omegat.util.Language, org.omegat.util.Language, org.omegat.gui.glossary.taas.BrowseTaasCollectionsController.CollectionsTable)"
"3096"	"CalcMatchStatistics"	"calcPerFile(String[][], java.lang.String, java.lang.String, java.lang.String, org.omegat.core.statistics.MatchStatCounts, int, org.omegat.core.statistics.MatchStatCounts, org.omegat.core.data.IProject.FileInfo)"
"3098"	"CalcMatchStatistics"	"forFile(boolean, boolean, org.omegat.core.data.SourceTextEntry, org.omegat.core.data.IProject.FileInfo, java.util.List, org.omegat.core.statistics.StatCount, org.omegat.core.statistics.MatchStatCounts)"
"7858"	"Core"	"initializeGUI(org.omegat.gui.main.MainWindow, java.util.Map, org.omegat.gui.glossary.GlossaryTextArea, org.omegat.core.threads.SaveThread)"
"9554"	"DictionariesTextArea"	"DictionariesTextArea(java.lang.String, org.omegat.gui.main.IMainWindow)"
"5245"	"DocumentFilter3"	"isPossible(int, int, int, org.omegat.gui.editor.Document3, int, javax.swing.text.Document, int, int, org.omegat.gui.editor.SegmentBuilder, java.lang.String, org.omegat.core.data.ProtectedPart)"
"5304"	"EditorController"	"EditorController(org.omegat.gui.main.MainWindow)"
"5331"	"EditorController"	"showStat(boolean, int, java.text.NumberFormat, int, int, org.omegat.core.data.IProject, org.omegat.core.data.IProject.FileInfo, org.omegat.core.statistics.StatisticsInfo, org.omegat.gui.main.MainWindowUI.StatusBarMode, java.lang.String, java.lang.StringBuilder, org.omegat.core.data.SourceTextEntry)"
"5354"	"EditorController"	"gotoEntry(org.omegat.core.data.EntryKey, java.lang.String, org.omegat.core.data.TMXEntry, int, org.omegat.core.data.SourceTextEntry, java.util.List)"
"5325"	"EditorController"	"activateEntry(org.omegat.gui.editor.IEditor.CaretPosition, org.omegat.core.data.SourceTextEntry, int, org.omegat.gui.editor.SegmentBuilder, int, org.omegat.core.data.TMXEntry)"
"5359"	"EditorController"	"replaceEditText(java.lang.String, org.omegat.gui.editor.SegmentBuilder, int, int, org.omegat.gui.editor.mark.CalcMarkersThread)"
"5439"	"EditorPopups"	"init(org.omegat.gui.editor.EditorController)"
"5443"	"EditorPopups.SpellCheckerPopup"	"addItems(org.omegat.util.Token, javax.swing.JMenuItem, org.omegat.util.Token, boolean, org.omegat.gui.editor.SegmentBuilder, boolean, javax.swing.text.JTextComponent, int, javax.swing.JPopupMenu, javax.swing.text.AbstractDocument, int, int, int, java.lang.String, java.lang.String, java.util.List, java.lang.String)"
"895"	"Entry"	"detectTags(java.lang.String, int, java.lang.String, int, org.omegat.filters3.Element, org.omegat.filters3.Tag, org.omegat.filters3.Element, boolean, org.omegat.filters3.Element, boolean, int, org.omegat.filters3.xml.XMLContentBasedTag, int, org.omegat.filters3.Tag, org.omegat.filters3.xml.XMLContentBasedTag, int, boolean)"
"4448"	"EntryListPane"	"EntryListPane()"
"2958"	"ExternalFinderItemEditorController"	"show(java.awt.Dimension, java.awt.Window, javax.swing.JDialog)"
"2942"	"ExternalFinderItemURLEditorController"	"show(java.awt.Window, javax.swing.JDialog)"
"1800"	"FilenamePatternsEditorController"	"show(java.util.List, javax.swing.event.ListSelectionListener, org.omegat.gui.dialogs.FilenamePatternsEditor, javax.swing.table.AbstractTableModel)"
"3952"	"FilterEditor"	"initComponents(java.awt.GridBagConstraints)"
"4395"	"FilterMaster"	"translateFile(java.lang.String, java.lang.String, java.lang.String, org.omegat.filters2.FilterContext, org.omegat.filters2.ITranslateCallback, org.omegat.filters2.master.FilterMaster.LookupInformation, org.omegat.filters2.IFilter, java.io.File, java.io.File, java.lang.Exception)"
"9294"	"FilterVisitor"	"endup(org.htmlparser.Node, int, java.lang.String, int, org.htmlparser.Node, java.lang.String, boolean, int, boolean, org.htmlparser.Tag, java.lang.String, boolean, java.lang.String, int, java.lang.StringBuilder, java.util.List, org.htmlparser.Node, org.htmlparser.Tag, int, int, boolean, int, int, java.lang.String)"
"6808"	"FindGlossaryThread"	"search(org.omegat.tokenizer.ITokenizer, java.util.List, org.omegat.gui.glossary.GlossarySearcher)"
"6865"	"GlossaryTextArea"	"GlossaryTextArea(java.lang.String, org.omegat.gui.main.IMainWindow)"
"6878"	"GlossaryTextArea"	"populatePaneMenu(java.lang.String, javax.swing.JPopupMenu, javax.swing.JMenuItem, javax.swing.JMenuItem)"
"6871"	"GlossaryTextArea"	"setFoundResult(java.util.List, org.omegat.core.data.SourceTextEntry, org.omegat.gui.glossary.GlossaryEntry)"
"6877"	"GlossaryTextArea"	"showCreateGlossaryEntryDialog(org.omegat.gui.dialogs.CreateGlossaryEntry, java.lang.String, java.awt.Frame, java.io.File, org.omegat.core.data.ProjectProperties, org.omegat.gui.dialogs.CreateGlossaryEntry)"
"2830"	"IssueProvidersSelectorController"	"show(java.awt.Window, java.util.Set, javax.swing.JDialog, javax.swing.JCheckBox, org.omegat.gui.issues.IssueProvidersSelectorPanel, javax.swing.JCheckBox, org.omegat.gui.issues.IIssueProvider)"
"2836"	"IssuesPanelController"	"init(java.lang.String, int, int, java.awt.event.MouseAdapter, java.lang.NumberFormatException)"
"2885"	"IssuesPanelController.IssueLoader"	"done(java.util.List, boolean, int[], javax.swing.table.TableRowSorter, java.lang.Exception)"
"2261"	"LanguageToolConfigurationController"	"initGui()"
"8897"	"Main"	"runConsoleAlign(java.lang.String, org.omegat.core.data.RealProject, java.util.Map, java.util.Map, java.lang.String, java.util.Map.Entry)"
"8898"	"Main"	"selectProjectConsoleMode(org.omegat.core.data.ProjectProperties, boolean, org.omegat.core.data.RealProject, java.lang.Exception)"
"8896"	"Main"	"runCreatePseudoTranslateTMX(org.omegat.core.data.PrepareTMXEntry, java.lang.String, java.lang.String, org.omegat.CLIParameters.PSEUDO_TRANSLATE_TYPE, org.omegat.core.data.ProjectProperties, org.omegat.core.data.RealProject, java.util.List, java.util.Map, java.io.IOException, org.omegat.core.data.SourceTextEntry)"
"8893"	"Main"	"runGUI(java.lang.String, java.lang.reflect.Field, java.awt.Toolkit, java.lang.Class, java.lang.Throwable, java.lang.Exception)"
"7342"	"MainWindowMenuHandler"	"projectSingleCompileMenuItemActionPerformed(java.lang.String, java.util.List, java.lang.String)"
"7351"	"MainWindowMenuHandler"	"projectAccessCurrentSourceDocumentMenuItemActionPerformed(java.io.File, java.lang.String, java.lang.String, int)"
"7352"	"MainWindowMenuHandler"	"projectAccessCurrentTargetDocumentMenuItemActionPerformed(java.io.File, java.lang.String, java.lang.String, int)"
"7355"	"MainWindowMenuHandler"	"projectExitMenuItemActionPerformed(boolean)"
"7361"	"MainWindowMenuHandler"	"editOverwriteSourceMenuItemActionPerformed(java.lang.String)"
"7362"	"MainWindowMenuHandler"	"editInsertSourceMenuItemActionPerformed(java.lang.String)"
"7363"	"MainWindowMenuHandler"	"editExportSelectionMenuItemActionPerformed(org.omegat.core.data.TMXEntry, java.lang.String, org.omegat.core.data.SourceTextEntry)"
"3883"	"MatchesTextArea"	"populateContextMenu(javax.swing.JMenuItem, boolean, org.omegat.core.matching.NearString, org.omegat.core.matching.NearString, int, javax.swing.JPopupMenu, java.lang.String, int, java.lang.StringBuilder, javax.swing.JMenuItem)"
"1666"	"NewTeamProject"	"initComponents(java.awt.GridBagConstraints)"
"6705"	"PreferencesWindowController"	"createNodeTree(org.omegat.gui.preferences.PreferencesWindowController.HideableNode, org.omegat.gui.preferences.PreferencesWindowController.HideableNode, org.omegat.gui.preferences.PreferencesWindowController.HideableNode, org.omegat.gui.preferences.PreferencesWindowController.HideableNode, org.omegat.gui.preferences.PreferencesWindowController.HideableNode)"
"6704"	"PreferencesWindowController"	"show(javax.swing.tree.DefaultMutableTreeNode, java.awt.Window, java.lang.Class, javax.swing.ActionMap, java.lang.String, javax.swing.KeyStroke, javax.swing.InputMap, javax.swing.tree.DefaultTreeCellRenderer)"
"9386"	"ProjectFilesListController"	"ProjectFilesListController(int, java.lang.String, org.omegat.gui.main.MainWindow, org.omegat.util.gui.TableColumnSizer)"
"1162"	"ProjectTMX.Loader"	"onEntry(org.omegat.core.data.PrepareTMXEntry, org.omegat.core.data.TMXEntry.ExternalLinked, org.omegat.util.TMXReader2.ParsedTu, org.omegat.util.TMXReader2.ParsedTuv, org.omegat.util.TMXReader2.ParsedTuv, boolean, java.util.List, java.lang.String, org.omegat.core.data.EntryKey, long, java.util.List, boolean, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, long)"
"7249"	"ProjectUICommands"	"projectImportFiles(boolean, java.lang.String, File[], java.io.IOException)"
"7251"	"ProjectUICommands"	"doWikiImport(java.lang.String, java.lang.String, java.lang.Exception)"
"7237"	"ProjectUICommands"	"projectOpen(java.io.File, java.io.File, boolean, org.omegat.util.gui.OmegaTFileChooser)"
"7234"	"ProjectUICommands"	"projectCreateMED(java.lang.String, java.io.File, java.io.File, int, org.omegat.gui.dialogs.ChooseMedProject, java.lang.Exception)"
"7242"	"ProjectUICommands"	"projectEditProperties(int, org.omegat.gui.dialogs.ProjectPropertiesDialog, org.omegat.core.data.ProjectProperties)"
"1339"	"RealProject"	"mergeTMX(java.lang.String, org.omegat.core.data.ProjectTMX, java.lang.String, org.omegat.core.data.ProjectTMX, java.lang.StringBuilder, org.omegat.core.data.ProjectTMX, org.madlonkay.supertmxmerge.StmProperties)"
"1332"	"RealProject"	"doExternalCommand(java.lang.String, java.lang.String, org.omegat.core.data.CommandVarExpansion, java.lang.Process, org.omegat.core.threads.CommandMonitor, java.lang.Throwable, org.omegat.core.threads.CommandMonitor, java.io.IOException)"
"1334"	"RealProject"	"saveProject(boolean, java.lang.String, java.lang.String, java.lang.Exception, org.omegat.core.KnownException)"
"1342"	"RealProject"	"loadSourceFiles(java.lang.String, org.omegat.core.data.IProject.FileInfo, org.omegat.filters2.IFilter, java.io.File, long, org.omegat.filters2.master.FilterMaster, long, java.util.List, org.omegat.core.data.RealProject.LoadFilesCallback, java.lang.Error)"
"1403"	"RealProject.AlignFilesCallback"	"addTranslation(java.lang.String, java.lang.String, java.lang.String, org.omegat.core.data.PrepareTMXEntry, java.lang.String, boolean, org.omegat.filters2.IFilter, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.util.List, java.util.List, short, org.omegat.core.data.ParseEntry.ParseEntryResult)"
"1395"	"RealProject.LoadFilesCallback"	"addSegment(java.util.List, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, org.omegat.core.data.EntryKey, String[], boolean, short, org.omegat.core.data.SourceTextEntry)"
"7065"	"SRX"	"initDefaults(java.lang.String, java.lang.String, boolean, java.util.List, java.util.List, gen.core.segmentation.Srx, java.net.URL, gen.core.segmentation.Languagemap, gen.core.segmentation.Languagerule, java.lang.Exception, gen.core.segmentation.Rule)"
"4893"	"ScriptingWindow"	"createMenuBar(javax.swing.JMenuItem, javax.swing.JMenu)"
"4516"	"SearchWindowController"	"doSearch(java.lang.String, java.lang.String, org.omegat.core.search.SearchExpression, org.omegat.core.search.Searcher, java.io.File, org.omegat.gui.search.EntryListPane, java.lang.String)"
"4503"	"SearchWindowController"	"SearchWindowController(org.omegat.core.search.SearchMode, java.util.Date, java.util.Date, java.util.Date, javax.swing.SpinnerNumberModel, java.util.Calendar)"
"4514"	"SearchWindowController"	"doReplace(java.lang.String, org.omegat.gui.search.EntryListPane)"
"5071"	"SegmentBuilder"	"createActiveSegmentElement(org.omegat.core.data.TMXEntry, java.lang.String, org.omegat.core.data.TMXEntry, boolean, long, org.omegat.util.Language, java.util.Map, int, java.lang.OutOfMemoryError, java.util.Map.Entry)"
"6341"	"SegmentPropertiesArea"	"SegmentPropertiesArea(java.lang.Class, org.omegat.gui.main.IMainWindow, java.lang.String, java.lang.ClassNotFoundException)"
"6385"	"SegmentPropertiesTableView"	"install(org.omegat.gui.properties.SegmentPropertiesArea)"
"5819"	"StatisticsWindow"	"StatisticsWindow(org.omegat.gui.stat.PerFileMatchStatisticsPanel, javax.swing.JComponent, org.omegat.gui.stat.StatisticsPanel, org.omegat.gui.stat.MatchStatisticsPanel, org.omegat.gui.stat.StatisticsWindow.STAT_TYPE, java.awt.Frame)"
"3131"	"TaaSGlossary"	"search(java.lang.String, org.omegat.util.Language, org.omegat.util.Language, java.util.List, gen.taas.TaasExtractionResult, java.lang.String)"
"6924"	"TransTipsMarker"	"getMarksForEntry(boolean, org.omegat.core.data.SourceTextEntry, java.lang.String, java.lang.String, java.util.List, java.lang.String, java.util.List, java.util.List, org.omegat.gui.glossary.GlossaryEntry)"
"6586"	"org.omegat.externalfinder.ExternalFinder$1"	"onLoad(javax.swing.JMenu, java.util.List, java.awt.Component, org.omegat.util.gui.MenuItemPager, org.omegat.externalfinder.item.IExternalFinderItemMenuGenerator, javax.swing.JMenuItem)"
"8383"	"org.omegat.gui.align.AlignPanelController$11"	"actionPerformed(java.awt.event.ActionEvent, java.util.List, javax.swing.JFileChooser, java.io.File, java.lang.Exception)"
"3127"	"org.omegat.gui.glossary.taas.TaaSPlugin$1"	"onApplicationStartup(javax.swing.JMenuItem)"
"7324"	"org.omegat.gui.main.MainWindowUI$2"	"mouseClicked(org.omegat.gui.main.MainWindowUI.StatusBarMode, java.lang.String, java.lang.String, StatusBarMode[], java.awt.event.MouseEvent)"

-- after-refactoring.sqlite
1329	VersioningProject	rebaseAndCommitProject(java.util.List, boolean, java.lang.String, java.lang.String, java.lang.StringBuilder, java.lang.String, java.io.File)	10.0
1545	RealProject	startSave(boolean, java.lang.String, java.lang.String)	10.0
7567	Core	regMarker()	10.0
2853	org.omegat.gui.glossary.taas.TaaSPlugin$1	onApplicationStartup(javax.swing.JMenuItem)	8.875
5344	EditorController	EditorController(org.omegat.gui.main.MainWindow)	8.875
7565	Core	initializeGUI(org.omegat.core.threads.SaveThread, org.omegat.gui.main.MainWindow, java.util.Map)	8.875
7566	Core	addOnMainWindow(org.omegat.gui.glossary.GlossaryTextArea, org.omegat.gui.main.MainWindow)	8.875
7772	MainWindowMenuHandler	projectSingleCompileMenuItemActionPerformed(java.lang.String, java.util.List, java.lang.String)	8.875
9040	Main	runConsoleAlign(java.lang.String, org.omegat.core.data.RealProject, java.util.Map, java.util.Map, java.lang.String, java.util.Map.Entry)	8.875
1362	ProjectTMX.Loader	onEntry(org.omegat.core.data.PrepareTMXEntry, org.omegat.core.data.TMXEntry.ExternalLinked, org.omegat.util.TMXReader2.ParsedTuv, org.omegat.util.TMXReader2.ParsedTuv, org.omegat.util.TMXReader2.ParsedTu, boolean, java.lang.String, java.lang.String, org.omegat.core.data.EntryKey, java.util.List, java.lang.String, boolean, java.util.List, java.lang.String, java.lang.String, long, long, int)	7.75
3079	ExternalFinderItemEditorController	show(java.awt.Dimension, java.awt.Window, javax.swing.JDialog)	7.75
3903	FilterEditor	initComponents(java.awt.GridBagConstraints)	7.75
4473	EntryListPane	EntryListPane()	7.75
5350	EditorController	setCurrentTrans(org.omegat.gui.editor.SegmentBuilder, org.omegat.core.data.TMXEntry)	7.75
7086	TransTipsMarker	getMarksForEntry(boolean, org.omegat.core.data.SourceTextEntry, java.lang.String, java.lang.String, java.util.List, java.util.List, java.lang.String, java.util.List, org.omegat.gui.glossary.GlossaryEntry)	7.75
9041	Main	selectProjectConsoleMode(org.omegat.core.data.ProjectProperties, boolean, org.omegat.core.data.RealProject, java.lang.Exception)	7.75
1690	NewTeamProject	initComponents(java.awt.GridBagConstraints)	6.625
1827	FilenamePatternsEditorController	show(java.util.List, javax.swing.event.ListSelectionListener, javax.swing.table.AbstractTableModel, org.omegat.gui.dialogs.FilenamePatternsEditor)	6.625
2289	LanguageToolConfigurationController	initGui()	6.625
2824	CalcMatchStatistics	iterateAllSegments(boolean, boolean, org.omegat.core.data.SourceTextEntry, org.omegat.core.statistics.MatchStatCounts, java.util.List, org.omegat.core.statistics.StatCount)	6.625
3307	IssuesPanelController.IssueLoader	done(java.util.List, boolean, int[], javax.swing.table.TableRowSorter, java.lang.Exception)	6.625
4001	MatchesTextArea	populateContextMenu(javax.swing.JMenuItem, boolean, org.omegat.core.matching.NearString, org.omegat.core.matching.NearString, int, javax.swing.JPopupMenu, java.lang.String, java.lang.StringBuilder, javax.swing.JMenuItem, int)	6.625
5285	DocumentFilter3	isPossible(int, int, int, org.omegat.gui.editor.Document3, int, int, javax.swing.text.Document, int, java.lang.String, org.omegat.gui.editor.SegmentBuilder, org.omegat.core.data.ProtectedPart)	6.625
5402	EditorController	gotoEntry(java.lang.String, org.omegat.core.data.EntryKey, java.util.List, org.omegat.core.data.SourceTextEntry, int, org.omegat.core.data.TMXEntry)	6.625
5432	EditorController	setFilter(org.omegat.gui.editor.IEditorFilter, int, org.omegat.core.data.IProject, int, org.omegat.core.data.SourceTextEntry, org.omegat.gui.editor.Document3)	6.625
5507	EditorPopups	init(org.omegat.gui.editor.EditorController)	6.625
5931	StatisticsWindow	StatisticsWindow(org.omegat.gui.stat.MatchStatisticsPanel, org.omegat.gui.stat.PerFileMatchStatisticsPanel, javax.swing.JComponent, org.omegat.gui.stat.StatisticsPanel, org.omegat.gui.stat.StatisticsWindow.STAT_TYPE, java.awt.Frame)	6.625
6738	org.omegat.externalfinder.ExternalFinder$1	onLoad(javax.swing.JMenu, java.util.List, java.awt.Component, org.omegat.util.gui.MenuItemPager, org.omegat.externalfinder.item.IExternalFinderItemMenuGenerator, javax.swing.JMenuItem)	6.625
6864	PreferencesWindowController	setTeamNode(org.omegat.gui.preferences.PreferencesWindowController.HideableNode, org.omegat.gui.preferences.PreferencesWindowController.HideableNode)	6.625
6971	FindGlossaryThread	search(org.omegat.tokenizer.ITokenizer, java.util.List, org.omegat.gui.glossary.GlossarySearcher)	6.625
7028	GlossaryTextArea	GlossaryTextArea(java.lang.String, org.omegat.gui.main.IMainWindow)	6.625
7781	MainWindowMenuHandler	projectAccessCurrentSourceDocumentMenuItemActionPerformed(java.io.File, java.lang.String, java.lang.String, int)	6.625
7782	MainWindowMenuHandler	projectAccessCurrentTargetDocumentMenuItemActionPerformed(java.io.File, java.lang.String, java.lang.String, int)	6.625
7793	MainWindowMenuHandler	editOverwriteSourceMenuItemActionPerformed(java.lang.String)	6.625
7794	MainWindowMenuHandler	editInsertSourceMenuItemActionPerformed(java.lang.String)	6.625
8340	AlignFilePickerController	main(String[])	6.625
8547	org.omegat.gui.align.AlignPanelController$18	actionPerformed(java.awt.event.ActionEvent, java.util.List, java.io.File, javax.swing.JFileChooser, java.lang.Exception)	6.625
9697	DictionariesTextArea	DictionariesTextArea(java.lang.String, org.omegat.gui.main.IMainWindow)	6.625
45	Entry	detectTags(java.lang.String, int, java.lang.String, int, org.omegat.filters3.Element, org.omegat.filters3.Tag, org.omegat.filters3.Element, boolean, org.omegat.filters3.Element, boolean, int, org.omegat.filters3.xml.XMLContentBasedTag, int, org.omegat.filters3.Tag, org.omegat.filters3.xml.XMLContentBasedTag, int, boolean)	5.5
1326	VersioningProject	commitSourceFiles(java.lang.Exception)	5.5
1597	RealProject.AlignFilesCallback	addTranslation(java.lang.String, java.lang.String, java.lang.String, org.omegat.core.data.PrepareTMXEntry, java.lang.String, boolean, org.omegat.filters2.IFilter, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.List, org.omegat.core.data.ParseEntry.ParseEntryResult, boolean, java.util.List, short)	5.5
3258	IssuesPanelController	init(int, java.lang.String, int, java.awt.event.MouseAdapter, java.lang.NumberFormatException)	5.5
5511	EditorPopups.SpellCheckerPopup	addItems(javax.swing.JMenuItem, org.omegat.util.Token, org.omegat.util.Token, javax.swing.JPopupMenu, int, boolean, javax.swing.text.JTextComponent, boolean, org.omegat.gui.editor.SegmentBuilder, java.util.List, javax.swing.text.AbstractDocument, int, java.lang.String, int, int, java.lang.String, java.lang.String)	5.5
6447	SRX	initDefaults(java.lang.String, java.util.List, boolean, java.lang.String, java.net.URL, gen.core.segmentation.Srx, java.util.List, gen.core.segmentation.Languagemap, gen.core.segmentation.Languagerule, java.lang.Exception, gen.core.segmentation.Rule)	5.5
7754	org.omegat.gui.main.MainWindowUI$2	mouseClicked(org.omegat.gui.main.MainWindowUI.StatusBarMode, java.lang.String, java.lang.String, StatusBarMode[], java.awt.event.MouseEvent)	5.5
9039	Main	runCreatePseudoTranslateTMX(org.omegat.core.data.PrepareTMXEntry, java.lang.String, java.lang.String, org.omegat.CLIParameters.PSEUDO_TRANSLATE_TYPE, org.omegat.core.data.ProjectProperties, org.omegat.core.data.RealProject, java.util.List, java.util.Map, java.io.IOException, org.omegat.core.data.SourceTextEntry)	5.5
9529	ProjectFilesListController	ProjectFilesListController(java.lang.String, int, org.omegat.gui.main.MainWindow, org.omegat.util.gui.TableColumnSizer)	5.5
2857	TaaSGlossary	search(java.lang.String, org.omegat.util.Language, org.omegat.util.Language, java.lang.String, java.util.List, gen.taas.TaasExtractionResult)	4.375
4420	FilterMaster	translateFile(java.lang.String, java.lang.String, java.lang.String, org.omegat.filters2.FilterContext, org.omegat.filters2.ITranslateCallback, org.omegat.filters2.master.FilterMaster.LookupInformation, org.omegat.filters2.IFilter, java.io.File, java.io.File, java.lang.Exception)	4.375
5903	BaseStatisticsPanel	generateTableDisplay(java.awt.Font, String[][], String[], java.lang.String, org.omegat.gui.stat.TitledTablePanel)	4.375
9437	FilterVisitor	endup(org.htmlparser.Node, int, java.lang.String, int, org.htmlparser.Node, java.lang.String, boolean, int, boolean, org.htmlparser.Tag, java.lang.String, boolean, java.lang.String, int, java.lang.StringBuilder, java.util.List, org.htmlparser.Node, org.htmlparser.Tag, int, int, boolean, int, int, java.lang.String)	4.375
1589	RealProject.LoadFilesCallback	addSegment(java.lang.String, java.util.List, java.lang.String, String[], java.lang.String, java.lang.String, java.lang.String, org.omegat.core.data.EntryKey, java.lang.String, short, boolean, org.omegat.core.data.SourceTextEntry)	3.25
3063	ExternalFinderItemURLEditorController	show(java.awt.Window, javax.swing.JDialog)	3.25
3252	IssueProvidersSelectorController	show(java.awt.Window, javax.swing.JDialog, java.util.Set, org.omegat.gui.issues.IssueProvidersSelectorPanel, javax.swing.JCheckBox, javax.swing.JCheckBox, org.omegat.gui.issues.IIssueProvider)	3.25
7795	MainWindowMenuHandler	editExportSelectionMenuItemActionPerformed(java.lang.String, org.omegat.core.data.TMXEntry, org.omegat.core.data.SourceTextEntry)	3.25
9036	Main	runGUI(java.lang.String, java.lang.reflect.Field, java.awt.Toolkit, java.lang.Class, java.lang.Throwable, java.lang.Exception)	3.25