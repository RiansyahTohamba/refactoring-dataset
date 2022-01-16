AlignPanelController.md

# show()
extract method terjadi 22 kali.

## 16 method listener baru
comparisonListener = ..
algorithmListener = ..
highlightListener = ..
alasan : separate setter didalam method.
tapi tidak semua di extract


# kenapa saya tidak refactoring setter 'frame' nya ?
A: karena tidak ada if-else

## 6 method yang lain ?

# pertanyaan
## cakupan dari method yang akan di extract ?
ukurannya dari mana ?


## apakah metric FANOUT + CINT memandu refactoring ku?
tidak, hal pertama yang saya lakukan pada case ini adalah 
1. mengecek logika yang ada di 
2. 

## perubahan metric
### sebelum
	// CDISP = 0.968
	// CINT = 32
    // FANOUT = 31
### sesudah
	// CDISP = 1
	// CINT = 6
    // FANOUT = 6

