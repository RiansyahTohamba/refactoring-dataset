editorController.md

di extract menjadi kelas baru, EditorEntry

EditorEntry tidak bermasalah.

# apakah ini method baru ?
setCurrentTrans, goToEntry, setFilter 

## cek dengan "line history"
## setCurrentTrans, ini saya yang buat. jadi smell DCO baru.

## gotoEntry
gotoEntry memang  belum direfactor
goToEntry nya punya 2 params, dengan 4 paramater

EditorController	gotoEntry(
## 2 params
java.lang.String, 
org.omegat.core.data.EntryKey, 
## ini yang diinclude di body methodnya
	java.util.List, 
	org.omegat.core.data.TMXEntry, 
	int, 
	org.omegat.core.data.SourceTextEntry
)	6.625

## setFilter
belum dikerjakan