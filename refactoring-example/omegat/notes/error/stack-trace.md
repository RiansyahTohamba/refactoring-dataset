# solusi
tinggal di ubah urutannya wkwk

# konten
kenapa Core.getMainWindow() nya null ? 
null pada saat kapan ?

editorFont = Core.getMainWindow().getApplicationFont();


java.lang.NullPointerException: Cannot invoke "org.omegat.gui.main.IMainWindow.getApplicationFont()" 
because the return value of "org.omegat.core.Core.getMainWindow()" is null 




# dimanakah mainwindow ini mulai di set ?
saat di initializeGUI, mainwindow di set
~~~
...
MainWindow me = new MainWindow();
mainWindow = me;
...
~~~
berarti sudah sampai getApplicationFont sudah berhasil
tinggal ada sesuatu yang membuatnya null ditengah jalan

FontFallbackMarker dipanggil kapan ?

ketika mau register FontFallBack, ketahuan deh bahwa MainWindow nya masih null.

registerMarker(new FontFallbackMarker());


86056: Error: 	at org.omegat.gui.editor.mark.FontFallbackMarker.<init>(FontFallbackMarker.java:47) 

86056: Error: 	at org.omegat.core.Core.regMarker(Core.java:374) 

86056: Error: 	at org.omegat.core.Core.initializeGUI(Core.java:321) 

86056: Error: 	at org.omegat.Main.runGUI(Main.java:296) 

86056: Error: 	at org.omegat.Main.main(Main.java:183) 